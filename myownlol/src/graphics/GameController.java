package graphics;

import implementation.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import implementation.*;
import enums.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController extends Scene {

	private Group root;
	private BoardDisplay board;
	private Game game;

	public GameController(Group root, double width, double height, Game g) {
		super(root, width, height);

		this.root = root;
		this.game = g;

		this.board = new BoardDisplay(Constants.BOARD_WIDTH,
				Constants.BOARD_HEIGHT, game.getBoard().getSquares());

	
		

	}

}
