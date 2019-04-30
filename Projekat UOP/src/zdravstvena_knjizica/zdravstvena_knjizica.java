package zdravstvena_knjizica;

import java.sql.Date;

import osobe.Pacijent;

public class zdravstvena_knjizica {
	protected int broj;
	protected Date datumIsteka; //Date po potrebi ?
	protected KategorijaOsiguranja kategorijaOsiguranja;
	protected Pacijent pacijent;
	
	
	public zdravstvena_knjizica() {
		this.broj=0;
		this.datumIsteka=null;
		this.kategorijaOsiguranja=null;
		this.pacijent=new Pacijent();
		
		
	}
	

	public zdravstvena_knjizica(int broj, Date datumIsteka, KategorijaOsiguranja kategorijaOsiguranja, Pacijent pacijent) {
		super();
		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.kategorijaOsiguranja = kategorijaOsiguranja;
		this.pacijent=pacijent;
	}


	public Pacijent getPacijent() {
		return pacijent;
	}


	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}


	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public Date getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(Date datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	public KategorijaOsiguranja getKategorijaOsiguranja() {
		return kategorijaOsiguranja;
	}

	public void setKategorijaOsiguranja(KategorijaOsiguranja kategorijaOsiguranja) {
		this.kategorijaOsiguranja = kategorijaOsiguranja;
	}
	
	

}
