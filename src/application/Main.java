package application;
	
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	Button button;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage)throws Exception{
		primaryStage.setTitle("Tournament");
		button = new Button();
		button.setText("Submit");
		
		StackPane bracket_layout = new StackPane();
		bracket_layout.getChildren().add(button);
		
		Scene scene = new Scene(bracket_layout,1200,700);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
