package johan;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class vendingMachineGUI extends JFrame {
    // Konstanter för fönstrets storlek (touch screen 500x500 enligt kravspec)
    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 500;

    // GUI-komponenter
    private JPanel mainpanel;           // Huvudpanelen som innehåller alla komponenter
    private JButton[] productbuttons;   // Array med knappar för alla produkter
    private JButton adminbutton;        // Knapp för att öppna admin-panelen
    private VMLogik logic;             // Kopplingen till affärslogiken
    private String csvfilepath;         // Sökväg till CSV-filen med produktdata

    // Konstruktor - skapar och initierar hela användargränssnittet
    public vendingMachineGUI() {
        // Grundinställningar för fönstret
        setTitle("Varuautomat");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);  // Låst storlek enligt kravspec
        
        // Skapar sökvägen till CSV-filen (finns i GUI-mappen)
        String projectpath = System.getProperty("user.dir");
        csvfilepath = projectpath + File.separator + "GUI" + File.separator + "products.csv";
        
        // Laddar sparat tillstånd eller skapar nytt via filehandler
        logic = filehandler.loadState();
        logic.setCsvFilePath(csvfilepath);
        
        // Bygger upp gränssnittet
        initComponents();
        layoutComponents();
        updateProductButtons();

        // Sparar automatiskt tillståndet när programmet stängs
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                filehandler.saveState(logic);
            }
        });

        setVisible(true);
    }

    // Skapar alla GUI-komponenter
    private void initComponents() {
        mainpanel = new JPanel();
        
        // Skapar 9 produktknappar (3x3 rutnät)
        productbuttons = new JButton[9];
        for (int i = 0; i < productbuttons.length; i++) {
            final int index = i;
            productbuttons[i] = new JButton();
            productbuttons[i].addActionListener(e -> buyProduct(index));
        }
        
        // Skapar admin-knappen
        adminbutton = new JButton("Admin");
        adminbutton.addActionListener(e -> showAdminPanel());
        
        // Laddar in produkter om automaten är tom
        loadInitialProducts();
        updateProductButtons();
    }

    // Laddar initiala produkter från CSV om inga finns
    private void loadInitialProducts() {
        if (logic.getProducts().isEmpty()) {
            List<Produkt> newproducts = filehandler.loadProductsFromCSV(csvfilepath);
            for (Produkt product : newproducts) {
                logic.addProdukt(product);
            }
        }
    }

    // Fyller på lagret genom att läsa in nya kvantiteter från CSV
    private void restockFromCsv() {
        // Läser in aktuella värden från CSV
        List<Produkt> csvproducts = filehandler.loadProductsFromCSV(csvfilepath);
        
        // Uppdaterar kvantiteter för befintliga produkter
        for (Produkt csvproduct : csvproducts) {
            for (Produkt existingproduct : logic.getProducts()) {
                if (existingproduct.getName().equals(csvproduct.getName())) {
                    existingproduct.setQuantity(csvproduct.getQuantity());
                    break;
                }
            }
        }
        
        updateProductButtons();
        JOptionPane.showMessageDialog(this, "Lagret har fyllts på från CSV-filen");
    }

    // Placerar ut alla komponenter i fönstret enligt ett rutnät
    private void layoutComponents() {
        mainpanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Lägger ut produktknapparna i ett 3x3 rutnät
        for (int i = 0; i < productbuttons.length; i++) {
            gbc.gridx = i % 3;        // x-position: 0, 1 eller 2
            gbc.gridy = i / 3;        // y-position: 0, 1 eller 2
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);  // Mellanrum mellan knappar
            mainpanel.add(productbuttons[i], gbc);
        }

        // Lägger till admin-knappen längst ner
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weighty = 0.1;
        mainpanel.add(adminbutton, gbc);

        setContentPane(mainpanel);
    }

    // Uppdaterar alla produktknappar med aktuell information
    private void updateProductButtons() {
        for (int i = 0; i < productbuttons.length; i++) {
            if (i < logic.getProducts().size()) {
                Produkt product = logic.getProducts().get(i);
                // Visar produktinfo med HTML-formatering för radbrytningar
                productbuttons[i].setText(String.format("<html>%s<br>%.2f kr<br>Antal: %d</html>",
                        product.getName(), product.getPrice(), product.getQuantity()));
                // Inaktiverar knappen om produkten är slut
                productbuttons[i].setEnabled(product.getQuantity() > 0);
            } else {
                productbuttons[i].setText("Tom");
                productbuttons[i].setEnabled(false);
            }
        }
    }

    // Hanterar köp av en produkt
    private void buyProduct(int index) {
        Produkt boughtproduct = logic.buyProduct(index);
        if (boughtproduct != null) {
            JOptionPane.showMessageDialog(this,
                    String.format("Du köpte %s", boughtproduct.getName()));
            updateProductButtons();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Kunde inte köpa produkten. Kontrollera tillgänglighet.");
        }
    }

    // Visar admin-panelen med påfyllningsfunktion
    private void showAdminPanel() {
        JDialog admindialog = new JDialog(this, "Admin Panel", true);
        admindialog.setSize(300, 100);
        admindialog.setLayout(new GridLayout(1, 1));

        JButton restockbutton = new JButton("Fyll på från CSV");
        restockbutton.addActionListener(e -> {
            restockFromCsv();
            admindialog.dispose();  // Stänger admin-panelen efter påfyllning
        });

        admindialog.add(restockbutton);
        admindialog.setLocationRelativeTo(this);
        admindialog.setVisible(true);
    }

    // Huvudmetod som startar programmet
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new vendingMachineGUI());
    }
}