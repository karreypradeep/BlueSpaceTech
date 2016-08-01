package com.bluespacetech;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class CollaboratorWebAppApplication {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
	return new RestTemplate();
    }

    public static void main(final String[] args) {
	SpringApplication.run(CollaboratorWebAppApplication.class, args);
    }
}

@RestController
@RequestMapping("/contacts")
class ContactApiGatewayRestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("")
    public ResponseEntity<Contact[]>  getContactEmails() {
	Contact[] contacts  = restTemplate.getForObject("http://contactservice/contacts", Contact[].class);
	System.out.println(contacts.length);
	return new ResponseEntity<Contact[]>(contacts, HttpStatus.OK);
	//return responseEntity.getBody().getContent().stream().map(Contact::getEmail).collect(Collectors.toList());
    }
}

class Contact {

    private Long id;
    private Long version;
    private Timestamp lastUpdatedDate;
    private String lastUpdatedUser;
    private Timestamp creationDate;
    private String createdUser;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * @return the firstName
     */
    public String getFirstName() {
	return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(final String firstName) {
	this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
	return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(final String lastName) {
	this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
	this.email = email;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
	this.id = id;
    }

    /**
     * @return the version
     */
    public Long getVersion() {
	return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final Long version) {
	this.version = version;
    }

    /**
     * @return the lastUpdatedDate
     */
    public Timestamp getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    /**
     * @param lastUpdatedDate
     *            the lastUpdatedDate to set
     */
    public void setLastUpdatedDate(final Timestamp lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * @return the lastUpdatedUser
     */
    public String getLastUpdatedUser() {
	return lastUpdatedUser;
    }

    /**
     * @param lastUpdatedUser
     *            the lastUpdatedUser to set
     */
    public void setLastUpdatedUser(final String lastUpdatedUser) {
	this.lastUpdatedUser = lastUpdatedUser;
    }

    /**
     * @return the creationDate
     */
    public Timestamp getCreationDate() {
	return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(final Timestamp creationDate) {
	this.creationDate = creationDate;
    }

    /**
     * @return the createdUser
     */
    public String getCreatedUser() {
	return createdUser;
    }

    /**
     * @param createdUser
     *            the createdUser to set
     */
    public void setCreatedUser(final String createdUser) {
	this.createdUser = createdUser;
    }
}