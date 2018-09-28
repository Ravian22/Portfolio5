import java.util.Arrays;
import java.util.List;

public class HumanPlayer extends Player {

	public HumanPlayer() {
		super(false);
	}

	public Domino play(Domino uncoveredDomino) {
		System.out.println("Ihre Steine: " + Arrays.toString(showAllPlayerDominos()));
		Userdialog userDialog = new Userdialog();
		Domino playedDomino;
		List<Domino> possibleSelection = getFittingDominos(uncoveredDomino);
		int selectedInput = userDialog.getUserInput("Auswahlmölichkeiten: ", showPossibleSelection(uncoveredDomino));
		if (selectedInput == possibleSelection.size() ) {
			playedDomino = null;
		} else {
			playedDomino = possibleSelection.get(selectedInput);
			playersDominos.remove(playedDomino);
		}
		return playedDomino;
	}
	
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
