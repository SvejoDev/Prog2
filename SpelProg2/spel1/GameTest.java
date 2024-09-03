package spel1;

import se.egy.graphics.*; // Spelbiblioteket importeras


public class GameTest {
	 
	public static void main(String[] args) {
    	// Skapar ett spelfönster
    	GameScreen gameScreen = new GameScreen("Test", 800, 600, false);
 
    	// Skapar och laddar in en bild med en x,y-koordinat.
    	ImgContainer ic = new ImgContainer(200, 100, "playerImg.png");
 
    	for(int i = 0; i < 100; i++) {
        	gameScreen.render(ic); // renderar bilden
 
        	try{ Thread.sleep(20);}catch(Exception e){}; // pausar i 20 ms
 
        	// Förflyttar bilden genom att öka x,y - koordinaterna med 5
        	int newX = ic.getX() + 4;
        	int newY = ic.getY() + 4;
 
        	ic.setX(newX);
        	ic.setY(newY);
    	}  
    	System.exit(0);
	}
}

