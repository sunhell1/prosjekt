package graphics;

import implementation.Level;

import java.awt.Point;

import enums.Constants;
import enums.Square;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class BoardDisplay {

	private GridPane square;

	private Group group;

	private Square[][] squares;

	private ImageView herder;
	private ImageView[] sheepArray;
	private ImageView wolf;
	private ImageView[] board;
	private ImageView[] trees;


	private Image herderImage;
	private Image sheepImage;
	private Image wolfImage;
	private Image treeImage;

	private Level level;

	private int sheepCounter = 0;
	private int treeCounter = 0;

	final int PREFERRED_DIM = 50;

	public BoardDisplay(int width, int height, Level level) {

		this.level = level;
		this.squares = this.level.getBoardLayout();

		this.square = new GridPane();

		this.group = new Group();

		this.herderImage = Square.HERDER.getImage();
		this.sheepImage = Square.SHEEP.getImage();
		this.wolfImage = Square.WOLF.getImage();
		this.treeImage = new Image("images/tree.png");

		sheepArray = new ImageView[level.getSheepCount()];
		trees = new ImageView[level.getTreeCount()];
		
		for (int i = 0; i < trees.length; i++) {
			trees[i] = new ImageView();
			trees[i].setFitHeight(PREFERRED_DIM);
			trees[i].setFitWidth(PREFERRED_DIM);
			trees[i].setImage(treeImage);
		}

		for (int i = 0; i < sheepArray.length; i++) {
			sheepArray[i] = new ImageView();
			sheepArray[i].setFitHeight(PREFERRED_DIM);
			sheepArray[i].setFitWidth(PREFERRED_DIM);
			sheepArray[i].setImage(sheepImage);
		}

		herder = new ImageView();
		herder.setFitHeight(PREFERRED_DIM);
		herder.setFitWidth(PREFERRED_DIM);
		herder.setImage(herderImage);
		
		wolf = new ImageView();
		wolf.setFitHeight(PREFERRED_DIM);
		wolf.setFitWidth(PREFERRED_DIM);
		wolf.setImage(wolfImage);

		square.setHgap(0);
		square.setVgap(0);

		square.setPrefHeight(height);
		square.setPrefWidth(width);

		square.setPrefSize(PREFERRED_DIM * width, PREFERRED_DIM * height);
		
		board = new ImageView[Constants.BOARD_HEIGHT * Constants.BOARD_WIDTH];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Point p = new Point(i,j);
				board[convertPointToIndex(p)] = new ImageView();
				board[convertPointToIndex(p)].setFitHeight(PREFERRED_DIM);
				board[convertPointToIndex(p)].setFitWidth(PREFERRED_DIM);
				board[convertPointToIndex(p)].setImage(squares[i][j].getImage());
				square.add(board[convertPointToIndex(p)], i, j);
			}
		}
		
		
		group.getChildren().addAll(square);

		for (ImageView iv : sheepArray) {
			group.getChildren().add(iv);
		}
		for (ImageView iv : trees) {
			group.getChildren().add(iv);
		}
		group.getChildren().add(herder);
		group.getChildren().add(wolf);
	}

	public void drawHerder(Point p) {
		herder.setX(p.x * PREFERRED_DIM);
		herder.setY(p.y * PREFERRED_DIM);
	}
	
	public void drawWolf(Point p) {
		wolf.setX(p.x * PREFERRED_DIM);
		wolf.setY(p.y * PREFERRED_DIM);
	}
	
	public void drawTrees(Point p) {
		trees[treeCounter].setX(p.x * PREFERRED_DIM);
		trees[treeCounter++].setY(p.y * PREFERRED_DIM);
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
		sheepArray[sheepCounter].setX(p.x * PREFERRED_DIM);
		sheepArray[sheepCounter++].setY(p.y * PREFERRED_DIM);
	}

	public void beerAnimation() {
		RotateTransition rotateTransition = new RotateTransition(
				Duration.millis(1000), group);
		rotateTransition.setByAngle(180);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(false);
		rotateTransition.play();

		rotateTransition = new RotateTransition(Duration.millis(1000), herder);
		rotateTransition.setByAngle(180);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(false);
		rotateTransition.play();

		for (int i = 0; i < sheepArray.length; i++) {
			rotateTransition = new RotateTransition(Duration.millis(1000),
					sheepArray[i]);
			rotateTransition.setByAngle(180);
			rotateTransition.setCycleCount(1);
			rotateTransition.setAutoReverse(false);
			rotateTransition.play();
		}

//		rotateTransition = new RotateTransition(Duration.millis(1000), square
//				.getChildren().get(6 * Constants.BOARD_WIDTH + 6));
//		rotateTransition.setByAngle(180);
//		rotateTransition.setCycleCount(1);
//		rotateTransition.setAutoReverse(false);
//		rotateTransition.play();
		
		for (int i = 0; i < treeCounter; i++){
			rotateTransition = new RotateTransition(Duration.millis(1000), trees[i]);
			rotateTransition.setByAngle(180);
			rotateTransition.setCycleCount(1);
			rotateTransition.setAutoReverse(false);
			rotateTransition.play();	
		}	
	}

	public void bananaAnimation() {
		RotateTransition rotateTransition = new RotateTransition(
				Duration.millis(1000), herder);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(true);
		rotateTransition.play();
	}

	public void animateMovement(Point p) {
		Path path = new Path();

		double px = p.x * PREFERRED_DIM;
		double py = p.y * PREFERRED_DIM;

		path.getElements().add(
				new MoveTo(herder.getX() + PREFERRED_DIM / 2, herder.getY()
						+ PREFERRED_DIM / 2));
		path.getElements().add(
				new LineTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));
		path.getElements().add(
				new MoveTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));

		PathTransition pathTransition = new PathTransition(
				Duration.millis(250), path, herder);

		pathTransition.play();

		herder.setX(px);
		herder.setY(py);
	}
	
	public void animateWolfMovement(Point p){
		Path path = new Path();
		double px = p.x * PREFERRED_DIM;
		double py = p.y * PREFERRED_DIM;
		
		path.getElements().add(new MoveTo(wolf.getX() + PREFERRED_DIM / 2, wolf.getY() + PREFERRED_DIM / 2));
		path.getElements().add(new LineTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));
		path.getElements().add(new MoveTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));
		
		PathTransition pt = new PathTransition(Duration.millis(250), path, wolf);
		pt.play();
		
		wolf.setX(px);
		wolf.setY(py);
	}

	public void animateToStart(Point p) {
		
		int px = p.x * PREFERRED_DIM;
		int py = p.y * PREFERRED_DIM;

		Path path = new Path();
		path.getElements().add(
				new MoveTo(herder.getX() + PREFERRED_DIM / 2, herder.getY()
						+ PREFERRED_DIM / 2));
		path.getElements().add(new LineTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM/2));
		path.getElements().add(new MoveTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM/2));
		
		PathTransition pt = new PathTransition(Duration.millis(1000), path, herder);
		pt.play();
		
		herder.setX(px);
		herder.setY(py);
	}

	public void damageAnimation() {
		FadeTransition fadeTransition = new FadeTransition(
				Duration.millis(100), herder);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0.1);
		fadeTransition.setCycleCount(6);
		fadeTransition.setAutoReverse(true);
		fadeTransition.play();
	}
	
	
	public void updateSquareAt(Point p){
		this.board[convertPointToIndex(p)].setImage(level.getSquareAt(p).getImage());
	}

	public GridPane getGridPane() {
		return this.square;
	}

	public Group getGroup() {
		return this.group;
	}
	
	private int convertPointToIndex(Point p) {
		
		int a = (p.y * Constants.BOARD_WIDTH) + p.x;
		
		return a;	

	}

}
