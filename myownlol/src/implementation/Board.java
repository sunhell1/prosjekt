package implementation;

import java.awt.Point;
import java.util.ArrayList;

import enums.Condition;
import enums.Constants;
import enums.Square;
import implementation.Player;

public class Board implements interfaces.Board {

	private int width;
	private int height;
	private Player player;
	private Square[][] board;
	private ArrayList<Sheep> sheep;

	public Board() {
		this.width = Constants.BOARD_WIDTH;
		this.height = Constants.BOARD_HEIGHT;
		board = new Square[width][height];
	}
	
	public void setSquareAt(int x, int y, Square sq){
		board[x][y] = sq;
		
	}
	
	public Square getSquareAt(int x, int y){
		return board[x][y];
		
	}

	@Override
	public int getWidth() {
		return this.width;

	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public Player getPlayer() {
		return this.player;
	}
	
	public ArrayList<Sheep> getSheep(){
		return this.sheep;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public void setSquares(Square[][] squares){
		this.board = squares;
	}

	public Square[][] getSquares() {
		return this.board;
	}

	public boolean isLocationOutOfBounds(Point p) {

		if (p.x < 0 || p.x >= Constants.BOARD_WIDTH) {
			return true;
		} else if (p.y < 0 || p.y >= Constants.BOARD_HEIGHT) {
			return true;
		}
		return false;
	}

}
