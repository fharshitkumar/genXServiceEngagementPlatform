package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.A_DatabaseCommunicationEngine;
import utility.ApplicationUtilities;

public class SignupController implements 	Initializable{

	@FXML 
	private JFXButton backBtn;
	
	@FXML 
	private Label lblStatus;
	
	@FXML 
	private JFXButton closeBtn;
	
	@FXML
	private JFXTextField username;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXPasswordField confirmpassword;
	
	@FXML
	private JFXTextField secretquestion;

	@FXML
	private JFXTextField secretanswer;

	@FXML
	private JFXTextField email;
	
	@FXML
	private JFXComboBox<String> relationship;

	ApplicationUtilities util;
	
	public SignupController() {
		super();
		util = new ApplicationUtilities();
	}
	
	@FXML
	public void back(ActionEvent event) {
		util.back("/view/Login.fxml",event);
	}
	
	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		util.close(event);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> relationships = new ArrayList<>();
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "SELECT * FROM ROLE";
		ResultSet resultSet = null;

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			ResultSet rs = resultSet;
			while(rs.next())
			relationships.add(rs.getString(2));
			relationship.setItems(FXCollections.observableArrayList(relationships));
			rs.close();
		} catch (SQLException e) {
			System.out.println("Failed to get Application Roles");
		}	
	}
	
	@SuppressWarnings("unused")
	public void signup(ActionEvent event){
		String username = this.username.getText();
		String password = this.password.getText();
		String confirmpassword = this.confirmpassword.getText();
		String secretquestion = this.secretquestion.getText();
		String secretanswer = this.secretanswer.getText();
		String email = this.email.getText();
		
		/******Check if user name is left blank or empty*******/
		if(username == null || username.trim().equals("")) {
			lblStatus.setText("Username cannot be empty or spaces");
			lblStatus.setStyle("-fx-text-fill:red");
			return;
		}
		
		/******Check if password is left blank or empty*******/
		if(password == null || password.trim().equals("")) {
			lblStatus.setText("Password cannot be empty or spaces");
			lblStatus.setStyle("-fx-text-fill:red");
			return;
		}

		/******Check if confirm password is left blank or empty*******/
		if(confirmpassword == null || confirmpassword.trim().equals("")) {
			lblStatus.setText("Confirm Password cannot be empty or spaces");
			lblStatus.setStyle("-fx-text-fill:red");
			return;
		}
		
		/******Check if secret question is left blank or empty*******/
		if(secretquestion == null || secretquestion.trim().equals("")) {
			lblStatus.setText("Secret question cannot be empty or spaces");
			lblStatus.setStyle("-fx-text-fill:red");
			return;
		}
		
		/******Check if secret answer is left blank or empty*******/
		if(secretanswer == null || secretanswer.trim().equals("")) {
			lblStatus.setText("Secret answer cannot be empty or spaces");
			lblStatus.setStyle("-fx-text-fill:red");
		}
		
		/******Check if email is left blank or empty*******/
		if(email == null || email.trim().equals("")) {
			lblStatus.setText("email cannot be empty or spaces");
			lblStatus.setStyle("-fx-text-fill:red");
		}
		
		/******Check if relationship is left blank or empty*******/
		String selectedRelationship = null;
		try {
		selectedRelationship = relationship.getSelectionModel().getSelectedItem().toString();
		}catch(NullPointerException e) {
			lblStatus.setText("Relationship with us cannot be empty or spaces");
			lblStatus.setStyle("-fx-text-fill:red");
		return;
		}
		
		/******Check if password and confirm password is same or not*******/
		if(!password.equals(confirmpassword)) {
			lblStatus.setText("Password entered are not same");
			lblStatus.setStyle("-fx-text-fill:red");
			return;
		}
		
		
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		
		
		boolean SignupFlag = true;  
		try {
			
		/************************Check if user with same email id already exists or not*******************************/		
		String CheckIfUserExistSQL = "SELECT COUNT(*) FROM LOGIN WHERE EMAIL='"+email+"'";
		ResultSet resultSetUserExist = DCE.getResultSet(CheckIfUserExistSQL);
		ResultSet rsUserExist = resultSetUserExist;
		int UserExist = 0;
		while(rsUserExist.next())
			UserExist = rsUserExist.getInt(1);
		rsUserExist.close();
		/************************Check if username already exists  or not *******************************************/
		String CheckIfUserNameExistSQL = "SELECT COUNT(*) FROM LOGIN WHERE USERNAME='"+username+"'";
		ResultSet resultSetUserNameExist = DCE.getResultSet(CheckIfUserNameExistSQL);
		ResultSet rsUserNameExist = resultSetUserNameExist;
		int UserNameExist = 0;
		while(rsUserNameExist.next())
			UserNameExist = rsUserNameExist.getInt(1);
		rsUserNameExist.close();
		
		if(UserExist!=0) {
			lblStatus.setText("user with email: "+email+ " already exists. Kindly login with your credentials");
			lblStatus.setStyle("-fx-text-fill:red");
			SignupFlag = false;
		} 
		else if(UserNameExist!=0) {
			lblStatus.setText("user with username: "+username+ " already exists. Kindly choose another username");
			lblStatus.setStyle("-fx-text-fill:red");
			SignupFlag = false;
		}		
		else {
		String SQLQuery = "SELECT COUNT(PERSONID) FROM LOGIN";
		ResultSet resultSet = DCE.getResultSet(SQLQuery);
		ResultSet rs = resultSet;
		int personID = 0;
		while(rs.next())
			personID = rs.getInt(1)+1;
		rs.close();
		String sql = "INSERT INTO LOGIN" + 
				" VALUES('"
				+ personID + "', '"
				+ username + "', '"
				+ password + "', '"
				+ "N"+"', '"
				+ secretquestion + "', '"
				+ secretanswer + "', '"
				+ email + "', '"
				+ (relationship.getSelectionModel().getSelectedIndex() + 1) +"'"
				+ ")";
			DCE.DDLCommandDatabase(sql);
		}
		}catch (SQLException e) {
			System.out.println("There is an issue in inserting into the backend during signup");
			SignupFlag = false;
			return;
		}
		
		if(SignupFlag) {
		lblStatus.setText("Thanks for signing up with us. Please Check mail for more details.");
		lblStatus.setStyle("-fx-text-fill:green");
		}
	}
}