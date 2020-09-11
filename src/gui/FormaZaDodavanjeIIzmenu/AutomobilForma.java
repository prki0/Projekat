package gui.FormaZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import automobili.Automobil;
import automobili.Gorivo;
import automobili.Marka;
import automobili.Model;
import main.ProjekatMain;
import net.miginfocom.swing.MigLayout;
import radnja.Prodavnica;

public class AutomobilForma extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblVlasnik = new JLabel("Vlasnik");
	private JTextField txtVlasnik = new JTextField(20);
	private JLabel lblMarka = new  JLabel("Marka");
	private JComboBox<Marka> cbMarke = new JComboBox<Marka>(Marka.values());
	private JLabel lblModel = new JLabel("Model");
	private JComboBox<Model> cbModeli = new JComboBox<Model>(Model.values());
	private JLabel lblGodina = new JLabel("Godina Proizvodnje");
	private JTextField txtGodina= new JTextField(20);
	private JLabel lblZapremina = new JLabel("Zapremina Motora");
	private JTextField txtZapremina = new JTextField(20);
	private JLabel lblSnaga = new JLabel("Snaga Motora");
	private JTextField txtSnaga = new JTextField(20);
	private JLabel lblGorivo = new JLabel("Tip Goriva");
	private JComboBox<Gorivo> cbGoriva = new JComboBox<Gorivo>(Gorivo.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Automobil automobil;
	
	public AutomobilForma(Prodavnica prodavnica, Automobil automobil) {
		this.prodavnica = prodavnica;
		this.automobil = automobil;
		if(automobil == null) {
			setTitle("Dodavanje automobila");
		}else {
			setTitle("Izmena podataka - " + automobil.getID());
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
		
		if(automobil != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblVlasnik);
		add(txtVlasnik);
		add(lblMarka);
		add(cbMarke);
		add(lblModel);
		add(cbModeli);
		add(lblGodina);
		add(txtGodina);
		add(lblZapremina);
		add(txtZapremina);
		add(lblSnaga);
		add(txtSnaga);
		add(lblGorivo);
		add(cbGoriva);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	public void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String ID = txtID.getText().trim();
					String Vlasnik = txtVlasnik.getText().trim();
					Marka Marka = (Marka)cbMarke.getSelectedItem();
					Model Model = (Model)cbModeli.getSelectedItem();
					int godinaProizvodnje = Integer.parseInt(txtGodina.getText().trim());
					double ZapreminaMotora = Double.parseDouble(txtGodina.getText().trim());
					int SnagaMotora = Integer.parseInt(txtSnaga.getText().trim());
					Gorivo gorivo = (Gorivo)cbGoriva.getSelectedItem();
					
					if(automobil == null) {
						Automobil novi = new Automobil(ID, Vlasnik, Marka, Model, godinaProizvodnje, ZapreminaMotora, SnagaMotora, gorivo, false);
						prodavnica.dodajAutomobil(novi);
					}else {
						automobil.setID(ID);
						automobil.setVlasnik(Vlasnik);
						automobil.setMarka(Marka);
						automobil.setModel(Model);
						automobil.setGodinaProizvodnje(godinaProizvodnje);
						automobil.setZapreminaMotora(ZapreminaMotora);
						automobil.setSnagaMotora(SnagaMotora);
						automobil.setGorivo(gorivo);
						
					}
					
					prodavnica.snimiAutomobili(ProjekatMain.AUTOMOBILI_FAJL);
					AutomobilForma.this.dispose();
					AutomobilForma.this.setVisible(false);
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       AutomobilForma.this.dispose();
		    }
		});
	}
	
	public void popuniPolja() {
		txtID.setText(automobil.getID());
		txtVlasnik.setText(automobil.getVlasnik());
		cbMarke.setSelectedItem(automobil.getMarka());
		cbModeli.setSelectedItem(automobil.getModel());
		txtGodina.setText(String.valueOf((automobil.getGodinaProizvodnje())));
		txtZapremina.setText(String.valueOf(automobil.getZapreminaMotora()));
		txtSnaga.setText(String.valueOf(automobil.getSnagaMotora()));
		cbGoriva.setSelectedItem(automobil.getGorivo());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "-Unesite ID\n";
			ok = false;
		}else if(automobil == null) {
			String ID = txtID.getText().trim();
			Automobil pronadjeni = prodavnica.nadjiAutomobil(ID);
			if(pronadjeni !=null) {
				poruka += "-Automobil sa tim identifikacionim kodom vec postoji.\n";
				ok = false;
			}
		}
		if(txtVlasnik.getText().trim().equals("")) {
			poruka += "-Unesite vlasnika automobila\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtGodina.getText().trim());
		}catch(NumberFormatException e) {
			poruka += "- Godina mora biti broj\n";
			ok = false;
		}
		if(txtGodina.getText().trim().length() != 4) {
			poruka += "-Godina mora biti cetvorocifreni broj\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtZapremina.getText().trim());
		}catch(NumberFormatException e) {
			poruka += "- Zapremina mora biti broj\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtSnaga.getText().trim());
		}catch(NumberFormatException e) {
			poruka += "- Snaga mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Pogresni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		return ok;
	}

}