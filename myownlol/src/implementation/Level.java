package implementation;

import java.awt.Point;
import java.util.ArrayList;

import enums.Condition;
import enums.Direction;
import enums.Square;
import enums.Constants;

public class Level {
	private ArrayList<Sheep> sheepArray;
	private Square[][] level;
	private Player herder;
	private Board board;
	private int sheepcount;

	public Level(int levelNumber) {
		
		this.sheepcount = 0;
		
		this.board = new Board();
		this.level = new Square[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
		
		switch (levelNumber) {
		case 1:
			for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
				for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
					level[i][j] = Square.GRASS;
				}
			}

			Sheep sheep1 = new Sheep(new Point(8, 8), Condition.ALIVE);
			Sheep sheep2 = new Sheep(new Point(11, 1), Condition.ALIVE);
			Sheep sheep3 = new Sheep(new Point(9, 3), Condition.ALIVE);

			sheepArray = new ArrayList<Sheep>();
			sheepArray.add(sheep1);
			sheepArray.add(sheep2);
			sheepArray.add(sheep3);
			
			sheepcount = sheepArray.size();

			herder = new Player(Direction.WEST, new Point(0, 0), this.board,
					Condition.ALIVE);

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

			herder = new Player(Direction.EAST, new Point(5, 5), board,
					Condition.ALIVE);

			break;
		case 3:
			break;
		default: System.out.println("LOL");
			break;
		}
	}

	public ArrayList<Sheep> getLevelSheep() {
		return this.sheepArray;
	}


	public Point getHerderStart() {
		return this.herder.getLocation();
	}
	
	public Square[][] getBoardLayout(){
		return this.level;
	}
	
	public int getSheepCount() {
		return this.sheepcount;
	}
}
