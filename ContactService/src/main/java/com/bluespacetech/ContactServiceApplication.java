package com.bluespacetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ContactServiceApplication {

    public static void main(final String[] args) {
	SpringApplication.run(ContactServiceApplication.class, args);
    }
}