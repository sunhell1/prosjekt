package graphics;

import java.awt.Point;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import enums.Condition;
import enums.Constants;
import enums.Direction;
import enums.Levels;
import implementation.Board;
import implementation.Player;
import implementation.Sheep;

public class SheepHerder extends Scene {

	private Board board;
	private Sheep sheep;
	private Player herder;
	
	private boolean winner;
	
	private StartDisplay sd;
	private WinningDisplay wd;
	private BoardDisplay bd;
	
	private Group group;
	
	private GUI gui;

	public SheepHerder(Group group, int width, int height, GUI gui,
			WinningDisplay wDisplay, StartDisplay sDisplay,
			BoardDisplay bDisplay) {

		super(group, width, height);
		
		this.group = group;
		
		this.winner = false;
		
		this.wd = wDisplay;
		this.sd = sDisplay;
		this.bd = bDisplay;
		
		this.gui = gui;
		
		startScene();

	}

	public boolean isWinningConiditions() {
		board.updateStatus();
		return (this.sheep.getCondition().equals(Condition.CAUGHT));
	}

	public void startSheepHerder() {
		while (sheep.getCondition().equals(Condition.ALIVE)
				&& herder.getCondition().equals(Condition.ALIVE)) {

			this.setOnKeyPressed(event -> keyPressed(event));

		}
	}

	public Image getImageAt(int x, int y) {
		return this.board.getSquareAt(x, y).getImage();
	}

	public void startScene() {
		this.group.getChildren().add(sd.getStartGroup());
		sd.getStartButton().setOnMouseClicked(event -> mouseClicked(event));

	}

	public void winningScene() {
		this.group.getChildren().add(wd.getWinningGroup());
		this.setOnMouseClicked(event -> mouseClicked(event));

	}

	public void initateGame(int levelNumber) {
		System.out.println("ER I INITIATE GAME");
		this.group.getChildren().remove(sd.getStartGroup());
		this.group.getChildren().remove(wd.getWinningGroup());
		this.group.getChildren().add(bd.getGroup());

		Point sheepPoint = Constants.sheepStartPoint;
		Point playerPoint = Constants.playerStartPoint;

		this.board = new Board();

		this.herder = new Player(Direction.NORTH, playerPoint, board,
				Condition.ALIVE);

		this.sheep = new Sheep(sheepPoint, Direction.SOUTH, board,
				Condition.ALIVE);

		board.setPlayer(this.herder);
		board.setSheep(this.sheep);

		startSheepHerder();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			gui.closePrimary();
		} else if (e.getCode() == KeyCode.UP) {
			herder.move(herder.getLocation(), Direction.NORTH);
		} else if (e.getCode() == KeyCode.DOWN) {
			herder.move(herder.getLocation(), Direction.SOUTH);
		} else if (e.getCode() == KeyCode.LEFT) {
			herder.move(herder.getLocation(), Direction.WEST);
		} else if (e.getCode() == KeyCode.RIGHT) {
			herder.move(herder.getLocation(), Direction.EAST);
		}
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("DU KLIKKA PÅ KNAPPEN, DET FUNKA BERRE IKKJE");
		int level = 0;
		initateGame(level++);
	}
}
