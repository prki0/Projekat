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
import automobili.Delovi;
import gui.FormaZaDodavanjeIIzmenu.DeoForma;
import main.ProjekatMain;
import radnja.Prodavnica;

public class DeloviPrikaz extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel modelTabele;
	private JTable deloviTabela;
	
	private Prodavnica prodavnica;
	
	public DeloviPrikaz(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Delovi");
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
		
		String[] zaglavlje = new String[] {"ID", "Marka", "Model", "Naziv", "Cena"};
		Object[][] sadrzaj = new Object[prodavnica.sviNeobrisaniDelovi().size()][zaglavlje.length];
		
		for(int i=0; i<prodavnica.sviNeobrisaniDelovi().size(); i++) {
			Delovi delovi = prodavnica.sviNeobrisaniDelovi().get(i);
			sadrzaj[i][0] = delovi.getID();
			sadrzaj[i][1] = delovi.getMarka();
			sadrzaj[i][2] = delovi.getModel();
			sadrzaj[i][3] = delovi.getNaziv();
			sadrzaj[i][4] = delovi.getCena();
			
		}
		
		modelTabele = new DefaultTableModel(sadrzaj, zaglavlje);
		deloviTabela = new JTable(modelTabele);
		
		deloviTabela.setRowSelectionAllowed(true);
		deloviTabela.setColumnSelectionAllowed(false);
		deloviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deloviTabela.setDefaultEditor(Object.class, null);
		deloviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollpane = new JScrollPane(deloviTabela);
		add(scrollpane, BorderLayout.CENTER);
		
		
	}
	
	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red iz tabele.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String ID = modelTabele.getValueAt(red, 0).toString();
					Delovi delovi = prodavnica.nadjiDeo(ID);
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelize da obrisete deo?", ID + "-potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						delovi.setObrisan(true);
						modelTabele.removeRow(red);
						prodavnica.snimiDeo(ProjekatMain.DELOVI_FAJL);
					}
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DeoForma df = new DeoForma(prodavnica, null);
				df.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String ID = modelTabele.getValueAt(red, 0).toString();
					Delovi delovi = prodavnica.nadjiDeo(ID);
					if(delovi == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja dela sa ovim identifikacionim kodom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						DeoForma df = new DeoForma(prodavnica, delovi);
						df.setVisible(true);
					}
				}
				
			}
		});
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}