package application;

public class Games {
	public Games () {
		
	}
	public String compareScore(String team1,String team2) {
		Challenger challenger1 = new Challenger(team1,1); ///change the second param to teamrank
		Challenger challenger2 = new Challenger(team2,2);
		
		int result = challenger1.getTeamScore().compareTo(challenger2.getTeamScore());
		
		if ( result < 0 ) {
			return team1;
		}
		else  { ///result > 0
			return team2;
		}
	}
}
