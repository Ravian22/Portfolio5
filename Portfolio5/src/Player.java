import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Player {

	private List<Domino> playersDominos;
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
	
	public void removeDomino(Domino domino) {
		playersDominos.remove(domino);
	}

	public List<Domino> getPlayersDominos() {
		return playersDominos;
	}
	
	public void clearDominos() {
		playersDominos.clear();
	}

	public int getPlayersDrawback() {
		return playersDrawback;
	}

	public void setPlayersDrawback(int playersDrawback) {
		this.playersDrawback = playersDrawback;
	}
	
	public boolean canPlay(Domino uncoveredDomino) {
		return !getFittingDominos(uncoveredDomino).isEmpty();
	}
	
	public void printPlayersDrawback() {
		System.out.print("Ich: ");
		System.out.println(Arrays.toString(showAllPlayerDominos()));
		System.out.println("Meine Minuspunkte: " + getPlayersDrawback());
	}

	protected String[] showPossibleSelection(Domino attachableEnds) {
		List<Domino> possibleSelections = getFittingDominos(attachableEnds);
		String[] selectionString = new String[possibleSelections.size() + 1];

		for (int i = 0; i < possibleSelections.size(); i++) {
			selectionString[i] = possibleSelections.get(i).toString();
		}
		selectionString[selectionString.length - 1] = "ziehen";
		return selectionString;
	}

	/**
	 * This method returns all dominos which fits the uncovered domino.
	 * 
	 * @param attachableEnds
	 * @return List<Domino> possibleSelections
	 */
	protected List<Domino> getFittingDominos(Domino attachableEnds) {
		List<Domino> possibleSelections = new ArrayList<Domino>();
		try {
			for (Domino domino : playersDominos) {
				if (attachableEnds.fitsDomino(domino)) {
					possibleSelections.add(domino);
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Es wurde kein offen aufgedeckter Stein gefunden.");
		}
		return possibleSelections;
	}

	public String[] showAllPlayerDominos() {
		String[] allPlayerDominos = new String[playersDominos.size()];
		for (int i = 0; i < playersDominos.size(); i++) {
			allPlayerDominos[i] = playersDominos.get(i).toString();
		}
		return allPlayerDominos;
	}
}
