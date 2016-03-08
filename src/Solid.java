import java.awt.Color;
import java.awt.Graphics;

public class Solid extends GameObject{
	
	public Solid(int x, int y){
		super(x,y,80,80);
	}
	
	public Solid(int x, int y, int width, int height){
		super(x,y,width,height);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}
}
