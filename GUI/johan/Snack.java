package johan;

public class Snack extends Produkt {
    public Snack(String name, double price, int quantity) {
        super(name, price, quantity, 0.12);
    }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Snack";
	}

}
