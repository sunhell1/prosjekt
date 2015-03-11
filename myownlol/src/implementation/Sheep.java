package implementation;

import java.awt.Point;

import enums.Condition;

public class Sheep{

	private Point location;
	private Condition con;

	public Sheep(Point point, Condition condition) {
		this.location = point;
		this.con = condition;
	}

	public Condition getCondition() {
		return this.con;
	}

	public void setCondition(Condition condition) {
		this.con = condition;
	}

	public Point getLocation() {
		return this.location;
	}
}
