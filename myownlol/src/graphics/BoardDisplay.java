package graphics;

import java.awt.Point;

import enums.Square;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class BoardDisplay {

	private GridPane square;
	
	private Group group;

	private Square[][] board;

	private ImageView herder;
	private ImageView sheep;
	private ImageView grass;

	private Image herderImage;
	private Image sheepImage;
	private Image grassImage;

	private int width;
	private int height;

	final int PREFERRED_DIM = 50;

	public BoardDisplay(int width, int height, Square[][] board) {

		this.width = width;
		this.height = height;
		this.board = board;
		
		this.square = new GridPane();
		TilePane k = new TilePane();

		this.group = new Group(); 

		herderImage = Square.HERDER.getImage();
		sheepImage = Square.SHEEP.getImage();
		grassImage = Square.GRASS.getImage();

		herder = new ImageView();
		herder.setFitHeight(PREFERRED_DIM);
		herder.setFitWidth(PREFERRED_DIM);
		herder.setImage(herderImage);

		square.setHgap(0);
		square.setVgap(0);

		square.setPrefHeight(height);
		square.setPrefWidth(width);
		
		square.setPrefSize(PREFERRED_DIM * width, PREFERRED_DIM * height);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grass = new ImageView();
				grass.setFitHeight(PREFERRED_DIM);
				grass.setFitWidth(PREFERRED_DIM);
				grass.setImage(grassImage);
				square.add(grass, j, i);
			}
		}
		group.getChildren().add(square);
	}

	public void drawHerder(Point p) {
		square.getChildren().removeAll(herder);
		square.add(herder, p.x, p.y);
	}

	public void moveHerder(Point p) {
		square.getChildren().remove(herder);
		square.add(herder, p.x, p.y);
	}
	
	public void removeSheep(int index) {
		square.getChildren().remove(index);
		square.getChildren().remove(index);
		
	}

	public void drawSheep(Point p) {
		sheep = new ImageView();
		sheep.setFitHeight(PREFERRED_DIM);
		sheep.setFitWidth(PREFERRED_DIM);
		sheep.setImage(sheepImage);
		square.add(sheep, p.x, p.y);
		
//		square.getChildren().removeAll(sheep);
	}

	public GridPane getGridPane() {
		return this.square;
	}

	public Group getGroup() {
		return this.group;
	}
	
	public ImageView getSheep() {
		return this.sheep;
	}

}
