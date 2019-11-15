import java.util.ArrayList;

public class Board {

	private final static int NUM_ROW = 6;
	private final static int NUM_COL = 7;
	private final static char P1 = '#';
	private final static char P2 = 'O';
	private final static char EMPTY = ' ';
	
	private char currentPlayer;
	private ArrayList<Character> boardList;
	
	/**
	 * 
	 */
	public Board() {
		
		currentPlayer = P1;
		boardList = new ArrayList<>();
		
		int i;
		int j;
		for (i = 0; i < NUM_ROW; i++) {
			for (j = 0; j < NUM_COL; j++) {
				
				boardList.add(EMPTY);
				
			}
		}
		
	}
	
	/**
	 * 
	 * @param b
	 */
	public Board(Board b) {
		
		this.boardList = new ArrayList<>();
		
		int p1Count = 0;
		int p2Count = 0;
		int i;
		int j;
		
		for (i = 0; i < NUM_ROW; i++) {
			for (j = 0; j < NUM_COL; j++) {
				
				this.boardList.add(b.boardList.get(i + j));
				if (this.boardList.get(i + j) == P1) {
					p1Count++;
				}
				else if(this.boardList.get(i + j) == P2) {
					p2Count++;
				}
				
			}
		}
		
		if (p1Count == p2Count)
			currentPlayer = P1;
		else
			currentPlayer = P2;
		
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public char getPos(int row, int col) {
		
		if (validPos(row, col)) {
			
			return boardList.get(row + col * NUM_ROW);
			
		}
		
		throw new IllegalArgumentException("Invalid board position");
		
	}
	
	/**
	 * 
	 * @param col
	 */
	public void setPos(int col) {
		if (validPos(col)) {
			
			for (int i = 0; i < NUM_ROW; i++) {
				
				if (boardList.get(i + col * NUM_ROW) == ' ') {
					boardList.set(i + col * NUM_ROW, currentPlayer);
					return;
				}
				
			}
			
		}
	}
	
	/**
	 * 
	 */
	public void switchPlayer() {
		
		if (currentPlayer == P1)
			currentPlayer = P2;
		else
			currentPlayer = P1;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public char getCurrPlayer() {return currentPlayer;}
	
	/**
	 * 
	 * @return
	 */
	public boolean testWin() {
		
		int row, col, i, j, k, count;
		
		for (row = 0; row < NUM_ROW; row++) {
			for (col = 0; col < NUM_COL; col++) {
				if (boardList.get(row + col * NUM_COL) == currentPlayer) {
						
					for (i = -1; i <= 1; i++) {
						for (j = -1; j <= 1; j++) {
							count = 1;
							try {
								for (k = 1; boardList.get((row + (i * k)) + (col + (j * k)) * NUM_ROW) == currentPlayer; k++) {
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
		
		return false;
		
	}
	
	/**
	 * 
	 * @param col
	 * @return
	 */
	private boolean validPos(int col) {
		
		try {
			if (col >= 0 && col < NUM_COL && boardList.get(col * NUM_ROW) == EMPTY)
				return true;
		} catch (Exception e) {		
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean validPos(int row, int col) {
		
		try {
			if (col >= 0 && col < NUM_COL
					&& row >= 0 && row < NUM_ROW 
					&& boardList.get(row + col * NUM_ROW) == EMPTY)
				return true;
		} catch (Exception e) {		
		}
		
		return false;
		
	}
	
}
