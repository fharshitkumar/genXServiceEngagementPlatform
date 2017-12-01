package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import entities.Channel;
import entities.Incident;
import entities.KnowledgeBase;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.A_AnalyticsReportingEngine;
import models.A_DatabaseCommunicationEngine;
import models.A_IncidentManagementEngine;
import models.A_InteractionManagementEngine;
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
	private TableColumn<Incident,String> ichannel;
	@FXML
	private TableColumn<Incident,String> iservice;
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
	private TableColumn<KnowledgeBase,String> iserviceknowledgebase;
	
	@FXML
	private TableColumn<KnowledgeBase,String> inotesknowledgebase;
	
	@FXML
	private TableColumn<KnowledgeBase,Integer> isentimentidknowledgebase;
	
	
	
	/**************** Pie Chart for Incident status ********************/
	@FXML 
	private PieChart pieChart1; // Pie Chart to show current open incidents
	
	@FXML
	private Label totalCount1;
	
	/**************** Pie Chart for Employee target ********************/
	@FXML 
	private PieChart pieChart2; // Pie Chart to show current open incidents
	
	@FXML
	private Label totalCount2;
	
	
	
	
	
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
		if(util.isWindowclosestatus())
		util.back("/view/Login.fxml",event);
	}


	@FXML
	public void OpenIncidentStatus(ActionEvent event){
		
		

		/************************************************ANALYTICS*********************************************/
		/***************************Incident Status Pie Chart **********************************/
		int countHigh=0 , countLow=0 , countMedium = 0;
		A_DatabaseCommunicationEngine dce = new A_DatabaseCommunicationEngine();
		// Query to get open incident and their priorities
		String SQLquery = "SELECT PRIORITY FROM INCIDENT WHERE STATUS = 'OPEN'";
		ResultSet rs= null;
		try{
			rs= dce.getResultSet(SQLquery);
			while(rs.next()){
				if (rs.getString(1).equals("HIGH")){
					countHigh++;
				}else{
					if(rs.getString(1).equals("LOW")){
						countLow++;
					}else{
						countMedium++;
					}
				}
					
				
			}
		}
		catch(SQLException e){
			System.out.println("Failed to get open incidents.");
		}
		ObservableList<PieChart.Data> piechartdata= FXCollections.observableArrayList();
		// adding the counts to pie chart
		piechartdata.add(new PieChart.Data("HIGH", countHigh));
		piechartdata.add(new PieChart.Data("LOW", countLow));
		piechartdata.add(new PieChart.Data("MEDIUM", countMedium));
		pieChart1.setTitle("Open Incidents");
		pieChart1.setData(piechartdata);
		// add a mouse event to show the counts for each area
		for(final PieChart.Data data : pieChart1.getData()){
						data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>(){
							@Override
							public void handle(MouseEvent arg0) {
								// TODO Auto-generated method stub
								totalCount1.setText(data.getName()+ " : " + String.valueOf(data.getPieValue()));
							}
		});
						
			
		}	
		/***************************Incident Status Pie Chart **********************************/
	    int tcountHigh=0 , tcountLow=0 , tcountMedium = 0 , tremaining=0;
	    String currentemployeeid = String.valueOf(ApplicationUser.getApplicationUser().getPersonid());
	    // query to get the closed incident count by the current employee
		String SQLquery2 = "SELECT PRIORITY FROM INCIDENT WHERE STATUS = 'CLOSED' AND PROCESSORID =" + currentemployeeid;
		rs= null;
		try{
			rs= dce.getResultSet(SQLquery2);
			while(rs.next()){
				if (rs.getString(1).equals("HIGH")){
					tcountHigh++;
				}else{
					if(rs.getString(1).equals("LOW")){
						tcountLow++;
					}else{
						tcountMedium++;
					}
				}
					
			}
		}
		catch(SQLException e){
			System.out.println("Failed to get open incidents.");
		}
		tremaining = 100 - (tcountHigh + tcountLow + tcountMedium);
		ObservableList<PieChart.Data> piechartdata2 = FXCollections.observableArrayList();
		// Adding the count data to the pie chart
		piechartdata2.add(new PieChart.Data("HIGH", tcountHigh));
		piechartdata2.add(new PieChart.Data("LOW", tcountLow));
		piechartdata2.add(new PieChart.Data("MEDIUM", tcountMedium));
		piechartdata2.add(new PieChart.Data("REMAINING", tremaining));
		pieChart2.setTitle("Traget Status");
		pieChart2.setData(piechartdata2);
		// adding a mouse event to show the count for each area	
					for(final PieChart.Data data : pieChart2.getData()){
				data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent arg0) {
						// TODO Auto-generated method stub
						totalCount2.setText(data.getName()+ " : " + String.valueOf(data.getPieValue()));
					}
				});
}
		

	
		
		/******************************GENERATE REPORTS********************************************/
		
		A_AnalyticsReportingEngine ARE = new A_AnalyticsReportingEngine();
		A_InteractionManagementEngine.FacebookManager();
		List<String> Analytics = new ArrayList<>();
		Analytics.add("RETRIEVE EMPLOYEE BASED ON SPECIALIZATION");
		Analytics.add("SPECIALIZATION REQUIRED TO SOLVE A PARTICULAR PROBLEM");
		Analytics.add("UNASSIGNED TICKETS");
		Analytics.add("AGENT WORKLOAD");
		Analytics.add("TICKETS BASED ON PRIORITY");
		Analytics.add("AGENT WORKLOAD");
		Analytics.add("UNASSIGNED TICKETS");
		Analytics.add("AGENT WORKLOAD");
		
		ARE.genenerateReport1();
		ARE.genenerateReport2();
		ARE.genenerateReport3();
		ARE.genenerateReport4();
		ARE.genenerateReport5();
		ARE.genenerateReport6();
		ARE.genenerateReport7();
		ARE.genenerateReport8();
		incidentPane.setVisible(true);
		
	}

	@FXML
	public void RefreshServiceHistory(ActionEvent event) {
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		A_InteractionManagementEngine.FacebookManager();	
		ObservableList<Incident> incidentdata = IME.displayTickets(0,true);
		iincidentQueue.setItems(incidentdata);
		firstname.setText("");
		lastname.setText("");
		phone.setText("");
		email.setText("");
		CustomerType.setText("");
   		customerServicesList.setItems(null);
   		knowledgebasetable.setItems(null);
   		customerservicehistoryCSR.setItems(null);
   		
	}

	private String getChannelName(Integer channelid)
	{
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		Channel Channelname = IME.displayChannel(channelid);
		return Channelname.getChannelname();
	}
	
	private String getServiceName(Integer serviceid)
	{
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		return IME.getServices(serviceid);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		
		/**************************Bind the Data Type to the javaFX Table **************************/
		iincidentid.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getIncidentid()));

		ichannel.setCellValueFactory( c ->
		new ReadOnlyStringWrapper(getChannelName(c.getValue().getChannelid())));
		
		iservice.setCellValueFactory( c ->
		new ReadOnlyStringWrapper(getServiceName(c.getValue().getServiceid())));
		
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
		ObservableList<Incident> incidentdata = IME.displayTickets(0,true);
		iincidentQueue.setItems(incidentdata);
		

		System.out.println("Welcome CSR : "+ApplicationUser.getApplicationUser().getPersonid());
				
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
    	Incident selectedincident = null;
    	selectedincident = iincidentQueue.getSelectionModel().getSelectedItem();
    	V_ViewManagementEngine VME = new V_ViewManagementEngine();
    	ObservableList<CustomerInfoView> customerinfoview = null;
    	customerinfoview = VME.customerinfoviewer(selectedincident.getCustomerid());
    
    	knowledgebasetable.setItems(null);
		String selectedcustomerincident= null;
		selectedcustomerincident = selectedincident.getProblem();

		/**************************Bind the Data Type to the javaFX Table **************************/

		A_KnowledgeHubEngine KHE = new A_KnowledgeHubEngine();
		
		iserviceknowledgebase.setCellValueFactory( c ->
		new ReadOnlyStringWrapper(getServiceName(c.getValue().getServiceid())));
		
		ishorttextknowledgebase.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getShorttext())));
		
		isolutionknowledgebase.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getSolution())));

		inotesknowledgebase.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getNotes())));

		isentimentidknowledgebase.setCellValueFactory( c ->
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getSentiment()));
		
		
		/**************************Bind the Ticket Data to the javaFX Table **************************/	
		ObservableList<KnowledgeBase> knowledgebaseincidentdata = KHE.displayKnowledgeHub(selectedcustomerincident);
		knowledgebasetable.setItems(knowledgebaseincidentdata);

    	
    	
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
