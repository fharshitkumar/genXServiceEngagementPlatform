package entities;

import java.sql.Timestamp;


/**
 * Class		: WorkSchedule		
 * Description	: WorkSchedule Entity
 * This is an Workschedule table Entity which will represent the daily work schedule for employees.
 * Manager is able to view these schedules for the employee under him.													
 * @author PRAGYA SHUKLA 			
 * Date			    : 30 November, 2017
 * Source File name	: WorkSchedule.java       	
 * 
 *
*/

public class WorkSchedule {
	
	private Integer workpackageid;
	private Integer employeeid;
	private Timestamp intime;
	private Timestamp outtime;
	private Integer managerid;
	
	/******************* Constructor to assign values*****************************************/
	public WorkSchedule(Integer workpackageid, Integer employeeid, Timestamp intime, 
			Timestamp outtime, Integer managerid) {
		super();
		this.workpackageid = workpackageid;
		this.employeeid = employeeid;
		this.intime = intime;
		this.outtime = outtime;
		this.managerid = managerid;
	}

	/**
	 * @return the workpackageid
	 */
	public Integer getWorkpackageid() {
		return workpackageid;
	}

	/**
	 * @param workpackageid the workpackageid to set
	 */
	public void setWorkpackageid(Integer workpackageid) {
		this.workpackageid = workpackageid;
	}

	/**
	 * @return the employeeid
	 */
	public Integer getEmployeeid() {
		return employeeid;
	}

	/**
	 * @param employeeid the employeeid to set
	 */
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	/**
	 * @return the intime
	 */
	public Timestamp getIntime() {
		return intime;
	}

	/**
	 * @param intime the intime to set
	 */
	public void setIntime(Timestamp intime) {
		this.intime = intime;
	}

	/**
	 * @return the outtime
	 */
	public Timestamp getOuttime() {
		return outtime;
	}

	/**
	 * @param outtime the outtime to set
	 */
	public void setOuttime(Timestamp outtime) {
		this.outtime = outtime;
	}

	/**
	 * @return the managerid
	 */
	public Integer getManagerid() {
		return managerid;
	}

	/**
	 * @param managerid the managerid to set
	 */
	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}



}
