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
import guiDodavanje.PregledDodavanjeGUI;
import guiDodavanje.SestraDodavanjeGUI;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;
import pregled.Pregled;
import pregled.StatusPregleda;
import zdravstvena_knjizica.KategorijaOsiguranja;

public class PreglediPrikazGUI extends JFrame {
	private JButton btnAddPac = new JButton();
	private JButton btnEditPac = new JButton();
	private JButton btnDeletePac = new JButton();
	private JButton btnRacun = new JButton();
	
	
	private DefaultTableModel tableModel;
	private JTable pacijentTabela;
	
	private JToolBar mainToolbar = new JToolBar();

	
	private DomZdravlja domzdravlja;
	
	public  PreglediPrikazGUI(DomZdravlja domzdravlja){
		this.domzdravlja=domzdravlja;
		setTitle("Prikaz pregleda" );
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		PregledGUI();
		initbtnActions();
		
	}
	private void PregledGUI() {

		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAddPac.setIcon(addIcon);
		mainToolbar.add(btnAddPac);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEditPac.setIcon(editIcon);
		mainToolbar.add(btnEditPac);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDeletePac.setIcon(deleteIcon);
		mainToolbar.add(btnDeletePac);
		ImageIcon racunIcon =new ImageIcon(getClass().getResource("/slike/racun.gif.png")); 
		btnRacun.setIcon(racunIcon);
		mainToolbar.add(btnRacun);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Ime pacijenta","Prezime pacijenta", "Ime Lekara","Prezime lekara","Termin","Soba","Opis","Status","Broj"};	
	    ArrayList<Pregled> pregledActive = new ArrayList<Pregled>();
	    for (Pregled pregled: domzdravlja.getPreglede()) {
	    	if (pregled.isState()==true) {
	    		pregledActive.add(pregled);
	    	}
	    	else {
	    		System.out.println(pregled+"\n");
	    	}
	    }
	    Object[][] podaci = new Object[pregledActive.size()][zaglavlje.length];
	    System.out.println(pregledActive.size());
		for(int i=0; i<pregledActive.size(); i++) {
			Pregled pregled = pregledActive.get(i);
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			podaci[i][0] = pregled.getPacijent().getKorisnickoIme();
			podaci[i][1] =  pregled.getPacijent().getPrezime()  ;
			podaci[i][2] = pregled.getLekar().getIme()  ;
			podaci[i][3] = pregled.getLekar().getPrezime()  ;
			podaci[i][4] = sdf.format(pregled.getTermin() ) ;
			podaci[i][5] = pregled.getSoba()  ;
			podaci[i][6] = pregled.getKratak_opis()  ;
//			podaci[i][7] = pregled.getRacun() ;
			podaci[i][7] = pregled.getStatus() ;
			podaci[i][8] = pregled.getBroj();

	
		}
		pacijentTabela = new JTable();
		tableModel= (DefaultTableModel) pacijentTabela.getModel();
		tableModel.setDataVector(podaci, zaglavlje);	
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
				PregledDodavanjeGUI ss= new PregledDodavanjeGUI(domzdravlja,null);
				ss.setVisible(true);/////////////////////////////////////////////////////////////////////METODE ZA DODAVANJE SESTRE
				PreglediPrikazGUI.this.dispose();
				PreglediPrikazGUI.this.setVisible(false);
				
			}
		});
		btnEditPac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pacijentTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = pacijentTabela.getValueAt(red, 8).toString();
					System.out.println(id);
//					Pacijent pacijent = domzdravlja.nadjiPacijenta(id);
					Pregled pregled = domzdravlja.nadjiPregled(Integer.parseInt(id));
					if(pregled != null && pregled.getStatus()!=StatusPregleda.otkazan&& pregled.getStatus()!=StatusPregleda.zavrsen ) {
						PregledDodavanjeGUI pacADD= new PregledDodavanjeGUI(domzdravlja,pregled);
						pacADD.setVisible(true);
						//
						PreglediPrikazGUI.this.dispose();
						PreglediPrikazGUI.this.setVisible(false);
						
						//
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce promeniti pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					String id = pacijentTabela.getValueAt(red, 8).toString();
					Pregled pregled = domzdravlja.nadjiPregled(Integer.parseInt(id));
					if(pregled != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete pacijenta?",pregled.getBroj() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							tableModel= (DefaultTableModel) pacijentTabela.getModel();
							//DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
							if(pregled instanceof Pregled) {
								domzdravlja.obrisiPreglede(pregled);
							}
							domzdravlja.snimiPreglede("pregledi.txt");
							PreglediPrikazGUI.this.dispose();
							PreglediPrikazGUI.this.setVisible(false);
							PreglediPrikazGUI pp=new PreglediPrikazGUI(domzdravlja);
							pp.setVisible(true);
						
						//
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				}
			}
		});
		btnRacun.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pacijentTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = pacijentTabela.getValueAt(red, 8).toString();
					Pregled pregled = domzdravlja.nadjiPregled(Integer.parseInt(id));
					if(pregled != null && pregled.getStatus()==StatusPregleda.zavrsen) {
//						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete pacijenta?",pregled.getBroj() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
//						if(izbor == JOptionPane.YES_OPTION) {
							tableModel= (DefaultTableModel) pacijentTabela.getModel();
							//DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
//							if(pregled instanceof Pregled) {
//								domzdravlja.obrisiPreglede(pregled);
//							}
//							domzdravlja.snimiPreglede("pregledi.txt");
//							PreglediPrikazGUI.this.dispose();
//							PreglediPrikazGUI.this.setVisible(false);
//							PreglediPrikazGUI pp=new PreglediPrikazGUI(domzdravlja);
//							pp.setVisible(true);
							int racun =0;
							if(pregled.getPacijent().getKnjizica().getKategorijaOsiguranja()==KategorijaOsiguranja.prva) {
								racun=300;
								
							}else if(pregled.getPacijent().getKnjizica().getKategorijaOsiguranja()==KategorijaOsiguranja.druga) {
								racun=50;
							}
							PrikazRacunaGUI ss = new PrikazRacunaGUI(pregled.getPacijent().getIme(), pregled.getPacijent().getPrezime(),
									pregled.getBroj(), racun);
							ss.setVisible(true);
						
						//
//					}
//						else {
//						JOptionPane.showMessageDialog(null, "Nije moguce prikazati racun!", "Greska", JOptionPane.ERROR_MESSAGE);
//					}
				}
					else {
						JOptionPane.showMessageDialog(null, "Nije moguce prikazati racun!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	
		
	
	
	
}
}
