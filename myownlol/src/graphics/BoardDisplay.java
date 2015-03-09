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
	private ImageView board;

	private Image herderImage;
	private Image sheepImage;

	private Level level;

	private int counter = 0;

	final int PREFERRED_DIM = 50;

	public BoardDisplay(int width, int height, Level level) {

		this.level = level;
		this.squares = this.level.getBoardLayout();

		this.square = new GridPane();

		this.group = new Group();

		this.herderImage = Square.HERDER.getImage();
		this.sheepImage = Square.SHEEP.getImage();

		sheepArray = new ImageView[level.getSheepCount()];

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

		for (ImageView iv : sheepArray) {
			group.getChildren().add(iv);
		}
		group.getChildren().add(herder);
	}

	public void drawHerder(Point p) {
		herder.setX(p.x * PREFERRED_DIM);
		herder.setY(p.y * PREFERRED_DIM);
	}

	public void moveHerder(Point p) {
		animateMovement(p);
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

	public void animateMovement(Point p) {

		if (squares[p.y][p.x].equals(Square.BANANA)) {
			Point pa = new Point(p.x + 1, p.y);
			bananaAnimation();
			damageAnimation();
		}
		else if (squares[p.y][p.x].equals(Square.BEER)){
			beerAnimation();

		} else if (herder.getX() == p.x * PREFERRED_DIM
				&& herder.getY() == p.y * PREFERRED_DIM) {
			damageAnimation();
		} else {
			movementAnimation(p);
		}
	}
	
	private void beerAnimation() {
		RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), group);
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
			rotateTransition = new RotateTransition(Duration.millis(1000), sheepArray[i]);
			rotateTransition.setByAngle(180);
			rotateTransition.setCycleCount(1);
			rotateTransition.setAutoReverse(false);
			rotateTransition.play();
		}
		
		rotateTransition = new RotateTransition(Duration.millis(1000), square.getChildren().get(6 * Constants.BOARD_WIDTH +6));
		rotateTransition.setByAngle(180);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(false);
		rotateTransition.play();
			
		}
	

	private void bananaAnimation() {
		RotateTransition rotateTransition = new RotateTransition(
				Duration.millis(1000), herder);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(true);
		rotateTransition.play();
	}

	private void movementAnimation(Point p) {
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

	private void damageAnimation() {
		FadeTransition fadeTransition = new FadeTransition(
				Duration.millis(100), herder);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0.1);
		fadeTransition.setCycleCount(6);
		fadeTransition.setAutoReverse(true);
		fadeTransition.play();
	}

	public GridPane getTilePane() {
		return this.square;
	}

	public Group getGroup() {
		return this.group;
	}

}
