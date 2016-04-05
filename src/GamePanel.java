import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private HashMap<Integer,GameObject> objects;
	private ArrayList<GameObject> objectsToRemove;

	char[][] level = {
		{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
		{'-', 'P', '-', '-', '-', '-', '-', 'S', '-', '-', '-', '-', '-'},
		{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
		{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
		{'-', '-', '-', 'C', '-', '-', '-', 'V', '-', '-', '-', '-', '-'},
		{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
		{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
		{'-', 'S', '-', 'H', '-', '-', '-', 'S', '-', '-', '-', '-', '-'},
		{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
		{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}
	};

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

		GamePanel panel = GamePanel.getInstance(); 

		frame.add(panel);

		frame.setVisible(true);
	}

	private GamePanel(){
		instance = this;

		objectsToRemove = new ArrayList<GameObject>();

		objects = new HashMap<Integer,GameObject>();
		
		initLevel();

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
	
	private void initLevel(){
		for(int i = 0; i < level.length; i++){
			for(int j = 0; j < level[i].length; j++){
				switch(level[i][j]){
				case 'P':	addObject(new Player(j*64,i*64)); break;
				}
			}
		}
		/*
		addObject(new Player(50,50));
		
		addObject(new Solid(400,400,64,64));
		addObject(new Solid(100,400,64,64));
		addObject(new Solid(400,100,64,64));
		
		addObject(new Enemy(200,400));
		addObject(new Enemy(400,200,false));
		
		addObject(new Coin(200,400));
		addObject(new Coin(400,200));
		*/
	}
	
	private void gameLoop(){
		for(GameObject object : objects.values()){
			object.gameLoop();
		}
		for(GameObject object : objectsToRemove){
			objects.remove(object.getId());
		}
		objectsToRemove.clear();
	}

	@Override
	protected void paintComponent(Graphics p) {
		p.setColor(Color.WHITE);
		p.fillRect(0, 0, getWidth(), getHeight());
				
		for(GameObject object : objects.values()){
			object.draw(p);
		}
	}

	public HashMap<Integer,GameObject> getObjects(){
		return objects;
	}
	
	private void addObject(GameObject obj){
		objects.put(obj.getId(),obj);
	}
	
	public void removeObject(GameObject obj){
		objectsToRemove.add(obj);
	}
}
