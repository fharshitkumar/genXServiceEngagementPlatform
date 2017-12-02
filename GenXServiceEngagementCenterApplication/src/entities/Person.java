package entities;

import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;
/**
 * Class		: Person		
 * Description	: Person Entity
 * This is SLA entity and we can maintain/retrieve Person  details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: Person.java       	
 * 
 *
*/

public class Person {

	private Integer personid;
	private SimpleStringProperty fname;
	private SimpleStringProperty lname;
	private Integer contact;
	private SimpleStringProperty address;
	private SimpleStringProperty gender;
	private Date dateofbirth;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private Integer zipcode;
	public Person(Integer personid, String fname, String lname, Integer contact,
			String address, String gender, Date dateofbirth, String city,
			String state, Integer zipcode) {
		super();
		this.personid = personid;
		this.fname = new SimpleStringProperty(fname);
		this.lname = new SimpleStringProperty(lname);
		this.contact = contact;
		this.address = new SimpleStringProperty(address);
		this.gender = new SimpleStringProperty(gender);
		this.dateofbirth = dateofbirth;
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zipcode = zipcode;
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
		this.fname = new SimpleStringProperty(fname);
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
		this.lname = new SimpleStringProperty(lname);
	}
	/**
	 * @return the contact
	 */
	public Integer getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(Integer contact) {
		this.contact = contact;
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
		this.address = new SimpleStringProperty(address);
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
		this.gender = new SimpleStringProperty(gender);
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
		this.city = new SimpleStringProperty(city);
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
	public Integer getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	
	
}
