package graphics;

import implementation.Level;


import java.awt.Point;

import enums.Square;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class BoardDisplay {

	private GridPane square;
	
	private Group group;

	private Square[][] squares;

	private ImageView herder;
	private ImageView[] sheepArray;
	private ImageView board;

	private Image herderImage;
	private Image sheepImage;
 
	private Level level;
	
	private PathTransition pathTransition;
	private Path path;
	
	private int counter = 0;

	final int PREFERRED_DIM = 50;

	public BoardDisplay(int width, int height, Level level) {
		
		pathTransition = new PathTransition();
		path = new Path();

		
		this.level = level;
		this.squares = this.level.getBoardLayout();
	
		this.square = new GridPane();
	
		this.group = new Group(); 

		this.herderImage = Square.HERDER.getImage();
		this.sheepImage = Square.SHEEP.getImage();
		
		
		sheepArray = new ImageView[level.getSheepCount()];
	
		for (int i = 0; i < sheepArray.length; i++){
			sheepArray[i] = new ImageView();
			sheepArray[i].setFitHeight(PREFERRED_DIM);
			sheepArray[i].setFitWidth(PREFERRED_DIM);
			sheepArray[i].setImage(sheepImage);
		}

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
				board = new ImageView();
				board.setFitHeight(PREFERRED_DIM);
				board.setFitWidth(PREFERRED_DIM);
				board.setImage(squares[i][j].getImage());
				square.add(board, i, j);
			}
		}
		group.getChildren().addAll(square);
		
		for (ImageView iv : sheepArray){
			
			group.getChildren().add(iv);
		}
		
		group.getChildren().add(herder);
		group.getChildren().add(path);
		
	}

	public void drawHerder(Point p) {
		herder.setX(p.x * PREFERRED_DIM);
		herder.setY(p.y * PREFERRED_DIM);
	}

	public void moveHerder(Point p) {
		animateMovement(p.x * PREFERRED_DIM, p.y * PREFERRED_DIM);
		herder.relocate(p.x * PREFERRED_DIM, p.y * PREFERRED_DIM);
	}
	
	public void removeSheep(Point p) {
		for (ImageView img : sheepArray) {

			if (img.getX() == p.getX() * PREFERRED_DIM
					&& img.getY() == p.getY() * PREFERRED_DIM) {

				group.getChildren().remove(img);			

			}
		}
	}

	public void drawSheep(Point p) {
		sheepArray[counter].setX(p.x * PREFERRED_DIM);
		sheepArray[counter++].setY(p.y * PREFERRED_DIM);
	}
	
	public void animateMovement(double x, double y){
		System.out.println("HER");
		double px = x;
		double py = y;
	
		path.getElements().add(new MoveTo(px,py));
	
		
		pathTransition.setDuration(Duration.millis(5000));
		pathTransition.setPath(path);
		pathTransition.setNode(herder);
		pathTransition.setOrientation(OrientationType.NONE);
		pathTransition.setCycleCount(1);
		pathTransition.setAutoReverse(false);
		pathTransition.play();
		
		System.out.println(pathTransition.getNode().toString());
		System.out.println("DER");
	}
	

	public GridPane getTilePane() {
		return this.square;
	}

	public Group getGroup() {
		return this.group;
	}
	
}
