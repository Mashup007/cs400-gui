package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

/**
 * the class of game
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
		int level = (int)(Math.log(getTeamNumber()) / Math.log(2)) * 2 - 1;
		// draw information
		for (int i = 0; i < tournament.size(); i++) {
			ArrayList<Challenger> challengers = tournament.get(i);
			if (challengers.size() == 1 && i != 0)
				break;
			for (int j = 0; j < challengers.size(); j++) {
				Challenger challenger = challengers.get(j);
				int split = (challengers.size() / 2 == 0 ? 1 : challengers.size() / 2);
				int y = (j % split)  * 	((int)Math.pow(2, i) * 100);
				int x = j >= split ? (level - i) * 150 : i * 150;
				if (i != 0)
					y += ((int)Math.pow(2, i) * 100) / 2 - 50;
				challenger.draw(g, x,  y);
			}
		}
		// draw line
		for (int i = 0; i < tournament.size(); i++) {
			ArrayList<Challenger> challengers = tournament.get(i);
			if (challengers.size() == 1 || i + 1 == tournament.size())
				continue;
			if (challengers.size() == 2) {
				g.strokeLine(challengers.get(0).getDrawX() + 100, challengers.get(0).getDrawY() + 25,
						challengers.get(1).getDrawX(), challengers.get(1).getDrawY() + 25);
				continue;
			}
			ArrayList<Challenger> nextchallengers = tournament.get(i + 1);
			for (int j = 0; j < challengers.size() / 2; j += 2) {
				drawLine(g, challengers.get(j).getDrawX() + 100, challengers.get(j).getDrawY() + 25,
						challengers.get(j + 1).getDrawX() + 100, challengers.get(j + 1).getDrawY() + 25,
						nextchallengers.get(j / 2).getDrawX(), nextchallengers.get(j / 2).getDrawY() + 25);
			}
			for (int j = challengers.size() / 2; j < challengers.size(); j += 2) {
				drawLine(g, challengers.get(j).getDrawX(), challengers.get(j).getDrawY() + 25,
						challengers.get(j + 1).getDrawX(), challengers.get(j + 1).getDrawY() + 25,
						nextchallengers.get(j / 2).getDrawX() + 100, nextchallengers.get(j / 2).getDrawY() + 25);
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
		challenger1nd.setHonor("CHAMPION");
		Challenger challenger2nd = null;
		Challenger challenger3nd = null;
		if (tournament.size() - 2 >= 0) {
			ArrayList<Challenger> temp = tournament.get(tournament.size() - 2);
			challenger2nd = temp.get(0).getTeamName().equals(challenger1nd.getTeamName()) ?
					temp.get(1) : temp.get(0);
			challenger1nd = temp.get(0).getTeamName().equals(challenger1nd.getTeamName()) ?
							temp.get(0) : temp.get(1);
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
		if (challenger2nd != null)
			challenger2nd.setHonor("2nd Place");
		if (challenger3nd != null)
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
