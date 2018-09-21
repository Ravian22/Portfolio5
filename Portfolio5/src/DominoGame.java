import java.util.List;

public class DominoGame {
	
	private DominoPool dominoPool;
	private Userdialog userDialog;
	private Domino uncoveredDomino;
	private List<Domino> dominos;
	private List<Player> players;
	
	public void play() {
		dominoPool = new DominoPool();
		userDialog = new Userdialog();
		dominos = dominoPool.provideShuffledDominoHeap();
		
		dealOutDomninos();
		
		while (gameRunning()) {
			uncoveredDomino = dominos.get(0);
			
		}
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void dealOutDomninos() {
		for(Player player: players) {
			for (int counter = players.indexOf(player) *5 ; counter < counter + 4; counter++) {
				player.addDomino(dominos.get(counter));
				dominos.remove(counter);
			}
		}
	}
	
	public void setUncoveredDomino() {
		
	}
	
	public boolean gameRunning() {
		boolean gameRunning = true;
		int counter = 0;
		while (gameRunning && counter < players.size()) {
			gameRunning = players.get(counter).hasDominos();
			counter++;
		}
		return gameRunning;
	}
	
	
	
	public void printCurrentMove() {
		System.out.print("Auswahlmöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
		System.out.print("Ihre Steine: ");
		
	}
	
	public boolean possibleSelection(Domino uncoveredDomino, Domino domino) {
		boolean possibleSelection = false;
		if (domino.getRight() == uncoveredDomino.getLeft() || domino.getLeft() == uncoveredDomino.getRight()) {
			possibleSelection = true;
		}
		return possibleSelection;
	}
}
