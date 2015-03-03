package graphics;

import enums.Square;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.image.ImageView;
import javafx.scene.Group;


public class BoardDisplay {
	
	
	private final int PREFERRED_DIM = 50; // kvar bilde har denne stoerrelsen
	private Square[][] square; //Liste med squares
	private TilePane tilePane; //Vetsje heilt ka detta e enda
	private ImageView sheep;    // sikkert bilde for sheep og player
	private ImageView player;
	
	private int row;
	private int column;
	
	public BoardDisplay(int rows, int columns, Square[][] squares) {
		
		Group root = new Group();
		root.resize(columns * PREFERRED_DIM, rows * PREFERRED_DIM); // vetsje heilt ka som skjer her 
		
		
		this.row = rows;
		this.column = columns;
		this.square = squares;
		

		this.tilePane = new TilePane();
		this.tilePane.setHgap(0);
		this.tilePane.setVgap(0);
		this.tilePane.setPrefColumns(columns);
		this.tilePane.setPrefRows(rows);
		this.tilePane.setPrefSize(PREFERRED_DIM * rows, PREFERRED_DIM * columns);
		
		sheep = new ImageView();
		player = new ImageView();
		
		sheep.setFitWidth(PREFERRED_DIM);
		sheep.setFitHeight(PREFERRED_DIM);
		sheep.setImage(Square.SHEEP.getImage());
		
		player.setFitWidth(PREFERRED_DIM);
		player.setFitHeight(PREFERRED_DIM);
		player.setImage(Square.HERDER.getImage());
		
		/* 
		 * Setter alle standard squaresa til den predefinerte 2D-arrayen
		 */
		
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				ImageView tileImgView = new ImageView(); 
				tileImgView.setFitHeight(PREFERRED_DIM);
				tileImgView.setFitWidth(PREFERRED_DIM);

				Image tileImg = square[row][column].getImage();
				tileImgView.setImage(tileImg);


				this.tilePane.getChildren().add(tileImgView); // Trur vi adder bilda til tilePanen her
			}
		}
		
		root.getChildren().addAll(this.tilePane);
		
		root.getChildren().add(sheep);
		root.getChildren().add(player);
		
	}
}
