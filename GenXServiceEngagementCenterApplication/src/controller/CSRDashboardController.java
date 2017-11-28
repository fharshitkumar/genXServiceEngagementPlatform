package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import entities.Incident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utility.ApplicationUtilities;

public class CSRDashboardController implements Initializable{

	@FXML
	private AnchorPane CSRDashboard;
	
	@FXML
	private Pane incidentPane;
	
	@FXML
	JFXListView customerServicesList;
	
	ApplicationUtilities util;
	
	
	@FXML
	public void backCSR(ActionEvent event) {
		
		incidentPane.setVisible(false);
		CSRDashboard.setVisible(true);
		//personalinfopane.setVisible(false);
		//homepanel.setVisible(true);
	}
	
	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		util.close(event);
	}


	@FXML
	public void OpenIncidentStatus(ActionEvent event){

		incidentPane.setVisible(true);
		
	}

	@FXML
	private TableView<Incident> customerservicehistoryCSR;	
	
	
	
	public CSRDashboardController() {
		util = new ApplicationUtilities();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> items =FXCollections.observableArrayList (
				"Word",
				"Excel",
				"PowerPoint",
				"Outlook",
				"OneNote",
				"OneDrive",
				"Publisher",
				"Access",
				"PictureMgr",
				"SharePoint");
		customerServicesList.setItems(items);
		
	}

}
