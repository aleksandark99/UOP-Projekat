package osobe;

public class Lekar extends ID_Korisnika {
	
	private double plata;
	private String specijalizacija;
	private String sluzba;
	
	public Lekar() {
		this.plata=0;
		this.specijalizacija="";
		this.sluzba="";/// ZA SADA
		
	}

	public Lekar(String ime, String prezime, String jmbg, String pol, String adresa, String broj_telefona,
			String korisnicko_ime, String lozinka, String uloga, double plata, String specijalizacija, String sluzba) {
		
		super(ime, prezime, jmbg, pol, adresa, broj_telefona, korisnicko_ime, lozinka, uloga);
		
		this.plata = plata;
		this.specijalizacija = specijalizacija;
		this.sluzba = sluzba;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public String getSluzba() {
		return sluzba;
	}

	public void setSluzba(String sluzba) {
		this.sluzba = sluzba;
	}
	
	
	
	
	

}
