package gui.FormaZaDodavanjeIIzmenu;

import java.awt.HeadlessException; 
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import main.ProjekatMain;
import net.miginfocom.swing.MigLayout;
import radnja.Prodavnica;
import servisi.Knjizica;
import servisi.Servis;


public class KnjizicaForma extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblAuto =new JLabel("Automobil");
	private JTextField txtAuto = new JTextField(20);
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Knjizica knjizice;
	
	
	public KnjizicaForma(Prodavnica prodavnica, Knjizica knjizice) throws HeadlessException {
		super();
		this.prodavnica = prodavnica;
		this.knjizice = knjizice;
		
		if(knjizice == null) {
			setTitle("Dodavanje servisera");
		}else {
			setTitle("Izmena podataka-" + knjizice.getID());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
		
	}
	
	
	public void initGUI() {
		
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][]20[]");
		setLayout(layout);
		
		
		if(knjizice != null) {
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblAuto);
		add(txtAuto);
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
					String automobil = txtAuto.getText().trim();
					
					
					if(knjizice == null) {
						Knjizica novi = new Knjizica(ID, automobil, new ArrayList<Servis>(), false);
						prodavnica.dodajKnjizicu(novi);	
					}else {
						knjizice.setID(ID);
						knjizice.setAutomobil(automobil);
						
					}
					
					prodavnica.snimiKnjizicu(ProjekatMain.KNJIZICE_FAJL);
					KnjizicaForma.this.dispose();
					KnjizicaForma.this.setVisible(false);
				}
				
			}
		});
		
	}
	
	public void popuniPolja() {
		txtID.setText(knjizice.getID());
		txtAuto.setText(knjizice.getAutomobil());
		
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "-Unesite ID\n";
			ok = false;
		}else if(knjizice == null) {
			String ID = txtID.getText().trim();
			Knjizica pronadjeni = prodavnica.nadjiKnjizicu(ID);
			if(pronadjeni !=null) {
				poruka += "-Servisna knjiga sa tim identifikacionim kodom vec postoji.\n";
				ok = false;
			}
		}
		if(txtAuto.getText().trim().equals("")) {
			poruka += "-Unesite automobil\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Pogresni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		return ok;
	}
	
	

}
