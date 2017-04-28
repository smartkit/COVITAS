package info.smartkit.service.impl;

import info.smartkit.pojo.PFMessage;
import info.smartkit.service.ITASServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smartkit on 28/04/2017.
 */
public class TASService implements ITASServices {

    private static final Logger LOG = LoggerFactory.getLogger(TASService.class);

    private RestTemplate restTemplate = new RestTemplate();

    final private String API_URL = "https://www.personalityforge.com/api/chat/?";
    final private String API_KEY = "OICIWPR2SUubw8ZY";
    final private String API_SECRET = "yXNCf1LNd8P7cy6K2AEYXsUdU6b5L46N";

    @Override
    public Object personalityForge(PFMessage message) {

//        To easily manipulate URLs / path / params / etc., you can use Spring's UriComponentsBuilder class. It's cleaner that manually concatenating strings and it takes care of the URL encoding for you:

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        String jsonMessage = "";
        //TODO:HmacUtils impl,https://www.personalityforge.com/chatbot-api-php.php

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("apiKey", API_KEY)
                .queryParam("hash", API_SECRET)
                .queryParam("message", jsonMessage);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

       LOG.info("personalityForge Response:",response);
        return response;
    }
}
