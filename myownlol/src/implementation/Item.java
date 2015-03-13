package implementation;

import javafx.scene.image.Image;

public class Item {

	private Image img;
	private String name;

	public Item(String name, Image img) {

		this.name = name;
		this.img = img;

	}

	public String getName() {
		return this.name;

	}

	public Image getImage() {

		return this.img;
	}

}
