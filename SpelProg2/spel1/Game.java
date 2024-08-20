package spel1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import se.egy.graphics.*;

public class Game implements KeyListener{
    private HashMap<String, Boolean> keyDown = new HashMap<>();

	
    private ImgContainer player;


	private boolean gameRunning = true;

	private GameScreen gameScreen = new GameScreen("Game", 800, 600, false); // false vid testkörning

	public Game(){
		gameScreen.setKeyListener(this);
		
		//Y-led
		keyDown.put("up", false);
		keyDown.put("down", false);
		//X-led
		keyDown.put("left", false);
		keyDown.put("right", false);
		
		loadImages();
		gameLoop();
	}

	public void loadImages(){
		player = new ImgContainer(384, 284, "/playerImg.png");

	}

	public void update(){
		 if(keyDown.get("right"))
		        player.setX(player.getX() + 5);
		    if(keyDown.get("left"))
			player.setX(player.getX() - 5);
		    if(keyDown.get("up"))
		        player.setY(player.getY() - 5);
		    if(keyDown.get("down"))
		        player.setY(player.getY() + 5);
		    
	}

	public void render(){
	    gameScreen.render(player);

	}

	public void gameLoop(){
		while(gameRunning){
			update();
			render();
			// Fördröjning
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
	}

	/** Spelets tangentbordslyssnare */
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

	     if(key == KeyEvent.VK_LEFT)
	        keyDown.put("left", true);
	     else if(key == KeyEvent.VK_RIGHT)
	        keyDown.put("right", true);
	     if(key == KeyEvent.VK_UP)
		        keyDown.put("up", true);
		  else if(key == KeyEvent.VK_DOWN)
		        keyDown.put("down", true);
		    	
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

	     if(key == KeyEvent.VK_LEFT)
	        keyDown.put("left", false);
	     else if(key == KeyEvent.VK_RIGHT)
	        keyDown.put("right", false);
	     if(key == KeyEvent.VK_UP)
		        keyDown.put("up", false);
		     else if(key == KeyEvent.VK_DOWN)
		        keyDown.put("down", false);
	     
	     
	}

	public static void main(String[] args) {
		new Game();
	}
}
