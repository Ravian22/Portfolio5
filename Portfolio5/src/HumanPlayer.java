import java.util.List;

public class HumanPlayer extends Player{
	
	public HumanPlayer(boolean isComputer) {
		super(isComputer);
	}	

	public String showPossibleSelection(Domino uncoveredDomino) {
		StringBuilder playerBuilder = new StringBuilder();
		List<Domino> possibleSelections = getPossibleSelection(uncoveredDomino);
		for (int i=0; i < possibleSelections.size(); i++) {
			playerBuilder.append("(");
			playerBuilder.append(i);
			playerBuilder.append(") ");
			playerBuilder.append(possibleSelections.get(i).showDomino());
		}
		return playerBuilder.toString();
	}
}
