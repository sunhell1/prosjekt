package enums;




import javafx.scene.image.Image;



public enum Square {
	
	
	
	GRASS(new Image("images/basic-tile.png")),
	SHEEP(new Image("images/sheep-tile.png")), 
	HERDER(new Image("images/herder-tile.png"));
	
	

	
	
	private Image img;
	
	private Square(Image image){
		
		
		this.img = image;
		
	}
	
	public Image getImage(){
		return this.img;
	}
	
	

}
