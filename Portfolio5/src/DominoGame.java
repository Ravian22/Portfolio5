import java.util.ArrayList;
import java.util.List;

public class DominoGame {

	private DominoPool dominoPool;
	private Userdialog userDialog;
	private Domino uncoveredDomino;
	private List<Domino> dominos;
	private List<HumanPlayer> humanPlayers;
	private List<ComputerPlayer> computerPlayers;

	public DominoGame() {
		dominos = new ArrayList<Domino>();
		humanPlayers = new ArrayList<HumanPlayer>();
	}

	public void play() {
		dominoPool = new DominoPool();
		userDialog = new Userdialog();
		dominos = dominoPool.provideShuffledDominoHeap();

		dealOutDomninos();
		uncoveredDomino = dominos.get(0);
		dominos.remove(0);
		while (gameRunning()) {
			for (Player player : humanPlayers) {
					printPlayerMove(player);
					int selectedDomino = userDialog.getUserInput("Auswahlmöglichkeiten: ", 
										player.showPossibleSelection(uncoveredDomino));
					if (player.showPossibleSelection(uncoveredDomino)[selectedDomino].equals("ziehen")) {
						
					}
					player.getPlayersDominos().remove(selectedDomino);
					setUncoveredDomino(selectedDomino);
				}
			}
		}
	}

	public void addHumanPlayer(HumanPlayer player) {
		humanPlayers.add(player);
	}

	public void dealOutDomninos() {
		for (int i = 0; i < humanPlayers.size(); i++) {
			for (int counter = i*5; counter <(i*5+5);counter++) {
				humanPlayers.get(i).addDomino(dominos.get(counter));
				dominos.remove(counter);
			}
		}
	}
	
	public void takeOneDomino(Player player) {
		player.addDomino(dominos.get(0));
		dominos.remove(0);
	}

	public void setUncoveredDomino(Domino domino) {
		if (uncoveredDomino.getRight() == domino.getLeft()) {
			uncoveredDomino = new Domino(uncoveredDomino.getLeft(), domino.getRight());
		} else {
			uncoveredDomino = new Domino(domino.getLeft(), uncoveredDomino.getRight());
		}
	}

	public boolean gameRunning() {
		boolean gameRunning = true;
		int counter = 0;
		while (gameRunning && counter < humanPlayers.size()) {
			gameRunning = humanPlayers.get(counter).hasDominos();
			counter++;
		}
		return gameRunning;
	}

	public void printPlayerMove(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
		System.out.print("Ihre Steine: ");
		System.out.println("[" + player.showAllPlayerDominos() + "]");
	}

	public void printComputerMove(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
	}
}
