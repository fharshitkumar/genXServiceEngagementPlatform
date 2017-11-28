package utility;

import java.io.IOException;
import java.util.Optional;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ApplicationUtilities {

	//To keep a track of Window Close Action Status for any window
	public boolean windowclosestatus = false;
	
	public void close(ActionEvent event)
	{
	Alert alert =  new  Alert(AlertType.CONFIRMATION);
	alert.setTitle("CONFIRM");
	//alert.setHeaderText("This is to confirm your decision.");
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

	public Boolean Commit()
	{
	Alert alert =  new  Alert(AlertType.CONFIRMATION);
	alert.setTitle("CONFIRM");
	//alert.setHeaderText("This is to confirm your decision.");
	alert.setContentText("Do you really want to Commit the changes?");
	Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
	if(result.get()==javafx.scene.control.ButtonType.OK)
		return Boolean.TRUE;
	else
		return Boolean.FALSE;
	}
	
	
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
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	
}
