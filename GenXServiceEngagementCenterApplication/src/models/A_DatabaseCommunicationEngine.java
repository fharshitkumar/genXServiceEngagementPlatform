package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.*;

public class A_DatabaseCommunicationEngine {

	DatabaseConnector DBConnectionHandler;

	public A_DatabaseCommunicationEngine() {
		DBConnectionHandler = null;	
	}

	/**
	 * This function is used to do INSERT queries.
	 * @param SQLQuery
	 * @throws SQLException
	 */
	public void DDLCommandDatabase(String SQLQuery) throws SQLException {

		DBConnectionHandler = new DatabaseConnector();

		try {
			Connection connection = DBConnectionHandler.DBConnectionManager();
			Statement transaction = connection.createStatement();
			transaction.executeUpdate(SQLQuery);
			transaction.close();
			connection.close();
			DBConnectionHandler.close();
		} catch (Exception e) {
			System.out.println("There was an error in inserting values to the table");
			System.out.println(e.getMessage());	
		}
	}

	/**
	 * This function is used to run SELECT queries
	 * @param SQLQuery
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String SQLQuery) throws SQLException {

		ResultSet retrivedResultSet = null;
		CachedRowSet rowset = null;

		DBConnectionHandler = new DatabaseConnector();

		try {
			Connection connection = DBConnectionHandler.DBConnectionManager();
			Statement transaction = connection.createStatement();
			retrivedResultSet=transaction.executeQuery(SQLQuery);
			rowset = new CachedRowSetImpl();
			rowset.populate(retrivedResultSet);
			retrivedResultSet.close();
			transaction.close();
			connection.close();
			DBConnectionHandler.close();
		} catch (Exception e) {
			System.out.println("There was an error in retriving values from the table");
			System.out.println(e.getMessage());	
		}
		ResultSet rs = rowset;

		return rs;
	}


	/***************This Function is used to SET the Database columns(String type) to new values*****************/
	public void CommitChanges(String tablename, String columnname, String columnvalue, int personid)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "UPDATE " + tablename +
				" SET "+columnname+ "='" + columnvalue+"'" + 
				" WHERE PERSONID="+personid;
		try {
			DCE.DDLCommandDatabase(SQLQuery);
		} catch (SQLException e) {
			System.out.println("Database SET query for field "+columnname+" failed . Check if the changes are made legitimately or not.");
		}		
	}

	/***************This Function is used to SET the Database columns(Numeric type) to new values*****************/
	public void CommitChanges(String tablename, String columnname, Integer columnvalue, int personid)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "UPDATE " + tablename + 
				" SET "+columnname+ "=" + (int)columnvalue+" " + 
				" WHERE PERSONID="+personid;
		try {
			DCE.DDLCommandDatabase(SQLQuery);
		} catch (SQLException e) {
			System.out.println("Database SET query for field "+columnname+" failed . Check if the changes are made legitimately or not.");
		}		
	}
	
	
	
	
	/***************This Function is used to SET the Database incident columns(String type) to new values*****************/
	public void CommitIncidentChanges(String tablename, String columnname, String columnvalue, int incidentid)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "UPDATE " + tablename +
				" SET "+columnname+ "='" + columnvalue+"'" + 
				" WHERE INCIDENTID="+incidentid;
		try {
			DCE.DDLCommandDatabase(SQLQuery);
		} catch (SQLException e) {
			System.out.println("Database SET query for field "+columnname+" failed . Check if the changes are made legitimately or not.");
		}		
	}
	
	
	
	/***************This Function is used to SET the Database incident columns(String type) to new values*****************/
	public void CommitIncidentChanges(String tablename, String columnname, Integer columnvalue, int incidentid)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "UPDATE " + tablename +
				" SET "+columnname+ "= " + columnvalue+" " + 
				" WHERE INCIDENTID="+incidentid;
		try {
			DCE.DDLCommandDatabase(SQLQuery);
		} catch (SQLException e) {
			System.out.println("Database SET query for field "+columnname+" failed . Check if the changes are made legitimately or not.");
		}		
	}
	
	
	/***************This Function is used to SET the Database incident columns(Timestamp type) to new values*****************/
	public void CommitIncidentChanges(String tablename, String columnname, Timestamp columnvalue, int incidentid)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
	
		String lastupdatevar = "to_timestamp('"+columnvalue+"', 'yyyy/mm/dd hh24:mi.ss.ff')";
		
		String SQLQuery = "UPDATE " + tablename +
				" SET "+columnname+ "= " + lastupdatevar+" " + 
				" WHERE INCIDENTID="+incidentid;
		try {
			DCE.DDLCommandDatabase(SQLQuery);
		} catch (SQLException e) {
			System.out.println("Database SET query for field "+columnname+" failed . Check if the changes are made legitimately or not.");
		}		
	}
	
	
	
	
}
