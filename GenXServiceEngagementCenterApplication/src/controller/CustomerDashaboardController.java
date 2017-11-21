package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
	public void OpenFacebookPanel(ActionEvent event) {
		System.out.println("You clicked Facebook button");
		facebookpanel.setVisible(true);
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
	private JFXButton	serviceWord;
	@FXML 
	private JFXButton serviceExcel;
	@FXML 
	private JFXButton servicePowerPoint;
	@FXML 
	private JFXButton serviceOutlook;
	@FXML 
	private JFXButton serviceOneNote;
	@FXML 
	private JFXButton serviceOneDrive;
	@FXML 
	private JFXButton servicePublisher;
	@FXML 
	private JFXButton serviceAccess;
	@FXML 
	private JFXButton servicePictureMgr;
	@FXML 
	private JFXButton serviceSharePoint;
	@FXML 
	private JFXButton serviceSkype;
	@FXML 
	private JFXButton serviceExchange;
	@FXML 
	private JFXButton serviceYammer;
	@FXML 
	private JFXButton serviceSway;
	@FXML 
	private JFXButton servicePowerBI;
	@FXML 
	private JFXButton serviceVisio;
	@FXML 
	private JFXButton serviceProject;

	@FXML
	public void serviceselected(ActionEvent event) {
		Button service = new Button();
		service = (Button)event.getSource();
		System.out.println("You pressed : "+ service.getId());
		
	}
	
	ApplicationUtilities util;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		util = new ApplicationUtilities();	
	}
}
