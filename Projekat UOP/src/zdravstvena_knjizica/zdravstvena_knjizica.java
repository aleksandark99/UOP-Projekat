package zdravstvena_knjizica;

import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;


public class zdravstvena_knjizica {
	private int broj;
	private Date datumIsteka; 
	private KategorijaOsiguranja kategorijaOsiguranja;
	private boolean state;
	
	public zdravstvena_knjizica() {
		this.broj=0;
		this.datumIsteka=null;
		this.kategorijaOsiguranja=null;
		this.state=true;

		
		
	}
	

	public zdravstvena_knjizica(int broj, Date datumIsteka, KategorijaOsiguranja kategorijaOsiguranja,boolean state ) {
		super();
		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.kategorijaOsiguranja = kategorijaOsiguranja;
		this.state=state;
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


	public boolean isState() {
		return state;
	}


	public void setState(boolean state) {
		this.state = state;
	}


	@Override
	public String toString() {
		SimpleDateFormat datumisteka=new SimpleDateFormat("DD/mm/yyyy");//DODATI VREME 
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
