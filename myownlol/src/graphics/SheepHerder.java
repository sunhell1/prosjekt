package graphics;

import java.awt.Point;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Levels;
import implementation.Board;
import implementation.Player;
import implementation.Sheep;

public class SheepHerder extends Scene {

	private Board board;
	private Sheep[] sheeps;
	private Player herder;

	private boolean winner;

	private StartDisplay sd;
	private WinningDisplay wd;
	private BoardDisplay bd;

	private Group group;

	private int sheepCount;

	private GUI gui;

	public SheepHerder(Group group, int width, int height, GUI gui,
			WinningDisplay wDisplay, StartDisplay sDisplay,
			BoardDisplay bDisplay) {

		super(group, width, height);

		this.group = group;

		this.winner = false;

		this.wd = wDisplay;
		this.sd = sDisplay;
		this.bd = bDisplay;
		
		Sheep sheep1 = new Sheep(new Point(3,5), Direction.EAST, this.board, Condition.ALIVE);
		Sheep sheep2 = new Sheep(new Point(7,8), Direction.WEST, this.board, Condition.ALIVE);
		Sheep sheep3 = new Sheep(new Point(5,10), Direction.EAST, this.board, Condition.ALIVE);
		
		sheeps = new Sheep[3];
		sheeps[0] = sheep1;
		sheeps[1] = sheep2;
		sheeps[2] = sheep3;
		
		this.sheepCount = sheeps.length;
		
		this.gui = gui;

		startScene();

	}

	public void startScene() {
		this.group.getChildren().remove(bd.getGroup());
		this.group.getChildren().add(sd.getStartGroup());
		sd.getStartButton().setOnMouseClicked(event -> mouseClicked(event));

	}

	public void winningScene() {
		this.group.getChildren().removeAll(bd.getGroup());
		this.group.getChildren().removeAll(sd.getStartGroup());
		this.group.getChildren().removeAll(wd.getWinningGroup());
		this.group.getChildren().add(wd.getWinningGroup());
		wd.getWinningButton().setOnMouseClicked(event -> mouseClicked(event));

	}

	public void initateGame(int levelNumber) {

		this.group.getChildren().add(bd.getGroup());

		Point sheepPoint = Constants.sheepStartPoint;
		Point playerPoint = Constants.playerStartPoint;

		this.board = new Board();

		this.herder = new Player(Direction.NORTH, playerPoint, board,
				Condition.ALIVE);


		board.setPlayer(this.herder);
		board.setSheep(sheeps);

		placePlayer();
		placeSheep();

		this.setOnKeyPressed(event -> keyPressed(event));

	}

	public void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			gui.closePrimary();
		} else if (e.getCode() == KeyCode.UP) {
			herder.move(herder.getLocation(), Direction.NORTH);
			bd.moveHerder(herder.getLocation());
			int reply = gameOver();
			if (reply > 0) {

				switch (reply) {
				case 1:
					winningScene();
					break;
				case 2:
					winningScene();
					break;
				default:
					System.out.println("THIS SHOULD NEVER HAPPEN");
					break;
				}
			}
		} else if (e.getCode() == KeyCode.DOWN) {
			herder.move(herder.getLocation(), Direction.SOUTH);
			bd.moveHerder(herder.getLocation());
			int reply = gameOver();
			if (reply > 0) {
				switch (reply) {
				case 1:
					winningScene();
					break;
				case 2:
					winningScene();
					break;
				default:
					System.out.println("THIS SHOULD NEVER HAPPEN");
					break;
				}
			}
		} else if (e.getCode() == KeyCode.LEFT) {
			herder.move(herder.getLocation(), Direction.WEST);
			bd.moveHerder(herder.getLocation());
			int reply = gameOver();
			if (reply > 0) {
				switch (reply) {
				case 1:
					winningScene();
					break;
				case 2:
					winningScene();
					break;
				default:
					System.out.println("THIS SHOULD NEVER HAPPEN");
					break;
				}
			}
		} else if (e.getCode() == KeyCode.RIGHT) {
			herder.move(herder.getLocation(), Direction.EAST);
			bd.moveHerder(herder.getLocation());
			int reply = gameOver();
			if (reply > 0) {
				switch (reply) {
				case 1:
					winningScene();
					break;
				case 2:
					winningScene();
					break;
				default:
					System.out.println("THIS SHOULD NEVER HAPPEN");
					break;
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		int level = 0;
		initateGame(level++);
	}

	public int convertPointToIndex(Point p) {

		int x = p.x;
		int y = p.y;

		int r = (y * Constants.BOARD_WIDTH) + x;

		return r;
	}

	public void placePlayer() {
		bd.drawHerder(herder.getLocation());
	}

	public void placeSheep() {
		for (Sheep sheep : sheeps) {
			bd.drawSheep(sheep.getLocation());
		}
	}

	public int gameOver() {

		for (Sheep sheep : sheeps) {
			if (herder.getLocation().equals(sheep.getLocation())) {
				bd.removeSheep((convertPointToIndex(herder.getLocation())));
				//sheepCount--;
			}
		}
		if (sheepCount == 0){
			return 1;
		}
		

		
		else if (herder.getLives() <= 0) {
			return 2;
		} else
			return 0;
	}
}
