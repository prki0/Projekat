package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.FormaZaDodavanjeIIzmenu.MusterijaForma;
import korisnici.Musterija;
import main.ProjekatMain;
import radnja.Prodavnica;

public class MusterijePrikaz extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel modelTabele;
	private JTable musterijeTabela;
	
	private Prodavnica prodavnica;
	
	public MusterijePrikaz(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Musterije");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
		
	}
	
	private void initGUI() {
		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		mainToolbar.setFloatable(false);
		
		String[] zaglavlje = new String[] {"ID", "Ime", "Prezime","JMBG", "Pol","Adresa","Broj Telefona", "Korisnicko ime", "Lozinka", "Broj Bodova"};
		Object[][] sadrzaj = new Object[prodavnica.sveNeobrisaneMusterije().size()][zaglavlje.length];
		
		for(int i=0; i<prodavnica.sveNeobrisaneMusterije().size(); i++) {
			Musterija musterija = prodavnica.sveNeobrisaneMusterije().get(i);
			sadrzaj[i][0] = musterija.getID();
			sadrzaj[i][1] = musterija.getIme();
			sadrzaj[i][2] = musterija.getPrezime();
			sadrzaj[i][3] = musterija.getJMBG();
			sadrzaj[i][4] = musterija.getPol();
			sadrzaj[i][5] = musterija.getAdresa();
			sadrzaj[i][6] = musterija.getBrojTelefona();
			sadrzaj[i][7] = musterija.getKorisnickoIme();
			sadrzaj[i][8] = musterija.getLozinka();
			sadrzaj[i][9] = musterija.getBrojBodova();
		}
		
		modelTabele = new DefaultTableModel(sadrzaj, zaglavlje);
		musterijeTabela = new JTable(modelTabele);
		
		musterijeTabela.setRowSelectionAllowed(true);
		musterijeTabela.setColumnSelectionAllowed(false);
		musterijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musterijeTabela.setDefaultEditor(Object.class, null);
		musterijeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollpane = new JScrollPane(musterijeTabela);
		add(scrollpane, BorderLayout.CENTER);
		
		
		
	}

private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red iz tabele.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = modelTabele.getValueAt(red, 4).toString();
					Musterija musterija = prodavnica.nadjiMusteriju(korisnickoIme);
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelize da obrisete musteriju?", korisnickoIme + "-potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						musterija.setObrisan(true);
						modelTabele.removeRow(red);
						prodavnica.snimiMusterije(ProjekatMain.MUSTERIJE_FAJL);
					}
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MusterijaForma mf = new MusterijaForma (prodavnica, null);
				mf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String musterijaID = modelTabele.getValueAt(red, 0).toString();
					Musterija musterija = prodavnica.nadjiMusteriju(musterijaID);
					if(musterija == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja musterije sa ovim korisnickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						MusterijaForma mf = new MusterijaForma(prodavnica, musterija);
						mf.setVisible(true);
					}
				}
				
			}
		});
		
	}

}