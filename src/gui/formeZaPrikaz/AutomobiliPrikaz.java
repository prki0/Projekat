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
import automobili.Automobil;
import gui.FormaZaDodavanjeIIzmenu.AutomobilForma;
import main.ProjekatMain;
import radnja.Prodavnica;

public class AutomobiliPrikaz extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable automobiloviTabela;
	
	private Prodavnica prodavnica;
	
	public AutomobiliPrikaz(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Automobili");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
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
		
		String[] zaglavlja = new String[] {"ID", "Marka", "Model", "Godina Proizvodnje","Zapremina Motora", "Snaga Motora", "Tip Goriva"};
		Object[][] sadrzaj = new Object[prodavnica.sviNeobrisaniAutomobili().size()][zaglavlja.length];
		
		for(int i=0; i<prodavnica.sviNeobrisaniAutomobili().size(); i++) {
			Automobil automobil = prodavnica.sviNeobrisaniAutomobili().get(i);
			sadrzaj[i][0] = automobil.getID();
			sadrzaj[i][1] = automobil.getMarka();
			sadrzaj[i][2] = automobil.getModel();
			sadrzaj[i][3] = automobil.getGodinaProizvodnje();
			sadrzaj[i][4] = automobil.getZapreminaMotora();
			sadrzaj[i][5] = automobil.getSnagaMotora();
			sadrzaj[i][6] = automobil.getGorivo();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		automobiloviTabela = new JTable(tableModel);
		
		automobiloviTabela.setRowSelectionAllowed(true);
		automobiloviTabela.setColumnSelectionAllowed(false);
		automobiloviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		automobiloviTabela.setDefaultEditor(Object.class, null);
		automobiloviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(automobiloviTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String automobilID = tableModel.getValueAt(red, 0).toString();
					Automobil automobil = prodavnica.nadjiAutomobil(automobilID);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete automobil?", 
							automobilID + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						automobil.setObrisan(true);
						tableModel.removeRow(red);
						prodavnica.snimiAutomobili(ProjekatMain.AUTOMOBILI_FAJL);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobilForma df = new AutomobilForma(prodavnica, null);
				df.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String automobilID = tableModel.getValueAt(red, 0).toString();
					Automobil automobil = prodavnica.nadjiAutomobil(automobilID);
					AutomobilForma df = new AutomobilForma(prodavnica, automobil);
					df.setVisible(true);
				}
			}
		});
	}
}