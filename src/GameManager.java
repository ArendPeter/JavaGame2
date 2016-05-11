import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
	private GamePanel panel;
	
	private HashMap<Integer,GameObject> objects;
	private ArrayList<GameObject> objectsToRemove;
	private ArrayList<GameObject> objectsToAdd;

	private HashMap<String,Level> levels;

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
		objectsToAdd = new ArrayList<GameObject>();
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
	
	public void initLevel(){
		levels = new HashMap<String, Level>();

		char[][] lvlData = {
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', 'P', '-', '-', '-', '-', '-', 'S', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', '-', '-', 'C', '-', '-', '-', 'V', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', 'S', '-', 'H', '-', '-', '-', 'S', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'O', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}
		};
		levels.put("Level1",new Level(lvlData));

		levels.put("Level2", new Level("level2"));

		levels.get("Level1").load();
	}
	
	private void gameLoop(){
		for(GameObject object : objects.values()){
			object.gameLoop();
		}
		for(GameObject object : objectsToRemove){
			objects.remove(object.getId());
		}
		objectsToRemove.clear();
		for(GameObject object : objectsToAdd){
			objects.put(object.getId(),object);
		}
		objectsToAdd.clear();
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
	
	public void addObject(GameObject obj){
		objectsToAdd.add(obj);
	}
	
	public void removeObject(GameObject obj){
		objectsToRemove.add(obj);
	}
	
	public void clearRoom(){
		for(GameObject object : objects.values()){
			objectsToRemove.add(object);
		}
	}
	
	public void goToLevel(String lvl){
		levels.get(lvl).load();
	}
}
