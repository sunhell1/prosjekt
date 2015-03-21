package implementation;

import java.awt.Point;

import javafx.scene.image.ImageView;
import enums.Condition;
import enums.Square;
import graphics.BoardDisplay;

public class Wolf extends ImageView {
	
	private Condition con;
	private Point location;
	private BoardDisplay bd;
	private final int PREFERRED_DIM = 50;
	
	public Wolf(Point startLoc, Condition condition, BoardDisplay bd) {
		
		this.setFitHeight(PREFERRED_DIM);
		this.setFitWidth(PREFERRED_DIM);
		this.setImage(Square.WOLF.getImage());
		
		this.location = startLoc;
		this.con = condition;
		this.bd = bd;
		
	}
	
	public void moveWolf(Point nextPosition) {
		this.location = nextPosition;
		bd.animateWolfMovement(location);
	}
	
	public Point getWolfPosition() {
		return this.location;
	}
}
