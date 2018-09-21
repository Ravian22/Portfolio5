import java.util.List;

public class DominoGame {
	
	private DominoPool dominoPool;
	private Userdialog userDialog;
	private List<Domino> dominos;
	private List<Player> players;
	
	public void play() {
		dominoPool = new DominoPool();
		userDialog = new Userdialog();
		dominos = dominoPool.provideShuffledDominoHeap();
		for (Domino domino:dominos) {
			System.out.println(domino);
		}
		
	}
	
	public void dealOutDomninos() {
		for(Player player: players) {
			for (int counter = players.indexOf(player) *5 ; counter < counter + 4; counter++) {
				player.addDomino(dominos.get(counter));
			}
		}
	}

}
