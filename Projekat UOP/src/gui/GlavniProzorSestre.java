package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import domZdravlja.DomZdravlja;
import guiDodavanje.PacijentDodavanjeGUI;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;

public class GlavniProzorSestre extends JFrame {

	private JMenuBar mainMenu;
	
	private JMenu pacijentiMenu;
	private JMenu sestreMenu;
	private JMenu lekariMenu;
	private JMenu preglediMenu;
	
	private JMenuItem pacijentiItem;
	private JMenuItem sestreItem; 
	private JMenuItem lekariItem;
	private JMenuItem preglediItem;
	
	private DomZdravlja domzdravlja;
	private Medicinska_Sestra sestra;
	
	///
	private JButton btnAddPac = new JButton();
	private JButton btnEditPac = new JButton();
	private JButton btnDeletePac = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable pacijentTabela;
	
	private JToolBar mainToolbar = new JToolBar();

	//
	
	public GlavniProzorSestre(DomZdravlja domZdravlja,Medicinska_Sestra prijavljenaSestra) {
		this.domzdravlja=domZdravlja;
		this.sestra=prijavljenaSestra;
		setTitle("Dom Zdravlja - " + prijavljenaSestra.getKorisnickoIme());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		initMenu();
		initActions();
		initbtnActions();
		
		
	
	}
	
	
	private void initMenu() {
		this.mainMenu = new JMenuBar();
		this.pacijentiMenu = new JMenu("Pacijenti");
		this.sestreMenu = new JMenu("Sestre");
		this.lekariMenu = new JMenu("Lekari");
		this.preglediMenu=new JMenu("Pregledi");
		
		this.pacijentiItem = new JMenuItem("Prikazi pacijente");
		this.lekariItem = new JMenuItem("Prikazi lekare");
		this.sestreItem = new JMenuItem("Prikazi sestre");
		this.preglediItem = new JMenuItem("Prikazi prglede");
		
		this.pacijentiMenu.add(pacijentiItem);
		this.sestreMenu.add(sestreItem);
		this.lekariMenu.add(lekariItem);
		this.preglediMenu.add(preglediItem);
		
		this.mainMenu.add(pacijentiMenu);
		this.mainMenu.add(preglediMenu);
		this.mainMenu.add(lekariMenu);
		this.mainMenu.add(sestreMenu);
		
		setJMenuBar(this.mainMenu);
		
	}
	private void initActions() {
		pacijentiItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentGUI();
				
			}
		});
		
		
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime","JMBG","Pol","Adresa","Broj Telefona","Korisnicko ime","Lozinka","Izabrani lekar"};
		Object[][] podaci = new Object[this.domzdravlja.getPacijente().size()][zaglavlje.length];
		
		for(int i=0; i<this.domzdravlja.getPacijente().size(); i++) {

			Pacijent pacijent = domzdravlja.getPacijente().get(i);


			podaci[i][0] = pacijent.getIme()  ;
			podaci[i][1] = pacijent.getPrezime()  ;
			podaci[i][2] = pacijent.getJmbg()  ;
			podaci[i][3] = pacijent.getPol()  ;
			podaci[i][4] = pacijent.getAdresa()  ;
			podaci[i][5] = pacijent.getBrojTelefona()  ;
			podaci[i][6] = pacijent.getKorisnickoIme()  ;
			podaci[i][7] = pacijent.getLozinka()  ;
			podaci[i][8] = pacijent.getIzabraniLekar().getKorisnickoIme()  ;
		}
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		pacijentTabela = new JTable(tableModel);
		pacijentTabela.setRowSelectionAllowed(true);
		pacijentTabela.setColumnSelectionAllowed(false);
		pacijentTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pacijentTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(pacijentTabela);
		add(scrollPane, BorderLayout.CENTER);
		
		revalidate();
	}
	
	private void initbtnActions() {
		btnAddPac.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			PacijentDodavanjeGUI pacADD= new PacijentDodavanjeGUI(domzdravlja,null);
			pacADD.setVisible(true);
				
			}
		});
	}
	
	
	
	
	
}
