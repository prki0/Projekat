package gui;

import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import gui.formeZaPrikaz.KnjizicePrikaz;
import korisnici.Musterija;
import radnja.Prodavnica;


public class MusterijaProzor extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu servisMenu = new JMenu("Servisi");
	private JMenuItem knjigeItem = new JMenuItem("Knjizice");
	private JMenuItem StatusServisaItem = new JMenuItem("Zakazi Servis");	
	private Prodavnica prodavnica;
	
	public MusterijaProzor(Prodavnica prodavnica, Musterija korisnik1) throws HeadlessException {
		super();
		this.prodavnica = prodavnica;
		setTitle("Musterija:" + korisnik1.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	public void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(servisMenu);
		servisMenu.add(knjigeItem);
		servisMenu.add(StatusServisaItem);
	}
	
public void initActions() {
	knjigeItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			KnjizicePrikaz ap = new KnjizicePrikaz(prodavnica);
			ap.setVisible(true);
		}
	});
		
	}
	
	

}