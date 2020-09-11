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
import gui.FormaZaDodavanjeIIzmenu.KnjizicaForma;
import main.ProjekatMain;
import radnja.Prodavnica;
import servisi.Knjizica;

public class KnjizicePrikaz extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel modelTabele;
	private JTable knjiziceTabela;
	
	private Prodavnica prodavnica;
	
	public KnjizicePrikaz(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Servisne knjizice");
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
		
		String[] zaglavlje = new String[] {"ID", "Automobil"};
		Object[][] sadrzaj = new Object[prodavnica.sveNeobrisaneKnjizice().size()][zaglavlje.length];
		
		for(int i=0; i<prodavnica.sveNeobrisaneKnjizice().size(); i++) {
			Knjizica knjizice = prodavnica.sveNeobrisaneKnjizice().get(i);
			sadrzaj[i][0] = knjizice.getID();
			sadrzaj[i][1] = knjizice.getAutomobil();
		}
		
		modelTabele = new DefaultTableModel(sadrzaj, zaglavlje);
		knjiziceTabela = new JTable(modelTabele);
		
		knjiziceTabela.setRowSelectionAllowed(true);
		knjiziceTabela.setColumnSelectionAllowed(false);
		knjiziceTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjiziceTabela.setDefaultEditor(Object.class, null);
		knjiziceTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollpane = new JScrollPane(knjiziceTabela);
		add(scrollpane, BorderLayout.CENTER);
		
		
		
	}
	
	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red iz tabele.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = modelTabele.getValueAt(red, 0).toString();
					Knjizica knjizica = prodavnica.nadjiKnjizicu(korisnickoIme);
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelize da obrisete servis?", korisnickoIme + "-potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						knjizica.setObrisan(true);
						modelTabele.removeRow(red);
						prodavnica.snimiServis(ProjekatMain.SERVISI_FAJL);
					}
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				KnjizicaForma kf = new KnjizicaForma(prodavnica, null);
				kf.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = modelTabele.getValueAt(red, 0).toString();
					Knjizica knjizica = prodavnica.nadjiKnjizicu(korisnickoIme);
					if(knjizica == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja servisa sa ovim serviserom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						KnjizicaForma kdi = new KnjizicaForma(prodavnica, knjizica);
						kdi.setVisible(true);
					}
				}
				
			}
		});
		
	}

}