package pregled;

import java.sql.Date;

import osobe.Lekar;
import osobe.Pacijent;

public class Pregled {
	protected Pacijent pacijent;
	protected Lekar lekar;
	protected Date termin;
	protected int soba;
	protected Status status;
	protected String kratak_opis;
	
	public Pregled() {
		this.pacijent=null;
		this.lekar=null;
		this.termin=null;
		this.soba=0;
		this.status=null;
		this.kratak_opis="";
	}

	public Pregled(Pacijent pacijent, Lekar lekar, Date termin, int soba, Status status, String kratak_opis) {
		super();
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.termin = termin;
		this.soba = soba;
		this.status = status;
		this.kratak_opis = kratak_opis;
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

	public int getSoba() {
		return soba;
	}

	public void setSoba(int soba) {
		this.soba = soba;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getKratak_opis() {
		return kratak_opis;
	}

	public void setKratak_opis(String kratak_opis) {
		this.kratak_opis = kratak_opis;
	}
	
	
}
