package main;

import domZdravlja.DomZdravlja;
import gui.LoginWindow;

public class TestDomaGUI {

	private static String LEKARI_FAJL = "lekari.txt";
	private static String PACIJENT_FAJL="pacijenti.txt";
	private static String KNJIZICE_FAJL="knjizice.txt";
	private static String SESTRE_FAJL="medicinskeSestre.txt";
	private static String PREGLEDI_FAJL="pregledi.txt";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DomZdravlja domzdravlja = new DomZdravlja();
		
		domzdravlja.ucitajLekare(LEKARI_FAJL);
		domzdravlja.ucitajKnjizice(KNJIZICE_FAJL);
		domzdravlja.ucitajPacijente(PACIJENT_FAJL);
		domzdravlja.ucitajSestre(SESTRE_FAJL);
		domzdravlja.ucitajPreglede(PREGLEDI_FAJL);
		LoginWindow login = new LoginWindow(domzdravlja);
		login.setVisible(true);
		
	}

}
