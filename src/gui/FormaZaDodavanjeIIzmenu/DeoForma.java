package gui.FormaZaDodavanjeIIzmenu;

import java.awt.Label; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import automobili.Delovi;
import automobili.Marka;
import automobili.Model;
import main.ProjekatMain;
import net.miginfocom.swing.MigLayout;
import radnja.Prodavnica;


public class DeoForma extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblDeo =new JLabel("Opis");
	private JTextField txtDeo = new JTextField(20);
	private JLabel lblMarka = new JLabel("Marka");
	private JComboBox<Marka> cbMarka = new JComboBox<Marka>(Marka.values());
	private JLabel lblModel = new JLabel("Model");
	private JComboBox<Model> cbModel = new JComboBox<Model>(Model.values());
	private JLabel lblCena = new JLabel("Cena");
	private JTextField txtCena = new JTextField(20);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Delovi delovi;
	
	public DeoForma(Prodavnica prodavnica, Delovi delovi) {
		
		this.prodavnica = prodavnica;
		this.delovi = delovi;
		
		if(delovi == null) {
			setTitle("Dodavanje dela");
		}else {
			setTitle("Izmena podataka-" + delovi.getID());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
		
		
		
	}
	
	public void initGUI() {
		
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		
		if(delovi != null) {
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblDeo);
		add(txtDeo);
		add(lblMarka);
		add(cbMarka);
		add(lblModel);
		add(cbModel);
		add(lblCena);
		add(txtCena);
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
					String naziv = txtDeo.getText().trim();
					Marka marka = (Marka)cbMarka.getSelectedItem();
					Model model = (Model)cbModel.getSelectedItem();
					int cena = Integer.parseInt(txtCena.getText().trim());
					
					
					if(delovi == null) {
						Delovi novi = new Delovi(ID, marka, model, naziv, cena, false);
						prodavnica.dodajDeo(novi);	
					}else {
						delovi.setID(ID);
						delovi.setNaziv(naziv);
						delovi.setMarka(marka);
						delovi.setModel(model);
						delovi.setCena(cena);
						
					}
					
					prodavnica.snimiDeo(ProjekatMain.DELOVI_FAJL);
					DeoForma.this.dispose();
					DeoForma.this.setVisible(false);
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       DeoForma.this.dispose();
		    }
		});
		
	}
	
	public void popuniPolja() {
		txtID.setText(delovi.getID());
		txtDeo.setText(delovi.getNaziv());
		cbMarka.setSelectedItem(delovi.getMarka());
		cbModel.setSelectedItem(delovi.getModel());
		txtCena.setText(String.valueOf(delovi.getCena()));
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "-Unesite ID\n";
			ok = false;
		}else if(delovi == null) {
			String ID = txtID.getText().trim();
			Delovi pronadjeni = prodavnica.nadjiDeo(ID);
			if(pronadjeni !=null) {
				poruka += "-Deo sa tim identifikacionim kodom vec postoji.\n";
				ok = false;
			}
		}
		if(txtDeo.getText().trim().equals("")) {
			poruka += "-Unesite ime dela\n";
			ok = false;
		}
		if(txtCena.getText().trim().equals("")) {
			poruka += "-Unesite cenu dela\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Pogresni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		return ok;
	}

}