package graphics;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import enums.Square;

public class WinningDisplay {
	
	Group winningdisplay;

	Button winningButton;

	TilePane tilePane;

	ImageView winningImage;

	public WinningDisplay() {

		this.winningdisplay = new Group();

		this.winningButton = new Button("PLAY MORE??!?");

		this.tilePane = new TilePane();

		this.tilePane = new TilePane();
		this.tilePane.setHgap(0);
		this.tilePane.setPrefColumns(1);

		this.winningImage = new ImageView();
		this.winningImage.setImage(Square.WINNER_DISPLAY.getImage());
		this.winningImage.setFitHeight(600);
		this.winningImage.setFitWidth(600);

		this.tilePane.getChildren().add(winningImage);

		this.winningdisplay.getChildren().add(tilePane);
		this.winningdisplay.getChildren().add(winningButton);

	}

	public TilePane getTilePane() {
		return this.tilePane;
	}

	public Button getWinningButton() {
		return this.winningButton;
	}

	public ImageView getWinningImage() {
		return this.winningImage;
	}

	public Group getWinningGroup() {
		return this.winningdisplay;
	}

}
