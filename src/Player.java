import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private int startX, startY;
	private int points=0;
	
	public void gainPoint(){
		points++;
	}
	
	public Player(){
		this(10,10);
	}
	
	public Player(int x, int y){
		super(x,y,50,50);
		startX = x;
		startY = y;
	}
	
	public void gameLoop(){
		getInputMovement(); 
		checkCollisions(); 
		applyVelocity();
	}
	
	private void getInputMovement(){
		double sqrt2 = Math.sqrt(2);
		boolean verticalInput = KeyboardController.isUpHeld() || KeyboardController.isDownHeld();
		boolean horizontalInput = KeyboardController.isLeftHeld() || KeyboardController.isRightHeld();
		
		dx = 0;
		dy = 0;
		
		if(KeyboardController.isUpHeld()){
			if(!horizontalInput){
				dy=-8;
			}else{
				dy=-(int)(8 / sqrt2);
			}
		}
		if(KeyboardController.isDownHeld()){
			if(!horizontalInput){
				dy=8;
				
			}else{
				dy=(int)(8 / sqrt2);
			}
		}
		if(KeyboardController.isLeftHeld()){
			if(!verticalInput){
				dx=-8;
			}else{
				dx=-(int)(8 / sqrt2);
			}
		}
		if(KeyboardController.isRightHeld()){
			if(!verticalInput){
				dx=8;
			}else{
				dx= (int)(8 / sqrt2);
			}
		}
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
				dx = 0;
			}else{
				if(bounds.getMinY() < oBounds.getMinY()){//collision w/ top of solid
					bounds.y = (int)oBounds.getMinY() - bounds.height;
				}else{//collision w/ bot of solid
					bounds.y = (int)oBounds.getMaxY();
				}
				dy = 0;
			}
		}
		if(object instanceof Enemy){
			bounds.setLocation(startX, startY);
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);

		g.setColor(Color.WHITE);
		Font font = new Font(null, Font.BOLD, 18);
		g.setFont(font);
		g.drawString(points+"", (int)bounds.getCenterX(), (int)bounds.getCenterY());
	}
}
