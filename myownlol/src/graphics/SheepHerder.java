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
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Square;
import implementation.Herder;
import implementation.Sheep;
import implementation.Level;
import implementation.Wolf;

public class SheepHerder extends Scene {

	private ArrayList<Sheep> sheeps;
	private Herder herder;
	private Wolf wolf;

	private boolean winner;

	private Level level;

	private StartDisplay sd;
	private WinningDisplay wd;
	private BoardDisplay bd;
	private LoserDisplay ld;
	private PlayerStats statsDisplay;
	
	private String state;
	
	private Group group;

	private BackgroundDisplay backGround;

	private int sheepCount;

	private int levelCount = 0;

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
		sd.getStartButton().setOnMouseClicked(event -> mouseClicked(event, "NEW"));
		GameSounds.playTheme();
	}

	private void winningScene() {
		this.group.getChildren().add(wd.getWinningGroup());
		wd.getWinningButton().setOnMouseClicked(event -> mouseClicked(event, "NEW"));
		GameSounds.playTheme();
	}

	private void loserScene() {
		this.group.getChildren().remove(ld.getLoserGroup());
		this.group.getChildren().add(ld.getLoserGroup());
		ld.getLoserButton().setOnMouseClicked(event -> mouseClicked(event, "RETRY"));
		GameSounds.playTheme();
	}

	public void initateGame(int levelNumber) {
		
		this.statsDisplay = new PlayerStats();
		statsDisplay.relocate(0, 600);

		this.level = new Level(levelCount);

		this.bd = new BoardDisplay(Constants.BOARD_WIDTH,
				Constants.BOARD_HEIGHT, level);

		this.group.getChildren().add(bd.getGroup());

		
		this.group.getChildren().add(statsDisplay);

		this.herder = new Herder(level.getHerderStart(), Condition.ALIVE,
				this.level, this.bd, this.statsDisplay);

		this.wolf = new Wolf(level.getWolfStart(), Condition.ALIVE, this.bd);

		this.sheepCount = level.getSheepCount();

		sheeps = level.getLevelSheep();
		
		this.group.getChildren().add(herder.getGroup());

		placePlayer();
		placeSheep();
		placeWolf();
		placeTrees();

		levelCount++;

		this.setOnKeyPressed(event -> keyPressed(event));
	}

	public void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			gui.closePrimary();
		} else if (e.getCode() == KeyCode.UP) {
			herder.move(herder.getLocation(), Direction.NORTH);
			wolf.moveWolf(level.getWolfStack().pop());
			gameFlow();
		} else if (e.getCode() == KeyCode.DOWN) {
			herder.move(herder.getLocation(), Direction.SOUTH);
			wolf.moveWolf(level.getWolfStack().pop());
			gameFlow();
		} else if (e.getCode() == KeyCode.LEFT) {
			herder.move(herder.getLocation(), Direction.WEST);
			wolf.moveWolf(level.getWolfStack().pop());
			gameFlow();
		} else if (e.getCode() == KeyCode.RIGHT) {
			herder.move(herder.getLocation(), Direction.EAST);
			wolf.moveWolf(level.getWolfStack().pop());
			gameFlow();
		}
	}

	public void mouseClicked(MouseEvent e , String state) {
		this.group.getChildren().removeAll(statsDisplay);
	//	this.group.getChildren().removeAll(herder.getGroup());
		this.group.getChildren().removeAll(sd.getStartGroup());
		this.group.getChildren().removeAll(wd.getWinningGroup());
		this.group.getChildren().removeAll(ld.getLoserGroup());
		GameSounds.stopTheme();
		
		this.state = state;
		
		switch (state) {
		case "RETRY" :
			initateGame(--this.levelCount);
			break;
		case "NEW" : 
			initateGame(this.levelCount++);
			break;
		}
 
		
		
	}

	public void placePlayer() {
		bd.drawHerder(herder.getLocation());
	}

	public void placeSheep() {
		for (Sheep sheep : sheeps) {
			bd.drawSheep(sheep.getLocation());
		}
	}

	public void placeTrees() {
		Square[][] levelSquares = level.getBoardLayout();
		for (int i = 0; i < levelSquares.length; i++) {
			for (int j = 0; j < levelSquares[i].length; j++) {
				if (levelSquares[i][j].equals(Square.TREE)) {
					Point treePoint = new Point(i, j);
					bd.drawTrees(treePoint);
				}

			}
		}
	}

	public void placeWolf() {
		bd.drawWolf(wolf.getWolfPosition());
	}
	
	public boolean wolfAteHerder() {
		if (wolf.getWolfPosition().equals(herder.getLocation())) {
			return true;
		}
		return false;
	}

	public int gameOver() {
		for (Sheep sheep : sheeps) {
			if (herder.getLocation().equals(sheep.getLocation())
					|| wolf.getWolfPosition().equals(sheep.getLocation())) {
				if (herder.getLocation().equals(sheep.getLocation())) {
					GameSounds.playBaa();
					this.statsDisplay.sheepCaught();
					sheeps.remove(sheep);
					bd.removeSheep(sheep.getLocation());

					sheepCount--;
					if (sheepCount == 0) {
						return 1;
					}
					return 0;
				}
				else if (wolf.getWolfPosition().equals(sheep.getLocation())) {
					return 2;
				}
			}
		}
		
		if (wolfAteHerder()){
			herder.takeDamage();
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
