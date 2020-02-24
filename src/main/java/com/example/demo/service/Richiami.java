package com.example.demo.service;

import com.example.demo.data.GenericResponse;
import com.example.demo.request.RequestLogin;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class Richiami {

    private  final String token = "6e8b8871-026e-4da5-8871-6f30aa0a90af";

    private static final Logger logger = LoggerFactory.getLogger(Richiami.class);

    public static void genericResponse(String url) {
        RestTemplate restTemplate = new RestTemplate();

        GenericResponse response = restTemplate.getForObject(url, GenericResponse.class);

        System.out.println(response.getStatusMessage() + " " + response.getData());
    }


    public Object login(String url){
        RestTemplate restTemplate = new RestTemplate();

        RequestLogin rq = new RequestLogin();
        rq.setEmail("francesco@gmail.com");
        rq.setPassword("CIao1234567");

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,rq,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

        return body.getData();

    }

    public  void informazioni(String url) {

        try {
            String token = ("Bearer " + login("http://localhost:8080/login"));
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8080/informazioni");


            httpPost.addHeader(new BasicHeader("Authorization",token));

            CloseableHttpResponse response = client.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity());

            logger.info(json);

            client.close();
        } catch (IOException e) {
            logger.error("Eccezione IOException in informazioni ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in informazioni ", e);
        }

    }



/*
    public static void informazioni(String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,entity,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

    }
*/

    public  void modifica(String url) {

/*        RequestModificaUtente requestModificaUtente = new RequestModificaUtente();
        requestModificaUtente.setNome("francescooooooo");
        requestModificaUtente.setCognome("salviaaaaa");*/

        Map<String, String> map = new HashMap<>();
        map.put("nome","francesco");
        map.put("cognome","salvia");



        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ token);

        HttpEntity< Map<String, String>> entity2 = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,entity2,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

    }


    public  void modificaPassword(String url) {

        Map<String, String> map = new HashMap<>();
        map.put("password","CIao1234567");

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer "+ token);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,entity,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

    }

    public  void logOut(String url) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer "+ token);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,entity,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());
    }


}
