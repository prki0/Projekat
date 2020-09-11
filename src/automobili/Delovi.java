package automobili;

public class Delovi {
	private String ID;
	private Marka marka;
	private Model model;
	private String naziv;
	private double cena;
	private boolean obrisan;
	
	
	
	public Delovi() {
		this.ID = "";
		this.marka = Marka.Audi;
		this.model = Model.A3;
		this.naziv = "";
		this.cena = 0;
		this.obrisan = false;
				
	}
	
	public Delovi(String ID, Marka marka, Model model, String naziv, double cena, boolean obrisan) {
		this.ID = ID;
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		this.cena = cena;
		this.obrisan = obrisan;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public Marka getMarka() {
		return marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public double getCena() {
		return cena;
	}
	
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	@Override
	public String toString() {
		return  "DEO \nID: " + ID +
				"\nMarka: " + marka +
				"\nModel: " + model +
				"\nNaziv Dela: " + naziv +
				"\nCena: " + cena +
				"\nObrisan: " + obrisan;
	}
	

}
