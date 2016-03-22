import java.awt.Color;
import java.awt.Graphics;

public class Coin extends GameObject {

	public Coin(int x, int y){
		super(x,y);
	}
	
	public void gameLoop(){
		checkCollisions();
	}
	
	
	protected void collideWith(GameObject object){
		if(object instanceof Player){
			Player p = (Player)object;
			p.gainPoint();
			delete();
		}
	}
	
	
	public void draw(Graphics h){
		h.setColor(Color.yellow);
		h.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);	
	}
	
}
