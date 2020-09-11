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
import gui.FormaZaDodavanjeIIzmenu.ServiserForma;
import korisnici.Serviser;
import main.ProjekatMain;
import radnja.Prodavnica;


public class ServiseriPrikaz extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel modelTabele;
	private JTable serviseriTabela;
	
	private Prodavnica prodavnica;
	
	public ServiseriPrikaz(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Serviseri");
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
		
		String[] zaglavlje = new String[] {"ID", "Ime", "Prezime","JMBG", "Pol","Adresa","Broj Telefona", "Korisnicko ime", "Lozinka", "Plata"};
		Object[][] sadrzaj = new Object[prodavnica.sviNeobrisaniServiseri().size()][zaglavlje.length];
		
		for(int i=0; i<prodavnica.sviNeobrisaniServiseri().size(); i++) {
			Serviser serviser = prodavnica.sviNeobrisaniServiseri().get(i);
			sadrzaj[i][0] = serviser.getID();
			sadrzaj[i][1] = serviser.getIme();
			sadrzaj[i][2] = serviser.getPrezime();
			sadrzaj[i][3] = serviser.getJMBG();
			sadrzaj[i][4] = serviser.getPol();
			sadrzaj[i][5] = serviser.getAdresa();
			sadrzaj[i][6] = serviser.getBrojTelefona();
			sadrzaj[i][7] = serviser.getKorisnickoIme();
			sadrzaj[i][8] = serviser.getLozinka();
			sadrzaj[i][9] = serviser.getPlata();
		}
		
		modelTabele = new DefaultTableModel(sadrzaj, zaglavlje);
		serviseriTabela = new JTable(modelTabele);
		
		serviseriTabela.setRowSelectionAllowed(true);
		serviseriTabela.setColumnSelectionAllowed(false);
		serviseriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serviseriTabela.setDefaultEditor(Object.class, null);
		serviseriTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollpane = new JScrollPane(serviseriTabela);
		add(scrollpane, BorderLayout.CENTER);
		
		
		
	}
	
	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red iz tabele.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = modelTabele.getValueAt(red, 4).toString();
					Serviser serviser = prodavnica.nadjiServiser(korisnickoIme);
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelize da obrisete servisera?", korisnickoIme + "-potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						serviser.setObrisan(true);
						modelTabele.removeRow(red);
						prodavnica.snimiServisera(ProjekatMain.SERVISERI_FAJL);
					}
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ServiserForma sf = new ServiserForma(prodavnica, null);
				sf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String serviserID = modelTabele.getValueAt(red, 0).toString();
					Serviser serviser = prodavnica.nadjiServiser(serviserID);
					if(serviser == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja servisera sa ovim korisnickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						ServiserForma sf = new ServiserForma(prodavnica, serviser);
						sf.setVisible(true);
					}
				}
				
			}
		});
		
	}

}