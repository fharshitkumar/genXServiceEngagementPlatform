package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utility.ApplicationUtilities;

public class CustomerDashaboardController implements Initializable {

	@FXML
	public void OpenCustomerHomePanel(ActionEvent event) {
		System.out.println("You clicked Home button");
	}
	
	@FXML
	public void OpenFacebookPanel(ActionEvent event) {
		System.out.println("You clicked Facebook button");
	}
	
	@FXML
	public void OpenTwitterPanel(ActionEvent event) {
		System.out.println("You clicked Twitter button");
	}
	
	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		util.close(event);
	}
	
	ApplicationUtilities util;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		util = new ApplicationUtilities();
	}

	
}
