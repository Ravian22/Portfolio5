import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DominoGame {

	public static final int NUMBER_FIT_DOMINO_LEFT = 0;
	public static final int NUMBER_FIT_DOMINO_RIGHT = 1;
	private DominoPool dominoPool;
	private Userdialog userDialog;
	private Domino uncoveredDomino;
	private List<Domino> dominos;
	private List<Player> players;
	private int numberOfComputerPlayers;
	private int numberOfHumanPlayers;

	public DominoGame(int numberOfComputerPlayers, int numberOfHumanPlayers, boolean randomComputer) {
		dominos = new ArrayList<Domino>();
		players = new ArrayList<Player>();
		this.numberOfComputerPlayers = numberOfComputerPlayers;
		this.numberOfHumanPlayers = numberOfHumanPlayers;
		setPlayers(randomComputer);
	}

	// Just for testing at the moment.
	public void setDominos(List<Domino> dominos) {
		this.dominos = dominos;
	}

	// Just for testing at the moment.
	public List<Domino> getDominos() {
		return dominos;
	}

	public void setPlayers(boolean randomComputer) {
		for (int i = 0; i < numberOfHumanPlayers; i++) {
			players.add(new HumanPlayer());
		}
		for (int i = 0; i < numberOfComputerPlayers; i++) {
			players.add(new ComputerPlayer(randomComputer));
		}
	}

	public void setupGame() {
		dominoPool = new DominoPool();
		userDialog = new Userdialog();
		dominos = dominoPool.provideShuffledDominoHeap();

		for (Player player : players) {
			player.clearDominos();
		}
		try {
			dealOutDomninos();
			uncoveredDomino = dominos.get(0);
			dominos.remove(0);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			System.out.println("Zu viele Spieler !");
			System.out.println("Es können höchstens 4 Spieler angelegt werden.");
		}

	}

	public void play() {
		setupGame();
		int counter = 0;
		while (gameRunning()) {
			Domino playedDomino;
			Player player = players.get(counter);

			printPlay(player);
			
			// For Debug
			System.out.println(dominos.size());
			if (player.canPlay(uncoveredDomino)) {
				playedDomino = player.play(uncoveredDomino);
				if (playedDomino == null) {
					drawDomino(player);
				} else {
					setUncoveredDomino(playedDomino);
				}
			} else {
				System.out.println("Keine Anlegemöglichkeit.");
				drawDomino(player);
			}
			if(counter == players.size()-1) {
				counter = 0;
			} else {
				counter++;
			}
		}
		processEndOfGame();
		showEndOfGame();
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
		if (uncoveredDomino.getRight() == domino.getLeft()) {
			uncoveredDomino = new Domino(uncoveredDomino.getLeft(), domino.getRight());
		} else {
			uncoveredDomino = new Domino(domino.getLeft(), uncoveredDomino.getRight());
		}
	}

	public boolean gameRunning() {
		boolean gameRunning = true;
		int counter = 0;
		while (gameRunning && counter < players.size()) {
			gameRunning = players.get(counter).canPlay(uncoveredDomino);
			counter++;
		}
		boolean playing = gameRunning || !dominos.isEmpty();
		return playing;
	}

	public void printPlay(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
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

	public void showEndOfGame() {
		String[] newGame = new String[2];
		newGame[0] = "Nein";
		newGame[1] = "Ja";

		System.out.println("Spielende");
		for (Player player : players) {
			if (player.isComputer()) {
				System.out.print("Ich: ");
				System.out.println(Arrays.toString(player.showAllPlayerDominos()));
				System.out.println("Meine Minuspunkte: " + player.getPlayersDrawback());
			} else {
				System.out.print("Sie: ");
				System.out.println(Arrays.toString(player.showAllPlayerDominos()));
				System.out.println("Ihre Minuspunkte: " + player.getPlayersDrawback());
			}
		}
		int selectedInput = userDialog.getUserInput("Neues Spiel ?: ", newGame);
		if (selectedInput == 0) {
			System.out.println("Tschüss");
		} else {
			play();
		}
	}
}
