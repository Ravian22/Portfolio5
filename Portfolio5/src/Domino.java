
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
	
	public String showDomino() {
		String domino = "[" + getLeft() + "|" + getRight() + "]";
		return domino;
	}
	
	public boolean fitsBothSides(Domino domino) {
		boolean fits = false;
		if(this.getLeft() == domino.getRight() && this.getRight() == domino.getLeft()) {
			fits = true;
		}
		return fits;
	}
}
