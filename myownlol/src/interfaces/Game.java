package interfaces;

import enums.Square;

public interface Game {

	public void newLevel(boolean cleared);
	public Board getBoard();
	public void gameOver(boolean gameover);
	public void theWinnerIs(Player player);

}
