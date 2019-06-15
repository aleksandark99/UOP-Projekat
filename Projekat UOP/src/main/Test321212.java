package main;

import java.awt.Window.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import domZdravlja.DomZdravlja;
import pregled.Pregled;

public class Test321212 {

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
		domzdravlja.snimiPreglede(PREGLEDI_FAJL);
		
		
		int a=Integer.parseInt("05");
		System.out.println(a);
		Date termin=null;


		String termin1="05/08/1998 14:23:33";

		SimpleDateFormat datumisteka=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//DODATI VREME 
		try {
			termin =datumisteka.parse(termin1);
		} catch (ParseException e) {e.printStackTrace();}

		Date min, max;   // assume these are set to something
		Date d;          // the date in question
		
		 Calendar calendar1 = Calendar.getInstance();
		 Calendar calendar2 = Calendar.getInstance();
		 System.out.println("===========");
		 System.out.println(calendar1.HOUR_OF_DAY);
		 
		 System.out.println(calendar1.DAY_OF_YEAR);
		 System.out.println(Calendar.DAY_OF_MONTH);
		 System.out.println(Calendar.YEAR);
		 System.out.println();
		 System.out.println();
		 
		 calendar1.setTime(termin);
		 calendar1.add(Calendar.MINUTE, 15);
		 
		 calendar2.setTime(termin);
		 calendar2.add(Calendar.MINUTE, -15);
		
		for (Pregled pregled : domzdravlja.getPreglede()) {
			System.out.println(pregled.getTermin());
			System.out.println("======");
			if(pregled.getTermin().after(calendar2.getTime())&&pregled.getTermin().before(calendar1.getTime())) {
				System.out.println("sss");
				System.out.println(calendar1.getTime());
				System.out.println(calendar2.getTime());

			}
			else {
//				System.out.println(calendar1.getTime());
//				System.out.println(calendar2.getTime());
				System.out.println("22");

			}
			
			
		}
	//	return d.after(min) && d.before(max);
		

}
}
