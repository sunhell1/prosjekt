package graphics;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Distant;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import enums.Square;

public class StartDisplay {

	Group startdisplay;

	Button startButton;
	Button levelButton;
	
	private Media media;
	
	private MediaPlayer player;
	
	private MediaView view;
	
	private Pane display;

	public StartDisplay() {

		startdisplay = new Group();
		
		display = new Pane();
		display.setPrefHeight(650);
		display.setPrefWidth(800);

		startButton = new Button("Start");
		
		startButton.setPrefHeight(50);
		startButton.setPrefWidth(100);
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setStyle("-fx-border: 12px solid;" + "-fx-border-color: black;" + "-fx-background-color: green;");
		
		levelButton = new Button("Levels");
		
		levelButton.setPrefHeight(50);
		levelButton.setPrefWidth(100);
		levelButton.setLayoutX(350);
		levelButton.setLayoutY(350);
		levelButton.setStyle("-fx-border: 12px solid;" + "-fx-border-color: black;" + "-fx-background-color: green;");
		
		
		display.getChildren().add(startButton);
		display.getChildren().add(levelButton);
		
		media = new Media(getClass().getResource("/media/intro.mp4").toString());
		
		player = new MediaPlayer(media);
		
		view = new MediaView(player);
		
		this.startdisplay.getChildren().add(view);
		this.startdisplay.getChildren().add(display);
		
		player.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		
		player.play();
	}

	public Button getStartButton() {
		return this.startButton;
	}

	public Group getStartGroup() {
		return this.startdisplay;
	}

}
