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
import gui.FormaZaDodavanjeIIzmenu.AdminForma;
import korisnici.Admin;
import main.ProjekatMain;
import radnja.Prodavnica;

public class AdminiPrikaz extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable adminoviTabela;
	
	private Prodavnica prodavnica;
	
	public AdminiPrikaz(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Admini");
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG","Pol", "Adresa", "Broj Telefona", "Korisnicko Ime", "Lozinka", "Plata"};
		Object[][] sadrzaj = new Object[prodavnica.sviNeobrisaniAdmini().size()][zaglavlja.length];
		
		for(int i=0; i<prodavnica.sviNeobrisaniAdmini().size(); i++) {
			Admin admin = prodavnica.sviNeobrisaniAdmini().get(i);
			sadrzaj[i][0] = admin.getID();
			sadrzaj[i][1] = admin.getIme();
			sadrzaj[i][2] = admin.getPrezime();
			sadrzaj[i][3] = admin.getJMBG();
			sadrzaj[i][4] = admin.getPol();
			sadrzaj[i][5] = admin.getAdresa();
			sadrzaj[i][6] = admin.getBrojTelefona();
			sadrzaj[i][7] = admin.getKorisnickoIme();
			sadrzaj[i][8] = admin.getLozinka();
			sadrzaj[i][9] = admin.getPlata();
			
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		adminoviTabela = new JTable(tableModel);
		
		adminoviTabela.setRowSelectionAllowed(true);
		adminoviTabela.setColumnSelectionAllowed(false);
		adminoviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminoviTabela.setDefaultEditor(Object.class, null);
		adminoviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(adminoviTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String adminID = tableModel.getValueAt(red, 0).toString();
					Admin a = prodavnica.nadjiAdmin(adminID);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete admin?", 
							adminID + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						a.setObrisan(true);
						tableModel.removeRow(red);
						prodavnica.snimiAdmina(ProjekatMain.AUTOMOBILI_FAJL);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminForma df = new AdminForma(prodavnica, null);
				df.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String adminID = tableModel.getValueAt(red, 0).toString();
					Admin admin = prodavnica.nadjiAdmin(adminID);
					AdminForma df = new AdminForma(prodavnica, admin);
					df.setVisible(true);
				}
			}
		});
	}
}