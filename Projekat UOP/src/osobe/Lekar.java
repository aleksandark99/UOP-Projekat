package osobe;


public class Lekar extends Zaposleni {
	
	private String specijalizacija;
	
	
	public Lekar() {
		this.specijalizacija="";
	}


	


	public Lekar(String ime, String prezime, String jmbg, String pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Uloga uloga, double plata, Sluzba sluzbaZaposlenog,
			String specijalizacija) {
		super(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, plata, sluzbaZaposlenog);
		this.specijalizacija = specijalizacija;
	}





	public String getSpecijalizacija() {
		return specijalizacija;
	}


	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}





	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ime:"+this.ime+
				"\nPrezime:"+this.prezime+
				"\njmbg:"+this.jmbg+
				"\npol:"+this.pol+
				"\nadresa:"+this.adresa+
				"\nbrojTelefona:"+this.brojTelefona+
				"\nkorisnickoIme:"+this.korisnickoIme+
				"\nlozinka:"+this.lozinka+
				"\nsluzba:"+this.sluzbaZaposlenog+
				"\nspecijalizacija:"+this.specijalizacija+
				"\nplata:"+this.plata
				
				
				
				;
	


	}
	
	
}
