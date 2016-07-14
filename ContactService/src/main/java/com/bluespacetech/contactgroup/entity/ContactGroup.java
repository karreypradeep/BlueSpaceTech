/**
 *
 */
package com.bluespacetech.contactgroup.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.bluespacetech.core.model.BaseEntity;

/**
 * @author pradeep created date 13-Jul-2016
 *
 *
 */
@Entity
@Table(name = "CONTACT_GROUP")
public class ContactGroup extends BaseEntity implements Serializable {

    /**
     * Universal serial version id for this class
     */
    private static final long serialVersionUID = -9115675653111826751L;

    @NotEmpty(message = "Name is mandatory.")
    @Column(name = "NAME")
    private String				name;

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
	this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ContactGroup [name=" + name + "]";
    }
}
