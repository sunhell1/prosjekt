package implementation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.image.ImageView;
import enums.Condition;
import enums.Direction;
import enums.SUMMER_SQUARE;
import enums.Square;
import enums.Constants;
import graphics.SheepHerder;

public class Level {

	private Square[][] level;

	private int bigSheepCount;
	private int treeCount;
	private int babySheepCount;

	private Point herderStart;
	private Point wolfStart;
	private Point fencePoint;

	private Stack<Point> wolfMoves;
	private Stack<Point> sheepStarts;
	
	private boolean hasWolf;
	private boolean fence;
	private boolean hasItem;

	private Square backupSquare;
	
	private ArrayList<Item> items;
	

	public Level(int levelNumber) {

		this.babySheepCount = 0;
		this.bigSheepCount = 0;
		this.treeCount = 0;

		this.fence = false;
		this.hasItem = false;

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
			items = new ArrayList<Item>();
			sheepStarts = new Stack<Point>();
			Point sheep1 = new Point(6,9);
			Point sheep2 = new Point(6,10);
			Point sheep3 = new Point(7,10);
//			Point sheep4 = new Point(8,10);
			
			sheepStarts.push(sheep1);
			sheepStarts.push(sheep2);
			sheepStarts.push(sheep3);
//			sheepStarts.push(sheep4);
			
			this.bigSheepCount = sheepStarts.size();

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
			level[6][6] = Square.PICKAXESQUARE;
			level[7][6] = Square.PICKAXESQUARE;
			level[8][6] = Square.PICKAXESQUARE;
			level[9][6] = Square.PICKAXESQUARE;
			level[10][6] = Square.PICKAXESQUARE;
			level[11][6] = Square.PICKAXESQUARE;
			
			Item PICKAXE = new Item("PICKAXE", Square.PICKAXE_ITEM.getImage(), 20, 30);
			Item PICKAXE2 = new Item("PICKAXE", Square.PICKAXE_ITEM.getImage(), 20, 30);
			Item PICKAXE3 = new Item("PICKAXE", Square.PICKAXE_ITEM.getImage(), 20, 30);
			Item PICKAXE4 = new Item("PICKAXE", Square.PICKAXE_ITEM.getImage(), 20, 30);
			items.add(PICKAXE);
			items.add(PICKAXE2);
			items.add(PICKAXE3);
			items.add(PICKAXE4);
			
			this.hasItem = true;
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
	
	public Stack<Point> getSheepStarts() {
		return this.sheepStarts;
	}

	public void setSquareAt(Point p, Square sq) {
		level[p.x][p.y] = sq;
	}

	public boolean hasSheep() {
		if (sheepStarts.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasWolf() {
		return this.hasWolf;
	}

	public Point getFencePoint() {
		return this.fencePoint;
	}

	public boolean hasFencePoint() {
		return this.fence;
	}
	
	public ArrayList<Item> getItems(){
		return this.items;
	}
	
	public boolean hasItems(){
		return this.hasItem;
	}

	public boolean isSquareValid(Point p) {

		if (isLocationOutOfBounds(p)) {
			return false;
		} else if (getSquareAt(p).equals(Square.BABYSHEEP)
				|| getSquareAt(p).equals(Square.ROCK)
				|| getSquareAt(p).equals(Square.TREE)
				|| getSquareAt(p).equals(Square.BREAKABLE_ROCK)
				|| getSquareAt(p).equals(Square.HFENCE)
				|| getSquareAt(p).equals(Square.VFENCE)
				|| getSquareAt(p).equals(Square.PICKAXESQUARE)
				|| getSquareAt(p).equals(Square.SNOWROCK)
				|| getSquareAt(p).equals(Square.SNOWTREE)) {
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