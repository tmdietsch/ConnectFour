import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ConnectFour extends JFrame implements ActionListener{

	private final static int WIDTH = 512;
	private final static int HEIGHT = 512;
	
	private final static int B_WIDTH = WIDTH / 7;
	private final static int B_HEIGHT = HEIGHT / 7;
	
	private JPanel contentFrame;
	private static Game cFour;
	
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
				//btn.setEnabled(false);
				panel_char.add(btn);
				
			}
		}
		
	}
	
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
		
		cFour = new Game();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int butNum = Integer.getInteger(e.getActionCommand());
		
		cFour.addPiece(butNum);
		
		//contentFrame.get
		
	}
	
	public void updateBoard() {
		
		char[][] temp = cFour.getBoardLayout();
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				
				JButton button = getButton(i, j);
				
				if (temp[i][j] == '#')
					button.setBackground(Color.YELLOW);
				else if (temp[i][j] == 'O')
					button.setBackground(Color.RED);
				else
					button.setBackground(Color.GRAY);
				
			}
		}
		
		
		
	}
	
	private JButton getButton(int x, int y) {
		return (JButton)contentFrame.getComponentAt(x * B_WIDTH + + B_WIDTH/2, y * B_HEIGHT + B_HEIGHT/2);
	}

}
