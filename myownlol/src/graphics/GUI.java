package graphics;

import implementation.Board;
import implementation.Player;
import implementation.Sheep;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Square;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {

	private Stage primaryStage;

	private Group masterGroup;

	private SheepHerder sh;

	private Displays display;

	private Button startButton;

	private Button playagain;

	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {

		initModel();

		this.primaryStage = primaryStage;

		this.masterGroup = new Group();
		
		scene = display.getStartDisplay();

		primaryStage.setTitle("SheepHerder");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


	public void initModel() {
		display = new Displays(Constants.BOARD_HEIGHT, Constants.BOARD_HEIGHT);
		display.setLevel(0);
		this.sh = new SheepHerder(this.display);
		sh.initateGame();
	}

}
