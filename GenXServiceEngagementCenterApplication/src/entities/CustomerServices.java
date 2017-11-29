package entities;

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
