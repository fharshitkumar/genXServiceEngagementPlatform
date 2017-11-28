package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import entities.Incident;
import entities.KnowledgeBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.A_IncidentManagementEngine;
import models.A_KnowledgeHubEngine;
import models.ApplicationUser;
import models.V_ViewManagementEngine;
import tableviews.CustomerInfoView;
import tableviews.PastIncidentView;
import utility.ApplicationUtilities;

public class CSRDashboardController implements Initializable{

	@FXML
	private AnchorPane CSRDashboard;
	
	@FXML
	private Pane incidentPane;
	
	@FXML
	JFXListView<String> customerServicesList;
	
	
	//#-----------------------INCIDENT---------------------------#//
	@FXML
	TableView<Incident> iincidentQueue;
	
	@FXML
	private TableColumn<Incident,Integer> iincidentid;
	@FXML
	private TableColumn<Incident,Integer> ichannel;
	@FXML
	private TableColumn<Incident,Integer> iservice;
	@FXML
	private TableColumn<Incident,String> ishorttext;
	@FXML
	private TableColumn<Incident,String> iproblem;
	@FXML
	private TableColumn<Incident,String> ipriority;
	@FXML
	private TableColumn<Incident,String> icreatedon;
	@FXML
	private TableColumn<Incident,String> itag;
	@FXML
	private TableColumn<Incident,String> inote;
	
	
	//#-----------------------KNOWLEDGE HUB---------------------------#//
	@FXML
	TableView<KnowledgeBase> knowledgebasetable;
	
	
	
	@FXML
	private TableColumn<KnowledgeBase,String> ishorttextknowledgebase;
	
	@FXML
	private TableColumn<KnowledgeBase,String> isolutionknowledgebase;
	
	@FXML
	private TableColumn<KnowledgeBase,Integer> iserviceknowledgebase;
	
	@FXML
	private TableColumn<KnowledgeBase,String> inotesknowledgebase;
	
	@FXML
	private TableColumn<KnowledgeBase,Integer> isentimentidknowledgebase;
	
	
	
	
	
	
	
	
	
	ApplicationUtilities util;
	
	public CSRDashboardController() {
		util = new ApplicationUtilities();
	}
	
	
	
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
		if(util.windowclosestatus)
		util.back("/view/Login.fxml",event);
	}


	@FXML
	public void OpenIncidentStatus(ActionEvent event){

		incidentPane.setVisible(true);
		
	}

	@FXML
	public void RefreshServiceHistory(ActionEvent event) {
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
			
		ObservableList<Incident> incidentdata = IME.displayTickets();
		iincidentQueue.setItems(incidentdata);
		firstname.setText("");
		lastname.setText("");
		phone.setText("");
		email.setText("");
		CustomerType.setText("");
   		customerServicesList.setItems(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		
		/**************************Bind the Data Type to the javaFX Table **************************/
		iincidentid.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getIncidentid()));

		ichannel.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getChannelid()));
		
		iservice.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getServiceid()));
		
		ishorttext.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getShorttext())));
		
		iproblem.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getProblem())));
		
		ipriority.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getPriority())));
		
		icreatedon.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getCreatedon())));
		
		itag.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getTag())));
		
		inote.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getNotes())));
		
		/**************************Bind the Ticket Data to the javaFX Table **************************/	
		ObservableList<Incident> incidentdata = IME.displayTickets();
		iincidentQueue.setItems(incidentdata);
		
		
		
		
		



		

		/**************************Bind the Data Type to the javaFX Table **************************/

		A_KnowledgeHubEngine KHE = new A_KnowledgeHubEngine();
		
		iserviceknowledgebase.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getServiceid()));
		
		ishorttextknowledgebase.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getShorttext())));
		
		isolutionknowledgebase.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getSolution())));

		inotesknowledgebase.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getNotes())));

		isentimentidknowledgebase.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getSentiment()));
		
		
		/**************************Bind the Ticket Data to the javaFX Table **************************/	
		ObservableList<KnowledgeBase> knowledgebaseincidentdata = KHE.displayKnowledgeHub();
		knowledgebasetable.setItems(knowledgebaseincidentdata);

		System.out.println("Welcome CSR : "+ApplicationUser.applicationUser.getPersonid());
		
		
				
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

	
	@FXML
	private Label firstname; 
	
	@FXML
	private Label lastname; 
	
	@FXML
	private Label phone; 
	
	@FXML
	private Label email; 
	
	
	@FXML
	private Label CustomerType; 
	
	@FXML
	private ImageView customertypeimage;
	
    @FXML
    void TableAccessLevel(MouseEvent event) {
    	Incident selectedincident = iincidentQueue.getSelectionModel().getSelectedItem();
    	V_ViewManagementEngine VME = new V_ViewManagementEngine();
    	ObservableList<CustomerInfoView> customerinfoview = VME.customerinfoviewer(selectedincident.getCustomerid());
    	ObservableList<String> items =FXCollections.observableArrayList ();
    	try {
    	if(customerinfoview!=null)
    	{
    		System.out.println("The customer name is : "+ customerinfoview.get(0).getFname());

    		firstname.setText(customerinfoview.get(0).getFname());
    		lastname.setText(customerinfoview.get(0).getLname());
    		phone.setText(customerinfoview.get(0).getContact());
    		email.setText(customerinfoview.get(0).getEmail());
    		CustomerType.setText(customerinfoview.get(0).getRolename());
    		
    		for(CustomerInfoView c : customerinfoview)
    		{
    			items.add(c.getServicename());
    		}
    		customerServicesList.setItems(items);
    	}
    	else
    	{
    		firstname.setText("");
    		lastname.setText("");
    		phone.setText("");
    		email.setText("");
    		CustomerType.setText("");
    		customerServicesList.setItems(null);
    	}
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	//------------------------------GET THE PAST TICKET HISTORY FOR A PARTICULAR CUSTOMER-----------------// 
    	
    	
    	ObservableList<PastIncidentView> pastincidentinfoview = VME.pastincidentviewer(selectedincident.getCustomerid());
    
    	ishorttextpastticket.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getShorttext())));
		
    	istatuspastticket.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getStatus())));
    	
    	customerservicehistoryCSR.setItems(pastincidentinfoview);
    	
    }

    @FXML
	private TableView<PastIncidentView> customerservicehistoryCSR;	
	
    @FXML
	private TableColumn<PastIncidentView,String> ishorttextpastticket;
	
	@FXML
	private TableColumn<PastIncidentView,String> istatuspastticket;

	
	
	
}
