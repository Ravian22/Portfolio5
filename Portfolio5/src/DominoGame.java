import java.util.ArrayList;
import java.util.Arrays;
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
		dominos = new ArrayList<Domino>();
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
		dominos = dominoPool.provideShuffledDominoHeap();

		for (Player player : players) {
			player.clearDominos();
		}
		try {
			dealOutDomninos();
			attachableEnds = dominos.get(0);
			dominos.remove(0);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
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

				print(player);
				
				if (player.canPlay(attachableEnds)) {
					playedDomino = player.play(attachableEnds);
					if (playedDomino == null) {
						drawDomino(player);
					} else {
						setUncoveredDomino(playedDomino);
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
		processEndOfGame();
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

	public void setUncoveredDomino(Domino domino) {
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

	public void print(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(attachableEnds.toString());
	}

	public void processEndOfGame() {
		for (Player player : players) {
			int drawback = player.getPlayersDrawback();
			for (Domino domino : player.getPlayersDominos()) {
				drawback += domino.getLeft();
				drawback += domino.getRight();
			}
			player.setPlayersDrawback(drawback);
		}
	}

	public void printEndOfGame() {
		Userdialog userDialog = new Userdialog();

		System.out.println("Spielende");
		for (Player player : players) {
			player.printPlayersDrawback();
		}
		int selectedInput = userDialog.getUserInput("Neues Spiel ?: ", "Nein","Ja");
		if (selectedInput == 0) {
			System.out.println("Tschüss");
		} else {
			play();
		}
	}
}
