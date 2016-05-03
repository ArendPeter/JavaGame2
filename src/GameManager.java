import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
	private GamePanel panel;
	
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
	private static GameManager instance;
	
	public static GameManager getInstance(){
		return instance;
	}
	//////////
	
	public GameManager(GamePanel panel){
		instance = this;

		this.panel = panel;

		objectsToRemove = new ArrayList<GameObject>();
		objects = new HashMap<Integer,GameObject>();
		
		initLevel();
		
		new Thread(){
			public void run(){
				try{
					while(true){
						gameLoop();
						panel.repaint();
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
				case 'C':	addObject(new Coin(j*64,i*64)); break;
				case 'S':	addObject(new Solid(j*64,i*64)); break;
				case 'V':	addObject(new Enemy(j*64,i*64,false)); break;
				case 'H':	addObject(new Enemy(j*64,i*64,true)); break;
				}
			}
		}
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
	
	public void draw(Graphics p){
		p.setColor(Color.WHITE);
		p.fillRect(0, 0, panel.getWidth(), panel.getHeight());
				
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
