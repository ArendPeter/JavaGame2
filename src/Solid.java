import java.awt.Color;
import java.awt.Graphics;

public class Solid {
	private int x, y;
	private int width, height;
	
	public Solid(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 80;
		this.height = 80;
	}
	
	public Solid(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}

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
