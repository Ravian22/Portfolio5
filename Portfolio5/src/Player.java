import java.util.ArrayList;
import java.util.List;

public abstract class Player {

	protected List<Domino> playersDominos;
	private int playersDrawback;

	public Player() {
		playersDominos = new ArrayList<Domino>();
		playersDrawback = 0;
	}

	public abstract Domino play(Domino attachableEnds);

	public abstract int chooseSide();

	public void addDomino(Domino domino) {
		playersDominos.add(domino);
	}

	public List<Domino> getPlayersDominos() {
		return playersDominos;
	}

	public int getPlayersDrawback() {
		return playersDrawback;
	}

	public void setPlayersDrawback(int playersDrawback) {
		this.playersDrawback = playersDrawback;
	}

	public boolean hasDominos() {
		boolean hasDominos = true;
		if (playersDominos.isEmpty()) {
			hasDominos = false;
		}
		return hasDominos;
	}

	public String[] showPossibleSelection(Domino uncoveredDomino) {
		List<Domino> possibleSelections = getFittingDominos(uncoveredDomino);
		String[] selectionString = new String[possibleSelections.size() + 1];

		for (int i = 0; i < possibleSelections.size(); i++) {
			selectionString[i] = possibleSelections.get(i).toString();
		}
		selectionString[selectionString.length - 1] = "ziehen";
		return selectionString;
	}

	/**
	 * This method returns all dominos which fits the uncovered domino in the and
	 * are playable.
	 * 
	 * @param uncoveredDomino
	 * @return List<Domino> possibleSelections
	 */
	public List<Domino> getFittingDominos(Domino uncoveredDomino) {
		List<Domino> possibleSelections = new ArrayList<Domino>();
		try {
			for (Domino domino : playersDominos) {
				if (uncoveredDomino.fitsDomino(domino)) {
					possibleSelections.add(domino);
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Es wurde kein offen aufgedeckter Stein gefunden.");
		}
		return possibleSelections;
	}

	public boolean canPlay(Domino uncoveredDomino) {
		return !getFittingDominos(uncoveredDomino).isEmpty();
	}

	public String[] showAllPlayerDominos() {
		String[] allPlayerDominos = new String[playersDominos.size()];
		for (int i = 0; i < playersDominos.size(); i++) {
			allPlayerDominos[i] = playersDominos.get(i).toString();
		}
		return allPlayerDominos;
	}

	public void clearDominos() {
		playersDominos.clear();
	}

}
