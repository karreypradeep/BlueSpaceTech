/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.contactgroup.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.service.ContactGroupService;

/**
 * @author sandeep created date 25-Aug-2015
 */
@RestController
@RequestMapping("/contactgroups")
@CrossOrigin
public class ContactGroupController {

	@Autowired
	private ContactGroupService contactGroupService;

	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ContactGroup>> getContactGroups() {
		final Collection<ContactGroup> contactGroups = contactGroupService.findAll();
		return new ResponseEntity<Collection<ContactGroup>>(contactGroups, HttpStatus.OK);
	}

}
