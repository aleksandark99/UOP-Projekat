package osobe;

import zdravstvena_knjizica.zdravstvena_knjizica;

public class Pacijent extends ID_Korisnika {
	Lekar izabraniLekar;
	zdravstvena_knjizica knjizica;
	
	
	public Pacijent() {
		//napisati ga posle
	}


	public Pacijent(String ime, String prezime, String jmbg, String pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, String uloga, Lekar izabraniLekar, zdravstvena_knjizica knjizica) {
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

	
	
	
	
	
}
