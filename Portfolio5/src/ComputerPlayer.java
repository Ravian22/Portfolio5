import java.util.List;

public class ComputerPlayer extends Player {

	public ComputerPlayer(boolean random) {
		super();
	}

	@Override
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

	/**
	 * The computer Player will always choose the first side to attach the domino.
	 */
	@Override
	public int chooseSide() {
		return 0;
	}
}
