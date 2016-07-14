/**
 * This document is a part of the source code and related artifacts for
 * ContactGroupService.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.contactgroup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.contactgroup.entity.ContactGroup;

/**
 * class for ContactGroupRepository
 *
 * @author pradeep created date 25-June-2015
 */
@Repository
public interface ContactGroupRepository extends JpaRepository<ContactGroup, Long> {

    List<ContactGroup> findByNameLike(final String name);
}
