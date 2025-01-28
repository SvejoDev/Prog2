package johan;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Klass som hanterar all filhantering för varuautomaten:
 * - Sparar och laddar automatens tillstånd
 * - Läser produktdata från CSV
 * - Loggar alla köp
 */
public class filehandler {
    // Konstanta sökvägar till filer
    private static final String SAVE_FILE = "vending_machine_state.ser";    // Fil för sparat tillstånd
    private static final String LOG_FILE = "purchase_log.txt";              // Fil för köploggar
    
    /**
     * Sparar automatens nuvarande tillstånd till en fil
     * Detta körs automatiskt när programmet stängs
     */
    public static void saveState(VMLogik logic) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(logic);
            System.out.println("Sparade automatens tillstånd");
        } catch (IOException e) {
            System.err.println("Kunde inte spara tillstånd: " + e.getMessage());
        }
    }
    
    /**
     * Laddar automatens tidigare sparade tillstånd
     * Om ingen sparad fil hittas skapas en ny automat
     */
    public static VMLogik loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            VMLogik logic = (VMLogik) ois.readObject();
            System.out.println("Laddade sparat tillstånd");
            return logic;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Inget sparat tillstånd hittades, skapar ny automat");
            return new VMLogik();
        }
    }
    
    /**
     * Läser in produktdata från CSV-filen
     * CSV-filen har formatet:
     * Rad 1: Rubriker (hoppas över)
     * Rad 2-4: [Dryck],[Snacks],[Bok]
     */
    public static List<Produkt> loadProductsFromCSV(String filename) {
        List<Produkt> products = new ArrayList<>();
        System.out.println("Läser produkter från: " + filename);
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Hoppar över första raden (rubriker)
            br.readLine(); 
            
            String line;
            int row = 0;
            // Läser max 3 rader med produktdata
            while ((line = br.readLine()) != null && row < 3) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    // Läser in dryck
                    String drinkName = data[0].trim();
                    int drinkQuantity = extractQuantity(drinkName);
                    String cleanDrinkName = cleanProductName(drinkName);
                    products.add(new Drink(cleanDrinkName, 20.0, drinkQuantity));
                    
                    // Läser in snacks
                    String snackName = data[1].trim();
                    int snackQuantity = extractQuantity(snackName);
                    String cleanSnackName = cleanProductName(snackName);
                    products.add(new Snack(cleanSnackName, 10.0, snackQuantity));
                    
                    // Läser in bok (tar bort citattecken)
                    String bookName = data[2].replaceAll("\"", "").trim();
                    int bookQuantity = extractQuantity(bookName);
                    String cleanBookName = cleanProductName(bookName);
                    products.add(new Pocketbok(cleanBookName, 50.0, bookQuantity));
                }
                row++;
            }
            System.out.println("Läste in " + products.size() + " produkter");
            
        } catch (IOException e) {
            System.err.println("Fel vid läsning av CSV-fil: " + e.getMessage());
        }
        
        return products;
    }
    
    /**
     * Hittar antal produkter från produktnamnet
     * Exempel: "Cola 10 st" ger 10
     */
    private static int extractQuantity(String productName) {
        try {
            int stIndex = productName.indexOf(" st");
            if (stIndex > 0) {
                String[] parts = productName.substring(0, stIndex).split(" ");
                return Integer.parseInt(parts[parts.length - 1]);
            }
        } catch (Exception e) {
            System.err.println("Kunde inte hitta antal i: " + productName);
        }
        return 0;  // Returnerar 0 om inget antal hittas
    }
    
    /**
     * Tar bort antalsinformation från produktnamnet
     * Exempel: "Cola 10 st" blir "Cola"
     */
    private static String cleanProductName(String productName) {
        int lastSpaceIndex = productName.lastIndexOf(" ");
        if (lastSpaceIndex > 0 && productName.endsWith(" st")) {
            return productName.substring(0, productName.lastIndexOf(" ", lastSpaceIndex - 1));
        }
        return productName;
    }
    
    /**
     * Loggar ett köp med datum, tid och momsinformation
     * Format: [datum tid] - Köpt: [produkt], Pris: [pris] kr, Moms: [moms] kr
     */
    public static void logPurchase(Produkt product) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String logEntry = String.format("%s - Köpt: %s, Pris: %.2f kr, Moms: %.2f kr%n",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    product.getName(),
                    product.getPrice(),
                    product.getTaxAmount());
            writer.write(logEntry);
            System.out.println("Loggade köp: " + product.getName());
        } catch (IOException e) {
            System.err.println("Kunde inte logga köp: " + e.getMessage());
        }
    }
}