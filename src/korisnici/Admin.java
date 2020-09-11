package korisnici;

public class Admin extends Osoba {
	
	private int plata;
	
	public Admin() {
		this.plata = 0;
		
	}
	public Admin(String ID, String ime, String prezime, int JMBG, Pol pol, String adresa, int brojTelefona, String korisnickoIme, String lozinka, boolean obrisan, int plata) {
		super(ID, ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
	}
	
	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}
	
	@Override
	public String toString() {
		return "ADMIN " + super.toString() + 
				"\nPlata: " + this.plata; 
	}

}
