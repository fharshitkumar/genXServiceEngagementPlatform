package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.*;

public class A_DatabaseCommunicationEngine {
	
	DatabaseConnector DBConnectionHandler;
	
	public A_DatabaseCommunicationEngine() {
		DBConnectionHandler = null;	
	}

/**
 * Thsi function is used to do INSERT queries.
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
	
}
