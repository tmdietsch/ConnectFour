import java.util.ArrayList;

public class Game {

	private Board cBoard;
	
	/**
	 * Creates a new board object
	 */
	public Game() {
		
		cBoard = new Board();
		
	}
	
	/**
	 * Adds a piece where the user wants to play
	 * @param col - column where the user wants to add a piece
	 */
	public void turn(int space) {
		if (cBoard.setPos(space)) {
			cBoard.switchPlayer();
		}
	}
	
	/**
	 * @return true if win conditions are met in the board class
	 */
	public boolean testWin() {	//Currently Broken
		
		int row, col, i, j, k, count;
		
		for (row = 0; row < cBoard.getNumRow(); row++) {
			for (col = 0; col < cBoard.getNumCol(); col++) {
				if (cBoard.getPos(row, col) == cBoard.getCurrPlayer()) {
					
					for (i = -1; i <= 1; i++) {
						for (j = -1; j <= 1; j++) {
							if (i * j != 0) {
								count = 0;
								try {
									for (k = 0; cBoard.getPos(row + (i * k), col + (j * k)) == cBoard.getCurrPlayer(); k++) {
										count++;
										if (count == 4) {
											return true;
										}
										
									}
								}
								catch (Exception e) {
									
								}
							}
						}
					}
						
				}
			}
		}
		
		return false;
		
	}
	
	/**
	 * @return the board represented as a 2D array of characters
	 */
	public char[][] getBoardLayout() {
		
		char[][] tempBoard = new char[6][7];
		
		int i;
		int j;
		
		for (i = 0; i < tempBoard.length; i++) {
			for (j = 0; j < tempBoard[i].length; j++) {
				tempBoard[i][j] = cBoard.getPos(i, j);
			}
		}
		
		return tempBoard;
		
	}
	
	/**
	 * @return the player whose turn it is currently, determined in board
	 */
	public char getCurrPlayer() {
		return cBoard.getCurrPlayer();
	}
	
}
