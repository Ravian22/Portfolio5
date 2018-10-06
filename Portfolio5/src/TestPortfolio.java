import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestPortfolio {

	public final static int MAX_NUMBER_ON_DOMINO = 5;

	@Test
	void fitBothSides() {
		Domino domino1 = new Domino(3, 4);
		Domino domino2 = new Domino(4, 3);
		Domino domino3 = new Domino(4, 4);
		Domino domino4 = new Domino(3, 3);
		assertTrue(domino1.fitsBothSides(domino2));
		assertTrue(domino2.fitsBothSides(domino1));
		assertFalse(domino1.fitsBothSides(domino3));
		assertFalse(domino2.fitsBothSides(domino4));
	}

	@Test
	void getPossibleSelection() {
		Player humanPlayer = new HumanPlayer();
		Domino uncoveredDomino = new Domino(0, 0);
		Domino domino1 = new Domino(0, 1);
		Domino domino2 = new Domino(0, 2);
		Domino domino3 = new Domino(0, 3);
		Domino domino4 = new Domino(0, 4);
		Domino domino5 = new Domino(1, 2);
		humanPlayer.addDomino(domino1);
		humanPlayer.addDomino(domino2);
		humanPlayer.addDomino(domino3);
		humanPlayer.addDomino(domino4);
		humanPlayer.addDomino(domino5);
		assertTrue(humanPlayer.getFittingDominos(uncoveredDomino).contains(domino1));
		assertTrue(humanPlayer.getFittingDominos(uncoveredDomino).contains(domino2));
		assertTrue(humanPlayer.getFittingDominos(uncoveredDomino).contains(domino3));
		assertTrue(humanPlayer.getFittingDominos(uncoveredDomino).contains(domino4));
		assertFalse(humanPlayer.getFittingDominos(uncoveredDomino).contains(domino5));
	}

//	@Test
//	void canPlay() {
//		Player humanPlayer = new HumanPlayer();
//		Domino uncoveredDomino = new Domino(0, 0);
//		Domino domino1 = new Domino(1, 1);
//		Domino domino2 = new Domino(1, 2);
//		Domino domino3 = new Domino(1, 3);
//		Domino domino4 = new Domino(1, 4);
//		Domino domino5 = new Domino(2, 2);
//		Domino domino6 = new Domino(2, 0);
//		Domino domino7 = new Domino(0, 4);
//		humanPlayer.addDomino(domino1);
//		humanPlayer.addDomino(domino2);
//		humanPlayer.addDomino(domino3);
//		humanPlayer.addDomino(domino4);
//		humanPlayer.addDomino(domino5);
//		assertFalse(humanPlayer.canPlay(uncoveredDomino));
//		humanPlayer.addDomino(domino6);
//		assertTrue(humanPlayer.canPlay(uncoveredDomino));
//		humanPlayer.playersDominos.remove(domino6);
//		humanPlayer.addDomino(domino7);
//		assertTrue(humanPlayer.canPlay(uncoveredDomino));
//	}

//	@Test
//	public void dealOutDomninos() {
//		DominoGame game = new DominoGame(0,0,false);
//		Player player1 = new HumanPlayer();
//		Player player2 = new HumanPlayer();
//		game.addPlayer(player1);
//		game.addPlayer(player2);
//		assertEquals(5, player1.playersDominos.size());
//		assertEquals(5, player2.playersDominos.size());
//	}
}
