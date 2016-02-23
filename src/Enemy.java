import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
		move();
	}
	
	private void checkCollisions(){
		int myLeft = x + dx;
		int myRight = myLeft + width;
		int myTop = y + dy;
		int myBot = myTop + height;
		
		ArrayList<GameObject> objects = GamePanel.getInstance().getObjects();
		for(GameObject object : objects){
			int sLeft = object.getX();
			int sRight = sLeft + object.getWidth();
			int sTop = object.getY();
			int sBot = sTop + object.getHeight();
			
			boolean xOverlaps = myLeft < sRight && sLeft < myRight;
			boolean yOverlaps = myTop < sBot && sTop < myBot;
			if(xOverlaps && yOverlaps){
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
			}
		}
	}
	
	private void move(){
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x,y,width,height);
	}

}
