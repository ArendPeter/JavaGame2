import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends GameObject{
	
	private int startX, startY;
	
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
				dx = 0;
			}else{
				if(myTop < sTop){//collision w/ top of solid
					y = sTop - height;
				}else{//collision w/ bot of solid
					y = sBot;
				}
				dy = 0;
			}
		}
		*/
		if(object instanceof Enemy){
			bounds.setLocation(startX, startY);
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
	}
}
