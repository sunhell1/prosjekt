package graphics;

import javafx.application.Application;
import javafx.stage.Stage;
import implementation.*;
import implementation.Board;
import implementation.Game;
import implementation.Player;
import implementation.Sheep;
import enums.*;
import interfaces.*;
import images.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Point;

public class GUI extends Application {

	private Game game;

	public static void main(String[] args) { // her starter main (???)
		Application.launch(args);
	}

	private Stage primaryStage;
	private Scene splash;

	private GameController gamecontroller;
	private double gameX;
	private double gameY;

	@Override
	public void start(Stage primaryStage) throws Exception {
		initiateModel();

		try {
			this.primaryStage = primaryStage;

			double screenWidth = java.awt.Toolkit.getDefaultToolkit()
					.getScreenSize().width;
			double screenHeight = java.awt.Toolkit.getDefaultToolkit()
					.getScreenSize().height;
			
			double gameWidth = 19 * screenWidth / 20;
			double gameHeight = 4 * screenHeight / 5;
			
			this.gameX = (screenWidth - gameWidth) / 2;
			this.gameY = (screenHeight - gameHeight) / 2;
			
			this.gamecontroller = new GameController(new Group(), gameWidth, gameHeight, this.game);
			

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initiateModel() {
		Point startPlayerPosition = new Point(2, 5);
		Point startSheepPosition = new Point(10, 10);

		Board board = new Board();

		Player fredrik = new Player(Direction.EAST, startPlayerPosition, board,
				Condition.ALIVE);
		Sheep rodmundur = new Sheep(startSheepPosition, Direction.WEST, board,
				Condition.ALIVE);

		board.setPlayer(fredrik);
		board.setSheep(rodmundur);

		game = new Game(fredrik, rodmundur, board);

	}

	public void startGame() {
		primaryStage.setScene(gamecontroller);
		primaryStage.setX(this.gameX);
		primaryStage.setY(this.gameY);
	}

}
