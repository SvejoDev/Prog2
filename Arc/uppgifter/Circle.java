package uppgifter;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Ellipse {
 	private int diameter;
 	
 	/** Konstruktor */
 	public Circle(int diameter, int xPos, int yPos, Color color) {
    	   super(diameter, diameter, xPos, yPos, color); // anropar Shapes konstruktor
    	   this.diameter = diameter;
 	}
 
 	public void draw(Graphics2D g) {
    	   g.setColor(getColor());
    	   g.fillOval(getxPos(), getyPos(), diameter, diameter);
 	}
 
 	public int getArea() {
    	   int area = (int) (Math.PI*(diameter/2)*(diameter/2));
    	   return area;
 	}

      public int getOmk() {
    	   int omk = (int) (Math.PI*diameter); 
    	   return omk;
 	}
}

