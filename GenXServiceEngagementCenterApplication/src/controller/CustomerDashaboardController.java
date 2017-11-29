package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entities.Incident;
import entities.PersonalInfo;
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
import models.A_PersonInformationEngine;
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
		if(util.isWindowclosestatus())
			util.back("/view/Login.fxml",event);
	}

	@FXML
	public void PersonalInfoOpen(ActionEvent event) {
		personalinfopane.setVisible(true);
		displayCustomer();
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

		String Subject = shorttext.getText();
		String Problem = problem.getText();

		IME.createIncident(
				null, 
				ApplicationUser.getApplicationUser().getPersonid(), 
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
				ObservableList<Incident> incidentdata = IME.displayTickets();
				customerservicehistory.setItems(incidentdata);
		
		/***************SHOW THE SERVICES OPTIONS ON CREATE INCIDENT SCREEN *******************/
		service.setItems(FXCollections.observableArrayList(IME.getServices()));

		/***************SHOW THE PRIORITY OPTIONS ON CREATE INCIDENT SCREEN *******************/
		priority.setItems(FXCollections.observableArrayList(IME.getPriority()));
		
		
		
		
		List<String> genderoption = new ArrayList<>();
		genderoption.add("M");
		genderoption.add("F");
		gender.setItems(FXCollections.observableArrayList(genderoption));	

		displayCustomer();
	}

	void displayCustomer() {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		PersonalInfo customerinfo = PIE.displayPersonalInfo(ApplicationUser.getApplicationUser().getPersonid());
		
		 this.fname.setText(customerinfo.getFname());
		 this.lname.setText(customerinfo.getLname());
		 this.contact.setText(customerinfo.getContact());
		 this.address.setText(customerinfo.getAddress());
		 this.gender.getSelectionModel().select(customerinfo.getGender());
		 dateofbirth.setText(customerinfo.getDateofbirth().toString());
		 city.setText(customerinfo.getCity());
		 state.setText(customerinfo.getState());
		 zipcode.setText(customerinfo.getZipcode());
	}
	
	
	@FXML
	private JFXTextField fname;
	
	@FXML
	private JFXTextField lname;
	
	@FXML
	private JFXTextField contact;
	
	@FXML
	private JFXTextField dateofbirth;
	
	@FXML
	private JFXTextField address;
	
	@FXML
	private JFXTextField city;
	
	@FXML
	private JFXTextField state;
	
	@FXML
	private JFXTextField zipcode;
	
	@FXML
	private JFXComboBox<String> gender;
	
	@FXML
	private JFXButton serviceOffice;
	
	@FXML
	public void updatePersonalInfo(ActionEvent event) {
		
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		

		Date sqlDate ;
		
		try {
			
			sqlDate = java.sql.Date.valueOf(dateofbirth.getText() );
			PIE.updatePerson(
					ApplicationUser.getApplicationUser().getPersonid(), 
					fname.getText(), 
					lname.getText(), 
					contact.getText(), 
					address.getText(), 
					gender.getSelectionModel().getSelectedItem(), 
					sqlDate, 
					city.getText(), 
					state.getText(), 
					zipcode.getText());

			
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		
	}

	/***********************************************************************************/
	/********************ALL THE SERVICES SELECTIONS FLAGS ON UI************************/
    /***********************************************************************************/
	@FXML
	private MaterialDesignIconView selectedflagWord;
	@FXML
	private MaterialDesignIconView selectedflagExcel;
	@FXML
	private MaterialDesignIconView selectedflagPowerPoint;
	@FXML
	private MaterialDesignIconView selectedflagOutlook;
	@FXML
	private MaterialDesignIconView selectedflagOneNote;
	@FXML
	private MaterialDesignIconView selectedflagOneDrive;
	@FXML
	private MaterialDesignIconView selectedflagPublisher;
	@FXML
	private MaterialDesignIconView selectedflagAccess;
	@FXML
	private MaterialDesignIconView selectedflagPictureMgr;
	@FXML
	private MaterialDesignIconView selectedflagSharePoint;
	@FXML
	private MaterialDesignIconView selectedflagSkype;
	@FXML
	private MaterialDesignIconView selectedflagExchange;
	@FXML
	private MaterialDesignIconView selectedflagYammer;
	@FXML
	private MaterialDesignIconView selectedflagSway;
	@FXML
	private MaterialDesignIconView selectedflagPowerBI;
	@FXML
	private MaterialDesignIconView selectedflagVisio;
	@FXML
	private MaterialDesignIconView selectedflagProject;

	
	
	/**
	 * ******************Microsoft Project*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceProject(ActionEvent event) {
		
		if(selectedflagProject.isVisible()) {
			selectedflagProject.setVisible(false);
		}else {
			selectedflagProject.setVisible(true);
		}
		
	}

	/**
	 * ******************Microsoft Visio*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceVisio(ActionEvent event) {
		if(selectedflagVisio.isVisible()) {
			selectedflagVisio.setVisible(false);
		}else {
			selectedflagVisio.setVisible(true);
		}

	}

	/**
	 * ******************Microsoft PowerBI*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePowerBI(ActionEvent event) {
		
	}

	/**
	 * ******************Microsoft Sway*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceSway(ActionEvent event) {
		
		if(selectedflagSway.isVisible()) {
			selectedflagSway.setVisible(false);
		}else {
			selectedflagSway.setVisible(true);
		}
	}

	/**
	 * ******************Microsoft Yammer*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceYammer(ActionEvent event) {
		
		if(selectedflagYammer.isVisible()) {
			selectedflagYammer.setVisible(false);
		}else {
			selectedflagYammer.setVisible(true);
		}
	}

	/**
	 * ******************Microsoft Exchange*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceExchange(ActionEvent event) {
		if(selectedflagExchange.isVisible()) {
			selectedflagExchange.setVisible(false);
		}else {
			selectedflagExchange.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft Skype*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceSkype(ActionEvent event) {
		if(selectedflagSkype.isVisible()) {
			selectedflagSkype.setVisible(false);
		}else {
			selectedflagSkype.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft SharePoint*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceSharePoint(ActionEvent event) {
		if(selectedflagSharePoint.isVisible()) {
			selectedflagSharePoint.setVisible(false);
		}else {
			selectedflagSharePoint.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft PictureMgr*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePictureMgr(ActionEvent event) {
		if(selectedflagPictureMgr.isVisible()) {
			selectedflagPictureMgr.setVisible(false);
		}else {
			selectedflagPictureMgr.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft Access*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceAccess(ActionEvent event) {
		if(selectedflagAccess.isVisible()) {
			selectedflagAccess.setVisible(false);
		}else {
			selectedflagAccess.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft Publisher*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePublisher(ActionEvent event) {
		if(selectedflagPublisher.isVisible()) {
			selectedflagPublisher.setVisible(false);
		}else {
			selectedflagPublisher.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft OneDrive*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceOneDrive(ActionEvent event) {
		if(selectedflagOneDrive.isVisible()) {
			selectedflagOneDrive.setVisible(false);
		}else {
			selectedflagOneDrive.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft OneNote*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceOneNote(ActionEvent event) {
		if(selectedflagOneNote.isVisible()) {
			selectedflagOneNote.setVisible(false);
		}else {
			selectedflagOneNote.setVisible(true);
		}
	}

	/**
	 * ******************Microsoft Outlook*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceOutlook(ActionEvent event) {
		if(selectedflagOutlook.isVisible()) {
			selectedflagOutlook.setVisible(false);
		}else {
			selectedflagOutlook.setVisible(true);
		}
	}


	/**
	 * ******************Microsoft PowerPoint*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePowerPoint(ActionEvent event) {
		if(selectedflagPowerPoint.isVisible()) {
			selectedflagPowerPoint.setVisible(false);
		}else {
			selectedflagPowerPoint.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft Excel*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceExcel(ActionEvent event) {
		if(selectedflagExcel.isVisible()) {
			selectedflagExcel.setVisible(false);
		}else {
			selectedflagExcel.setVisible(true);
		}
	}
	/**
	 * ******************Microsoft Word*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceWord(ActionEvent event) {
		if(selectedflagWord.isVisible()) {
			selectedflagWord.setVisible(false);
		}else {
			selectedflagWord.setVisible(true);
		}
	}


	
	
}
