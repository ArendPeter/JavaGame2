import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	KeyboardController keyboard;
	
	int playerX = 10;
	int playerY = 10;
	
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
		keyboard = new KeyboardController();
		this.addKeyListener(keyboard);
		setFocusable(true);
		requestFocus();
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
		double sqrt2 = Math.sqrt(2);
		boolean verticalInput = keyboard.isUpHeld() || keyboard.isDownHeld();
		boolean horizontalInput = keyboard.isLeftHeld() || keyboard.isRightHeld();
		
		if(keyboard.isUpHeld()){
			if(!horizontalInput){
				playerY-=8;
			}else{
				playerY-= (int)(8 / sqrt2);
			}
		}
		if(keyboard.isDownHeld()){
			if(!horizontalInput){
				playerY+=8;
			}else{
				playerY+= (int)(8 / sqrt2);
			}
		}
		if(keyboard.isLeftHeld()){
			if(!verticalInput){
				playerX-=8;
			}else{
				playerX-= (int)(8 / sqrt2);
			}
		}
		if(keyboard.isRightHeld()){
			if(!verticalInput){
				playerX+=8;
			}else{
				playerX+= (int)(8 / sqrt2);
			}
		}
	}


	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.ORANGE);
		g.fillRect(playerX,playerY,50,50);
	}
}
