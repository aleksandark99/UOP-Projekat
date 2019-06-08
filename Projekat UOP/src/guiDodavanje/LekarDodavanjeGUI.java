package guiDodavanje;

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
import guizaPrikaz.LekarPrikazGUI;
import guizaPrikaz.SestrePrikazGUI;
import net.miginfocom.swing.MigLayout;
import osobe.Lekar;
import osobe.Medicinska_Sestra;
import osobe.Sluzba;
import osobe.Uloga;

public class LekarDodavanjeGUI extends JFrame {
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);

	private JLabel lblPol = new JLabel("Pol");
	private JTextField txtpol = new JTextField(20);
	
	
	private JLabel lblAdresa  = new JLabel("Adreasa");
	private JTextField txtAdresa = new JTextField(20);
	
	private JLabel lblSpecijalizacija  = new JLabel("Specijalizacija ");
	private JTextField txtSpecijalizacija  = new JTextField(20);
	
	private JLabel lblBrojTelefona  = new JLabel("Broj Telefona");
	private JTextField txtBrojTelefona  = new JTextField(20);
	

	
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	

	
	private JLabel lblplata= new JLabel("plata");
	private JTextField txtplata= new JTextField(20);
	
	private JLabel lblSluzba = new JLabel("Sluzba");
	private JComboBox<String> cbSluzba; 
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	
	private DomZdravlja domzravlja;
	private Lekar lekar;
	
	public LekarDodavanjeGUI(DomZdravlja domzravlja,Lekar lekar) {
		
		this.domzravlja = domzravlja;
		this.lekar = lekar;
		if(lekar != null) {
			setTitle(lekar.getIme() + " - Izmena podataka");
		}else {
			setTitle("Dodavanje Lekara");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		
	}
	
	private void initGUI(){
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		cbSluzba = new JComboBox<>();
		cbSluzba.addItem(String.valueOf(Sluzba.sluzbaOpsteMedicine));

		cbSluzba.addItem(String.valueOf(Sluzba.SluzbaZdravstveneZastiteDece));
		cbSluzba.addItem(String.valueOf(Sluzba.sluzbaZdravstvneneZastiteRadnika));
		cbSluzba.addItem(String.valueOf(Sluzba.stomatoloskaSluzba));
		
		if(lekar != null) {
			popuniPolja();
		}else {
			txtKorisnickoIme.setEnabled(true);
		

		}
		add(lblIme);  add(txtIme);
		add(lblPrezime);  add(txtPrezime);
		add(lblJMBG);  add(txtJMBG);
		add(lblPol);  add(txtpol);
		add(lblAdresa);  add(txtAdresa);
		add(lblBrojTelefona);  add(txtBrojTelefona);
		add(lblKorisnickoIme);  add(txtKorisnickoIme);
		add(lblLozinka);  add(pfLozinka);
		add(lblplata);   add(txtplata);
//		add(lblBrojKnjizice);  add(txtBrojKnjizice);
		add(lblSpecijalizacija); add(txtSpecijalizacija);
		add(lblSluzba); add(cbSluzba);
		add(btnOk); add(btnCancel);
		
		this.getRootPane().setDefaultButton(btnOk);
		
	}
	
	private void initActions(){
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String ime =txtIme.getText().trim();
					String prezime=txtPrezime.getText().trim();
					String jmbg =txtJMBG.getText().trim();
					String pol =txtpol.getText().trim();
					String adresa =txtAdresa.getText().trim();
					String brojTelefona =txtBrojTelefona.getText().trim();
					String korisnickoIme=txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					double plata =  Double.parseDouble(txtplata.getText().trim());
					String specijalizacija=txtSpecijalizacija.getText().trim();
					Sluzba sluzba =Sluzba.valueOf((String) cbSluzba.getSelectedItem());
					Uloga uloga =Uloga.Lekar;
					
					if(lekar==null) {
					Lekar lekar = new Lekar(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, plata, sluzba, specijalizacija, true);
					
					domzravlja.dodajLekara(lekar);;
					domzravlja.snimiLekare("lekari.txt");

					}else {
						lekar.setIme(ime);
						lekar.setPrezime(prezime);
						lekar.setJmbg(jmbg);
						lekar.setPol(pol);
						lekar.setAdresa(adresa);
						lekar.setBrojTelefona(brojTelefona);
						lekar.setLozinka(lozinka);
						lekar.setPlata(plata);
						lekar.setSluzbaZaposlenog(sluzba);
						lekar.setSpecijalizacija(specijalizacija);
						
					}
					
					LekarDodavanjeGUI.this.dispose();
					LekarDodavanjeGUI.this.setVisible(false);
					LekarPrikazGUI ss= new LekarPrikazGUI(domzravlja);
					ss.setVisible(true);
				}
				
			}
		});
		
	}
	
	private void popuniPolja(){
		txtIme.setText(lekar.getIme());
		txtPrezime.setText(lekar.getPrezime());
		txtJMBG.setText(lekar.getJmbg());
		txtpol.setText(lekar.getPol());
		txtAdresa.setText(lekar.getAdresa());
		txtBrojTelefona.setText(lekar.getBrojTelefona());
		txtKorisnickoIme.setText(lekar.getKorisnickoIme());
		pfLozinka.setText(lekar.getLozinka());
		txtplata.setText(String.valueOf(lekar.getPlata()));
		txtSpecijalizacija.setText(lekar.getSpecijalizacija());
		
		int a = vratiIndex(lekar.getSluzbaZaposlenog());
		cbSluzba.setSelectedIndex(a);
		
		
		
		txtKorisnickoIme.setEnabled(false);
		
	}
	
	
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Morate uneti ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Morate uneti prezime\n";
			ok = false;
		}
		if(txtJMBG.getText().trim().equals("")) {
			poruka += "- Morate uneti JMBG\n";
			ok = false;
		}
		if(txtpol.getText().trim().equals("")) {
			poruka += "- Morate uneti pol\n";
			ok = false;
		}
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Morate uneti adresu\n";
			ok = false;
		}
		if(txtBrojTelefona.getText().trim().equals("")) {
			poruka += "- Morate uneti broj Telefona\n";
			ok = false;
		}
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Morate uneti korisnicko ime\n";
			ok = false;
		}
		if(txtSpecijalizacija.getText().trim().equals("")) {
			poruka += "- Morate uneti specijlaizaciju\n";
			ok = false;
		}
		String sifra = new String(pfLozinka.getPassword()).trim();
		if(sifra.trim().equals("")) {
			poruka += "- Morate uneti lozinku\n";
			ok = false;
			
		}
		try {
			Double.parseDouble(txtplata.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Plata mora biti uneta i broj !\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	
	
	
	
	private int vratiIndex(Sluzba sluzba) {
		if(sluzba==Sluzba.sluzbaOpsteMedicine) {
			return 0;
		}else if(sluzba==Sluzba.SluzbaZdravstveneZastiteDece) {
			return 1;
		}else if(sluzba==Sluzba.sluzbaZdravstvneneZastiteRadnika) {
			return 2;
		}
		else if(sluzba==Sluzba.stomatoloskaSluzba) {
			return 3;
		}
		
		
		return 0;
		
	}

}
