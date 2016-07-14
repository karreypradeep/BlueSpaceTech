/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.contactgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.repository.ContactGroupRepository;
import com.bluespacetech.core.exceptions.BusinessException;


/**
 * class for ContactGroupService
 *
 * @author pradeep created date 25-June-2015
 */
@Service
//@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
//ApplicationException.class })
//@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class ContactGroupServiceImpl implements ContactGroupService {


    @Autowired
    private ContactGroupRepository contactGroupRepository;

    public static void validateContactGroup(final ContactGroup contactGroup) throws BusinessException {
	if ((contactGroup.getName() == null) || (contactGroup.getName().trim().length() == 0)) {
	    throw new BusinessException("ContactGroup Name is Mandatory.");
	}
    }

    @Override
    @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN')  or ((hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_EMPLOYEE')) and (hasAuthority('CREATE_PERSON') ))")
    public ContactGroup createContactGroup(final ContactGroup contactGroup) throws BusinessException {
	ContactGroupServiceImpl.validateContactGroup(contactGroup);
	final ContactGroup newContactGroup = contactGroupRepository.save(contactGroup);
	return newContactGroup;
    }

    @Override
    // @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN')  or ((hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_EMPLOYEE')) and (hasAuthority('DELETE_PERSON')))")
    public void deleteContactGroup(final Long contactGroupId) throws BusinessException {
	contactGroupRepository.delete(contactGroupId);
    }

    @Override
    //@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN')  or ((hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_EMPLOYEE')) and (hasAuthority('ACCESS_PERSON')))")
    public ContactGroup getContactGroupById(final Long contactGroupId) {
	final ContactGroup contactGroup = contactGroupRepository.findOne(contactGroupId);
	return contactGroup;
    }

    @Override
    //@PreAuthorize("hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('UPDATE_PERSON')")
    public ContactGroup updateContactGroup(final ContactGroup contactGroup) throws BusinessException {
	ContactGroupServiceImpl.validateContactGroup(contactGroup);
	final ContactGroup updatedContactGroup = contactGroupRepository.save(contactGroup);
	return updatedContactGroup;
    }

    /* (non-Javadoc)
     * @see com.bluespacetech.contactGroup.service.ContactGroupService#findByName(java.lang.String)
     */
    @Override
    public List<ContactGroup> findByName(final String email) {
	return contactGroupRepository.findByNameLike(email);
    }

    /* (non-Javadoc)
     * @see com.bluespacetech.contactGroup.service.ContactGroupService#findAll()
     */
    @Override
    public List<ContactGroup> findAll() {
	return contactGroupRepository.findAll();
    }

}
