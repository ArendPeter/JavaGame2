import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Resources {
	private HashMap<String, BufferedImage> images;

	//singleton
	private static Resources instance;
	
	public static Resources getInstance(){
		if(instance == null){
			instance = new Resources();
		}
		return instance;
	}
	
	private Resources(){
		images = new HashMap<String,BufferedImage>();
		images.put("", new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB));
	}
	
	public BufferedImage getImage(String imgName){
		if(!images.containsKey(imgName)){
			images.put(imgName, loadImage(imgName));
		}
		return images.get(imgName);
	}
	
	private BufferedImage loadImage(String imgName){
		BufferedImage img;
		try{
			img = ImageIO.read(new File("img/"+imgName+".png"));
		}catch(IOException e){
			img = images.get("");
			e.printStackTrace();
		}
		return img;
	}
}
