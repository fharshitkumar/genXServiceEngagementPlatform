package tableviews;

import javafx.beans.property.SimpleStringProperty;

public class PastIncidentView {

	private SimpleStringProperty status;
	private Integer customerid;
	private SimpleStringProperty shorttext;
	public PastIncidentView(String status, Integer customerid, String shorttext) {
		super();
		this.status = new SimpleStringProperty(status);
		this.customerid = customerid;
		this.shorttext = new SimpleStringProperty(shorttext);
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status.get();
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = new SimpleStringProperty(status);
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
	 * @return the shorttext
	 */
	public String getShorttext() {
		return shorttext.get();
	}
	/**
	 * @param shorttext the shorttext to set
	 */
	public void setShorttext(String shorttext) {
		this.shorttext = new SimpleStringProperty(shorttext);
	}

	
	
	
}
