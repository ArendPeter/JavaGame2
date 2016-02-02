import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
 
	private Player player;
	private Solid[] solids;
	
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
		player = new Player(50,50);
		
		solids = new Solid[5];
		solids[0] = new Solid(400,25,50,50);
		solids[1] = new Solid(50,200,60,70);
		solids[2] = new Solid(100,100);
		solids[3] = new Solid(200,300);
		solids[4] = new Solid(400,200);
		
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
		player.gameLoop();
	}

	@Override
	protected void paintComponent(Graphics panelG) {
		panelG.setColor(Color.WHITE);
		panelG.fillRect(0, 0, getWidth(), getHeight());
			
		player.draw(panelG);
		
		for(int i=0; i<solids.length; i++){
			solids[i].draw(panelG);
		}
	}

	public Solid[] getSolids() {
		return solids;
	}
	
}
