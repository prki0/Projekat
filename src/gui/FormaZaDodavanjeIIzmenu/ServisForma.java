package gui.FormaZaDodavanjeIIzmenu;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import main.ProjekatMain;
import net.miginfocom.swing.MigLayout;
import radnja.Prodavnica;
import servisi.Servis;
import servisi.StatusServisa;
import automobili.Delovi;


public class ServisForma extends JFrame {
	
	private JLabel lblID = new JLabel("Prodavnicaer");
	private JTextField txtID = new JTextField(20);
	private JLabel lblAutomobil =new JLabel("Automobil");
	private JTextField txtAutomobil = new JTextField(20);
	private JLabel lblServiser = new JLabel("Korisceni delovi");
	private JTextField txtServiser = new JTextField(20);
	private JLabel lblDatum = new JLabel("Serviser");
	private JTextField txtDatum = new JTextField(20);
	private JLabel lblVreme = new JLabel("Termin");
	private JTextField txtVreme = new JTextField(20);
	private JLabel lblOpis = new JLabel("Kratak opis");
	private JTextField txtOpis = new JTextField(20);
	private JLabel lblDelovi = new JLabel("Prodavnicana knjiga");
	private JTextField txtDelovi = new JTextField(20);
	private JLabel lblStatus = new JLabel("Status prodavnicaa");
	private JComboBox<StatusServisa> cbStatus = new JComboBox<StatusServisa>(StatusServisa.values());
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Servis servis;
	
	public ServisForma(Prodavnica prodavnica, Servis servis) {
		
		this.prodavnica = prodavnica;
		this.servis = servis;
		
		if(servis == null) {
			setTitle("Dodavanje servisa");
		}else {
			setTitle("Izmena podataka-" + prodavnica.getKnjizica());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
		
		
		
	}
	
	public void initGUI() {
		
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][]20[]");
		setLayout(layout);
		
		
		if(prodavnica != null) {
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblAutomobil);
		add(txtAutomobil);
		add(lblDelovi);
		add(txtDelovi);
		add(lblServiser);
		add(txtServiser);
		add(lblDatum);
		add(txtDatum);
		add(lblOpis);
		add(txtOpis);
		add(lblVreme);
		add(txtVreme);
		add(lblStatus);
		add(cbStatus);
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
					String automobil = txtAutomobil.getText().trim();
					String serviser = txtServiser.getText().trim();
					String datum = txtDatum.getText().trim();
					String vreme = txtVreme.getText().trim();
					String opis = txtOpis.getText().trim();
					StatusServisa status = (StatusServisa)cbStatus.getSelectedItem();
					
					if(prodavnica == null) {
						Servis novi = new Servis(ID, automobil, serviser, datum, vreme, opis, new ArrayList<Delovi>(), status, false);
						prodavnica.dodajServis(novi);	
					}else {
						servis.getID();
						servis.getAutomobil();
						servis.getDelovi();
						servis.getServiser();
						servis.getDatum();
						servis.getOpis();
						servis.getStatus();
					}
					
					prodavnica.snimiServis(ProjekatMain.SERVISI_FAJL);
					ServisForma.this.dispose();
					ServisForma.this.setVisible(false);
				}
				
			}
		});
		
	}
	
	public void popuniPolja() {
		txtID.setText(servis.getID());
		txtOpis.setText(servis.getOpis());
		cbStatus.setSelectedItem(servis.getStatus());
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "-Unesite ID\n";
			ok = false;
		}
		
		if(txtAutomobil.getText().trim().equals("")) {
			poruka += "-Unesite automobil\n";
			ok = false;
		}
		if(txtDelovi.getText().trim().equals("")) {
			poruka += "-Unesite deo\n";
			ok = false;
		}
		if(txtServiser.getText().trim().equals("")) {
			poruka += "-Unesite servisera\n";
			ok = false;
		}
		
		if(txtDatum.getText().trim().equals("")) {
			poruka += "-Unesite termin.\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Pogresni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		
		
		return ok;
	}




}