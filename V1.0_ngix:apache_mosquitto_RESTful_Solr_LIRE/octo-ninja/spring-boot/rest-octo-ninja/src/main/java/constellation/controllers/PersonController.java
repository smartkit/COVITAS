package constellation.controllers;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.ApiOperation;

import constellation.dto.PersonCreate;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.SecureRandom;

import constellation.consts.ApiConsts;

@RestController
public class PersonController {
	//
	private SecureRandom random = new SecureRandom();
	public String nextSessionId(){
		return new BigInteger(130,random).toString(32);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="constellation/person/create")
	@ApiOperation(httpMethod="GET",value="Create a person.One Person may contain at most 10000 Faces,The app under developer status can  NOT create more than 100 persons.")
	public PersonCreate create() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		PersonCreate personCreate = null;
		/*
		// Load the directory as a resource　for testing.
		URL dir_url = ClassLoader.getSystemResource("PersonCreate.json");
		personCreate = mapper.readValue(new File(dir_url.toURI()), PersonCreate.class);
		return personCreate;
		*/
		//
		HttpRequests httpRequests = new HttpRequests(ApiConsts.API_KEY_FACEPP, ApiConsts.API_SECRET_FACEPP, true, true);
		JSONObject result = null;
//		
		try {
//			System.out.println(Charset.forName("UTF-8").name());
			System.out.println("FacePlusPlus API Test:");
			//detection/detect
			result = httpRequests.detectionDetect(new PostParameters().setUrl("http://cn.faceplusplus.com/wp-content/themes/faceplusplus/assets/img/demo/9.jpg"));
			System.out.println(result);
			System.out.println(result.getJSONArray("face").getJSONObject(0).getJSONObject("position").getJSONObject("center"));
			//-----------------Person-----------------
			//person/create
			System.out.println("\nperson/create");
//			for (int i = 0; i < result.getJSONArray("face").length(); ++i)
			for (int i = 0; i < 1; ++i)	
			{
				JSONObject jsonObj = httpRequests.personCreate(new PostParameters().setPersonName("person_"+nextSessionId()));
				System.out.println("jsonObj:"+jsonObj.toString());
				personCreate = mapper.readValue(new StringReader(jsonObj.toString()), PersonCreate.class);
//				System.out.println("personCreate:"+personCreate.toString());
			}
		} catch(FaceppParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
//			try {
//				for (int i = 1; i < result.getJSONArray("face").length(); ++i) {
//					httpRequests.personDelete(new PostParameters().setPersonName("person_"+i));
//					httpRequests.facesetDelete(new PostParameters().setFacesetName("faceset_"+i));
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			} catch (FaceppParseException e) {
//				e.printStackTrace();
//			}
		}
		return personCreate;
	}
}
