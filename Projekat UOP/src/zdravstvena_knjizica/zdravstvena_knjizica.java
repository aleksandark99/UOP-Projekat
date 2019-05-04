package zdravstvena_knjizica;

import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;


public class zdravstvena_knjizica {
	protected int broj;
	protected Date datumIsteka; 
	protected KategorijaOsiguranja kategorijaOsiguranja;
	
	
	public zdravstvena_knjizica() {
		this.broj=0;
		this.datumIsteka=null;
		this.kategorijaOsiguranja=null;

		
		
	}
	

	public zdravstvena_knjizica(int broj, Date datumIsteka, KategorijaOsiguranja kategorijaOsiguranja ) {
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


	@Override
	public String toString() {
		SimpleDateFormat datumisteka=new SimpleDateFormat("dd/mm/yyyy");//DODATI VREME 
		String strDate = datumisteka.format(datumIsteka);  
		//istek =datumisteka.parse(termin1);
		// TODO Auto-generated method stub
		return "broj:"+this.broj+
				//"\ndatum_isteka:"+this.datumIsteka+
				"\ndatum_isteka:"+strDate+

				"\nkategorija osiguranja"+this.kategorijaOsiguranja
				
				
				;
	}
	
	

}
