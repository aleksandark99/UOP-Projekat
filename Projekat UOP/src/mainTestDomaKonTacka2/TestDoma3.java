package mainTestDomaKonTacka2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import domZdravlja.DomZdravlja;
import osobe.Lekar;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;
import osobe.Sluzba;
import osobe.Uloga;
import pregled.Pregled;
import pregled.StatusPregleda;
import zdravstvena_knjizica.KategorijaOsiguranja;
import zdravstvena_knjizica.zdravstvena_knjizica;

public class TestDoma3 {
	private static String LEKARI_FAJL = "lekari.txt";
	private static String PACIJENT_FAJL="pacijenti.txt";
	private static String KNJIZICE_FAJL="knjizice.txt";
	private static String SESTRE_FAJL="medicinskeSestre.txt";
	private static String PREGLEDI_FAJL="pregledi.txt";
			
	public static void main(String[] args) {
		DomZdravlja domzdravlja = new DomZdravlja();
//	
//		domzdravlja.ucitajLekare(LEKARI_FAJL);
//		domzdravlja.ucitajKnjizice(KNJIZICE_FAJL);
//		domzdravlja.ucitajPacijente(PACIJENT_FAJL);
//		domzdravlja.ucitajSestre(SESTRE_FAJL);
//		domzdravlja.ucitajPreglede(PREGLEDI_FAJL);
		
		

		Lekar Testlekar =new Lekar("testimeLekara","testprezime", "12321", "musko", "perdobrica 13", "022 555-888","TestkorisnickoIme", "Testlozinka", Uloga.Lekar, 300.00, Sluzba.sluzbaOpsteMedicine,"Test specijalizacija",true);
		domzdravlja.dodajLekara(Testlekar);

		
		Medicinska_Sestra Testsestra=new Medicinska_Sestra("testimeSestre","testprezime", "12321", "zensko", "perdobrica 13", "022 555-888","TestkorisnickoIme", "Testlozinka", Uloga.MedicinskaSestra, 300.00, Sluzba.sluzbaOpsteMedicine,true);
		domzdravlja.dodajSestru(Testsestra);
		
//		Date TestDate =new Date(30/12/2001);	
		//
		DateFormat sdff = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date TestDate22 = null;
		String datumm="5/5/2025 14:22:33";
	    try {  	    	
	    	TestDate22 = sdff.parse(datumm);	       
	    } catch (Exception e) {}
		
		//
		zdravstvena_knjizica testKnjizica=new zdravstvena_knjizica(15,TestDate22, KategorijaOsiguranja.druga,true);
		domzdravlja.dodajKnjizice(testKnjizica);

		
		Pacijent testPacijent = new Pacijent("testimePacijenta","testprezime", "12321", "zensko", "perdobrica 13", "022 555-888","TestkorisnickoIme", "Testlozinka", Uloga.Pacijent,Testlekar,testKnjizica,true);
		domzdravlja.dodajPacijenta(testPacijent);
		
		
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date TestDate2 = null;
		String datum="5/5/1998 14:22:33";
	    try {  	    	
	    	TestDate2 = sdf.parse(datum);	       
	    } catch (Exception e) {}
		
	    Pregled testPregled = new Pregled(testPacijent, Testlekar, TestDate2, "7D", StatusPregleda.zakazan, "testopis",true);
		domzdravlja.dodajPreglede(testPregled);
		
//		Lekar Testlekar1 =new Lekar("LekarzaBrisanjeTEST","testprezime", "12321", "musko", "perdobrica 13", "022 555-888","TestkorisnickoIme", "Testlozinka", Uloga.Lekar, 300.00, Sluzba.sluzbaOpsteMedicine,"Test specijalizacija",true);
//		domzdravlja.dodajLekara(Testlekar1);
		
		Lekar Testlekar1 =new Lekar("NOVI IZABRANI LEKAR","NOVI IZABRANI LEKAR", "12321", "musko", "perdobrica 13", "022 555-888","NOVI IZABRANI LEKAR", "NOVI IZABRANI LEKAR", Uloga.Lekar, 300.00, Sluzba.sluzbaOpsteMedicine,"Test specijalizacija",true);
		domzdravlja.dodajLekara(Testlekar1);

		
		System.out.println("Ispis Doma zdravlja pre izmene Lekara,Pacijenta,MedicinskeSestre,Pregleda");
		Ispis(domzdravlja);
		///////////////////
		//IZMENA PACIJENTA
//		domzdravlja.updateImePacijenta(testPacijent);
//		domzdravlja.updatePrezimePacijenta(testPacijent);
//		domzdravlja.updateJMBGPacijenta(testPacijent);
//		domzdravlja.updatePolacijenta(testPacijent);
//		domzdravlja.updateAdresuPacijenta(testPacijent);
//		domzdravlja.updateBrojPacijenta(testPacijent);
//		domzdravlja.updateLozinkuPacijenta(testPacijent);
//		domzdravlja.updateIzabranogLekara(testPacijent, Testlekar1);
		
		//IZMENA LEKARA
//		domzdravlja.updateImeLekara(Testlekar);
//		domzdravlja.updatePrezimeLekara(Testlekar);
//		domzdravlja.updateJMBGLekara(Testlekar);
//		domzdravlja.updatePolLekara(Testlekar);
//		domzdravlja.updateAdresuLekara(Testlekar);
//		domzdravlja.updateBrojLekara(Testlekar);
//		domzdravlja.updateLozinkuLekara(Testlekar);
//		domzdravlja.updateSluzbuLekara(Testlekar);
//		domzdravlja.updatePlataLekara(Testlekar);
		
		//IZMENA SESTRE
//		domzdravlja.updateImeSestre(Testsestra);
//		domzdravlja.updatePrezimeSestre(Testsestra);
//		domzdravlja.updateJMBGSestre(Testsestra);
//		domzdravlja.updatePolSestre(Testsestra);
//		domzdravlja.updateAdresuSestre(Testsestra);
//		domzdravlja.updateBrojSestre(Testsestra);
//		domzdravlja.updateLozinkuSestre(Testsestra);
//		domzdravlja.updateSluzbuSestre(Testsestra);
//		domzdravlja.updatePlatuSestre(Testsestra);
		
		//IZMENA PREGLEDA		
//		domzdravlja.updateDatumPregleda(testPregled);
//		domzdravlja.updateSobuPregleda(testPregled);
//		domzdravlja.updateStatusPregleda(testPregled);
//		domzdravlja.updateOpisPregleda(testPregled);
		
		//IZMENA KNJIZICE PACIJENTA		
//		domzdravlja.updateDatumIstekaKnjizice(testPacijent);
//		domzdravlja.updateKategorijuOsiguranja(testPacijent);

		System.out.println("Ispis Doma zdravlja posle izmene Lekara,Pacijenta,MedicinskeSestre,Pregleda");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		Ispis(domzdravlja);
		
		/////////////////////////////////////////////////////////////////////
		
		//domzdravlja.updateImePacijenta(testPacijent);
		
		//Ispis(domzdravlja);
		
//		domzdravlja.snimiLekare(LEKARI_FAJL);
//		domzdravlja.snimiSestre(SESTRE_FAJL);
//		domzdravlja.snimiKnjizice(KNJIZICE_FAJL);
//		domzdravlja.snimiPacijente(PACIJENT_FAJL);
//		domzdravlja.snimiPreglede(PREGLEDI_FAJL);



	
	}
	public static void Ispis(DomZdravlja domzdravlja) {
		System.out.println("=============Lekari===============");
		for(Lekar lekar : domzdravlja.getLekare()) {
			if(lekar.isState()==true) {
				
			
			System.out.println(lekar + "\n");
			}
			
		}
		System.out.println("==================================");
		System.out.println("=============Pacijenti============");
		for(Pacijent pacijent : domzdravlja.getPacijente()) {
			if(pacijent.isState()==true) {
				
		System.out.println(pacijent + "\n"); 
			}
		}
		System.out.println("==================================");
		System.out.println("=============Kjnizice=============");
		for(zdravstvena_knjizica knjizica : domzdravlja.getKnjizice()) {
			if(knjizica.isState()==true) {
			System.out.println(knjizica+"\n");
		}
		}
		System.out.println("==================================");
		System.out.println("=============Medicinske_Sestre====");
		for(Medicinska_Sestra sestra: domzdravlja.getSestre()) {
			if(sestra.isState()==true) {
				
			System.out.println(sestra+"\n");
			}
		}
		System.out.println("==================================");
		System.out.println("=============Pregledi=============");
		for(Pregled pregled:domzdravlja.getPreglede()) {
			if(pregled.isState()==true) {
				System.out.println(pregled+"\n");

			}
		}
		
	}

	
	
}