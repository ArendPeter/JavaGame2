import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int x, y;
	private int dx, dy;
	private int width, height;
	
	private int startX, startY;
	
	public Player(){
		this(10,10);
	}
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
		startX = x;
		startY = y;
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
		int myLeft = x + dx;
		int myRight = myLeft + width;
		int myTop = y + dy;
		int myBot = myTop + height;
		
		Solid[] solids = GamePanel.getInstance().getSolids();
		for(Solid solid : solids){
			int sLeft = solid.getX();
			int sRight = sLeft + solid.getWidth();
			int sTop = solid.getY();
			int sBot = sTop + solid.getHeight();
			
			boolean xOverlaps = myLeft < sRight && sLeft < myRight;
			boolean yOverlaps = myTop < sBot && sTop < myBot;
			if(xOverlaps && yOverlaps){
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
		}

		Enemy[] enemies = GamePanel.getInstance().getEnemies();
		for(Enemy enemy : enemies){
			int sLeft = enemy.getX();
			int sRight = sLeft + enemy.getWidth();
			int sTop = enemy.getY();
			int sBot = sTop + enemy.getHeight();
			
			boolean xOverlaps = myLeft < sRight && sLeft < myRight;
			boolean yOverlaps = myTop < sBot && sTop < myBot;
			if(xOverlaps && yOverlaps){
				x = startX;
				y = startY;
			}
		}
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
