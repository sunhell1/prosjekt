package implementation;

import java.util.Random;

import javafx.scene.image.Image;

public class Item {

	private Image img;
	private String name;
	private int minDMG;
	private int maxDMG;

	public Item(String name, Image img, int minDMG, int maxDMG) {

		this.name = name;
		this.img = img;
		this.minDMG = minDMG;
		this.maxDMG = maxDMG;

	}

	public String getName() {
		return this.name;

	}

	public Image getImage() {

		return this.img;
	}
	
	public int getMinDmg(){
		return this.minDMG;
	}
	
	public int getMaxDmg(){
		return this.maxDMG;
	}
	
	public int getCalculatedDamage(){
		
		Random r = new Random();
		int low = this.minDMG;
		int high = this.maxDMG;
		
		int dmg = r.nextInt(high-low) + low;
		dmg += 1;
		
		return dmg;
	
	}
	

}
