package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.PersonalInfo;
/**
 * Class		: A_PersonInformationEngine		
 * Description	: Perform Personal Information Lifecycle Management
 *
 *	..........................................................................
 *	....+----------------------------------------------------------------+....						
 *	....|            A_PersonInformationEngine Class                     |....						
 *	....|             << concrete class >>                               |....	                    
 *	....+----------------------------------------------------------------+....						
 *	....+----------------------------------------------------------------+....						
 *	....| [+] void: updatePerson()                                       |....
 *	....| [+] PersonalInfo: displayPersonalInfo(int)                     |....
 *	....| [+] void: updateCustomerServices()                             |....
 *	....+----------------------------------------------------------------+....						
 *	..........................................................................																				
 * 																								
 * @author ISHAN KAMAT 			
 * Date			    : 28 November, 2017
 * Source File name	: A_PersonInformationEngine.java       	
 * 
 *
*/

public class A_PersonInformationEngine {

	public A_PersonInformationEngine() {
		super();
	}


	/***********************************************************************************************************
	 * Create an Personal Information Object and save it in Database.
	 * **********************************************************************************************************
	 */
	public void updatePerson(Integer personid, String fname, String lname, String contact,
			String address, String gender, Date dateofbirth, String city, String state,
			String zipcode) {

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();

		try {

			String DOB = "TO_DATE('"+dateofbirth+"', 'yyyy-mm-dd')"; 

			String SQLQuery = "UPDATE " + "PERSONINFO" +
					" SET "			+
					"FNAME" 		+ "='" + fname+"' , " 	+
					"LNAME" 		+ "='" + lname+"' , " 	+
					"CONTACT" 		+ "='" + contact+"' , "	+
					"ADDRESS" 		+ "='" + address+"' , "	+
					"GENDER" 		+ "='" + gender+"' , " 	+
					"DATEOFBIRTH" 	+ "=" + DOB+" , " 		+
					"CITY" 			+ "='" + city+"' , "	+
					"STATE" 		+ "='" + state+"' , " 	+
					"ZIPCODE" 		+ "='" + zipcode+"'  "	+
					" WHERE PERSONID="+personid;

			DCE.DDLCommandDatabase(SQLQuery);

		} catch (SQLException e) {
			System.out.println("There is an issue in updating personal information into the backend . More Details");
			System.out.println(e.getMessage());
		}
	}





	/**********************************************************************************************************
	 * TO GET THE PERSONAL INFORMATION FROM THE DATABASE FOR A GIVEN PERSON
	 * ********************************************************************************************************
	 * @return ObservableList<PersonalInfo>
	 */
	public PersonalInfo displayPersonalInfo(int personid){

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		PersonalInfo personaldata = null;
		String SQLQuery = "SELECT * FROM PERSONINFO WHERE PERSONID ="+ personid;
		ResultSet resultSet = null;
		ResultSet rs = null;
		System.out.println("Inside A_PersonInformationEngine: Function ---> displayPersonalInfo");
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;
			Integer PersonID;
			String fname;
			String lname;
			String contact;
			String address;
			String gender;
			Date dateofbirth;
			String city;
			String state;
			String zipcode;

			while(rs.next())
			{
				PersonID 	= rs.getInt(1);
				fname    	= (String)rs.getString(2);
				lname    	= (String)rs.getString(3);
				contact  	= (String)rs.getString(4);
				address  	= (String)rs.getString(5);
				gender   	= (String)rs.getString(6);
				dateofbirth = rs.getDate(7);
				city     	= (String)rs.getString(8);
				state    	= (String)rs.getString(9);
				zipcode  	= (String)rs.getString(10);

				personaldata =new PersonalInfo (PersonID ,fname,lname,contact,address,gender,dateofbirth,city,state,zipcode);

			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Personal Information. More Details:");
			System.out.println(e.getMessage());
		}	

		return personaldata;

	}

	
	/**
	 * *************************************************************************************************
	 * To update Cusotmer's Services preferences realtime
	 * *************************************************************************************************
	 * @param customerid
	 * @param serviceid
	 * @param insert
	 */
	public void updateCustomerServices(Integer customerid, Integer serviceid, Boolean insert) {

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		
		if(insert) {
			try {

				/*****First time a user is subscribing to the service ***********/
				String SQLQuery = "INSERT INTO  " + "CUSTOMERSERVICES VALUES (" +
						 + customerid +" , " 	+
						 + serviceid +")" ;
				DCE.DDLCommandDatabase(SQLQuery);			
			}

			catch (SQLException e) {
				System.out.println("There is an issue in updating personal information into the backend . More Details");
				System.out.println(e.getMessage());
			}
		}
		else {
			try {
				/*******************Delete the subscribed service*********************/
				String SQLQueryDeleteService = "DELETE FROM " + "CUSTOMERSERVICES" +
						" WHERE CUSTOMERID =  " + customerid + 
						" AND SERVICEID = " + serviceid;

				DCE.DDLCommandDatabase(SQLQueryDeleteService);
			} catch (SQLException e) {
				System.out.println("Cannot remove the service id: "+ serviceid +" from customer id : "+customerid);
				System.out.println(e.getMessage());

			}
		}
	}


	

}
