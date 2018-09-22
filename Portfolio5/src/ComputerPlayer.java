
public class ComputerPlayer extends Player {
	
	private boolean chooseRandom;

	public ComputerPlayer(boolean isComputer, boolean random) {
		super(isComputer);
		chooseRandom = random;
	}
	
	public boolean isRandom() {
		return chooseRandom;
	}
	
	

}
