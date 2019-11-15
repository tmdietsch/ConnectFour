
public class Game {

	private Board cBoard;
	
	/*
	 * Creates a new board object
	 */
	public Game() {
		
		cBoard = new Board();
		
	}
	
	/*
	 * Adds a piece where the user wants to play
	 * @param col - column where the user wants to add a piece
	 */
	public void addPiece(int col) {
		
		cBoard.setPos(col);
		
		
		
		cBoard.switchPlayer();
		
	}
	
	/*
	 * @return true if win conditions are met in the board class
	 */
	public boolean testWin() {
		return cBoard.testWin();
	}
	
	/*
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
	
	/*
	 * @return the player whose turn it is currently, determined in board
	 */
	public char getCurrPlayer() {
		return cBoard.getCurrPlayer();
	}
	
}
