package graphics;

import javafx.scene.image.Image;

import enums.Condition;
import enums.Direction;
import implementation.Board;
import implementation.Player;
import implementation.Sheep;

public class SheepHerder {
	
	private Board board;
	private Sheep sheep;
	private Player herder;

	public SheepHerder(Player player, Sheep sheep, Board board) {
		this.board = board;
		this.herder = player;
		this.sheep = sheep;
	}

	public Board getBoard() {
		return board;
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
			this.board.updateStatus();
		}
	}
	public Image getImageAt(int x , int y) {
		return this.board.getSquareAt(x, y).getImage();
	}
}
