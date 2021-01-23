package constellation.controllers;

import java.io.File;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.facepp.http.HttpRequests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;

import constellation.dto.GroupCreate;

@RestController
public class GroupController {
	@RequestMapping(method=RequestMethod.GET,value="constellation/group/create")
	@ApiOperation(httpMethod="GET",value="Create a group.One Group may contains at most 10000 persons,The app under developer status can  NOT create more than 5 groups.")
	public GroupCreate create() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		GroupCreate groupCreate = null;
		// Load the directory as a resource　for testing.
		URL dir_url = ClassLoader.getSystemResource("GroupCreate.json");
		groupCreate = mapper.readValue(new File(dir_url.toURI()), GroupCreate.class);
		return groupCreate;
	}
}
