package tableviews;

import java.sql.Timestamp;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class		: ManagerView		
 * Description	: 
 *  This is an Manager_View view Entity which will represent the incidents details that are needed by the 
 *  manager.
 *  Manager is able to view these incidents and assign to an employee or change priorities.
 *
 *	.....................................................................
 *	....+-----------------------------------------------------------+....						
 *	....|                 ManagerView Class                         |....						
 *	....|             << concrete class >>                          |....	                    
 *	....+-----------------------------------------------------------+....						
 *	....| [-] incidentid       : int                                |....						
 *	....| [-] processorid      : int                                |....						
 *	....| [-] servicename      : string                             |....
 *	....| [-] problem          : string                             |....
 *	....| [-] priority         : string                             |....
 *	....| [-] status           : string                             |....
 *	....| [-] createdon        : Timestamp                          |....						
 *	....+-----------------------------------------------------------+....						
 *	....| [+] ManagerView()                                         |....						
 *	....| [+] void: setincidentid(int)                              |....						
 *	....| [+] void: setprocessorid(int)                             |....						
 *	....| [+] void: setservicename(String)                          |....	
 *	....| [+] void: setproblem(String)                              |....
 *	....| [+] void: setpriority(String)                             |....
 *	....| [+] void: setstatus(String)                               |....
 *	....| [+] void: setcreatedon(Timestamp)                         |....						
 *	....| [+] string:  getstatus()                                  |....						
 *	....| [+] int:  getincidentid()                                 |....
 *	....| [+] int:  getprocessorid()                                |....
 *	....| [+] string:  getservicename()                             |....
 *	....| [+] string:  getproblem()                                 |....
 *	....| [+] string:  getpriority()                                |....
 *	....| [+] string:  getstatus()                                  |....
 *	....| [+] Timestamp:  getcreatedon()                            |....						
 *	....+-----------------------------------------------------------+....						
 *	.....................................................................																				
 * 																								
 * @author PRAGYA SHUKLA 			
 * Date			    : 28 November, 2017
 * Source File name	: ManagerView.java       	
 * 
 *
*/

public class ManagerView {
	
	private Integer incidentid;
	private Integer processorid;
	private SimpleStringProperty servicename;
	private SimpleStringProperty problem;
	private SimpleStringProperty priority;
	private Timestamp createdon;
	private SimpleStringProperty status; 
	
	/******************* Constructor to assign values*****************************************/
	public ManagerView(Integer incidentid, Integer processorid, String status, 
			 String servicename, String problem, String priority, Timestamp createdon) {
		super();
		this.incidentid = incidentid;
		this.processorid = processorid;
		this.servicename = new SimpleStringProperty (servicename);
		this.problem = new SimpleStringProperty(problem);
		this.priority = new SimpleStringProperty(priority);
		this.createdon = createdon;
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
	
}
