package application;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;


public class Main extends Application {
<<<<<<< HEAD
	Button button;

=======
    //To make window dimensions more accessible for future use
	public int win_width = 1200;
	public int win_height = 600;
	
>>>>>>> c9a99fe47c1023be735d43804cbc94b47b317dc6
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage)throws Exception{
		primaryStage.setTitle("Tournament");
		
		//Creating submit button
		Button button = new Button();
		button.setText("Submit");
<<<<<<< HEAD

		StackPane bracket_layout = new StackPane();
		bracket_layout.getChildren().add(button);

		Scene scene = new Scene(bracket_layout,1200,700);
		primaryStage.setScene(scene);
		primaryStage.show();

=======
		
		BorderPane bracket_layout = new BorderPane();
		bracket_layout.getChildren().add(button);
		
		Scene scene = new Scene(bracket_layout, win_width, win_height);
		primaryStage.setScene(scene);
		primaryStage.show();
>>>>>>> c9a99fe47c1023be735d43804cbc94b47b317dc6
	}

	/**
<<<<<<< HEAD
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
=======
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
>>>>>>> c9a99fe47c1023be735d43804cbc94b47b317dc6
