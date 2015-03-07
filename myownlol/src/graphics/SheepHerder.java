package graphics;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import implementation.Board;
import implementation.Player;
import implementation.Sheep;

public class SheepHerder {
	
	private Board board;
	private Sheep sheep;
	private Player herder;
	private boolean winner;
	private Displays displays;
	private Group group;
	private Scene scene;

	public SheepHerder(Displays displays) {
	
		this.winner = false;
		this.displays = displays;
		group = new Group();
	}

	public Board getBoard() {
		return board;
	}
	
	public boolean isWinningConiditions(){
		board.updateStatus();
		return (this.sheep.getCondition().equals(Condition.CAUGHT));
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Sheep getSheep() {
		return this.sheep;
	}

	public void setSheep(Sheep sheep) {
		this.sheep = sheep;
	}

	public Player getPlayer() {
		return this.herder;
	}

	public void setPlayer(Player player) {
		this.herder = player;
	}

	public void startSheepHerder() {
		while(sheep.getCondition().equals(Condition.ALIVE)) {
			
			scene.setOnKeyPressed(event -> keyPressed(event));
			scene.setOnKeyTyped(event -> keyTyped(event));
			scene.setOnKeyReleased(event -> keyReleased(event));
			
		}
	}
	public Image getImageAt(int x , int y) {
		return this.board.getSquareAt(x, y).getImage();
	}
	
	public void initateGame() {
		Point sheepPoint = Constants.sheepStartPoint;
		Point playerPoint = Constants.playerStartPoint;

		this.board = new Board();

		this.herder = new Player(Direction.NORTH, playerPoint, board,
				Condition.ALIVE);

		this.sheep = new Sheep(sheepPoint, Direction.SOUTH, board,
				Condition.ALIVE);
		
		board.setPlayer(this.herder);
		board.setSheep(this.sheep);
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			System.exit(0); 						// TO DO - KA FAEN GJER VI HER
		} else if (e.getCode() == KeyCode.UP) {
			getPlayer().move(getPlayer().getLocation(), Direction.NORTH);
		} else if (e.getCode() == KeyCode.DOWN) {
			getPlayer().move(getPlayer().getLocation(), Direction.SOUTH);
		} else if (e.getCode() == KeyCode.LEFT) {
			getPlayer().move(getPlayer().getLocation(), Direction.WEST);
		} else if (e.getCode() == KeyCode.RIGHT) {
			getPlayer().move(getPlayer().getLocation(), Direction.EAST);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
