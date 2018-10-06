import java.util.List;
import java.util.Random;


public class ComputerPlayer extends Player {

	public static final int RANDOM_NUMBER_MIN_VALUE = 0;
	private boolean choosesRandom;

	public ComputerPlayer(boolean random) {
		super();
		choosesRandom = random;
	}

	public boolean isRandom() {
		return choosesRandom;
	}

	public Domino play(Domino attachableEnds) {
		System.out.print("Ich: ");
		Domino playedDomino;
		List<Domino> possibleSelection = getFittingDominos(attachableEnds);
		int selectedInput;
		Random randomGenerator = new Random();
		
		if(isRandom()) {
			if(canPlay(attachableEnds)) {
				selectedInput = randomGenerator.nextInt(possibleSelection.size()+1);
				System.out.println("Nummer: " + selectedInput);
			} else {
				selectedInput = 0;
			}
		} else {
			selectedInput = 0;
		}
		if (selectedInput == possibleSelection.size() ) {
			playedDomino = null;
			System.out.println("ziehe");
		} else {
			playedDomino = possibleSelection.get(selectedInput);
			System.out.println(possibleSelection.get(selectedInput).toString());
			playersDominos.remove(playedDomino);
		}
		return playedDomino;	
	}

	@Override
	public int chooseSide() {
		// TODO Auto-generated method stub
		return 0;
	}
}
