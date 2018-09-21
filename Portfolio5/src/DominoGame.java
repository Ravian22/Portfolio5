import java.util.ArrayList;
import java.util.List;

public class DominoGame {

	private DominoPool dominoPool;
	private Userdialog userDialog;
	private Domino uncoveredDomino;
	private List<Domino> dominos;
	private List<Player> players;

	public DominoGame() {
		dominos = new ArrayList<Domino>();
		players = new ArrayList<Player>();
	}

	public void play() {
		dominoPool = new DominoPool();
		userDialog = new Userdialog();
		dominos = dominoPool.provideShuffledDominoHeap();

		dealOutDomninos();

		while (gameRunning()) {
			uncoveredDomino = dominos.get(0);
			dominos.remove(0);
			for (Player player : players) {
				if (!player.isComputer()) {
					printPlayerMove(player);
					userDialog.getUserInput("Auswahlmöglichkeiten: ", player.showPossibleSelection(uncoveredDomino));
				}
			}
		}
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void dealOutDomninos() {
		for (int i = 0; i < players.size(); i++) {
			for (int counter = i*5; counter <(i*5+4);counter++) {
				players.get(i).addDomino(dominos.get(counter));
				dominos.remove(counter);
			}
		}
	}

	public void setUncoveredDomino(Domino domino) {
		if (uncoveredDomino.getRight() == domino.getLeft()) {
			uncoveredDomino = new Domino(domino.getLeft(), uncoveredDomino.getRight());
		}
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

	public void printPlayerMove(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
		System.out.print("Ihre Steine: ");
		System.out.println("[" + player.showAllPlayerDominos() + "]");
		System.out.println(player.showPossibleSelection(uncoveredDomino));

	}

	public void printComputerMove(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
	}
}
