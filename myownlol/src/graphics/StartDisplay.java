package graphics;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import enums.Square;

public class StartDisplay {

	Group startdisplay;

	Button startButton;

	TilePane tilePane;

	ImageView startImage;

	public StartDisplay() {

		this.startdisplay = new Group();

		this.startButton = new Button("STARTT");

		this.tilePane = new TilePane();

		this.tilePane = new TilePane();
		this.tilePane.setHgap(0);
		this.tilePane.setPrefColumns(1);

		this.startImage = new ImageView();
		this.startImage.setImage(Square.START_DISPLAY.getImage());
		this.startImage.setFitHeight(600);
		this.startImage.setFitWidth(600);

		this.tilePane.getChildren().add(startImage);

		this.startdisplay.getChildren().add(tilePane);
		this.startdisplay.getChildren().add(startButton);

	}

	public TilePane getTilePane() {
		return this.tilePane;
	}

	public Button getStartButton() {
		return this.startButton;
	}

	public ImageView getStartImage() {
		return this.startImage;
	}

	public Group getStartGroup() {
		return this.startdisplay;
	}

}
