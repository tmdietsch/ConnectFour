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
											System.out.println(cBoard.getCurrPlName() + " Wins!"); //Replace with graphic on window
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
	 * 
	 */
	public void switchPlayer() {
		
		if (cBoard.getCurrPlayer() == cBoard.getP1())
			cBoard.setCurrPlayer(cBoard.getP2());
		else
			cBoard.setCurrPlayer(cBoard.getP1());
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumRow() {return cBoard.getNumRow();}
	
	/**
	 * 
	 * @return
	 */
	public int getNumCol() {return cBoard.getNumCol();}
	
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
	 * @return the player whose turn it is currently, determined in board
	 */
	public char getCurrPlayer() {
		return cBoard.getCurrPlayer();
	}
	
}
