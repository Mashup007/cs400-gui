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
		VBox r1Left = new VBox(100);
		VBox r2Left = new VBox(220);
		
		HBox hbox1 = new HBox();
        Label label1 = new Label();
        label1.setAlignment(Pos.CENTER);
        label1.setMinHeight(25);
        label1.setText("Team1");

        TextField score1 = new TextField();
        score1.setMaxHeight(20); score1.setMaxWidth(60);
        score1.setPromptText("Score");
        score1.setFocusTraversable(false);
        hbox1.getChildren().addAll(label1, score1);
        
        HBox hbox2 = new HBox();
        Label label2 = new Label();
        label2.setAlignment(Pos.CENTER);
        label2.setMinHeight(25);
        label2.setText("Team2");

        TextField score2 = new TextField();
        score2.setMaxHeight(20); score2.setMaxWidth(60);
        score2.setPromptText("Score");
        score2.setFocusTraversable(false);
        hbox2.getChildren().addAll(label2, score2);
        
        HBox hbox3 = new HBox();
		Label label3 = new Label();
		label3.setAlignment(Pos.CENTER);
		label3.setMinHeight(25);
		label3.setText("Team3");

		TextField score3 = new TextField();
		score3.setMaxHeight(20); score3.setMaxWidth(60);
		score3.setPromptText("Score");
		score3.setFocusTraversable(false);
		hbox3.getChildren().addAll(label3, score3);
		
		HBox hbox4 = new HBox();
		Label label4 = new Label();
		label4.setAlignment(Pos.CENTER);
		label4.setMinHeight(25);
		label4.setText("Team4");

		TextField score4 = new TextField();
		score4.setMaxHeight(20); score4.setMaxWidth(60);
		score4.setPromptText("Score");
		score4.setFocusTraversable(false);
		hbox4.getChildren().addAll(label4, score4);
		////////////setting up for round 2/////////////////
		HBox hbox1_r2 = new HBox();
        Label label1_r2 = new Label();
        label1_r2.setAlignment(Pos.CENTER);
        label1_r2.setMinHeight(25);
        label1_r2.setText("Team1");

        TextField score1_r2 = new TextField();
        score1_r2.setMaxHeight(20); score1_r2.setPrefWidth(60);
        score1_r2.setPromptText("Score");
        score1_r2.setFocusTraversable(false);
        hbox1_r2.getChildren().addAll(label1_r2, score1_r2);
		
        HBox hbox3_r2 = new HBox();
		Label label3_r2 = new Label();
		label3_r2.setAlignment(Pos.CENTER);
		label3_r2.setMinHeight(25);
		label3_r2.setText("Team3");
		
		TextField score3_r2 = new TextField();
		score3_r2.setMaxHeight(20); score3_r2.setMaxWidth(60);
		score3_r2.setPromptText("Score");
		score3_r2.setFocusTraversable(false);
		hbox3_r2.getChildren().addAll(label3_r2, score3_r2);
		////////////setting up for round 2 done/////////////
		
		HBox hbox9 = new HBox();
		hbox9.getChildren().addAll(label1_r2,score1_r2);
		
		HBox hbox10 = new HBox();
		hbox10.getChildren().addAll(label3_r2,score3_r2);
		
		r2Left.getChildren().addAll(hbox9,hbox10);
		bracket_layout.setCenter(r2Left);
		r2Left.setAlignment(Pos.CENTER);
		r2Left.setPadding(new Insets(0,0,0,90));
        
        //add evertying into the current vbox
		r1Left.getChildren().addAll(hbox1,hbox2,hbox3,hbox4);
		bracket_layout.setLeft(r1Left);
		r1Left.setAlignment(Pos.CENTER);
		r1Left.setPadding(new Insets(0,0,0,100));
////////////////Set Challengers Done/////////////////////////
		
		//Set up the scene(framework)
		Scene scene = new Scene(bracket_layout, win_width, win_height);
		primaryStage.setScene(scene);
		primaryStage.show();
		

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

		//Holds all the lines needed to create bracket
//		Line[] lines = new Line[getNumLines(numTeams)];
//		int rounds = 3;
//		int line_height = 200;
//		int line_width = 500/rounds-50;
//		//Center line
//		lines[0] = new Line(550, 300, 650, 300); 
//		curLine++;
//		lines = addBrack(lines, 650, 300, line_height, line_width, rounds);
//		
//		
//		for(Line line: lines) {
//		    if(line == null)
//		    {
//		        break;
//		    }
//		    bracket_layout.getChildren().add(line);
//		}
//		
//	}
//	private static Line[] addBrack(Line[] lines, int x, int y, int line_height, int line_width, int rounds) {
//	    int bot = y+line_height/2;
//	    int top = y-line_height/2;
//	    int h_end = x+line_width;
//	    //vert
//	    lines[curLine] = new Line(x, top, x, bot);
//	    lines[curLine+1] = new Line(x, top, h_end, top);
//	    lines[curLine+2] = new Line(x, bot, h_end, bot);
//	    curLine += 3;
//	    if(rounds != 0) {
////	        lines = addBrack(lines, h_end, bot, line_height/2, line_width, rounds-1);
//	        return addBrack(lines, h_end, top, line_height/2, line_width, rounds-1);
//	    }
//	    else {
//	        return lines;
//	    }
//	}
//    private static int getNumLines(int numTeams)
//    {
//        int result;
//        if(numTeams == 2)
//        {
//            return 1;
//        }
//        else
//        {
//            return 3*(numTeams/2)+getNumLines(numTeams/2);
//        }
//    }
    

}
}