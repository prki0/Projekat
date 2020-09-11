package gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import korisnici.Musterija;
import korisnici.Serviser;
import radnja.Prodavnica;


public class ServiserProzor extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu automobiliMenu = new JMenu("Automobili");
	private JMenuItem AutomobiliItem = new JMenuItem("Automobili");
	private JMenu servisneKnjigeMenu = new JMenu("Knjizice");
	private JMenuItem knjigeItem = new JMenuItem("Knjige");
	private JMenu statusServisaMenu = new JMenu("Status servisa");
	private JMenuItem StatusServisaItem = new JMenuItem("Pogledajte status");
	private Prodavnica prodavnica;
	
	public ServiserProzor(Prodavnica prodavnica, Serviser korisnik2) throws HeadlessException {
		super();
		this.prodavnica = prodavnica;
		setTitle("Musterija:" + korisnik2.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	public void initMenu() {
		setJMenuBar(mainMenu); 
		mainMenu.add(automobiliMenu);
		automobiliMenu.add(AutomobiliItem);
		mainMenu.add(servisneKnjigeMenu);
		servisneKnjigeMenu.add(knjigeItem);
		mainMenu.add(statusServisaMenu);
		statusServisaMenu.add(StatusServisaItem);
	}
	
public void initActions() {
		
		
	}
	
	

}