package johan;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	public vendingMachineGUI() {
		setTitle("Varuautomat");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		initComponents();
		layoutComponents();

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
