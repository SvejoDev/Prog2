package johan;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	private void layoutComponetns() {
		//gridbag såg häftigt ut i dokumentationen
		mainPanel.setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();	}

}
