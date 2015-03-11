package enums;




import javafx.scene.image.Image;



public enum Square {
	
	
	
	GRASS(new Image("images/basic-tile.png")),
	SHEEP(new Image("images/sheep-tile.png")), 
	HERDER(new Image("images/herder-tile.png")),
	START_DISPLAY(new Image("images/startdisplay.png")),
	WINNER_DISPLAY(new Image("images/winnerdisplay.png")),
	LOSER_DISPLAY(new Image("images/loserDisplay.png")),
	BANANA(new Image("images/banana.png")),
	BEER(new Image("images/beerbottle.png")),
	BACKGROUND(new Image("images/backgroundDisplay.png")),
	TREE(new Image("images/tree.png")),
	HOLE(new Image("images/holeintheground.png")),
	ROCK(new Image("images/rock.png"));
	
	

	
	
	private Image img;
	
	private Square(Image image){
		
		
		this.img = image;
		
	}
	
	public Image getImage(){
		return this.img;
	}
	
	

}
