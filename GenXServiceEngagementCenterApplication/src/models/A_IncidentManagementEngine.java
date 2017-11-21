package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entities.Incident;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class A_IncidentManagementEngine {

	public A_IncidentManagementEngine() {
		super();
	}

	
	/**
	 * Create an Incident and save it in Database.
	 * @param incidentid
	 * @param processorid
	 * @param customerid
	 * @param departmentid
	 * @param channelid
	 * @param serviceid
	 * @param shorttext
	 * @param problem
	 * @param priority
	 * @param createdon
	 * @param status
	 * @param solution
	 * @param lastupdate
	 * @param closedate
	 * @param tag
	 * @param notes
	 * @param escalatedstatus
	 * @param sentiment
	 */
	public void createIncident(Integer incidentid, Integer processorid, Integer customerid, Integer departmentid,
			Integer channelid, Integer serviceid, String shorttext, String problem, String priority, Date createdon,
			String status, String solution, Date lastupdate, Date closedate, String tag, String notes,
			boolean escalatedstatus, Integer sentiment) {
		
	}
	
	
	/**
	 * To get all the required incidents as per the requirements from the Database.
	 * @return ObservableList<Incident>
	 */
	public ObservableList<Incident> displayTickets(){
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<Incident> incidentdata = FXCollections.observableArrayList();
		String SQLQuery = "SELECT * FROM INCIDENT";
		ResultSet resultSet = null;
		ResultSet rs = null;
		
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;
		Integer incidentid;
		Integer processorid;
		Integer customerid;
		Integer departmentid;
		Integer channelid;
		Integer serviceid;
		String shorttext;
		String problem;
		String priority;
		Date createdon;
		String status;
		String solution;
		Date lastupdate;
		Date closedate;
		String tag;
		String notes;
		Boolean escalatedstatus;
		Integer sentiment;
		while(rs.next())
		{
			incidentid = rs.getInt(1);
			processorid = rs.getInt(2);
			customerid = rs.getInt(3);
			departmentid = rs.getInt(4);
			channelid = rs.getInt(5);
			serviceid = rs.getInt(6);
			shorttext = (String)rs.getString(7);
			problem = (String)rs.getString(8);
			priority = (String)rs.getString(9);
			createdon = (Date)rs.getDate(10);
			status = (String)rs.getString(11);
			solution = (String)rs.getString(12);
			lastupdate = (Date)rs.getDate(13);
			closedate = (Date)rs.getDate(14);
			tag = (String)rs.getString(15);
			notes = (String)rs.getString(16);
			escalatedstatus = (Boolean)(rs.getString(17).equals("Y")? true : false);
			sentiment = rs.getInt(18);
			incidentdata.add(new Incident(
					incidentid,
					processorid,
					customerid,
					departmentid,
					channelid,
					serviceid,
					shorttext,
					problem,
					priority,
					createdon,
					status,
					solution,
					lastupdate,
					closedate,
					tag,
					notes,
					escalatedstatus,
					sentiment
					));
		}
		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}	

	return incidentdata;

	}

	

}
