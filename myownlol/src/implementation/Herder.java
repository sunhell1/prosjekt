package implementation;

import java.awt.Point;
import java.util.ArrayList;

import sun.java2d.loops.ProcessPath.DrawHandler;
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
	private int counter;

	private Condition con;

	private Level level;

	ArrayList<Sheep> sheeps;

	private BoardDisplay currentBoard;

	private int sheepCaught;

	private ArrayList<ImageView> lifeNodes;

	private PlayerStats playerstats;

	private ArrayList<Item> inventorylist;

	private ConsoleDisplay cd;
	private Group group;

	private Item item;

	private String itemEquipped;

	private boolean killedSheep;
	private boolean smackedSheep;

	private SheepHerder sh;

	public Herder(Point startLocation, Condition con, Level currentLevel,
			BoardDisplay currentBoard, PlayerStats playerStats, SheepHerder sh) {

		this.group = new Group();

		this.lives = Constants.MAX_LIVES;
		this.counter = 0;
		this.location = startLocation;
		this.con = con;
		this.level = currentLevel;
		this.currentBoard = currentBoard;
		this.playerstats = playerStats;
		this.sheepCaught = 0;
		this.inventorylist = new ArrayList<Item>();
		this.sh = sh;
		this.cd = new ConsoleDisplay(this, this.sh);
		this.cd.relocate(600, 0);

		this.group.getChildren().add(cd);

		this.item = new Item("PICKAXE", Square.GRASS.getImage());

		this.itemEquipped = "";

		this.sheeps = sh.getSheepArray();

		this.killedSheep = false;
		this.smackedSheep = false;
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

		if (level.isLocationOutOfBounds(newPoint)) {
			takeDamage();
		} else if (level.getSquareAt(newPoint).equals(Square.HOLE)) {
			this.location = newPoint;
			currentBoard.animateHerderMovement(this.location);
			takeDamage();
		} else if (level.getSquareAt(newPoint).equals(Square.ROCK)
				|| level.getSquareAt(newPoint).equals(Square.SNOWROCK)) {
			counter++;
			if (counter == 5) {
				currentBoard.chatDisplay("I think its a rock");
				counter = 0;
			}
		}

		else if (level.getSquareAt(newPoint).equals(Square.TREE)
				|| level.getSquareAt(newPoint).equals(Square.SNOWTREE)) {
			counter++;
			if (counter == 5) {
				currentBoard.chatDisplay("I think its a tree.");
				counter = 0;
			}
		}

		else if (level.getSquareAt(newPoint).equals(Square.PICKAXESQUARE)) {
			inventorylist.add(item);
			cd.addItemToInvetoryList(item);
			this.location = newPoint;
			currentBoard.animateHerderMovement(this.location);
			level.setSquareAt(newPoint, level.getBackupSquare());
			currentBoard.updateSquareAt(this.location, level.getBackupSquare());
		}

		// else if (level.getSquareAt(newPoint).equals(Square.BEER)) {
		// this.location = newPoint;
		// currentBoard.animateMovement(this.location);
		// currentBoard.beerAnimation();
		// level.setSquareAt(location, Square.GRASS);
		// currentBoard.updateSquareAt(location, Square.GRASS);
		// }

		else if (level.getSquareAt(newPoint).equals(Square.ICESQUARE)) {
			this.location = newPoint;
			Point destinationPoint = new Point();
			boolean slidOutOfBounds = false;

			if (dir.equals(Direction.NORTH)) {
				for (int y = location.y; y >= 0; y--) {
					destinationPoint.setLocation(location.x, y - 1);
					if (level.isLocationOutOfBounds(destinationPoint)) {
						slidOutOfBounds = true;
						break;
					}

					if (level.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			else if (dir.equals(Direction.WEST)) {
				for (int x = location.x; x >= 0; x--) {
					destinationPoint.setLocation(x - 1, location.y);
					if (level.isLocationOutOfBounds(destinationPoint)) {
						slidOutOfBounds = true;
						break;
					}

					if (level.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			else if (dir.equals(Direction.SOUTH)) {
				for (int y = location.y; y < 12; y++) {
					destinationPoint.setLocation(location.x, y + 1);
					if (level.isLocationOutOfBounds(destinationPoint)) {
						slidOutOfBounds = true;
						break;
					}

					if (level.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			else if (dir.equals(Direction.EAST)) {
				for (int x = location.x; x < 12; x++) {
					destinationPoint.setLocation(x + 1, location.y);
					if (level.isLocationOutOfBounds(destinationPoint)) {
						slidOutOfBounds = true;
						break;
					}

					if (level.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			currentBoard.iceSquareAnimation(this.location, destinationPoint,
					slidOutOfBounds);

			if (slidOutOfBounds == true) {
				this.location = level.getHerderStart();
				playerstats.removeLife();
				this.lives--;
			} else
				this.location = destinationPoint;
		}

		else if (level.getSquareAt(newPoint).equals(Square.BREAKABLE_ROCK)) {

			if (itemEquipped.equals("PICKAXE")) {
				currentBoard.pickRockAnimation();
				this.location = newPoint;
				currentBoard.animateHerderMovement(this.location);
				level.setSquareAt(location, level.getBackupSquare());
				currentBoard.updateSquareAt(this.location,
						level.getBackupSquare());
			} else {
				counter++;
				if (counter == 5) {
					currentBoard
							.chatDisplay("Maybe i can break this with something?");
					counter = 0;
				}
			}
		}

		else if (level.getSquareAt(newPoint).equals(Square.BABYSHEEP)) {
			counter++;
			if (counter > 2) {
				currentBoard.chatDisplay("Press \"D\" to pick up the sheep.");
				counter = 0;
			}
		}

		else if (level.getSquareAt(newPoint).equals(Square.VFENCE)) {
			counter++;
			if (counter == 5) {
				currentBoard.chatDisplay("It's a fence ...");
				counter = 0;
			}
		}

		else if (level.getSquareAt(newPoint).equals(Square.HFENCE)) {
			counter++;
			if (counter == 5) {
				currentBoard.chatDisplay("It's a fence ...");
				counter = 0;
			}
		} else if (sh.containsSheep(newPoint)) {
		}

		else {
			this.location = newPoint;
			currentBoard.animateHerderMovement(this.location);
		}
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

	public boolean isStandingNextToSheep(Point p) {

		if (!level.isLocationOutOfBounds(new Point(p.x + 1, p.y))) {
			for (Sheep sheep : sheeps) {
				if (sheep.getLocation().equals(new Point(p.x + 1, p.y))) {
					return true;
				}
			}
		}
		if (!level.isLocationOutOfBounds(new Point(p.x - 1, p.y))) {
			for (Sheep sheep : sheeps) {
				if (sheep.getLocation().equals(new Point(p.x - 1, p.y))) {
					return true;
				}
			}
		}
		if (!level.isLocationOutOfBounds(new Point(p.x, p.y + 1))) {
			for (Sheep sheep : sheeps) {
				if (sheep.getLocation().equals(new Point(p.x, p.y + 1))) {
					return true;
				}
			}
		}
		if (!level.isLocationOutOfBounds(new Point(p.x, p.y - 1))) {
			for (Sheep sheep : sheeps) {
				if (sheep.getLocation().equals(new Point(p.x, p.y - 1))) {
					return true;
				}
			}
		}

		return false;
	}

	public void smackSheep(Point p) {

		Point p1;
		if (!level.isLocationOutOfBounds(new Point(p.x + 1, p.y))
				&& level.isSquareValid(new Point(p.x + 2, p.y))) {
			for (Sheep sheep : sheeps) {
				if (sh.containsSheep(new Point(p.x + 1, p.y))) {
					p1 = new Point(p.x + 1, p.y);
					if (sheep.getLocation().equals(p1))
						sheep.move(sheep.getLocation(), Direction.EAST);
				}
			}
		}
		if (!level.isLocationOutOfBounds(new Point(p.x - 1, p.y))
				&& level.isSquareValid(new Point(p.x - 2, p.y))) {
			for (Sheep sheep : sheeps) {
				if (sh.containsSheep(new Point(p.x - 1, p.y))) {
					p1 = new Point(p.x - 1, p.y);
					if (sheep.getLocation().equals(p1))
						sheep.move(sheep.getLocation(), Direction.WEST);
				}
			}
		}
		if (!level.isLocationOutOfBounds(new Point(p.x, p.y + 1))
				&& level.isSquareValid(new Point(p.x, p.y + 2))) {
			for (Sheep sheep : sheeps) {
				if (sh.containsSheep(new Point(p.x, p.y + 1))) {
					p1 = new Point(p.x, p.y + 1);
					if (sheep.getLocation().equals(p1))
						sheep.move(sheep.getLocation(), Direction.SOUTH);
				}
			}
		}
		if (!level.isLocationOutOfBounds(new Point(p.x, p.y - 1))
				&& level.isSquareValid(new Point(p.x, p.y - 2))) {
			for (Sheep sheep : sheeps) {
				if (sh.containsSheep(new Point(p.x, p.y - 1))) {
					p1 = new Point(p.x, p.y - 1);
					if (sheep.getLocation().equals(p1))
						sheep.move(sheep.getLocation(), Direction.NORTH);
				}
			}
		}
	}

	public void pickUpSheep() {
		if (level.getSquareAt(location).equals(Square.BABYSHEEP)) {
			GameSounds.playBaa();
			level.setSquareAt(location, level.getBackupSquare());
			currentBoard.updateSquareAt(location, level.getBackupSquare());
			playerstats.sheepCaught();
		}
	}

	public boolean killedSheep() {

		if (!level.isLocationOutOfBounds(new Point(location.x + 1, location.y))) {
			if (sh.containsSheep(new Point(location.x + 1, location.y))
					&& level.isLocationOutOfBounds(new Point(location.x + 2,
							location.y))) {
				if (this.smackedSheep == true) {
					return true;
				}
			}
		}

		if (!level.isLocationOutOfBounds(new Point(location.x - 1, location.y))) {
			if (sh.containsSheep(new Point(location.x - 1, location.y))
					&& level.isLocationOutOfBounds(new Point(location.x - 2,
							location.y))) {
				if (this.smackedSheep == true) {
					return true;
				}
			}
		}
		if (!level.isLocationOutOfBounds(new Point(location.x, location.y + 1))) {
			if (sh.containsSheep(new Point(location.x, location.y + 1))
					&& level.isLocationOutOfBounds(new Point(location.x,
							location.y + 2))) {
				if (this.smackedSheep == true) {
					return true;
				}
			}
		}

		if (!level.isLocationOutOfBounds(new Point(location.x, location.y - 1))) {
			if (sh.containsSheep(new Point(location.x, location.y - 1))
					&& level.isLocationOutOfBounds(new Point(location.x,
							location.y - 2))) {
				if (this.smackedSheep == true) {
					return true;
				}
			}
		}

		return false;
	}

	public void setSmacked(boolean smacked) {
		this.smackedSheep = smacked;
	}
}
