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

	private final int PREFERRED_DIM = 50;

	private Group masterGroup;
	
	private SheepHerder sh;

	private StartDisplay sd;

	private BoardDisplay bd;
	
	private WinnerDisplay wd;

	private Button startButton;
	
	private Button playagain;
	
	private Scene scene;
	
	
	Scanner key = new Scanner(System.in);

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;

		this.masterGroup = new Group();
	
		
		sd = new StartDisplay();
		wd = new WinnerDisplay();

		startButton = new Button("STARTT");
		startButton.setOnMouseClicked(event -> mouseClicked(event));
		startButton.setOnMouseExited(event -> mouseReleased(event));

		this.masterGroup.getChildren().add(sd.getTilePane());
		this.masterGroup.getChildren().add(startButton);

		scene = new Scene(masterGroup, 600, 600);

		scene.setOnKeyPressed(event -> keyPressed(event));
		scene.setOnKeyTyped(event -> keyTyped(event));
		scene.setOnKeyReleased(event -> keyReleased(event));

		primaryStage.setTitle("SheepHerder");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			primaryStage.close();
		} else if (e.getCode() == KeyCode.UP) {
			sh.getPlayer().move(sh.getPlayer().getLocation(), Direction.NORTH);
			rePaint();

		} else if (e.getCode() == KeyCode.DOWN) {
			sh.getPlayer().move(sh.getPlayer().getLocation(), Direction.SOUTH);
			rePaint();
		} else if (e.getCode() == KeyCode.LEFT) {
			sh.getPlayer().move(sh.getPlayer().getLocation(), Direction.WEST);
			rePaint();
		} else if (e.getCode() == KeyCode.RIGHT) {
			sh.getPlayer().move(sh.getPlayer().getLocation(), Direction.EAST);
			rePaint();
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {

		Point sheepPoint = Constants.sheepStartPoint;
		Point playerPoint = Constants.playerStartPoint;

		Board board = new Board();

		Player Fredrik = new Player(Direction.NORTH, playerPoint, board,
				Condition.ALIVE);

		Sheep Rodmundur = new Sheep(sheepPoint, Direction.SOUTH, board,
				Condition.ALIVE);
		
		board.setPlayer(Fredrik);
		board.setSheep(Rodmundur);

		sh = new SheepHerder(Fredrik, Rodmundur, board);

		bd = new BoardDisplay(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, sh);		
		masterGroup.getChildren().add(bd.getGroup());
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	public void rePaint(){
		
		if(sh.isWinningConiditions()){
			masterGroup.getChildren().remove(sd.getTilePane());
			masterGroup.getChildren().remove(startButton);
			masterGroup.getChildren().removeAll(bd.getGroup());
			masterGroup.getChildren().add(wd.getTilePane());
			
			playagain = new Button("PLAY MORE??");
			playagain.setOnMouseClicked(event -> mouseClicked(event));
			masterGroup.getChildren().add(playagain);
			

		}
		else{
		masterGroup.getChildren().remove(bd.getGroup());
		bd = new BoardDisplay(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, sh);
		masterGroup.getChildren().add(bd.getGroup());
		}
	}

}
