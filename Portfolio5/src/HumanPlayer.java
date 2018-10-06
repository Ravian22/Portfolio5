import java.util.Arrays;
import java.util.List;

public class HumanPlayer extends Player {

	public HumanPlayer() {
		super();
	}
	
	@Override
	public void printPlayersDrawback() {
		System.out.print("Sie: ");
		System.out.println(Arrays.toString(showAllPlayerDominos()));
		System.out.println("Ihre Minuspunkte: " + getPlayersDrawback());
	}

	@Override
	public Domino play(Domino attachableEnds) {
		System.out.println("Ihre Steine: " + Arrays.toString(showAllPlayerDominos()));
		Userdialog userDialog = new Userdialog();
		Domino playedDomino;
		List<Domino> possibleSelection = getFittingDominos(attachableEnds);
		int selectedInput = userDialog.getUserInput("Auswahlmölichkeiten: ", showPossibleSelection(attachableEnds));
		if (selectedInput == possibleSelection.size()) {
			playedDomino = null;
		} else {
			playedDomino = possibleSelection.get(selectedInput);
			removeDomino(playedDomino);
		}
		return playedDomino;
	}

	/*
	 * The human player can choose on which side he wants to attach the domino, if
	 * it fits both sides of the domnio in the middle.
	 */
	@Override
	public int chooseSide() {
		Userdialog userDialog = new Userdialog();
		int side = userDialog.getUserInput("Auswahlmölichkeiten: ", "links anlegen","rechts anlegen");
		return side;
	}
}
