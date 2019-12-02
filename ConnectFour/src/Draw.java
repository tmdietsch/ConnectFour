import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Draw extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String p1Str = "Player 1";
	private final String p2Str = "Player 2";
	private final String winStr = " Wins!";
	private int winCondition;
	private Color c;
	private Color bgC;
	private int pOne;
	private int pTwo;
	
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
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(bgC);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString(p1Str + ": " + pOne, 10, 30);
        g.setColor(Color.RED);
        g.drawString(p2Str + ": " + pTwo, 10, 60);
		if (winCondition > 0) {
			String s = "";
			g.setColor(c);
			if (winCondition == 3)
				s = "It's a Draw!";
			else 
				s = "Player " + winCondition + winStr;
			
			g.drawString(s, 270, 45);
//			JButton jb = new JButton();
//			add(jb, BorderLayout.CENTER);
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
	 * The method accepts integers from 0 to 2, 0 corresponding to no one, and 1 and 2
	 * corresponding with the player that has won
	 * @param w
	 */
	public void ifWin(int w) {
		if (w >= 0 && w <= 3) {
			winCondition = w;
		}
	}
	
	/**
	 * Sets the color of the square 
	 * @param c
	 */
	public void setColor(Color c) {
		this.c = c;
	}
	
}
