package myownlol;

import java.awt.Point;

import enums.Constants;
import enums.Direction;

public class Player implements interfaces.Player {

	private Direction dir;
	private String name;
	private Point startLocation;
	private Point location;
	private int lives;
	private Board board;
	private boolean alive;

	public Player(String name, Direction dir, Point startLocation, Board board) {
		this.name = name;
		this.dir = dir;
		this.board = board;
		this.lives = Constants.MAX_LIVES;
		this.startLocation = startLocation;
		this.location = startLocation;
		this.alive = true;

	}

	@Override
	public Point getLocation() {
		return this.location;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}

	public String getName() {
		return this.name;

	}

	public Point getStartLocation() {
		return this.startLocation;
	}

	public int getLives() {
		return this.lives;
	}

	public void takeDamage() {
		this.lives--;
		if (this.lives <= 0){
			
			this.alive = false;
		}

	}
	
	public boolean isAlive(){
		return this.alive;
	}

	@SuppressWarnings("serial")
	public void move(Point p, Direction dir) throws IllegalArgumentException {

		if (dir.equals(Direction.NORTH)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {

				this.takeDamage();
			} 
			else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());
		}

		else if (dir.equals(Direction.SOUTH)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {

				this.takeDamage();
			} 
			else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());

		}

		else if (dir.equals(Direction.WEST)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {

				this.takeDamage();
			} 
			else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());

		}

		else if (dir.equals(Direction.EAST)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {

				this.takeDamage();
			} 
			else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());

		}

		else
			throw new IllegalArgumentException(
					"Provided direction is not a valid direction");

	}

}
