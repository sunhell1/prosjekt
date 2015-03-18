package graphics;

import implementation.Herder;


import implementation.Item;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ConsoleDisplay extends Pane {

	private final int height = 600;
	private final int width = 200;
	private int layY;
	
	private Herder herder;
	private SheepHerder sh;

	public ConsoleDisplay(Herder herder, SheepHerder sh) {

		this.herder = herder;
		this.sh = sh;

		this.setPrefHeight(height);
		this.setPrefWidth(width);

		this.setBackground(new Background(new BackgroundFill(Color.GRAY,
				CornerRadii.EMPTY, Insets.EMPTY)));
		
		VBox vbox = new VBox();

		Text string1 = new Text();
		string1.setFont(new Font(25));
		string1.setWrappingWidth(width);
		string1.setText("YOUR INVENTORY:");
		string1.setTextAlignment(TextAlignment.CENTER);

		Text string2 = new Text();
		string2.setFont(new Font(20));
		string2.setText("          ");
		string2.setTextAlignment(TextAlignment.CENTER);
		
		vbox.getChildren().add(string1);
		vbox.getChildren().add(string2);

		this.getChildren().add(vbox);
		
		this.setStyle("-fx-border-color: black;");
		
		layY = 100;

	}

	public void mouseClicked(MouseEvent event, String string) {

		if (!herder.isEquipped(string)) {
			herder.equip(string);
		}

		else {
			herder.unEquip();
		}

	}

	public void addItemToInvetoryList(Item item) {

		Text text = new Text();
		text.setText(item.getName());
		text.setFont(new Font(20));
		text.setWrappingWidth(width);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setOnMouseClicked(event -> mouseClicked(event, item.getName()));
		
		VBox vbox = new VBox();
		vbox.getChildren().add(text);

		vbox.setLayoutY(layY);
		
		layY += 25;

		this.getChildren().add(vbox);
	}
}
