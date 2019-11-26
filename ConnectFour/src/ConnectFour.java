import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ConnectFour extends JFrame implements ActionListener {

	/**
	 * Serial Version UID: 1L (Default)
	 */
	private static final long serialVersionUID = 1L;
	
	private final static int WIDTH = 512;
	private final static int HEIGHT = 597;
	private final static int X = (Toolkit.getDefaultToolkit().getScreenSize().width - WIDTH) / 2;
	private final static int Y = (Toolkit.getDefaultToolkit().getScreenSize().height - HEIGHT) / 2;
	
	private final static Color COLOR_ONE = Color.YELLOW;
	private final static Color COLOR_TWO = Color.RED;
	private final static Color COLOR_DEFAULT = Color.GRAY;
	
	private static Map<Integer, JButton> buttons;
	private static Game cFour;
	private static Draw drawPanel;
	
	private int butNum;
	
	/**
	 * Sets up the window when first created
	 */
	public ConnectFour() {
		
		setTitle("Connect Four");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Creating Frame
		setBounds(X, Y, WIDTH, HEIGHT);
		
		JPanel contentFrame = new JPanel();
		getContentPane().add(contentFrame);
		contentFrame.setBackground(Color.DARK_GRAY);
		
		contentFrame.setLayout(new GridLayout(0, 1, 0, 0));
		
		try {
			ImageIcon img = new ImageIcon("ConnectFour//connectFourIcon.jpg");
			setIconImage(img.getImage());
		} catch (Exception e) {
		}
				
		//Creating MenuBar
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		mb.add(file);
		mb.add(help);
		JMenuItem nRound = new JMenuItem("New Round");
		JMenuItem nGame = new JMenuItem("New Game");
		JMenuItem nRules = new JMenuItem("Rules");
		JMenuItem credit = new JMenuItem("Credits");
		nRound.addActionListener(this);
		nGame.addActionListener(this);
		nRules.addActionListener(this);
		credit.addActionListener(this);
		file.add(nGame);
		file.add(nRound);
		
		if(!Desktop.isDesktopSupported()) {
			nRules.setEnabled(false);
			credit.setEnabled(false);
		}
		help.add(nRules);
		help.add(credit);
		
		setJMenuBar(mb);
		
		//End of MenuBar
		
		//Start of drawPanel
		drawPanel = new Draw();
		
		drawPanel.setP1Points(cFour.getP1Points());
		drawPanel.setP2Points(cFour.getP2Points());
		
		contentFrame.add(drawPanel);
		//End of drawPanel
		
		for (int i = 0; i < cFour.getNumRow(); i++) {
			
			JPanel panel_char = new JPanel() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public void paintComponent(Graphics g) {}
			};
			contentFrame.add(panel_char);
			panel_char.setLayout(new GridLayout(1, 0, 0, 0));
			
			for (int j = 0; j < cFour.getNumCol(); j++) {
				
				JButton btn = new JButton();
				btn.setBackground(COLOR_DEFAULT);
				btn.setForeground(COLOR_DEFAULT);
				btn.addActionListener(this);
				btn.setActionCommand(Integer.toString(i + j * 6));
				buttons.put(i + j * 6, btn);
				panel_char.add(btn);
				
			}
		}
	}
	
	/**
	 * Main driver
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectFour frame = new ConnectFour();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		buttons = new HashMap<>();
		cFour = new Game();

	}

	@Override
	/**
	 * Runs when the user presses a button or accesses a window
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("New Round")) {
			cFour = new Game(cFour.getP1Points(), cFour.getP2Points());
			drawPanel.ifWin(0);
			resetButtons();
		}
		else if(e.getActionCommand().equals("New Game")) {
			cFour = new Game();
			drawPanel.ifWin(0);
			drawPanel.setP1Points(0);
			drawPanel.setP2Points(0);
			resetButtons();
		}
		else if(e.getActionCommand().equals("Rules") || e.getActionCommand().equals("Credits")) {
			File file = new File("ConnectFour//" + e.getActionCommand() + ".txt");
			Desktop desktop = Desktop.getDesktop();
			try {
				if (file.createNewFile()) {
					FileWriter writer = new FileWriter(file);
					if (e.getActionCommand().equals("Rules")) {
						writer.write("Connect Four Rules\r\n" + 
								"\r\n" + 
								"Object:\r\n" + 
								"To win Connect Four, you must be the first player to get\r\n" + 
								"four of your colored checkers in a row either horizontally,\r\n" + 
								"vertically or diagonally.");
					}
					else {
						writer.write("Credits\r\n"
								+ "\r\n"
								+ "Timothy Dietsch\r\n"
								+ "Timothy Magargee\r\n"
								+ "Timothy Warner");
					}
					writer.close();
				}
				desktop.open(file);
			} catch (IOException ioe) {
			}
		}
		else {
		
			butNum = Integer.parseInt(e.getActionCommand());
			
			cFour.turn(butNum);
			updateBoard();
			cFour.switchPlayer();
		
		}		
	}
	
	/**
	 * Updates the board after an action has been performed
	 */
	public void updateBoard() {
		
		ArrayList<Character> board = cFour.getBoardLayout();
		
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i) == '#') {
				buttons.get(i).setBackground(COLOR_ONE);
				buttons.get(i).setEnabled(false);
			}
			else if (board.get(i) == 'O') {
				buttons.get(i).setBackground(COLOR_TWO);
				buttons.get(i).setEnabled(false);
			}
			else
				buttons.get(i).setBackground(COLOR_DEFAULT);
		}
		
		if (cFour.testWin()) {
			
			for (Integer bi : buttons.keySet()) {
				buttons.get(bi).setEnabled(false);
			}
			
			//drawPanel update
			drawPanel.setP1Points(cFour.getP1Points());
			drawPanel.setP2Points(cFour.getP2Points());
			drawPanel.ifWin(cFour.getCurrPlayerInt());			
			drawPanel.repaint();
			
		}
		else if (cFour.testDraw()) {
			
			//drawPanel update
			drawPanel.ifWin(3);
			drawPanel.repaint();
		}
	}
	
	/**
	 * 
	 */
	private void resetButtons() {		
		for (Integer bi : buttons.keySet()) {
			buttons.get(bi).setBackground(COLOR_DEFAULT);
			buttons.get(bi).setEnabled(true);
		}		
	}

}
