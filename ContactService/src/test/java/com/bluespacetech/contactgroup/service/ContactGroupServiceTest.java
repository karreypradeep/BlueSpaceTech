package com.bluespacetech.contactgroup.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bluespacetech.ContactServiceApplication;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.service.GroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactServiceApplication.class)
@WebAppConfiguration
public class ContactGroupServiceTest {

	@Autowired
	private GroupService groupService;

	@Test
	public void testCreateContactGroup() throws BusinessException {
		
		Group group1 = new Group();
		group1.setName("Group1");
		Group group2 = new Group();
		group2.setName("Group2");
		
		groupService.createGroup(group1);
		groupService.createGroup(group2);

	}

}
