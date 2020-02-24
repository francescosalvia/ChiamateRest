package com.example.demo;

import com.example.demo.data.GenericResponse;
import com.example.demo.request.RequestLogin;
import com.example.demo.request.RequestModificaUtente;
import com.example.demo.service.Richiami;
import net.minidev.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class DemoApplicationTests {


    public Object login(String url) {
        RestTemplate restTemplate = new RestTemplate();

        RequestLogin rq = new RequestLogin();
        rq.setEmail("francesco@gmail.com");
        rq.setPassword("CIao1234567");

        ResponseEntity<GenericResponse> response = restTemplate.postForEntity(url, rq, GenericResponse.class);

        GenericResponse body = response.getBody();

        logger.info(body.getData() + " " + body.getStatusMessage());

        return body.getData();

    }

    private static final Logger logger = LoggerFactory.getLogger(Richiami.class);

    @Test
    public void testInformazioni() {
        try {
            String token = ("Bearer " + login("http://localhost:8080/login"));
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8080/informazioni");


            httpPost.addHeader(new BasicHeader("Authorization", token));

            CloseableHttpResponse response = client.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity());

            logger.info(json);

            assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);

            client.close();
        } catch (IOException e) {
            logger.error("Eccezione IOException in informazioni ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in informazioni ", e);
        }
    }

    @Test
    public void modifica() {

        try {


            JSONObject json3 = new JSONObject();
            json3.put("nome", "francesco");
            json3.put("cognome", "savia");

            String token = ("Bearer " + login("http://localhost:8080/login"));
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8080/modifica");

            httpPost.addHeader(new BasicHeader("Authorization", token));
            httpPost.addHeader("content-type", "application/json");  // MANCAVA QUESTO per inviare body


            StringEntity params = new StringEntity(json3.toString());
            httpPost.setEntity(params);


            CloseableHttpResponse response = client.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity());

            logger.info(json);


            client.close();
        } catch (IOException e) {
            logger.error("Eccezione IOException in modifica ", e);
        } catch (ParseException e) {
            logger.error("Eccezione ParseException in modifica ", e);
        }

    }


}

