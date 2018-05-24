package application;

import controller.GameplayController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
* The simple game. Player cans move, interact with items (chests, it allows to collect money)
* and fight in turn based combat system.
* Game uses JavaFX.
*
* @author  Hubert Ziarek
* @version 1.0
* @since   2018-05-24
*/

public class Main extends Application {
	
	private GameplayController mainController;

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
		
		mainController = new GameplayController(primaryStage);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		
		mainController.start();
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}