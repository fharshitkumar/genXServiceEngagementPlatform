package tableviews;

import java.sql.Date;
import java.sql.Timestamp;

import javafx.beans.property.SimpleStringProperty;

public class CustomerInfoView {

	private Integer customerid;
	private Integer contractid;
	private Integer personid;
	private SimpleStringProperty fname;
	private SimpleStringProperty lname;
	private SimpleStringProperty contact;
	private SimpleStringProperty address;
	private SimpleStringProperty gender;
	private Date dateofbirth;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private Integer zipcode;
	private Integer roleid;
	private SimpleStringProperty rolename;
	private SimpleStringProperty roledesc;
	private Integer serviceid;
	private SimpleStringProperty servicename;
	private SimpleStringProperty email;
	
	public CustomerInfoView(Integer customerid, Integer contractid, Integer personid, String fname,
			String lname, String contact, String address,
			String gender, Date dateofbirth, String city, String state,
			Integer zipcode, Integer roleid, String rolename, String roledesc,
			Integer serviceid, String servicename, String email) {
		super();
		this.customerid = customerid;
		this.contractid = contractid;
		this.personid = personid;
		this.fname =  new SimpleStringProperty(fname);
		this.lname = new SimpleStringProperty(lname);
		this.contact = new SimpleStringProperty(contact);
		this.address = new SimpleStringProperty(address);
		this.gender = new SimpleStringProperty(gender);
		this.dateofbirth = dateofbirth;
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zipcode = zipcode;
		this.roleid = roleid;
		this.rolename = new SimpleStringProperty(rolename);
		this.roledesc = new SimpleStringProperty(roledesc);
		this.serviceid = serviceid;
		this.servicename = new SimpleStringProperty(servicename);
		this.email = new SimpleStringProperty(email);
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email.get();
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}
	/**
	 * @return the customerid
	 */
	public Integer getCustomerid() {
		return customerid;
	}
	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	/**
	 * @return the contractid
	 */
	public Integer getContractid() {
		return contractid;
	}
	/**
	 * @param contractid the contractid to set
	 */
	public void setContractid(Integer contractid) {
		this.contractid = contractid;
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
	public String getContact() {
		return contact.get();
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = new SimpleStringProperty(contact);
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
	/**
	 * @return the roleid
	 */
	public Integer getRoleid() {
		return roleid;
	}
	/**
	 * @param roleid the roleid to set
	 */
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	/**
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename.get();
	}
	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = new SimpleStringProperty(rolename);
	}
	/**
	 * @return the roledesc
	 */
	public String getRoledesc() {
		return roledesc.get();
	}
	/**
	 * @param roledesc the roledesc to set
	 */
	public void setRoledesc(String roledesc) {
		this.roledesc = new SimpleStringProperty(roledesc);
	}
	/**
	 * @return the serviceid
	 */
	public Integer getServiceid() {
		return serviceid;
	}
	/**
	 * @param serviceid the serviceid to set
	 */
	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}
	/**
	 * @return the servicename
	 */
	public String getServicename() {
		return servicename.get();
	}
	/**
	 * @param servicename the servicename to set
	 */
	public void setServicename(String servicename) {
		this.servicename = new SimpleStringProperty(servicename);
	}

	
	
}
