package automobili;
import korisnici.Musterija;
public class Automobil {
	
	private String ID;
	private String vlasnik;
	private Marka marka;
	private Model model;
	private int godinaProizvodnje;
	private double zapreminaMotora;
	private int snagaMotora;
	private Gorivo gorivo;
	private boolean obrisan;
	
	
	public Automobil() {
		this.ID = "";
		this.vlasnik = "";
		this.marka = Marka.Audi;
		this.model = Model.A3;
		this.godinaProizvodnje = 0;
		this.zapreminaMotora = 0;
		this.snagaMotora = 0;
		this.gorivo = Gorivo.Dizel;
		this.obrisan = false;
	}
	
	public Automobil(String ID, String vlasnik, Marka marka, Model model, int godinaProizvodnje, double zapreminaMotora, int snagaMotora, Gorivo gorivo, boolean obrisan) {
		this.ID = ID;
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.gorivo = gorivo;
		this.obrisan = obrisan;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
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
	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}
	public double getZapreminaMotora() {
		return zapreminaMotora;
	}

	public void setZapreminaMotora(double zapreminaMotora) {
		this.zapreminaMotora = zapreminaMotora;
	}
	public int getSnagaMotora() {
		return snagaMotora;
	}

	public void setSnagaMotora(int snagaMotora) {
		this.snagaMotora = snagaMotora;
	}
	public Gorivo getGorivo() {
		return gorivo;
	}

	public void setGorivo(Gorivo gorivo) {
		this.gorivo = gorivo;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	@Override
	public String toString() {
		return 	"AUTOMOBIL \nID: " + ID +
				"\nVlasnik: " + vlasnik +
				"\nMarka: " + marka +
				"\nModel: " + model +
				"\nGodina Proizvnodnje: " + godinaProizvodnje +
				"\nZapremina Motora: " + zapreminaMotora +
				"\nSnaga Motora: " + snagaMotora +
				"\nTip Goriva: " + gorivo +
				"\nObrisan: " + obrisan;
	}


}
