package implementation;

public class Game implements interfaces.Game {
	
	Player p1;
	Sheep s;
	Board b;

	public Game(Player player, Sheep sheep, Board board){
		this.p1 = player;
		this.s = sheep;
		this.b = board;
		
	}

	@Override
	public void newLevel(boolean cleared) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public implementation.Board getBoard() {
		return this.b;
	}

	@Override
	public void gameOver(boolean gameover) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void theWinnerIs(interfaces.Player player) {
		// TODO
	}
	
	
	
	
	
}
