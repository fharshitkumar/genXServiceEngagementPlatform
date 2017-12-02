package utility;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class		: ApplicationUtilities		
 * Description	: Allows the application components to perform generic Application tasks. 
 *
 *
 *	.....................................................................
 *	....+-----------------------------------------------------------+....						
 *	....|            ApplicationUtilities Class                     |....						
 *	....|             << concrete class >>                          |....	                    
 *	....+-----------------------------------------------------------+....						
 *	....| [-] windowclosestatus           : boolean                 |....						
 *	....| [-] customerid       : int                                |....						
 *	....| [-] shorttext        : string                             |....						
 *	....+-----------------------------------------------------------+....						
 *	....| [+] PastIncidentView()                                    |....						
 *	....| [+] void: setstatus(int)                                  |....						
 *	....| [+] void: setcustomerid(int)                              |....						
 *	....| [+] void: setshorttext(int)                               |....						
 *	....| [+] int:  getstatus()                                     |....						
 *	....| [+] int:  getcustomerid()                                 |....						
 *	....| [+] int:  getshorttext()                                  |....						
 *	....+-----------------------------------------------------------+....						
 *	.....................................................................																				
 * 																								
 * @author ISHAN KAMAT 			
 * Date			    : 28 November, 2017
 * Source File name	: ApplicationUtilities.java       	
 * 
 *
*/

public class ApplicationUtilities {

	//To keep a track of Window Close Action Status for any window
	private boolean windowclosestatus = false;

	/** 
	 * ***************************************
	 * GETTER: To get the window closed status
	 * ***************************************
	 * @return the windowclosestatus
	 */
	public boolean isWindowclosestatus() {
		return windowclosestatus;
	}

	/** 
	 * ******************************************
	 * SETTER: To set the window closed status
	 * ******************************************
	 * @param windowclosestatus the windowclosestatus to set
	 */
	public void setWindowclosestatus(boolean windowclosestatus) {
		this.windowclosestatus = windowclosestatus;
	}


	/**
	 * **********************************************************************************************
	 * This function is used to close the application screen and exit the current application control.
	 * **********************************************************************************************
	 * @param event
	 */
	public void close(ActionEvent event)
	{
		Alert alert =  new  Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRM");
		alert.setContentText("Do you really want to exit the application?");
		Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
		if(result.get()==javafx.scene.control.ButtonType.OK)
		{
			windowclosestatus=true;
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		}
		else
		{
			windowclosestatus=false;
		}
	}

	/**
	 * **************************************************************************************************
	 * This function is used to confirm the action taken from the user if any CRUD operation is performed
	 * ************************************************************************************************** 
	 * @return Boolean
	 */
	public Boolean Commit()
	{
		Alert alert =  new  Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRM");
		alert.setContentText("Do you really want to Commit the changes?");
		Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
		if(result.get()==javafx.scene.control.ButtonType.OK)
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}


	/**
	 * ***************************************************************************************************************
	 * This function is used to navigate back to the previous page from where the application control has been called.
	 * ***************************************************************************************************************
	 * @param pagePath
	 * @param event
	 */
	public void back(String pagePath , ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(pagePath));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch (IOException e) {
			System.out.println("Unable to navigate back. More information");
			System.out.println(e.getMessage());
		}
	}

}
