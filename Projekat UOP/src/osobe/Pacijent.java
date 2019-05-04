package osobe;

import zdravstvena_knjizica.zdravstvena_knjizica;

public class Pacijent extends ID_Korisnika {
	protected Lekar izabraniLekar;
	protected zdravstvena_knjizica knjizica;
	
	
	public Pacijent() {
		this.izabraniLekar= new Lekar();
		this.knjizica=new zdravstvena_knjizica();
	}


	public Pacijent(String ime, String prezime, String jmbg, String pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Uloga uloga, Lekar izabraniLekar, zdravstvena_knjizica knjizica) {
		super(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
		this.izabraniLekar = izabraniLekar;
		this.knjizica = knjizica;
	}


	public Lekar getIzabraniLekar() {
		return izabraniLekar;
	}


	public void setIzabraniLekar(Lekar izabraniLekar) {
		this.izabraniLekar = izabraniLekar;
	}


	public zdravstvena_knjizica getKnjizica() {
		return knjizica;
	}


	public void setKnjizica(zdravstvena_knjizica knjizica) {
		this.knjizica = knjizica;
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
				"\nbrojKnjizice:"+this.knjizica.getBroj()+
				"\nizabraniLekar:"+this.izabraniLekar.getIme()+" "+this.izabraniLekar.getPrezime()
				
				;
	


	}
	
	
	
	
	
}
