package constellation;

import java.util.Random;
import java.util.List;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.io.File;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import constellation.consts.ApiConsts;
import constellation.dto.PersonCreate;
/**
 * Post fixture data for person stream based on Spring-XD
 * @author yangboz
 * @see https://bitbucket.org/jakubmarciniak/spring-xd-cars-stream/src/7363837f0a92400a81cd02d0010b3572c259f55a/src/main/java/CarsStream.java?at=master
 */
public class PersonStreamApplication {

	private Random random = new Random();
	private List<PersonCreate> personCreates;

	public static void main(String[] args) throws Exception {
		PersonStreamApplication personStreamApp = new PersonStreamApplication();
		personStreamApp.loadData();
		for (int i = 0; i < 10; i++) {
			personStreamApp.postRandomData(i);
			Thread.sleep(20);
		}
	}

	private void postRandomData(int index) throws Exception {
		URL url = new URL(ApiConsts.SPRING_XD_STREAM_URL);
		HttpURLConnection xdConnection = (HttpURLConnection) url.openConnection();
		xdConnection.setRequestMethod("POST");
		xdConnection.setDoOutput(true);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String postData = ow.writeValueAsString(personCreates.get(index));
		System.out.println("postData(personCreate):"+postData);
		DataOutputStream wr = new DataOutputStream(xdConnection.getOutputStream());
		wr.writeBytes(postData);
		wr.flush();
		wr.close();
		xdConnection.getResponseCode();
	}	

	public void loadData() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		// Load the directory as a resource　for testing.
		URL dir_url = ClassLoader.getSystemResource("PersonCreates.json");
		//Enable feature
		mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
		personCreates = mapper.readValue(new File(dir_url.toURI()), new TypeReference<List<PersonCreate>>() {});
		System.out.print("personCreates:"+personCreates.toString());
	}

}
