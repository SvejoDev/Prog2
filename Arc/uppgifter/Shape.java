package uppgifter;

import java.awt.Color;
import java.awt.Graphics2D;

import se.egy.graphics.Drawable;

public abstract class Shape implements Drawable{
 	protected int xPos, yPos;
 	protected Color color;
 	
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

