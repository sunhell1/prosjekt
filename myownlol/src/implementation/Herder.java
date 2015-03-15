package implementation;

import java.awt.Point;
import java.util.ArrayList;

import Sounds.GameSounds;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Square;
import graphics.BoardDisplay;
import graphics.ConsoleDisplay;
import graphics.PlayerStats;
import graphics.SheepHerder;

public class Herder {

	private Point location;

	private int lives;

	private Condition con;

	private Level level;

	private BoardDisplay currentBoard;

	private int sheepCaught;

	private ArrayList<ImageView> lifeNodes;

	private PlayerStats playerstats;

	private ArrayList<Item> inventorylist;

	private ConsoleDisplay cd;
	private Group group;

	private Item item;

	private String itemEquipped;

	public Herder(Point startLocation, Condition con, Level currentLevel,
			BoardDisplay currentBoard, PlayerStats playerStats) {

		this.group = new Group();

		this.lives = Constants.MAX_LIVES;
		this.location = startLocation;
		this.con = con;
		this.level = currentLevel;
		this.currentBoard = currentBoard;
		this.playerstats = playerStats;
		this.sheepCaught = 0;
		inventorylist = new ArrayList<Item>();
		this.cd = new ConsoleDisplay(this);
		cd.relocate(600, 0);

		this.group.getChildren().add(cd);

		this.item = new Item("PICKAXE", Square.GRASS.getImage());

		this.itemEquipped = "";

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
		playerstats.removeLife();
		this.lives--;
		GameSounds.playHurt();
		this.location = level.getHerderStart();
		currentBoard.animateToStart(this.location);
		currentBoard.damageAnimation();
		if (this.lives <= 0) {
			this.con = Condition.DEAD;
		}
	}

	public void move(Point p, Direction dir) throws IllegalArgumentException {

		Point newPoint = new Point(p.x + dir.getX(), p.y + dir.getY());

		if (isLocationOutOfBounds(newPoint)) {
			takeDamage();
		}
		else if (level.getSquareAt(newPoint).equals(Square.HOLE)) {
			this.location = newPoint;
			currentBoard.animateMovement(this.location);
			takeDamage();
		}
		else if (level.getSquareAt(newPoint).equals(Square.ROCK)) {
			
		}

		else if (level.getSquareAt(newPoint).equals(Square.TREE)) {

		}

		else if (level.getSquareAt(newPoint).equals(Square.PICKAXESQUARE)) {
			inventorylist.add(item);
			cd.addItemToInvetoryList(item);
			this.location = newPoint;
			currentBoard.animateMovement(this.location);
			level.setSquareAt(newPoint, Square.GRASS);
			currentBoard.updateSquareAt(this.location, Square.GRASS);
		}

		else if (level.getSquareAt(newPoint).equals(Square.BEER)) {
			this.location = newPoint;
			currentBoard.animateMovement(this.location);
			currentBoard.beerAnimation();
			level.setSquareAt(location, Square.GRASS);
			currentBoard.updateSquareAt(location, Square.GRASS);
		}

		else if (level.getSquareAt(newPoint).equals(Square.BANANA)) {
			currentBoard.bananaAnimation();
			this.location = newPoint;
			currentBoard.animateMovement(this.location);
			level.setSquareAt(location, Square.GRASS);
			currentBoard.updateSquareAt(location, Square.GRASS);
			move(this.location, dir);

		}
		else if (level.getSquareAt(newPoint).equals(Square.BREAKABLE_ROCK)) {

			if (itemEquipped.equals("PICKAXE")) {
				currentBoard.pickRockAnimation();
				this.location = newPoint;
				currentBoard.animateMovement(this.location);
				level.setSquareAt(location, Square.GRASS);
				currentBoard.updateSquareAt(this.location, Square.GRASS);
			}
			else {
				// DO NOTHING
			}

		} 
		else {
			this.location = newPoint;
			currentBoard.animateMovement(this.location);
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

	public Group getGroup() {
		return this.group;

	}

	public boolean isEquipped(String name) {
		return name.equals(itemEquipped);
	}

	public void equip(String name) {
		itemEquipped = name;
		if (itemEquipped.equals("PICKAXE")) {
			currentBoard.changeHerderImage(Square.PICKAXE.getImage());
		} else
			currentBoard.changeHerderImage(Square.HERDER.getImage());

	}

	public String getItemEquipped() {
		return this.itemEquipped;
	}

	public int getSheepCaught() {
		return this.sheepCaught;
	}

	public void unEquip() {
		itemEquipped = "";
		currentBoard.changeHerderImage(Square.HERDER.getImage());
	}

}
