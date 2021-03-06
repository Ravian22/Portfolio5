import java.util.List;
import java.util.Random;

public class RandomComputerPlayer extends Player {

	@Override
	public Domino play(Domino attachableEnds) {
		System.out.print("Ich: ");
		Domino playedDomino;
		List<Domino> possibleSelection = getFittingDominos(attachableEnds);
		int selectedInput;
		Random randomGenerator = new Random();

		if (canPlay(attachableEnds)) {
			selectedInput = randomGenerator.nextInt(possibleSelection.size() + 1);
		} else {
			selectedInput = 0;
		}
		if (selectedInput == possibleSelection.size()) {
			playedDomino = null;
			System.out.println("ziehe");
		} else {
			playedDomino = possibleSelection.get(selectedInput);
			System.out.println(playedDomino.toString());
			removeDomino(playedDomino);
		}
		return playedDomino;
	}
}
