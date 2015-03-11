package graphics;

import javafx.application.Application;
import javafx.scene.Group;

import javafx.stage.Stage;

public class GUI extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		
		primaryStage.setTitle("SheepHerder");
		primaryStage.setScene(new SheepHerder(new Group(), 600, 600, this));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void closePrimary() {
		primaryStage.close();
	}

}
