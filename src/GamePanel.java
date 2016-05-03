import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		GamePanel panel = new GamePanel(); 

		frame.add(panel);

		frame.setVisible(true);
	}

	private GamePanel(){
		this.addKeyListener(KeyboardController.getInstance());
		setFocusable(true);
		requestFocus();
		
		new GameManager(this);
	}
	
	@Override
	protected void paintComponent(Graphics p) {
		GameManager.getInstance().draw(p);
	}
}
