package main;

import java.util.ArrayList;

import gui.LoginProzor;
import radnja.Prodavnica;

public class ProjekatMain {
	public static String ADMINI_FAJL = "admini.txt";
	public static String SERVISERI_FAJL = "serviseri.txt";
	public static String MUSTERIJE_FAJL = "musterije.txt";
	public static String AUTOMOBILI_FAJL = "automobili.txt";
	public static String DELOVI_FAJL = "delovi.txt";
	public static String SERVISI_FAJL = "servisi.txt";
	public static String KNJIZICE_FAJL = "knjizice.txt";
	
	
	public static void main(String[] args) {
		Prodavnica prodavnica = new Prodavnica();
		prodavnica.ucitajAdmine(ADMINI_FAJL);
		prodavnica.ucitajServisere(SERVISERI_FAJL);
		prodavnica.ucitajMusterije(MUSTERIJE_FAJL);
		prodavnica.ucitajAutomobile(AUTOMOBILI_FAJL);
		prodavnica.ucitajDelove(DELOVI_FAJL);
		prodavnica.ucitajServise(SERVISI_FAJL);
		prodavnica.ucitajKnjizice(KNJIZICE_FAJL);

		LoginProzor lp = new LoginProzor(prodavnica);
		lp.setVisible(true);
		
		
		

	}

}