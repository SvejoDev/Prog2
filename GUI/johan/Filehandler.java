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
        System.out.println("Försöker läsa CSV-fil: " + filename);
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Skippa första raden (header)
            br.readLine();
            
            // Läs produktrader
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 3) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    // Lägg till dryck
                    String drinkName = data[0].trim();
                    products.add(new Drink(drinkName, 20.0, 10));
                    
                    // Lägg till snacks
                    String snackName = data[1].trim();
                    products.add(new Snack(snackName, 10.0, 10));
                    
                    // Lägg till bok (ta bort citattecken)
                    String bookName = data[2].replaceAll("\"", "").trim();
                    String[] bookParts = bookName.split(" (?=\\d)"); // Dela vid siffror (för att hantera "5 st")
                    bookName = bookParts[0].trim();
                    products.add(new Pocketbok(bookName, 50.0, 5));
                }
                row++;
            }
        } catch (IOException e) {
            System.err.println("Fel vid läsning av CSV-fil: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("Antal inlästa produkter: " + products.size());
        return products;
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
}