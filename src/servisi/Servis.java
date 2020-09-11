package servisi;

import java.util.ArrayList;
import automobili.Automobil;
import automobili.Delovi;
import korisnici.Serviser;

public class Servis {
	private String ID;
	private String automobil;
	private String serviser;
	private String datum;
	private String vreme;
	private String opis;
	private ArrayList<Delovi> delovi;
	private StatusServisa status;
	private boolean obrisan;
	
	public Servis() {
		this.ID = "";
		this.automobil = "";
		this.serviser = "";
		this.datum = "";
		this.vreme = "";
		this.opis = "";
		this.delovi = new ArrayList<Delovi>();
		this.status = StatusServisa.Zakazan;
		this.obrisan = false;
	}
	
	public Servis(String ID, String automobil, String serviser, String datum, String vreme, String opis,
			ArrayList<Delovi> delovi, StatusServisa status, boolean obrisan) {
		this.ID = ID;
		this.automobil = automobil;
		this.serviser = serviser;
		this.datum = datum;
		this.vreme = vreme;
		this.opis = opis;
		this.delovi = delovi;
		this.status = status;
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
	
	public String getServiser() {
		return serviser;
	}

	public void setServiser(String serviser) {
		this.serviser = serviser;
	}
	
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
	
	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}
	
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public ArrayList<Delovi> getDelovi() {
		return delovi;
	}

	public void setDelovi(ArrayList<Delovi> delovi) {
		this.delovi = delovi;
	}
	
	public StatusServisa getStatus() {
		return status;
	}

	public void setStatus(StatusServisa status) {
		this.status = status;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	@Override
	public String toString() {
		String s = "SERVIS\nID: " + this.ID + 
				"\nAutomobil: " + this.automobil +
				"\nServiser: " + this.serviser +
				"\nDatum: " + this.datum +
				"\nVreme: " + this.vreme +
				"\nOpis: " + this.opis + 
				"\nStatus: " + this.status +
				"\nObrisan: " + this.obrisan;
		for (Delovi delovi : delovi) {
			s += "\n" + delovi;
		}
		return s;
	}
	
	
	
	

}
