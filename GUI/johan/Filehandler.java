package johan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Filehandler {
    private static final String SAVE_FILE = "vending_machine_state.ser";
    private static final String LOG_FILE = "purchase_log.txt";
    
    // Sparar automaten status
    public static void saveState(VMLogik logic) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(logic);
            System.out.println("Varuautomat status sparad");
        } catch (IOException e) {
            System.err.println("Fel med att spara varuautomat status: " + e.getMessage());
        }
    }
    
    // Laddar automaten status
    public static VMLogik loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            VMLogik logic = (VMLogik) ois.readObject();
            System.out.println("Varuautomat data laddat lyckat");
            return logic;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ingen sparad data hittades eller fel vid laddning. Skapar ny varuautomat.");
            return new VMLogik();
        }
    }
    
    // Läser in produkter från CSV-fil
    public static List<Produkt> loadProductsFromCSV(String filename) {
        List<Produkt> products = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Läs första raden (header) för att identifiera kolumner
            String headerLine = br.readLine();
            if (headerLine == null) {
                System.err.println("CSV-filen är tom");
                return products;
            }
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    try {
                        // Skapa produkt baserat på rad-data
                        Produkt product = createProductFromCSVLine(data);
                        if (product != null) {
                            products.add(product);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Fel vid parsning av data: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Fel vid läsning av CSV-fil: " + e.getMessage());
        }
        return products;
    }
    
    // Hjälpmetod för att skapa produkter från CSV-data
    private static Produkt createProductFromCSVLine(String[] data) {
        try {
            String productInfo = data[0].trim();
            int quantity = 0;
            
            // Extrahera antal från andra kolumnen
            if (data.length > 1) {
                String quantityStr = data[1].replaceAll("[^0-9]", "");
                quantity = Integer.parseInt(quantityStr);
            }
            
            // Extrahera pris och namn
            String[] parts = productInfo.split(" ");
            String name = parts[0];
            double price = Double.parseDouble(parts[1].replaceAll("[^0-9.]", ""));
            
            // Skapa rätt typ av produkt baserat på produktinformationen
            if (productInfo.toLowerCase().contains("dricka")) {
                return new Drink(name, price, quantity);
            } else if (productInfo.toLowerCase().contains("snacks")) {
                return new Snack(name, price, quantity);
            } else if (productInfo.toLowerCase().contains("bok")) {
                return new Pocketbok(name, price, quantity);
            }
        } catch (Exception e) {
            System.err.println("Fel vid skapande av produkt från CSV-data: " + e.getMessage());
        }
        return null;
    }
    
    // Loggar köp till fil
    public static void logPurchase(Produkt product) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String logEntry = String.format("%s - Köpt: %s, Pris: %.2f kr, Moms: %.2f kr%n",
                    java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    product.getName(),
                    product.getPrice(),
                    product.getTaxAmount());
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Fel vid loggning av köp: " + e.getMessage());
        }
    }
    
    // Hjälpmetod för att läsa in testdata
    public static void loadInitialTestData(VMLogik logic) {
        // Lägg till några testprodukter om ingen data finns
        if (logic.getProducts().isEmpty()) {
            logic.addProdukt(new Drink("Cola", 20.0, 10));
            logic.addProdukt(new Snack("Chips", 10.0, 10));
            logic.addProdukt(new Pocketbok("Sea of Tranquility", 50.0, 5));
        }
    }
}