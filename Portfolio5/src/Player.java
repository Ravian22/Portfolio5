import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	
	List<Domino> playersDominos;
	boolean isComputer;
	
	public Player(boolean isComputer) {
		playersDominos = new ArrayList<Domino>();
		this.isComputer = isComputer;
	}
	
	public void addDomino(Domino domino) {
		playersDominos.add(domino);
	}
	
	public boolean hasDominos() {
		boolean hasDominos = true;
		if (playersDominos.size() == 0) {
			hasDominos = false;
		}
		return hasDominos;
	}

}
