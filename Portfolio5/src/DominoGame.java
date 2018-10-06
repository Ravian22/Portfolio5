import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DominoGame {

	private DominoPool dominoPool;
	private Domino attachableEnds;
	private List<Domino> dominos;
	private List<Player> players;
	private int numberOfComputerPlayers;
	private int numberOfHumanPlayers;
	private int numberOfRandomComputers;

	public DominoGame(int numberOfHumanPlayers, int numberOfComputerPlayers, int numberOfRandomComputers) {
		dominos = new LinkedList<Domino>();
		players = new ArrayList<Player>();
		this.numberOfComputerPlayers = numberOfComputerPlayers;
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		this.numberOfRandomComputers = numberOfRandomComputers;
		setPlayers();
	}

	private void setPlayers() {
		for (int i = 0; i < numberOfHumanPlayers; i++) {
			players.add(new HumanPlayer());
		}
		for (int i = 0; i < numberOfComputerPlayers; i++) {
			players.add(new ComputerPlayer());
		}
		for (int i = 0; i < numberOfRandomComputers; i++) {
			players.add(new RandomComputerPlayer());
		}
	}

	private void setupGame() {
		dominoPool = new DominoPool();

		for (Player player : players) {
			player.clearDominos();
		}
		try {
			dominos = dominoPool.provideShuffledDominoHeap();
			dealOutDomninos();
			attachableEnds = dominos.get(0);
			dominos.remove(0);
		} catch (NullPointerException e) {
			System.out.println("Keine Dominos im Spiel.");
		} catch (IndexOutOfBoundsException e) {
			// This error is possible if too many players are added to the game.
			System.out.println("Zu viele Spieler !");
			System.out.println("Es können höchstens 4 Spieler angelegt werden.");
		}

	}

	public void play() {
		setupGame();
		int counter = 0;
		try {
			while (gameRunning()) {
				Domino playedDomino;
				Player player = players.get(counter);

				printAttachableEnds(player);

				if (player.canPlay(attachableEnds)) {
					playedDomino = player.play(attachableEnds);
					if (playedDomino == null) {
						drawDomino(player);
					} else {
						if (playedDomino.fitsBothSides(attachableEnds)) {
							if (player.chooseSide() == 0) {
								attachableEnds = new Domino(playedDomino.getLeft(), attachableEnds.getRight());
							} else {
								attachableEnds = new Domino(attachableEnds.getLeft(), playedDomino.getRight());
							}
						} else {
							setAttachableEnds(playedDomino);
						}
					}
				} else {
					System.out.println("Keine Anlegemöglichkeit.");
					drawDomino(player);
				}
				if (counter == players.size() - 1) {
					counter = 0;
				} else {
					counter++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Zu wenig Spieler !");
			System.out.println("Es muss mindestens ein Spieler geben.");
		}
		printEndOfGame();

	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void dealOutDomninos() {
		for (Player player : players) {
			for (int i = 0; i < 5; i++) {
				player.addDomino(dominos.get(0));
				dominos.remove(0);
			}
		}
	}

	public void drawDomino(Player player) {
		if (!dominos.isEmpty()) {
			player.addDomino(dominos.get(0));
			dominos.remove(0);
		}
	}

	public void setAttachableEnds(Domino domino) {
		if (attachableEnds.getRight() == domino.getLeft()) {
			attachableEnds = new Domino(attachableEnds.getLeft(), domino.getRight());
		} else {
			attachableEnds = new Domino(domino.getLeft(), attachableEnds.getRight());
		}
	}

	public boolean gameRunning() {
		boolean gameRunning = true;
		int counter = 0;
		while (gameRunning && counter < players.size()) {
			gameRunning = players.get(counter).canPlay(attachableEnds);
			counter++;
		}
		boolean playing = gameRunning || !dominos.isEmpty();
		return playing;
	}

	public void printAttachableEnds(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(attachableEnds.toString());
	}

	public void printEndOfGame() {
		Userdialog userDialog = new Userdialog();

		System.out.println("Spielende");
		for (Player player : players) {
			player.updateDrawback();
			player.printPlayersDrawback();
		}
		int selectedInput = userDialog.getUserInput("Neues Spiel ?: ", "Nein", "Ja");
		if (selectedInput == 0) {
			System.out.println("Tschüss");
		} else {
			play();
		}
	}
}
