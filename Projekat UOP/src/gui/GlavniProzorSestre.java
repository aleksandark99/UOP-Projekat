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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import domZdravlja.DomZdravlja;
import guiDodavanje.PacijentDodavanjeGUI;
import guizaPrikaz.LekarPrikazGUI;
import guizaPrikaz.PacijentPrikazGUI;
import guizaPrikaz.PreglediPrikazGUI;
import guizaPrikaz.SestrePrikazGUI;
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
	//	initbtnActions();
		
		
	
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
				PacijentPrikazGUI pp= new PacijentPrikazGUI(domzdravlja);
				pp.setVisible(true);
			}
		});
		sestreItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SestrePrikazGUI ss= new SestrePrikazGUI(domzdravlja);
				ss.setVisible(true);
				
			}
		});
		lekariItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LekarPrikazGUI ss= new LekarPrikazGUI(domzdravlja);
				ss.setVisible(true);
				
			}
		});
		preglediItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PreglediPrikazGUI ss= new PreglediPrikazGUI(domzdravlja);
				ss.setVisible(true);
				
			}
		});
		
		
		
		
	}
	

	
	
}
