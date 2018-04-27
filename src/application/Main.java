package application;

import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;  

/**
 * the main class
 *
 */
public class Main extends Application {

	private static Game game = null;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Invalid command-line argument!");
			return;
		}
		//
		game = new Game(FileManager.loadChallenger(args[0]));
		//
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Tournament Bracket");
		// GridPane - Form
		GridPane gPane = new GridPane();
		Scene scene = new Scene(gPane, 1400, 800, Color.DARKGRAY);
		
		Label nameLabel = new Label();
		nameLabel.setAlignment(Pos.CENTER);
		nameLabel.setMinHeight(25);
		nameLabel.setText("Name: ");
		
		TextField usernameInput = new TextField();
		usernameInput.setMaxHeight(20); 
		usernameInput.setMaxWidth(200);
		usernameInput.setPromptText("Input Team Name");
		usernameInput.setFocusTraversable(false);
		

		Label scoreLabel = new Label();
		scoreLabel.setAlignment(Pos.CENTER);
		scoreLabel.setMinHeight(25);
		scoreLabel.setText("Score: ");
		
		TextField scoreInput = new TextField();
		scoreInput.setMaxHeight(20); 
		scoreInput.setMaxWidth(200);
		scoreInput.setPromptText("Input Team Score");
		scoreInput.setFocusTraversable(false);

		int width = (int)(Math.log(game.getTeamNumber()) / Math.log(2)) * 2 * 150 + 100;
		int height = game.getTeamNumber() * 50 + 100;
		Canvas canvas = new Canvas(width , height);  
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(canvas);
		
		Button submitButton = new Button();
		submitButton.setText("Submit Score");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					String result = game.addScore(usernameInput.getText(), 
							Integer.valueOf(scoreInput.getText()));	
					if (result != null) {
						createAlertWarning(result);						
					} else {
						usernameInput.setText("");
						scoreInput.setText("");
					}
				} catch (Exception e) {
					createAlertWarning("Invalid Team Score!");
				}
				game.draw(canvas.getGraphicsContext2D());
			}
		});
		gPane.add(nameLabel, 0, 0);
		gPane.add(usernameInput, 1, 0);
		gPane.add(scoreLabel, 2, 0);
		gPane.add(scoreInput, 3, 0);
		gPane.add(submitButton, 4, 0, 2, 1);
		gPane.add(scrollPane, 0, 1, 10, 10);
		GridPane.setHalignment(submitButton, HPos.CENTER);
		GridPane.setHalignment(canvas, HPos.CENTER);
		primaryStage.setScene(scene);
		primaryStage.show();
		game.draw(canvas.getGraphicsContext2D());
	}
	
	/**
	 * message alert
	 * @param msg
	 */
	private void createAlertWarning(String msg) {
		Alert alert = new Alert(Alert.AlertType.WARNING, msg);
		alert.showAndWait()
		.filter(response -> response == ButtonType.OK);
	}


}
