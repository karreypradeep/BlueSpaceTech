package com.bluespacetech.contactgroup.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bluespacetech.ContactServiceApplication;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.exceptions.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactServiceApplication.class)
@WebAppConfiguration
public class ContactGroupServiceTest {

	@Autowired
	private ContactGroupService contactGroupService;

	@Test
	public void testCreateContactGroup() throws BusinessException {
		
		ContactGroup contactGroup1 = new ContactGroup();
		contactGroup1.setName("Group1");
		ContactGroup contactGroup2 = new ContactGroup();
		contactGroup2.setName("Group2");
		ContactGroup contactGroup3 = new ContactGroup();
		contactGroup3.setName("Group3");
		
		contactGroupService.createContactGroup(contactGroup1);
		contactGroupService.createContactGroup(contactGroup2);
		contactGroupService.createContactGroup(contactGroup3);

	}

}
