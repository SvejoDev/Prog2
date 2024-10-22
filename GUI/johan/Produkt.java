package johan;

import java.io.Serializable;

public abstract class Produkt implements Serializable {
    private String name;
    private double price;
    private int quantity;
    private double taxRate;

    public Produkt(String name, double price, int quantity, double taxRate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.taxRate = taxRate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTaxAmount() {
        return price * taxRate;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("%s - %.2f kr", name, price);
    }
}