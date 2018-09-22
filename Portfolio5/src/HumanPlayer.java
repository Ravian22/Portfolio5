import java.util.List;

public class HumanPlayer extends Player{
	
	public HumanPlayer(boolean isComputer) {
		super(isComputer);
	}	

	public String[] showPossibleSelection(Domino uncoveredDomino) {
		
		List<Domino> possibleSelections = getPossibleSelection(uncoveredDomino);
		String[] selectionString = new String[getPossibleSelection(uncoveredDomino).size()+1];
		
		for (int i=0; i < possibleSelections.size(); i++) {
			selectionString[i] = possibleSelections.get(i).showDomino();
		}
		selectionString[selectionString.length-1] = "ziehen";
		return selectionString;
	}
}
