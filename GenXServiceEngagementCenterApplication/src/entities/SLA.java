package entities;

import javafx.beans.property.SimpleStringProperty;

public class SLA {
	private Integer slaid;
	private SimpleStringProperty slaname;
	private SimpleStringProperty sladesc;
	private Integer slatimeduration;
	public SLA(Integer slaid, String slaname, String sladesc, Integer slatimeduration) {
		super();
		this.slaid = slaid;
		this.slaname =new SimpleStringProperty(slaname);
		this.sladesc = new SimpleStringProperty(sladesc);
		this.slatimeduration = slatimeduration;
	}
	/**
	 * @return the slaid
	 */
	public Integer getSlaid() {
		return slaid;
	}
	/**
	 * @param slaid the slaid to set
	 */
	public void setSlaid(Integer slaid) {
		this.slaid = slaid;
	}
	/**
	 * @return the slaname
	 */
	public String getSlaname() {
		return slaname.get();
	}
	/**
	 * @param slaname the slaname to set
	 */
	public void setSlaname(String slaname) {
		this.slaname = new SimpleStringProperty(slaname);
	}
	/**
	 * @return the sladesc
	 */
	public String getSladesc() {
		return sladesc.get();
	}
	/**
	 * @param sladesc the sladesc to set
	 */
	public void setSladesc(String sladesc) {
		this.sladesc = new SimpleStringProperty(sladesc);
	}
	/**
	 * @return the slatimeduration
	 */
	public Integer getSlatimeduration() {
		return slatimeduration;
	}
	/**
	 * @param slatimeduration the slatimeduration to set
	 */
	public void setSlatimeduration(Integer slatimeduration) {
		this.slatimeduration = slatimeduration;
	}
	
}
