package implementation;

import java.awt.Point;

import Sounds.GameSounds;
import javafx.scene.media.AudioClip;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Square;
import graphics.BoardDisplay;
import graphics.SheepHerder;

public class Herder {

	private Point location;
	private int lives;
	private Condition con;
	private Level level;
	private BoardDisplay currentBoard;

	public Herder(Point startLocation, Condition con, Level currentLevel,
			BoardDisplay currentBoard) {

		this.lives = Constants.MAX_LIVES;
		this.location = startLocation;
		this.con = con;
		this.level = currentLevel;
		this.currentBoard = currentBoard;

	}

	public Point getLocation() {
		return this.location;
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
		// GameSounds.playHurt();
		if (this.lives <= 0) {
			this.con = Condition.DEAD;
		}
	}

	public void move(Point p, Direction dir) throws IllegalArgumentException {

		if (isLocationOutOfBounds(new Point(p.x + dir.getX(), p.y + dir.getY()))) {
			takeDamage();
//			GameSounds.playHurt();
			this.location = level.getHerderStart();
			currentBoard.animateToStart(this.location);
		} else if (level.getSquareAt(p).equals(Square.HOLE)) {
			takeDamage();
//			GameSounds.playHurt();
			this.location = level.getHerderStart();
			currentBoard.animateToStart(this.location);
		} else if (level.getSquareAt(p).equals(Square.ROCK)) {

		} else if (level.getSquareAt(p).equals(Square.TREE)) {

		} else if (level.getSquareAt(p).equals(Square.BEER)) {

		} else if (level.getSquareAt(p).equals(Square.BANANA)) {

		} else {
			this.location = new Point(p.x + dir.getX(), p.y + dir.getY());
		}
	}

	public boolean isLocationOutOfBounds(Point p) {

		if (p.x < 0 || p.x >= Constants.BOARD_WIDTH) {
			return true;
		} else if (p.y < 0 || p.y >= Constants.BOARD_HEIGHT) {
			return true;
		}
		return false;
	}

}
