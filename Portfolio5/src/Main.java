
public class Main {

	public static void main(String[] args) {
		DominoGame game = new DominoGame(1,0);
		ComputerPlayer player = new ComputerPlayer(true);
		game.addPlayer(player);
		game.play();
	}
}
