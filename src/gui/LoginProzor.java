package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import radnja.Prodavnica;

public class LoginProzor extends JFrame {

	private JLabel lblGreeting = new JLabel("Dobrodošli. Kako zelite da se prijavite?");
	private JButton btnAdmin = new JButton("Admin");
	private JButton btnMusterija = new JButton("Musterija");
	private JButton btnServiser = new JButton("Serviser");
	
	private Prodavnica prodavnica;
	
	public LoginProzor(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblGreeting, "span 2");
		add(btnMusterija, "split 2");
		add(btnServiser, "split 2");
		add(btnAdmin, "split 2");
		getRootPane().setDefaultButton(btnMusterija);
	}
	
	public void initActions() {
		btnMusterija.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginProzor.this.dispose();
			LoginProzor.this.setVisible(false);
			MusterijaLogin mg = new MusterijaLogin(prodavnica);
			mg.setVisible(true);
			
			
			
			}
		});
		
		btnAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);
				AdminLogin ag = new AdminLogin(prodavnica);
				ag.setVisible(true);
				
				
				
				}
			});
		
		btnServiser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);
				ServiserLogin sg = new ServiserLogin(prodavnica);
				sg.setVisible(true);
				
				
				
				}
			});
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
}
