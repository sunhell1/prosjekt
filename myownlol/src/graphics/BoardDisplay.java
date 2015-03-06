package graphics;

import implementation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import enums.Constants;
import enums.Square;

public class BoardDisplay {

	private int width;
	private int height;
	private Square[][] board;
	private SheepHerder sheepherder;
	final static int PREFERRED_DIM = 50;
	private Group group;

	public BoardDisplay(int width, int height, SheepHerder sheepherder) {
		group = new Group();
		this.width = width;
		this.height = height;
		this.board = Constants.levelOne;
		this.sheepherder = sheepherder;

		TilePane tp = new TilePane();
		TilePane sheepandplayer = new TilePane();

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

		sheepandplayer.setHgap(0);
		sheepandplayer.setVgap(0);

		sheepandplayer.setPrefRows(height);
		sheepandplayer.setPrefColumns(width);

		sheepandplayer.setPrefSize(PREFERRED_DIM * width, PREFERRED_DIM
				* height);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				if (i == sheepherder.getPlayer().getLocation().y
						&& j == sheepherder.getPlayer().getLocation().x) {

					Image img = Square.HERDER.getImage();
					ImageView iv = new ImageView();
					iv.setFitHeight(PREFERRED_DIM);
					iv.setFitWidth(PREFERRED_DIM);
					iv.setImage(img);

					sheepandplayer.getChildren().add(iv);
				}

				else if (i == sheepherder.getSheep().getX()
						&& j == sheepherder.getSheep().getY()) {

					Image img = Square.SHEEP.getImage();
					ImageView iv = new ImageView();
					iv.setFitHeight(PREFERRED_DIM);
					iv.setFitWidth(PREFERRED_DIM);
					iv.setImage(img);

					sheepandplayer.getChildren().add(iv);

				}

				else {

					Image img = sheepherder.getImageAt(j, i);
					ImageView iv = new ImageView();
					iv.setFitHeight(PREFERRED_DIM);
					iv.setFitWidth(PREFERRED_DIM);
					iv.setImage(img);

					sheepandplayer.getChildren().add(iv);

				}

			}

		}

		group.getChildren().add(tp);
		group.getChildren().add(sheepandplayer);

	}

	public Group getGroup() {
		return this.group;
	}

	public SheepHerder getSheepHerder() {
		return this.sheepherder;
	}
}
