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
import javafx.scene.layout.FlowPane;
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
	private int numTeams = 8;
	public static int curLine = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage)throws Exception{
		primaryStage.setTitle("Tournament");
		
		//Creating submit button
		Button button = new Button();
		button.setText("Submit");
		
		//Creating the general layout (Using BorderPane)
		BorderPane bracket_layout = new BorderPane();
		
		//Set up the submit botton
		HBox hbox_bottom = new HBox();
		hbox_bottom.getChildren().add(button);
		hbox_bottom.setAlignment(Pos.CENTER);
		hbox_bottom.setPadding(new Insets(20));
		bracket_layout.setBottom(hbox_bottom);
		
		//////////////Set up the challengers/////////////////

        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setMinHeight(25);
        label.setText("Team1");

        TextField team = new TextField();
        team.setMaxHeight(20); team.setMaxWidth(100);
        team.setPromptText("Input score");
        team.setFocusTraversable(false);
        
        Label label2 = new Label();
        label2.setAlignment(Pos.CENTER);
        label2.setMinHeight(25);
        label2.setText("Team2");

        TextField team2 = new TextField();
        team2.setMaxHeight(20); team2.setMaxWidth(100);
        team2.setPromptText("Input score");
        team2.setFocusTraversable(false);
        
        VBox vbox1 = new VBox(label, team);  //vbox for team1       
        vbox1.setPadding(new Insets(20));
        
        VBox vbox2 = new VBox(label2, team2);  //vbox for team2 
        vbox2.setPadding(new Insets(20));
        //add evertying into the current vbox
        
        //adjust the position of the vbox
        bracket_layout.getChildren().add(vbox1);
        bracket_layout.getChildren().add(vbox2);
       // BorderPane.setAlignment(vbox, Pos.BOTTOM_CENTER);

		//Set up the scene(framework)
		Scene scene = new Scene(bracket_layout, win_width, win_height);
		primaryStage.setScene(scene);
		primaryStage.show();
		
<<<<<<< HEAD
//		//Creating bracket
//		Path bracket = new Path();
//		
//		//Setting starting point of path
//		MoveTo start = new MoveTo();
//		start.setX(1050);
//		start.setY(300);
//		
//		//Creating winner line
//		HLineTo winner = new HLineTo();
//		winner.setX(1150);
//		
//		bracket.getElements().add(start);
//        bracket.getElements().add(winner);
//        
//		//Creating semifinals
//		MoveTo move = new MoveTo();
//		move.setX(1050);
//		move.setY(400);
//		
//		VLineTo vline = new VLineTo();
//		vline.setY(200);
//		
//		bracket.getElements().add(move);
//		bracket.getElements().add(vline);
//		
//		HLineTo hline = new HLineTo();
//		hline.setX(950);
//		
//		bracket.getElements().add(hline);
//		bracket.getElements().add(move);
//		bracket.getElements().add(hline);
//		
//		move.setY(475);
//		vline.setY(325);
//		
//		bracket.getElements().add(move);
//		bracket.getElements().add(vline);
//		
//		move.setY(275);
//		vline.setY(125);
//		
//		bracket.getElements().add(move);
//        bracket.getElements().add(vline);
//        
//        hline.setX(850);
//        
//        bracket.getElements().add(hline);
//        
//        move.setY(275);
//        
//        bracket.getElements().add(move);
//        bracket.getElements().add(hline);
//        
//        move.setY(325);
//        
//        bracket.getElements().add(move);
//        bracket.getElements().add(hline);
//        
//        move.setY(475);
//        
//        bracket.getElements().add(move);
//        bracket.getElements().add(hline);
//        
//		
=======
		//Holds all the lines needed to create bracket
		Line[] lines = new Line[getNumLines(numTeams)];
		int rounds = 3;
		int line_height = 200;
		int line_width = 500/rounds-50;
		//Center line
		lines[0] = new Line(550, 300, 650, 300); 
		curLine++;
		lines = addBrack(lines, 650, 300, line_height, line_width, rounds);
		
		
		for(Line line: lines) {
		    if(line == null)
		    {
		        break;
		    }
		    bracket_border.getChildren().add(line);
		}
		
>>>>>>> dd355392bac530d45e6447c119942285f4b66970
	}
	private static Line[] addBrack(Line[] lines, int x, int y, int line_height, int line_width, int rounds) {
	    int bot = y+line_height/2;
	    int top = y-line_height/2;
	    int h_end = x+line_width;
	    //vert
	    lines[curLine] = new Line(x, top, x, bot);
	    lines[curLine+1] = new Line(x, top, h_end, top);
	    lines[curLine+2] = new Line(x, bot, h_end, bot);
	    curLine += 3;
	    if(rounds != 0) {
//	        lines = addBrack(lines, h_end, bot, line_height/2, line_width, rounds-1);
	        return addBrack(lines, h_end, top, line_height/2, line_width, rounds-1);
	    }
	    else {
	        return lines;
	    }
	}
    private static int getNumLines(int numTeams)
    {
        int result;
        if(numTeams == 2)
        {
            return 1;
        }
        else
        {
            return 3*(numTeams/2)+getNumLines(numTeams/2);
        }
    }
    
	
}