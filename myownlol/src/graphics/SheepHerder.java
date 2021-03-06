package graphics;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

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
import javafx.scene.shape.Rectangle;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Square;
import implementation.Herder;
import implementation.Item;
import implementation.Sheep;
import implementation.Level;
import implementation.Wolf;

public class SheepHerder extends Scene {

	private ArrayList<Sheep> sheeps;

	private Herder herder;
	private Wolf wolf;

	private boolean winner;
	private boolean blocked;

	private Level level;

	private StartDisplay sd;
	private WinningDisplay wd;
	private BoardDisplay bd;
	private LoserDisplay ld;
	private PlayerStats statsDisplay;

	private String state;

	private Group group;

	private Stack<Point> sheepStarts;

	private BackgroundDisplay backGround;

	private int sheepCount;

	private int levelCount = 0;

	private GUI gui;
	
	private ArrayList<Item> itemsOnMap;

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
		sd.getStartButton().setOnMouseClicked(
				event -> mouseClicked(event, "NEW"));
		// GameSounds.playTheme();
	}

	private void winningScene() {
		this.group.getChildren().add(wd.getWinningGroup());
		wd.getWinningButton().setOnMouseClicked(
				event -> mouseClicked(event, "NEW"));
		// GameSounds.playTheme();
	}

	public void loserScene() {
		this.group.getChildren().removeAll(bd.getGroup());
		this.group.getChildren().remove(statsDisplay);
		this.group.getChildren().remove(ld);
		this.group.getChildren().add(ld);
		ld.getLoserButton().setOnMouseClicked(
				event -> mouseClicked(event, "RETRY"));
		// GameSounds.playTheme();
	}

	public void initateGame(int levelNumber) {

		sheeps = new ArrayList<Sheep>();

		this.level = new Level(levelCount);
		this.sheepCount = level.getBigSheepCount() + level.getBabySheepCount();
		this.sheepStarts = level.getSheepStarts();

		this.statsDisplay = new PlayerStats(this.level);
		statsDisplay.relocate(0, 600);

		this.bd = new BoardDisplay(Constants.BOARD_WIDTH,
				Constants.BOARD_HEIGHT, level, this);

		this.herder = new Herder(level.getHerderStart(), Condition.ALIVE,
				this.level, this.bd, this.statsDisplay, this);

		for (int i = 0; i <= sheepStarts.size()+1; i++) {
			Sheep sheep = new Sheep(sheepStarts.pop(), Condition.ALIVE,
					Direction.NORTH, level, this);
			sheeps.add(sheep);
			this.group.getChildren().add(sheep);
		}

		this.group.getChildren().add(herder.getGroup());
		this.group.getChildren().add(bd);
		this.group.getChildren().add(statsDisplay);

		if (level.hasWolf()) {
			this.wolf = new Wolf(level.getWolfStart(), Condition.ALIVE, this.bd);
			placeWolf();
		}
		
		if (level.hasItems()){
			this.itemsOnMap = level.getItems();
		}

		placePlayer();
		placeTrees();
		placeSheep(sheeps);

		this.setOnKeyPressed(event -> keyPressed(event));
		this.blocked = false;
	}

	public void keyPressed(KeyEvent e) {
		if (isBlocked()) {
			return;
		} else if (e.getCode() == KeyCode.ESCAPE) {
			gui.closePrimary();
		} else if (e.getCode() == KeyCode.UP) {
			herder.move(herder.getLocation(), Direction.NORTH);
			if (level.hasWolf()) {
				wolf.moveWolf(level.getWolfStack().pop());
			}
			gameFlow();
		} else if (e.getCode() == KeyCode.DOWN) {
			herder.move(herder.getLocation(), Direction.SOUTH);
			if (level.hasWolf()) {
				wolf.moveWolf(level.getWolfStack().pop());
			}
			gameFlow();
		} else if (e.getCode() == KeyCode.LEFT) {
			herder.move(herder.getLocation(), Direction.WEST);
			if (level.hasWolf()) {
				wolf.moveWolf(level.getWolfStack().pop());
			}
			gameFlow();
		} else if (e.getCode() == KeyCode.RIGHT) {
			herder.move(herder.getLocation(), Direction.EAST);
			if (level.hasWolf()) {
				wolf.moveWolf(level.getWolfStack().pop());
			}
			gameFlow();
		} else if (e.getCode() == KeyCode.SPACE) {
			if (herder.getItemEquipped().equals("")) {
				herder.smackSheep(herder.getLocation());
				bd.smackAnimation();
				if (sheepFenced()) {
					gameFlow();
				}
				herder.setSmacked(true);
				herder.killedSheep();
			}
		} else if (e.getCode() == KeyCode.D) {
			if (herder.isStandingNextToSheep(herder.getLocation())) {
				herder.pickUpSheep();
				sheepCount--;
				gameFlow();
			}
		}
		else if(e.getCode() == KeyCode.DIGIT1){
			if (!herder.isInventoryGreaterThan(1)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(0).getName())){
				herder.equip(herder.getItems().get(0).getName());
			}
			else herder.unEquip();
		
		}
		else if(e.getCode() == KeyCode.DIGIT2){
			if (!herder.isInventoryGreaterThan(2)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(1).getName())){
				herder.equip(herder.getItems().get(1).getName());
			}
			else herder.unEquip();
		
		}
		
		else if(e.getCode() == KeyCode.DIGIT3){
			if (!herder.isInventoryGreaterThan(3)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(2).getName())){
				herder.equip(herder.getItems().get(2).getName());
			}
			else herder.unEquip();
		
		}
		
		else if(e.getCode() == KeyCode.DIGIT4){
			if (!herder.isInventoryGreaterThan(4)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(3).getName())){
				herder.equip(herder.getItems().get(3).getName());
			}
			else herder.unEquip();
		
		}
		
		else if(e.getCode() == KeyCode.DIGIT5){
			if (!herder.isInventoryGreaterThan(5)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(4).getName())){
				herder.equip(herder.getItems().get(4).getName());
			}
			else herder.unEquip();
		
		}
		
		else if(e.getCode() == KeyCode.DIGIT6){
			if (!herder.isInventoryGreaterThan(6)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(5).getName())){
				herder.equip(herder.getItems().get(5).getName());
			}
			else herder.unEquip();
		}
		
		else if(e.getCode() == KeyCode.DIGIT7){
			if (!herder.isInventoryGreaterThan(7)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(6).getName())){
				herder.equip(herder.getItems().get(6).getName());
			}
			else herder.unEquip();
		}
		
		else if(e.getCode() == KeyCode.DIGIT8){
			if (!herder.isInventoryGreaterThan(8)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(7).getName())){
				herder.equip(herder.getItems().get(7).getName());
			}
			else herder.unEquip();
		}
		
		else if(e.getCode() == KeyCode.DIGIT9){
			if (!herder.isInventoryGreaterThan(9)) return;
			if (!herder.getItemEquipped().equals(herder.getItems().get(8).getName())){
				herder.equip(herder.getItems().get(8).getName());
			}
			else herder.unEquip();
		}
		
		
	
	}

	private boolean isBlocked() {
		return this.blocked;
	}

	public void mouseClicked(MouseEvent e, String state) {
		this.group.getChildren().removeAll(statsDisplay);
		//this.group.getChildren().removeAll(herder.getGroup());
		this.group.getChildren().removeAll(sd.getStartGroup());
		this.group.getChildren().removeAll(wd.getWinningGroup());
		this.group.getChildren().removeAll(ld);
		// GameSounds.stopTheme();

		this.state = state;

		switch (state) {
		case "RETRY":
			initateGame(this.levelCount);
			break;
		case "NEW":
			initateGame(++this.levelCount);
			break;
		}
	}

	public void placePlayer() {
		bd.drawHerder(herder.getLocation());
		bd.setHPvisibility(herder.getHPbar(), false);
		bd.animateHPMovement(herder.getHPbar(), herder.getLocation());
	}

	public void placeSheep(ArrayList<Sheep> sheeps) {
		this.sheeps = sheeps;
		for (Sheep sheep : sheeps) {
			bd.drawSheep(sheep);
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

		if (herder.getLives() <= 0) {
			return 2;
		}
		if (sheepCount == 0) {
			return 1;
		}

		if (level.hasWolf()) {
			if (level.getSquareAt(wolf.getWolfPosition()).equals(
					Square.BABYSHEEP)
					|| level.getSquareAt(wolf.getWolfPosition()).equals(
							Square.BIGSHEEP)) {
				return 2;
			}
		}

		if (level.hasWolf()) {
			if (wolfAteHerder()) {
				herder.takeDamage();
			}
		}
		return 0;
	}

	private boolean sheepFenced() {
		if (level.hasFencePoint()) {
			if (level.getSquareAt(level.getFencePoint())
					.equals(Square.BIGSHEEP)) {
				sheepCount--;
				GameSounds.playBaa();
				statsDisplay.sheepCaught();
				level.setSquareAt(level.getFencePoint(), Square.GRASS);
				bd.updateSquareAt(level.getFencePoint(), Square.GRASS);
				return true;
			}
		}
		return false;
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

	public BoardDisplay getBoardDisplay() {
		return this.bd;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean containsSheep(Point p) {
		for (Sheep sheep : sheeps) {
			if (sheep.getLocation().equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	public Sheep getSheep(Point p) {
		for (Sheep sheep : sheeps) {
			if (sheep.getLocation().equals(p)) {
				return sheep;
			}
		}
		return null;
	}

	public ArrayList<Sheep> getSheepArray() {
		return this.sheeps;
	}
	
	public ArrayList<Item> getItemsOnMap(){
		return this.itemsOnMap;
	}
	
	public Rectangle getHerderHP() {
		return herder.getHPbar();
	}
}
