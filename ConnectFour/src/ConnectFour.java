import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ConnectFour extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static int WIDTH = 512;
	private final static int HEIGHT = 512;
	
	private final static int B_WIDTH = WIDTH / 7;
	private final static int B_HEIGHT = HEIGHT / 7;
	
	private JPanel contentFrame;
	private static Map<Integer, JButton> buttons;
	private static Game cFour;
	
	/**
	 * Sets up the window when first created
	 */
	public ConnectFour() {
		
		setTitle("Connect Four");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Creating Frame
		setBounds(100, 100, WIDTH, HEIGHT);
		
		
		contentFrame = new JPanel();
		setContentPane(contentFrame);
		
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
		JMenuItem nGame = new JMenuItem("New Game");
		JMenuItem nRules = new JMenuItem("Rules");
		nGame.addActionListener(this);
		nRules.addActionListener(this);
		file.add(nGame);
		
		if(!Desktop.isDesktopSupported()) {
			nRules.setEnabled(false);
		}
		
		help.add(nRules);
		
		this.setJMenuBar(mb);
		
		char[][] tempAry = cFour.getBoardLayout();		
		
		for (int i = 0; i < tempAry.length; i++) {
			
			JPanel panel_char = new JPanel();
			contentFrame.add(panel_char);
			panel_char.setLayout(new GridLayout(1, 0, 0, 0));
			
			for (int j = 0; j < tempAry[i].length; j++) {
				
				JButton btn = new JButton();
				btn.setBackground(Color.GRAY);
				btn.setForeground(Color.GRAY);
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
		System.out.println(e.getActionCommand());	//remove
		if (e.getActionCommand().equals("New Game")) {
			cFour = new Game();
			resetButtons();
		}
		else if(e.getActionCommand().equals("Rules")) {
			File file = new File("ConnectFour//Rules.txt");
			Desktop desktop = Desktop.getDesktop();
			
			try {
				if (file.createNewFile()) {
					FileWriter writer = new FileWriter(file);
					writer.write("Connect Four Rules\r\n" + 
							"\r\n" + 
							"Object:\r\n" + 
							"To win Connect Four, you must be the first player to get\r\n" + 
							"four of your colored checkers in a row either horizontally,\r\n" + 
							"vertically or diagonally.");
					writer.close();
				}
				desktop.open(file);
			} catch (IOException ioe) {
				
			}
		}
		else {
		
			int butNum = Integer.parseInt(e.getActionCommand());
			
			cFour.turn(butNum);
		
		}
		
		updateBoard();
		
	}
	
	/**
	 * Updates the board after an action has been performed
	 */
	public void updateBoard() {
		
		char[][] temp = cFour.getBoardLayout();
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				
				if (temp[i][j] == '#')
					buttons.get(i + j * 6).setBackground(Color.YELLOW);
				else if (temp[i][j] == 'O')
					buttons.get(i + j * 6).setBackground(Color.RED);
				else
					buttons.get(i + j * 6).setBackground(Color.GRAY);
				
			}
		}
		
		if (cFour.testWin()) {
			System.out.println(cFour.getCurrPlayer() + " Wins!");
		}
		
	}
	
	/**
	 * 
	 */
	private void resetButtons() {
		
		char[][] temp = cFour.getBoardLayout();
		
		for (int i = 0; i < 6; i++) 
			for (int j = 0; j < 7; j++) 
				buttons.get(i*6 + j).setBackground(Color.GRAY);
		
	}

}
