package graphslib.network;

public class ValuePair {
	
	private int up;
	private int down;

	public ValuePair(int up, int down) {
		this.up = up;
		this.down = down;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}
}
