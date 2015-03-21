package graphics;

import javafx.scene.Group;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import enums.Square;

public class BackgroundDisplay {

	TilePane tilePane;

	ImageView backgroundImage;

	public BackgroundDisplay() {

		this.tilePane = new TilePane();

		this.tilePane = new TilePane();
		this.tilePane.setHgap(0);
		this.tilePane.setPrefColumns(1);

		this.backgroundImage = new ImageView();
		this.backgroundImage.setImage(Square.BACKGROUND.getImage());
		this.backgroundImage.setFitHeight(600);
		this.backgroundImage.setFitWidth(600);

		this.tilePane.getChildren().add(backgroundImage);

	}
	
	public TilePane getBackgroundTilePane() {
		return this.tilePane;
	}

}
