package com.bluespacetech.contact.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.group.entity.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pradeep created date 13-Jul-2016
 */
@Entity
@Table(name = "CONTACTS")
public class Contact extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8030722963619449582L;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@NotEmpty(message = "Email is mandatory.")
	@Column(name = "EMAIL")
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "contactGroupPK.contact", cascade = CascadeType.ALL)
	private Collection<ContactGroup> contactGroups = new ArrayList<>();
	
	@Transient
	private Collection<Group> groups = new ArrayList<>(); 
	
	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

	public Collection<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Collection<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
