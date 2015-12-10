import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	int playerX = 10; 
	int playerY = 10; 
	
	boolean right = true;
	boolean down = false;

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
		new Thread(){
			public void run(){
				try{
					while(true){
						gameLoop();
						repaint();
						Thread.sleep(33);
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private void gameLoop(){
		if(playerX+50 > getWidth()){
			right = false;
		}
		if(playerX < 0){
			right = true;
		}
		if(playerY+50 > getHeight()){
			down = false;
		}
		if(playerY < 0){
			down = true;
		}

		playerX += (right)? 5 : -5;
		playerY += (down)? 5 : -5;
	}


	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.ORANGE);
		g.fillRect(playerX,playerY,50,50);
	}
}
