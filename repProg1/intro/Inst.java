package intro;

public class Inst {
	///instansvariabler
	private double l;
	private double w;
	private String shape;
	
	///kontruktor
	public Inst(String shape, double l, double w) {
		this.shape = shape;
		this.l = l;
		this.w = w;
	}
	public double omkr() {
		return 2*l + 2*w;
	}
	public double area() {
		return l * w;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public String getShape() {
		return shape;
	}
	

}
