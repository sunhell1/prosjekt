package graphics;

import enums.Condition;
import enums.Direction;
import implementation.Board;
import implementation.Player;
import implementation.Sheep;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class SheepHerder {
	
	private Board board;
	private Sheep sheep;
	private Player herder;

	public SheepHerder(Player player, Sheep sheep, Board board) {
		this.board = board;
		this.herder = player;
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Sheep getSheep() {
		return sheep;
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
			board.updateStatus();
		}
	}
}
