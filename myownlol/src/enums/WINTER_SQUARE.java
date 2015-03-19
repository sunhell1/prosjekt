package enums;

import javafx.scene.image.Image;

public enum WINTER_SQUARE {
	SNOWSQUARE(new Image("images/snow-tile.png")),
	ICESQUARE(new Image("images/ice-tile.png")),
	SNOWTREE(new Image("images/snow-tree.png")),
	SNOWROCK(new Image("images/snow-rock.png")),
	SNOWBIGSHEEP(new Image("images/snow-bigsheep.png"));
	
	private Image img;
	
	private WINTER_SQUARE(Image image) {

		this.img = image;

	}

	public Image getImage() {
		return this.img;
	}

}
