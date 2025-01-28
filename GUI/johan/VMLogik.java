package johan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VMLogik implements Serializable {
    // Instansvariabler
    private List<Produkt> products;      // Lista med alla produkter i automaten
    private String csvfilepath;          // Sökväg till CSV-filen

    // Konstruktor - skapar en tom produktlista
    public VMLogik() {
        products = new ArrayList<>();
    }
    
    // Sätter sökvägen till CSV-filen
    public void setCsvFilePath(String path) {
        this.csvfilepath = path;
    }
    
    // Lägger till en ny produkt i automaten
    public void addProdukt(Produkt product) {
        if (product != null) {
            products.add(product);
        }
    }
    
    // Hämtar alla produkter
    public List<Produkt> getProducts() {
        return products;
    }
    
    // Hanterar köp av en produkt
    public Produkt buyProduct(int index) {
        // Kontrollerar att index är giltigt
        if (index < 0 || index >= products.size()) {
            return null;
        }

        Produkt product = products.get(index);
        // Kontrollerar att produkten finns i lager
        if (product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            logPurchase(product);  // Loggar köpet
            return product;
        }
        return null;
    }

    // Loggar ett köp via filehandler
    private void logPurchase(Produkt product) {
        filehandler.logPurchase(product);
    }
}