import java.util.List;
import java.util.Random;


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

	public Domino play(Domino uncoveredDomino) {
		System.out.print("Ich: ");
		Domino playedDomino;
		List<Domino> possibleSelection = getPossibleSelection(uncoveredDomino);
		int selectedInput;
		Random random = new Random();
		
		if(isRandom()) {
			if(canPlay(uncoveredDomino)) {
				selectedInput = random.nextInt(possibleSelection.size());
			} else {
				selectedInput = 0;
			}
		} else {
			selectedInput = 0;
		}
		if (selectedInput >= possibleSelection.size() ) {
			playedDomino = null;
			System.out.println("ziehe");
		} else {
			playedDomino = possibleSelection.get(selectedInput);
			System.out.println(possibleSelection.get(selectedInput).showDomino());
			playersDominos.remove(playedDomino);
		}
		return playedDomino;	
	}

	@Override
	public String[] showPossibleSelection(Domino uncoveredDomino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int chooseSide() {
		// TODO Auto-generated method stub
		return 0;
	}
}
