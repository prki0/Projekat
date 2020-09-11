package radnja;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import automobili.Automobil;
import automobili.Delovi;
import automobili.Gorivo;
import automobili.Marka;
import automobili.Model;
import korisnici.Admin;
import korisnici.Musterija;
import korisnici.Serviser;
import korisnici.Specijalizacija;
import korisnici.Pol;
import servisi.Knjizica;
import servisi.Servis;
import servisi.StatusServisa;


public class Prodavnica {

	private ArrayList<Automobil> automobili;
	private ArrayList<Delovi> delovi;
	private ArrayList<Admin> admini;
	private ArrayList<Musterija> musterije;
	private ArrayList<Serviser> serviseri;
	private ArrayList<Servis> servisi;
	private ArrayList<Knjizica> knjizice;

	public Prodavnica() {
		this.automobili = new ArrayList<Automobil>();
		this.delovi = new ArrayList<Delovi>();
		this.admini = new ArrayList<Admin>();
		this.musterije = new ArrayList<Musterija>();
		this.serviseri = new ArrayList<Serviser>();
		this.servisi = new ArrayList<Servis>();
		this.knjizice = new ArrayList<Knjizica>();
	}

	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}

	public void dodajAutomobil(Automobil automobil) {
		this.automobili.add(automobil);
	}

	public void obrisiAutomobil(Automobil automobil) {
		this.automobili.remove(automobil);
	}

	public Automobil nadjiAutomobil(String ID) {
		for (Automobil automobil : automobili) {
			if (automobil.getID().equals(ID)) {
				return automobil;
			}
		}
		return null;
	}
	
	public Automobil nadjiAutomobilPo(String vlasnik) {
		for (Automobil automobil : automobili) {
			if (automobil.getVlasnik().equals(vlasnik)) {
				return automobil;
			}
		}
		return null;
	}
	
	public ArrayList<Automobil> sviNeobrisaniAutomobili() {
		ArrayList<Automobil> neobrisani = new ArrayList<Automobil>();
		for (Automobil automobil : automobili) {
			if(!automobil.isObrisan()) {
				neobrisani.add(automobil);
			}
		}
		return neobrisani;
	}
	
	public void snimiAutomobili(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Automobil automobil : automobili) {
				content += automobil.getID() + "|" + automobil.getVlasnik() + "|"
						+ automobil.getMarka().ordinal() + "|" + automobil.getModel().ordinal() + "|"
						+ automobil.getGodinaProizvodnje() + "|" + automobil.getZapreminaMotora() + "|" + automobil.getSnagaMotora() + "|"
						+ automobil.getGorivo().ordinal() + "|" + automobil.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja automobila.");
		}
	}
	
	public void ucitajAutomobile(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ID = split[0];
				String vlasnik = split[1];
				int markaInt = Integer.parseInt(split[2]);
				Marka marka = Marka.values()[markaInt];
				int modelInt = Integer.parseInt(split[3]);
				Model model = Model.values()[modelInt];
				String godinaProizvodnjeString = split[4];
				int godinaProizvodnje = Integer.parseInt(godinaProizvodnjeString);
				String zapreminaMotoraString = split[5];
				double zapreminaMotora = Double.parseDouble(zapreminaMotoraString);
				String snagaMotoraString = split[6];
				int snagaMotora = Integer.parseInt(snagaMotoraString);
				int gorivoInt = Integer.parseInt(split[7]);
				Gorivo gorivo = Gorivo.values()[gorivoInt];
				boolean obrisan = Boolean.parseBoolean(split[8]);
				Automobil automobil = new Automobil(ID, vlasnik, marka, model, godinaProizvodnje, zapreminaMotora, snagaMotora, gorivo, obrisan);
				automobili.add(automobil);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja automobila");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Admin> getAdmini() {
		return admini;
	}

	public void dodajAdmina(Admin admin) {
		this.admini.add(admin);
	}

	public void obrisiAdmin(Admin admin) {
		this.admini.remove(admin);
	}

	public Admin nadjiAdmin(String ID) {
		for (Admin admin : admini) {
			if (admin.getID().equals(ID)) {
				return admin;
			}
		}
		return null;
	}
	
	public ArrayList<Admin> sviNeobrisaniAdmini() {
		ArrayList<Admin> neobrisani = new ArrayList<Admin>();
		for (Admin admin : admini) {
			if(!admin.isObrisan()) {
				neobrisani.add(admin);
			}
		}
		return neobrisani;
	}
	
	public void snimiAdmina(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Admin admin : admini) {
				content += admin.getID() + "|" + admin.getIme() + "|"
						+ admin.getPrezime() + "|" + admin.getJMBG() + "|"
						+ admin.getPol().ordinal() + "|" + admin.getAdresa() + "|" + admin.getBrojTelefona() + "|" + admin.getKorisnickoIme() + "|"
						+ admin.getLozinka() + "|" + admin.isObrisan() + "|" + admin.getPlata() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja admina.");
		}
	}
	
	public void ucitajAdmine(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ID = split[0];
				String ime = split[1];
				String prezime = split[2];
				String JMBGString = split[3];
				int JMBG = Integer.parseInt(JMBGString);
				int polInt = Integer.parseInt(split[4]);
				Pol pol = Pol.values()[polInt];
				String adresa = split[5];
				String brojString = split[6];
				int broj = Integer.parseInt(brojString);
				String username = split[7];
				String password = split[8];
				boolean obrisan = Boolean.parseBoolean(split[9]);
				String plataString = split[10];
				int plata = Integer.parseInt(plataString);
				Admin admin = new Admin(ID, ime, prezime, JMBG , pol, adresa, broj, username, password, obrisan, plata);
				admini.add(admin);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja admina");
			e.printStackTrace();
		}
	}
	
	
	public Admin loginAdmin(String korisnickoIme, String lozinka) {
		for(Admin admin : admini) {
			if(admin.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					admin.getLozinka().equals(lozinka) && !admin.isObrisan()) {
				return admin;
			}
		}
		return null;
	}
	
	public ArrayList<Serviser> getServiseri() {
		return serviseri;
	}

	public void dodajServisera(Serviser serviser) {
		this.serviseri.add(serviser);
	}

	public void obrisiServiser(Serviser serviser) {
		this.serviseri.remove(serviser);
	}

	public Serviser nadjiServiser(String ID) {
		for (Serviser serviser : serviseri) {
			if (serviser.getID().equals(ID)) {
				return serviser;
			}
		}
		return null;
	}
	
	public ArrayList<Serviser> sviNeobrisaniServiseri() {
		ArrayList<Serviser> neobrisani = new ArrayList<Serviser>();
		for (Serviser serviser : serviseri) {
			if(!serviser.isObrisan()) {
				neobrisani.add(serviser);
			}
		}
		return neobrisani;
	}
	
	public void snimiServisera(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Serviser serviser : serviseri) {
				content += serviser.getID() + "|" + serviser.getIme() + "|"
						+ serviser.getPrezime() + "|" + serviser.getJMBG() + "|"
						+ serviser.getPol().ordinal() + "|" + serviser.getAdresa() + "|" + serviser.getBrojTelefona() + "|" + serviser.getKorisnickoIme() + "|"
						+ serviser.getLozinka() + "|" + serviser.isObrisan() + "|" + serviser.getPlata() + "|" + serviser.getSpec() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja servisera.");
		}
	}
	
	public void ucitajServisere(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ID = split[0];
				String ime = split[1];
				String prezime = split[2];
				String JMBGString = split[3];
				int JMBG = Integer.parseInt(JMBGString);
				int polInt = Integer.parseInt(split[4]);
				Pol pol = Pol.values()[polInt];
				String adresa = split[5];
				String brojString = split[6];
				int broj = Integer.parseInt(brojString);
				String username = split[7];
				String password = split[8];
				boolean obrisan = Boolean.parseBoolean(split[9]);
				String plataString = split[10];
				int plata = Integer.parseInt(plataString);
				int specInt = Integer.parseInt(split[11]);
				Specijalizacija spec = Specijalizacija.values()[specInt];
				Serviser serviser = new Serviser(ID, ime, prezime, JMBG , pol, adresa, broj, username, password, obrisan, plata, spec);
				serviseri.add(serviser);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o serviserima");
			e.printStackTrace();
		}
	}
	
	
	public Serviser loginServiser(String korisnickoIme, String lozinka) {
		for(Serviser serviser : serviseri) {
			if(serviser.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					serviser.getLozinka().equals(lozinka) && !serviser.isObrisan()) {
				return serviser;
			}
		}
		return null;
	}
	
	public ArrayList<Musterija> getMusterija() {
		return musterije;
	}
	
	public void dodajMusteriju(Musterija musterija) {
		this.musterije.add(musterija);
	}
	
	public void obrisiMusteriju(Musterija musterija) {
		this.musterije.remove(musterija);
	}
	
	public ArrayList<Musterija> sveNeobrisaneMusterije() {
		ArrayList<Musterija> neobrisani = new ArrayList<Musterija>();
		for (Musterija musterija : musterije) {
			if(!musterija.isObrisan()) {
				neobrisani.add(musterija);
			}
		}
		return neobrisani;
	}
	
	public Musterija nadjiMusteriju(String korisnickoIme) {
		for (Musterija musterija : musterije) {
			if (musterija.getKorisnickoIme().equals(korisnickoIme)) {
				return musterija;
			}
		}
		return null;
	}
	
	public Musterija loginMusterija(String korisnickoIme, String lozinka) {
		for(Musterija musterija : musterije) {
			if(musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					musterija.getLozinka().equals(lozinka) && !musterija.isObrisan()) {
				return musterija;
			}
		}
		return null;
	}
	
	public void ucitajMusterije(String imeFajla) {
		try {
			File musterijaFile = new File("src/fajlovi/" + imeFajla);
			BufferedReader br = new BufferedReader(new FileReader(musterijaFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|");
				String ID = split[0];
				String ime = split[1];
				String prezime = split[2];
				String JMBGString = split[3];
				int JMBG = Integer.parseInt(JMBGString);
				int polInt = Integer.parseInt(split[4]);
				Pol pol = Pol.values()[polInt];
				String adresa = split[5];
				String brojString = split[6];
				int broj = Integer.parseInt(brojString);
				String username = split[7];
				String password = split[8];
				boolean obrisan = Boolean.parseBoolean(split[9]);
				String nagradniBodoviString = split[10];
				int nagradniBodovi = Integer.parseInt(nagradniBodoviString);
				Musterija musterija = new Musterija(ID, ime, prezime, JMBG, pol, adresa, broj, username, password, obrisan, nagradniBodovi);
				musterije.add(musterija);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Greska prilikom ucitavanja podataka o musteriji");
			e.printStackTrace();
		}
	}
	
	public void snimiMusterije(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			String sadrzaj = "";
			for (Musterija musterija : musterije) {
				sadrzaj += musterija.getIme() + "|" + musterija.getPrezime() + "|" + musterija.getPol().ordinal() + "|" 
						+ musterija.getKorisnickoIme() + "|" + musterija.getLozinka() + "|" + musterija.isObrisan() + "\n";
			}
			br.write(sadrzaj);
			br.close();
		} catch (Exception e) {
			System.out.println("Greska prilikom snimanja podataka o musteriji");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Delovi> getDelovi() {
		return delovi;
	}
	
	public void dodajDeo(Delovi deo) {
		this.delovi.add(deo);
	}
	
	public void obrisiDeo(Delovi deo) {
		this.delovi.remove(deo);
	}
	
	public Delovi nadjiDeo(String ID) {
		for (Delovi deo : delovi) {
			if (deo.getID().equals(ID)) {
				return deo;
			}
		}
		return null;
	}
	
	public ArrayList<Delovi> sviNeobrisaniDelovi() {
		ArrayList<Delovi> neobrisani = new ArrayList<Delovi>();
		for (Delovi deo : delovi) {
			if(!deo.isObrisan()) {
				neobrisani.add(deo);
			}
		}
		return neobrisani;
	}
	
	public void ucitajDelove(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ID = split[0];
				int markaInt = Integer.parseInt(split[2]);
				Marka marka = Marka.values()[markaInt];
				int modelInt = Integer.parseInt(split[2]);
				Model model = Model.values()[modelInt];
				String naziv = split[3];
				String cenaString= split[4];
				double cena = Double.parseDouble(cenaString);
				boolean obrisan = Boolean.parseBoolean(split[5]);
				Delovi deo = new Delovi(ID, marka, model, naziv, cena, obrisan);
				delovi.add(deo);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o delu");
			e.printStackTrace();
		}
	}
	
	public void snimiDeo(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Delovi deo : delovi) {
				content += deo.getID() + "|" + deo.getMarka().ordinal() + "|" + deo.getModel().ordinal() + "|" + deo.getNaziv() + "|"
						+ deo.getCena() + "|" + deo.isObrisan()  + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja delova.");
		}
	}
	
	public ArrayList<Servis> getServisi() {
		return servisi;
	}
	
	public void dodajServis(Servis servis) {
		this.servisi.add(servis);
	}
	
	public void obrisiServis(Servis servis) {
		this.servisi.remove(servis);
	}
	
	public Servis nadjiServis(String ID) {
		for (Servis servis : servisi) {
			if (servis.getID().equals(ID)) {
				return servis;
			}
		}
		return null;
	}
	
	public ArrayList<Servis> sviNeobrisaniServisi() {
		ArrayList<Servis> neobrisani = new ArrayList<Servis>();
		for (Servis servis : servisi) {
			if(!servis.isObrisan()) {
				neobrisani.add(servis);
			}
		}
		return neobrisani;
	}
	
	public void ucitajServise(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ID = split[0];
				String automobil = split[1];
				String musterija = split[2];
				String datum = split[3];
				String vreme = split[4];
				String opis = split[5];
				ArrayList<Delovi> delovi = new ArrayList<Delovi>();
				int statusInt = Integer.parseInt(split[6]);
				StatusServisa status = StatusServisa.values()[statusInt];
				boolean obrisan = Boolean.parseBoolean(split[7]);
				Servis servis = new Servis(ID, automobil, musterija, datum, vreme, opis , delovi, status, obrisan);
				servisi.add(servis);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o servisu");
			e.printStackTrace();
		}
	}
	
	public void snimiServis(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Servis servis : servisi) {
				content += servis.getID() + "|" + servis.getAutomobil() + "|" + servis.getServiser() + "|" + servis.getDatum() + "|"
						+ servis.getVreme() + "|" + servis.getOpis() + "|" + servis.isObrisan()  + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja servisa.");
		}
	}
	
	public ArrayList<Knjizica> getKnjizica() {
		return knjizice;
	}
	
	public void dodajKnjizicu(Knjizica knjizica) {
		this.knjizice.add(knjizica);
	}
	
	public void obrisiKnjizicu(Knjizica knjizica) {
		this.knjizice.remove(knjizica);
	}
	
	public Knjizica nadjiKnjizicu(String ID) {
		for (Knjizica knjizica : knjizice) {
			if (knjizica.getID().equals(ID)) {
				return knjizica;
			}
		}
		return null;
	}
	
	public ArrayList<Knjizica> sveNeobrisaneKnjizice() {
		ArrayList<Knjizica> neobrisani = new ArrayList<Knjizica>();
		for (Knjizica knjizica : knjizice) {
			if(!knjizica.isObrisan()) {
				neobrisani.add(knjizica);
			}
		}
		return neobrisani;
	}
	
	
	public void ucitajKnjizice(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ID = split[0];
				String automobil = split[1];
				ArrayList<Servis> servisi = new ArrayList<Servis>();
				boolean obrisan = Boolean.parseBoolean(split[2]);
				Knjizica knjizica = new Knjizica(ID, automobil, servisi, obrisan);
				knjizice.add(knjizica);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o knjizici");
			e.printStackTrace();
		}
	}
	
	public void snimiKnjizicu(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Knjizica knjizica : knjizice) {
				content += knjizica.getID() + "|" + knjizica.getAutomobil() + "|"
						 + knjizica.isObrisan()  + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja knjizice.");
		}
	}
	




}