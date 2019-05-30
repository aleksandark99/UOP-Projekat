package osobe;

public class Medicinska_Sestra extends Zaposleni {

	public Medicinska_Sestra() {
		super();
	}

	public Medicinska_Sestra(String ime, String prezime, String jmbg, String pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Uloga uloga, double plata, Sluzba sluzbaZaposlenog,boolean state) {
		super(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, plata, sluzbaZaposlenog,state);
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
				"\nsluzba"+this.sluzbaZaposlenog+
				"\nplata"+this.plata
				
				
				
				;
	


	}
}
