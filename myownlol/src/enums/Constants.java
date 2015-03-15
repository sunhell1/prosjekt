package enums;

public class Constants {
	public static final int MAX_LIVES = 5;
	public static final int BOARD_WIDTH = 12;
	public static final int BOARD_HEIGHT = 12;
	public static Square[][] level;
	
	
	
	public static Square[][] levelOne() {
		level = new Square[BOARD_WIDTH][BOARD_HEIGHT];
		for (int i = 0; i < BOARD_WIDTH; i++) {
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				level[i][j] = Square.GRASS;
			}
		}
		
		level[4][0] = Square.TREE;
		level[5][0] = Square.TREE;
		level[6][0] = Square.TREE;
		level[7][0] = Square.TREE;
		level[8][0] = Square.TREE;
		level[9][0] = Square.TREE;
		level[10][0] = Square.TREE;
		level[11][0] = Square.TREE;
		level[5][1]	= Square.TREE;
		level[6][1]	= Square.TREE;
		level[7][1] = Square.TREE;
		level[8][1] = Square.TREE;
		level[9][1] = Square.TREE;
		level[10][1] = Square.TREE;
		level[11][1] = Square.TREE;
		level[5][2] = Square.TREE;
		level[6][2] = Square.TREE;
		level[7][2] = Square.TREE;
		level[8][2] = Square.TREE;
		level[9][2] = Square.TREE;
		level[10][2] = Square.TREE;
		level[7][3] = Square.TREE;
		level[8][3] = Square.TREE;
		level[0][7] = Square.TREE;
		level[0][8] = Square.TREE;
		level[0][9] = Square.TREE;
		level[0][10] = Square.TREE;
		level[1][7]  = Square.TREE;
		level[1][8] = Square.TREE;
		level[1][9] = Square.TREE;
		level[2][8] = Square.TREE;
		level[3][8] = Square.HOLE;
		level[5][9] = Square.ROCK;
		level[6][9] = Square.ROCK;
		level[7][9] = Square.ROCK;
		level[7][10] = Square.ROCK;
		level[7][11] = Square.ROCK;
		
		return level;
	}
	
	
}


