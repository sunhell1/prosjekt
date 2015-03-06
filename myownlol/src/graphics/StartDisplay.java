package graphics;

import com.sun.org.apache.bcel.internal.Constants;

import enums.Square;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class StartDisplay {
	
	private TilePane tilePane;
	
	private ImageView startDisplay;
	
	public StartDisplay() {

		tilePane = new TilePane();
		tilePane.setHgap(0);
		tilePane.setPrefColumns(1);
		
		startDisplay = new ImageView();
		startDisplay.setImage(Square.START_DISPLAY.getImage());
		startDisplay.setFitHeight(600);
		startDisplay.setFitWidth(600);
		
		tilePane.getChildren().add(startDisplay);
		
	}
	
	public TilePane getTilePane() {
		return this.tilePane;
	}

}
