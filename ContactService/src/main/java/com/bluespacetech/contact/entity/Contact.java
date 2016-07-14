/**
 *
 */
package com.bluespacetech.contact.entity;

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
@Table(name = "CONTACT")
public class Contact extends BaseEntity implements Serializable {

    /**
     * Universal serial version id for this class
     */
    private static final long serialVersionUID = -8030722963619449582L;

    @Column(name = "FIRST_NAME")
    private String				firstName;

    @Column(name = "LAST_NAME")
    private String				lastName;

    @NotEmpty(message = "Email is mandatory.")
    @Column(name = "EMAIL")
    private String				email;

    /**
     * @return the firstName
     */
    public String getFirstName() {
	return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(final String firstName) {
	this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
	return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(final String lastName) {
	this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
	this.email = email;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }

}
