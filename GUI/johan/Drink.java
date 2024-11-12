package johan;

public class Drink extends Produkt {
    public Drink(String name, double price, int quantity) {
        super(name, price, quantity, 0.12); // 12% moms för drycker
    }

    @Override
    public String getType() {
        return "Dryck";
    }
}