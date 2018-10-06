import java.util.List;
import java.util.Random;


public class ComputerPlayer extends Player {

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
		
		selectedInput = 0;
		if (selectedInput == possibleSelection.size() ) {
			playedDomino = null;
			System.out.println("ziehe");
		} else {
			playedDomino = possibleSelection.get(selectedInput);
			System.out.println(playedDomino.toString());
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
