import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player {

	public static final int RANDOM_NUMBER_MIN_VALUE = 0;
	private boolean chooseRandom;

	public ComputerPlayer(boolean random) {
		super(true);
		chooseRandom = random;
	}

	public boolean isRandom() {
		return chooseRandom;
	}

	public Domino selectDomino(int selectedNumber, Domino uncoveredDomino) {
		Domino selectedDomino;
		List<Domino> possibleSelection = getPossibleSelection(uncoveredDomino);

		if (isRandom()) {
			selectedNumber = ThreadLocalRandom.current().nextInt(RANDOM_NUMBER_MIN_VALUE, possibleSelection.size());
		} else {
			selectedNumber = 0;
		}
		selectedDomino = possibleSelection.get(selectedNumber);
		if (playersDominos.contains(selectedDomino)) {
			playersDominos.remove(selectedDomino);
		}
		return selectedDomino;
	}

	@Override
	public String[] showPossibleSelection(Domino uncoveredDomino) {
		return null;
	}

	@Override
	public Domino play(Domino uncoveredDomino) {
		int selectedNumber;
		List<Domino> possibleSelection = getPossibleSelection(uncoveredDomino);
		
		if(isRandom()) {
			selectedNumber = ThreadLocalRandom.current().nextInt(RANDOM_NUMBER_MIN_VALUE, possibleSelection.size());
		} else {
			selectedNumber = 0;
		}
		if (possibleSelection.get(selectedNumber) == null) {
			System.out.println("ziehe");
		} else {
			System.out.println(possibleSelection.get(selectedNumber).showDomino());
		}
		return possibleSelection.get(selectedNumber);
	}

	@Override
	public int chooseSide() {
		// TODO Auto-generated method stub
		return 0;
	}

}
