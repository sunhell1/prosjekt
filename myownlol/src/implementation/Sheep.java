package implementation;

import java.awt.Point;

import enums.Condition;
import enums.Direction;

public class Sheep implements interfaces.Sheep {

	private Direction direction;
	private Point location;
	private String name;
	private Condition con;

	public Sheep(Point point, Condition condition) {
		this.location = point;
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
		return this.direction;
	}

	public Point getLocation() {
		return this.location;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {

		return this.location.x;

	}

	public int getY() {
		return this.location.y;
	}

	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
