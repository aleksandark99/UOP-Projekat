package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import domZdravlja.DomZdravlja;
import guizaPrikaz.PreglediPrikazGUI;
import guizaPrikaz.PrikazPacijentovihPregleda;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;

public class GlavniProzorPacijenta extends JFrame {
	
	private DomZdravlja domzdravlja;
	private Pacijent pacijent;
	private JMenuBar mainMenu;
	private JMenu preglediMenu;
	private JMenuItem preglediItem;
	
	
	public GlavniProzorPacijenta(DomZdravlja domZdravlja,Pacijent prijavljeniPacijent) {
		this.domzdravlja=domZdravlja;
		this.pacijent=prijavljeniPacijent;
		setTitle("Dom Zdravlja - " + prijavljeniPacijent.getKorisnickoIme());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		this.mainMenu = new JMenuBar();

		this.preglediMenu=new JMenu("Pregledi");
		

		this.preglediItem = new JMenuItem("Prikazi prglede");

		this.preglediMenu.add(preglediItem);
		

		this.mainMenu.add(preglediMenu);

		
		setJMenuBar(this.mainMenu);
		
	}
	private void initActions() {
		preglediItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPacijentovihPregleda ss = new PrikazPacijentovihPregleda(domzdravlja,pacijent);
				
				ss.setVisible(true);
				
			}
		});
	}
	
	
	
	
	
	

}
