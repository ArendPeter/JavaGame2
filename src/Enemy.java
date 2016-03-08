import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	
	public Enemy(int xx, int yy){
		super(xx,yy,48,48);
		dx = 4;
		dy = 0;
	}
	
	public Enemy(int x, int y, boolean horizontal){
		super(x,y,48,48);
		dx = (horizontal)? 4 : 0;
		dy = (horizontal)? 0 : 4;
	}
	
	public void gameLoop(){
		checkCollisions();
		applyVelocity();
	}
	
	protected void collideWith(GameObject object){
		if(object instanceof Solid){
			Rectangle oBounds = object.getBounds();
			boolean yDidOverlap = bounds.getMinY() < oBounds.getMaxY() 
					&& oBounds.getMinY() < bounds.getMaxY();
			boolean horCollision = yDidOverlap;
			if(horCollision){
				if(bounds.getMinX() < oBounds.getMinX()){//collision w/ left side of solid
					bounds.x = (int)oBounds.getMinX() - bounds.width;
				}else{//collision w/ right side of solid
					bounds.x = (int)oBounds.getMaxX();
				}
				dx = -dx;
			}else{
				if(bounds.getMinY() < oBounds.getMinY()){//collision w/ top of solid
					bounds.y = (int)oBounds.getMinY() - bounds.height;
				}else{//collision w/ bot of solid
					bounds.y = (int)oBounds.getMaxY();
				}
				dy = -dy;
			}
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
	}
}
