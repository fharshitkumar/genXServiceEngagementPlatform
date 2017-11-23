package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entities.Incident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class A_IncidentManagementEngine {

	public A_IncidentManagementEngine() {
		super();
	}

	/**
	 * Create an Incident and save it in Database.
	 */
	public void createIncident(Integer processorid, Integer customerid, Integer channelid, 
			Integer serviceid, String shorttext, String problem, String priority, Timestamp createdon,
			String status, String solution, Timestamp lastupdate, Timestamp closedate, String tag, String notes,
			boolean escalatedstatus, Integer sentiment) {

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		System.out.println("Inside A_IncidentManagementEngine: Function ---> createIncident");
		String SQLQuery = "SELECT COUNT(INCIDENTID) FROM INCIDENT";
		ResultSet resultSet = null;

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			ResultSet rs = resultSet;
			int incidentID = 0;
			while(rs.next())
				incidentID = rs.getInt(1)+1;
			rs.close();
			String createdonvar = "to_timestamp('"+createdon+"', 'yyyy/mm/dd hh24:mi.ss.ff')"; 
			String lastupdatevar = (lastupdate==null? null:"to_timestamp('"+lastupdate+"', 'yyyy/mm/dd hh24:mi.ss.ff')");
			String closedatevar = (closedate==null? null:"to_timestamp('"+closedate+"', 'yyyy/mm/dd hh24:mi.ss.ff')");
			String sql = "INSERT INTO INCIDENT" + 
					" VALUES("
					+ incidentID + ", "
					+ processorid + ", "
					+ customerid + ", "
					+ channelid + ", "
					+ serviceid + ", '"
					+ shorttext + "', '"
					+ problem + "', '"
					+ priority + "', "
					+ createdonvar+ ", '"
					+ status  + "', '"
					+ solution  + "', "
					+ lastupdatevar + ", "
					+  closedatevar + ", '"
					+ tag  + "', '"
					+ notes  + "', "
					+ ((escalatedstatus==true)?"'Y'":"'N'")  + ", "
					+ sentiment
					+ ")";
			DCE.DDLCommandDatabase(sql);
		} catch (SQLException e) {
			System.out.println("There is an issue in inserting into the backend during Incident creation");
			e.printStackTrace();
		}


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
		System.out.println("Inside A_IncidentManagementEngine: Function ---> displayTickets");
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;
			Integer incidentid;
			Integer processorid;
			Integer customerid;
			Integer channelid;
			Integer serviceid;
			String shorttext;
			String problem;
			String priority;
			Timestamp createdon;
			String status;
			String solution;
			Timestamp lastupdate;
			Timestamp closedate;
			String tag;
			String notes;
			Boolean escalatedstatus;
			Integer sentiment;
			while(rs.next())
			{
				incidentid = rs.getInt(1);
				processorid = rs.getInt(2);
				customerid = rs.getInt(3);
				channelid = rs.getInt(4);
				serviceid = rs.getInt(5);
				shorttext = (String)rs.getString(6);
				problem = (String)rs.getString(7);
				priority = (String)rs.getString(8);
				createdon = rs.getTimestamp(9);
				status = (String)rs.getString(10);
				solution = (String)rs.getString(11);
				lastupdate = rs.getTimestamp(12);
				closedate = rs.getTimestamp(13);
				tag = (String)rs.getString(14);
				notes = (String)rs.getString(15);
				escalatedstatus = (Boolean)(rs.getString(16).equals("Y")? true : false);
				sentiment = rs.getInt(17);
				incidentdata.add(new Incident(
						incidentid,
						processorid,
						customerid,
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
	public List<String> getPriority() {
		List<String> priorities = new ArrayList<>();
		priorities.add("LOW");
		priorities.add("MEDIUM");
		priorities.add("HIGH");
		priorities.add("VERY HIGH");
		return priorities;
	}

	public List<String> getServices() {
		List<String> services = new ArrayList<>();
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "SELECT * FROM SERVICE";
		ResultSet resultSet = null;
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			ResultSet rs = resultSet;
			while(rs.next())
				services.add(rs.getString(2));
			rs.close();
		} catch (SQLException e) {
			System.out.println("Failed to get Services");
		}
		return services;
	}



	//
	//					/*******************FOR REFERENCE TO HAVE TIMESTAMP AND DATE*****************************/
	///************************************************************************************************************************************/
	//	//INSERT INTO DEMOTIMESTAMP VALUES(2, to_timestamp('2017-11-21 20:52:22.887', 'yyyy/mm/dd hh24:mi.ss.ff'),  TO_DATE('2017-11-17', 'yyyy/mm/dd'));
	//	int personid = 1 ;
	//	Date dob = new Date(System.currentTimeMillis());
	//	Timestamp ticketcreated = new Timestamp(System.currentTimeMillis());
	//	//System.out.println(dob + "  "+ ticketcreated);
	//	private String sqlinsert = "INSERT INTO DEMOTIMESTAMP" + 
	//			" VALUES("
	//			+ personid + ", "
	//			+"to_timestamp('"+ticketcreated+"', 'yyyy/mm/dd hh24:mi.ss.ff'), "
	//			+ "TO_DATE('"+dob + "', 'yyyy/mm/dd') "
	//			+ ")";
	// /************************************************************************************************************************************/



}
