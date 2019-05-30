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
	private boolean state;
	
	public Pregled() {
		this.pacijent=new Pacijent();
		this.lekar=new Lekar();
		this.termin=null;
		this.soba="";
		this.statusPregleda=StatusPregleda.otkazan;
		this.kratak_opis="";
		this.state=true;
	}

	public Pregled(Pacijent pacijent, Lekar lekar, Date termin, String soba, StatusPregleda statusPregleda, String kratak_opis,boolean state) {
		super();
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.termin = termin;
		this.soba = soba;
		this.statusPregleda = statusPregleda;
		this.kratak_opis = kratak_opis;
		this.state=state;
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
		  SimpleDateFormat termin=new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");//DODATI VREME 
		  String strDate = termin.format(this.termin);  
		return //"termin:"+this.termin+
				"termin:"+strDate+
				"\nsoba:"+this.soba+
				"\nstatus:"+this.statusPregleda+
				"\nopis:"+this.kratak_opis+
				"\nPacijent:"+this.pacijent.getIme()+
				"\nLekar_Pregleda:"+this.lekar.getIme();
				
				
				//"\nizabraniLekar:"+this.izabraniLekar.getIme()+" "+this.izabraniLekar.getPrezime()
				
				
	


	}

	
}
