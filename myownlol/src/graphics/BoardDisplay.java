package graphics;

import enums.Square;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class BoardDisplay {

	private TilePane tp;
	private Group group;
	private Square[][] board;

	private int width;
	private int height;

	final int PREFERRED_DIM = 50;

	public BoardDisplay(int width, int height, Square[][] board) {

		this.width = width;
		this.height = height;
		this.board = board;

		this.tp = new TilePane();
		this.group = new Group();

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

	public TilePane getTilePane() {

		return this.tp;
	}

	public Group getGroup() {
		return this.group;
	}

}
