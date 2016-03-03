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
		/*
		if(object instanceof Solid){
			boolean yDidOverlap = myTop-dy < sBot && sTop < myBot-dy;
			boolean horCollision = yDidOverlap;
			if(horCollision){
				if(myLeft < sLeft){//collision w/ left side of solid
					x = sLeft - width;
				}else{//collision w/ right side of solid
					x = sRight;
				}
				dx = -dx;
			}else{
				if(myTop < sTop){//collision w/ top of solid
					y = sTop - height;
				}else{//collision w/ bot of solid
					y = sBot;
				}
				dy = -dy;
			}
		}
		*/
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
	}
}
