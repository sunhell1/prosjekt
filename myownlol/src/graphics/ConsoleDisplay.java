package graphics;

import implementation.Herder;
import implementation.Item;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class ConsoleDisplay extends VBox {

	private final int height = 600;
	private final int width = 200;
	private Text text;
	private Herder herder;

	public ConsoleDisplay(Herder herder) {

		this.herder = herder;

		this.setPrefHeight(height);
		this.setPrefWidth(width);

		this.setBackground(new Background(new BackgroundFill(Color.GRAY,
				CornerRadii.EMPTY, Insets.EMPTY)));

		Text string1 = new Text();
		string1.setFont(new Font(25));
		string1.setWrappingWidth(width);
		string1.setText("YOUR INVENTORY:");
		string1.setTextAlignment(TextAlignment.CENTER);

		Text string2 = new Text();
		string2.setFont(new Font(20));
		string2.setText("          ");
		string2.setTextAlignment(TextAlignment.CENTER);

		this.getChildren().add(string1);
		this.getChildren().add(string2);

		this.setStyle("-fx-border-color: black;");

	}

	private void mouseClicked(MouseEvent event, String string) {

		if (!herder.isEquipped()) {
			herder.equip(string);
		}

		else {
			this.getChildren().remove(text);

			this.text = new Text();
			text.setText("You already have this item equipped");
			text.setFont(new Font(20));
			text.setWrappingWidth(width);
			text.setTextAlignment(TextAlignment.CENTER);

			this.getChildren().add(text);

			FadeTransition fadeTransition = new FadeTransition(
					Duration.millis(500), text);
			fadeTransition.setFromValue(1);
			fadeTransition.setToValue(0.1);
			fadeTransition.setCycleCount(5);
			fadeTransition.setAutoReverse(true);
			fadeTransition.play();

			fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {

					text.setVisible(false);
					text.setDisable(true);

				}

			});
		}

	}

	public void addItemToInvetoryList(Item item) {

		Text text = new Text();
		text.setText(item.getName());
		text.setFont(new Font(20));
		text.setWrappingWidth(width);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setOnMouseClicked(event -> mouseClicked(event, item.getName()));

		this.getChildren().add(text);
	}
}
