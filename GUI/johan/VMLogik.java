package johan;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VMLogik implements Serializable{
	private List<Produkt> products;
	private double balance;
	
	public VMLogik() {
		products = new ArrayList<>();
		balance = 0.0;
	}
	public void addProdukt(Produkt product) {
		((List<Produkt>) product).add(product);
	}
	public List<Produkt> getProducts(){
		return products;
	}
	
	public double getBalance() {
		return balance;
	}
	public void addMoney (double amount) {
		balance += amount;
	}
	
    public Produkt buyProduct(int index) {
        if (index < 0 || index >= products.size()) {
            return null;
        }

        Produkt product = products.get(index);
        if (product.getQuantity() > 0 && balance >= product.getPrice()) {
            product.setQuantity(product.getQuantity() - 1);
            balance -= product.getPrice();
            logPurchase(product);
            return product;
        }
        return null;
    }
    private void logPurchase(Produkt product) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String logEntry = String.format("%s - Purchased: %s, Price: %.2f kr, Tax: %.2f kr\n",
                now.format(formatter), product.getName(), product.getPrice(), product.getTaxAmount());
        
        System.out.println(logEntry);
    }
    public void restockProducts(List<Produkt> newProducts) {
    	for(Produkt newProduct : newProducts) {
    		boolean found = false;
    		for (Produkt existingProduct : products) {
                if (existingProduct.getName().equals(newProduct.getName())) {
                    existingProduct.setQuantity(existingProduct.getQuantity() + newProduct.getQuantity());
                    found = true;
                    break;
                }    	}
    }
    }}
