package guizaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import osobe.Pacijent;
import domZdravlja.DomZdravlja;
import guiDodavanje.PacijentDodavanjeGUI;


public class PacijentPrikazGUI extends JFrame {
	private JButton btnAddPac = new JButton();
	private JButton btnEditPac = new JButton();
	private JButton btnDeletePac = new JButton();
	
	
	private DefaultTableModel tableModel;
	private JTable pacijentTabela;
	
	private JToolBar mainToolbar = new JToolBar();

	
	private DomZdravlja domzdravlja;
	
	public  PacijentPrikazGUI(DomZdravlja domzdravlja){
		this.domzdravlja=domzdravlja;
		setTitle("Prikaz Pacijenata" );
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		PacijentGUI();
		initbtnActions();
		
	}
	
	
	
	
	
	
	private void PacijentGUI() {

		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAddPac.setIcon(addIcon);
		mainToolbar.add(btnAddPac);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEditPac.setIcon(editIcon);
		mainToolbar.add(btnEditPac);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDeletePac.setIcon(deleteIcon);
		mainToolbar.add(btnDeletePac);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Ime", "Prezime","JMBG","Pol","Adresa","Broj Telefona","Korisnicko ime","Lozinka","Izabrani lekar","Broj knjizice","Datum isteka knjizice","Kategorija osiguranja"};
		
		
		
	    ArrayList<Pacijent> pacijentiActive = new ArrayList<Pacijent>();
	    for (Pacijent pacijent: domzdravlja.getPacijente()) {
	    	if (pacijent.isState()==true) {
	    		pacijentiActive.add(pacijent);
	    	}
	    	else {
	    		System.out.println(pacijent+"\n");
	    	}
	    }
	    Object[][] podaci = new Object[pacijentiActive.size()][zaglavlje.length];
	    System.out.println(pacijentiActive.size());
//		for(int i=0; i<this.domzdravlja.getPacijente().size(); i++) {
		for(int i=0; i<pacijentiActive.size(); i++) {
//			Pacijent pacijent = domzdravlja.getPacijente().get(i);
			Pacijent pacijent = pacijentiActive.get(i);
//			if (pacijent.isState()==true) {
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			podaci[i][0] = pacijent.getIme()  ;
			podaci[i][1] = pacijent.getPrezime()  ;
			podaci[i][2] = pacijent.getJmbg()  ;
			podaci[i][3] = pacijent.getPol()  ;
			podaci[i][4] = pacijent.getAdresa()  ;
			podaci[i][5] = pacijent.getBrojTelefona()  ;
			podaci[i][6] = pacijent.getKorisnickoIme()  ;
			podaci[i][7] = pacijent.getLozinka()  ;
			podaci[i][8] = pacijent.getIzabraniLekar().getKorisnickoIme()  ;
			podaci[i][9] = pacijent.getKnjizica().getBroj();
			podaci[i][10] =sdf.format( pacijent.getKnjizica().getDatumIsteka());
			podaci[i][11] = pacijent.getKnjizica().getKategorijaOsiguranja();
		}
		
//		pacijentTabela = new JTable(tableModel);
		
		pacijentTabela = new JTable();
		tableModel= (DefaultTableModel) pacijentTabela.getModel();
		tableModel.setDataVector(podaci, zaglavlje);
/////////////////////////
//		for (int i=0; i<podaci.length ;i++) {
//			System.out.println(podaci[i][0]);
//			if (podaci[i][0]==null){
//				this.tableModel.removeRow(i);
//			}
//		}
//		
		
		
		/////////////////////

		
		
		
		pacijentTabela.setRowSelectionAllowed(true);
		pacijentTabela.setColumnSelectionAllowed(false);
		pacijentTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pacijentTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(pacijentTabela);
		add(scrollPane, BorderLayout.CENTER);
		
		
		revalidate();
		repaint();
	}
	
	
	
	private void initbtnActions() {
		btnAddPac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			PacijentDodavanjeGUI pacADD= new PacijentDodavanjeGUI(domzdravlja,null);
			pacADD.setVisible(true);
			PacijentPrikazGUI.this.dispose();
			PacijentPrikazGUI.this.setVisible(false);
				
			}
		});
		btnEditPac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pacijentTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = pacijentTabela.getValueAt(red, 6).toString();
					System.out.println(id);
					Pacijent pacijent = domzdravlja.nadjiPacijenta(id);
					if(pacijent != null) {
						PacijentDodavanjeGUI pacADD= new PacijentDodavanjeGUI(domzdravlja,pacijent);
						pacADD.setVisible(true);
						//
						PacijentPrikazGUI.this.dispose();
						PacijentPrikazGUI.this.setVisible(false);
						
						//
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani artikal!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDeletePac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pacijentTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = pacijentTabela.getValueAt(red, 6).toString();
					Pacijent pacijent = domzdravlja.nadjiPacijenta(id);
					if(pacijent != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete pacijenta?", pacijent.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							tableModel= (DefaultTableModel) pacijentTabela.getModel();
							//DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
							if(pacijent instanceof Pacijent) {
								domzdravlja.obrisiPacijenta(pacijent);
							}
							
							domzdravlja.snimiPacijente("pacijenti.txt");
							domzdravlja.snimiKnjizice("knjizice.txt");
							PacijentPrikazGUI.this.dispose();
							PacijentPrikazGUI.this.setVisible(false);
							PacijentPrikazGUI ss= new PacijentPrikazGUI(domzdravlja);
							ss.setVisible(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}
	
	
	
	
	
}
