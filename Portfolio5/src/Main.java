
public class Main {

	public static void main(String[] args) {
		DominoGame game = new DominoGame(0,1,false);
		ComputerPlayer player = new ComputerPlayer(false);
		game.addPlayer(player);
		game.play();
	}
}
