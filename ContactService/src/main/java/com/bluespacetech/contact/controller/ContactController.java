/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contact.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;

/**
 * @author pradeep created date 30-Jan-2015
 */
@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactController {

	@Autowired
	ContactService contactService;

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Contact>> getContacts() {
		final Collection<Contact> contacts = contactService.findAll();
		return new ResponseEntity<Collection<Contact>>(contacts, HttpStatus.OK);
	}

	/**
	 * Retrieve Financial year by Id.
	 *
	 * @param id
	 *            id of Financial year to be retrieved.
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> getContactById(@PathVariable final Long id) throws BusinessException {
		final Contact contact = contactService.getContactById(id);
		if (contact == null) {
			throw new BusinessException("Supplied Contact ID is invalid.");
		}
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody final Contact contact) throws BusinessException {
		Collection<Group> groups = contact.getGroups();
		if (groups != null) {
			Collection<ContactGroup> contactGroups = new ArrayList<>();
			for (Group group : groups) {
				ContactGroup contactGroup = new ContactGroup();
				contactGroup.setContact(contact);
				contactGroup.setGroup(group);
				contactGroup.setActive(true);
				contactGroup.setUnSubscribed(false);
				contactGroups.add(contactGroup);
			}
			contact.setContactGroups(contactGroups);
			contactService.createContact(contact);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody final Contact contact)
			throws BusinessException {

		// Get existing Financial Year
		final Contact currentContact = contactService.getContactById(id);
		if (currentContact == null) {
			throw new BusinessException("Supplied Contact does not exist.");
		}
		if (!currentContact.getVersion().equals(contact.getVersion())) {
			throw new BusinessException("Stale Contact. Please update.");
		}

		contactService.updateContact(contact);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) throws BusinessException {
		contactService.deleteContact(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<String> handleContactNotFoundException(final Exception e) {
		return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}", e.getMessage()), HttpStatus.NOT_FOUND);
	}
}
