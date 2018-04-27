package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

/**
 * creating the schedule of the tournament
 *
 */

public class Game {
	private ArrayList< ArrayList<Challenger> > tournament;
	
	/**
	 * Constructor
	 * @param challenger
	 */
	public Game(ArrayList<Challenger> challenger) {
		tournament = new ArrayList<>();
		tournament.add(challenger);
	}
		
	/**
	 * draw all
	 * @param g
	 */
	public void draw(GraphicsContext g) {
		g.setLineWidth(1);
		for (int i = 0; i < tournament.size(); i++) {
			ArrayList<Challenger> challengers = tournament.get(i);
			int last_y = 0;
			for (int j = 0; j < challengers.size(); j++) {
				Challenger challenger = challengers.get(j);
				int y = j * ((int)Math.pow(2, i) * 50);
				if (i != 0)
					y += ((int)Math.pow(2, i) * 50) / 2 - 25;
				challenger.draw(g, i * 210,  y);
				// draw line
				if (j % 2 == 1 && i + 1 < tournament.size()) {
					int target_y = (j / 2) * ((int)Math.pow(2, i + 1) * 50);
					target_y += ((int)Math.pow(2, i  + 1) * 50) / 2 - 25;
					drawLine(g, i * 210 + 100, y + 25, 
							i * 210 + 100, last_y + 25, 
							(i + 1) * 210, target_y + 25);
				}
				last_y = y;
			}
		}
	}
	
	/**
	 * add score
	 * @param teamName
	 * @param teamScore
	 * @return
	 */
	public String addScore(String teamName, int teamScore) {
		if (finish())
			return "The final challenge is completed!";
		ArrayList<Challenger> current = tournament.get(tournament.size() - 1);
		Challenger target = null;
		for (Challenger item : current) {
			if (item.getTeamName().equals(teamName)) 
				target = item;
		}
		if (target == null)
			return "Can't find the team!";
		target.setTeamScore(teamScore);
		//
		for (Challenger item : current) {
			if (!item.hasScore()) 
				return null;
		}
		//
		if (current.size() == 1) {
			computingRankings();
			return null;
		}
		//
		ArrayList<Challenger> next = new ArrayList<>();
		for (int i = 0; i < current.size(); i += 2) {
			Challenger win = compareScore(current.get(i), current.get(i + 1));
			next.add(new Challenger(win.getTeamName()));
		}
		tournament.add(next);
		return null;
	}
	
	/**
	 * check if the game has finish
	 * @return
	 */
	public boolean finish() {
		ArrayList<Challenger> current = tournament.get(tournament.size() - 1);
		if (current.size() == 0)
			return true;
		if (current.size() == 1 && current.get(0).hasScore())
			return true;
		return false;
	}
	
	/**
	 * get the team number
	 * @return
	 */
	public int getTeamNumber() {
		return tournament.get(0).size();
	}
	
	/**
	 * draw line
	 * @param g
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param ex
	 * @param ey
	 */
	private void drawLine(GraphicsContext g, int x1, int y1, int x2, int y2, int ex, int ey) {
		g.strokeLine(x1, y1, ex, ey);
		g.strokeLine(x2, y2, ex, ey);
	}
	
	/**
	 * computing rankings
	 */
	private void computingRankings() {
		ArrayList<Challenger> current = tournament.get(tournament.size() - 1);
		if (current.size() != 1)
			return;
		if (!current.get(0).hasScore())
			return;
		Challenger challenger1nd = current.get(0);
		Challenger challenger2nd = null;
		Challenger challenger3nd = null;
		if (tournament.size() - 2 >= 0) {
			ArrayList<Challenger> temp = tournament.get(tournament.size() - 2);
			challenger2nd = temp.get(0).getTeamName().equals(challenger1nd.getTeamName()) ?
					temp.get(1) : temp.get(0);
		}
		if (tournament.size() - 3 >= 0) {
			ArrayList<Challenger> temp = tournament.get(tournament.size() - 3);
			for (Challenger item : temp) {
				if (item.getTeamName().equals(challenger1nd.getTeamName()))
					continue;
				if (item.getTeamName().equals(challenger2nd.getTeamName()))
					continue;
				if (challenger3nd == null)
					challenger3nd = item;
				else if (challenger3nd.getTeamScore() < item.getTeamScore())
					challenger3nd = item;
			}
		}
		challenger1nd.setHonor("CHAMPION");
		challenger2nd.setHonor("2nd Place");
		challenger3nd.setHonor("3nd Place");
	}
	
	/**
	 * compare score between two challengers
	 * @param challenger_1
	 * @param challenger_2
	 * @return
	 */
	private Challenger compareScore(Challenger challenger_1, Challenger challenger_2) {
		if (challenger_1.getTeamScore() > challenger_2.getTeamScore())
			return challenger_1;
		return challenger_2;
	}
}
