package graphics;

import java.awt.Point;

import implementation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Square;

public class Displays {

	private int width;
	private int height;

	private Square[][] board;

	final static int PREFERRED_DIM = 50;

	private Group group;

	private TilePane tp;

	public Displays(int width, int height) {
		group = new Group();
		this.width = width;
		this.height = height;
		this.board = Constants.levelOne;
		tp = new TilePane();
	}

	public Scene getWinningDisplay() {

		Group group = new Group();

		Button nextLvl = new Button("NEXT LVL!!?");

		TilePane tilePane = new TilePane();

		tilePane = new TilePane();
		tilePane.setHgap(0);
		tilePane.setPrefColumns(1);

		ImageView winnerDisplay = new ImageView();
		winnerDisplay.setImage(Square.WINNER_DISPLAY.getImage());
		winnerDisplay.setFitHeight(600);
		winnerDisplay.setFitWidth(600);

		tilePane.getChildren().add(winnerDisplay);

		group.getChildren().add(tilePane);
		group.getChildren().add(nextLvl);

		Scene scene = new Scene(group, 600, 600);

		return scene;
	}

	public Scene getStartDisplay() {

		Group group = new Group();

		Button startButton = new Button("STARTT");

		TilePane tilePane = new TilePane();

		tilePane = new TilePane();
		tilePane.setHgap(0);
		tilePane.setPrefColumns(1);

		ImageView startDisplay = new ImageView();
		startDisplay.setImage(Square.START_DISPLAY.getImage());
		startDisplay.setFitHeight(600);
		startDisplay.setFitWidth(600);

		tilePane.getChildren().add(startDisplay);

		group.getChildren().add(tilePane);
		group.getChildren().add(startButton);

		Scene scene = new Scene(group, 600, 600);

		return scene;
	}

	public void setLevel(int level) {

		tp.setHgap(0);
		tp.setVgap(0);

		tp.setPrefRows(height);
		tp.setPrefColumns(width);

		tp.setPrefSize(PREFERRED_DIM * width, PREFERRED_DIM * height);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				Image img = board[i][j].getImage();

				ImageView iv = new ImageView();
				iv.setFitHeight(PREFERRED_DIM);
				iv.setFitWidth(PREFERRED_DIM);
				iv.setImage(img);

				tp.getChildren().add(iv);
			}
		}

		Square[] sheepandherder = { Square.HERDER, Square.SHEEP };

		for (Square sq : sheepandherder) {

			Image img = sq.getImage();

			ImageView iv = new ImageView();
			iv.setFitHeight(PREFERRED_DIM);
			iv.setFitWidth(PREFERRED_DIM);
			iv.setImage(img);

			tp.getChildren().add(iv);

		}

		group.getChildren().add(tp);
	}

	public Group getGroup() {
		return this.group;
	}
}
