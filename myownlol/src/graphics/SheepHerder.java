package graphics;

import java.awt.Point;
import java.util.ArrayList;

import Sounds.GameSounds;
import sun.audio.AudioPlayer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import implementation.Herder;
import implementation.Sheep;
import implementation.Level;

public class SheepHerder extends Scene {

	private ArrayList<Sheep> sheeps;
	private Herder herder;

	private boolean winner;

	private Level level;

	private StartDisplay sd;
	private WinningDisplay wd;
	private BoardDisplay bd;
	private LoserDisplay ld;

	private Group group;

	private BackgroundDisplay backGround;

	private int sheepCount;

	private int levelCount = 1;

	private GUI gui;

	public SheepHerder(Group group, int width, int height, GUI gui) {

		super(group, width, height);

		this.group = group;

		this.backGround = new BackgroundDisplay();
		this.group.getChildren().add(backGround.getBackgroundTilePane());

		this.sd = new StartDisplay();
		this.wd = new WinningDisplay();
		this.ld = new LoserDisplay();


		this.winner = false;

		this.gui = gui;

		startScene();

	}

	private void startScene() {
		this.group.getChildren().add(sd.getStartGroup());
		sd.getStartButton().setOnMouseClicked(event -> mouseClicked(event));
	//	GameSounds.playTheme();
	}

	private void winningScene() {
		this.group.getChildren().add(wd.getWinningGroup());
		wd.getWinningButton().setOnMouseClicked(event -> mouseClicked(event));
	//	GameSounds.playTheme();
	}

	private void loserScene() {
		this.group.getChildren().remove(ld.getLoserGroup());
		this.group.getChildren().add(ld.getLoserGroup());
		ld.getLoserButton().setOnMouseClicked(event -> mouseClicked(event));
	//	GameSounds.playTheme();
	}

	public void initateGame(int levelNumber) {

		this.level = new Level(levelCount);

		this.bd = new BoardDisplay(Constants.BOARD_WIDTH,
				Constants.BOARD_HEIGHT, level);
		this.group.getChildren().add(bd.getGroup());

		this.herder = new Herder(level.getHerderStart(), Condition.ALIVE, this.level, this.bd);

		this.sheepCount = level.getSheepCount();

		sheeps = level.getLevelSheep();

		placePlayer();
		placeSheep();

		levelCount++;

		this.setOnKeyPressed(event -> keyPressed(event));
	}

	public void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			gui.closePrimary();
		} else if (e.getCode() == KeyCode.UP) {
			herder.move(herder.getLocation(), Direction.NORTH);
			bd.animateMovement(herder.getLocation());		//SKAL INN I HERDER
			gameFlow();
		}
		else if (e.getCode() == KeyCode.DOWN) {
			herder.move(herder.getLocation(), Direction.SOUTH);
			bd.animateMovement(herder.getLocation());			//SKAL INN I HERDER
			gameFlow();
		} else if (e.getCode() == KeyCode.LEFT) {
			herder.move(herder.getLocation(), Direction.WEST);
			bd.animateMovement(herder.getLocation());			//SKAL INN I HERDER
			gameFlow();
		} else if (e.getCode() == KeyCode.RIGHT) {
			herder.move(herder.getLocation(), Direction.EAST);
			bd.animateMovement(herder.getLocation());			//SKAL INN I HERDER
			gameFlow();
		}
	}


	public void mouseClicked(MouseEvent e) {
		this.group.getChildren().removeAll(sd.getStartGroup());
		this.group.getChildren().removeAll(wd.getWinningGroup());
		this.group.getChildren().removeAll(ld.getLoserGroup());
	//	GameSounds.stopTheme();
		int level = 0;
		initateGame(level++);
	}

	public int convertPointToIndex(Point p) {

		int x = p.x;
		int y = p.y;
		int r = (y * Constants.BOARD_WIDTH) + x;
		return r;
	}

	public void placePlayer() {
		bd.drawHerder(herder.getLocation());
	}

	public void placeSheep() {
		for (Sheep sheep : sheeps) {
			bd.drawSheep(sheep.getLocation());
		}
	}

	public int gameOver() {
		for (Sheep sheep : sheeps) {
			if (herder.getLocation().equals(sheep.getLocation())) {
	//			GameSounds.playBaa();
				sheeps.remove(sheep);
				bd.removeSheep(sheep.getLocation());

				sheepCount--;
				if (sheepCount == 0) {
					return 1;
				}

				return 0;

			}
		}
		if (herder.getLives() <= 0) {
			return 2;
		} else
			return 0;
	}
	

	private void gameFlow() {
		int reply = gameOver();
		if (reply > 0) {

			switch (reply) {
			case 1:
				winningScene();
				break;
			case 2:
				loserScene();
				break;
			default:
				System.out.println("THIS SHOULD NEVER HAPPEN");
				break;
			}
		}
	}
}
