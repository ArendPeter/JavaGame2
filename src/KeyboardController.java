import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener{
	private boolean upHeld = false;
	private boolean downHeld = false;
	private boolean rightHeld = false;
	private boolean leftHeld = false;
	
	//singleton
	private static KeyboardController instance;
	
	public static KeyboardController getInstance(){
		if(instance == null){
			instance = new KeyboardController();
		}
		return instance;
	}
	//end-singleton
	
	private KeyboardController(){}
	
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_UP:
			upHeld = true;
			break;
		case KeyEvent.VK_DOWN:
			downHeld = true;
			break;
		case KeyEvent.VK_LEFT:
			leftHeld = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightHeld = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_UP:
			upHeld = false;
			break;
		case KeyEvent.VK_DOWN:
			downHeld = false;
			break;
		case KeyEvent.VK_LEFT:
			leftHeld = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightHeld = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e){}
	
	public static boolean isUpHeld(){
		return getInstance().upHeld;
	}

	public static boolean isDownHeld() {
		return getInstance().downHeld;
	}

	public static boolean isRightHeld() {
		return getInstance().rightHeld;
	}

	public static boolean isLeftHeld() {
		return getInstance().leftHeld;
	}
}
