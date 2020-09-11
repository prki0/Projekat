package korisnici;

public abstract class Osoba {
	
	protected String ID;
	protected String ime;
	protected String prezime;
	protected int JMBG;
	protected Pol pol;
	protected String adresa;
	protected int brojTelefona;
	protected String korisnickoIme;
	protected String lozinka;
	protected boolean obrisan;
	
	public Osoba() {
		this.ID = "";
		this.ime = "";
		this.prezime = "";
		this.JMBG = 0;
		this.pol = Pol.ZENSKI;
		this.adresa = "";
		this.brojTelefona = 0;
		this.korisnickoIme = "";
		this.lozinka = "";
		this.obrisan = false;
	}

	public Osoba(String ID, String ime, String prezime, int JMBG, Pol pol, String adresa, int brojTelefona, String korisnickoIme, String lozinka, boolean obrisan) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public int getJMBG() {
		return JMBG;
	}

	public void setJMBG(int JMBG) {
		this.JMBG = JMBG;
	}
	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public int getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(int brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	@Override
	public String toString() {
		return 	"\nID: " + ID +
				"\nIme: " + ime +
				"\nPrezime: " + prezime +
				"\nJMBG: " + JMBG +
				"\nPol: " + pol +
				"\nAdresa: " + adresa +
				"\nBrojTelefona: " + brojTelefona +
				"\nKorisnicko Ime: " + korisnickoIme + 
				"\nLozinka: " + lozinka +
				"\nObrisan: " + obrisan;
	}
}
