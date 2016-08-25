/**
 * This document is a part of the source code and related artifacts for
 * ContactService.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contact.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;

/**
 * @author sandeep created date 24-Aug-2016
 */
@Repository
public class ContactRepositoryCustomImpl implements ContactRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contact> findContactsBySearchCriteria(ContactSearchCriteria contactSearchCriteria) {
		StringBuilder queryString = new StringBuilder("select C from Contact C, Group G, ContactGroup CG ");
		if (contactSearchCriteria != null) {
			queryString = queryString
					.append(" where C.id = CG.contactGroupPK.contact.id and G.id = CG.contactGroupPK.group.id ");
		}
		if (contactSearchCriteria.getFirstName() != null) {
			queryString = queryString.append(" and C.firstName like :firstName ");
		}
		if (contactSearchCriteria.getLastName() != null) {
			queryString = queryString.append(" and C.lastName like :lastName ");
		}
		if (contactSearchCriteria.getEmail() != null) {
			queryString = queryString.append(" and C.email like :email ");
		}
		if (contactSearchCriteria.getGroupNames() != null && contactSearchCriteria.getGroupNames().size() > 0) {
			queryString = queryString.append(" and G.name in :groupNames ");
		}
		TypedQuery<Contact> query = entityManager.createQuery(queryString.toString(), Contact.class);
		if (contactSearchCriteria.getFirstName() != null) {
			query.setParameter("firstName", "%" + contactSearchCriteria.getFirstName() + "%");
		}
		if (contactSearchCriteria.getLastName() != null) {
			query.setParameter("lastName", contactSearchCriteria.getLastName());
		}
		if (contactSearchCriteria.getEmail() != null) {
			query.setParameter("email", contactSearchCriteria.getEmail());
		}
		if (contactSearchCriteria.getGroupNames() != null && contactSearchCriteria.getGroupNames().size() > 0) {
			query.setParameter("groupNames", contactSearchCriteria.getGroupNames());
		}
		return query.getResultList();
	}

}
