package graphics;

import implementation.Herder;


import implementation.Item;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
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
		
		
		Text string1 = new Text();
		string1.setFont(new Font(25));
		string1.setWrappingWidth(width);
		string1.setText("YOUR INVENTORY:");
		string1.setTextAlignment(TextAlignment.CENTER);

		Text string2 = new Text();
		string2.setFont(new Font(20));
		string2.setText("          ");
		string2.setTextAlignment(TextAlignment.CENTER);
		
		Text string3 = new Text();
		string3.setFont(new Font(20));
		string3.setText("1");
		string3.setLayoutX(50);
		string3.setLayoutY(100);
		
		Text string4 = new Text();
		string4.setFont(new Font(20));
		string4.setText("2");
		string4.setLayoutX(50);
		string4.setLayoutY(160);
		
		Text string5 = new Text();
		string5.setFont(new Font(20));
		string5.setText("3");
		string5.setLayoutX(50);
		string5.setLayoutY(220);
		
		Text string6 = new Text();
		string6.setFont(new Font(20));
		string6.setText("4");
		string6.setLayoutX(50);
		string6.setLayoutY(280);
		
		Text string7 = new Text();
		string7.setFont(new Font(20));
		string7.setText("5");
		string7.setLayoutX(50);
		string7.setLayoutY(340);
		
		Text string8 = new Text();
		string8.setFont(new Font(20));
		string8.setText("6");
		string8.setLayoutX(50);
		string8.setLayoutY(400);
		
		Text string9 = new Text();
		string9.setFont(new Font(20));
		string9.setText("7");
		string9.setLayoutX(50);
		string9.setLayoutY(460);
		
		Text string10 = new Text();
		string10.setFont(new Font(20));
		string10.setText("8");
		string10.setLayoutX(50);
		string10.setLayoutY(520);		
		
		Text string11 = new Text();
		string11.setFont(new Font(20));
		string11.setText("9");
		string11.setLayoutX(50);
		string11.setLayoutY(580);
	

		this.getChildren().add(string1);
		this.getChildren().add(string2);
		this.getChildren().add(string3);
		this.getChildren().add(string4);
		this.getChildren().add(string5);
		this.getChildren().add(string6);
		this.getChildren().add(string7);
		this.getChildren().add(string8);
		this.getChildren().add(string9);
		this.getChildren().add(string10);
		this.getChildren().add(string11);
		
		this.setStyle("-fx-border-color: black;");
		
		layY = 70;

	}


	public void addItemToInvetoryList(Item item) {
		
		ImageView iv = new ImageView();
		iv.setFitHeight(50);
		iv.setFitWidth(50);
		iv.setImage(item.getImage());
		iv.setX(75);

		iv.setLayoutY(layY);

		layY += 60;
		
		this.getChildren().add(iv);

	}
}
