package com.bluespacetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ContactServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ContactServiceApplication.class, args);
	}
	
}
