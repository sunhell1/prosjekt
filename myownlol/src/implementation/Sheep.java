package implementation;

import java.awt.Point;

import enums.Condition;
import enums.Direction;

public class Sheep implements interfaces.Sheep {
	
	private Direction dir;
	private Point location;
	private Board board;
	private String name;
	private Condition con;
	
	


	public Sheep(Point point, Direction dir, Board board, Condition condition) {
		this.location = point;
		this.dir = dir;
		this.board = board;
		this.name = "Sheep";
		this.con = condition;
		
	}


	public Condition getCondition() {
		return this.con;
	}


	public void setCondition(Condition condition) {
		this.con = condition;
	}


	public Direction getDir() {
		return dir;
	}


	public Point getLocation() {
		return location;
	}


	public Board getBoard() {
		return board;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}
