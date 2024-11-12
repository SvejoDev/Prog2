package johan;

public class Snack extends Produkt {
    public Snack(String name, double price, int quantity) {
        super(name, price, quantity, 0.12); // 12% moms för snacks
    }

    @Override
    public String getType() {
        return "Snack";
    }
}