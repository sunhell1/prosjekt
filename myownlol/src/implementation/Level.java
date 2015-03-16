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
	private ArrayList<Sheep> sheepArray;
	
	private Square[][] level;
	
	private int sheepcount;
	private int treeCount;
	private int babySheepCount;
	
	private Point herderStart;
	private Point wolfStart;
	
	private Stack<Point> wolfMoves;
	private boolean hasWolf;

	public Level(int levelNumber) {

		this.sheepcount = 0;
		this.treeCount = 0;


		this.level = new Square[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];

		switch (levelNumber) {
		case 1:
			
			level = Constants.levelOne();
			this.hasWolf = false;
			
			for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
				for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
					if (level[i][j].equals(Square.TREE)) {
						treeCount++;
					}
				}
			}
			

			sheepArray = new ArrayList<Sheep>();
			this.sheepcount = sheepArray.size();
			
			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BABYSHEEP)) {
						this.babySheepCount++;
					}
				}
			}

			herderStart = new Point(0,0);
			

			break;
		case 2:

			this.level = Constants.levelTwo();
			this.hasWolf = false;
			
			for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
				for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
					if (level[i][j].equals(Square.TREE)) {
						treeCount++;
					}
				}
			}
			
			Sheep sheep = new Sheep(new Point(6,7), Condition.ALIVE);
			sheepArray = new ArrayList<Sheep>();
			sheepArray.add(sheep);

			this.sheepcount = sheepArray.size();
			
			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					if (level[i][j].equals(Square.BABYSHEEP)) {
						this.babySheepCount++;
					}
				}
			}

			herderStart = new Point(2,3);

			break;
		case 3:
			break;
		default:
			System.out.println("LOL");
			break;
		}
	}

	public ArrayList<Sheep> getLevelSheep() {
		return this.sheepArray;
	}

	public Square[][] getBoardLayout() {
		return this.level;
	}

	public int getSheepCount() {
		return this.sheepcount;
	}
	
	public int getBabySheepCount() {
		return this.babySheepCount;
	}
	
	public int getTreeCount() {
		return this.treeCount;
	}
	
	public Point getHerderStart(){
		return this.herderStart;
	}
	
	public Point getWolfStart() {
		return this.wolfStart;
	}
	
	public Square getSquareAt(Point p){
	
		return level[p.x][p.y];
	}
	
	public Stack<Point> getWolfStack() {
		return this.wolfMoves;
	}
	
	public void setSquareAt(Point p, Square sq){
		level[p.x][p.y] = sq;
	}
	
	public boolean hasSheep() {
		if (sheepArray.size() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean hasWolf() {
		return this.hasWolf;
	}
}