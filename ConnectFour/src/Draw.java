import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Draw extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String p1Str = "Player 1";
	private final String p2Str = "Player 2";
	private final String winStr = " Wins!";
	private int winCondition;	//winCondition as described in the ifWin method
	private Color c;	//Win condition text color
	private Color bgC;	//Background color
	private int pOne;	//Player 1's points
	private int pTwo;	//Player 2's points
	
	/**
	 * Creates default board 
	 */
	public Draw() {
		pOne = 0;
		pTwo = 0;
		c = Color.LIGHT_GRAY;
		bgC = Color.DARK_GRAY;
		winCondition = 0;
	}
	
	/**
	 * Displays player's scores and says if one of the players won,
	 * or if the game ended in a draw
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(bgC);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString(p1Str + ": " + pOne, 10, 30);
        g.setColor(Color.RED);
        g.drawString(p2Str + ": " + pTwo, 10, 60);
		if (winCondition != 0) {
			String s = "";
			g.setColor(c);
			if (winCondition == 3)
				s = "It's a Draw!";
			else 
				s = "Player " + winCondition + winStr;
			
			g.drawString(s, 270, 45);
		}
    }
	
	/**
	 * Number of rounds won by player one
	 * @param p
	 */
	public void setP1Points(int p) {
		pOne = p;
	}
	
	/**
	 * Number of rounds won by player two
	 * @param p
	 */
	public void setP2Points(int p) {
		pTwo = p;
	}
	
	/**
	 * 
	 * @return pOne Number of rounds won by player one
	 */
	public int getP1Points() {return pOne;}
	
	/**
	 * 
	 * @return pTwo Number of rounds won by player two
	 */
	public int getP2Points() {return pTwo;}
	
	/**
	 * The method accepts integers from 0 to 3, 0 if the game is still ongoing, 1 and 2
	 * corresponding with the player that has won, and 3 if the game ended in a draw
	 * @param w
	 */
	public void ifWin(int w) {
		if (w >= 0 && w <= 3) {
			winCondition = w;
		}
	}
	
	/**
	 * Sets the color of the win condition text
	 * @param c
	 */
	public void setColor(Color c) {
		this.c = c;
	}
}
