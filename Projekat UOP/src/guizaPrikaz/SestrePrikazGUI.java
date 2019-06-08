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

import domZdravlja.DomZdravlja;
import guiDodavanje.PacijentDodavanjeGUI;
import guiDodavanje.SestraDodavanjeGUI;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;

public class SestrePrikazGUI extends JFrame {
	private JButton btnAddPac = new JButton();
	private JButton btnEditPac = new JButton();
	private JButton btnDeletePac = new JButton();
	
	
	private DefaultTableModel tableModel;
	private JTable pacijentTabela;
	
	private JToolBar mainToolbar = new JToolBar();

	
	private DomZdravlja domzdravlja;
	
	public  SestrePrikazGUI(DomZdravlja domzdravlja){
		this.domzdravlja=domzdravlja;
		setTitle("Prikaz Pacijenata" );
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		SestraGUI();
		initbtnActions();
		
	}
	
	
	
	
	
	
	private void SestraGUI() {

		
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime","JMBG","Pol","Adresa","Broj Telefona","Korisnicko ime","Lozinka","Sluzba","Plata"};
		
		
		
	    ArrayList<Medicinska_Sestra> sestraActive = new ArrayList<Medicinska_Sestra>();
	    for (Medicinska_Sestra sestra: domzdravlja.getSestre()) {
	    	if (sestra.isState()==true) {
	    		sestraActive.add(sestra);
	    	}
	    	else {
	    		System.out.println(sestra+"\n");
	    	}
	    }
	    Object[][] podaci = new Object[sestraActive.size()][zaglavlje.length];
	    System.out.println(sestraActive.size());
//		for(int i=0; i<this.domzdravlja.getPacijente().size(); i++) {
		for(int i=0; i<sestraActive.size(); i++) {
//			Pacijent pacijent = domzdravlja.getPacijente().get(i);
			Medicinska_Sestra sestra = sestraActive.get(i);
//			if (pacijent.isState()==true) {
			SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
			podaci[i][0] = sestra.getIme()  ;
			podaci[i][1] = sestra.getPrezime()  ;
			podaci[i][2] = sestra.getJmbg()  ;
			podaci[i][3] = sestra.getPol()  ;
			podaci[i][4] = sestra.getAdresa()  ;
			podaci[i][5] = sestra.getBrojTelefona()  ;
			podaci[i][6] = sestra.getKorisnickoIme()  ;
			podaci[i][7] = sestra.getLozinka()  ;
			podaci[i][8] = sestra.getSluzbaZaposlenog()  ;
			podaci[i][9] = sestra.getPlata();
	
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
			SestraDodavanjeGUI ss= new SestraDodavanjeGUI(domzdravlja,null);
			ss.setVisible(true);/////////////////////////////////////////////////////////////////////METODE ZA DODAVANJE SESTRE
			SestrePrikazGUI.this.dispose();
			SestrePrikazGUI.this.setVisible(false);
				
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
					Medicinska_Sestra sestra = domzdravlja.nadjiSestru(id);
					if(sestra != null) {
						SestraDodavanjeGUI ss= new SestraDodavanjeGUI(domzdravlja,sestra);
						ss.setVisible(true);
						//
						SestrePrikazGUI.this.dispose();
						SestrePrikazGUI.this.setVisible(false);
						
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
//					Pacijent pacijent = domzdravlja.nadjiPacijenta(id);
					Medicinska_Sestra sestra = domzdravlja.nadjiSestru(id);
					if(sestra != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete pacijenta?", sestra.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							tableModel= (DefaultTableModel) pacijentTabela.getModel();
							//DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
							if(sestra instanceof Medicinska_Sestra) {
								domzdravlja.obrisiSestru(sestra);
							}
							
							domzdravlja.snimiSestre("medicinskeSestre.txt");
							SestrePrikazGUI.this.dispose();
							SestrePrikazGUI.this.setVisible(false);
							SestrePrikazGUI ss= new SestrePrikazGUI(domzdravlja);
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
