package implementation;

import java.awt.Point;

import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.image.ImageView;
import enums.Condition;
import enums.Direction;
import enums.Square;
import enums.Constants;

public class Level {
	// private ArrayList<Sheep> sheepArray;

	private Square[][] level;

	private int bigSheepCount;
	private int treeCount;
	private int babySheepCount;

	private Point herderStart;
	private Point wolfStart;
	private Point fencePoint;

	private Stack<Point> wolfMoves;
	private boolean hasWolf;
	private boolean fence;

	private Square backupSquare;

	public Level(int levelNumber) {

		this.babySheepCount = 0;
		this.bigSheepCount = 0;
		this.treeCount = 0;

		this.fence = false;

		this.level = new Square[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];

		switch (levelNumber) {
		case 3:

			this.backupSquare = Square.GRASS;
			level = Constants.levelOne();
			this.hasWolf = false;

			for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
				for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
					if (level[i][j].equals(Square.TREE)) {
						this.treeCount++;
					}
				}
			}

			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BABYSHEEP)) {
						this.babySheepCount++;
					}
				}
			}

			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BIGSHEEP)) {
						this.bigSheepCount++;
					}
				}
			}

			herderStart = new Point(0, 0);

			break;
		case 2:

			this.backupSquare = Square.GRASS;
			this.level = Constants.levelTwo();
			this.hasWolf = false;
			this.fencePoint = new Point(2, 0);
			this.fence = true;

			for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
				for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
					if (level[i][j].equals(Square.TREE)) {
						this.treeCount++;
					}
				}
			}

			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BABYSHEEP)) {
						this.babySheepCount++;
					}
				}
			}

			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BIGSHEEP)) {
						this.bigSheepCount++;
					}
				}
			}

			herderStart = new Point(2, 3);

			break;
		case 1:
			this.backupSquare = Square.SNOWSQUARE;

			for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
				for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
					level[i][j] = Square.SNOWSQUARE;
				}
			}

			level[5][5] = Square.ICESQUARE;
			level[6][5] = Square.ICESQUARE;
			level[7][5] = Square.ICESQUARE;
			level[8][5] = Square.ICESQUARE;
			level[9][5] = Square.ICESQUARE;
			level[10][5] = Square.ICESQUARE;
			level[11][5] = Square.ICESQUARE;
			level[5][4] = Square.ICESQUARE;
			level[5][3] = Square.ICESQUARE;
			level[5][2] = Square.ICESQUARE;
			level[5][1] = Square.ICESQUARE;
			level[5][0] = Square.ICESQUARE;
			level[7][0] = Square.SNOWTREE;
			level[8][0] = Square.SNOWTREE;
			level[9][0] = Square.SNOWTREE;
			level[8][1] = Square.SNOWTREE;
			level[0][9] = Square.SNOWROCK;
			level[9][10] = Square.SNOWROCK;
			level[9][9] = Square.SNOWBIGSHEEP;

			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BABYSHEEP)) {
						this.babySheepCount++;
					}
				}
			}
			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BIGSHEEP)
							|| level[i][j].equals(Square.SNOWBIGSHEEP)) {
						this.bigSheepCount++;
					}
				}
			}

			this.hasWolf = false;
			this.fence = false;

			this.herderStart = new Point(1, 1);

			break;
		default:
			System.out.println("LOL");
			break;
		}
	}

	public Square[][] getBoardLayout() {
		return this.level;
	}

	public int getBabySheepCount() {
		return this.babySheepCount;
	}

	public int getBigSheepCount() {
		return this.bigSheepCount;
	}

	public int getTreeCount() {
		return this.treeCount;
	}

	public Point getHerderStart() {
		return this.herderStart;
	}

	public Point getWolfStart() {
		return this.wolfStart;
	}

	public Square getSquareAt(Point p) {
		return level[p.x][p.y];
	}

	public Stack<Point> getWolfStack() {
		return this.wolfMoves;
	}

	public void setSquareAt(Point p, Square sq) {
		level[p.x][p.y] = sq;
	}

	// public boolean hasSheep() {
	// if (sheepArray.size() > 0) {
	// return true;
	// }
	// return false;
	// }

	public boolean hasWolf() {
		return this.hasWolf;
	}

	public Point getFencePoint() {
		return this.fencePoint;
	}

	public boolean hasFencePoint() {
		return this.fence;
	}

	public boolean isSquareValid(Point p) {

		if (isLocationOutOfBounds(p)) {
			return false;
		} else if (getSquareAt(p).equals(Square.BABYSHEEP)
				|| getSquareAt(p).equals(Square.BIGSHEEP)
				|| getSquareAt(p).equals(Square.ROCK)
				|| getSquareAt(p).equals(Square.TREE)
				|| getSquareAt(p).equals(Square.BREAKABLE_ROCK)
				|| getSquareAt(p).equals(Square.HFENCE)
				|| getSquareAt(p).equals(Square.VFENCE)
				|| getSquareAt(p).equals(Square.PICKAXESQUARE)
				|| getSquareAt(p).equals(Square.SNOWROCK)
				|| getSquareAt(p).equals(Square.SNOWTREE)
				|| getSquareAt(p).equals(Square.SNOWBIGSHEEP)) {
			return false;
		} else
			return true;
	}

	public boolean isLocationOutOfBounds(Point p) {

		if (p.x < 0 || p.x >= Constants.BOARD_WIDTH) {
			return true;
		} else if (p.y < 0 || p.y >= Constants.BOARD_HEIGHT) {
			return true;
		}
		return false;
	}

	public Square getBackupSquare() {
		return this.backupSquare;
	}

}