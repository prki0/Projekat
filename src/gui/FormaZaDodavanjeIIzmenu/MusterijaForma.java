package gui.FormaZaDodavanjeIIzmenu;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import korisnici.Musterija;
import korisnici.Pol;
import main.ProjekatMain;
import net.miginfocom.swing.MigLayout;
import radnja.Prodavnica;



public class MusterijaForma extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblIme =new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblAdresa = new JLabel("JMBG");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrojTelefona = new JLabel("JMBG");
	private JTextField txtBrojTelefona= new JTextField(20);
	private JLabel lblkorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblbrojBodova = new JLabel("Nagradni bodovi");
	private JTextField txtbrojBodova = new JTextField(20);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Musterija musterija;
	
	public MusterijaForma(Prodavnica prodavnica, Musterija musterija) {
		
		this.prodavnica = prodavnica;
		this.musterija = musterija;
		
		if(musterija == null) {
			setTitle("Dodavanje musterije");
		}else {
			setTitle("Izmena podataka-" + musterija.getKorisnickoIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
		
		
		
	}
	
	public void initGUI() {
		
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][]20[]");
		setLayout(layout);
		
		
		if(musterija != null) {
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblPol);
		add(cbPol);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrojTelefona);
		add(txtBrojTelefona);
		add(lblkorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblbrojBodova);
		add(txtbrojBodova);
		add(new Label());
		add(btnOk, "split 2");
		add(btnCancel);
		
	}
	
	
	public void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String ID = txtID.getText().trim();
					String Ime = txtIme.getText().trim();
					String Prezime = txtPrezime.getText().trim();
					int JMBG = Integer.parseInt(txtJMBG.getText().trim());
					Pol pol = (Pol)cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					int brojTelefona = Integer.parseInt(txtBrojTelefona.getText().trim());
					String KorisnickoIme = txtKorisnickoIme.getText().trim();
					String sifra = new String(pfLozinka.getPassword()).trim();
					int brojBodova = Integer.parseInt(txtbrojBodova.getText().trim());
					
					if(musterija == null) {
						Musterija novi = new Musterija(ID, Ime, Prezime, JMBG, pol, adresa, brojTelefona,  KorisnickoIme, sifra, false, brojBodova);
						prodavnica.dodajMusteriju(novi);	
					}else {
						musterija.setID(ID);
						musterija.setIme(Ime);
						musterija.setPrezime(Prezime);
						musterija.setPol(pol);
						musterija.setKorisnickoIme(KorisnickoIme);
						musterija.setLozinka(sifra);
						musterija.setBrojBodova(brojBodova);
					}
					
					prodavnica.snimiMusterije(ProjekatMain.MUSTERIJE_FAJL);
					MusterijaForma.this.dispose();
					MusterijaForma.this.setVisible(false);
				}
				
			}
		});
		
		
		
	}
	
	public void popuniPolja() {
		txtID.setText(musterija.getID());
		txtIme.setText(musterija.getIme());
		txtPrezime.setText(musterija.getPrezime());
		cbPol.setSelectedItem(musterija.getPol());
		txtKorisnickoIme.setText(musterija.getKorisnickoIme());
		pfLozinka.setText(musterija.getLozinka());
		txtbrojBodova.setText(String.valueOf(musterija.getBrojBodova()));
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "-Unesite ID\n";
			ok = false;
		}else if(musterija == null) {
			String ID = txtID.getText().trim();
			Musterija pronadjeni = prodavnica.nadjiMusteriju(ID);
			if(pronadjeni !=null) {
				poruka += "-Musterija sa tim identifikacionim kodom vec postoji.\n";
				ok = false;
			}
		}
		if(txtIme.getText().trim().equals("")) {
			poruka += "-Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "-Unesite prezime\n";
			ok = false;
		}
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "-Unesite korisnicko ime\n";
			ok = false;
		}else if(musterija == null) {
			String koisnickoIme = txtKorisnickoIme.getText().trim();
			Musterija pronadjeni = prodavnica.nadjiMusteriju(koisnickoIme);
			if(pronadjeni != null) {
				poruka += "-Musterija sa tim korisnickim imenom vec postoji.\n";
				ok = false;
			}
		}
		String sifra = new String(pfLozinka.getPassword()).trim();
		if(sifra.equals("")) {
			poruka += "-Unesite lozinku.\n";
			ok = false;
		}
		if(txtbrojBodova.getText().trim().equals("")) {
			poruka += "-Unesite broj nagradnih bodova\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Pogresni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		return ok;
	}






}