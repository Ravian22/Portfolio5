import java.util.ArrayList;
import java.util.List;

public abstract class Player {

	private List<Domino> playersDominos;
	private boolean isComputer;

	public Player(boolean isComputer) {
		playersDominos = new ArrayList<Domino>();
		this.isComputer = isComputer;
	}
	
	public void addDomino(Domino domino) {
		playersDominos.add(domino);
	}
	
	public List<Domino> getPlayersDominos() {
		return playersDominos;
	}
	
	public boolean isComputer() {
		return isComputer;
	}
	
	public boolean hasDominos() {
		boolean hasDominos = true;
		if (playersDominos.size() == 0) {
			hasDominos = false;
		}
		return hasDominos;
	}
	
	public List<Domino> getPossibleSelection(Domino uncoveredDomino) {
		List<Domino> possibleSelections = new ArrayList<Domino>();
		for(Domino domino:playersDominos ) {
			if (uncoveredDomino.getLeft() == domino.getRight() || uncoveredDomino.getRight() == domino.getLeft()) {
				possibleSelections.add(domino);
			}
		}
		return possibleSelections;
	}
	
	public String showAllPlayerDominos() {
		StringBuilder playerBuilder = new StringBuilder();
		if (!isComputer) {
			for (Domino domino: playersDominos) {
				playerBuilder.append(domino.showDomino());
				playerBuilder.append(", ");
			}
		}
		return playerBuilder.toString();
	}

	public String[] showPossibleSelection(Domino uncoveredDomino) {
		return null;
	}
}
