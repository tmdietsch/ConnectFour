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
	 * @param row
	 * @param col
	 * @return
	 */
	public char getPos(int row, int col) {return boardList.get(row + col * NUM_ROW);}
	
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
	
//	/**
//	 * 
//	 */
//	public void printBoard() {
//		
//		for (int i = 0; i < NUM_ROW; i++) {
//			for (int j = 0; j < NUM_COL; j++) {
//				
//				System.out.print(boardList.get(i + j * NUM_ROW));
//				
//			}
//			System.out.println();
//		}
//		
//		System.out.println("---------------");
//		
//	}
	
}
