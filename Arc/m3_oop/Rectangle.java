package m3_oop;

import java.awt.Color;
import java.awt.Graphics2D;

import se.egy.graphics.*;

public class Rectangle implements Drawable {
	private int width;
	private int height;
	private int xPos, yPos;
	private Color color;

	/**
	 * Konstruktor
	 */
	public Rectangle(int width, int height, int xPos, int yPos, Color color){
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = color;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}

	public int getArea(){
		int area = width*height;
		return area;
	}

	public int getPerimeter(){
		int perimeter = 2*width + 2*height;
		return perimeter;
	}
	
	public void setX(int xPos) {
		this.xPos = xPos;
	}
	
	public void setY(int yPos) {
		this.yPos = yPos;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(xPos, yPos, width, height);
	}
}
