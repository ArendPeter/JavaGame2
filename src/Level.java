import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
	char[][] level;

	public Level(char[][] level){
		this.level = level;
	}
	
	public Level(String fileName){
		try{
			Scanner scan = new Scanner( new FileInputStream(
				new File("levels/"+fileName+".txt")));
			int numRows = scan.nextInt();
			int numCols = scan.nextInt();
			scan.nextLine();
			level = new char[numRows][numCols];
			for(int i = 0; i < numRows; i++){
				String line = scan.nextLine();
				level[i] = line.toCharArray();
			}
			scan.close();
		}catch(FileNotFoundException e){
			System.out.println("Could not find level file");
		}
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
