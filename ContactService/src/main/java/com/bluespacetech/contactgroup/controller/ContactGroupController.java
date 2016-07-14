/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 *
 */
package com.bluespacetech.contactgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.service.ContactGroupService;
import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * @author pradeep created date 30-Jan-2015
 */
@RestController
@RequestMapping("/contactservice/contactgroups")
public class ContactGroupController extends AbstractBaseController {

    @Autowired
    ContactGroupService contactgroupService;

    /**
     * Retrieve All Financial Years.
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactGroup>> getContactGroups() {
	final List<ContactGroup> contactgroups = contactgroupService.findAll();
	return new ResponseEntity<List<ContactGroup>>(
		contactgroups, HttpStatus.OK);
    }

    /**
     * Retrieve Financial year by Id.
     *
     * @param id
     *            id of Financial year to be retrieved.
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactGroup> getContactGroupById(
	    @PathVariable final Long id) throws BusinessException{
	final ContactGroup contactgroup = contactgroupService
		.getContactGroupById(id);
	if (contactgroup == null) {
	    throw new BusinessException(
		    "Supplied ContactGroup ID is invalid.");
	}
	return new ResponseEntity<ContactGroup>(contactgroup,
		HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(
	    @RequestBody final ContactGroup contactgroup)
		    throws BusinessException {
	contactgroupService.createContactGroup(contactgroup);
	return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable final Long id,
	    @RequestBody final ContactGroup contactgroupResource)
		    throws BusinessException {

	// Get existing Financial Year
	final ContactGroup currentContactGroup = contactgroupService
		.getContactGroupById(id);
	if (currentContactGroup == null) {
	    throw new BusinessException(
		    "Supplied ContactGroup does not exist.");
	}
	if (!currentContactGroup.getVersion().equals(
		contactgroupResource.getVersion())) {
	    throw new BusinessException("Stale ContactGroup. Please update.");
	}

	contactgroupService.updateContactGroup(currentContactGroup);
	return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final Long id) throws BusinessException {
	contactgroupService.deleteContactGroup(id);
	return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<String> handleContactGroupNotFoundException(
	    final Exception e) {
	return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}",
		e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
