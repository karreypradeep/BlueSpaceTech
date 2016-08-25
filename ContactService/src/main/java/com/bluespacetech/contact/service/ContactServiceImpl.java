/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.repository.ContactRepository;
import com.bluespacetech.contact.repository.ContactRepositoryCustom;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * class for ContactService
 *
 * @author pradeep created date 25-June-2015
 */
@Service
// @Transactional(rollbackFor = { Exception.class, RuntimeException.class,
// BusinessException.class,
// ApplicationException.class })
// @PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ContactRepositoryCustom contactRepositoryCustom;

	public static void validateContact(final Contact contact) throws BusinessException {
		if ((contact.getEmail() == null) || (contact.getEmail().trim().length() == 0)) {
			throw new BusinessException("Contact Email is Mandatory.");
		}
	}

	@Override
	// @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or
	// ((hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_EMPLOYEE'))
	// and (hasAuthority('CREATE_PERSON') ))")
	public Contact createContact(final Contact contact) throws BusinessException {
		ContactServiceImpl.validateContact(contact);
		final Contact newContact = contactRepository.save(contact);
		return newContact;
	}

	@Override
	// @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or
	// ((hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_EMPLOYEE'))
	// and (hasAuthority('DELETE_PERSON')))")
	public void deleteContact(final Long contactId) throws BusinessException {
		contactRepository.delete(contactId);
	}

	@Override
	// @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or
	// ((hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_EMPLOYEE'))
	// and (hasAuthority('ACCESS_PERSON')))")
	public List<Contact> findByFirstNameOrLastName(final String firstName, final String lastName) {
		return contactRepository.findByFirstNameLikeOrLastNameLike(firstName, lastName);
	}

	@Override
	// @PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or
	// ((hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_EMPLOYEE'))
	// and (hasAuthority('ACCESS_PERSON')))")
	public Contact getContactById(final Long contactId) {
		final Contact contact = contactRepository.findOne(contactId);
		return contact;
	}

	@Override
	// @PreAuthorize("hasAuthority('ACC_TYPE_ADMIN') or
	// hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('UPDATE_PERSON')")
	public Contact updateContact(final Contact contact) throws BusinessException {
		ContactServiceImpl.validateContact(contact);
		final Contact updatedContact = contactRepository.save(contact);
		return updatedContact;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluespacetech.contact.service.ContactService#findByEmail(java.lang.
	 * String)
	 */
	@Override
	public List<Contact> findByEmail(final String email) {
		return contactRepository.findByEmailLike(email);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluespacetech.contact.service.ContactService#findAll()
	 */
	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public List<Contact> findBySearchCriteria(ContactSearchCriteria contactSearchCriteria) {
		return contactRepositoryCustom.findContactsBySearchCriteria(contactSearchCriteria);
	}

}
