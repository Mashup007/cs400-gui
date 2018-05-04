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
// Email:            mzimmers@wisc.edu
//					 tmandalapu@wisc.edu 
//                   zzhang773@wisc.edu
//					 szhang399@wisc.edu
//                   cwang573@wisc.edu
// Lecturer's Name:  Debra Deppeler
// Source Credits:   StackOverflow
// Known Bugs:       Final round of competition has weird gap between team
//                   name and their associated text field 
///////////////////////////////////////////////////////////////////////////////
package application;

import java.nio.file.Path;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
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

/*
 * Set attributions for Challengers 
 */
public class Challenger {
    private String teamName; //Challenger name
    private Integer teamRank; //Challenger seed
    private Integer teamScore; //Score of most recent matchup
    private boolean teamInGame = true; //Boolean determining if team is still in the tournament
    private Challenger opposition; //Opposing challenger
    private TextField scoreField; //Text Entry for FX
    private Label nameLabel; //Label to display name
    private int currRound; //Challenger's current round
    
    /**
     * Basic constructor, initializes teamName, teamRank, and teamInGame.
     * @param name: team name (decided by name in file)
     * @param rank: team rank (decided by position of team name in file)
     */
    public Challenger(String name, Integer rank)
    {
        teamScore = -1;
        this.teamName = name;
        this.teamRank = rank;
        currRound = 1;
    }
    
    /**
     * Fills hBox with team name and text entry for score
     * @param hbox to be filled
     * @return hbox after it's been filled
     */
    public HBox fillHBox(HBox hbox) {
        //Adding label
        nameLabel = new Label();
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setText(teamName);
        //Adding text entry
        scoreField = new TextField();
        scoreField.setPromptText("Score");
        scoreField.setMaxSize(60, 30);
        scoreField.setMinSize(60, 30);
        //For getting user-inputted score
        scoreField.textProperty().addListener((obs, oldText, newText) -> {
            try {
                if (newText == "") teamScore = 0;
                else teamScore = Integer.parseInt(newText);
            } catch (Exception e) {}
        });
        //Adding everything to hbox
        hbox.getChildren().addAll(nameLabel, scoreField);
        
        return hbox;
        
    }
    
    /**
     * For when the current round is completed, remove text entry and replace with score
     * @param hbox: hbox with text entry
     * @return hbox with static score
     */
    public HBox restrictHBox(HBox hbox) {
        //Clearing hbox
        hbox.getChildren().clear();
        //Add name
        nameLabel = new Label();
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setText(teamName);
        //Adding static score
        Label s = new Label();
        s.setText(teamScore.toString());
        s.setMaxSize(60, 30);
        s.setMinSize(60, 30);
        //Adding above to hbox
        hbox.getChildren().addAll(nameLabel, s);
        
        return hbox;
    }
    /**
     * Same as hbox fill method, but for vBoxes
     * @param vbox: vbox to be filled
     * @return vbox after being filled with teamname and text entry for score
     */
    public VBox fillVBox(VBox vbox) {
        nameLabel = new Label();
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setText(teamName);
        
        scoreField = new TextField();
        scoreField.setPromptText("Score");
        scoreField.setMaxSize(60, 30);
        scoreField.setMinSize(60, 30);
        scoreField.textProperty().addListener((obs, oldText, newText) -> {
            try {
                if (newText == "") teamScore = 0;
                else teamScore = Integer.parseInt(newText);
            } catch (Exception e) {}
        });
        
        vbox.getChildren().addAll(nameLabel, scoreField);
        
        return vbox;
    }
    
    /**
     * Getter for current round
     * @return: returns current round
     */
    public int getCurrRound() {
        return currRound;
    }
    /**
     * Setter for current round
     * @param currRound: new currRound
     */
    public void setCurrRound(int currRound) {
        this.currRound = currRound;
    }
    
    /**
     * Getter for opposing challenger
     * @return returns opposing challenger
     */
    public Challenger getOpposition() {
        return opposition;
    }
    /**
     * Setter for opposing challenger
     * @param opposition: new opposition
     */
    public void setOpposition(Challenger opposition) {
        this.opposition = opposition;
    }

    /**
     * Getter for team name
     * @return team name
     */
    public String getTeamName() {
        return teamName;
    }
    
    /**
     * Getter for team rank
     * @return teamRank
     */
    public Integer getTeamRank() {
        return teamRank;
    }

    /**
     * Getter for team score
     * @return teamScore
     */
    public Integer getTeamScore() {
        return teamScore;
    }

    /**
     * Change team score when user submits new team scores
     * @param teamScore: new team score
     */
    public void setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
    }

    /**
     * Return true if team hasn't lost yet, return false if the team has lost and is no longer
     * part of the tournament
     * @return teamInGame
     */
    public boolean isTeamInGame() {
        return teamInGame;
    }

    /**
     * Set teamInGame to false when the team has lost a game.
     */
    public void exitTournament() {
        this.teamInGame = false;
    }

}

