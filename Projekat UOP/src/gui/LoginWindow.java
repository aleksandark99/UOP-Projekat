package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import net.miginfocom.swing.MigLayout;
import osobe.Lekar;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;

public class LoginWindow extends JFrame {
	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOK;
	private JButton btnCancel;
	
	private JLabel lblTipKorisnika;

	private JComboBox<String> cTipKorisnika;

	
	private DomZdravlja domzdravlja;
	
	
	public LoginWindow (DomZdravlja domZdravlja) {
		this.domzdravlja=domZdravlja;
		setTitle("Prijava");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}

	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		
		this.lblPoruka = new JLabel("Dobrodošli. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisničko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Šifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		this.lblTipKorisnika= new JLabel("Tip korisnika:");
		
		cTipKorisnika = new JComboBox<>();
		cTipKorisnika.addItem("Sestra");
		cTipKorisnika.addItem("Lekar");
		cTipKorisnika.addItem("Pacijent");


		this.getRootPane().setDefaultButton(btnOK);
		
		
		add(lblPoruka, "span 2");// span 2 dodati za izabir korisnika
		add(lblTipKorisnika);
		add(cTipKorisnika);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
	}
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword()).trim();
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");
				}else {
				if (cTipKorisnika.getSelectedItem()=="Sestra") {
					for(Medicinska_Sestra sestra: domzdravlja.getSestre()) {
						if(sestra.isState()==true && sestra.getKorisnickoIme().equals(korisnickoIme) && sestra.getLozinka().equals(sifra)) {
							// Uspesno logovanje:
							// 1. Sakrijemo login prozor
							GlavniProzorSestre gpSestre = new GlavniProzorSestre(domzdravlja,sestra);
							gpSestre.setVisible(true);
						
							LoginWindow.this.setVisible(false);
							LoginWindow.this.dispose();
						}
					}
					
					
				}
				else if (cTipKorisnika.getSelectedItem()=="Pacijent") {
					for(Pacijent pacijent: domzdravlja.getPacijente()) {
						if(pacijent.isState()==true && pacijent.getKorisnickoIme().equals(korisnickoIme) && pacijent.getLozinka().equals(sifra)) {
							// Uspesno logovanje:
							// 1. Sakrijemo login prozor
							GlavniProzorPacijenta gpPac = new GlavniProzorPacijenta(domzdravlja,pacijent);
							gpPac.setVisible(true);
						
							LoginWindow.this.setVisible(false);
							LoginWindow.this.dispose();
						}
					}
					
					
				}
				else if (cTipKorisnika.getSelectedItem()=="Lekar") {
					for(Lekar lekar: domzdravlja.getLekare()) {
						if(lekar.isState()==true && lekar.getKorisnickoIme().equals(korisnickoIme) && lekar.getLozinka().equals(sifra)) {
							// Uspesno logovanje:
							// 1. Sakrijemo login prozor
							GlavniProzorLekara gpLek = new GlavniProzorLekara();
							gpLek.setVisible(true);
						
							LoginWindow.this.setVisible(false);
							LoginWindow.this.dispose();
						}
					}
				}
					
					
				
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginWindow.this.setVisible(false);
				LoginWindow.this.dispose();
			}
		});
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
