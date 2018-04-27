package application;

import javafx.scene.canvas.GraphicsContext;

/**
 * the class of challenger
 *
 */
public class Challenger {
	private String teamName;
	private int teamScore;
	private String honor;
	private int drawX;
	private int drawY;
	
	/**
	 * Constructor
	 * @param teamName
	 */
	public Challenger(String teamName) {
		this.teamName = teamName;
		this.teamScore = -1;
		this.honor = null;
	}
	
	/**
	 * check if the team has score
	 * @return
	 */
	public boolean hasScore() {
		return teamScore != -1;
	}
	
	/**
	 * getter
	 * @return
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * setter
	 * @param teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * getter
	 * @return
	 */
	public int getTeamScore() {
		return teamScore;
	}

	/**
	 * setter
	 * @param teamName
	 */
	public void setTeamScore(int teamScore) {
		this.teamScore = teamScore;
	}

	/**
	 * getter
	 * @return
	 */
	public String getHonor() {
		return honor;
	}

	/**
	 * setter
	 * @param teamName
	 */
	public void setHonor(String honor) {
		this.honor = honor;
	}
	
	/**
	 * getter
	 * @return
	 */
	public int getDrawX() {
		return drawX;
	}

	/**
	 * getter
	 * @return
	 */
	public int getDrawY() {
		return drawY;
	}

	/**
	 * draw the team
	 * @param g
	 * @param x
	 * @param y
	 */
	public void draw(GraphicsContext g, int x, int y) {
		drawX = x;
		drawY = y;
		g.strokeRect(x, y, 100, 40);
		g.fillText(teamName, x + 5, y + 15);
		if (hasScore())
			g.fillText(teamScore + "", x + 5, y + 30);
		if (honor != null)
			g.fillText(honor, x + 25, y + 30);
	}
	
}
