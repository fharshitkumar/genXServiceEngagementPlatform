package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Image anotherIcon = new Image("http://mypages.iit.edu/~segre/iit-icon.png");
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.getIcons().add(anotherIcon);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Welcome to GenX Service Engagement system");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
