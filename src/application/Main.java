package application;
	
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;


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
		
		bracket_border.setBottom(hbox_bottom);
		
		//Set up the scene(framework)
		Scene scene = new Scene(bracket_border, win_width, win_height);
		primaryStage.setScene(scene);
<<<<<<< HEAD
		primaryStage.show();	
=======
		primaryStage.show();
		
		
		
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
		
		bracket_layout.getChildren().add(bracket);
		
>>>>>>> 7c783ee7661c6ca470bfcd1105efc42718a91208
	}
	
	/**
     * Simplifies creating lines
     * @param line
     * @param x0- starting x
     * @param y0- starting y
     * @param x1- ending x
     * @param y1- ending y
     */
    public static void initLine(Line line, Float x0, Float y0, Float x1, Float y1)
    {
        line.setStartX(x0);
        line.setStartY(y0);
        line.setEndX(x1);
        line.setEndY(y1);
    }
}