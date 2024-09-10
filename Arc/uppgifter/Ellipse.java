package uppgifter;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Shape{
	private int width;
	private int height;

	public Ellipse(int height, int width, int xPos, int yPos, Color color) {
		super(xPos, yPos, color);
		this.width = width;
		this.height = height;
		
	}

	@Override
	public void draw(Graphics2D g) {
	   g.setColor(getColor());
  	   g.fillOval(getxPos(), getyPos(), width, height);
		
	}

	@Override
	public int getArea() {
		int area = (int) (Math.PI * width * height);
		return area;
	}

	@Override
	public int getOmk() {
		int omk = (int) (Math.PI * Math.sqrt(2* (Math.pow(width, 2) + 2 * Math.pow(height, 2))));
		return omk;
	}

}
