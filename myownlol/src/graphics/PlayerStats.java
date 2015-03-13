package graphics;

import java.util.ArrayList;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

import enums.Constants;
import enums.Square;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PlayerStats extends HBox {

	final int height = 50;
	final int width = 800;

	private Image herderImage;

	private ArrayList<ImageView> herder;
	
	private ImageView player;
	
	private Text text;
	
	private int life;
	
	private Text sheepCaught;
	
	private int sheepCounter;

	public PlayerStats() {

		this.setPrefHeight(height);
		this.setPrefWidth(width);
		
		this.life = Constants.MAX_LIVES;
		
		this.sheepCounter = 0;
		
		this.text = new Text();
		text.setText("Health : ");
		text.setFill(Color.BLACK);
		text.setFont(Font.font("null", FontWeight.SEMI_BOLD, 40));
		
		this.sheepCaught = new Text();
		sheepCaught.setText("Sheep Caught: " + sheepCounter);
		sheepCaught.setFill(Color.BLACK);
		sheepCaught.setFont(Font.font("null", FontWeight.SEMI_BOLD, 40));
		
		DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
		
		text.setEffect(ds);
		
		this.herderImage = Square.HERDER.getImage();
		
		this.getChildren().add(this.text);
		
		this.setStyle("-fx-border-color: black;");
		
		this.herder = new ArrayList<ImageView>();

		for (int i = 0; i < Constants.MAX_LIVES; i++) {
			player = new ImageView();
			player.setFitHeight(50);
			player.setFitWidth(50);
			player.setImage(herderImage);
			herder.add(player);
			this.getChildren().add(player);
		}
		
		this.getChildren().add(sheepCaught);
		
		this.setBackground(new Background(new BackgroundFill(Color.GREY,
				CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void removeLife() {
		this.getChildren().remove(herder.get(--life));
	}
	
	public void sheepCaught() {
		++this.sheepCounter;
	}
}
