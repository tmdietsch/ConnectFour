import java.util.ArrayList;

public class Game {

	private int p1Points;
	private int p2Points;
	private Board cBoard;
	
	/**
	 * Creates a new board object
	 * Used for a new game
	 */
	public Game() {
		
		cBoard = new Board();
		p1Points = 0;
		p2Points = 0;
		
	}
	
	/**
	 * Starts a new round after a previous game has ended 
	 * @param p1Points
	 * @param p2Points
	 */
	public Game(int p1Points, int p2Points) {
		
		cBoard = new Board();
		this.p1Points = p1Points;
		this.p2Points = p2Points;
	}
	
	/**
	 * Adds a piece where the user wants to play
	 * @param col - column where the user wants to add a piece
	 */
	public void turn(int space) {
		cBoard.setPos(space);
	}
	
	/**
	 * @return true if win conditions are met in the board class
	 */
	public boolean testWin() {
		
		int row, col, i, j, k, count;
		
		for (row = 0; row < cBoard.getNumRow(); row++) {
			for (col = 0; col < cBoard.getNumCol(); col++) {
				if (cBoard.getPos(row, col) == cBoard.getCurrPlayer()) {
					for (i = -1; i <= 1; i++) {
						for (j = -1; j <= 1; j++) {
							if (i != 0 || j != 0) {
								count = 0;
								
								try {
									for (k = 0; cBoard.getPos(row + (i * k), col + (j * k)) == cBoard.getCurrPlayer(); k++) {
										count++;
										if (count == 4) {
											if (cBoard.getCurrPlayer() == cBoard.getP1()) 
												p1Points++;
											else
												p2Points++;
											return true;
										}
									}
									
								} catch (Exception e) {
									
								}
							} // if i and j are both not zero
						}// j
					}// i
				}// if cBoard
			}// col
		}// row
		
		return false;
		
	}
	
	/**
	 * Checks to see if the board is in a draw condition,
	 * where all the spaces are used and there are no more valid moves
	 * 
	 * @return	boolean if the board is in a draw condition
	 */
	public boolean testDraw() {
		for (char c : getBoardLayout()) {
			if (c == cBoard.getEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Changes turns
	 */
	public void switchPlayer() {
		
		if (cBoard.getCurrPlayer() == cBoard.getP1())
			cBoard.setCurrPlayer(cBoard.getP2());
		else
			cBoard.setCurrPlayer(cBoard.getP1());
		
	}
	
	/**
	 * Gets the number of rows in the board
	 * @return int Row
	 */
	public int getNumRow() {return cBoard.getNumRow();}
	
	/**
	 * Gets the number of columns in the board
	 * @return int Column
	 */
	public int getNumCol() {return cBoard.getNumCol();}
	
	/**
	 * Gets the number of points for player 1
	 * @return int Player one points 
	 */
	public int getP1Points() {return p1Points;}
	
	/**
	 * Gets the number of points for player 2
	 * @return int Player two points 
	 */
	public int getP2Points() {return p2Points;}
	
	/**
	 * @return the board represented as an ArrayList of characters
	 */
	public ArrayList<Character> getBoardLayout() {
		
		ArrayList<Character> tempBoard = new ArrayList<>();
		
		int i = 0;
		try {
			while(true) {
				tempBoard.add(cBoard.getPos(i));
				
				i++;
			}
		} catch(Exception e) {
		}
		
		return tempBoard;
		
	}
	
	/**
	 * @return char CurrPlayer the player whose turn it is currently, determined in board
	 */
	public char getCurrPlayer() {
		return cBoard.getCurrPlayer();
	}
	
	/**
	 * 
	 * @return int current player 1 or 2 
	 */
	public int getCurrPlayerInt() {
		
		if (getCurrPlayer() == cBoard.getP1())
			return 1;
		else
			return 2;
	}
	
}
