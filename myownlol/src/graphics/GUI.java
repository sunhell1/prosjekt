package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.KeyStroke;

import enums.Constants;
import enums.Square;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {

	private final int PREFERRED_DIM = 50;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Group root = new Group();

		TilePane tiles = new TilePane();
		TilePane playersheep = new TilePane();

		tiles.setVgap(0);
		tiles.setHgap(0);

		tiles.setPrefRows(12);
		tiles.setPrefColumns(12);

		tiles.setPrefSize(PREFERRED_DIM * Constants.BOARD_WIDTH, PREFERRED_DIM
				* Constants.BOARD_HEIGHT);

		playersheep.setVgap(0);
		playersheep.setHgap(0);
		playersheep.setPrefRows(Constants.BOARD_HEIGHT);
		playersheep.setPrefColumns(Constants.BOARD_WIDTH);

		for (int i = 0; i < 144; i++) {

			if (i == 55) {
				ImageView iv1 = new ImageView();
				iv1.setFitHeight(PREFERRED_DIM);
				iv1.setFitWidth(PREFERRED_DIM);
				iv1.setImage(Square.HERDER.getImage());
				playersheep.getChildren().add(iv1);

			}

			else if (i == 132) {
				ImageView iv1 = new ImageView();
				iv1.setFitHeight(PREFERRED_DIM);
				iv1.setFitWidth(PREFERRED_DIM);
				iv1.setImage(Square.SHEEP.getImage());
				playersheep.getChildren().add(iv1);

			}

			else {

				ImageView iv1 = new ImageView();
				iv1.setFitHeight(PREFERRED_DIM);
				iv1.setFitWidth(PREFERRED_DIM);
				iv1.setImage(null);
				playersheep.getChildren().add(iv1);
			}
		}

		for (int i = 0; i < 144; i++) {
			ImageView iv = new ImageView();
			iv.setFitWidth(PREFERRED_DIM);
			iv.setFitHeight(PREFERRED_DIM);
			iv.setImage(Square.GRASS.getImage());

			tiles.getChildren().add(iv);
		}

		root.getChildren().add(tiles);
		root.getChildren().add(playersheep);
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setTitle("SheepHerder");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
