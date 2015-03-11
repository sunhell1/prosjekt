package enums;

public enum Direction {
	
	NORTH (0, -1),
	SOUTH (0, 1),
	WEST (-1, 0),
	EAST (1, 0);
	
	
	private int x;
	private int y;
	
	private Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
