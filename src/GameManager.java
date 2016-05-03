import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
	private GamePanel panel;
	
	private HashMap<Integer,GameObject> objects;
	private ArrayList<GameObject> objectsToRemove;

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
		char[][] lvlData = {
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
		levels.put("Level1",new Level(lvlData));

		char[][] lvlData2 = {
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', 'S', '-', 'H', '-', '-', '-', 'S', '-', '-', '-', '-', '-'},
			{'-', 'C', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', 'H', '-', 'C', '-', '-', '-', 'V', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', 'C', '-', '-', '-', '-', '-'},
			{'-', 'S', '-', 'H', '-', '-', '-', 'S', '-', 'P', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
			{'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}
		};
		levels.put("Level2", new Level(lvlData2));

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
		objects.put(obj.getId(),obj);
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
