import java.util.List;

public class HumanPlayer extends Player {

	public HumanPlayer() {
		super(false);
	}

	public String[] showPossibleSelection(Domino uncoveredDomino) {

		List<Domino> possibleSelections = getPossibleSelection(uncoveredDomino);
		String[] selectionString = new String[getPossibleSelection(uncoveredDomino).size()+1];

		for (int i = 0; i < possibleSelections.size(); i++) {
				selectionString[i] = possibleSelections.get(i).showDomino();
			}
		selectionString[selectionString.length -1] ="ziehen";
		return selectionString;
	}

	public Domino play(Domino uncoveredDomino) {
		Userdialog userDialog = new Userdialog();
		Domino playedDomino;
		List<Domino> possibleSelection = getPossibleSelection(uncoveredDomino);
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
