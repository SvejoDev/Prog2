package johan;

public class Pocketbok extends Produkt {
    public Pocketbok(String name, double price, int quantity) {
        super(name, price, quantity, 0.06); // 6% moms för böcker
    }

    @Override
    public String getType() {
        return "Bok";
    }
}