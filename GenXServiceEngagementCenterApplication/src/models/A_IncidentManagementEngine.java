package models;

import java.sql.Date;
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

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		
		String SQLQuery = "SELECT COUNT(INCIDENTID) FROM INCIDENT";
		ResultSet resultSet = null;
		
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			ResultSet rs = resultSet;
			int incidentID = 0;
			while(rs.next())
				incidentID = rs.getInt(1)+1;
			rs.close();
			String sql = "INSERT INTO INCIDENT" + 
					" VALUES("
					+ incidentID + ", "
					+ processorid + ", "
					+ customerid + ", "
					+ departmentid + ", "
					+ channelid + ", "
					+ serviceid + ", '"
					+ shorttext + "', '"
					+ problem + "', '"
					+ priority + "', "
					+ createdon + ", '"
					+ status  + "', '"
					+ solution  + "', "
					+ lastupdate + ", '"
					+ closedate + ", '"
					+ tag  + "', '"
					+ notes  + "', '"
					+ (escalatedstatus?'Y':'N')  + "', "
					+ sentiment + ", "
					+ ")";
				DCE.DDLCommandDatabase(sql);
		} catch (SQLException e) {
			System.out.println("There is an issue in inserting into the backend during Incident creation");
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * Create a Customer Ticket using normal mode
	 * @param customerid
	 * @param channelid
	 * @param serviceid
	 * @param shorttext
	 * @param problem
	 * @param priority
	 * @param createdon
	 * @param status
	 */
	public void createIncidentCustomer(
			Integer customerid,
			Integer channelid, 
			Integer serviceid, 
			String shorttext, 
			String problem, 
			String priority, 
			Timestamp createdon,
			String status,
			Boolean escalation) {

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		
		String SQLQuery = "SELECT COUNT(INCIDENTID) FROM INCIDENT";
		ResultSet resultSet = null;
		
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			ResultSet rs = resultSet;
			int incidentID = 0;
			while(rs.next())
				incidentID = rs.getInt(1)+1;
			rs.close();
			String sql = "INSERT INTO INCIDENT" + 
					" VALUES("
					+ (incidentID+1) + ", "
					+ null + ", "
					+ customerid + ", "
					+ null + ", "
					+ channelid + ", "
					+ serviceid + ", '"
					+ shorttext + "', '"
					+ problem + "', '"
					+ priority + "', "
					+ "to_timestamp('"+createdon+"', 'yyyy/mm/dd hh24:mi.ss.ff')"+ ", '"
					+ status  + "', '"
					+ null  + "', "
					+ null + ", "
					+ null + ", '"
					+ null  + "', '"
					+ null  + "', "
					+ ((escalation==true)?"'Y'":"'N'")  + ", "
					+ null + " "
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
			departmentid = rs.getInt(4);
			channelid = rs.getInt(5);
			serviceid = rs.getInt(6);
			shorttext = (String)rs.getString(7);
			problem = (String)rs.getString(8);
			priority = (String)rs.getString(9);
			createdon = (Timestamp)rs.getTimestamp(10);
			status = (String)rs.getString(11);
			solution = (String)rs.getString(12);
			lastupdate = (Timestamp)rs.getTimestamp(13);
			closedate = (Timestamp)rs.getTimestamp(14);
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

					/*******************FOR REFERENCE TO HAVE TIMESTAMP AND DATE*****************************/
/************************************************************************************************************************************/
	//INSERT INTO DEMOTIMESTAMP VALUES(2, to_timestamp('2017-11-21 20:52:22.887', 'yyyy/mm/dd hh24:mi.ss.ff'),  TO_DATE('2017-11-17', 'yyyy/mm/dd'));
	int personid = 1 ;
	Date dob = new Date(System.currentTimeMillis());
	Timestamp ticketcreated = new Timestamp(System.currentTimeMillis());
	//System.out.println(dob + "  "+ ticketcreated);
	private String sqlinsert = "INSERT INTO DEMOTIMESTAMP" + 
			" VALUES("
			+ personid + ", "
			+"to_timestamp('"+ticketcreated+"', 'yyyy/mm/dd hh24:mi.ss.ff'), "
			+ "TO_DATE('"+dob + "', 'yyyy/mm/dd') "
			+ ")";
 /************************************************************************************************************************************/
}
