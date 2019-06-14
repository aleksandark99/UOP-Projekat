package pregled;

import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;

import osobe.Lekar;
import osobe.Pacijent;


public class Pregled {
	private Pacijent pacijent;
	private Lekar lekar;
	private Date termin;
	private String soba;
	private StatusPregleda statusPregleda;
	private String kratak_opis;
	private int racun;
	private boolean state;
	private int broj;
	
	public Pregled() {
		this.pacijent=new Pacijent();
		this.lekar=new Lekar();
		this.termin=null;
		this.soba="";
		this.statusPregleda=StatusPregleda.otkazan;
		this.kratak_opis="";
		this.state=true;
		this.racun=0;
		this.broj=0;
	}

	public Pregled(Pacijent pacijent, Lekar lekar, Date termin, String soba, StatusPregleda statusPregleda, String kratak_opis,int racun,boolean state,int broj) {
		super();
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.termin = termin;
		this.soba = soba;
		this.statusPregleda = statusPregleda;
		this.kratak_opis = kratak_opis;
		this.state=state;
		this.racun=racun;
		this.broj=broj;
	}

	


	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public int getRacun() {
		return racun;
	}

	public void setRacun(int racun) {
		this.racun = racun;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}

	public String getSoba() {
		return soba;
	}

	public void setSoba(String soba) {
		this.soba = soba;
	}

	public StatusPregleda getStatus() {
		return statusPregleda;
	}

	public void setStatus(StatusPregleda statusPregleda) {
		this.statusPregleda = statusPregleda;
	}

	public String getKratak_opis() {
		return kratak_opis;
	}

	public void setKratak_opis(String kratak_opis) {
		this.kratak_opis = kratak_opis;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		  SimpleDateFormat termin=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//DODATI VREME 
		  String strDate = termin.format(this.termin);  
//		  System.out.println(this.pacijent.getIme());
		return //"termin:"+this.termin+
				"termin:"+strDate+
				"\nsoba:"+this.soba+
				"\nstatus:"+this.statusPregleda+
				"\nopis:"+this.kratak_opis
				+
				"\nPacijent:"+this.getPacijent().getIme()+
				
				"\nLekar_Pregleda:"+this.lekar.getIme()
				+"\nRacun:"+this.racun
				;
				
				
				//"\nizabraniLekar:"+this.izabraniLekar.getIme()+" "+this.izabraniLekar.getPrezime()
				
				
	


	}

	
}
