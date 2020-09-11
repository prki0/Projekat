package korisnici;



public class Serviser extends Osoba {
	
	private int plata;
	private Specijalizacija spec;
	
	public Serviser() {
		this.plata = 0;
		this.spec = Specijalizacija.Automehanicar;
		
	}
	public Serviser(String ID, String ime, String prezime, int JMBG, Pol pol, String adresa, int brojTelefona, String korisnickoIme, String lozinka, boolean obrisan, int plata, Specijalizacija spec) {
		super(ID, ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		this.spec = spec;
	}
	
	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}
	
	public Specijalizacija getSpec() {
		return spec;
	}

	public void setSpec(Specijalizacija spec) {
		this.spec = spec;
	}
	
	@Override
	public String toString() {
		return "SERVISER " + super.toString() + 
				"\nPlata: " + this.plata +
				"\nSpecijalizacija: " + this.spec;
	}

}