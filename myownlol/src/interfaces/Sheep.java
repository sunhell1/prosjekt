package interfaces;

import implementation.Board;

import java.awt.Point;

import enums.Direction;

public interface Sheep {
	
	public Direction getDir();
	
	public Point getLocation();
	
	public Board getBoard();
	
	public String getName();
	
	public void setName(String name);
	
}
