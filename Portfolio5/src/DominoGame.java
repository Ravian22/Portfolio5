import java.util.ArrayList;
import java.util.Arrays;
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
	
	// Just for testing at the moment.
	public void setDominos(List<Domino> dominos) {
		this.dominos = dominos;
	}
	
	// Just for testing at the moment.
	public List<Domino> getDominos() {
		return dominos;
	}

	public void setupGame() {
		dominoPool = new DominoPool();
		userDialog = new Userdialog();
		dominos = dominoPool.provideShuffledDominoHeap();

		for (Player player : players) {
			player.clearDominos();
		}
		dealOutDomninos();

		uncoveredDomino = dominos.get(0);
		dominos.remove(0);
	}

	public void play() {
		setupGame();
		int counter = 0;
		while (gameRunning() && !drawGame()) {

			List<Domino> possibleSelection = players.get(counter).getPossibleSelection(uncoveredDomino);

			if (!players.get(counter).isComputer()) {
				printPlayerMove(players.get(counter));
				humanPlays(players.get(counter), possibleSelection);
			} else {
				printComputerMove(players.get(counter));
				computerPlays(players.get(counter), possibleSelection);
			}
			if (counter == players.size() - 1) {
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
		for(Player player: players) {
			for(int i= 0; i<5; i++) {
				player.addDomino(dominos.get(0));
				dominos.remove(0);
			}
		}
	}

	public void humanPlays(Player player, List<Domino> possibleSelection) {
		Domino selectedDomino;
		int selectedInput = userDialog.getUserInput("Auswahlmölichkeiten: ",
				player.showPossibleSelection(uncoveredDomino));
		if (possibleSelection.get(selectedInput) == null) {
			drawDomino(player);
		} else {
			selectedDomino = player.selectDomino(selectedInput, uncoveredDomino);
			if (selectedDomino.fitsBothSides(uncoveredDomino)) {
				String[] chooseSide = new String[2];
				chooseSide[0] = "links anlegen";
				chooseSide[1] = "rechts anlegen";
				selectedInput = userDialog.getUserInput("Auswahlmölichkeiten: ", chooseSide);
				if (selectedInput == 0) {
					uncoveredDomino = new Domino(selectedDomino.getLeft(), uncoveredDomino.getRight());
				} else {
					uncoveredDomino = new Domino(selectedDomino.getRight(), uncoveredDomino.getLeft());
				}
			} else {
				setUncoveredDomino(selectedDomino);
			}
		}
	}

	public void computerPlays(Player player, List<Domino> possibleSelection) {
		Domino selectedDomino;
		selectedDomino = player.selectDomino(0, uncoveredDomino);
		if (selectedDomino == null) {
			drawDomino(player);
			System.out.println("ziehe");
		} else {
			System.out.println(selectedDomino.showDomino());
			setUncoveredDomino(selectedDomino);
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
			gameRunning = players.get(counter).hasDominos();
			counter++;
		}
		boolean playing = gameRunning || !dominos.isEmpty();
		return playing;
	}

	public boolean drawGame() {
		boolean draw = true;
		int counter = 0;
		while (draw && counter < players.size()) {
			if (players.get(counter).canPlay(uncoveredDomino) || !dominos.isEmpty()) {
				draw = false;
			} else {
				draw = true;
				counter++;
			}
		}
		return draw;
	}

	public void printPlayerMove(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
		System.out.print("Ihre Steine: ");
		System.out.println(Arrays.toString(player.showAllPlayerDominos()));
		System.out.println("Steine im Spiel: " + dominos.size());
	}

	public void printComputerMove(Player player) {
		System.out.print("Anlegemöglichkeit: ");
		System.out.println(uncoveredDomino.showDomino());
		System.out.print("Ich: ");
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
		if (drawGame()) {
			System.out.println(uncoveredDomino.showDomino());
			System.out.println("Keine weiteren Züge möglich.");
		}
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
