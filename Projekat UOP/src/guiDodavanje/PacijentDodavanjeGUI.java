package guiDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import gui.GlavniProzorSestre;
import gui.LoginWindow;
import guizaPrikaz.PacijentPrikazGUI;
import net.miginfocom.swing.MigLayout;
import osobe.Lekar;
import osobe.Pacijent;
import osobe.Sluzba;
import osobe.Uloga;
import zdravstvena_knjizica.KategorijaOsiguranja;
import zdravstvena_knjizica.zdravstvena_knjizica;

public class PacijentDodavanjeGUI extends JFrame {
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
	
	private JLabel lblKorImeIzabranogLekara = new JLabel("Korisnicko ime izabranog lekara");
	private JTextField txtKorImeIzabranogLekara  = new JTextField(20);
	
	private JLabel lblBrojKnjizice  = new JLabel("Broj knjizice");
	private JTextField txtBrojKnjizice = new JTextField(20);
	
	private JLabel lblKatOsiguranja = new JLabel("Kategorija osiguranja");
	private JComboBox<String> cbKatOsiguranja; 
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja domzravlja;
	private Pacijent pacijent;
	
	
	
	public PacijentDodavanjeGUI(DomZdravlja domzravlja,Pacijent pacijent) {
		
		this.domzravlja = domzravlja;
		this.pacijent = pacijent;
		if(pacijent != null) {
			setTitle(pacijent.getIme() + " - Izmena podataka");
		}else {
			setTitle("Dodavanje pacijenata");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		cbKatOsiguranja = new JComboBox<>();
		cbKatOsiguranja.addItem(String.valueOf(KategorijaOsiguranja.prva));
		cbKatOsiguranja.addItem(String.valueOf(KategorijaOsiguranja.druga));
		cbKatOsiguranja.addItem(String.valueOf(KategorijaOsiguranja.treca));
		
		if(pacijent != null) {
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
		add(lblKorImeIzabranogLekara);  add(txtKorImeIzabranogLekara);
//		add(lblBrojKnjizice);  add(txtBrojKnjizice);
		add(lblKatOsiguranja); add(cbKatOsiguranja);
		add(btnOk); add(btnCancel);
		
		this.getRootPane().setDefaultButton(btnOk);
	}
	
	
	
	private void initActions() {
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
//					String lozinka=tx.getText().trim();
					Lekar lekar =domzravlja.nadjiLekara(txtKorImeIzabranogLekara.getText().trim());
					zdravstvena_knjizica knjizica =NovaKnjizica();
					Uloga uloga =Uloga.Pacijent;
					
					if(pacijent==null) {
					Pacijent pacijent = new Pacijent(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, lekar, knjizica, true);
					
					domzravlja.dodajPacijenta(pacijent);
					domzravlja.snimiPacijente("pacijenti.txt");
					domzravlja.dodajKnjizice(pacijent.getKnjizica());
					domzravlja.snimiKnjizice("knjizice.txt");
					}else {
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setJmbg(jmbg);
						pacijent.setPol(pol);
						pacijent.setAdresa(adresa);
						pacijent.setBrojTelefona(brojTelefona);
						pacijent.setLozinka(lozinka);
						pacijent.setIzabraniLekar(lekar);
						KategorijaOsiguranja katos =KategorijaOsiguranja.valueOf((String) cbKatOsiguranja.getSelectedItem());
						pacijent.getKnjizica().setKategorijaOsiguranja(katos);
					}
					
					PacijentDodavanjeGUI.this.dispose();
					PacijentDodavanjeGUI.this.setVisible(false);
					PacijentPrikazGUI pp= new PacijentPrikazGUI(domzravlja);
					pp.setVisible(true);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentDodavanjeGUI.this.setVisible(false);
				PacijentDodavanjeGUI.this.dispose();
				PacijentPrikazGUI pp= new PacijentPrikazGUI(domzravlja);
				pp.setVisible(true);
				
			}
		});
		
		
		
		
		
	}
	
	
	
	private void popuniPolja(){
//		cbKatOsiguranja = new JComboBox<>();
//		cbKatOsiguranja.addItem(String.valueOf(KategorijaOsiguranja.prva));
//		cbKatOsiguranja.addItem(String.valueOf(KategorijaOsiguranja.druga));
//		cbKatOsiguranja.addItem(String.valueOf(KategorijaOsiguranja.treca));
//	
		txtIme.setText(pacijent.getIme());
		txtPrezime.setText(pacijent.getPrezime());
		txtJMBG.setText(pacijent.getJmbg());
		txtpol.setText(pacijent.getPol());
		txtAdresa.setText(pacijent.getAdresa());
		txtBrojTelefona.setText(pacijent.getBrojTelefona());
		txtKorisnickoIme.setText(pacijent.getKorisnickoIme());
		pfLozinka.setText(pacijent.getLozinka());
		txtKorImeIzabranogLekara.setText(pacijent.getIzabraniLekar().getKorisnickoIme());
		txtBrojKnjizice.setText(String.valueOf(pacijent.getKnjizica().getBroj()));
		
		int b =vratiIndex(pacijent.getKnjizica().getKategorijaOsiguranja());
		cbKatOsiguranja.setSelectedIndex(b);
		
		txtKorisnickoIme.setEnabled(false);
		txtBrojKnjizice.setEnabled(false);
		
	}
	
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		if(pacijent==null) {
			for(Pacijent a:domzravlja.getPacijente()) {
				if(a.getKorisnickoIme().equals(txtKorisnickoIme.getText().trim())) {
					poruka += "-Zauzeto korisnicko ime\n";
					ok = false;
					break;
				}
			}
		}
		
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
		}
		Lekar lekar=domzravlja.nadjiLekara(txtKorImeIzabranogLekara.getText().trim());
		if( lekar !=null) {
			
		}
		else {
			poruka += "- Morate uneti postojeceg lekara\n";
			ok = false;
		}
//		for(Lekar lekar : domzravlja.getLekare())
//			if(lekar.isState()==true) {
//				
//			}
//		try {
//			Integer.parseInt(txtBrojKnjizice.getText().trim());
//		}catch (NumberFormatException e) {
//			poruka += "- Broj knjizice mora biti broj\n";
//			ok = false;
//		}

		
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	


	private int brNoveKnjizice() {
		ArrayList<zdravstvena_knjizica> zk =domzravlja.getKnjizice() ;
		zdravstvena_knjizica knjizica= zk.get(zk.size() - 1);
		int PoslednjiBroj =knjizica.getBroj();
		
		
		return PoslednjiBroj+1;
		
	}
	private zdravstvena_knjizica NovaKnjizica() {
		
		int broh = brNoveKnjizice();
		Date datumIsteka = new Date();

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdg= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdg.format(datumIsteka);
//		Date newDate = sdf.arse(datumIsteka);
		String kat =(String) cbKatOsiguranja.getSelectedItem();
		KategorijaOsiguranja kategorija = KategorijaOsiguranja.valueOf(kat);
	
		
		zdravstvena_knjizica knjizica =new zdravstvena_knjizica(broh, datumIsteka, kategorija, true);
		domzravlja.dodajKnjizice(knjizica);
		
		return knjizica;
	}
	
	private int vratiIndex(KategorijaOsiguranja kat) {
		if(kat ==KategorijaOsiguranja.prva) {
			return 0;
		}else if (kat ==KategorijaOsiguranja.druga) {
			return 1;
		}else if (kat ==KategorijaOsiguranja.treca) {
			return 2;
		}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
