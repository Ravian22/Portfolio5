import java.util.Arrays;
import java.util.List;

public class HumanPlayer extends Player {

	public HumanPlayer() {
		super();
	}

	public Domino play(Domino attachableEnds) {
		System.out.println("Ihre Steine: " + Arrays.toString(showAllPlayerDominos()));
		Userdialog userDialog = new Userdialog();
		Domino playedDomino;
		List<Domino> possibleSelection = getFittingDominos(attachableEnds);
		int selectedInput = userDialog.getUserInput("Auswahlmölichkeiten: ", showPossibleSelection(attachableEnds));
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
