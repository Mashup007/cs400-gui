package application;

import java.nio.file.Path;
import java.util.Observable;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
    //To make window dimensions more accessible for future use
	public int win_width = 1200;
	public int win_height = 600;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage)throws Exception{
		primaryStage.setTitle("Tournament");
		
		//Creating submit button
		Button button = new Button();
		button.setText("Submit");
		
		//Creating the general layout
		BorderPane bracket_border = new BorderPane();
		
		//Set up the submit botton
		HBox hbox_bottom = new HBox();
		hbox_bottom.getChildren().add(button);
		hbox_bottom.setAlignment(Pos.CENTER);
		hbox_bottom.setPadding(new Insets(30));
		bracket_border.setBottom(hbox_bottom);
		
		//Set up the challengers 
		
		
		//Set up the scene(framework)
		Scene scene = new Scene(bracket_border, win_width, win_height);
		primaryStage.setScene(scene);
		primaryStage.show();
<<<<<<< HEAD
	

=======
		
		
		
		//Creating bracket
		Path bracket = new Path();
		
		//Setting starting point of path
		MoveTo start = new MoveTo();
		start.setX(1050);
		start.setY(300);
		
		//Creating winner line
		HLineTo winner = new HLineTo();
		winner.setX(1150);
		
		bracket.getElements().add(start);
        bracket.getElements().add(winner);
        
		//Creating semifinals
		MoveTo move = new MoveTo();
		move.setX(1050);
		move.setY(400);
		
		VLineTo vline = new VLineTo();
		vline.setY(200);
		
		bracket.getElements().add(move);
		bracket.getElements().add(vline);
		
		HLineTo hline = new HLineTo();
		hline.setX(950);
		
		bracket.getElements().add(hline);
		bracket.getElements().add(move);
		bracket.getElements().add(hline);
		
		move.setY(475);
		vline.setY(325);
		
		bracket.getElements().add(move);
		bracket.getElements().add(vline);
		
		move.setY(275);
		vline.setY(125);
		
		bracket.getElements().add(move);
        bracket.getElements().add(vline);
        
        hline.setX(850);
        
        bracket.getElements().add(hline);
        
        move.setY(275);
        
        bracket.getElements().add(move);
        bracket.getElements().add(hline);
        
        move.setY(325);
        
        bracket.getElements().add(move);
        bracket.getElements().add(hline);
        
        move.setY(475);
        
        bracket.getElements().add(move);
        bracket.getElements().add(hline);
        
		
>>>>>>> a7c0c767dc5e5673dab374a4d01e94a0fcc3d147
	}
	
	
}