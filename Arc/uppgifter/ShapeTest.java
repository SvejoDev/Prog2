package uppgifter;

import java.awt.Color;

import se.egy.graphics.GameScreen;

public class ShapeTest {

	public static void main(String[] args) {
		Shape s = new Rectangle(300, 600, 100, 100, Color.cyan);
		GameScreen gs=new GameScreen("ShapeTest", 800, 600);
		gs.render(s);
	}

}
