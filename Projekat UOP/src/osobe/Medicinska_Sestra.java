package osobe;

public class Medicinska_Sestra extends ID_Korisnika {
	
	protected double plata;
	protected String sluzba;
	
	public Medicinska_Sestra() {
		this.plata=0;
		this.sluzba="";
	}

	public Medicinska_Sestra(String ime, String prezime, String jmbg, String pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, String uloga, double plata, String sluzba) {
		
		super(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
		
		this.plata = plata;
		this.sluzba = sluzba;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getSluzba() {
		return sluzba;
	}

	public void setSluzba(String sluzba) {
		this.sluzba = sluzba;
	}

	
}
