package gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.formeZaPrikaz.AdminiPrikaz;
import gui.formeZaPrikaz.AutomobiliPrikaz;
import gui.formeZaPrikaz.DeloviPrikaz;
import gui.formeZaPrikaz.KnjizicePrikaz;
import gui.formeZaPrikaz.MusterijePrikaz;
import gui.formeZaPrikaz.ServiseriPrikaz;
import korisnici.Admin;
import korisnici.Musterija;
import radnja.Prodavnica;


public class AdminProzor extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu automobiliMenu = new JMenu("Automobili i delovi");
	private JMenuItem AutomobiliItem = new JMenuItem("Automobili");
	private JMenuItem DeloviItem = new JMenuItem("Delovi");
	private JMenu servisMenu = new JMenu("Servisi");
	private JMenu RadnikMenu = new JMenu("Korisnici");
	private JMenuItem radniciItem = new JMenuItem("Radnici");
	private JMenuItem musterijeItem = new JMenuItem("Musterije");
	private JMenuItem adminiItem = new JMenuItem("Admini");
	private JMenuItem knjigeItem = new JMenuItem("Knjizice");
	private JMenuItem StatusServisaItem = new JMenuItem("Status Servisa");
	
	private Prodavnica prodavnica;
	
	public AdminProzor(Prodavnica prodavnica, Admin korisnik3) throws HeadlessException {
		super();
		this.prodavnica = prodavnica;
		setTitle("Musterija:" + korisnik3.getKorisnickoIme());
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
		automobiliMenu.add(DeloviItem);
		mainMenu.add(servisMenu);
		servisMenu.add(knjigeItem);
		servisMenu.add(StatusServisaItem);
		mainMenu.add(RadnikMenu);
		RadnikMenu.add(radniciItem);
		RadnikMenu.add(adminiItem);
		RadnikMenu.add(musterijeItem);
	}
	
public void initActions() {
	AutomobiliItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			AutomobiliPrikaz ap = new AutomobiliPrikaz(prodavnica);
			ap.setVisible(true);
		}
	});
	
	DeloviItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			DeloviPrikaz ap = new DeloviPrikaz(prodavnica);
			ap.setVisible(true);
		}
	});
	
	knjigeItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			KnjizicePrikaz ap = new KnjizicePrikaz(prodavnica);
			ap.setVisible(true);
		}
	});
	
	radniciItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ServiseriPrikaz ap = new ServiseriPrikaz(prodavnica);
			ap.setVisible(true);
		}
	});
	
	adminiItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			AdminiPrikaz ap = new AdminiPrikaz(prodavnica);
			ap.setVisible(true);
		}
	});
	
	musterijeItem.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			MusterijePrikaz ap = new MusterijePrikaz(prodavnica);
			ap.setVisible(true);
		}
	});
		
	}
	
	

}