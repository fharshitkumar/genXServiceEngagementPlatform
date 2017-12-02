package entities;

import java.sql.Date;


import javafx.beans.property.SimpleStringProperty;

/**
 * Class		: PersonalInfo		
 * Description	: PersonalInfo Entity
 * This is SLA entity and we can maintain/retrieve Personal Info details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: PersonalInfo.java       	
 * 
 *
*/

public class PersonalInfo {

	private Integer personid;
	private SimpleStringProperty fname;
	private SimpleStringProperty lname;
	private SimpleStringProperty contact;
	private SimpleStringProperty address;
	private SimpleStringProperty gender;
	private Date dateofbirth;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private SimpleStringProperty zipcode;
	public PersonalInfo(Integer personid, String fname, String lname,
			String contact, String address, String gender, Date dateofbirth,
			String city, String state, String zipcode) {
		super();
		this.personid = personid;
		this.fname  = new SimpleStringProperty(fname);
		this.lname  = new SimpleStringProperty(lname);
		this.contact  = new SimpleStringProperty(contact);
		this.address  = new SimpleStringProperty(address);
		this.gender  = new SimpleStringProperty(gender);
		this.dateofbirth = dateofbirth;
		this.city  = new SimpleStringProperty(city);
		this.state  = new SimpleStringProperty(state);
		this.zipcode  = new SimpleStringProperty(zipcode);
	}
	/**
	 * @return the personid
	 */
	public Integer getPersonid() {
		return personid;
	}
	/**
	 * @param personid the personid to set
	 */
	public void setPersonid(Integer personid) {
		this.personid = personid;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname.get();
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname  = new SimpleStringProperty(fname);
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname.get();
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname  = new SimpleStringProperty(lname);
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact.get();
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact  = new SimpleStringProperty(contact);
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address.get();
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address  = new SimpleStringProperty(address);
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender.get();
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender  = new SimpleStringProperty(gender);
	}
	/**
	 * @return the dateofbirth
	 */
	public Date getDateofbirth() {
		return dateofbirth;
	}
	/**
	 * @param dateofbirth the dateofbirth to set
	 */
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city.get();
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city  = new SimpleStringProperty(city);
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state.get();
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode.get();
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode  = new SimpleStringProperty(zipcode);
	}

	
	
}
