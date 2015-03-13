package implementation;

import java.awt.Point;

import enums.Condition;
import graphics.BoardDisplay;

public class Wolf {
	
	private Condition con;
	private Point location;
	private BoardDisplay bd;
	
	public Wolf(Point startLoc, Condition condition, BoardDisplay bd) {
		
		this.location = startLoc;
		this.con = condition;
		this.bd = bd;
		
	}
	
	public void moveWolf(Point nextPosition) {
		this.location = nextPosition;
		bd.animateWolfMovement(location);
	}
	
	public Point getWolfPosition() {
		return this.location;
	}
}
