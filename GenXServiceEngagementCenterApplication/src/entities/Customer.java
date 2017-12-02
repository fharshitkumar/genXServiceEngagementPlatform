package entities;

/**
 * Class		: Customer		
 * Description	: Customer  Entity
 * This is SLA entity and we can maintain/retrieve Customer  details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: Customer.java       	
 * 
 *
*/

public class Customer extends User{

	private Integer customerid;
	private Integer serviceid;
	private Integer contractid;
	
	public Customer(Integer personid, String username, String password, Boolean admin, String secretquestion,
			String secretanswer, String email, Integer customerid, Integer serviceid, Integer roleid,
			Integer contractid) {
		super(personid, username, password, admin, secretquestion, secretanswer, email, roleid);
		this.customerid = customerid;
		this.serviceid = serviceid;
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
