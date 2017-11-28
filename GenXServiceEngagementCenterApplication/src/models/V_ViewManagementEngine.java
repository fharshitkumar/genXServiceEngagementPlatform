package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tableviews.CustomerInfoView;
import tableviews.PastIncidentView;


/**
 * ********************************************************************************************************
 * Class		: V_ViewManagementEngine		
 * Description	: Allows the application components to access the Backend Oracle Views 
 *                and generate a proper structured View that is consumable for further functionalities.  
 *	.............................................................................
 *	....+-------------------------------------------------------------------+....						
 *	....|            V_ViewManagementEngine Class                   		|....						
 *	....|               << concrete class >>                        		|....	                    
 *	....+-------------------------------------------------------------------+....										
 *	....| [+] PastIncidentView()                                            |....						
 *	....| [+] ObservableList<CustomerInfoView>: customerinfoviewer(int)     |....						
 *	....| [+] ObservableList<PastIncidentView>: pastincidentviewer(int)     |....							
 *	....+-----------------------------------------------------------+....						
 *	.....................................................................																				
 * ********************************************************************************************************																								
 * @author HARSHIT KUMAR 			
 * Date			    : 28 November, 2017
 * Source File name	: V_ViewManagementEngine.java       	
 * 
 *
*/
public class V_ViewManagementEngine {

	/**
	 * This Function is used to get all the details of the customers which includes following information.
	 * 		Customer ID
	 *		Customer Contract ID
	 *		Customer First Name;
	 *		Customer Last Name;
	 *		Customer Contact;
	 *		Customer residential Address;
	 *		Customer Gender;
	 *		Customer Date of birth;
	 *		Customer residential city;
	 *		Customer residential state;
	 *		Customer residential zipcode;
	 *		Customer role id;
	 *		Customer role name;
	 *		Customer role desc;
	 *		Customer service id(s) ;
	 *		Customer service name(s);
	 *		Customer email;
	 * @param customer
	 * @return ObservableList<CustomerInfoView>
	 */
	
	public ObservableList<CustomerInfoView> customerinfoviewer(int customer){

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<CustomerInfoView> customerinfoview = FXCollections.observableArrayList();
		String SQLQuery = "SELECT * FROM VIEWCUSTOMERINFO WHERE CUSTOMERID = "+customer;
		ResultSet resultSet = null;
		ResultSet rs = null;
		System.out.println("Inside V_ViewManagementEngine: Function ---> customerinfoviewer");

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;

			Integer customerid;
			Integer contractid;
			Integer personid;
			String fname;
			String lname;
			String contact;
			String address;
			String gender;
			Date dateofbirth;
			String city;
			String state;
			Integer zipcode;
			Integer roleid;
			String rolename;
			String roledesc;
			Integer serviceid;
			String servicename;
			String email;

			/***************************************************************************************/
			/************-----| GET ALL THE RECORD ROWS DATA INTO A STRUCTURED FORMAT |------- *****/
			/***************************************************************************************/
			while(rs.next())
			{

				customerid= rs.getInt(1);
				contractid= rs.getInt(2);
				personid= rs.getInt(3);
				fname= (String)rs.getString(4);
				lname= (String)rs.getString(5);
				contact= (String)rs.getString(6);
				address= (String)rs.getString(7);
				gender= (String)rs.getString(8);
				dateofbirth=rs.getDate(9);
				city= (String)rs.getString(10);
				state= (String)rs.getString(11);
				zipcode= rs.getInt(12);
				roleid= rs.getInt(13);
				rolename= (String)rs.getString(14);
				roledesc= (String)rs.getString(15);
				serviceid= rs.getInt(16);
				servicename= (String)rs.getString(17);
				email= (String)rs.getString(18);
				
				/***************************************************************************************/
				/************ ----| STRUCTURED FORMAT: CustomerInfoView object |----- ******************/
				/***************************************************************************************/
				
				customerinfoview.add(new CustomerInfoView(
						customerid,
						contractid,
						personid,
						fname,
						lname,
						contact,
						address,
						gender,
						dateofbirth,
						city,
						state,
						zipcode,
						roleid,
						rolename,
						roledesc,
						serviceid,
						servicename,
						email
						));
			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Knowledge Hub data. More details:");
			System.out.println(e.getMessage());
		}	
		return customerinfoview;
	}

/**
 * ********************************************************************************************************
 * This Function is used to get all the details of the customer's past incidents which includes following information.
 * 	Customer Incident status
 *  Customer Incident description
 * ********************************************************************************************************
 * @param customer
 * @return
 */
	public ObservableList<PastIncidentView> pastincidentviewer(int customer){
		
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<PastIncidentView> pastincidentinfoview = FXCollections.observableArrayList();
		String SQLQuery = "SELECT * FROM INCIDENTINFO WHERE CUSTOMERID = "+customer;
		ResultSet resultSet = null;
		ResultSet rs = null;
		System.out.println("Inside V_ViewManagementEngine: Function ---> pastincidentviewer");

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;

			String status;
			Integer customerid;
			String shorttext;
			   /***************************************************************************************/
			   /********-----| GET ALL THE RECORD ROWS DATA INTO A STRUCTURED FORMAT |------- *********/
			   /***************************************************************************************/
			while(rs.next())
			{
				shorttext= (String)rs.getString(1);
				status=(String)rs.getString(2);
				customerid= rs.getInt(3);
				
				/***************************************************************************************/
				/************ ----| STRUCTURED FORMAT: PastIncidentView object |----- ******************/
				/***************************************************************************************/
				pastincidentinfoview.add(new PastIncidentView(
						status,
						customerid,
						shorttext
						));
			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Knowledge Hub data. More Details");
			System.out.println(e.getMessage());
		}	

		return pastincidentinfoview;

	}





}
