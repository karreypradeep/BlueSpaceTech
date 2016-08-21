/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contactgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.entity.ContactGroupPK;
import com.bluespacetech.contactgroup.repository.ContactGroupRepository;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * Class for ContactGroupService
 *
 * @author Sandeep created date 25-June-2015
 */
@Service
public class ContactGroupServiceImpl implements ContactGroupService {
	
	@Autowired
	private ContactGroupRepository contactGroupRepository;
	
	@Override
	public ContactGroup getContactGroupById(ContactGroupPK contactGroupPK) {
		return contactGroupRepository.findOne(contactGroupPK);
	}

	@Override
	public ContactGroup createContactGroup(ContactGroup contactGroup) throws BusinessException {
		return contactGroupRepository.save(contactGroup);
	}

	@Override
	public void deleteContactGroup(ContactGroupPK contactGroupPK) throws BusinessException {
		contactGroupRepository.delete(contactGroupPK);
	}

	@Override
	public List<ContactGroup> findAll() {
		return contactGroupRepository.findAll();
	}

	@Override
	public ContactGroup updateContactGroup(ContactGroup contactGroup) throws BusinessException {
		return contactGroupRepository.save(contactGroup);
	}

}
