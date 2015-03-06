package graphics;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import enums.Square;

public class WinnerDisplay {

	private TilePane tilePane;

	private ImageView winnerDisplay;

	private Group group;

	public WinnerDisplay() {

		group = new Group();

		tilePane = new TilePane();
		tilePane.setHgap(0);
		tilePane.setPrefColumns(1);

		winnerDisplay = new ImageView();
		winnerDisplay.setImage(Square.WINNER_DISPLAY.getImage());
		winnerDisplay.setFitHeight(600);
		winnerDisplay.setFitWidth(600);

		tilePane.getChildren().add(winnerDisplay);

	}

	public TilePane getTilePane() {
		return this.tilePane;
	}

}
