package entities;

/**
 * Class		: CustomerServices		
 * Description	: Customer Services Entity
 * This is SLA entity and we can maintain/retrieve Customer Services details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: CustomerServices.java       	
 * 
 *
*/

public class CustomerServices {
	private Integer customerid;
	private Integer serviceid;
	public CustomerServices(Integer customerid, Integer serviceid) {
		super();
		this.customerid = customerid;
		this.serviceid = serviceid;
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
	
}
