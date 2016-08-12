package com.bluespacetech.contact.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bluespacetech.ContactServiceApplication;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contactgroup.ContactGroup;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.service.GroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactServiceApplication.class)
@WebAppConfiguration
public class ContactServiceTest {
	
	@Autowired
	private ContactService contactService;

	@Autowired
	private GroupService groupService;

	@Test
	public void testCreateContact() throws BusinessException {
		
		Contact contact1 = new Contact();
		contact1.setFirstName("firstname1");
		contact1.setLastName("lastname1");
		contact1.setEmail("emailid1");
		
		Contact contact2 = new Contact();
		contact2.setFirstName("firstname2");
		contact2.setLastName("lastname2");
		contact2.setEmail("emailid2");

		Contact contact3 = new Contact();
		contact3.setFirstName("firstname3");
		contact3.setLastName("lastname3");
		contact3.setEmail("emailid3");

		Contact contact4 = new Contact();
		contact4.setFirstName("firstname4");
		contact4.setLastName("lastname4");
		contact4.setEmail("emailid4");

		Contact contact5 = new Contact();
		contact5.setFirstName("firstname5");
		contact5.setLastName("lastname5");
		contact5.setEmail("emailid5");

		Contact contact6 = new Contact();
		contact6.setFirstName("firstname6");
		contact6.setLastName("lastname6");
		contact6.setEmail("emailid6");
		
		Group group1 = new Group();
		group1.setName("Group1");
		groupService.createGroup(group1);
		
		Group group2 = new Group();
		group2.setName("Group2");
		groupService.createGroup(group2);
		
		ContactGroup contactGroup1 = new ContactGroup();
		contactGroup1.setContact(contact1);
		contactGroup1.setGroup(group1);
		contactGroup1.setActive(true);
		contactGroup1.setUnSubscribed(false);
		
		ContactGroup contactGroup2 = new ContactGroup();
		contactGroup2.setContact(contact2);
		contactGroup2.setGroup(group2);
		contactGroup2.setActive(true);
		contactGroup2.setUnSubscribed(false);
		
		ContactGroup contactGroup3 = new ContactGroup();
		contactGroup3.setContact(contact3);
		contactGroup3.setGroup(group1);
		contactGroup3.setActive(true);
		contactGroup3.setUnSubscribed(false);
		
		ContactGroup contactGroup4 = new ContactGroup();
		contactGroup4.setContact(contact4);
		contactGroup4.setGroup(group2);
		contactGroup4.setActive(true);
		contactGroup4.setUnSubscribed(false);
		
		ContactGroup contactGroup5 = new ContactGroup();
		contactGroup5.setContact(contact5);
		contactGroup5.setGroup(group1);
		contactGroup5.setActive(true);
		contactGroup5.setUnSubscribed(false);
		
		ContactGroup contactGroup6 = new ContactGroup();
		contactGroup6.setContact(contact6);
		contactGroup6.setGroup(group2);
		contactGroup6.setActive(true);
		contactGroup6.setUnSubscribed(false);
		
		contact1.getContactGroups().add(contactGroup1);
		contact2.getContactGroups().add(contactGroup2);
		contact3.getContactGroups().add(contactGroup3);
		contact4.getContactGroups().add(contactGroup4);
		contact5.getContactGroups().add(contactGroup5);
		contact6.getContactGroups().add(contactGroup6);
		
		
		contactService.createContact(contact1);
		contactService.createContact(contact2);
		contactService.createContact(contact3);
		contactService.createContact(contact4);
		contactService.createContact(contact5);
		contactService.createContact(contact6);
		
	}

}
