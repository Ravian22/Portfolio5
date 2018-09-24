import java.util.List;

public class HumanPlayer extends Player {

	public HumanPlayer() {
		super(false);
	}

	public String[] showPossibleSelection(Domino uncoveredDomino) {

		List<Domino> possibleSelections = getPossibleSelection(uncoveredDomino);
		String[] selectionString = new String[getPossibleSelection(uncoveredDomino).size()];

		for (int i = 0; i < possibleSelections.size(); i++) {
			if (possibleSelections.get(i) == null) {
				selectionString[i] = "ziehen";
			} else {
				selectionString[i] = possibleSelections.get(i).showDomino();
			}
		}
		return selectionString;
	}

	public Domino selectDomino(int selectedNumber, Domino uncoveredDomino) {
		Domino selectedDomino = getPossibleSelection(uncoveredDomino).get(selectedNumber);
		playersDominos.remove(selectedDomino);
		return selectedDomino;
	}

	@Override
	public Domino play(Domino uncoveredDomino) {
		Userdialog userDialog = new Userdialog();
		Domino playedDomino;
		List<Domino> possibleSelection = getPossibleSelection(uncoveredDomino);
		int selectedInput = userDialog.getUserInput("Auswahlmölichkeiten: ", showPossibleSelection(uncoveredDomino));
		if (possibleSelection.get(selectedInput) == null) {
			playedDomino = null;
		} else {
			playedDomino = possibleSelection.get(selectedInput);
		}
		return playedDomino;
	}
	
	public int chooseSide() {
		Userdialog userDialog = new Userdialog();
		String[] chooseSide = new String[2];
		chooseSide[0] = "links anlegen";
		chooseSide[0] = "rechts anlegen";
		System.out.println("Auswahlmöglichkeiten: ");
		int side = userDialog.getUserInput("Auswahlmölichkeiten: ", chooseSide);
		return side;
	}
}
