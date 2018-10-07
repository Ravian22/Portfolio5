import java.util.List;

public class ComputerPlayer extends Player {
	
	public static final int COMPUTER_SELECTION_NUMBER = 0;

	public ComputerPlayer() {
		super();
	}

	@Override
	public Domino play(Domino attachableEnds) {
		System.out.print("Ich: ");
		Domino playedDomino;
		List<Domino> possibleSelection = getFittingDominos(attachableEnds);
		
		if (possibleSelection.size() == COMPUTER_SELECTION_NUMBER) {
			playedDomino = null;
			System.out.println("ziehe");
		} else {
			playedDomino = possibleSelection.get(COMPUTER_SELECTION_NUMBER);
			System.out.println(playedDomino.toString());
			removeDomino(playedDomino);
		}
		return playedDomino;	
	}
}
