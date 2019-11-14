
public class Board {

	private final static int NUM_ROW = 6;
	private final static int NUM_COL = 7;
	private final static char P1 = '#';
	private final static char P2 = 'O';
	private final static char EMPTY = ' ';
	
	private char currentPlayer;
	private char[][] board;
	
	public Board() {
		
		currentPlayer = P1;
		board = new char[NUM_ROW][NUM_COL];
		
		int i;
		int j;
		for (i = 0; i < NUM_ROW; i++) {
			for (j = 0; j < NUM_COL; j++) {
				
				board[i][j] = EMPTY;
				
			}
		}
		
	}
	
	public Board(Board b) {
		
		this.board = new char[NUM_ROW][NUM_COL];
		
		int p1Count = 0;
		int p2Count = 0;
		int i;
		int j;
		
		for (i = 0; i < NUM_ROW; i++) {
			for (j = 0; j < NUM_COL; j++) {
				
				this.board[i][j] = b.board[i][j];
				if (b.board[i][j] == P1) {
					p1Count++;
				}
				else if(b.board[i][j] == P2) {
					p2Count++;
				}
				
			}
		}
		
		if (p1Count == p2Count)
			currentPlayer = P1;
		else
			currentPlayer = P2;
		
	}
	
	public char getPos(int row, int col) {
		
		if (validPos(row, col)) {
			
			return board[row][col];
			
		}
		
		throw new IllegalArgumentException("Invalid board position");
		
	}
	
	public void setPos(int col) {
		if (validPos(col)) {
			
			for (int i = 0; i < NUM_ROW; i++) {
				
				if (board[i][col] == ' ') {
					board[i][col] = currentPlayer;
					return;
				}
				
			}
			
		}
	}
	
	public void switchPlayer() {
		
		if (currentPlayer == P1)
			currentPlayer = P2;
		else
			currentPlayer = P1;
		
	}
	
	public char getCurrPlayer() {
		return currentPlayer;
	}
	
	public boolean testWin() {
		
		int i;
		int j;
		int k;
		int l;
		int m;
		int count;
		for (i = 0; i < NUM_ROW; i++) {
			for (j = 0; j < NUM_COL; j++) {
				if (board[i][j] == currentPlayer) {
						
					for (k = -1; k <= 1; k++) {
						for (l = -1; l <= 1; l++) {
							count = 1;
							try {
								for (m = 1; board[i + (m * k)][j + (m * l)] == currentPlayer; m++) {
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
	
	private boolean validPos(int col) {
		
		try {
			if (col >= 0 && col < NUM_COL && board[NUM_COL - 1][col] == EMPTY)
				return true;
		} catch (Exception e) {		
		}
		
		return false;
		
	}
	
	private boolean validPos(int row, int col) {
		
		try {
			if (col >= 0 && col < NUM_COL
					&& row >= 0 && row < NUM_ROW 
					&& board[row][col] == EMPTY)
				return true;
		} catch (Exception e) {		
		}
		
		return false;
		
	}
	
}
