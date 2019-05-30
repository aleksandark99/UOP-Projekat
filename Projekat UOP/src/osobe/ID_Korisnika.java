package osobe;

public abstract class ID_Korisnika {
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String pol;
	protected String adresa;
	protected String brojTelefona;
	protected String korisnickoIme;
	protected String lozinka;
	protected Uloga uloga;
	/////////////////////////
	protected boolean state;
	
	
	
	
	
	public ID_Korisnika(String ime, String prezime, String jmbg, String pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Uloga uloga, boolean state) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
		this.state = state;
	}

	/*
	 * public ID_Korisnika(String ime, String prezime, String jmbg, String pol,
	 * String adresa, String brojTelefona, String korisnickoIme, String lozinka,
	 * Uloga uloga) { super(); this.ime = ime; this.prezime = prezime; this.jmbg =
	 * jmbg; this.pol = pol; this.adresa = adresa; this.brojTelefona = brojTelefona;
	 * this.korisnickoIme = korisnickoIme; this.lozinka = lozinka; this.uloga =
	 * uloga; }
	 */
	public ID_Korisnika() {
		this.ime ="";
		this.prezime="";
		this.jmbg="";
		this.pol="";
		this.adresa="";
		this.brojTelefona="";
		this.korisnickoIme="";
		this.lozinka="";
		this.uloga=null;
		this.state=true;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	
	
	
}
