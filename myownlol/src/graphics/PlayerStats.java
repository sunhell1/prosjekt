package graphics;

import implementation.Level;

import java.util.ArrayList;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

import enums.Constants;
import enums.Square;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PlayerStats extends Pane {

	final int height = 50;
	final int width = 800;
	
	private HBox statsBox;

	private Image herderImage;

	private ArrayList<ImageView> herder;
	
	private ImageView player;
	
	private Text text;
	
	private int life;
	
	private Text sheepCaught;
	
	private int sheepCounter;
	
	private Level level;

	public PlayerStats(Level level) {
		
		this.herderImage = Square.HERDER.getImage();
		
		this.level = level;

		this.statsBox = new HBox();
		this.statsBox.setPrefHeight(height);
		this.statsBox.setPrefWidth(width);
		this.statsBox.setBackground(new Background(new BackgroundFill(Color.GREY,
				CornerRadii.EMPTY, Insets.EMPTY)));
		this.statsBox.setStyle("-fx-border-color: black;");

		this.setPrefHeight(height);
		this.setPrefWidth(width);
		
		this.life = Constants.MAX_LIVES;
		
		this.sheepCounter = level.getSheepCount();
		
		this.text = new Text();
		text.setText("Health: ");
		text.setFill(Color.BLACK);
		text.setFont(Font.font("null", FontWeight.SEMI_BOLD, 40));
		
		this.statsBox.getChildren().add(this.text);
		
		this.sheepCaught = new Text();
		this.sheepCaught.setText("Sheep remaining: " + sheepCounter);
		this.sheepCaught.setFill(Color.BLACK);
		this.sheepCaught.setFont(Font.font("null", FontWeight.BOLD, 25));

		this.herder = new ArrayList<ImageView>();

		for (int i = 0; i < Constants.MAX_LIVES; i++) {
			player = new ImageView();
			player.setFitHeight(50);
			player.setFitWidth(50);
			player.setImage(herderImage);
			herder.add(player);
			this.statsBox.getChildren().add(player);
		}
		
		
		this.getChildren().add(this.statsBox);
		this.getChildren().add(this.sheepCaught);
		this.sheepCaught.setLayoutX(500);
		this.sheepCaught.setLayoutY(37);
	}

	public void removeLife() {
		statsBox.getChildren().remove(herder.get(--life));
	}
	
	public void sheepCaught() {
		this.getChildren().remove(this.sheepCaught);
		--this.sheepCounter;
		this.sheepCaught = new Text();
		this.sheepCaught.setText("Sheep remaining: " + sheepCounter);
		this.sheepCaught.setFill(Color.BLACK);
		this.sheepCaught.setFont(Font.font("null", FontWeight.BOLD, 25));
		
		this.getChildren().add(sheepCaught);
		this.sheepCaught.setLayoutX(500);
		this.sheepCaught.setLayoutY(37);
	}
	
	public int getSheepCaught() {
		return this.sheepCounter;
	}
	
	public void animateSheepCaughtText() {
		FadeTransition fadeTransition = new FadeTransition(
				Duration.millis(100), this.sheepCaught);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0.1);
		fadeTransition.setCycleCount(6);
		fadeTransition.setAutoReverse(true);
		fadeTransition.play();
	}
}
