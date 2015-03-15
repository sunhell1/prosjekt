package enums;




import javafx.scene.image.Image;



public enum Square {
	
	
	
	GRASS(new Image("images/basic-tile.png")),
	SHEEP(new Image("images/sheep-tile.png")), 
	HERDER(new Image("images/herder-tile.png")),
	HERDER_PICKAXE(new Image("images/pickedrock.png")),
	HERDER_SMACK(new Image("images/herdersmack.png")),
	WINNER_DISPLAY(new Image("images/winnerdisplay.png")),
	LOSER_DISPLAY(new Image("images/loserDisplay.png")),
	BANANA(new Image("images/banana.png")),
	BEER(new Image("images/beerbottle.png")),
	BACKGROUND(new Image("images/backgroundDisplay.png")),
	TREE(new Image("images/basic-tile.png")),
	TREE_IMAGE(new Image("images/tree.png")),
	HOLE(new Image("images/holeintheground.png")),
	PICKAXESQUARE(new Image("images/pickaxeitem.png")),
	ROCK(new Image("images/rock.png")),
	PICKAXE(new Image("images/pickaxe.png")),
	WOLF(new Image("images/wolf-tile.png")),
	BREAKABLE_ROCK(new Image("images/pickablerock.png")),
	PICKAXE_ITEM(new Image("images/pickaxeitem.png"));
	
	

	
	
	private Image img;
	
	private Square(Image image){
		
		
		this.img = image;
		
	}
	
	public Image getImage(){
		return this.img;
	}
	
	

}
