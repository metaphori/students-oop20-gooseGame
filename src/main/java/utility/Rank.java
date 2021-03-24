package utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import model.Player;

public class Rank {
	
	private final List<Player> ranking = new ArrayList<>();

	public Rank(final List<Player> listPlayer) {
		for (final Player p : listPlayer) {
			ranking.add(p);
		}
	}

	public void updateRanking() {
		ranking.sort(Comparator.comparing(Player::getBoardPosition).reversed());
	}
	
	
	public List<Player> getRanking() {
		return ranking;
	}
}