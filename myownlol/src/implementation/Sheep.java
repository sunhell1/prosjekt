package implementation;

import java.awt.Point;

import com.sun.org.apache.bcel.internal.Constants;

import javafx.scene.image.ImageView;
import enums.Condition;
import enums.Direction;
import enums.Square;
import graphics.BoardDisplay;
import graphics.PlayerStats;
import graphics.SheepHerder;

public class Sheep extends ImageView{

	private Point location;
	
	private Condition con;
	
	private BoardDisplay currentBoard;
	 
	private SheepHerder sh;
	
	private Level currentLevel;
	
	private Direction dir;
	
	private final int PREFERRED_DIM = 50;
	
	public Sheep(Point point, Condition condition, Direction dir, Level currentLevel,
			 SheepHerder sh) {
		this.location = point;
		this.dir = dir;
		this.con = condition;
		this.currentLevel = currentLevel;
		this.sh = sh;
		this.currentBoard = sh.getBoardDisplay();
		
		this.setImage(Square.BIGSHEEP.getImage());
		this.setFitHeight(PREFERRED_DIM);
		this.setFitWidth(PREFERRED_DIM);
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
	
	public void move(Point p, Direction dir) throws IllegalArgumentException {

		Point newPoint = new Point(p.x + dir.getX(), p.y + dir.getY());

		if (currentLevel.isLocationOutOfBounds(newPoint)) {
			killSheep();
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.HOLE)) {
			currentBoard.animateSheepMovement(this.location, newPoint);
			this.location = newPoint;

		} else if (currentLevel.getSquareAt(newPoint).equals(Square.ROCK)
				|| currentLevel.getSquareAt(newPoint).equals(Square.SNOWROCK)) {
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.TREE)
				|| currentLevel.getSquareAt(newPoint).equals(Square.SNOWTREE)) {
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.PICKAXESQUARE)) {
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.ICESQUARE)) {
			this.location = newPoint;
			Point destinationPoint = new Point();
			boolean slidOutOfBounds = false;

			if (dir.equals(Direction.NORTH)) {
				for (int y = location.y; y >= 0; y--) {
					destinationPoint.setLocation(location.x, y - 1);
					 if (currentLevel.isLocationOutOfBounds(destinationPoint)){
					 slidOutOfBounds = true;
					 break;
					 }

					if (currentLevel.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			else if (dir.equals(Direction.WEST)) {
				for (int x = location.x; x >= 0; x--) {
					destinationPoint.setLocation(x - 1, location.y);
					 if (currentLevel.isLocationOutOfBounds(destinationPoint)){
					 slidOutOfBounds = true;
					 break;
					 }

					if (currentLevel.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			else if (dir.equals(Direction.SOUTH)) {
				for (int y = location.y; y < 12; y++) {
					destinationPoint.setLocation(location.x, y + 1);
					 if (currentLevel.isLocationOutOfBounds(destinationPoint)){
					 slidOutOfBounds = true;
					 break;
					 }

					if (currentLevel.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			else if (dir.equals(Direction.EAST)) {
				for (int x = location.x; x < 12; x++) {
					destinationPoint.setLocation(x + 1, location.y);
					 if (currentLevel.isLocationOutOfBounds(destinationPoint)){
					 slidOutOfBounds = true;
					 break;
					 }

					if (currentLevel.getSquareAt(destinationPoint).equals(
							Square.ICESQUARE)) {
						continue;
					} else
						break;
				}
			}

			currentBoard.sheepIceSquareAnimation(this.location, destinationPoint, this);
			
			this.location = destinationPoint;
			
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.BREAKABLE_ROCK)) {
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.BABYSHEEP)) {
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.VFENCE)) {
		} else if (currentLevel.getSquareAt(newPoint).equals(Square.HFENCE)) {
		} else if (sh.containsSheep(newPoint)) {
		}
		else {
			currentBoard.animateSheepMovement(this.location, newPoint);
			this.location = newPoint;
		}
	}
	
	public void killSheep() {
		currentBoard.getGroup().getChildren().remove(this);
	}
	

}
