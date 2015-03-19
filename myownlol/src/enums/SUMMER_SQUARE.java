package enums;

import javafx.scene.image.Image;

public enum SUMMER_SQUARE {
	
	GRASS(new Image("images/basic-tile.png")), 
	BABYSHEEP(new Image("images/sheep-tile.png")),
	PICKAXESQUARE(new Image("images/pickaxeitem.png")),
	PICKAXE_ITEM(new Image("images/pickaxeitem.png")),
	ROCK(new Image("images/rock.png")),
	BREAKABLE_ROCK(new Image("images/pickablerock.png")),
	HFENCE(new Image("images/Hfence.png")),
	VFENCE(new Image("images/Vfence.png")),
	WOLF(new Image("images/wolf-tile.png")),
	HOLE(new Image("images/holeintheground.png")),
	TREE(new Image("images/basic-tile.png")),
	BANANA(new Image("images/banana.png")),
	BEER(new Image("images/beerbottle.png")),
	BIGSHEEP(new Image("images/bigsheep.png"));
	
	private Image img;
	
	private SUMMER_SQUARE(Image image) {

		this.img = image;

	}

	public Image getImage() {
		return this.img;
	}

}
