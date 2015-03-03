package enums;

public enum Direction {
	NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0);

	private int x;
	private int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

}
