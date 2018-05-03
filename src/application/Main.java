///////////////////////////////////////////////////////////////////////////////
// Title:            Team Project, Milestone 3
// This file:        Main.java
// Other Files:      Challenger.java   FileManager.java   
// Semester:         CS 400 Spring 2018
//
// Authors:          Matt Zimmers
//					 Tarun Mandalapu 
//					 Zidong Zhang
//					 Shuyan Zhang
//					 Chao Wang
// Email:            tmandalapu@wisc.edu 
//					 
// Lecturer's Name:  Debra Deppeler
// Source Credits:   StackOverflow
// Known Bugs:       Final round of competition has weird gap between team
//                   name and their associated text field 
///////////////////////////////////////////////////////////////////////////////
package application;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
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
import javafx.scene.Parent;
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
import javafx.scene.text.TextAlignment;

public class Main extends Application {
    //To make window dimensions more accessible for future use
    public int win_width = 1200; 
    public int win_height = 600;
    //Getting number of teams from number of lines in file
    private int numTeams = number_of_Teams();
    private int rounds;
    //Used to create bracket lines
    public static int curLine = 0;
    //2D ArrayList to hold challengers for each round
    private ArrayList<ArrayList<Challenger>> challengers;
    //2D ArrayList to hold horizontal boxes for drawing challenger information and entry fields
    private ArrayList<ArrayList<HBox>> challengerContent;
    //Arraylist of vertical boxes to hold HBoxes
    private ArrayList<VBox> columns;
    
    //File path
    public static String path;
    
    /**
     * Getting file path, and opening file for reading
     * @param args: command line arguments
     */
    public static void main(String[] args) {
        path = args[0]; //Initializing input file path
        launch(args); 
    }

    @Override
    /**
     * FX runner
     * primaryStage: Base-level pane
     */
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tournament");
        //Initializing number of rounds
        if (numTeams == 1) {rounds = 1;}
        else { rounds = (int)(Math.log10(numTeams)/Math.log10(2)); }
        //Creating the general layout (Using BorderPane)
        BorderPane bracket_layout = new BorderPane();
        
        //Set up the title
        Label title = new Label("Tournament");
        title.setAlignment(Pos.CENTER);
        title.setMinHeight(30);
        title.setMaxHeight(30);
        title.setMaxWidth(win_width);
        bracket_layout.setTop(title);
        
        //Creating submit button
        Button submit = new Button();
        submit.setText("Submit");
        submit.setMinSize(80, 20);
        submit.setOnMouseClicked(e -> { submitClicked(); });
        HBox hbox_bottom = new HBox();
        hbox_bottom.getChildren().add(submit);
        hbox_bottom.setAlignment(Pos.TOP_CENTER);
        hbox_bottom.setMinHeight(40);
        hbox_bottom.setMaxHeight(40);
        bracket_layout.setBottom(hbox_bottom);

        //////////////Create Challenger Objects/////////////////
        // Set up the scene(framework)
        Scene scene = new Scene(bracket_layout, win_width, win_height);
        primaryStage.setScene(scene);
        primaryStage.show();
        if (numTeams==0) {return;}
        
        challengers = new ArrayList<ArrayList<Challenger>>();
        challengerContent = new ArrayList<ArrayList<HBox>>();
        for (int i = 0; i < rounds; i++) {
            challengers.add(new ArrayList<Challenger>());
            challengerContent.add(new ArrayList<HBox>());
        }
        challengers.set(0, seed());
        
        // Fill proceeding slots with nulls
        for (int i = 1; i < rounds; i++) {
            for (int j = 0; j < numTeams/Math.pow(2, i); j++) {
                challengers.get(i).add(null);
            }
        }
        
        // Split Center by Horizontally Ordered VBoxes
        HBox central = new HBox(); // VBox columns stored in here
        
        // create columns of VBoxes
        double colSpace = (double)(win_width)/(rounds*2-1);
        columns = new ArrayList<VBox>();
        for (int i = 0; i < rounds*2-1; i++) {
            VBox newCol = new VBox(); 
            // Fit to window and set constant height
            newCol.setMaxSize(colSpace, 530);
            newCol.setMinSize(colSpace, 530);
            newCol.setAlignment(Pos.CENTER);          
            columns.add(newCol);
        }
        
        // Add appropriate number of HBoxes to end columns containing Challenger info
        int challengerIndex = 0;
        for (int i = 0; i < columns.size(); i++) {
            int numBoxes = (int)Math.pow(2,Math.abs(rounds-1-i));
            if (rounds == 1 && numTeams == 2) numBoxes = 2;
            ArrayList<HBox> boxes = new ArrayList<HBox>();
            for (int j = 0; j < numBoxes; j++) {
                if (numBoxes != 1 && numBoxes != 0) {
                    HBox newHBox = new HBox(10);
                    newHBox.setAlignment(Pos.CENTER);
                    newHBox.setMinSize(colSpace, (double)530/numBoxes);
                    newHBox.setMaxSize(colSpace, (double)530/numBoxes);
                    if (i == 0 || i == columns.size()-1) {
                        challengers.get(0).get(challengerIndex).fillHBox(newHBox);
                        challengerIndex++;
                    }
                    if (i <= columns.size()/2)
                        challengerContent.get(i).add(newHBox);
                    else
                        challengerContent.get(columns.size()-(i+1)).add(newHBox);           
                    boxes.add(newHBox);
                } else if (numTeams == 1) {
                    VBox onlyTeam = new VBox();
                    onlyTeam.setAlignment(Pos.CENTER);
                    Label done = new Label();
                    done.setAlignment(Pos.CENTER);
                    done.setText("Champion by Default: " + challengers.get(0).get(0).getTeamName());
                    onlyTeam.getChildren().add(done);
                    
                    columns.clear();
                    columns.add(onlyTeam);
                    central.setAlignment(Pos.CENTER);
                }
            }
            if (boxes.size() != 0) columns.get(i).getChildren().addAll(boxes);
        }
        
        // Add finished columns to central layout
        central.getChildren().addAll(columns);
        bracket_layout.setCenter(central);
        
        //////////////// Creates Bracket Lines Independent of (H/V)Boxes ////////////////////////
        //Holds all the lines needed to create bracket
        Line[] lines = new Line[getNumLines(numTeams)];
        double line_height = (win_height-70)/2; // Fit to center height
        double line_width = (win_width)/(rounds*2-1); // Fit to window width
        //Center line
        if (numTeams != 1) { //added 5/1/2018
            lines[0] = new Line(600-line_width/2, 312, 600+line_width/2, 310); 
            if (lines.length != 1) {
                lines = addBrack(lines, 600+line_width/2, 312, line_height, line_width, rounds-1);
                lines = addBrack(lines, 600-line_width/2, 312, line_height, -line_width, rounds-1);
            }
            
            for(Line line: lines) {
                if(line == null)
                {
                    break;
                }
                bracket_layout.getChildren().add(line);
            }
        }
    } 
    
    /**
     * Recursive method for drawing brackets
     * @param lines: array of lines
     * @param x: x coord to start bracket
     * @param y: y coord to start bracket
     * @param line_height: height of lines to be drawn
     * @param line_width: width of lines to be drawn
     * @param rounds: Number of rounds 
     * @return lines array after lines for new bracket have been added to it
     */
    private static Line[] addBrack(Line[] lines, double x, double y, double line_height, double line_width, int rounds) {
        //To make line creating statements more concise
        double bot = y+line_height/2;
        double top = y-line_height/2;
        double h_end = x+line_width;
        
        lines[++curLine] = new Line(x, top, x, bot); //Vertical line
        lines[++curLine] = new Line(x, top, h_end, top); //Top horizontal line
        lines[++curLine] = new Line(x, bot, h_end, bot); //Bottom horizontal line
        
        //Recursive call
        if (rounds != 1) {
            lines = addBrack(lines, h_end, bot, line_height/2, line_width, rounds-1);
            return addBrack(lines, h_end, top, line_height/2, line_width, rounds-1);
        } 
        //Base case
        else {
            return lines;
        }
    }
   
    /**
     * Returns the number of lines needed to draw a tournament bracket for a given number
     * of teams
     * @param numTeams: Number of teams
     * @return total number of lines needed to draw tournament bracket
     */
    private static int getNumLines(int numTeams) {
        if (numTeams == 2 ) return 1; //Only one line needed
        if (numTeams == 1) return 0 ;//No lines need to be drawn
        //Math
        else return 3*(numTeams/2)+getNumLines(numTeams/2);
    }
    /**
     * Seeds tournament based on challenger positions in file
     * @return ArrayList of challengers for tournament
     */
    private ArrayList<Challenger> seed() {  
        ArrayList<Challenger> tempChallengers = new ArrayList<Challenger>();
        ArrayList<Challenger> startingChallengers = new ArrayList<Challenger>();
        ArrayList<Challenger> onlyoneChallenger = new ArrayList<Challenger>();
        
        //One team case
        if (numTeams == 1) { 
                    onlyoneChallenger.add(new Challenger(randName(0), 1 ));
                    onlyoneChallenger.add(null);
                    return onlyoneChallenger;
        }
        //Multiple teams case
        for (int i = 0; i < numTeams; i++) {
            tempChallengers.add(new Challenger(randName(i), i + 1 ));
           
        }
        // seeding opposition
        for (int i = 0; i < numTeams/2; i++) {
            tempChallengers.get(i).setOpposition(tempChallengers.get(numTeams-1-i));
            tempChallengers.get(numTeams-1-i).setOpposition(tempChallengers.get(i));
            
            startingChallengers.add(tempChallengers.get(i));
            startingChallengers.add(tempChallengers.get(i).getOpposition());
        }
        return startingChallengers;
    }
    
    /**
     * Gets number of teams for tournament from file 
     * @return number of teams
     */
    private int number_of_Teams() {
        int numTeam = 0;
        numTeam = FileManager.loadChallenger(path).size();
        return numTeam;
    }
    
    /**
     * Getting challenger name from file
     * @param index: the challenger's index in the array of challengers
     * @return challenger name
     */
    private String randName(int index) {        
        String name = new String(); 
        name = FileManager.loadChallenger(path).get(index);
        return name;
    }
    
    /**
     * Called when user clicks submit
     */
    private void submitClicked() {
        //for (ArrayList<Challenger> a : challengers) System.out.println(a.size());
        if (challengers.get(challengers.size()-1).get(0) == null || challengers.get(challengers.size()-1).get(1) == null) {
                int currRound = 1;
                //Check entry boxes for each set of opposing challengers, if they are filled use
                //scores to determine which team advances and advance them using tournamentAdvance
                for (ArrayList<Challenger> a : challengers) {
                    for (int i = 0; i < a.size(); i+=2) {
                        Challenger t1 = a.get(i);
                        Challenger t2 = a.get(i+1);
                        if (t1 != null && t2 != null && t1.getTeamScore() != t2.getTeamScore()
                                && t1.isTeamInGame() && t2.isTeamInGame() 
                                && t1.getTeamScore() != -1 && t2.getTeamScore() != -1
                                && t1.getCurrRound() == currRound && t2.getCurrRound() == currRound) {
                            Challenger winner = t1;
                            Challenger loser = t2;
                            if (t1.getTeamScore() < t2.getTeamScore()) { 
                                winner = t2;
                                loser = t1;
                            }
                            if (currRound < rounds-1) tournamentAdvance(currRound, winner, loser, i);
                            else finalRound(winner,loser);
                        }
                    }
                    currRound++;
                }
        } else determineWinner();
    }
    
    /**
     * Removes entry box for loser, draws new entry box and challenger name on next level of bracket for winner
     * @param currRound: Current round of tournament
     * @param winner: Winning challenger from previous round
     * @param loser: Losing challenger from previous round
     */
    private void tournamentAdvance(int currRound, Challenger winner, Challenger loser) {
        HBox modifyWinner = null;
        HBox modifyLoser = null;
        HBox destination = null;
        int numTeamsinRound = numTeams/(int)Math.pow(2, currRound-1);
        
        for (int i = 0; i < numTeamsinRound; i++) {
            if (challengers.get(currRound-1).get(i) == winner) {
                modifyWinner = challengerContent.get(currRound-1).get(i);
                destination = challengerContent.get(currRound).get(i/2);
                challengers.get(currRound).set(i/2, winner);
            } else if (challengers.get(currRound-1).get(i) == loser) {
                modifyLoser = challengerContent.get(currRound-1).get(i);
            }
        }
        if (modifyWinner == null) System.out.println("Error, winner not found");
        if (modifyLoser == null) System.out.println("Error, loser not found");
        if (destination == null) System.out.println("Error: destination not found");
        
        winner.restrictHBox(modifyWinner);
        loser.restrictHBox(modifyLoser);
        winner.fillHBox(destination);
        
        loser.exitTournament();
        winner.setTeamScore(-1);
        winner.setOpposition(null);
        winner.setCurrRound(winner.getCurrRound()+1);
    }
    
   private void finalRound(Challenger winner, Challenger loser) {
        VBox center = columns.get((columns.size()-1)/2);
        double colSpace = (double)(win_width)/(rounds*2-1);
        
        HBox modifyWinner = null;
        HBox modifyLoser = null;
        int numTeamsinRound = 4;
        
        for (int i = 0; i < numTeamsinRound; i++) {
            if (challengers.get(rounds-2).get(i) == winner) {
                modifyWinner = challengerContent.get(rounds-2).get(i);
                challengers.get(rounds-1).set(i/2, winner);
            } else if (challengers.get(rounds-2).get(i) == loser) {
                modifyLoser = challengerContent.get(rounds-2).get(i);
            }
        }
        
        if (center.getChildren().size() == 0) {
            HBox newHBox = new HBox(10);
            newHBox.setAlignment(Pos.BOTTOM_CENTER);
            newHBox.setMinSize(colSpace, (double)530/2);
            newHBox.setMaxSize(colSpace, (double)530/2);
            winner.fillHBox(newHBox);
            
            HBox newHBox2 = new HBox(40);
            newHBox2.setAlignment(Pos.TOP_CENTER);
            newHBox2.setMinSize(colSpace, (double)530/2);
            newHBox2.setMaxSize(colSpace, (double)530/2);
            
            center.setSpacing(10);
            center.getChildren().addAll(newHBox,newHBox2);
        } else { 
            HBox lower = (HBox)center.getChildren().get(1);
            lower.setPadding(new Insets(20,0,0,0));
            winner.fillHBox(lower);
        }
        
        winner.restrictHBox(modifyWinner);
        loser.restrictHBox(modifyLoser);
        
        loser.exitTournament();
        winner.setTeamScore(-1);
        winner.setOpposition(null);
        winner.setCurrRound(winner.getCurrRound()+1);
    }
    
    public void determineWinner() {
        VBox center = columns.get((columns.size()-1)/2);
        HBox modifyWinner = (HBox)center.getChildren().get(0);
        HBox modifyLoser = (HBox)center.getChildren().get(1);
        
        Challenger t1 = challengers.get(challengers.size()-1).get(0);
        Challenger t2 = challengers.get(challengers.size()-1).get(1);
        if (t2 == null) {
            updatePlacing(t1,modifyWinner,null,null);
            return;
        }
        Challenger winner = t1;
        Challenger loser = t2;
        if (t1.getTeamScore() < t2.getTeamScore()) {
            winner = t2;
            loser = t1;
            HBox temp = modifyWinner;
            modifyWinner = modifyLoser;
            modifyLoser = temp;
        }
        
        winner.restrictHBox(modifyWinner);
        loser.restrictHBox(modifyLoser);
        winner.exitTournament();
        loser.exitTournament();
      
        updatePlacing(winner, modifyWinner, loser, modifyLoser);
    }

    public void updatePlacing(Challenger winner, HBox winnerContent, Challenger second, HBox secondContent) {
        Challenger third = null;
        HBox thirdContent = null;
        
        if (rounds > 1) {
            for (int i = 0; i < 4; i++) {
                Challenger p = challengers.get(rounds-2).get(i);
                if ((third == null && p.getCurrRound() != rounds) || (p.getCurrRound() != rounds && third.getTeamScore() < p.getTeamScore())) {
                    third = p;
                    thirdContent = challengerContent.get(rounds-2).get(i);
                }
            }
        }
        
        winnerContent.getChildren().clear();
        Label firstL = new Label();
        Label secondL = new Label();
        Label thirdL = new Label();
        
        firstL.setAlignment(Pos.CENTER);
        firstL.setText("Champion: " + winner.getTeamName() + "\n  Score: " + winner.getTeamScore());
        winnerContent.getChildren().clear();
        winnerContent.getChildren().add(firstL);
        
        if (second != null) {
            secondL.setAlignment(Pos.CENTER);
            secondL.setText("2nd: " + second.getTeamName() + "\n  Score: " + second.getTeamScore());
            secondContent.getChildren().clear();
            secondContent.getChildren().add(secondL);
        }
        if (third != null) {
            thirdL.setAlignment(Pos.CENTER);
            thirdL.setText("3rd: " + third.getTeamName() + "\n  Score: " + third.getTeamScore());
            thirdContent.getChildren().clear();
            thirdContent.getChildren().add(thirdL);
        }
    }
}
