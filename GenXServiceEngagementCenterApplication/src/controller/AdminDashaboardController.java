package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entities.Service;
import entities.User;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.A_DatabaseCommunicationEngine;
import models.A_IncidentManagementEngine;
import utility.ApplicationUtilities;


/**
 * *************************************************************************************************
 * Class		: AdminDashaboardController		
 * Description	: AdminDashaboardController to controller Admin Screen
 *								
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: AdminDashaboardController.java       	
 * **************************************************************************************************
 *
*/


public class AdminDashaboardController implements Initializable{

	/*******************      LOGIN TABLE IDENTIFIERS     ******************************************/
	@FXML
	private TableView<User> tableID;
	@FXML
	private TableColumn<User,Integer> iID;
	@FXML
	private TableColumn<User,String> iusername;
	@FXML
	TableColumn<User,String> ipassword;
	@FXML
	TableColumn<User,Boolean> iadmin;
	@FXML
	TableColumn<User,String> iquestion;
	@FXML
	TableColumn<User,String> ianswer;
	@FXML
	TableColumn<User,String> iemail;
	@FXML
	private TableColumn<User,Integer> irole;

	/*******************        ADMIN PAGE NAVIGATORS    ******************************************/
	@FXML
	Pane FavouritePanel;
	
	@FXML
	Pane LoginTablePanel;
	
	@FXML
	JFXTextField searchusername;
	
	@FXML
	JFXTextField searchemail;
	
	/*******************        ADMIN SEARCH ENABLERS    ******************************************/
	@FXML
	JFXComboBox<String> searchrole;
	
	/*********************************************************************************************************
	 *****This is the helper utility to perform various UI level common tasks like go back, close window, etc.
	 *********************************************************************************************************
	 */
	ApplicationUtilities util;

	/*********************************************************************************************************
	 * This is will initialize the helper application UI utility.
	 *********************************************************************************************************
	 */
	public AdminDashaboardController() {
		util = new ApplicationUtilities();
	}

	/******************************************************************************************************
	 *  To close the Admin window and return to Login window.
	 * ****************************************************************************************************
	 * @param event
	 */
	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		util.close(event);
		if(util.isWindowclosestatus())
		util.back("/view/Login.fxml",event);
	}
	
	/******************************************************************************************************
	 *  To open the Admin Home panel. 
	 * ****************************************************************************************************
	 * @param event
	 */
	@FXML
	public void OpenLoginTablePanel(ActionEvent event) {
		System.out.println("You clicked Admin Home button");
		FavouritePanel.setVisible(false);
		LoginTablePanel.setVisible(true);		
	}
	
	
	
	
	
	
	
	
	
	
	
	@FXML
	private TableView<Service> servicesID;
	@FXML
	private TableColumn<Service,Integer> iserviceID;
	@FXML
	private TableColumn<Service,String> iservicename;
	@FXML
	private TableColumn<Service,String> iservicedescription;

	
	
	

	
	
	
	
	
	/******************************************************************************************************
	 *  To open the Admin Favorite panel. | Future enhancements can be performed.
	 * ****************************************************************************************************
	 * @param event
	 */
	@FXML
	public void OpenFavoritePanel(ActionEvent event) {
		/***************************MAINTAIN SERVICES***********************************/
		/*******************************************************************************/
		iserviceID.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getChannelid()));
		
		iservicename.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getChannelname())));
		
		iservicedescription.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getChanneldesc())));
		
		servicesID.setItems(getServiceData());
		servicesID.setEditable(true);
		iservicename.setCellFactory(TextFieldTableCell.forTableColumn()); 
		iservicedescription.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		System.out.println("You clicked Favourite Home button");
		FavouritePanel.setVisible(true);
		LoginTablePanel.setVisible(false);
	}
	

	/**********************************************************************************
	 * This function is responsible to help the admin search the Login table data.
	 **********************************************************************************
	 * @param event
	 */
	@FXML
	public void Search(ActionEvent event) {
		String username = this.searchusername.getText();
		String email = this.searchemail.getText();
		Integer roleid = (searchrole.getSelectionModel().getSelectedIndex());
		
		ObservableList<User> table = FXCollections.observableArrayList();
		ObservableList<User> searchresult = FXCollections.observableArrayList();
		table= getData();
		for(User user : table) {
			if(username!= null && user.getUsername().equals(username))
				searchresult.add(user);
			if(email!= null && user.getEmail().equals(email))
				searchresult.add(user);
			if(roleid!= -1 && user.getRoleid()==roleid+1)
				searchresult.add(user);
		}
		if(searchresult.isEmpty())
			tableID.setItems(table);
		else
			tableID.setItems(searchresult);		

	}

	
	/************************************************************************************8
	 * This function will allow to refresh the view. 
	 * @param event
	 */
	@FXML
	public void RefreshLogininfo(ActionEvent event) {
		this.searchrole.getSelectionModel().clearSelection();
		 this.searchusername.setText("");
		 this.searchemail.setText("");
		tableID.setItems(getData());
	}
	
	/****************************************************************************************************************
	 * This function will be used to initialize the Admin console with the initial load of the complete Login Details
	 * of all the users accessing this application. Admin will have all access rights to delete/update and records.
	 ***************************************************************************************************************
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		System.out.println("Welcome to Admin screen Initialization");

		iID.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getPersonid()));
		
		iusername.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getUsername())));
	
		iadmin.setCellValueFactory(c -> 
		new ReadOnlyBooleanWrapper( c.getValue().getAdmin()));
	
		iemail.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getEmail())));
		
		irole.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getRoleid()));
		
		/***************************************************************************************************
		 ********************----------| To Make all fields visible|--------------------********************
		 ***************************************************************************************************/
		//iID.setCellValueFactory(new PropertyValueFactory<User,Integer>("Personid"));
		//iusername.setCellValueFactory(new PropertyValueFactory<User,String>("Username"));
		//ipassword.setCellValueFactory(new PropertyValueFactory<User,String>("Password"));
		//iadmin.setCellValueFactory(new PropertyValueFactory<User,Boolean>("Admin"));
		//iquestion.setCellValueFactory(new PropertyValueFactory<User,String>("Secretquestion"));
		//ianswer.setCellValueFactory(new PropertyValueFactory<User,String>("Secretanswer"));
		//iemail.setCellValueFactory(new PropertyValueFactory<User,String>("Email"));
		//irole.setCellValueFactory(new PropertyValueFactory<User,Integer>("Roleid"));

		//Set the table contents to Login user administration tasks.
		tableID.setItems(getData());


		//Update the table to allow for the changes in username and password by admin
		tableID.setEditable(true);
		//iusername.setCellFactory(TextFieldTableCell.forTableColumn()); 
		//ipassword.setCellFactory(TextFieldTableCell.forTableColumn());
		iadmin.setCellFactory(TextFieldTableCell.<User,Boolean>forTableColumn(new BooleanStringConverter())); 
		//iquestion.setCellFactory(TextFieldTableCell.forTableColumn());
		//ianswer.setCellFactory(TextFieldTableCell.forTableColumn()); 
		//iemail.setCellFactory(TextFieldTableCell.forTableColumn());
		irole.setCellFactory(TextFieldTableCell.<User,Integer>forTableColumn(new IntegerStringConverter())); 
	
		//Get the Role list from DB for Search
		List<String> relationships = new ArrayList<>();
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "SELECT * FROM ROLE";
		ResultSet resultSet = null;

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			ResultSet rs = resultSet;
			while(rs.next())
			relationships.add(rs.getString(2));
			searchrole.setItems(FXCollections.observableArrayList(relationships));
			rs.close();
		} catch (SQLException e) {
			System.out.println("Failed to get Application Roles");
		}
	
		/***************************MAINTAIN SERVICES***********************************/
		/*******************************************************************************/
		iserviceID.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getChannelid()));
		
		iservicename.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getChannelname())));
		
		iservicedescription.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getChanneldesc())));
		
		servicesID.setItems(getServiceData());
		
		
		
	
	}

	private ObservableList<User> getData(){
		/****************************************************************************************************
		 *********************************get table data*****************************************************
		 ****************************************************************************************************
		 ***/
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<User> logindata = FXCollections.observableArrayList();
		String SQLQuery = "SELECT * FROM LOGIN";
		ResultSet resultSet = null;
		ResultSet rs = null;

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;
			Integer personid;
			String username;
			String password;
			Boolean admin;
			String secretquestion;
			String secretanswer;
			String email;
			Integer roleid;
			while(rs.next())
			{
				personid = rs.getInt(1);
				username= (String)rs.getString(2); 
				password = (String)rs.getString(3);
				admin = (Boolean)(rs.getString(4).equals("Y")? true : false);
				secretquestion = (String)rs.getString(5);
				secretanswer = (String)rs.getString(6);
				email = (String)rs.getString(7);
				roleid = rs.getInt(8);
				logindata.add(new User( 
						personid,
						username,
						password,
						admin,
						secretquestion,
						secretanswer,
						email,
						roleid
						));
			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Application Login Data");
		}	

		return logindata;
	}

	
	
	private ObservableList<Service> getServiceData(){
		/****************************************************************************************************
		 *********************************get table data*****************************************************
		 ****************************************************************************************************
		 ***/
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<Service> servicedata = FXCollections.observableArrayList();
		String SQLQuery = "SELECT * FROM SERVICE";
		ResultSet resultSet = null;
		ResultSet rs = null;

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;
			Integer serviceid;
			String servicename;
			String servicedesc;
			while(rs.next())
			{
				serviceid = rs.getInt(1);
				servicename= (String)rs.getString(2); 
				servicedesc = (String)rs.getString(3);
				servicedata.add(new Service( 
						serviceid,
						servicename,
						servicedesc
						));
			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Application Services Data");
		}	

		return servicedata;
	}

	/*****************************************************************************************************
	 * This function will allow the admin to double click the User name Cell and update the same
	 ***************************************************************************************************** 
	 * */
	@SuppressWarnings("rawtypes")
	public void changeUserUsernameCellEvent(CellEditEvent edittedCell)
	{
		User personselected = tableID.getSelectionModel().getSelectedItem();
		A_DatabaseCommunicationEngine DCE1 = new A_DatabaseCommunicationEngine();
		/************************Check if user with same email id already exists or not*******************************/		
		String CheckIfUserExistSQL = "SELECT COUNT(*) FROM LOGIN WHERE USERNAME='"+(edittedCell.getNewValue().toString())+"'";
		ResultSet resultSetUserExist;
		int UserExist = 0;
		try {
			resultSetUserExist = DCE1.getResultSet(CheckIfUserExistSQL);
			ResultSet rsUserExist = resultSetUserExist;
			while(rsUserExist.next())
				UserExist = rsUserExist.getInt(1);
			rsUserExist.close();
		} catch (SQLException e) {
		}

		if(UserExist!=0) {
			Alert alert =  new  Alert(AlertType.ERROR);
			alert.setContentText("user with username: "+(edittedCell.getNewValue().toString())+ " already exists.");
			alert.show();
		}
		else
		{

			if(util.Commit()){
				A_DatabaseCommunicationEngine DCE2 = new A_DatabaseCommunicationEngine();
				personselected.setUserName(edittedCell.getNewValue().toString());
				DCE2.CommitChanges("LOGIN", "USERNAME", personselected.getUsername(), personselected.getPersonid());
			}
		}
		tableID.setItems(getData());

	}
	/*****************************************************************************************************
	 * This function will allow the admin to double click the Password Cell and update the same
	 ***************************************************************************************************** 
	 * */
	@SuppressWarnings("rawtypes")
	public void changeUserPasswordCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setPassword(edittedCell.getNewValue().toString());
			DCE.CommitChanges("LOGIN","PASSWORD", personselected.getPassword(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}

	/****************************************************************************************************
	 * This function will allow the admin to double click the User name Cell and update the same
	 ****************************************************************************************************
	 */
	@SuppressWarnings("rawtypes")
	public void changeUseremailCellEvent(CellEditEvent edittedCell)
	{
		User personselected = tableID.getSelectionModel().getSelectedItem();
		A_DatabaseCommunicationEngine DCE1 = new A_DatabaseCommunicationEngine();
		/************************Check if user with same email id already exists or not*******************************/		
		String CheckIfUserExistSQL = "SELECT COUNT(*) FROM LOGIN WHERE EMAIL='"+(edittedCell.getNewValue().toString())+"'";
		ResultSet resultSetUserExist;
		int UserExist = 0;
		try {
			resultSetUserExist = DCE1.getResultSet(CheckIfUserExistSQL);
			ResultSet rsUserExist = resultSetUserExist;
			while(rsUserExist.next())
				UserExist = rsUserExist.getInt(1);
			rsUserExist.close();
		} catch (SQLException e) {
		}

		if(UserExist!=0) {
			Alert alert =  new  Alert(AlertType.ERROR);
			alert.setContentText("user with email: "+(edittedCell.getNewValue().toString())+ " already exists.");
			alert.show();
		}
		else
		{
			if(util.Commit()){
				A_DatabaseCommunicationEngine DCE2 = new A_DatabaseCommunicationEngine();
				personselected.setEmail(edittedCell.getNewValue().toString());
				DCE2.CommitChanges("LOGIN","EMAIL",personselected.getEmail(), personselected.getPersonid());
			}
		}
		tableID.setItems(getData());
	}

	/*****************************************************************************************************
	 * This function will allow the admin to double click the Password Cell and update the same
	 ***************************************************************************************************** 
	 * **/
	@SuppressWarnings("rawtypes")
	public void changeUserSecretQuestionCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setSecretquestion(edittedCell.getNewValue().toString());
			DCE.CommitChanges("LOGIN","SECRETQUESTION",personselected.getSecretquestion(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}

	/*****************************************************************************************************
	 * This function will allow the admin to double click the User name Cell and update the same
	 *****************************************************************************************************
	 */
	@SuppressWarnings("rawtypes")
	public void changeUserSecretAnswerCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setSecretanswer(edittedCell.getNewValue().toString());
			DCE.CommitChanges("LOGIN","SECRETANSWER",personselected.getSecretanswer(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}

	/*****************************************************************************************************
	 * This function will allow the admin to double click the Password Cell and update the same
	 *****************************************************************************************************
	 * */
	@SuppressWarnings("rawtypes")
	public void changeUserisAdminCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setAdmin(Boolean.valueOf(edittedCell.getNewValue().toString()));
			DCE.CommitChanges("LOGIN","ADMIN", (personselected.getAdmin()?"Y":"N"), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}	

	/****************************************************************************************************
	 **This function will allow the admin to double click the role Cell and update the same
	 ****************************************************************************************************
	 */
	@SuppressWarnings("rawtypes")
	public void changeUserroleCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setRoleid(Integer.valueOf(edittedCell.getNewValue().toString()));
			DCE.CommitChanges("LOGIN","ROLE", personselected.getRoleid(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}	

	
	/****************************************************************************************************
	 **This function will allow the admin to double click the service name Cell and update the same
	 ****************************************************************************************************
	 */
	@SuppressWarnings("rawtypes")
	public void changeServiceNameCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		Service Serviceselected = servicesID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			Serviceselected.setChannelname(edittedCell.getNewValue().toString());
			DCE.CommitServicesChanges("SERVICE","SERVICENAME", Serviceselected.getChannelname(), Serviceselected.getChannelid());
		}else{
			servicesID.setItems(getServiceData());
		}
	}	
	
	
	/****************************************************************************************************
	 **This function will allow the admin to double click the service description Cell and update the same
	 ****************************************************************************************************
	 */
	@SuppressWarnings("rawtypes")
	public void changeServiceDescCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		Service Serviceselected = servicesID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			Serviceselected.setChanneldesc(edittedCell.getNewValue().toString());
			DCE.CommitServicesChanges("SERVICE","SERVICEDESC", Serviceselected.getChanneldesc(), Serviceselected.getChannelid());
		}else{
			servicesID.setItems(getServiceData());
		}
	}	
	
	
	
	
}

