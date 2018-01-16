package code.challenge.service.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Subscriber implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	//TODO fecha coda o java 8
	private long id;
	private String email;
	private String firstname;
	private String gender;
	private Date dateOfBirth;
	private boolean consent;
	
	
	
	public Subscriber(long id, String email, String firstname, String gender, Date dateOfBirth, boolean consent) {
		super();
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.consent = consent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isConsent() {
		return consent;
	}

	public void setConsent(boolean consent) {
		this.consent = consent;
	}

	public Subscriber() {
		
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", email=" + email + ", firstname=" + firstname + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", consent=" + consent + "]";
	}
	
	
}
