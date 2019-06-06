package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import domZdravlja.DomZdravlja;
import osobe.Medicinska_Sestra;

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
	
	public GlavniProzorSestre(DomZdravlja domZdravlja,Medicinska_Sestra prijavljenaSestra) {
		this.domzdravlja=domZdravlja;
		this.sestra=prijavljenaSestra;
		setTitle("Dom Zdravlja - " + prijavljenaSestra.getKorisnickoIme());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initMenu();
		initActions();
		
		
	
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
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	
	
	
	
	
	
	
	
}
