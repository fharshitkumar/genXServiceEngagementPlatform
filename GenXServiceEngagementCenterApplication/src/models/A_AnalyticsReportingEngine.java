package models;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.WriteExcel;

/**
 * Class		: A_AnalyticsReportingEngine		
 * Description	: Perform Analytics and Reporting Lifecycle Management
 *
 *	.....................................................................
 *	....+-----------------------------------------------------------+....						
 *	....|        A_AnalyticsReportingEngine Class                   |....						
 *	....|             << concrete class >>                          |....	                    
 *	....+-----------------------------------------------------------+....						
 *	....+-----------------------------------------------------------+....						
 *	....| [+] void: genenerateReport1()  					        |....						
 *	....| [+] void: genenerateReport2()  					        |....						
 *	....| [+] void: genenerateReport3()  					        |....						
 *	....| [+] void: genenerateReport4()  					        |....						
 *	....| [+] void: genenerateReport5()  					        |....						
 *	....| [+] void: genenerateReport6()  					        |....						
 *	....| [+] void: genenerateReport7()  					        |....
 *	....| [+] void: genenerateReport8()  					        |....				
 *	*...+-----------------------------------------------------------+....						
 *	.....................................................................																				
 * 																								
 * @author ISHAN KAMAT 			
 * Date			    : 28 November, 2017
 * Source File name	: A_IncidentManagementEngine.java       	
 * 
 *
*/


public class A_AnalyticsReportingEngine {


	

public void genenerateReport1() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = "SELECT PI.PERSONID, PI.FNAME, PI.LNAME " + "FROM PERSONINFO PI"
			+ " INNER JOIN EMPLOYEESPECIALIZATION ES ON ES.EMPLOYEEID = PI.PERSONID"
			+ " INNER JOIN SPECIALIZATION S ON ES.SPECIALIZATIONID = S.SPECIALIZATIONID"
			+ " WHERE S.SPECIALIZATIONNAME = " + "'" + "Microsoft Excel" + "'";
	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_1(rs);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}

public void genenerateReport2() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = "SELECT SP.SPECIALIZATIONID, SP.SPECIALIZATIONNAME " + "FROM SPECIALIZATION SP "
			+ "INNER JOIN SERVICESPECIALIZATION SS ON SP.SPECIALIZATIONID = SS.SPECIALIZATIONID "
			+ "INNER JOIN SERVICE S ON S.SERVICEID = SS.SERVICEID " + "WHERE S.SERVICENAME = " + "'" + "Excel"
			+ "'";

	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_2(rs);
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}

public void genenerateReport3() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = "SELECT C.CHANNELID, C.CHANNELNAME, S.SERVICEID, S.SERVICENAME, I.PROBLEM, I.PRIORITY, I.STATUS "
			+ "FROM INCIDENT I " + "INNER JOIN CHANNEL C ON C.CHANNELID = I.CHANNELID "
			+ "INNER JOIN SERVICE S ON S.SERVICEID = I.SERVICEID " + "WHERE I.STATUS IS NULL ";

	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_3(rs);
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}

public void genenerateReport4() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = " SELECT PI.PERSONID, PI.FNAME, PI.LNAME, "
			+ "(SELECT NVL(SUM(24 * EXTRACT(DAY FROM (OUTTIME - INTIME)) + EXTRACT(HOUR FROM (OUTTIME - INTIME))), 0) FROM WORKSCHEDULE WS WHERE WS.EMPLOYEEID = PI.PERSONID) AS Hours_Spent_At_Work, "
			+ " (SELECT COUNT(1) FROM INCIDENT I WHERE I.PROCESSORID = PI.PERSONID) AS Assigned, "
			+ "(SELECT COUNT(1) FROM KNOWLEDGEBASE KB WHERE KB.PROCESSORID = KB.PROCESSORID) AS Resolved "
			+ "FROM PERSONINFO PI " + "INNER JOIN EMPLOYEE E ON E.EMPLOYEEID = PI.PERSONID "
			+ "ORDER BY Resolved DESC, Assigned DESC, Hours_Spent_At_Work DESC";

	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_4(rs);
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}

public void genenerateReport5() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = "SELECT I.PRIORITY, COUNT(1) AS Number_Of_Issues_Logged " + "FROM INCIDENT I "
			+ "INNER JOIN CHANNEL C ON I.CHANNELID = C.CHANNELID " + "WHERE C.CHANNELNAME = " + "'" + "FACEBOOK"
			+ "'" + "GROUP BY I.PRIORITY " + "ORDER BY I.PRIORITY ";

	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_5(rs);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}

public void genenerateReport6() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = " SELECT PROBLEM, COUNT(1) AS  Number_Of_Times_Raised " + "FROM INCIDENT "
			+ "GROUP BY PROBLEM " + "ORDER BY Number_Of_Times_Raised DESC ";

	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_6(rs);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}

public void genenerateReport7() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = "SELECT COUNT(1) AS Number_Of_Tickets_Raised, PI.STATE " + " FROM PERSONINFO PI "
			+ " INNER JOIN CUSTOMER C ON PI.PERSONID = C.CUSTOMERID " + "GROUP BY PI.STATE "
			+ "ORDER BY Number_Of_Tickets_Raised DESC ";

	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_7(rs);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}
public void genenerateReport8() {

	A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

	String SQLQuery = "SELECT C.CHANNELID, C.CHANNELNAME, S.SERVICEID, S.SERVICENAME, I.PROBLEM, I.PRIORITY, I.STATUS "+
			" FROM INCIDENT I "+
			" INNER JOIN CHANNEL C ON C.CHANNELID = I.CHANNELID "+
			" INNER JOIN SERVICE S ON S.SERVICEID = I.SERVICEID "+
			" WHERE I.STATUS <> 'RESOLVED' "+
			" ORDER BY I.PRIORITY ";


	ResultSet resultSet = null;
	ResultSet rs = null;

	System.out.println("SQL REPORT QUERY:::::::" + SQLQuery);

	try {
		resultSet = DCE.getResultSet(SQLQuery);
		rs = resultSet;

		if (rs != null) {
			WriteExcel obj_we = new WriteExcel();
			try {

				obj_we.writeXLSXFile_8(rs);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			System.out.println("Report Generated!!!!");
		}

		resultSet.close();

	} catch (SQLException e) {
		System.out.println("Failed to get Application Login Data");
	}

}
}




