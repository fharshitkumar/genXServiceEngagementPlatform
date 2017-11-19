package entities;

public class Customer extends User{

	private Integer customerid;
	private Integer serviceid;
	private Integer roleid;
	private Integer contractid;
	
	public Customer(Integer personid, String username, String password, Boolean admin, String secretquestion,
			String secretanswer, String email, Integer customerid, Integer serviceid, Integer roleid,
			Integer contractid) {
		super(personid, username, password, admin, secretquestion, secretanswer, email, roleid);
		this.customerid = customerid;
		this.serviceid = serviceid;
		this.roleid = roleid;
		this.contractid = contractid;
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

	
	
}
