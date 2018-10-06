import java.util.Arrays;
import java.util.List;

public class HumanPlayer extends Player {

	public HumanPlayer() {
		super();
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
		String[] chooseSide = new String[2];
		chooseSide[0] = "links anlegen";
		chooseSide[1] = "rechts anlegen";
		System.out.println("Auswahlmöglichkeiten: ");
		int side = userDialog.getUserInput("Auswahlmölichkeiten: ", chooseSide);
		return side;
	}
}
