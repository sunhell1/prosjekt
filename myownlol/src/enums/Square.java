package enums;



import java.awt.Image;




public enum Square {
	
	
	
	BASIC(new Image("images/square.png")), "lol");
	

	
	private String code;
	private Image img;
	
	private Square(Image image, String code){
		
		
		this.img = image;
		this.code = code;
		
		
	}

}
