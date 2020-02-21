package com.example.demo.service;

import com.example.demo.request.RequestLogin;
import com.example.demo.data.GenericResponse;
import com.example.demo.request.RequestModificaUtente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class Richiami {

    private static final String token = "96522c8e-7aac-47ef-a4bd-cfbd8dd5fd52";

    private static final Logger logger = LoggerFactory.getLogger(Richiami.class);

    public static void genericResponse(String url) {
        RestTemplate restTemplate = new RestTemplate();

        GenericResponse response = restTemplate.getForObject(url, GenericResponse.class);

        System.out.println(response.getStatusMessage() + " " + response.getData());
    }


    public static Object login(String url){
        RestTemplate restTemplate = new RestTemplate();

        RequestLogin rq = new RequestLogin();
        rq.setEmail("francesco@gmail.com");
        rq.setPassword("CIao1234");

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,rq,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

        return body.getData();

    }


    public static void informazioni(String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,entity,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

    }

    public static void modifica(String url) {

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


    public static void modificaPassword(String url) {

        Map<String, String> map = new HashMap<>();
        map.put("password","CIao123456");


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ token);


        HttpEntity<Map<String, String>> entity2 = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url,entity2,GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

    }

}
