package osobe;

public class Lekar extends Zaposleni {
	
	protected String specijalizacija;
	
	
	public Lekar() {
		this.specijalizacija="";
	}


	public Lekar(String specijalizacija) {
		super();
		this.specijalizacija = specijalizacija;
	}


	public String getSpecijalizacija() {
		return specijalizacija;
	}


	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	

	
	
	
	

}
