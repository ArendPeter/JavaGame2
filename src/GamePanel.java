import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private ArrayList<GameObject> objects;
 
	//singleton
	private static GamePanel instance;
	
	public static GamePanel getInstance(){
		if(instance == null){
			instance = new GamePanel();
		}
		return instance;
	}
	//////////
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		GamePanel panel = new GamePanel();
		instance = panel;

		frame.add(panel);

		frame.setVisible(true);
	}

	private GamePanel(){
		objects = new ArrayList<GameObject>();

		objects.add(new Player(50,50));
		
		objects.add(new Solid(400,400,64,64));
		objects.add(new Solid(100,400,64,64));
		objects.add(new Solid(400,100,64,64));
		
		objects.add(new Enemy(200,400));
		objects.add(new Enemy(400,200,false));
		
		objects.add(new Coin(200,400));
		objects.add(new Coin(400,200));
		
		this.addKeyListener(KeyboardController.getInstance());
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
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).gameLoop();
		}
	}

	@Override
	protected void paintComponent(Graphics p) {
		p.setColor(Color.WHITE);
		p.fillRect(0, 0, getWidth(), getHeight());
				
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).draw(p);
		}
	}

	public ArrayList<GameObject> getObjects(){
		return objects;
	}
	
}
