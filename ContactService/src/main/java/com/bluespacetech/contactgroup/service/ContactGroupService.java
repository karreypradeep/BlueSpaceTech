/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.contactgroup.service;

import java.util.List;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * class for ContactGroupService
 *
 * @author pradeep created date 25-June-2015
 */
public interface ContactGroupService {

    ContactGroup createContactGroup(final ContactGroup contactGroup) throws BusinessException;

    void deleteContactGroup(final Long contactGroupId) throws BusinessException;

    List<ContactGroup> findByName(final String name);

    List<ContactGroup> findAll();

    ContactGroup getContactGroupById(final Long contactGroupId);

    ContactGroup updateContactGroup(final ContactGroup contactGroup) throws BusinessException;
}
