package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;

import entities.PersonalInfo;
import entities.WorkSchedule;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import models.A_DatabaseCommunicationEngine;
import models.A_IncidentManagementEngine;
import models.A_PersonInformationEngine;
import models.ApplicationUser;
import tableviews.ManagerView;
import utility.ApplicationUtilities;
import java.sql.Timestamp;

/**
 * This is a controller for Manager View which  shows the different panels with open Incidents and 
 * Work schedule table. also the bar chart to show the count of incident from each service area and a 
 * pie chart to show the percent area for each priority incidents that are open.   
 * @author PRAGYA SHUKLA
 *
 */

public class ManagerDashboardController implements Initializable{
	
	
	/*********************************************************************************************************
	 * Declaration of welcome labels
	 *********************************************************************************************************
	 */	

	
	@FXML
	private Label managerName;
	
	/*********************************************************************************************************
	 * Declaration of table variables to show the open incidents on manager screen
	 *********************************************************************************************************
	 */	
	@FXML
	private TableView<ManagerView> tableIncident;
	
	@FXML
	private TableColumn<ManagerView,Integer> mincidentid;
	
	@FXML
	private TableColumn<ManagerView,String> mservicename;
	
	@FXML
	private TableColumn<ManagerView,String> mproblem;
	
	@FXML
	private TableColumn<ManagerView,String> mpriority;
	
	@FXML
	private TableColumn<ManagerView,String> mcreationdate;
	
	@FXML
	private TableColumn<ManagerView,Integer> mprocessor;

	@FXML
	private TableColumn<ManagerView,String> mstatus;
	
	/*********************************************************************************************************
	 * Search combo box to search incidents on the basis of service and priority
	 *********************************************************************************************************
	 */	
	@FXML
	JFXComboBox<String> msearchservice;
	
	@FXML
	JFXComboBox<String> msearchpriority;
	
	/*********************************************************************************************************
	 * Declaration of table variables to show employee work schedule
	 *********************************************************************************************************
	 */	
	
	@FXML
	private TableView<WorkSchedule> workscheduletable;
	
	@FXML
	private TableColumn<WorkSchedule,Integer> mworkpackageid;
	
	@FXML
	private TableColumn<WorkSchedule,Integer> memployeeID;
	
	@FXML
	private TableColumn<WorkSchedule,String> mintime;
	
	@FXML
	private TableColumn<WorkSchedule,String> mouttime;
	
	/*********************************************************************************************************
	 * Declaration of Pie Chart variable to show open Incident status wise
	 *********************************************************************************************************
	 */	
	
	@FXML 
	private PieChart piechart; // Pie Chart to show current open incidents
	
	@FXML
	private Label totalCount;
	
	/*********************************************************************************************************
	 * Declaration of Bar chart variable to show incident count for all services that are currently open
	 *********************************************************************************************************
	 */	
	@FXML
	private BarChart<String, Number> barchart;
	
	@FXML
	Pane workSchedulePanel;
	
	@FXML
	Pane incidentViewPanel;
	
	/*********************************************************************************************************
	 *****This is the helper utility to perform various UI level common tasks like go back, close window, etc.
	 *********************************************************************************************************
	 */
	ApplicationUtilities util;
	
	
	/*********************************************************************************************************
	 * This is will initialize the helper application UI utility.
	 *********************************************************************************************************
	 */
	public ManagerDashboardController() {
		util = new ApplicationUtilities();
		
	}

	/*********************************************************************************************************
	 * function to initialize the panel when manager screen opens
	 *********************************************************************************************************
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Welcome to Manager screen");
		
		A_PersonInformationEngine PIE = new A_PersonInformationEngine();
		PersonalInfo PI = PIE.displayPersonalInfo(ApplicationUser.getApplicationUser().getPersonid());
		managerName.setText(PI.getFname());
		
		// setting property type for each column
		mincidentid.setCellValueFactory(c -> 
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getIncidentid()));
		mcreationdate.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getCreatedon())));
		mservicename.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getServicename())));
		mproblem.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getProblem())));
		mpriority.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getPriority())));
		mprocessor.setCellValueFactory(c -> 
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getProcessorid()));
		
		//Set the table contents to Login user administration tasks.
		tableIncident.setItems(getData());
		
		//Update the table to allow for the changes in username and password by admin
		tableIncident.setEditable(true);
		mpriority.setCellFactory(TextFieldTableCell.forTableColumn()); 
		mprocessor.setCellFactory(TextFieldTableCell.<ManagerView,Integer>forTableColumn(new IntegerStringConverter()));	
		
		getServices();
        getPriority();
        drawGraphs();
		
		
		
		// getting data for workschedule table
		mworkpackageid.setCellValueFactory(c -> 
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getWorkpackageid()));
		memployeeID.setCellValueFactory(c -> 
		new ReadOnlyObjectWrapper<Integer>(c.getValue().getEmployeeid()));
		mintime.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getIntime())));
		mouttime.setCellValueFactory(c -> 
		new ReadOnlyStringWrapper( String.valueOf( c.getValue().getOuttime())));
			
		//Set the table contents to Login user administration tasks.
		workscheduletable.setItems(workScheduleGetData());
	}
	
	/*********************************************************************************************************
	 * function to select a incidents for manager_view and return the incident details
	 *********************************************************************************************************
	 */	
	private ObservableList<ManagerView> getData(){
		/*********************get table data*****************************************/
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<ManagerView> incidentdata = FXCollections.observableArrayList();
		String SQLQuery = "SELECT * FROM MANAGER_VIEW WHERE STATUS = 'OPEN'";
		ResultSet resultSet = null;
		ResultSet rs = null;
		// executing the query
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;
			Integer incidentid;
			String servicename;
			String problem;
			String priority;
			Timestamp creationdate;
			Integer processor;
			String status;
			// iterating the result set
			while(rs.next())
			{
				incidentid = rs.getInt(1);
				status = (String)rs.getString(7);
				processor = rs.getInt(6);
				creationdate = rs.getTimestamp(5);
				priority = (String)rs.getString(4);
				problem = (String)rs.getString(3);
				servicename= (String)rs.getString(2); 
								
				// binding data to observable list
				incidentdata.add(new ManagerView(
						incidentid,
						processor,
						status,
						servicename,
						problem,
						priority,
						creationdate
						));
			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Manager View Data " + e.getMessage());
		}	

		return incidentdata;
	}

	
	
	private void drawGraphs() {
		// Getting data for pie chart
				int countHigh=0 , countLow=0 , countMedium = 0;
				A_DatabaseCommunicationEngine dce = new A_DatabaseCommunicationEngine();
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
					rs=null;
				}
				catch(SQLException e){
					System.out.println("Failed to get open incidents.");
				}
				System.out.println("Count High: " + countHigh);
				System.out.println("Count Low: " + countLow);
				System.out.println("Count Medium: " + countMedium);
				ObservableList<PieChart.Data> piechartdata= FXCollections.observableArrayList();
				piechartdata.add(new PieChart.Data("HIGH", countHigh));
				piechartdata.add(new PieChart.Data("LOW", countLow));
				piechartdata.add(new PieChart.Data("MEDIUM", countMedium));
				// binding records to pie chart
				piechart.setData(piechartdata);
				// adding mouse move event to show count for  each area in pie chart
				for(final PieChart.Data data : piechart.getData()){
					data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent arg0) {
							totalCount.setText(data.getName()+ " : " + String.valueOf(data.getPieValue()));
						}
					});
				}

				// getting data for bar chart
				// creating x axis
				CategoryAxis xAxis = new CategoryAxis();
				xAxis.setLabel("Services");
				// creating Y axis
				NumberAxis yAxis = new NumberAxis();
			    yAxis.setLabel("Incident Count");
			    // Create a BarChart
			    barchart.setTitle("Service Open Incident Count");
			    XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
			    String sqlQuery1 = "SELECT SERVICENAME, COUNT(*) FROM MANAGER_VIEW WHERE STATUS= 'OPEN' GROUP BY SERVICENAME";
			    rs= null;
			    String servicename;
			    int servicecount;
				try{
					rs= dce.getResultSet(sqlQuery1);
					while(rs.next()){
						servicename= (String)rs.getString(1);
						servicecount = rs.getInt(2);
		    			dataSeries.getData().add(new Data<String, Number>(servicename, servicecount));
					}
					rs.close();
				} catch (SQLException e) {
					System.out.println("Failed to get Manager View Data " + e.getMessage());
				}	
				dataSeries.setName("Services");
				barchart.getData().add(dataSeries);
	}
	
	
	
	
	
	
	/*********************************************************************************************************
	 * function to select work schedule data from workshedule table and return the work schedule details
	 *********************************************************************************************************
	 */
	private ObservableList<WorkSchedule> workScheduleGetData(){
		/*********************get table data*****************************************/
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<WorkSchedule> workscheduledata = FXCollections.observableArrayList();
		// query to select work schedule
		String currentmanager = String.valueOf(ApplicationUser.getApplicationUser().getPersonid());
		String SQLQuery = "SELECT * FROM WORKSCHEDULE WHERE MANAGERID = " + currentmanager;
		ResultSet resultSet = null;
		ResultSet rs = null;
		// executing the query
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;
			Integer workscheduleid;
			Integer employeeid;
			Timestamp intime;
			Timestamp outtime;
			Integer managerid;
			// iterating result set
			while(rs.next())
			{
				workscheduleid = rs.getInt(1);
				employeeid = rs.getInt(2);
				intime = rs.getTimestamp(3);
				outtime = rs.getTimestamp(4);
				managerid = rs.getInt(5); 
								
				// binding data to observable list
				workscheduledata.add(new WorkSchedule(
						workscheduleid,
						employeeid,
						intime,
						outtime,
						managerid
						));
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("Failed to get Manager View Data " + e.getMessage());
		}	

		return workscheduledata;
	}
	
	/*****************************************************************************************************
	 * This function will allow the manager to change the priority
	 ***************************************************************************************************** 
	 * */
	public void changeIncidentPriority(CellEditEvent<?, ?> edittedCell)
	{
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		ManagerView incidentselected = tableIncident.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			incidentselected.setPriority(edittedCell.getNewValue().toString());
			IME.CommitChanges("INCIDENT","PRIORITY", incidentselected.getPriority(), incidentselected.getIncidentid());
		}else{
			tableIncident.setItems(getData());
		}
	}

	/*****************************************************************************************************
	 * This function will allow the manager to change the Incident processor
	 ***************************************************************************************************** 
	 * */
	public void changeIncidentProcessor(CellEditEvent<?, ?> edittedCell)
	{
		A_IncidentManagementEngine IME = new A_IncidentManagementEngine();
		ManagerView incidentselected = tableIncident.getSelectionModel().getSelectedItem();
		if(util.Commit()){
			incidentselected.setPriority(edittedCell.getNewValue().toString());
			incidentselected.setProcessorid(Integer.valueOf(edittedCell.getNewValue().toString()));
			IME.CommitChanges("INCIDENT","PROCESSORID", incidentselected.getProcessorid(), incidentselected.getIncidentid());
		}else{
			tableIncident.setItems(getData());
		}
	}

	/**********************************************************************************************
	 * This function is responsible to help the manager search Incident on the basis of Service
	 * and Priority
	 **********************************************************************************************
	 * @param event
	 */
	@FXML
	public void search(ActionEvent event) {
		String service = (msearchservice.getSelectionModel().getSelectedItem());
		String priority = (msearchpriority.getSelectionModel().getSelectedItem());
		
		ObservableList<ManagerView> table = FXCollections.observableArrayList();
		ObservableList<ManagerView> searchresult = FXCollections.observableArrayList();
		table= getData();
		for(ManagerView incident : table) {
			if (service!= null && priority!= null){
				if (incident.getServicename().equals(service) && incident.getPriority().equals(priority))
					searchresult.add(incident);	
			}
			if(service== null && incident.getPriority().equals(priority))
				searchresult.add(incident);
			if(priority== null && incident.getServicename().equals(service))
				searchresult.add(incident);
			}
		if(searchresult.isEmpty())
			tableIncident.setItems(table);
		else
			tableIncident.setItems(searchresult);		

	}

	/******************************************************************************************************
	 *  To close the Manager window and return to Login window.
	 * ****************************************************************************************************
	 * @param event
	 */
	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
		util.close(event);
		if(util.isWindowclosestatus())
		util.back("/view/Login.fxml",event);
	}
		
	/******************************************************************************************************
	 *  To open the Admin Home panel. 
	 * ****************************************************************************************************
	 * @param event
	 */
	@FXML
	public void OpenIncidentViewPanel(ActionEvent event) {
		System.out.println("You clicked Admin Home button");
		workSchedulePanel.setVisible(false);
		incidentViewPanel.setVisible(true);		
	}
	
	/******************************************************************************************************
	 *  To open the WorkSchedule Panel
	 * ****************************************************************************************************
	 * @param event
	 */
	@FXML
	public void OpenWorkSchedulePanel(ActionEvent event) {
		System.out.println("You clicked WorkSchedule Button");
		workSchedulePanel.setVisible(true);
		incidentViewPanel.setVisible(false);
	}
	
	/******************************************************************************************************
	 *  To refresh the Incident List
	 * ****************************************************************************************************
	 * @param event
	 */
	@FXML
	public void incidentRefresh(ActionEvent event) {
		System.out.println("You clicked Refresh Button");
		// setting property type for each column
		mincidentid.setCellValueFactory(c -> 
				new ReadOnlyObjectWrapper<Integer>(c.getValue().getIncidentid()));
		mcreationdate.setCellValueFactory(c -> 
				new ReadOnlyStringWrapper( String.valueOf( c.getValue().getCreatedon())));
		mservicename.setCellValueFactory(c -> 
				new ReadOnlyStringWrapper( String.valueOf( c.getValue().getServicename())));
		mproblem.setCellValueFactory(c -> 
				new ReadOnlyStringWrapper( String.valueOf( c.getValue().getProblem())));
		mpriority.setCellValueFactory(c -> 
				new ReadOnlyStringWrapper( String.valueOf( c.getValue().getPriority())));
		mprocessor.setCellValueFactory(c -> 
				new ReadOnlyObjectWrapper<Integer>(c.getValue().getProcessorid()));
		msearchpriority.setItems(null);
		msearchservice.setItems(null);
		getServices();
        getPriority();

		//Set the table contents to Login user administration tasks.
		tableIncident.setItems(getData());	
	}

	private void getServices() {
		//Get the service list for search
		List<String> totalservices = new ArrayList<>();
		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		String SQLQuery = "SELECT * FROM SERVICE";
		ResultSet resultSet = null;
		try {
			resultSet = DCE.getResultSet(SQLQuery);
			ResultSet rs = resultSet;
			while(rs.next())
				totalservices.add(rs.getString(2));
			msearchservice.setItems(FXCollections.observableArrayList(totalservices));
			rs.close();
		} catch (SQLException e) {
			System.out.println("Failed to get Services");
		}
	}
	
	
	private void getPriority() {
		//Get the service list for search
				List<String> prioritylist = new ArrayList<>();
				prioritylist.add("HIGH");
				prioritylist.add("MEDIUM");
				prioritylist.add("LOW");
				msearchpriority.setItems(FXCollections.observableArrayList(prioritylist));
	}
}
