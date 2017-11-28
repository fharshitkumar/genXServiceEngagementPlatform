package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tableviews.CustomerInfoView;
import tableviews.PastIncidentView;

public class V_ViewManagementEngine {

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
			System.out.println("Failed to get Knowledge Hub data");
		}	

		return customerinfoview;

	}


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


			while(rs.next())
			{

				shorttext= (String)rs.getString(1);
				status=(String)rs.getString(2);
				customerid= rs.getInt(3);

				pastincidentinfoview.add(new PastIncidentView(
						status,
						customerid,
						shorttext
						));
			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Knowledge Hub data");
		}	

		return pastincidentinfoview;

	}





}
