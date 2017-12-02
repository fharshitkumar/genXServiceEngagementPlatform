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
import entities.Channel;
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
import models.A_InteractionManagementEngine;
import models.A_PersonInformationEngine;
import models.ApplicationUser;
import models.V_ViewManagementEngine;
import tableviews.CustomerInfoView;
import utility.ApplicationUtilities;

/**
 * *************************************************************************************************
 * Class		: CustomerDashaboardController		
 * Description	: CustomerDashaboardController to controller Customer  Screen
 *								
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: CustomerDashaboardController.java       	
 * **************************************************************************************************
 *
*/



public class CustomerDashaboardController implements Initializable {

	
	/***********************************************************************************/
	/********************ALL THE NAVIGATION PANES IN CUSTOMER SCREEN********************/
    /***********************************************************************************/
	
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

	

	
	/***********************************************************************************/
	/**********************************SOCIAL MEDIA POSTS*******************************/
    /***********************************************************************************/
	
	

	/********---------------| FACEBOOK |--------------*********/
	@FXML
	private JFXTextArea wallpost;
	
	/********---------------| TWITTER |--------------*********/
	@FXML
	private JFXTextArea tweetwall;
	@FXML
	private ImageView popuptweet;
	@FXML
	private JFXButton tweet;
	@FXML
	private JFXButton closetwitter;
	
	


	/***********************************************************************************/
	/************************CREATE APPLICATION INCICDENT*******************************/
    /***********************************************************************************/
	
	@FXML 
	private JFXButton createincidentbtn;
	@FXML 
	private JFXTextField shorttext;
	@FXML 
	private JFXTextField problem;
	
	

	
	
	
	/***********************************************************************************/
	/************************MONITOR APPLICATION INCICDENT******************************/
    /***********************************************************************************/
	
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
	private TableColumn<Incident,String> ichannelid;
	@FXML 
	private JFXButton personalinfobtn;
	@FXML 
	private Label personalinfolabel;



	/***********************************************************************************/
	/******************************WELCOME SCREEN NAME**********************************/
    /***********************************************************************************/

	@FXML
	private Label customernamewelcomescreen;


	
	
	
	/***********************************************************************************/
	/**********************CUSTOMER PERSONAL INFORMATION********************************/
    /***********************************************************************************/

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
		
		/*********************************************************************************************/
		/********** -------| GET ALL THE INCIDENTS FROM SOCIAL MEDIA |-------- ***********************/
		/*********************************************************************************************/ 
		A_InteractionManagementEngine.FacebookManager();
		
		
		
		
		/*********************************************************************************************/
		/************* -------| GET ALL THE INCIDENTS FROM DATABASE |-------- ***********************/
		/*********************************************************************************************/ 
		servicepane.setVisible(true);
		ObservableList<String> items =FXCollections.observableArrayList ();
		V_ViewManagementEngine VME = new V_ViewManagementEngine();
		ObservableList<CustomerInfoView> customerinfoview = VME.customerinfoviewer(ApplicationUser.getApplicationUser().getPersonid());
		for(CustomerInfoView c : customerinfoview)
		{
			items.add(c.getServicename());
		}
		service.setItems(items);
		
		
		
		
	}



	@FXML
	public void FacebookPost(ActionEvent event) {

		System.out.println("You Posted "+ wallpost.getText());
		wallpost.setText("");
	}





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

		/*********************************************************************************************/
		/********** -------| GET ALL THE INCIDENTS FROM SOCIAL MEDIA |-------- ***********************/
		/*********************************************************************************************/ 
		A_InteractionManagementEngine.FacebookManager();
		
		ObservableList<Incident> incidentdata = IME.displayTickets(ApplicationUser.getApplicationUser().getPersonid(),false);
		customerservicehistory.setItems(incidentdata);
	}




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

	private String getChannelName(Integer channelid)
	{
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		Channel Channelname = IME.displayChannel(channelid);
		return Channelname.getChannelname();
	}
	
	private String changenulltoblank(String stringobject)
	{
		if(stringobject.equals(null))
		return "<AWAITING FOR SOLUTION>";
		else
			return stringobject;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Testing: Control came to Customer Dashboard Controller");
		V_ViewManagementEngine VME = new V_ViewManagementEngine();
		util = new ApplicationUtilities();

		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();

		ObservableList<CustomerInfoView> CIV =	VME.customerinfoviewer(ApplicationUser.getApplicationUser().getPersonid());
		String WelcomeScreenUSername = CIV.get(0).getFname();
		if(WelcomeScreenUSername == null)
			WelcomeScreenUSername = " ";
		customernamewelcomescreen.setText(WelcomeScreenUSername);
		
		/**************************Bind the Data Type to the javaFX Table **************************/
		iincidentid.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getIncidentid()));

		ishorttext.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getShorttext())));

		iproblem.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getProblem())));

		isolution.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( changenulltoblank(String.valueOf( c.getValue().getSolution()))));

		ichannelid.setCellValueFactory( c ->
		new ReadOnlyStringWrapper( (getChannelName(c.getValue().getChannelid())) ));		

		//		/**************************Bind the Ticket Data to the javaFX Table **************************/	
				ObservableList<Incident> incidentdata = IME.displayTickets(ApplicationUser.getApplicationUser().getPersonid(),false);
				customerservicehistory.setItems(incidentdata);
		
		/***************SHOW THE SERVICES OPTIONS ON CREATE INCIDENT SCREEN *******************/
		ObservableList<String> items =FXCollections.observableArrayList ();
		
		ObservableList<CustomerInfoView> customerinfoview = VME.customerinfoviewer(ApplicationUser.getApplicationUser().getPersonid());
		for(CustomerInfoView c : customerinfoview)
		{
			items.add(c.getServicename());
		}
		service.setItems(items);
		
		//service.setItems(FXCollections.observableArrayList(IME.getServices()));

		/***************SHOW THE PRIORITY OPTIONS ON CREATE INCIDENT SCREEN *******************/
		priority.setItems(FXCollections.observableArrayList(IME.getPriority()));
		
		
		
		ObservableList<CustomerInfoView> customerserviceinfo = VME.customerinfoviewer(ApplicationUser.getApplicationUser().getPersonid());
		if(customerserviceinfo!= null) {
			for(CustomerInfoView service : customerserviceinfo ) {
				switch (service.getServiceid()) {
				case 1:
					selectedflagWord.setVisible(true);
					break;
				case 2:
					selectedflagExcel.setVisible(true);
					break;
				case 3:
					selectedflagPowerPoint.setVisible(true);
					break;
				case 4:
					selectedflagOutlook.setVisible(true);
					break;
				case 5:
					selectedflagOneNote.setVisible(true);
					break;
				case 6:
					selectedflagOneDrive.setVisible(true);
					break;
				case 7:
					selectedflagPublisher.setVisible(true);
					break;
				case 8:
					selectedflagAccess.setVisible(true);
					break;
				case 9:
					selectedflagPictureMgr.setVisible(true);
					break;
				case 10:
					selectedflagSharePoint.setVisible(true);
					break;
				case 11:
					selectedflagSkype.setVisible(true);
					break;
				case 12:
					selectedflagExchange.setVisible(true);
					break;
				case 13:
					selectedflagYammer.setVisible(true);
					break;
				case 14:
					selectedflagSway.setVisible(true);
					break;
				case 15:
					selectedflagPowerBI.setVisible(true);
					break;
				case 16:
					selectedflagVisio.setVisible(true);
					break;
				case 17:
					selectedflagProject.setVisible(true);
					break;

				default:
					break;
				}
			}
		}
		
		
		
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
	public void updatePersonalInfo(ActionEvent event) {
		
		/******************************************************************************
		 * ---------------------| PART 1: Update the personal information
		 * ****************************************************************************
		 */
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
			System.out.println("Couldn't update the personal information.");
			System.out.println(e.getMessage());
		}
	
		/******************************************************************************
		 * ---------------------| PART 2: Update the customer services information
		 * ****************************************************************************
		 */
		
		
		
		
	}


	
	
	/**
	 * ******************Microsoft Project*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceProject(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagProject.isVisible()) {
			selectedflagProject.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 17, false);
		}else {
			selectedflagProject.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 17, true);
		}
		
	}

	/**
	 * ******************Microsoft Visio*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceVisio(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagVisio.isVisible()) {
			selectedflagVisio.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 16, false);
		}else {
			selectedflagVisio.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 16, true);
		}

	}

	/**
	 * ******************Microsoft PowerBI*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePowerBI(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagPowerBI.isVisible()) {
			selectedflagPowerBI.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 15, false);
		}else {
			selectedflagPowerBI.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 15, true);
		}

	}

	/**
	 * ******************Microsoft Sway*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceSway(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagSway.isVisible()) {
			selectedflagSway.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 14, false);
		}else {
			selectedflagSway.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 14, true);
		}
	}

	/**
	 * ******************Microsoft Yammer*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceYammer(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagYammer.isVisible()) {
			selectedflagYammer.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 13, false);
		}else {
			selectedflagYammer.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 13, true);
		}
	}

	/**
	 * ******************Microsoft Exchange*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceExchange(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagExchange.isVisible()) {
			selectedflagExchange.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 12, false);
		}else {
			selectedflagExchange.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 12, true);
		}
	}
	/**
	 * ******************Microsoft Skype*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceSkype(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagSkype.isVisible()) {
			selectedflagSkype.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 11, false);
		}else {
			selectedflagSkype.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 11, true);
		}
	}
	/**
	 * ******************Microsoft SharePoint*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceSharePoint(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagSharePoint.isVisible()) {
			selectedflagSharePoint.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 10, false);
		}else {
			selectedflagSharePoint.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 10, true);
		}
	}
	/**
	 * ******************Microsoft PictureMgr*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePictureMgr(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagPictureMgr.isVisible()) {
			selectedflagPictureMgr.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 9, false);
		}else {
			selectedflagPictureMgr.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 9, true);
		}
	}
	/**
	 * ******************Microsoft Access*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceAccess(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagAccess.isVisible()) {
			selectedflagAccess.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 8, false);
		}else {
			selectedflagAccess.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 8, true);
		}
	}
	/**
	 * ******************Microsoft Publisher*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePublisher(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagPublisher.isVisible()) {
			selectedflagPublisher.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 7, false);
		}else {
			selectedflagPublisher.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 7, true);
		}
	}
	/**
	 * ******************Microsoft OneDrive*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceOneDrive(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagOneDrive.isVisible()) {
			selectedflagOneDrive.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 6, false);
		}else {
			selectedflagOneDrive.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 6, true);
		}
	}
	/**
	 * ******************Microsoft OneNote*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceOneNote(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagOneNote.isVisible()) {
			selectedflagOneNote.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 5, false);
		}else {
			selectedflagOneNote.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 5, true);
		}
	}

	/**
	 * ******************Microsoft Outlook*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceOutlook(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagOutlook.isVisible()) {
			selectedflagOutlook.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 4, false);
		}else {
			selectedflagOutlook.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 4, true);
		}
	}


	/**
	 * ******************Microsoft PowerPoint*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedservicePowerPoint(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagPowerPoint.isVisible()) {
			selectedflagPowerPoint.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 3, false);
		}else {
			selectedflagPowerPoint.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 3, true);
		}
	}
	/**
	 * ******************Microsoft Excel*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceExcel(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagExcel.isVisible()) {
			selectedflagExcel.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 2, false);
		}else {
			selectedflagExcel.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 2, true);
		}
	}
	/**
	 * ******************Microsoft Word*******************************	
	 * @param event
	 */
	@FXML
	public void serviceselectedserviceWord(ActionEvent event) {
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		if(selectedflagWord.isVisible()) {
			selectedflagWord.setVisible(false);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 1, false);
		}else {
			selectedflagWord.setVisible(true);
			PIE.updateCustomerServices(ApplicationUser.getApplicationUser().getPersonid(), 1, true);
		}
	}


	
	
}
