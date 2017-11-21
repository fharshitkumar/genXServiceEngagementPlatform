package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.A_DatabaseCommunicationEngine;
import utility.ApplicationUtilities;

public class AdminDashaboardController implements Initializable{


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

	@FXML
	Pane FavouritePanel;
	
	@FXML
	Pane LoginTablePanel;
	
	@FXML
	JFXTextField searchusername;
	
	@FXML
	JFXTextField searchemail;
	
	@FXML
	JFXComboBox searchrole;
	
	ApplicationUtilities util;

	public AdminDashaboardController() {
		util = new ApplicationUtilities();
	}

	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		util.close(event);
	}
	
	@FXML
	public void OpenLoginTablePanel(ActionEvent event) {
		System.out.println("You clicked Admin Home button");
		FavouritePanel.setVisible(false);
		LoginTablePanel.setVisible(true);		
	}
	
	@FXML
	public void OpenFavoritePanel(ActionEvent event) {
		System.out.println("You clicked Favourite Home button");
		FavouritePanel.setVisible(true);
		LoginTablePanel.setVisible(false);

	}
	

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

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		System.out.println("Welcome to Admin screen Initialization");

		
		iID.setCellValueFactory(new PropertyValueFactory<User,Integer>("Personid"));

		iusername.setCellValueFactory(new PropertyValueFactory<User,String>("Username"));

		ipassword.setCellValueFactory(new PropertyValueFactory<User,String>("Password"));

		iadmin.setCellValueFactory(new PropertyValueFactory<User,Boolean>("Admin"));

		iquestion.setCellValueFactory(new PropertyValueFactory<User,String>("Secretquestion"));

		ianswer.setCellValueFactory(new PropertyValueFactory<User,String>("Secretanswer"));

		iemail.setCellValueFactory(new PropertyValueFactory<User,String>("Email"));

		irole.setCellValueFactory(new PropertyValueFactory<User,Integer>("Roleid"));

		//Set the table contents to Login user administration tasks.
		tableID.setItems(getData());


		//Update the table to allow for the changes in username and password by admin
		tableID.setEditable(true);
		iusername.setCellFactory(TextFieldTableCell.forTableColumn()); 
		ipassword.setCellFactory(TextFieldTableCell.forTableColumn());
		iadmin.setCellFactory(TextFieldTableCell.<User,Boolean>forTableColumn(new BooleanStringConverter())); 
		iquestion.setCellFactory(TextFieldTableCell.forTableColumn());
		ianswer.setCellFactory(TextFieldTableCell.forTableColumn()); 
		iemail.setCellFactory(TextFieldTableCell.forTableColumn());
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
	
	
	}

	private ObservableList<User> getData(){
		/*********************get table data*****************************************/
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


	/***This function will allow the admin to double click the User name Cell and update the same**/
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
				DCE2.CommitChanges("USERNAME", personselected.getUsername(), personselected.getPersonid());
			}
		}
		tableID.setItems(getData());

	}
	/***This function will allow the admin to double click the Password Cell and update the same**/
	@SuppressWarnings("rawtypes")
	public void changeUserPasswordCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setPassword(edittedCell.getNewValue().toString());
			DCE.CommitChanges("PASSWORD", personselected.getPassword(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}

	/***This function will allow the admin to double click the User name Cell and update the same**/
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
				DCE2.CommitChanges("EMAIL",personselected.getEmail(), personselected.getPersonid());
			}
		}
		tableID.setItems(getData());
	}

	/***This function will allow the admin to double click the Password Cell and update the same**/
	@SuppressWarnings("rawtypes")
	public void changeUserSecretQuestionCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setSecretquestion(edittedCell.getNewValue().toString());
			DCE.CommitChanges("SECRETQUESTION",personselected.getSecretquestion(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}

	/***This function will allow the admin to double click the User name Cell and update the same**/
	@SuppressWarnings("rawtypes")
	public void changeUserSecretAnswerCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setSecretanswer(edittedCell.getNewValue().toString());
			DCE.CommitChanges("SECRETANSWER",personselected.getSecretanswer(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}

	/***This function will allow the admin to double click the Password Cell and update the same**/
	@SuppressWarnings("rawtypes")
	public void changeUserisAdminCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setAdmin(Boolean.valueOf(edittedCell.getNewValue().toString()));
			DCE.CommitChanges("ADMIN", (personselected.getAdmin()?"Y":"N"), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}	

	/***This function will allow the admin to double click the role Cell and update the same**/
	@SuppressWarnings("rawtypes")
	public void changeUserroleCellEvent(CellEditEvent edittedCell)
	{
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		User personselected = tableID.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			personselected.setRoleid(Integer.valueOf(edittedCell.getNewValue().toString()));
			DCE.CommitChanges("ROLE", personselected.getRoleid(), personselected.getPersonid());
		}else{
			tableID.setItems(getData());
		}
	}	

}

