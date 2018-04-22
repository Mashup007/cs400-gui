package application;

public class Challenger {
    private String teamName;
    private int teamRank;
    private int teamScore;
    private boolean teamInGame = true;
    
    /**
     * Basic constructor, initializes teamName, teamRank, and teamInGame.
     * @param name: team name (decided by name in file)
     * @param rank: team rank (decided by position of team name in file)
     */
    public Challenger(String name, int rank)
    {
        this.teamName = name;
        this.teamRank = rank;
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
    public int getTeamRank() {
        return teamRank;
    }

    /**
     * Change team rank as tournament proceeds
     * @param teamRank: new team ranking
     */
    public void setTeamRank(int teamRank) {
        this.teamRank = teamRank;
    }

    /**
     * @return teamScore
     */
    public int getTeamScore() {
        return teamScore;
    }

    /**
     * Change team score when user submits new team scores
     * @param teamScore: new team score
     */
    public void setTeamScore(int teamScore) {
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
