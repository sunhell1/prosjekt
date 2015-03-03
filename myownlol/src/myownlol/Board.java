package myownlol;

import java.awt.Point;

import enums.Constants;
import enums.Square;
import interfaces.Player;

public class Board implements interfaces.Board {

	private int width;
	private int height;
	private Player player;
	private Square[][] board;

	public Board() {
		this.width = Constants.BOARD_WIDTH;
		this.height = Constants.BOARD_HEIGHT;
		this.player = player;
		board = new Square[width][height];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = Square.BASIC;
			}
		}
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

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Square[][] getBoard() {
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
