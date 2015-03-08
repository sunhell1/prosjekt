package graphics;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import enums.Square;

public class LoserDisplay {
	
	Group loserdisplay;

	Button loserButton;

	TilePane tilePane;

	ImageView loserImage;

	public LoserDisplay() {

		this.loserdisplay = new Group();

		this.loserButton = new Button("YOULOSE,AGAIN?");

		this.tilePane = new TilePane();

		this.tilePane = new TilePane();
		this.tilePane.setHgap(0);
		this.tilePane.setPrefColumns(1);

		this.loserImage = new ImageView();
		this.loserImage.setImage(Square.LOSER_DISPLAY.getImage());
		this.loserImage.setFitHeight(600);
		this.loserImage.setFitWidth(600);

		this.tilePane.getChildren().add(loserImage);

		this.loserdisplay.getChildren().add(tilePane);
		this.loserdisplay.getChildren().add(loserButton);

	}

	public TilePane getTilePane() {
		return this.tilePane;
	}

	public Button getLoserButton() {
		return this.loserButton;
	}

	public ImageView getLoserImage() {
		return this.loserImage;
	}

	public Group getLoserGroup() {
		return this.loserdisplay;
	}

}
