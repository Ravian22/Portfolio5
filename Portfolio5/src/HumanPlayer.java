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
}
