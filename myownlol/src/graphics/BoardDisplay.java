package graphics;

import implementation.Level;
import implementation.Sheep;
import implementation.Wolf;

import java.awt.Point;
import java.util.ArrayList;

import Sounds.GameSounds;
import enums.Constants;
import enums.Square;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class BoardDisplay extends Group {

	private GridPane square;

	private Square[][] squares;

	ArrayList<Sheep> sheeps;

	private ImageView herder;
	private Wolf wolf;
	private ImageView[] board;
	private ImageView[] trees;

	private Image herderImage;
	private Image sheepImage;
	private Image wolfImage;
	private Image treeImage;
	private Image herderPickingRock;
	private Image herderPickAxe;
	private Image herderSmack;
	private Image vfence;
	private Image hfence;

	private Level level;

	private SheepHerder sheepHerder;

	private int sheepCounter = 0;
	private int treeCounter = 0;

	final int PREFERRED_DIM = 50;

	public BoardDisplay(int width, int height, Level level, SheepHerder sh) {

		this.level = level;
		this.squares = this.level.getBoardLayout();

		this.square = new GridPane();

		this.sheeps = sh.getSheepArray();

		this.sheepHerder = sh;

		this.herderImage = Square.HERDER.getImage();
		this.sheepImage = Square.BIGSHEEP.getImage();
		this.wolfImage = Square.WOLF.getImage();
		this.treeImage = Square.TREE_IMAGE.getImage();
		this.herderPickingRock = Square.HERDER_PICKAXE.getImage();
		this.herderPickAxe = Square.PICKAXE.getImage();
		this.herderSmack = Square.HERDER_SMACK.getImage();
		this.hfence = Square.HFENCE.getImage();
		this.vfence = Square.VFENCE.getImage();

		trees = new ImageView[level.getTreeCount()];

		for (int i = 0; i < trees.length; i++) {
			trees[i] = new ImageView();
			trees[i].setFitHeight(PREFERRED_DIM);
			trees[i].setFitWidth(PREFERRED_DIM);
			trees[i].setImage(treeImage);
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

		board = new ImageView[Constants.BOARD_HEIGHT * Constants.BOARD_WIDTH];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Point p = new Point(i, j);
				board[convertPointToIndex(p)] = new ImageView();
				board[convertPointToIndex(p)].setFitHeight(PREFERRED_DIM);
				board[convertPointToIndex(p)].setFitWidth(PREFERRED_DIM);
				board[convertPointToIndex(p)]
						.setImage(squares[i][j].getImage());
				square.add(board[convertPointToIndex(p)], i, j);
			}
		}

		this.getChildren().addAll(square);

		for (ImageView iv : trees) {
			this.getChildren().add(iv);
		}
		this.getChildren().add(herder);

		if (level.hasWolf()) {
			this.getChildren().add(wolf);
		}
	}

	public void drawHerder(Point p) {
		herder.setX(p.x * PREFERRED_DIM);
		herder.setY(p.y * PREFERRED_DIM);
	}
	
//	public void drawHP(Point p, Rectangle HP) {
//		HP.setX(p.x * PREFERRED_DIM);
//		HP.setY(p.y * PREFERRED_DIM);
//	}

	public void drawWolf(Point p) {
		wolf.setX(p.x * PREFERRED_DIM);
		wolf.setY(p.y * PREFERRED_DIM);
	}

	public void drawSheep(Sheep sheep) {
		sheep.setX(sheep.getLocation().x * PREFERRED_DIM);
		sheep.setY(sheep.getLocation().y * PREFERRED_DIM);
		this.getChildren().add(sheep);
	}

	public void drawTrees(Point p) {
		trees[treeCounter].setX(p.x * PREFERRED_DIM);
		trees[treeCounter++].setY(p.y * PREFERRED_DIM);
	}

	// public void beerAnimation() {
	// RotateTransition rotateTransition = new RotateTransition(
	// Duration.millis(1000), group);
	// rotateTransition.setByAngle(180);
	// rotateTransition.setCycleCount(1);
	// rotateTransition.setAutoReverse(false);
	// rotateTransition.play();
	//
	// rotateTransition = new RotateTransition(Duration.millis(1000), herder);
	// rotateTransition.setByAngle(180);
	// rotateTransition.setCycleCount(1);
	// rotateTransition.setAutoReverse(false);
	// rotateTransition.play();
	//
	// for (int i = 0; i < sheepArray.length; i++) {
	// rotateTransition = new RotateTransition(Duration.millis(1000),
	// sheepArray[i]);
	// rotateTransition.setByAngle(180);
	// rotateTransition.setCycleCount(1);
	// rotateTransition.setAutoReverse(false);
	// rotateTransition.play();
	// }
	//
	// for (int i = 0; i < treeCounter; i++) {
	// rotateTransition = new RotateTransition(Duration.millis(1000),
	// trees[i]);
	// rotateTransition.setByAngle(180);
	// rotateTransition.setCycleCount(1);
	// rotateTransition.setAutoReverse(false);
	// rotateTransition.play();
	// }
	// }

	public void iceSquareAnimation(Point startPoint, Point endPoint,
			boolean animateToStart) {

		sheepHerder.setBlocked(true);

		boolean aniToStart = animateToStart;

		Path path = new Path();
		path.getElements().add(
				new MoveTo(startPoint.x * PREFERRED_DIM + PREFERRED_DIM / 2,
						startPoint.y * PREFERRED_DIM + PREFERRED_DIM / 2));
		path.getElements().add(
				new LineTo(endPoint.x * PREFERRED_DIM + PREFERRED_DIM / 2,
						endPoint.y * PREFERRED_DIM + PREFERRED_DIM / 2));
		path.getElements().add(
				new MoveTo(endPoint.x * PREFERRED_DIM + PREFERRED_DIM / 2,
						endPoint.y * PREFERRED_DIM + PREFERRED_DIM / 2));

		PathTransition pt = new PathTransition(Duration.millis(1500), path,
				herder);

		pt.play();

		RotateTransition rotateTransition = new RotateTransition(
				Duration.millis(1500), herder);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(true);
		rotateTransition.play();

		herder.setX(endPoint.x * PREFERRED_DIM);
		herder.setY(endPoint.y * PREFERRED_DIM);

		if (aniToStart == true) {
			pt.setOnFinished(event -> slideOutOfMap());
		}

		else {
			pt.setOnFinished(event -> sheepHerder.setBlocked(false));
			herder.setX(endPoint.x * PREFERRED_DIM);
			herder.setY(endPoint.y * PREFERRED_DIM);
		}
	}

	public void sheepIceSquareAnimation(Point startPoint, Point endPoint,
			Sheep sheep, boolean sheepDied) {

		sheepHerder.setBlocked(true);

		boolean sheepDead = sheepDied;

		Path path = new Path();
		path.getElements().add(
				new MoveTo(startPoint.x * PREFERRED_DIM + PREFERRED_DIM / 2,
						startPoint.y * PREFERRED_DIM + PREFERRED_DIM / 2));
		path.getElements().add(
				new LineTo(endPoint.x * PREFERRED_DIM + PREFERRED_DIM / 2,
						endPoint.y * PREFERRED_DIM + PREFERRED_DIM / 2));
		path.getElements().add(
				new MoveTo(endPoint.x * PREFERRED_DIM + PREFERRED_DIM / 2,
						endPoint.y * PREFERRED_DIM + PREFERRED_DIM / 2));

		PathTransition pt = new PathTransition(Duration.millis(1500), path,
				sheep);

		pt.play();

		RotateTransition rotateTransition = new RotateTransition(
				Duration.millis(1500), sheep);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(true);
		rotateTransition.play();

		sheep.setX(endPoint.x * PREFERRED_DIM);
		sheep.setY(endPoint.y * PREFERRED_DIM);

		if (sheepDead == true) {
			pt.setOnFinished(event -> sheepKilledAnimation(sheep));
		}

		else {
			pt.setOnFinished(event -> sheepHerder.setBlocked(false));
			sheep.setX(endPoint.x * PREFERRED_DIM);
			sheep.setY(endPoint.y * PREFERRED_DIM);
		}
	}

	private void slideOutOfMap() {

		Timeline slideLine = new Timeline();

		slideLine.setCycleCount(1);
		slideLine.setAutoReverse(false);

		KeyFrame ani1 = new KeyFrame(Duration.millis(200),
				event -> animateToStart(level.getHerderStart()));
		System.out.println("ANIMERER TIL START HER!");
		KeyFrame ani2 = new KeyFrame(Duration.millis(500),
				event -> damageAnimation());
		System.out.println("ANIMERER DAMAGEANIMATION");

		slideLine.getKeyFrames().add(ani1);
		slideLine.getKeyFrames().add(ani2);

		slideLine.play();

	}

	public void animateHerderMovement(Point p) {

		sheepHerder.setBlocked(true);

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
				Duration.millis(150), path, herder);

		pathTransition.play();

		herder.setX(px);
		herder.setY(py);

		pathTransition.setOnFinished(event -> sheepHerder.setBlocked(false));

	}
	
	public void animateHPMovement(Rectangle HP, Point p) {

		Path path = new Path();

		double px = p.x * PREFERRED_DIM;
		double py = p.y * PREFERRED_DIM-25;

		path.getElements().add(
				new MoveTo(HP.getX() + PREFERRED_DIM / 2, HP.getY()
						+ PREFERRED_DIM / 2));
		path.getElements().add(
				new LineTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));
		path.getElements().add(
				new MoveTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));

		PathTransition pathTransition = new PathTransition(
				Duration.millis(150), path, HP);

		pathTransition.play();

		HP.setX(px);
		HP.setY(py);
		

		pathTransition.setOnFinished(event -> setHPvisibility(HP, true));

	}

	public void animateWolfMovement(Point p) {
		Path path = new Path();
		double px = p.x * PREFERRED_DIM;
		double py = p.y * PREFERRED_DIM;

		path.getElements().add(
				new MoveTo(wolf.getX() + PREFERRED_DIM / 2, wolf.getY()
						+ PREFERRED_DIM / 2));
		path.getElements().add(
				new LineTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));
		path.getElements().add(
				new MoveTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));

		PathTransition pt = new PathTransition(Duration.millis(250), path, wolf);
		pt.play();

		wolf.setX(px);
		wolf.setY(py);
	}

	public void animateToStart(Point p) {

		sheepHerder.setBlocked(true);

		if (herder.getX() == 0 && herder.getY() == 0) {

			Path path = new Path();
			path.getElements().add(new MoveTo(0, 0));
			path.getElements().add(new LineTo(25, 25));
			path.getElements().add(new MoveTo(25, 25));

			PathTransition pt = new PathTransition(Duration.millis(1000), path,
					herder);
			pt.play();

			pt.setOnFinished(event -> sheepHerder.setBlocked(false));

			herder.setX(0);
			herder.setY(0);
		}

		else {
			int px = p.x * PREFERRED_DIM;
			int py = p.y * PREFERRED_DIM;

			Path path = new Path();
			path.getElements().add(
					new MoveTo(herder.getX() + PREFERRED_DIM / 2, herder.getY()
							+ PREFERRED_DIM / 2));
			path.getElements().add(
					new LineTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));
			path.getElements().add(
					new MoveTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM / 2));

			System.out.println("HERDER SIN POS: x= " + herder.getX() + " y= "
					+ herder.getY());

			PathTransition pt = new PathTransition(Duration.millis(1000), path,
					herder);
			pt.play();

			pt.setOnFinished(event -> sheepHerder.setBlocked(false));

			herder.setX(level.getHerderStart().x * PREFERRED_DIM);
			herder.setY(level.getHerderStart().y * PREFERRED_DIM);

			System.out.println("HERDER SIN POS: x= " + herder.getX() + " y= "
					+ herder.getY());
		}
	}
	
	public void loseHP(Rectangle HPbar, int hp) {
		HPbar.setWidth(HPbar.getWidth()-hp);
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

	public void pickRockAnimation() {

		sheepHerder.setBlocked(true);

		Timeline timeline = new Timeline();
		timeline.setCycleCount(2);
		timeline.setAutoReverse(true);
		KeyFrame kf = new KeyFrame(Duration.millis(200),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						changeHerderImage(herderPickingRock);
					}
				});

		KeyFrame kk = new KeyFrame(Duration.millis(400),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						changeHerderImage(herderPickAxe);
					}

				});
		timeline.getKeyFrames().add(kf);
		timeline.getKeyFrames().add(kk);
		timeline.play();

		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				changeHerderImage(herderPickAxe);
				sheepHerder.setBlocked(false);

			}
		});
	}

	public void smackAnimation() {
		Timeline smackLine = new Timeline();
		smackLine.setCycleCount(1);
		smackLine.setAutoReverse(true);

		KeyFrame smack = new KeyFrame(Duration.millis(0),
				event -> changeHerderImage(herderSmack));
		KeyFrame reset = new KeyFrame(Duration.millis(100),
				event -> changeHerderImage(herderImage));

		smackLine.getKeyFrames().add(smack);
		smackLine.getKeyFrames().add(reset);
		smackLine.play();
	}

	public void updateSquareAt(Point p, Square sq) {
		square.getChildren().remove(board[convertPointToIndex(p)]);
		board[convertPointToIndex(p)] = new ImageView();
		board[convertPointToIndex(p)].setFitHeight(PREFERRED_DIM);
		board[convertPointToIndex(p)].setFitWidth(PREFERRED_DIM);
		board[convertPointToIndex(p)].setImage(sq.getImage());
		square.add(board[convertPointToIndex(p)], p.x, p.y);
	}

	public GridPane getGridPane() {
		return this.square;
	}

	public Group getGroup() {
		return this;
	}

	private int convertPointToIndex(Point p) {

		int a = (p.y * Constants.BOARD_WIDTH) + p.x;

		return a;

	}

	public void changeHerderImage(Image img) {
		herder.setImage(img);
	}
	
	public Rectangle hpBar(int hp) {
		Rectangle r = new Rectangle();
		r.setWidth(hp);
		r.setHeight(5);
		r.setFill(Color.GREEN);
		this.getChildren().add(r);
		return r;
	}

	public void animateSheepMovement(Point location, Point destination) {

		for (int i = 0; i < sheeps.size(); i++) {
			if (sheeps.get(i).getLocation().equals(location)) {
				Path path = new Path();

				double px = destination.x * PREFERRED_DIM;
				double py = destination.y * PREFERRED_DIM;

				path.getElements().add(
						new MoveTo(sheeps.get(i).getX() + PREFERRED_DIM / 2,
								sheeps.get(i).getY() + PREFERRED_DIM / 2));
				path.getElements().add(
						new LineTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM
								/ 2));
				path.getElements().add(
						new MoveTo(px + PREFERRED_DIM / 2, py + PREFERRED_DIM
								/ 2));

				PathTransition pathTransition = new PathTransition(
						Duration.millis(150), path, sheeps.get(i));

				pathTransition.play();

				sheeps.get(i).setX(px);
				sheeps.get(i).setY(py);
			}
		}
	}
	
	public void sheepKilledAnimation(Sheep sheep) {
		
		sheepHerder.setBlocked(true);
		GameSounds.playBaa();
		RotateTransition rotateTransition = new RotateTransition(
				Duration.millis(1500), sheep);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(true);
		rotateTransition.play();
		
		rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sheepHerder.loserScene();
			}
		});
	}


	public void chatDisplay(String stringtext) {

		Text text = new Text();
		text.setText(stringtext);
		text.setFont(new Font(15));
		text.setTextAlignment(TextAlignment.CENTER);

		text.setWrappingWidth(100);

		if (herder.getX() >= 500) {

			text.setLayoutX(herder.getX() - 100);
			text.setLayoutY(herder.getY());

		}

		else if (herder.getX() <= 100) {
			text.setLayoutX(herder.getX() + 100);
			text.setLayoutY(herder.getY());
		}

		else if (herder.getY() >= 500) {
			text.setLayoutX(herder.getX());
			text.setLayoutY(herder.getY() - 100);

		}

		else if (herder.getY() <= 100) {
			text.setLayoutX(herder.getX());
			text.setLayoutY(herder.getY() + 100);

		}

		else {
			text.setLayoutX(herder.getX());
			text.setLayoutY(herder.getY());

		}

		this.getChildren().add(text);

		FadeTransition ft = new FadeTransition(Duration.millis(1500), text);
		ft.setAutoReverse(false);
		ft.setCycleCount(1);
		ft.setFromValue(1);
		ft.setToValue(1);
		ft.play();

		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				text.setVisible(false);
				text.setDisable(true);
			}
		});

	}
	
	public void setHPvisibility(Rectangle HP, boolean show) {
		HP.setVisible(show);
	}

}
