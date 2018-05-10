package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * Graphic paths in every main controller (ui, environment, character, opponents)
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
