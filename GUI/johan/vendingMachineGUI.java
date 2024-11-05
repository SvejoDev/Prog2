package johan;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class vendingMachineGUI extends JFrame{
	private static final int SCREEN_WIDTH = 500;
	private static final int SCREEN_HEIGHT = 500;

	private JPanel mainPanel;
	private JButton[] productButtons;
	private JButton adminbutton;
	private JLabel balanceLabel;
	private VMLogik logic;

	public vendingMachineGUI() {
		setTitle("Varuautomat");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		
		logic = Filehandler.loadState();
		initComponents();
		layoutComponents();

		setVisible(true);
		
	    addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Filehandler.saveState(logic);
            }
        });

        setVisible(true);
    }
	private void initComponents() {
		mainPanel = new JPanel();
		productButtons = new JButton[9];
		for(int i = 0; i < productButtons.length; i++) {
			productButtons[i] = new JButton("Produkt" + (i + 1));
			productButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//MetodKall för nästa vy

				}

			});
		}
		adminbutton = new JButton("Admin");
		adminbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});
		balanceLabel = new JLabel("Totalt 0kr");
	}
	
	///Nu jävlar kommer alla knappars logik, inshalla!

    private void updateProductButtons() {
        for (int i = 0; i < productButtons.length; i++) {
            if (i < logic.getProducts().size()) {
                Produkt product = logic.getProducts().get(i);
                productButtons[i].setText(String.format("<html>%s<br>%.2f kr<br>Antal: %d</html>",
                        product.getName(), product.getPrice(), product.getQuantity()));
                productButtons[i].setEnabled(product.getQuantity() > 0);
            } else {
                productButtons[i].setText("Tom");
                productButtons[i].setEnabled(false);
            }
        }
        balanceLabel.setText(String.format("Totalt: %.2f kr", logic.getBalance()));
    }

    private void buyProduct(int index) {
        Produkt boughtProduct = logic.buyProduct(index);
        if (boughtProduct != null) {
            JOptionPane.showMessageDialog(this,
                    String.format("Du köpte %s för %.2f kr", boughtProduct.getName(), boughtProduct.getPrice()));
            updateProductButtons();
        } else {
            JOptionPane.showMessageDialog(this, "Kunde inte köpa produkten. Kontrollera saldo och tillgänglighet.");
        }
    }

    private void showAdminPanel() {
        JDialog adminDialog = new JDialog(this, "Admin Panel", true);
        adminDialog.setSize(300, 200);
        adminDialog.setLayout(new GridLayout(3, 1));

        JButton restockButton = new JButton("Fyll på produkter");
        restockButton.addActionListener(e -> restockProducts());

        JButton addMoneyButton = new JButton("Lägg till pengar");
        addMoneyButton.addActionListener(e -> addMoney());

        adminDialog.add(restockButton);
        adminDialog.add(addMoneyButton);
        adminDialog.setLocationRelativeTo(this);
        adminDialog.setVisible(true);
    }

    private void restockProducts() {
        String filename = "products.csv"; // Din CSV-fils namn
        List<Produkt> newProducts = Filehandler.loadProductsFromCSV(filename);
        for (Produkt product : newProducts) {
            logic.addProdukt(product);
        }
        updateProductButtons();
    }

    private void addMoney() {
        String input = JOptionPane.showInputDialog(this, "Ange belopp att lägga till:");
        try {
            double amount = Double.parseDouble(input);
            logic.addMoney(amount);
            updateProductButtons();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ogiltigt belopp. Ange ett nummer.");
        }
    }
	
	private void layoutComponents() {
		//gridbag såg häftigt ut i dokumentationen
		mainPanel.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();

		 //produkt buttons:
		 for(int i = 0; i < productButtons.length; i++) {
			 //inkopierat från docs:
			  gbc.gridx = i % 3;
	            gbc.gridy = i / 3;
	            gbc.fill = GridBagConstraints.BOTH;
	            gbc.weightx = 1.0;
	            gbc.weighty = 1.0;
	            gbc.insets = new Insets(5, 5, 5, 5);
	            mainPanel.add(productButtons[i], gbc);
		 }
		   gbc.gridx = 0;
	        gbc.gridy = 3;
	        gbc.gridwidth = 2;
	        gbc.weightx = 0.66;
	        mainPanel.add(balanceLabel, gbc);

	        gbc.gridx = 2;
	        gbc.gridy = 3;
	        gbc.gridwidth = 1;
	        gbc.weightx = 0.33;
	        mainPanel.add(adminbutton, gbc);

	        setContentPane(mainPanel);
		 }
	   public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new vendingMachineGUI();
	            }
	        });
	    }

}
