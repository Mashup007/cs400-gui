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

public class Challenger {
    private String teamName;
    private Integer teamRank;
    private Integer teamScore;
    private boolean teamInGame = true;
    private Challenger opposition;
    private TextField scoreField;
    private Label nameLabel;
    private int currRound;
    
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
    
    public HBox fillHBox(HBox hbox) {

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
        
        hbox.getChildren().addAll(nameLabel, scoreField);
        
        return hbox;
        
    }
    
    public HBox restrictHBox(HBox hbox) {
        hbox.getChildren().clear();
        
        nameLabel = new Label();
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setText(teamName);
        
        Label s = new Label();
        s.setText(teamScore.toString());
        s.setMaxSize(60, 30);
        s.setMinSize(60, 30);
        
        hbox.getChildren().addAll(nameLabel, s);
        
        return hbox;
    }
    
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
    
    public int getCurrRound() {
        return currRound;
    }

    public void setCurrRound(int currRound) {
        this.currRound = currRound;
    }

    public Challenger getOpposition() {
        return opposition;
    }

    public void setOpposition(Challenger opposition) {
        this.opposition = opposition;
    }

    /**
     * @return teamName
     */
    public String getTeamName() {
        return teamName;
    }
    
    /**
     * @return teamRank
     */
    public Integer getTeamRank() {
        return teamRank;
    }

    /**
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
