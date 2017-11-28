package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Incident;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import models.A_IncidentManagementEngine;
import models.ApplicationUser;
import utility.ApplicationUtilities;

public class CustomerDashaboardController implements Initializable {

	@FXML
	private Pane homepanel;

	@FXML
	private Pane personalinfopane;

	@FXML
	private Pane servicepane;

	@FXML
	private Pane twitterpane;

	@FXML
	private Pane facebookpanel;

	@FXML
	public void OpenCustomerHomePanel(ActionEvent event) {
		System.out.println("You clicked Home button");
		homepanel.setVisible(true);
		personalinfopane.setVisible(false);
		servicepane.setVisible(false);
		twitterpane.setVisible(false);
		facebookpanel.setVisible(false);
	}



	@FXML
	public void OpenTwitterPanel(ActionEvent event) {
		System.out.println("You clicked Twitter button");
		twitterpane.setVisible(true);
	}

	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		util.close(event);
	}

	@FXML
	public void PersonalInfoOpen(ActionEvent event) {
		personalinfopane.setVisible(true);
	}

	@FXML
	public void serviceOpen(ActionEvent event) {
		servicepane.setVisible(true);
	}



	@FXML
	private JFXTextArea wallpost;

	@FXML
	public void FacebookPost(ActionEvent event) {

		System.out.println("You Posted "+ wallpost.getText());
		wallpost.setText("");
	}



	@FXML
	private JFXTextArea tweetwall;

	@FXML
	private ImageView popuptweet;

	@FXML
	private JFXButton tweet;

	@FXML
	private JFXButton closetwitter;

	@FXML
	public void TwitterPost(ActionEvent event) {
		System.out.println("You Posted "+ tweetwall.getText());
		tweetwall.setText("");		
	}	
	@FXML
	public void createTwitterPost(ActionEvent event) {

		System.out.println("You clicked to create new twitter post, opening the dialog");
		tweetwall.setVisible(true);
		popuptweet.setVisible(true);
		tweet.setVisible(true);
		closetwitter.setVisible(false);

	}

	@FXML
	public void closeTwitterPost(ActionEvent event) {

		System.out.println("You clicked to close twitter post, closing the dialog");
		tweetwall.setText("");
		tweetwall.setVisible(false);
		popuptweet.setVisible(false);
		tweet.setVisible(false);
		closetwitter.setVisible(false);
	}




	@FXML
	public void back(ActionEvent event) {
		personalinfopane.setVisible(false);
		homepanel.setVisible(true);
	}

	@FXML
	public void backService(ActionEvent event) {
		servicepane.setVisible(false);
		homepanel.setVisible(true);
	}

	@FXML
	public void backsocialmedia(ActionEvent event) {
		twitterpane.setVisible(false);
		homepanel.setVisible(true);
	}

	@FXML
	public void backfacebook(ActionEvent event) {
		facebookpanel.setVisible(false);
		homepanel.setVisible(true);
	}



	@FXML
	public void RefreshServiceHistory(ActionEvent event) {
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		/***Facebook API call****/
		
		ObservableList<Incident> incidentdata = IME.displayTickets();
		customerservicehistory.setItems(incidentdata);
	}

	@FXML 
	private JFXButton createincidentbtn;

	@FXML 
	private JFXTextField shorttext;

	@FXML 
	private JFXTextField problem;


	@FXML
	public void OpenFacebookPanel(ActionEvent event) {
		System.out.println("You clicked Facebook button");
		facebookpanel.setVisible(true);
	}
	

	@FXML
	public void OnCreateIncident (ActionEvent event) {
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();

		/***************************FOR REFERENCE TO CREATE TICKET***************************************/
		//				IME.createIncidentCustomer(
		//						customerid,  
		//						channelid, 
		//						serviceid, 
		//						shorttext, 
		//						problem, 
		//						priority, 
		//						createdon, 
		//						status, );
		/***********************************************************************************************/

		String Subject = shorttext.getText();
		String Problem = problem.getText();

		IME.createIncident(
				null, 
				ApplicationUser.applicationUser.getPersonid(), 
				300, 
				service.getSelectionModel().getSelectedIndex()+1, 
				Subject, 
				Problem, 
				priority.getValue(), 
				new Timestamp(System.currentTimeMillis()), 
				"OPEN", 
				"", 
				null, 
				null, 
				"", 
				"", 
				false, 
				3);

		shorttext.setText("");
		problem.setText("");
		service.getSelectionModel().clearSelection();
		priority.getSelectionModel().clearSelection();
	}

	@FXML
	public void serviceselected(ActionEvent event) {
		Button service = new Button();
		service = (Button)event.getSource();
		System.out.println("You pressed : "+ service.getId());
	}

	ApplicationUtilities util;
	@FXML
	private JFXComboBox<String> service;
	@FXML
	private JFXComboBox<String> priority;

	@FXML
	private TableView<Incident> customerservicehistory;

	@FXML
	private TableColumn<Incident,Integer> iincidentid;

	@FXML
	private TableColumn<Incident,String> ishorttext;

	@FXML
	private TableColumn<Incident,String> iproblem;

	@FXML
	private TableColumn<Incident,String> isolution;

	@FXML
	private TableColumn<Incident,Integer> ichannelid;

	@FXML JFXButton personalinfobtn;

	@FXML Label personalinfolabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Testing: Control came to Customer Dashboard Controller");

		util = new ApplicationUtilities();

		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();

		/**************************Bind the Data Type to the javaFX Table **************************/
		iincidentid.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getIncidentid()));

		ishorttext.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getShorttext())));

		iproblem.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getProblem())));

		isolution.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getSolution())));

		ichannelid.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getChannelid()));		

		//		/**************************Bind the Ticket Data to the javaFX Table **************************/	
		//		ObservableList<Incident> incidentdata = IME.displayTickets();
		//		customerservicehistory.setItems(incidentdata);
		//
		/***************SHOW THE SERVICES OPTIONS ON CREATE INCIDENT SCREEN *******************/
		service.setItems(FXCollections.observableArrayList(IME.getServices()));

		/***************SHOW THE PRIORITY OPTIONS ON CREATE INCIDENT SCREEN *******************/
		priority.setItems(FXCollections.observableArrayList(IME.getPriority()));
	}
}
