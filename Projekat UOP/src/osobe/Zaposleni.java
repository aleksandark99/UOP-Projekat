package osobe;

public abstract class Zaposleni extends ID_Korisnika {
	protected double plata;
	protected Sluzba sluzbaZaposlenog;
	
	
	public Zaposleni() {
		this.plata=0;
		this.sluzbaZaposlenog=null;
	}


	public Zaposleni(String ime, String prezime, String jmbg, String pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Uloga uloga, double plata, Sluzba sluzbaZaposlenog) {
		super(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
		this.plata = plata;
		this.sluzbaZaposlenog = sluzbaZaposlenog;
	}


	public double getPlata() {
		return plata;
	}


	public void setPlata(double plata) {
		this.plata = plata;
	}


	public Sluzba getSluzbaZaposlenog() {
		return sluzbaZaposlenog;
	}


	public void setSluzbaZaposlenog(Sluzba sluzbaZaposlenog) {
		this.sluzbaZaposlenog = sluzbaZaposlenog;
	}
	
	
}
