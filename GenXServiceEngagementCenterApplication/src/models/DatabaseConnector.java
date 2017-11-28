package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnector implements AutoCloseable{


	private  Connection connection; 
	private final static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final static String user ="SYSTEM";
	private final static String password ="";

	/**
	 *   DatabaseConnector() : Constructor will create a connection to Oracle 12C Database 
	 */
	public DatabaseConnector() {

		/************************************************************************/
		/***********CHECK IF ORACLE 12C DRIVER IS INSTALLED OR NOT***************/
		/************************************************************************/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Kindly make sure Oracle JDBC Driver is installed.");
			return;
		}
		System.out.println("Oracle JDBC Driver is successfully Registered!");

		/************************************************************************/
		/******************CREATE A CONNECTION TO ORACLE DATABASE****************/
		/************************************************************************/
		try {
			this.connection = DriverManager.getConnection(url, user, password);
			System.getProperties().setProperty("oracle.jdbc.J2EE13Compliant", "true");
		} catch (SQLException e) {
			System.out.println("Creation of Database connection failed. Kindly check output console ");

			e.printStackTrace();
			return;
		}

		if (this.connection != null) {
			System.out.println("Congratulations! You have established control for database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

	/**
	 * This method is used to establish connection to Oracle Database 
	 * and will be used for  GenX Engagement Center application
	 * @return Connection object
	 */
	public Connection DBConnectionManager()
	{
		return this.connection;
	}

	/**
	 * ************************************************************************************
	 * Overriding the close connection to implement user defined close connection actions. 
	 * ************************************************************************************
	 */
	
	@Override
	public void close() throws Exception {
		/************************************************************************************/
		/******************MAKE SURE THE CONNECTION IS CLOSED WHEN NOT IN USE****************/
		/************************************************************************************/
		
		try{
			this.connection.close();
			this.connection= null;
		}
		catch(SQLException e){
			System.out.println("Closure of Database connection failed :" + e);
		}
	}
}