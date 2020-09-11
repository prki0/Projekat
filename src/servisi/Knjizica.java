package servisi;

import java.util.ArrayList;

import automobili.Automobil;

public class Knjizica {
	private String ID;
	private String automobil;
	private ArrayList<Servis> servisi;
	private boolean obrisan;
	
	public Knjizica() {
		this.ID = "";
		this.automobil = "";
		this.servisi = new ArrayList<Servis>();
		this.obrisan = false;
		
	}
	
	public Knjizica(String ID, String automobil, ArrayList<Servis> servisi, boolean obrisan) {
		this.ID = ID;
		this.automobil = automobil;
		this.servisi = servisi;
		this.obrisan = obrisan;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getAutomobil() {
		return automobil;
	}

	public void setAutomobil(String automobil) {
		this.automobil = automobil;
	}
	
	public ArrayList<Servis> getServisi() {
		return servisi;
	}

	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	@Override
	public String toString() {
		String s = "Knjizica\nID: " + this.ID + 
				"\nAutomobil: " + this.automobil +
				"\nObrisan: " + this.obrisan;
		for (Servis servis : servisi) {
			s += "\n" + servis;
		}
		return s;
	}
	
	

}
