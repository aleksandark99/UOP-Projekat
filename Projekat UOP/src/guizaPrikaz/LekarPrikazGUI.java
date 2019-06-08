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
import guiDodavanje.LekarDodavanjeGUI;
import guiDodavanje.SestraDodavanjeGUI;
import osobe.Lekar;
import osobe.Medicinska_Sestra;


public class LekarPrikazGUI extends JFrame {
	private JButton btnAddPac = new JButton();
	private JButton btnEditPac = new JButton();
	private JButton btnDeletePac = new JButton();
	
	
	private DefaultTableModel tableModel;
	private JTable pacijentTabela;
	
	private JToolBar mainToolbar = new JToolBar();

	
	private DomZdravlja domzdravlja;
	
	public  LekarPrikazGUI(DomZdravlja domzdravlja){
		this.domzdravlja=domzdravlja;
		setTitle("Prikaz Lekara" );
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime","JMBG","Pol","Adresa","Broj Telefona","Korisnicko ime","Lozinka","Sluzba","Plata","Specijalizacija"};
		
		
		
	    ArrayList<Lekar> lekarActive = new ArrayList<Lekar>();
	    for (Lekar lekar: domzdravlja.getLekare()) {
	    	if (lekar.isState()==true) {
	    		lekarActive.add(lekar);
	    	}
	    	else {
	    		System.out.println(lekar+"\n");
	    	}
	    }
	    Object[][] podaci = new Object[lekarActive.size()][zaglavlje.length];
	    System.out.println(lekarActive.size());
//		for(int i=0; i<this.domzdravlja.getPacijente().size(); i++) {
		for(int i=0; i<lekarActive.size(); i++) {
//			Pacijent pacijent = domzdravlja.getPacijente().get(i);
			Lekar lekar = lekarActive.get(i);
//			if (pacijent.isState()==true) {
			SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
			podaci[i][0] = lekar.getIme()  ;
			podaci[i][1] = lekar.getPrezime()  ;
			podaci[i][2] = lekar.getJmbg()  ;
			podaci[i][3] = lekar.getPol()  ;
			podaci[i][4] = lekar.getAdresa()  ;
			podaci[i][5] = lekar.getBrojTelefona()  ;
			podaci[i][6] = lekar.getKorisnickoIme()  ;
			podaci[i][7] = lekar.getLozinka()  ;
			podaci[i][8] = lekar.getSluzbaZaposlenog()  ;
			podaci[i][9] = lekar.getPlata();
			podaci[i][10] = lekar.getSpecijalizacija();
	
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
			LekarDodavanjeGUI ss= new LekarDodavanjeGUI(domzdravlja,null); // LEKAR DODAVANJE GUI
			ss.setVisible(true);/////////////////////////////////////////////////////////////////////METODE ZA DODAVANJE SESTRE
			LekarPrikazGUI.this.dispose();
			LekarPrikazGUI.this.setVisible(false);
				
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
					Lekar lekar = domzdravlja.nadjiLekara(id);
					if(lekar != null) {
						LekarDodavanjeGUI ss= new LekarDodavanjeGUI(domzdravlja,lekar); // LEKAR DODAVANJE GUI
						ss.setVisible(true);
						//
						LekarPrikazGUI.this.dispose();
						LekarPrikazGUI.this.setVisible(false);
						
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
					Lekar  lekar = domzdravlja.nadjiLekara(id);
					if(lekar != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete lekara?", lekar.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							tableModel= (DefaultTableModel) pacijentTabela.getModel();
							//DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
							if(lekar instanceof Lekar) {
								domzdravlja.obrisiLekara(lekar);
							}
							
							domzdravlja.snimiSestre("medicinskeSestre.txt");
							LekarPrikazGUI.this.dispose();
							LekarPrikazGUI.this.setVisible(false);
							LekarPrikazGUI ss= new LekarPrikazGUI(domzdravlja);
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
