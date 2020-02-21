package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.demo.service.Richiami.*;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);


        genericResponse("http://localhost:8080/hello");


       // login("http://localhost:8080/login");

     //   informazioni("http://localhost:8080/informazioni");

      // modifica("http://localhost:8080/modifica");

        modificaPassword("http://localhost:8080/modificapassword");


    }


}


