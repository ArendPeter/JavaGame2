import java.awt.Graphics;

public class GameObject {
	protected int x, y, width, height, dx, dy;
	
	public GameObject(int x, int y){
		this.x = x;
		this.y = y;
		width = 64;
		height = 64;
		dx = 0;
		dy = 0;
	}
	
	public GameObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		dx = 0;
		dy = 0;
	}
	
	public void gameLoop(){}
	
	public void draw(Graphics g){}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
