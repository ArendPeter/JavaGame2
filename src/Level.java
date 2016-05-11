
public class Level {
	char[][] level;

	public Level(char[][] level){
		this.level = level;
	}
	
	public void load(){
		GameManager.getInstance().clearRoom();

		for(int i = 0; i < level.length; i++){
			for(int j = 0; j < level[i].length; j++){
				switch(level[i][j]){
				case 'P':	GameManager.getInstance().addObject(new Player(j*64,i*64)); break;
				case 'C':	GameManager.getInstance().addObject(new Coin(j*64,i*64)); break;
				case 'S':	GameManager.getInstance().addObject(new Solid(j*64,i*64)); break;
				case 'V':	GameManager.getInstance().addObject(new Enemy(j*64,i*64,false)); break;
				case 'H':	GameManager.getInstance().addObject(new Enemy(j*64,i*64,true)); break;
				case 'O':	GameManager.getInstance().addObject(new Portal(j*64,i*64,"Level2")); break;
				}
			}
		}
	}
}
