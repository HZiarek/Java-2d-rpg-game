package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.paint.Color;


public class Main extends Application {
	
	Controller mainController;
	Group root;

	@Override
	public void start(Stage primaryStage) {
		
		/*
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/

		root = new Group();
		Scene scene = new Scene(root, 1800, 1000, Color.FORESTGREEN);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		mainController = new Controller(root, scene);
		//mainController.createLevel();
		mainController.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
