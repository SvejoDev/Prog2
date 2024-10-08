package ownGUI;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;


public class GuiFromScratch extends JFrame{
	//Fönsterdimensioner
	private final int SIZEX = 400;
	private final int SIZEY = 400;
	//instansvariabler, objekt i fönsterna:
	JPanel startPanel, instPanel;
	JButton btnAlt1, btnAlt2, btnAlt3, btnSettings;
	JTextField txtIn;
	

	public static void main(String[] args) {
		JFrame window = new GuiFromScratch(); //Skapar ett objekt av klassen

	}
	
	private GuiFromScratch() {
		//funktion för när fönstret stängs
		addWindowListener(new WindowAdapter(){
			public void WindowClosing(WindowEvent e) {
				
			}
		});
		//synligt fönster
		setVisible(true);
		setSize(SIZEX, SIZEY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		startPanel = new JPanel();
		initComponents();
		startView();
		
	}
	private void initComponents() {
		btnAlt1 = new JButton ("Click me");
		btnAlt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//MetodKall för nästa vy
				
			}
			
		});
		btnAlt2 = new JButton("Open me");
		btnAlt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Metodkall för nästa vy
				
			}
		});
		btnAlt3 = new JButton("Knapp3");
		btnAlt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				settings();				
			}
			
		});
		txtIn = new JTextField();
	}
	private void startView() {
		startPanel.setLayout(new GridLayout(2, 2));
		startPanel.add(btnAlt1);
		startPanel.add(btnAlt2);
		startPanel.add(btnAlt3);
		startPanel.add(btnSettings);
		//ändra bakgrund
		setContentPane(startPanel);
		validate();
		
	}
	
	private void settings() {
		getContentPane().removeAll();
		instPanel = new JPanel(new GridLayout(2,1));
		instPanel.add(txtIn);
		setContentPane(instPanel);
		validate();
		
	}
	
}
