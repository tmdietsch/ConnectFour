
public class Game {

	private Board cBoard;
	
	public Game() {
		
		cBoard = new Board();
		
	}
	
	public void addPiece(int col) {
		
		cBoard.setPos(col);
		
		
		
		cBoard.switchPlayer();
		
	}
	
	public boolean testWin() {
		return cBoard.testWin();
	}
	
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
	
	public char getCurrPlayer() {
		return cBoard.getCurrPlayer();
	}
	
}
