package com.bluespacetech.group.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.bluespacetech.contactgroup.ContactGroup;
import com.bluespacetech.core.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pradeep created date 13-Jul-2016
 */
@Entity
@Table(name = "GROUPS")
public class Group extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -9115675653111826751L;

	@NotEmpty(message = "Name is mandatory.")
	@Column(name = "NAME")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "contactGroupPK.group", cascade = CascadeType.ALL)
	private Collection<ContactGroup> contactGroups = new ArrayList<>();

	public Collection<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Collection<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Group [name=" + name + "]";
	}
}
