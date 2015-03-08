package graphics;

import implementation.Board;
import implementation.Level;
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

	private BoardDisplay bd;
	private StartDisplay sd;
	private WinningDisplay wd;
	private LoserDisplay ld;
	private Level level;

	private SheepHerder sheepHerder;

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;

		this.bd = new BoardDisplay(Constants.BOARD_WIDTH,
				Constants.BOARD_HEIGHT, new Level(1));
		this.sd = new StartDisplay();
		this.wd = new WinningDisplay();
		this.ld = new LoserDisplay();

		primaryStage.setTitle("SheepHerder");
		primaryStage.setScene(new SheepHerder(new Group(), 600, 600, this, wd,
				sd, bd, ld));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void closePrimary() {
		primaryStage.close();
	}

}
