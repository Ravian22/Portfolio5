
public class Main {

	public static void main(String[] args) {
		DominoGame game = new DominoGame();
		Player humanPlayer = new HumanPlayer(false);
		Player computerPlayer = new ComputerPlayer(true);
		game.addPlayer(humanPlayer);
		game.addPlayer(computerPlayer);
		game.play();
	}

}
