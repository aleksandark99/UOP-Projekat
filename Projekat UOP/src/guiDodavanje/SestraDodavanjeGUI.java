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
import guizaPrikaz.PacijentPrikazGUI;
import guizaPrikaz.SestrePrikazGUI;
import net.miginfocom.swing.MigLayout;
import osobe.Lekar;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;
import osobe.Sluzba;
import osobe.Uloga;
import zdravstvena_knjizica.KategorijaOsiguranja;
import zdravstvena_knjizica.zdravstvena_knjizica;

public class SestraDodavanjeGUI extends JFrame {
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);

	private JLabel lblPol = new JLabel("Pol");
	private JTextField txtpol = new JTextField(20);
	
	
	private JLabel lblAdresa  = new JLabel("Adreas");
	private JTextField txtAdresa = new JTextField(20);
	
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
	private Medicinska_Sestra sestra;
	
	public SestraDodavanjeGUI(DomZdravlja domzravlja,Medicinska_Sestra sestra) {
		
		this.domzravlja = domzravlja;
		this.sestra = sestra;
		if(sestra != null) {
			setTitle(sestra.getIme() + " - Izmena podataka");
		}else {
			setTitle("Dodavanje sestre");
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
		cbSluzba.addItem(String.valueOf(Sluzba.sluzbaZaPravneIekonomskePoslove));
		cbSluzba.addItem(String.valueOf(Sluzba.sluzbaZaTehnickePoslove));
		cbSluzba.addItem(String.valueOf(Sluzba.SluzbaZdravstveneZastiteDece));
		cbSluzba.addItem(String.valueOf(Sluzba.sluzbaZdravstvneneZastiteRadnika));
		cbSluzba.addItem(String.valueOf(Sluzba.stomatoloskaSluzba));
		
		if(sestra != null) {
			popuniPolja();
		}else {
			txtKorisnickoIme.setEnabled(true);
		//	txtBrojKnjizice.setEnabled(true);// OVDE DODATI DA SE NAPRAVI I NOVA KNJIZICA 

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
				
					Sluzba sluzba =Sluzba.valueOf((String) cbSluzba.getSelectedItem());
					Uloga uloga =Uloga.MedicinskaSestra;
					
					if(sestra==null) {
					Medicinska_Sestra sestra = new Medicinska_Sestra(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, plata, sluzba, true);
					
					domzravlja.dodajSestru(sestra);
					domzravlja.snimiSestre("medicinskeSestre.txt");

					}else {
						sestra.setIme(ime);
						sestra.setPrezime(prezime);
						sestra.setJmbg(jmbg);
						sestra.setPol(pol);
						sestra.setAdresa(adresa);
						sestra.setBrojTelefona(brojTelefona);
						sestra.setLozinka(lozinka);
						sestra.setPlata(plata);
						sestra.setSluzbaZaposlenog(sluzba);
						
					}
					
					SestraDodavanjeGUI.this.dispose();
					SestraDodavanjeGUI.this.setVisible(false);
					SestrePrikazGUI ss= new SestrePrikazGUI(domzravlja);
					ss.setVisible(true);
				}
				
			}
		});
		
	}
	
	private void popuniPolja(){
		txtIme.setText(sestra.getIme());
		txtPrezime.setText(sestra.getPrezime());
		txtJMBG.setText(sestra.getJmbg());
		txtpol.setText(sestra.getPol());
		txtAdresa.setText(sestra.getAdresa());
		txtBrojTelefona.setText(sestra.getBrojTelefona());
		txtKorisnickoIme.setText(sestra.getKorisnickoIme());
		pfLozinka.setText(sestra.getLozinka());
		txtplata.setText(String.valueOf(sestra.getPlata()));
		
		int a = vratiIndex(sestra.getSluzbaZaposlenog());
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
		String sifra = new String(pfLozinka.getPassword()).trim();
		if(sifra.trim().equals("")) {
			poruka += "- Morate uneti lozinku\n";
			ok = false;
			
			///DODATI ZA PLATU
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
		}else if(sluzba==Sluzba.sluzbaZaPravneIekonomskePoslove) {
			return 1;
		}else if(sluzba==Sluzba.sluzbaZaTehnickePoslove) {
			return 2;
		}
		else if(sluzba==Sluzba.SluzbaZdravstveneZastiteDece) {
			return 3;
		}else if(sluzba==Sluzba.sluzbaZdravstvneneZastiteRadnika) {
			return 4;
		}else if(sluzba==Sluzba.stomatoloskaSluzba) {
			return 5;
		}
		
		
		return 0;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
