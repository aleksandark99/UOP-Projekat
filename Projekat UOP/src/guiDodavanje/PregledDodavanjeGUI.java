package guiDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import guizaPrikaz.PacijentPrikazGUI;
import guizaPrikaz.PreglediPrikazGUI;
import net.miginfocom.swing.MigLayout;
import osobe.Lekar;
import osobe.Pacijent;
import pregled.Pregled;
import pregled.StatusPregleda;
import zdravstvena_knjizica.KategorijaOsiguranja;
import zdravstvena_knjizica.zdravstvena_knjizica;

public class PregledDodavanjeGUI extends JFrame {
	private JLabel lblKorImePacijenta= new JLabel("Korisnicko ime pacijenta");
	private JTextField txtKorImePacijenta= new JTextField(20);
	
	private JLabel lblKorImeLekara= new JLabel("Korisnicko ime lekara");
	private JTextField txtKorImeLekara= new JTextField(20);

	private JLabel lblTermin= new JLabel("Termin dd/mm/yyyy HH:mm:ss");
	private JTextField txtTerminDan= new JTextField(3);
	private JTextField txtTerminMesec= new JTextField(3);
	private JTextField txtTerminGodina= new JTextField(3);
	private JTextField txtTerminSat= new JTextField(3);
	private JTextField txtTerminMinut= new JTextField(3);
	private JTextField txtTerminSekunde= new JTextField(3);
	
	private JLabel lblSoba= new JLabel("Soba");
	private JTextField txtSoba= new JTextField(20);
	
	private JLabel lblOpis= new JLabel("Opis");
	private JTextField txtOpis= new JTextField(20);

	private JLabel lblRacun= new JLabel("Racun");
	private JTextField txtRacun= new JTextField(20);
	
	private JLabel lblBroj= new JLabel("Broj");
	private JTextField txtBroj= new JTextField(20);
	
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
			setTitle("Dodavanje pregleda");
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
			cbStatus.setSelectedIndex(1);
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
//		add(lblRacun); add(txtRacun);
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
				SimpleDateFormat datumisteka=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//DODATI VREME 
				Date Termin=null;
				try {
					Termin=datumisteka.parse(termin);
				}catch (Exception s) {}
				
				
				String kratak_opis =txtOpis.getText().trim();
			//	int racun =Integer.parseInt(txtRacun.getText().trim());
				String soba=txtSoba.getText().trim();
			//	int brojA=Integer.parseInt(txtBroj.getText().trim());
				if(pregled==null) {
					int broj = brNovogPregleda();
					Pregled pregled = new Pregled(pacijent, lekar, Termin, soba, StatusPregleda.zakazan, kratak_opis, 0, true,broj);
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
						domzravlja.snimiPreglede("pregledi.txt");
						//pregled.setRacun(racun);
					}
				PregledDodavanjeGUI.this.dispose();
				PregledDodavanjeGUI.this.setVisible(false);
				PreglediPrikazGUI pp= new PreglediPrikazGUI(domzravlja);
				pp.setVisible(true);
				
			}
			
		}
	});
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			PregledDodavanjeGUI.this.dispose();
			PregledDodavanjeGUI.this.setVisible(false);
			PreglediPrikazGUI pp= new PreglediPrikazGUI(domzravlja);
			pp.setVisible(true);
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
		
		int a =vratiIndexLekara(pregled.getLekar());
		cbLekari.setSelectedIndex(a);
		txtBroj.setText(String.valueOf(pregled.getBroj()));
		

		txtKorImePacijenta.setEnabled(false);
		txtRacun.setEnabled(false);
		txtOpis.setEnabled(false);
		
		LocalDate localDate = pregled.getTermin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		int month = localDate.getMonthValue();
		int day   = localDate.getDayOfMonth();
		int hour 	=pregled.getTermin().getHours();
		int minutes =pregled.getTermin().getMinutes();
		int seconds =pregled.getTermin().getSeconds();
		
		txtTerminDan.setText(String.valueOf(day));
		txtTerminMesec.setText(String.valueOf(month));
		txtTerminGodina.setText(String.valueOf(year));
		txtTerminSat.setText(String.valueOf(hour));
		txtTerminMinut.setText(String.valueOf(minutes));
		txtTerminSekunde.setText(String.valueOf(seconds));

//		
		
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
		Pacijent pacijent =domzravlja.nadjiPacijenta(txtKorImePacijenta.getText().trim());
		if(pacijent==null) {
			poruka += "- Morate uneti postojece korisnicko ime pacijenta\n";
			ok = false;
		}
		if(txtSoba.getText().trim().equals("")) {
			poruka += "- Morate uneti sobu\n";
			ok = false;
		}
		else {
			
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
		
		else {
			String terminstr=txtTerminDan.getText().trim()+"/"+txtTerminMesec.getText().trim()+"/"+txtTerminGodina.getText().trim()
					+" "+txtTerminSat.getText().trim()+":"+txtTerminMinut.getText().trim()+":"+txtTerminSekunde.getText().trim();
			//
			Date termin=null;


			String termin1="05/08/1998 14:23:33";

			SimpleDateFormat datumisteka=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//DODATI VREME 
			try {
				termin =datumisteka.parse(terminstr);
			} catch (ParseException e) {e.printStackTrace();}
			
			
			 Calendar calendar1 = Calendar.getInstance();
			 Calendar calendar2 = Calendar.getInstance();

			 calendar1.setTime(termin);
			 calendar1.add(Calendar.MINUTE, 15);
			 
			 calendar2.setTime(termin);
			 calendar2.add(Calendar.MINUTE, -15);
			 ArrayList<Pregled> pregledii = new ArrayList<Pregled>();
			 pregledii = domzravlja.getPreglede();
//			 pregledii.remove(pregled);
			for (Pregled pregledA : pregledii) {
			///&&pregledA.getStatus()!=StatusPregleda.zavrsen za slucaj slucaja xD
				if(pregledA != pregled && pregledA.isState()==true ) {
				if(pregledA.getLekar()==domzravlja.nadjiLekara(txtKorImeLekara.getText().trim())||pregled==null) {

				if(pregledA.getTermin().after(calendar2.getTime())&&pregledA.getTermin().before(calendar1.getTime())) {
					poruka += "- Lekar ima zakazna pregled u to vreme\n";
					ok = false;
					break;//ako puca zbog ovoga je 
				}
				else {

					System.out.println("22");

				}
				}
				}
				
			}
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
	private int vratiIndexLekara(Lekar lekar) {
		int i =0;
		for(Lekar lekar1 :domzravlja.getLekare()) {
			if(lekar1==lekar) {
				return i;
			}
			else {
				if(lekar1.isState()==true) {
					i+=1;
	
				}
			}
	
		}
		return 0;
	}
	
	private int brNovogPregleda() {
		ArrayList<Pregled> zk =domzravlja.getPreglede() ;
		Pregled pregled= zk.get(zk.size() - 1);
		int PoslednjiBroj =pregled.getBroj();
		
		
		return PoslednjiBroj+1;
		
	}
	
	
	
	
	
	public static Date addMinutesToJavaUtilDate(Date date, int minuts) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MINUTE, minuts);
	    return calendar.getTime();
	}
	
	
	
}
