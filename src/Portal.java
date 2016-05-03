
public class Portal extends GameObject {

	String level;

	public Portal(int x, int y, String lvl){
		super(x,y);
		this.level = lvl;
	}

	protected void collideWith(GameObject object){
		if(object instanceof Player){
			GameManager.getInstance().goToLevel(level);
		}
	}
}
