package enums;

import java.awt.Point;

public class Constants {
	public static final int MAX_LIVES = 5;
	public static final int BOARD_WIDTH = 12;
	public static final int BOARD_HEIGHT = 12;

	public static final Point playerStartPoint = new Point(5, 5);
	public static final Point sheepStartPoint = new Point(3, 3);

	public static Square[][] levelOne = Levels.getLevelOne();
	public static Square[][] levelTwo = Levels.getLevelTwo();

}
