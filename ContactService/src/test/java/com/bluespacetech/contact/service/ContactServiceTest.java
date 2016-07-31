package com.bluespacetech.contact.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bluespacetech.ContactServiceApplication;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.service.ContactGroupService;
import com.bluespacetech.core.exceptions.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactServiceApplication.class)
@WebAppConfiguration
public class ContactServiceTest {
	
	@Autowired
	private ContactGroupService contactGroupService;
	
	@Autowired
	private ContactService contactService;

	@Test
	public void testCreateContact() throws BusinessException {
		
		ContactGroup contactGroup1 = contactGroupService.getContactGroupById(1L);
		ContactGroup contactGroup2 = contactGroupService.getContactGroupById(2L);
		
		Contact contact1 = new Contact();
		contact1.setFirstName("firstname1");
		contact1.setLastName("lastname1");
		contact1.setEmail("emailid1");
		contact1.setContactGroup(contactGroup1);
		
		Contact contact2 = new Contact();
		contact2.setFirstName("firstname2");
		contact2.setLastName("lastname2");
		contact2.setEmail("emailid2");
		contact2.setContactGroup(contactGroup1);
		
		Contact contact3 = new Contact();
		contact3.setFirstName("firstname3");
		contact3.setLastName("lastname3");
		contact3.setEmail("emailid3");
		contact3.setContactGroup(contactGroup1);
		
		Contact contact4 = new Contact();
		contact4.setFirstName("firstname4");
		contact4.setLastName("lastname4");
		contact4.setEmail("emailid4");
		contact4.setContactGroup(contactGroup2);
		
		Contact contact5 = new Contact();
		contact5.setFirstName("firstname5");
		contact5.setLastName("lastname5");
		contact5.setEmail("emailid5");
		contact5.setContactGroup(contactGroup2);
		
		Contact contact6 = new Contact();
		contact6.setFirstName("firstname6");
		contact6.setLastName("lastname6");
		contact6.setEmail("emailid6");
		contact6.setContactGroup(contactGroup2);
		
		contactService.createContact(contact1);
		contactService.createContact(contact2);
		contactService.createContact(contact3);
		contactService.createContact(contact4);
		contactService.createContact(contact5);
		contactService.createContact(contact6);

	}

}
