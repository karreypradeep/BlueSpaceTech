package com.bluespacetech;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@IntegrationComponentScan
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


@MessagingGateway
interface ContactWriter {
    @Gateway(requestChannel = "output")
    void write(String rn);
}

interface ContactChannels {
    @Output
    MessageChannel output();
}

@FeignClient("contactservice")
interface ContactReader {
    @RequestMapping(method = RequestMethod.GET, value = "/contacts")
    List<Contact> read();
}

@RestController
@RequestMapping("/contacts")
class ContactApiGatewayRestController {

    private final ContactReader contactReader;

    private final ContactWriter contactWriter;

    @Autowired
    public ContactApiGatewayRestController(final ContactReader contactReader, final ContactWriter contactWriter) {
	this.contactReader = contactReader;
	this.contactWriter = contactWriter;
    }

    public Collection<String> fallback() {
	return new ArrayList<>();
    }

    // @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(method = RequestMethod.GET, value = "/names")
    public ResponseEntity<List<Contact>> names() {
	List<Contact> contacts = this.contactReader.read();
	return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody final Contact contact) {
	this.contactWriter.write(contact.getFirstName());
    }

    /*@Autowired
    private RestTemplate restTemplate;

    @RequestMapping("")
    public ResponseEntity<Contact[]>  getContactEmails() {
	Contact[] contacts  = restTemplate.getForObject("http://contactservice/contacts", Contact[].class);
	System.out.println(contacts.length);
	return new ResponseEntity<Contact[]>(contacts, HttpStatus.OK);
    }*/
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