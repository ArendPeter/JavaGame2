import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject{
	
	private int startX, startY;
	private int points=0;
	
	private float frame = 0;
	private int numFrames = 10;
	private float frameSpeed = .20f;
	
	public void gainPoint(){
		points++;
	}
	
	public Player(){
		this(10,10);
	}
	
	public Player(int x, int y){
		super(x,y,64,64,"player_anim");
		startX = x;
		startY = y;
	}
	
	public void gameLoop(){
		getInputMovement(); 
		checkCollisions(); 
		applyVelocity();
		updateFrame();
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
	
	private void updateFrame(){
		if(dx < 0 || (dx == 0 && dy != 0)){
			frame += frameSpeed;
			if(frame >= numFrames){
				frame -= numFrames;
			}
		}
		if(dx > 0 ){
			frame -= frameSpeed;
			if(frame < 0){
				frame += numFrames;
			}
		}
	}
	
	public void draw(Graphics g){
		//super.draw(g);
		System.out.println(frame);
		g.drawImage(Resources.getInstance().getImage(imgName), 
				(int)bounds.getMinX(),(int)bounds.getMinY(),
				(int)bounds.getMaxX(),(int)bounds.getMaxY(),
				(int)frame * bounds.width, 0,
				(int)(frame + 1) * bounds.width, bounds.height, 
				null);

		g.setColor(Color.BLACK);
		Font font = new Font(null, Font.BOLD, 18);
		g.setFont(font);
		g.drawString(points+"", 10,20);
	}
}
