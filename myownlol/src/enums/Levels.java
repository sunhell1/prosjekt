package enums;

public class Levels {

	public Levels() {

	}

	public static Square[][] getLevelOne() {

		Square[][] temp = new Square[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];

		for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
			for (int j = 0; j < Constants.BOARD_WIDTH; j++) {

				temp[i][j] = Square.GRASS;

			}

		}

		return temp;

	}

	public static Square[][] getLevelTwo() {

		Square[][] temp = new Square[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];

		for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
			for (int j = 0; j < Constants.BOARD_WIDTH; j++) {

				temp[i][j] = Square.HERDER;

			}

		}

		return temp;

	}

}
