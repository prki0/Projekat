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

import korisnici.Pol;
import korisnici.Admin;
import main.ProjekatMain;
import net.miginfocom.swing.MigLayout;
import radnja.Prodavnica;


public class AdminForma extends JFrame {
	
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
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrojTelefona = new JLabel("Broj Telefona");
	private JTextField txtBrojTelefona= new JTextField(20);
	private JLabel lblkorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JPasswordField(20);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Admin admin;
	
	public AdminForma(Prodavnica prodavnica, Admin admin) {
		
		this.prodavnica = prodavnica;
		this.admin = admin;
		
		if(admin == null) {
			setTitle("Dodavanje admina");
		}else {
			setTitle("Izmena podataka-" + admin.getID());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
		
		
		
	}
	
	public void initGUI() {
		
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][]20[]");
		setLayout(layout);
		
		
		if(admin != null) {
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
		add(lblPlata);
		add(txtPlata);
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
					int plata = Integer.parseInt(txtPlata.getText().trim());
					
					if(admin == null) {
						Admin novi = new Admin(ID, Ime, Prezime, JMBG, pol, adresa, brojTelefona,  KorisnickoIme, sifra, false, plata);
						prodavnica.dodajAdmina(novi);	
					}else {
						admin.setID(ID);
						admin.setIme(Ime);
						admin.setPrezime(Prezime);
						admin.setJMBG(JMBG);
						admin.setPol(pol);
						admin.setAdresa(adresa);
						admin.setBrojTelefona(brojTelefona);
						admin.setKorisnickoIme(KorisnickoIme);
						admin.setLozinka(sifra);
					}
					
					prodavnica.snimiAdmina(ProjekatMain.SERVISERI_FAJL);
					AdminForma.this.dispose();
					AdminForma.this.setVisible(false);
				}
				
			}
		});
		
	}
	
	public void popuniPolja() {
		txtID.setText(admin.getID());
		txtIme.setText(admin.getIme());
		txtPrezime.setText(admin.getPrezime());
		cbPol.setSelectedItem(admin.getPol());
		txtKorisnickoIme.setText(admin.getKorisnickoIme());
		pfLozinka.setText(admin.getLozinka());
	}
	
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "-Unesite ID\n";
			ok = false;
		}else if(admin == null) {
			String ID = txtID.getText().trim();
			Admin pronadjeni = prodavnica.nadjiAdmin(ID);
			if(pronadjeni !=null) {
				poruka += "-Admin sa tim identifikacionim kodom vec postoji.\n";
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
		}else if(admin == null) {
			String koisnickoIme = txtKorisnickoIme.getText().trim();
			Admin pronadjeni = prodavnica.nadjiAdmin(koisnickoIme);
			if(pronadjeni != null) {
				poruka += "-Admin sa tim korisnickim imenom vec postoji.\n";
				ok = false;
			}
		}
		String sifra = new String(pfLozinka.getPassword()).trim();
		if(sifra.equals("")) {
			poruka += "-Unesite lozinku.\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Pogresni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		return ok;
	}

}