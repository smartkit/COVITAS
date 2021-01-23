package info.smartkit.tas.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import info.smartkit.tas.pojo.MessageResponse;
import info.smartkit.tas.pojo.PFMessage;
import info.smartkit.tas.pojo.PFMessageResponse;
import info.smartkit.tas.service.ITASServices;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.cert.X509Certificate;

/**
 * Created by smartkit on 28/04/2017.
 */
@Service
public class TASService implements ITASServices {

    private static final Logger LOG = LoggerFactory.getLogger(TASService.class);

    private RestTemplate restTemplate = new RestTemplate();

    final private String API_URL = "https://www.personalityforge.com/api/chat/?";
    final private String API_KEY = "OICIWPR2SUubw8ZY";
    final private String API_SECRET = "yXNCf1LNd8P7cy6K2AEYXsUdU6b5L46N";

    @Override
    public MessageResponse personalityForge(PFMessage message) throws IOException {
//@see:http://blog.codeleak.pl/2016/02/skip-ssl-certificate-verification-in.html
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
//
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(csf)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory();
//
//        requestFactory.setHttpClient(httpClient);
//
//        RestTemplate restTemplate = new RestTemplate(requestFactory);

//        To easily manipulate URLs / path / params / etc., you can use Spring's UriComponentsBuilder class. It's cleaner that manually concatenating strings and it takes care of the URL encoding for you:

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonMessage = ow.writeValueAsString(message);
        LOG.info("rawJsonMessage:"+jsonMessage);
        //HmacUtils impl,https://www.personalityforge.com/chatbot-api-php.php
        String hashedJsonMessage = HmacUtils.hmacSha1Hex(API_SECRET,jsonMessage);
        LOG.info("hashedJsonMessage:"+hashedJsonMessage);
        // Secure API call
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("apiKey", API_KEY)
                .queryParam("hash", hashedJsonMessage)
                .queryParam("message", jsonMessage);
//Simple API call
//        https://www.personalityforge.com/api/chat/?apiKey=sampleElyggY577I&chatBotID=6
// &message=How+are+you+doing+today%3F&externalID=abc-639184572&firstName=Tugger&lastName=Sufani&gender=m
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
//                .queryParam("apiKey", API_KEY)
//                .queryParam("chatBotID", message.getMessage().getChatbotID())
//                .queryParam("message", message.getMessage().getMessage())
//                .queryParam("externalID", message.getUser().getExternalID())
//                .queryParam("firstName", message.getUser().getFirstName())
//                .queryParam("lastName", message.getUser().getLastName())
//                .queryParam("gender", message.getUser().getGender());
//
        HttpEntity<?> entity = new HttpEntity<>(headers);
        //
        ResponseEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

       LOG.info("personalityForge Response body String:"+response.getBody());
       //
        ObjectMapper objectMapper = new ObjectMapper();
        PFMessageResponse pfMessageResponse = objectMapper.readValue(response.getBody().toString(),PFMessageResponse.class);
        LOG.info("PFMessageResponse.message:"+pfMessageResponse.getMessage());
        return pfMessageResponse.getMessage();
    }
}
