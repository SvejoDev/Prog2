package uppgifter;

import java.awt.Color;

import se.egy.graphics.GameScreen;

public class ShapeTest {

	public static void main(String[] args) {
		GameScreen gs = new GameScreen("Shapes", 800, 600, false);
    	
    	Shape[] shapeArray = new Shape[5];
    	
    	shapeArray[0] = new Rectangle(30, 50, 10, 10, Color.magenta);
    	shapeArray[1] = new Square(60, 80, 10, Color. green);
    	shapeArray[2] = new Circle(100, 100, 100, Color.yellow);
    	shapeArray[3] = new Rectangle(60, 150, 300, 10, Color.red);
    	shapeArray[4] = new Ellipse(250, 50, 300, 300, Color.blue);
		gs.render(shapeArray);
    	
    	for(int i = 0; i < shapeArray.length; i++) { 	
    		int area = shapeArray[i].getArea();
    		int omk = shapeArray[i].getOmk();
        	
    		System.out.println("Area = " + area);
    		System.out.println("Omkrets = " + omk);
    	}

	}

}
