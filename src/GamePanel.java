import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener{

	int playerX = 10;
	int playerY = 10;

	boolean upHeld = false;
	boolean downHeld = false;
	boolean rightHeld = false;
	boolean leftHeld = false;

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
		this.addKeyListener(this);
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
		boolean verticalInput = upHeld || downHeld;
		boolean horizontalInput = leftHeld || rightHeld;

		if(upHeld){
			if(!horizontalInput){
				playerY-=8;
			}else{
				playerY-= (int)(8 / sqrt2);
			}
		}
		if(downHeld){
			if(!horizontalInput){
				playerY+=8;
			}else{
				playerY+= (int)(8 / sqrt2);
			}
		}
		if(leftHeld){
			if(!verticalInput){
				playerX-=8;
			}else{
				playerX-= (int)(8 / sqrt2);
			}
		}
		if(rightHeld){
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

	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_UP:
			upHeld = true;
			break;
		case KeyEvent.VK_DOWN:
			downHeld = true;
			break;
		case KeyEvent.VK_LEFT:
			leftHeld = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightHeld = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_UP:
			upHeld = false;
			break;
		case KeyEvent.VK_DOWN:
			downHeld = false;
			break;
		case KeyEvent.VK_LEFT:
			leftHeld = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightHeld = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e){}
}
