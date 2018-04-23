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
	private int numTeams = 16;
	public static int curLine = 0;

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage)throws Exception{
		primaryStage.setTitle("Tournament");

		//Creating the general layout (Using BorderPane)
		BorderPane bracket_layout = new BorderPane();
		
		//Set up the title
		Label title = new Label("Tournament");
		title.setAlignment(Pos.CENTER);
		title.setMinHeight(25);
		title.setMaxWidth(1500);
		bracket_layout.setTop(title);
		
		
		//Creating submit button
		Button button = new Button();
		button.setText("Submit");

		//Set up the submit botton
		HBox hbox_bottom = new HBox();
		hbox_bottom.getChildren().add(button);
		hbox_bottom.setAlignment(Pos.CENTER);
		hbox_bottom.setPadding(new Insets(20));
		bracket_layout.setBottom(hbox_bottom);

		//////////////Set up the challengers/////////////////
		VBox r1Left = new VBox(100);
		VBox r2Left = new VBox(220);
		VBox r1Right = new VBox(100);
		VBox r2Right= new VBox(220);
		VBox r3final = new VBox(50);

		HBox hbox1 = new HBox(10);
		Label label1 = new Label();
		label1.setAlignment(Pos.CENTER);
		label1.setMinHeight(25);
		label1.setText("Team1");

		TextField score1 = new TextField();
		score1.setMaxHeight(20); score1.setMaxWidth(60);
		score1.setPromptText("Score");
		score1.setFocusTraversable(false);
		hbox1.getChildren().addAll(label1, score1);

		HBox hbox2 = new HBox(10);
		Label label2 = new Label();
		label2.setAlignment(Pos.CENTER);
		label2.setMinHeight(25);
		label2.setText("Team2");

		TextField score2 = new TextField();
		score2.setMaxHeight(20); score2.setMaxWidth(60);
		score2.setPromptText("Score");
		score2.setFocusTraversable(false);
		hbox2.getChildren().addAll(label2, score2);

		HBox hbox3 = new HBox(10);
		Label label3 = new Label();
		label3.setAlignment(Pos.CENTER);
		label3.setMinHeight(25);
		label3.setText("Team3");

		TextField score3 = new TextField();
		score3.setMaxHeight(20); score3.setMaxWidth(60);
		score3.setPromptText("Score");
		score3.setFocusTraversable(false);
		hbox3.getChildren().addAll(label3, score3);

		HBox hbox4 = new HBox(10);
		Label label4 = new Label();
		label4.setAlignment(Pos.CENTER);
		label4.setMinHeight(25);
		label4.setText("Team4");

		TextField score4 = new TextField();
		score4.setMaxHeight(20); score4.setMaxWidth(60);
		score4.setPromptText("Score");
		score4.setFocusTraversable(false);
		hbox4.getChildren().addAll(label4, score4);

		HBox hbox5 = new HBox(10);
		Label label5 = new Label();
		label5.setAlignment(Pos.CENTER);
		label5.setMinHeight(25);
		label5.setText("Team5");

		TextField score5 = new TextField();
		score5.setMaxHeight(20); score5.setMaxWidth(60);
		score5.setPromptText("Score");
		score5.setFocusTraversable(false);
		hbox5.getChildren().addAll(label5, score5);

		HBox hbox6 = new HBox(10);
		Label label6 = new Label();
		label6.setAlignment(Pos.CENTER);
		label6.setMinHeight(25);
		label6.setText("Team6");

		TextField score6 = new TextField();
		score6.setMaxHeight(20); score6.setMaxWidth(60);
		score6.setPromptText("Score");
		score6.setFocusTraversable(false);
		hbox6.getChildren().addAll(label6, score6);

		HBox hbox7 = new HBox(10);
		Label label7 = new Label();
		label7.setAlignment(Pos.CENTER);
		label7.setMinHeight(25);
		label7.setText("Team7");

		TextField score7 = new TextField();
		score7.setMaxHeight(20); score7.setMaxWidth(60);
		score7.setPromptText("Score");
		score7.setFocusTraversable(false);
		hbox7.getChildren().addAll(label7, score7);

		HBox hbox8 = new HBox(10);
		Label label8 = new Label();
		label8.setAlignment(Pos.CENTER);
		label8.setMinHeight(25);
		label8.setText("Team8");

		TextField score8 = new TextField();
		score8.setMaxHeight(20); score8.setMaxWidth(60);
		score8.setPromptText("Score");
		score8.setFocusTraversable(false);
		hbox8.getChildren().addAll(label8, score8);

		r1Right.getChildren().addAll(hbox5,hbox6,hbox7,hbox8);
		//add evertying into the current vbox

		bracket_layout.setRight(r1Right);
		r1Right.setAlignment(Pos.CENTER);
		r1Right.setPadding(new Insets(0,100,0,0));

		////////////setting up for round 2/////////////////
		///////left side////////////
		HBox hbox1_r2 = new HBox();
		Label label1_r2 = new Label();
		label1_r2.setAlignment(Pos.CENTER);
		label1_r2.setMinHeight(25);
		label1_r2.setText("Team1");

		TextField score1_r2 = new TextField();
		score1_r2.setMaxHeight(20); score1_r2.setMaxWidth(60);
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
		//////right side/////
		HBox hbox5_r2 = new HBox(10);
		Label label5_r2 = new Label();
		label5_r2.setAlignment(Pos.CENTER);
		label5_r2.setMinHeight(25);
		label5_r2.setText("Team5");

		TextField score5_r2 = new TextField();
		score5_r2.setMaxHeight(20); score5_r2.setMaxWidth(60);
		score5_r2.setPromptText("Score");
		score5_r2.setFocusTraversable(false);
		hbox5_r2.getChildren().addAll(label5_r2, score5_r2);
		
		HBox hbox7_r2 = new HBox(10);
		Label label7_r2 = new Label();
		label7_r2.setAlignment(Pos.CENTER);
		label7_r2.setMinHeight(25);
		label7_r2.setText("Team7");

		TextField score7_r2 = new TextField();
		score7_r2.setMaxHeight(20); score7_r2.setMaxWidth(60);
		score7_r2.setPromptText("Score");
		score7_r2.setFocusTraversable(false);
		hbox7_r2.getChildren().addAll(label7_r2, score7_r2);
		
		HBox hbox1_r3 = new HBox(10);
		Label label1_r3 = new Label();
		label1_r3.setAlignment(Pos.CENTER);
		label1_r3.setMinHeight(25);
		label1_r3.setText("Team1");

		TextField score1_r3 = new TextField();
		score1_r3.setMaxHeight(20); score1_r3.setMaxWidth(60);
		score1_r3.setPromptText("Score");
		score1_r3.setFocusTraversable(false);
		hbox1_r3.getChildren().addAll(label1_r3, score1_r3);
		
		HBox hbox7_r3 = new HBox(10);
		Label label7_r3 = new Label();
		label7_r3.setAlignment(Pos.CENTER);
		label7_r3.setMinHeight(25);
		label7_r3.setText("Team7");

		TextField score7_r3 = new TextField();
		score7_r3.setMaxHeight(20); score7_r3.setMaxWidth(60);
		score7_r3.setPromptText("Score");
		score7_r3.setFocusTraversable(false);
		hbox7_r3.getChildren().addAll(label7_r3, score7_r3);
		////////////setting up for round 2 done/////////////

		HBox hbox9 = new HBox(10);
		hbox9.getChildren().addAll(label1_r2,score1_r2);

		HBox hbox10 = new HBox(10);
		hbox10.getChildren().addAll(label3_r2,score3_r2);
		
		HBox hbox11 = new HBox(10);
		hbox11.getChildren().addAll(label5_r2,score5_r2);
		
		HBox hbox12 = new HBox(10);
		hbox12.getChildren().addAll(label7_r2,score7_r2);
		
		HBox hbox13 = new HBox(10);
		hbox13.getChildren().addAll(label1_r3,score1_r3);
		
		HBox hbox14 = new HBox(10);
		hbox14.getChildren().addAll(label7_r3,score7_r3);
		
		/////vobox for round2_right
		r2Right.getChildren().addAll(hbox11,hbox12);
		
		////////vbox for round2_left
		r2Left.getChildren().addAll(hbox9,hbox10);
		
		////////vbox for final round
		r3final.getChildren().addAll(hbox13,hbox14);
		r3final.setPadding(new Insets(100,100,0,100));
		
		HBox hboxcenter = new HBox();
		hboxcenter.getChildren().addAll(r2Left,r3final,r2Right);
		bracket_layout.setCenter(hboxcenter);
		hboxcenter.setPadding(new Insets(110,0,0,100));

		//////vbox for round1_left
		r1Left.getChildren().addAll(hbox1,hbox2,hbox3,hbox4);
		bracket_layout.setLeft(r1Left);
		r1Left.setAlignment(Pos.CENTER);
		r1Left.setPadding(new Insets(0,0,0,100));
		////////////////Set Challengers Done/////////////////////////
		
		//Set up the scene(framework)
		Scene scene = new Scene(bracket_layout, win_width, win_height);
		primaryStage.setScene(scene);
		primaryStage.show();


		

		//Holds all the lines needed to create bracket
		Line[] lines = new Line[getNumLines(numTeams)];
		int rounds = 3;
		int line_height = (800)/rounds;
		int line_width = ((700)/rounds);
		//Center line
		lines[0] = new Line(550, 300, 650, 300); 
		curLine++;
		lines = addBrack(lines, 650, 300, line_height, line_width, rounds-1);
		lines = addBrack(lines, 550, 300, line_height, -line_width, rounds-1);
		
		for(Line line: lines) {
		    if(line == null)
		    {
		        break;
		    }
		    bracket_layout.getChildren().add(line);
		}
		
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
	    if(rounds != 1) {
	        lines = addBrack(lines, h_end, bot, line_height/2, line_width, rounds-1);
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
