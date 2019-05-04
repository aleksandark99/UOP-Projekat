package main;

import java.text.SimpleDateFormat;
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

public class TestDoma {
	private static String LEKARI_FAJL = "lekari.txt";
	private static String PACIJENT_FAJL="pacijenti.txt";
	private static String KNJIZICE_FAJL="knjizice.txt";
	private static String SESTRE_FAJL="medicinskeSestre.txt";
	private static String PREGLEDI_FAJL="pregledi.txt";
			
	public static void main(String[] args) {
		DomZdravlja domzdravlja = new DomZdravlja();
	
		domzdravlja.ucitajLekare(LEKARI_FAJL);
		domzdravlja.ucitajKnjizice(KNJIZICE_FAJL);
		domzdravlja.ucitajPacijente(PACIJENT_FAJL);
		domzdravlja.ucitajSestre(SESTRE_FAJL);
		domzdravlja.ucitajPreglede(PREGLEDI_FAJL);
		Ispis(domzdravlja);
		
		System.out.println("Dodavanje test lekara...");
		Lekar Testlekar =new Lekar("testimeLekara"," testprezime", "12321", "musko", "perdobrica 13", "022 555-888"," TestkorisnickoIme", "Testlozinka", Uloga.Lekar, 300.00, Sluzba.sluzbaOpsteMedicine,"Test specijalizacija");
		domzdravlja.dodajLekara(Testlekar);
		domzdravlja.snimiLekare(LEKARI_FAJL);
		
		Medicinska_Sestra Testsestra=new Medicinska_Sestra("testimeSestre"," testprezime", "12321", "zensko", "perdobrica 13", "022 555-888"," TestkorisnickoIme", "Testlozinka", Uloga.MedicinskaSestra, 300.00, Sluzba.sluzbaOpsteMedicine);
		domzdravlja.dodajSestru(Testsestra);
		domzdravlja.snimiSestre(SESTRE_FAJL);
		Date TestDate =new Date(30/12/2001);		
		zdravstvena_knjizica testKnjizica=new zdravstvena_knjizica(15,TestDate, KategorijaOsiguranja.druga);
		domzdravlja.dodajKnjizice(testKnjizica);
		domzdravlja.snimiKnjizice(KNJIZICE_FAJL);

		
		Pacijent testPacijent = new Pacijent("testimePacijenta"," testprezime", "12321", "zensko", "perdobrica 13", "022 555-888"," TestkorisnickoIme", "Testlozinka", Uloga.Pacijent,Testlekar,testKnjizica);
		domzdravlja.dodajPacijenta(testPacijent);
		domzdravlja.snimiPacijente(PACIJENT_FAJL);
		
		
		Date TestDate2 =new Date("5/5/1998 4:22:33");	
		Pregled testPregled = new Pregled(testPacijent, Testlekar, TestDate2, "7D", StatusPregleda.zakazan, "testopis");
		domzdravlja.dodajPreglede(testPregled);
		domzdravlja.snimiPreglede(PREGLEDI_FAJL);
		System.out.println(testPregled);
	
	}
	public static void Ispis(DomZdravlja domzdravlja) {
		System.out.println("=============Lekari===============");
		for(Lekar lekar : domzdravlja.getLekare()) {
			System.out.println(lekar + "\n");
		}
		System.out.println("==================================");
		System.out.println("=============Pacijenti===============");
		for(Pacijent pacijent : domzdravlja.getPacijente()) {
		System.out.println(pacijent + "\n"); 
		}
		System.out.println("==================================");
		System.out.println("==============Kjnizice===============");
		for(zdravstvena_knjizica knjizica : domzdravlja.getKnjizice()) {
			System.out.println(knjizica+"\n");
		}
		System.out.println("==================================");
		System.out.println("===============Medicinske_Sestre==================");
		for(Medicinska_Sestra sestra: domzdravlja.getSestre()) {
			System.out.println(sestra+"\n");
		}
		System.out.println("==================================");
		System.out.println("===============Pregledi==================");
		for(Pregled pregled:domzdravlja.getPreglede()) {
			System.out.println(pregled+"\n");
		}
		
	}

}
