import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		
		//JPanel panel = new JPanel(new GridLayout(7,6));
		//contentFrame.add(panel);
		
//		JPanel panel_1 = new JPanel();
//		panel.add(panel_1);
//		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
				
		//Creating MenuBar
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		mb.add(file);
		mb.add(help);
		JMenuItem nGame = new JMenuItem("New Game");
		nGame.addActionListener(this);
		file.add(nGame);
		
		this.setJMenuBar(mb);
		
//		String[] buttonNames = {"1","2","3","4","5","6","7"};
//		
//		for (String name : buttonNames) {
//			
//			JButton btn = new JButton(name);
//			btn.setBackground(new Color(25, 25, 112));
//			btn.setForeground(Color.WHITE);
//			btn.addActionListener(this);
//			panel_1.add(btn);
//			
//		}
		
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
				btn.setActionCommand(Integer.toString(i * (tempAry.length + 1) + j));
				buttons.put(i * 6 + j, btn);
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
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals("New Game")) {
			cFour = new Game();
			resetButtons();
		}
		else {
		
			int butNum = Integer.parseInt(e.getActionCommand());
			
			cFour.turn(butNum);
		
		}
		
		updateBoard();
		
	}
	
	/**
	 * Updates the board after an action has been preformed
	 */
	public void updateBoard() {
		
		char[][] temp = cFour.getBoardLayout();
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				
				if (temp[i][j] == '#')
					buttons.get(i*6 + j).setBackground(Color.YELLOW);
				else if (temp[i][j] == 'O')
					buttons.get(i*6 + j).setBackground(Color.RED);
				else
					buttons.get(i*6 + j).setBackground(Color.GRAY);
				
			}
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
