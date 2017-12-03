package entities;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class		: KnowledgeBase		
 * Description	: KnowledgeBase Entity
 * This is SLA entity and we can maintain/retrieve Knowledge Base Info details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: KnowledgeBase.java       	
 * 
 *
*/

public class KnowledgeBase {

	
	private Integer incidentid;
	private SimpleStringProperty shorttext;
	private SimpleStringProperty problem;
	private Integer serviceid;
	private Integer processorid;
	private SimpleStringProperty solution;
	private SimpleStringProperty tag;
	private SimpleStringProperty notes;
	private Integer sentiment;
	
	
	public KnowledgeBase(Integer incidentid, String shorttext, String problem,
			Integer serviceid, Integer processorid, String solution, String tag,
			String notes, Integer sentiment) {
		super();
		this.incidentid = incidentid;
		this.shorttext = new SimpleStringProperty(shorttext);
		this.problem = new SimpleStringProperty(problem);
		this.serviceid = serviceid;
		this.processorid = processorid;
		this.solution = new SimpleStringProperty(solution);
		this.tag = new SimpleStringProperty(tag);
		this.notes = new SimpleStringProperty(notes);
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
