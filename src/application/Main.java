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