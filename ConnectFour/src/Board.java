import java.util.ArrayList;

public class Board {

	private final static int NUM_ROW = 6;
	private final static int NUM_COL = 7;
	private final static int TOT_SPACES = NUM_ROW * NUM_COL;
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
	 * @return
	 */
	public int getNumRow() {return NUM_ROW;}
	
	/**
	 * 
	 * @return
	 */
	public int getNumCol() {return NUM_COL;}
	
	/**
	 * 
	 * @return
	 */
	public char getP1() {return P1;}
	
	/**
	 * 
	 * @return
	 */
	public char getP2() {return P2;}
	
	/**
	 * 
	 * @return
	 */
	public char getEmpty() {return EMPTY;}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public char getPos(int row, int col) {
		if (row >= 0 && col >=0 && row < NUM_ROW && col < NUM_COL)
			return boardList.get(row + col * NUM_ROW);
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * 
	 * @param space
	 * @return
	 */
	public char getPos(int space) {return boardList.get(space);}
	
	/**
	 * 
	 * @param col
	 * @return
	 */
	public boolean setPos(int space) {
		if (validPos(space)) {
			
			int column = space / NUM_ROW;		
			for (int i = NUM_ROW - 1; i >= 0; i--) {
				
				if (boardList.get((column * NUM_ROW) + i) == ' ') {
					boardList.set((column * NUM_ROW) + i, currentPlayer);
					return true;
				}				
			}			
		}
		return false;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public char getCurrPlayer() {return currentPlayer;}
	
	/**
	 * 
	 * @param p
	 */
	public void setCurrPlayer(char p) {currentPlayer = p;}
	
	/**
	 * 
	 * @param col
	 * @return
	 */
	private boolean validPos(int space) {
		
		try {
			if (space >= 0 && space < TOT_SPACES && getPos(space) == EMPTY) {
				return true;
			}
		} catch (Exception e) {
		}
		
		return false;
		
	}
	
}
