package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import domZdravlja.DomZdravlja;
import guizaPrikaz.PreglediPrikazGUI;
import guizaPrikaz.PrikazLekarovihPregleda;
import guizaPrikaz.PrikazPacijentovihPregleda;
import osobe.Lekar;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;

public class GlavniProzorLekara extends JFrame {
	
	private DomZdravlja domzdravlja;
	private Pacijent pacijent;
	private Lekar lekar;
	private JMenuBar mainMenu;
	private JMenu preglediMenu;
	private JMenuItem preglediItem;
	
	
	public GlavniProzorLekara(DomZdravlja domZdravlja,Lekar lekar) {
		this.domzdravlja=domZdravlja;
		this.lekar=lekar;
		setTitle("Dom Zdravlja - " + lekar.getKorisnickoIme());
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
				PrikazLekarovihPregleda ss = new PrikazLekarovihPregleda(domzdravlja,lekar);
				
				ss.setVisible(true);
				
			}
		});
	}
	
	
	
	
	
	

}