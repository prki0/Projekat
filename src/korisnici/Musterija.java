package korisnici;



public class Musterija extends Osoba {
	
	private int brojBodova;
	
	public Musterija() {
		this.brojBodova = 0;
		
	}
	public Musterija(String ID, String ime, String prezime, int JMBG, Pol pol, String adresa, int brojTelefona, String korisnickoIme, String lozinka, boolean obrisan, int brojBodova) {
		super(ID, ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		this.brojBodova = brojBodova;
	}
	
	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}
	
	@Override
	public String toString() {
		return "Musterija: " + super.toString() + 
				"\nBrojBodova: " + this.brojBodova; 
	}


}