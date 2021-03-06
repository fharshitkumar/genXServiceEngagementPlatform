package entities;

import java.sql.Timestamp;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class		: Incident		
 * Description	: Incident Entity
 * This is SLA entity and we can maintain/retrieve Incident details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: Incident.java       	
 * 
 *
*/
public class Incident {

	private Integer incidentid;
	private Integer processorid;
	private Integer customerid;
	private Integer channelid;
	private Integer serviceid;
	private SimpleStringProperty shorttext;
	private SimpleStringProperty problem;
	private SimpleStringProperty priority;
	private Timestamp createdon;
	private SimpleStringProperty status;
	private SimpleStringProperty solution;
	private Timestamp lastupdate;
	private Timestamp closedate;
	private SimpleStringProperty tag;
	private SimpleStringProperty notes;
	private SimpleBooleanProperty escalatedstatus;
	private Integer sentiment;
	
	public Incident(Integer incidentid, Integer processorid, Integer customerid,
			Integer channelid, Integer serviceid, String shorttext, String problem,
			String priority, Timestamp createdon, String status, String solution,
			Timestamp lastupdate, Timestamp closedate, String tag, String notes,
			boolean escalatedstatus, Integer sentiment) {
		super();
		this.incidentid = incidentid;
		this.processorid = processorid;
		this.customerid = customerid;
		this.channelid = channelid;
		this.serviceid = serviceid;
		this.shorttext = new SimpleStringProperty(shorttext);
		this.problem = new SimpleStringProperty(problem);
		this.priority = new SimpleStringProperty(priority);
		this.createdon = createdon;
		this.status = new SimpleStringProperty(status);
		this.solution = new SimpleStringProperty(solution);
		this.lastupdate = lastupdate;
		this.closedate = closedate;
		this.tag = new SimpleStringProperty(tag);
		this.notes = new SimpleStringProperty(notes);
		this.escalatedstatus = new SimpleBooleanProperty(escalatedstatus);
		this.sentiment = sentiment;
	}

	/**
	 * @return the incidentid
	 */
	public Integer getIncidentid() {
		return incidentid;
	}

	/**
	 * @param incidentid the incidentid to set
	 */
	public void setIncidentid(Integer incidentid) {
		this.incidentid = incidentid;
	}

	/**
	 * @return the processorid
	 */
	public Integer getProcessorid() {
		return processorid;
	}

	/**
	 * @param processorid the processorid to set
	 */
	public void setProcessorid(Integer processorid) {
		this.processorid = processorid;
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
	 * @return the channelid
	 */
	public Integer getChannelid() {
		return channelid;
	}

	/**
	 * @param channelid the channelid to set
	 */
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
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

	/**
	 * @return the problem
	 */
	public String getProblem() {
		return problem.get();
	}

	/**
	 * @param problem the problem to set
	 */
	public void setProblem(String problem) {
		this.problem = new SimpleStringProperty(problem);
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority.get();
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = new SimpleStringProperty(priority);
	}

	/**
	 * @return the createdon
	 */
	public Timestamp getCreatedon() {
		return createdon;
	}

	/**
	 * @param createdon the createdon to set
	 */
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
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
	 * @return the solution
	 */
	public String getSolution() {
		return solution.get();
	}

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = new SimpleStringProperty(solution);
	}

	/**
	 * @return the lastupdate
	 */
	public Timestamp getLastupdate() {
		return lastupdate;
	}

	/**
	 * @param lastupdate the lastupdate to set
	 */
	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	/**
	 * @return the closedate
	 */
	public Timestamp getClosedate() {
		return closedate;
	}

	/**
	 * @param closedate the closedate to set
	 */
	public void setClosedate(Timestamp closedate) {
		this.closedate = closedate;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag.get();
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = new SimpleStringProperty(tag);
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes.get();
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = new SimpleStringProperty(notes);
	}

	/**
	 * @return the escalatedstatus
	 */
	public SimpleBooleanProperty getEscalatedstatus() {
		return escalatedstatus;
	}

	/**
	 * @param escalatedstatus the escalatedstatus to set
	 */
	public void setEscalatedstatus(SimpleBooleanProperty escalatedstatus) {
		this.escalatedstatus = escalatedstatus;
	}

	/**
	 * @return the sentiment
	 */
	public Integer getSentiment() {
		return sentiment;
	}

	/**
	 * @param sentiment the sentiment to set
	 */
	public void setSentiment(Integer sentiment) {
		this.sentiment = sentiment;
	}
	
}
