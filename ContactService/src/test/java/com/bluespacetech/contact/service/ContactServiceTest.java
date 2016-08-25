package com.bluespacetech.contact.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bluespacetech.ContactServiceApplication;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.contactgroup.entity.ContactGroup;
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
	public void testGetBySearchCriteria(){
		ContactSearchCriteria contactSearchCriteria = new ContactSearchCriteria();
		contactSearchCriteria.getGroupNames().add("Group1");
		List<Contact> contacts =  contactService.findBySearchCriteria(contactSearchCriteria);
		for (Contact contact : contacts) {
			for (ContactGroup contactGroup : contact.getContactGroups()) {
				System.out.println("-----------------------------------------");
				System.out.println(contactGroup.getContact().toString());
				System.out.println(contactGroup.getGroup().toString());
				System.out.println("-----------------------------------------");
			}
		}
	}
	
	public void testUpdateContact() throws BusinessException {
		Contact contact = contactService.getContactById(1L);
		contact.setFirstName("Update 5");
		contact.setLastName("Update 5");
		contact.setEmail("Update 5");
		contact.getContactGroups().remove(0);
		for (ContactGroup contactGroup : contact.getContactGroups()) {
			contactGroup.setUnSubscribed(true);
		}
		/*Group group1 = groupService.getGroupById(1L);
		ContactGroup contactGroup1 = new ContactGroup();
		contactGroup1.setContact(contact);
		contactGroup1.setGroup(group1);
		contactGroup1.setActive(true);
		contactGroup1.setUnSubscribed(false);
		contact.getContactGroups().add(contactGroup1);*/
		contactService.updateContact(contact);
	}
	
	public void testDeleteContact() throws BusinessException {
		Contact contact = contactService.getContactById(2L);		
		contactService.deleteContact(contact.getId());
	}

	public void testCreateContact() throws BusinessException {

		Contact contact1 = new Contact();
		contact1.setFirstName("firstname1");
		contact1.setLastName("lastname1");
		contact1.setEmail("emailid1");

		Group group1 = groupService.getGroupById(1L);
		Group group2 = groupService.getGroupById(2L);
		
		/*Group group1 = new Group();
		group1.setName("Group1");
		group1.setComments("Group1 for testing.");
		groupService.createGroup(group1);

		Group group2 = new Group();
		group2.setName("Group2");
		group2.setComments("Group2 for testing.");
		groupService.createGroup(group2);*/

		ContactGroup contactGroup1 = new ContactGroup();
		contactGroup1.setContact(contact1);
		contactGroup1.setGroup(group1);
		contactGroup1.setActive(true);
		contactGroup1.setUnSubscribed(false);

		ContactGroup contactGroup2 = new ContactGroup();
		contactGroup2.setContact(contact1);
		contactGroup2.setGroup(group2);
		contactGroup2.setActive(true);
		contactGroup2.setUnSubscribed(false);

		contact1.getContactGroups().add(contactGroup1);
		contact1.getContactGroups().add(contactGroup2);

		contactService.createContact(contact1);
	}

}
