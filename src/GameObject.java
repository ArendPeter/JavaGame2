import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

public class GameObject {
	protected Rectangle bounds;
	protected int dx, dy;
	protected int id;

	private static int next_id = 0;
	
	public GameObject(int x, int y){
		this(x,y,64,64);
	}
	
	public GameObject(int x, int y, int width, int height){
		bounds = new Rectangle(x,y,width,height);
		dx = 0;
		dy = 0;
		id = getNewID();
	}

	private static int getNewID(){
		return next_id++;
	}
	
	public void gameLoop(){}
	
	public void draw(Graphics g){}
	
	protected void checkCollisions(){
		HashMap<Integer,GameObject> objects = GamePanel.getInstance().getObjects();

		Rectangle newBounds = new Rectangle(bounds);
		newBounds.translate(dx, dy);
		
		for(GameObject object : objects.values()){
			Rectangle oBounds = object.getBounds();
			
			if(newBounds.intersects(oBounds)){
				collideWith(object);
			}
		}
	}
	
	protected void collideWith(GameObject object){}
	
	protected void applyVelocity(){
		bounds.translate(dx, dy);
	}
	
	protected void delete(){
		GamePanel.getInstance().removeObject(this);
	}
	
	public int getX(){
		return bounds.x;
	}

	public int getY(){
		return bounds.y;
	}

	public int getWidth(){
		return bounds.width;
	}

	public int getHeight(){
		return bounds.height;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}

	public int getId() {
		return id;
	}
}
