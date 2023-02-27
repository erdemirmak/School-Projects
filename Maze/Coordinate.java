public class Coordinate {
	
	private int x, y;
	private Coordinate previous;

	public Coordinate(int x, int y, Coordinate previous) {
		this.x = x;
		this.y = y;
		this.previous = previous;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Coordinate getPrevious() {
		return previous;
	}
	public void setPrevious(Coordinate previous) {
		this.previous = previous;
	}
}
