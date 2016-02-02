import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int x, y;
	private int dx, dy;
	private int width, height;
	
	public Player(){
		this(10,10);
	}
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
	}
	
	public void gameLoop(){
		getInputMovement();
		checkCollisions();
		applyMovement();
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
	
	private void checkCollisions(){
		Solid[] solids = GamePanel.getInstance().getSolids();
		int myLeft = x + dx;
		int myRight = myLeft + width;
		int myTop = y + dy;
		int myBot = myTop + height;
	}
	
	private void applyMovement(){
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillRect(x,y,width,height);
	}
	
	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}
}
