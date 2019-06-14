package guiDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import net.miginfocom.swing.MigLayout;
import osobe.Lekar;
import osobe.Pacijent;
import pregled.Pregled;
import pregled.StatusPregleda;
import zdravstvena_knjizica.KategorijaOsiguranja;
import zdravstvena_knjizica.zdravstvena_knjizica;

public class PregledDodavanjeGUI extends JFrame {
	private JLabel lblKorImePacijenta= new JLabel("Korisnicko ime pacijenta");
	private JTextField txtKorImePacijenta= new JTextField(40);
	
	private JLabel lblKorImeLekara= new JLabel("Korisnicko ime lekara");
	private JTextField txtKorImeLekara= new JTextField(40);

	private JLabel lblTermin= new JLabel("Termin dd/mm/yyyy HH:mm:ss");
	private JTextField txtTerminDan= new JTextField(5);
	private JTextField txtTerminMesec= new JTextField(5);
	private JTextField txtTerminGodina= new JTextField(5);
	private JTextField txtTerminSat= new JTextField(5);
	private JTextField txtTerminMinut= new JTextField(5);
	private JTextField txtTerminSekunde= new JTextField(5);
	
	private JLabel lblSoba= new JLabel("Soba");
	private JTextField txtSoba= new JTextField(40);
	
	private JLabel lblOpis= new JLabel("Opis");
	private JTextField txtOpis= new JTextField(40);

	private JLabel lblRacun= new JLabel("Racun");
	private JTextField txtRacun= new JTextField(40);
	
	private JLabel lblBroj= new JLabel("Broj");
	private JTextField txtBroj= new JTextField(40);
	
	private JLabel lblStatus = new JLabel("Status pregleda");
	private JComboBox<String> cbStatus; 
	
	private JComboBox<String> cbLekari; 
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja domzravlja;
	private Pregled pregled;
	
	private JLabel lblempty = new JLabel();
	


	
	public PregledDodavanjeGUI(DomZdravlja domzravlja,Pregled pregled) {
		this.domzravlja = domzravlja;
		this.pregled=pregled;
		if(pregled != null) {
			setTitle("Izmena podataka");
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
		cbStatus =  new JComboBox<>();
		cbStatus.addItem(String.valueOf(StatusPregleda.zatrazen));
		cbStatus.addItem(String.valueOf(StatusPregleda.zakazan));
		cbStatus.addItem(String.valueOf(StatusPregleda.zavrsen));
		cbStatus.addItem(String.valueOf(StatusPregleda.otkazan));
		
		cbLekari=new JComboBox<String>();
		for(Lekar lekar :domzravlja.getLekare()) {
			if(lekar.isState()==true) {
				cbLekari.addItem(lekar.getKorisnickoIme());
			}
		
		}
		
		if(pregled != null) {
			popuniPolja();
		}else {
//			txtKorisnickoIme.setEnabled(true);
		//	txtBrojKnjizice.setEnabled(true);// OVDE DODATI DA SE NAPRAVI I NOVA KNJIZICA 

		}
		add(lblKorImePacijenta);  add(txtKorImePacijenta);
		add(lblKorImeLekara); 
		add(cbLekari);
		//add(txtKorImeLekara);
		add(lblTermin);  add(txtTerminDan,"split 6"); add(txtTerminMesec); add(txtTerminGodina);add(txtTerminSat);add(txtTerminMinut);add(txtTerminSekunde);
		//add(lblempty);
		add(lblSoba);  add(txtSoba);
		add(lblOpis);  add(txtOpis);
		add(lblRacun); add(txtRacun);
		add(lblStatus); add(cbStatus);
//		add(lblBroj); add(txtBroj);
		add(btnOk); add(btnCancel);
		txtBroj.setEnabled(false);
		
		this.getRootPane().setDefaultButton(btnOk);

		
		
	}
	private void initActions() {
	btnOk.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija() == true) {
				String korImePacijenta=txtKorImePacijenta.getText().trim();
//				String korimeLekara=txtKorImeLekara.getText().trim();
				//StatusPregleda stat =StatusPregleda.valueOf((String) cbStatus.getSelectedItem());
				String korimeLekara=(String)cbLekari.getSelectedItem();
				Lekar lekar=domzravlja.nadjiLekara(korimeLekara);
				Pacijent pacijent =domzravlja.nadjiPacijenta(korImePacijenta);
//				05/22/1998 14:22:33
				String termin=txtTerminDan.getText().trim()+"/"+txtTerminMesec.getText().trim()+"/"+txtTerminGodina.getText().trim()
						+" "+txtTerminSat.getText().trim()+":"+txtTerminMinut.getText().trim()+":"+txtTerminSekunde.getText().trim();
				SimpleDateFormat datumisteka=new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");//DODATI VREME 
//				termin =datumisteka.parse(termin1);
			
				Date Termin=null;
				try {
					Termin=datumisteka.parse(termin);
				}catch (Exception s) {}
				
				
				String kratak_opis =txtOpis.getText().trim();
				int racun =Integer.parseInt(txtRacun.getText().trim());
				String soba=txtSoba.getText().trim();
			//	int brojA=Integer.parseInt(txtBroj.getText().trim());
				if(pregled==null) {
					int broj = brNovogPregleda();
					Pregled pregled = new Pregled(pacijent, lekar, Termin, soba, StatusPregleda.zakazan, kratak_opis, racun, true,broj);
					domzravlja.dodajPreglede(pregled);
					domzravlja.snimiPreglede("pregledi.txt");
				
					}else {
						pregled.setLekar(lekar);
						pregled.setPacijent(pacijent);
						pregled.setTermin(Termin);
						pregled.setSoba(soba);
						pregled.setKratak_opis(kratak_opis);
						
						StatusPregleda stat =StatusPregleda.valueOf((String) cbStatus.getSelectedItem());
						pregled.setStatus(stat);
						pregled.setRacun(racun);
					}
				
			}
			
		}
	});
	}
	
	
	private void popuniPolja() {
		txtKorImeLekara.setText(pregled.getLekar().getKorisnickoIme());
		txtKorImePacijenta.setText(pregled.getPacijent().getKorisnickoIme());
		txtSoba.setText(pregled.getSoba());
		txtRacun.setText(String.valueOf(pregled.getRacun()));
		txtOpis.setText(pregled.getKratak_opis());
		int b =vratiIndex(pregled.getStatus());
		cbStatus.setSelectedIndex(b);
		txtBroj.setText(String.valueOf(pregled.getBroj()));
		

		txtKorImePacijenta.setEnabled(false);
		txtRacun.setEnabled(false);
		txtOpis.setEnabled(false);
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";

//		if(txtKorImeLekara.getText().trim().equals("")) {
//			poruka += "- Morate uneti korisnicko ime lekara\n";
//			ok = false;
//		}
		if(txtKorImePacijenta.getText().trim().equals("")) {
			poruka += "- Morate uneti korisnicko ime pacijenta\n";
			ok = false;
		}
		if(txtSoba.getText().trim().equals("")) {
			poruka += "- Morate uneti sobu\n";
			ok = false;
		}
		if(txtOpis.getText().trim().equals("")) {
			poruka += "- Morate uneti opis\n";
			ok = false;
		}
		
		if(txtTerminDan.getText().trim().equals("") || txtTerminMesec.getText().trim().equals("")||
			txtTerminGodina.getText().trim().equals("")||txtTerminSat.getText().trim().equals("")
			||txtTerminMinut.getText().trim().equals("")||txtTerminSekunde.getText().trim().equals("")) {
			poruka += "- Morate uneti termin u ispravnom formatu\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	
	
	
	
	private int vratiIndex(StatusPregleda stat ) {
		if(stat ==StatusPregleda.zatrazen) {
			return 0;
		}else if (stat ==StatusPregleda.zakazan) {
			return 1;
		}else if (stat ==StatusPregleda.zavrsen) {
			return 2;
		}else if(stat ==StatusPregleda.otkazan) {
			return 3;
		}
		return 0;
	}
	
	private int brNovogPregleda() {
		ArrayList<Pregled> zk =domzravlja.getPreglede() ;
		Pregled pregled= zk.get(zk.size() - 1);
		int PoslednjiBroj =pregled.getBroj();
		
		
		return PoslednjiBroj+1;
		
	}
	
	
	
	
	
	
	
	
	
}
