package zdravstvena_knjizica;


public class zdravstvena_knjizica {
	protected int broj;
	protected String datumIsteka; //Date po potrebi ?
	protected int kategorijaOsiguranja;
	
	public zdravstvena_knjizica() {
		this.broj=0;
		this.datumIsteka="";
		this.kategorijaOsiguranja=0;
		
	}
	

	public zdravstvena_knjizica(int broj, String datumIsteka, int kategorijaOsiguranja) {
		super();
		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.kategorijaOsiguranja = kategorijaOsiguranja;
	}


	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(String datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	public int getKategorijaOsiguranja() {
		return kategorijaOsiguranja;
	}

	public void setKategorijaOsiguranja(int kategorijaOsiguranja) {
		this.kategorijaOsiguranja = kategorijaOsiguranja;
	}
	
	

}
