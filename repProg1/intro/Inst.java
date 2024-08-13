package intro;

public class Inst {
    private double l;
    private double w;
    private String shape;

    // Konstruktor 1
    public Inst(String shape, double l, double w) {
        this.shape = shape;
        this.l = l;
        this.w = w;
    }

    // Konstruktor 2
    public Inst(String shape, double s) {
        this.shape = shape;
        this.l = s;
    }

    // Metod för att beräkna omkrets (omkr)
    public double omkr() {
        return 2 * (l + w);
    }

	public String getShape() {
		return shape;
	}
}
