package johan;

public class Pocketbok extends Produkt {
    public Pocketbok(String name, double price, int quantity) {
        super(name, price, quantity, 0.12);
    }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Pocketbok";
	}

}
