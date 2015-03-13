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
	private Point herderStart;
	private Point wolfStart;
	private Stack<Point> wolfMoves;

	public Level(int levelNumber) {

		this.sheepcount = 0;
		this.treeCount = 0;

		this.level = new Square[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];

		switch (levelNumber) {
		case 1:
			
			level = Constants.levelOne();
			
			for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
				for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
					if (level[i][j].equals(Square.TREE)) {
						treeCount++;
					}
				}
			}

			Sheep sheep1 = new Sheep(new Point(6, 10), Condition.ALIVE);
			
			wolfStart = new Point(11,11);

			wolfMoves = new Stack<Point>();
			wolfMoves.push(new Point(6,10));
			wolfMoves.push(new Point(5,10));
			wolfMoves.push(new Point(4,10));
			wolfMoves.push(new Point(4,9));
			wolfMoves.push(new Point(4,8));
			wolfMoves.push(new Point(4,7));
			wolfMoves.push(new Point(4,6));
			wolfMoves.push(new Point(4,5));
			wolfMoves.push(new Point(4,4));
			wolfMoves.push(new Point(5,4));
			wolfMoves.push(new Point(6,4));
			wolfMoves.push(new Point(7,4));
			wolfMoves.push(new Point(8,4));
			wolfMoves.push(new Point(9,4));
			wolfMoves.push(new Point(10,4));
			wolfMoves.push(new Point(11,4));
			wolfMoves.push(new Point(11,5));
			wolfMoves.push(new Point(11,6));
			wolfMoves.push(new Point(11,7));
			wolfMoves.push(new Point(11,8));
			wolfMoves.push(new Point(11,9));
			wolfMoves.push(new Point(11,10));

			sheepArray = new ArrayList<Sheep>();
			sheepArray.add(sheep1);

			sheepcount = sheepArray.size();

			herderStart = new Point(0,0);
			

			break;
		case 2:
			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					level[i][j] = Square.GRASS;
				}
			}

			Sheep sheep4 = new Sheep(new Point(0, 0), Condition.ALIVE);
			Sheep sheep5 = new Sheep(new Point(11, 11), Condition.ALIVE);
			Sheep sheep6 = new Sheep(new Point(11, 0), Condition.ALIVE);
			Sheep sheep7 = new Sheep(new Point(0, 11), Condition.ALIVE);

			sheepArray = new ArrayList<Sheep>();
			sheepArray.add(sheep4);
			sheepArray.add(sheep5);
			sheepArray.add(sheep6);
			sheepArray.add(sheep7);

			sheepcount = sheepArray.size();

			herderStart = new Point(5,5);

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
		level[p.y][p.x] = sq;
	}
}