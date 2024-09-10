package uppgifter;

import java.awt.Color;
import java.awt.Graphics2D;

import se.egy.graphics.Drawable;

public abstract class Shape implements Drawable{
 	private int xPos, yPos;
 	private Color color;
 	
 	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
 	 * Konstruktor, kan inte användas för att skapa ett Shapeobjekt.
 	 * Kan däremot anropas med super från klasser som ärver.
 	 */
 	public Shape(int xPos, int yPos, Color color){
      	this.xPos = xPos;
      	this.yPos = yPos;
      	this.color = color;
 	}
 	
 	public abstract void draw(Graphics2D g);
 	
 	public abstract int getArea();
      public abstract int getOmk();
}

