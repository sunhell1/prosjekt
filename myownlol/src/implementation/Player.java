package implementation;

import java.awt.Point;

import javafx.scene.media.AudioClip;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import graphics.SheepHerder;

public class Player implements interfaces.Player {

	private Direction dir;

	private Point startLocation;
	private Point location;
	private int lives;
	private Board board;
	private Condition con;
	private AudioClip hurt;

	public Player(Direction dir, Point startLocation, Board board, Condition con) {

		this.dir = dir;
		this.board = board;
		this.lives = Constants.MAX_LIVES;
		this.startLocation = startLocation;
		this.location = startLocation;
		this.con = con;
		// Lyd til damage
		this.hurt = new AudioClip(SheepHerder.class.getResource(
				"/media/herderHurt.mp3").toString());
	}

	@Override
	public Point getLocation() {
		return this.location;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}

	public Point getStartLocation() {
		return this.startLocation;
	}

	public int getLives() {
		return this.lives;
	}

	public void setCondition(Condition con) {
		this.con = con;

	}

	public Condition getCondition() {
		return this.con;
	}

	public void takeDamage() {
		this.lives--;
		hurt.play();
		if (this.lives <= 0) {

			this.con = Condition.DEAD;
		}

	}

	public void move(Point p, Direction dir) throws IllegalArgumentException {

		if (dir.equals(Direction.NORTH)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {
				takeDamage();
			} else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());
		}

		else if (dir.equals(Direction.SOUTH)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {
				takeDamage();
			} else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());

		}

		else if (dir.equals(Direction.WEST)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {
				takeDamage();
			} else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());

		}

		else if (dir.equals(Direction.EAST)) {
			if (board.isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y
					+ dir.getY()))) {
				takeDamage();
			} else
				this.location = new Point(p.x + dir.getX(), p.y + dir.getY());

		}

		else
			throw new IllegalArgumentException(
					"Provided direction is not a valid direction");
	}

}
