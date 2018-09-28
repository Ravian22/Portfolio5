
public class Domino {
	
	private int left;
	private int right;
	
	public Domino(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	public int getLeft() {
		return left;
	}
	
	public int getRight() {
		return right;
	}

	@Override
	public String toString() {
		String domino = "[" + getLeft() + "|" + getRight() + "]";
		return domino;
	}
	
	public boolean fitsDomino(Domino domino) {
		boolean fits = false;
		if(this.getLeft() == domino.getRight() || this.getRight() == domino.getLeft()) {
			fits = true;
		}
		return fits;
	}
	
	public boolean fitsBothSides(Domino domino) {
		boolean fitsBoth = false;
		if(this.getLeft() == domino.getRight() && this.getRight() == domino.getLeft()) {
			fitsBoth = true;
		}
		return fitsBoth;
	}
}
