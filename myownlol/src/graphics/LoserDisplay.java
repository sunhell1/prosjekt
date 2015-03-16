package graphics;

import enums.Square;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class LoserDisplay extends Pane {

	private Button loserButton;

	private Media media;

	private MediaPlayer player;

	private MediaView view;

	private Image retryButtonImage;

	private ImageView pikkHomoNeger;

	private DropShadow shadow;

	public LoserDisplay() {

		this.loserButton = new Button();

		this.retryButtonImage = Square.RETRY.getImage();

		this.pikkHomoNeger = new ImageView(retryButtonImage);
		
		Color red = Color.rgb(255,0,0);
		double dobbelPenetration = 200;
		
		DropShadow shadow = new DropShadow ();
		InnerShadow shadowB = new InnerShadow (dobbelPenetration,red);
		
		pikkHomoNeger.setEffect(shadow);
		

		loserButton.setLayoutX(300);
		loserButton.setLayoutY(300);
		loserButton.setStyle("-fx-background-color: transparent;");

	
		
		loserButton.setGraphic(pikkHomoNeger);
		pikkHomoNeger.setOnMouseDragOver(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				pikkHomoNeger.setEffect(shadow);
			}
		});

		media = new Media(getClass().getResource("/media/death.mp4").toString());

		player = new MediaPlayer(media);

		view = new MediaView(player);

		player.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);

		this.getChildren().add(view);
		this.getChildren().add(loserButton);

		player.play();

	}

	public Button getLoserButton() {

		return this.loserButton;
	}

}
