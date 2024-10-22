package johan;

public class Drink extends Produkt {
    public Drink(String name, double price, int quantity) {
        super(name, price, quantity, 0.12); // 12% tax rate for drinks
    }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Drink";
	}
}