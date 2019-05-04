package domZdravlja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import osobe.Lekar;
import osobe.Medicinska_Sestra;
import osobe.Pacijent;
import osobe.Sluzba;
import osobe.Uloga;
import pregled.Pregled;
import pregled.StatusPregleda;
import zdravstvena_knjizica.KategorijaOsiguranja;
import zdravstvena_knjizica.zdravstvena_knjizica;
//import java.sql.Date;
import java.util.Date;


//import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Locale;
public class DomZdravlja {
	private ArrayList<Lekar> lekari;
	private ArrayList<Medicinska_Sestra> medicinskaSestre;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<Pregled> pregledi;
	private ArrayList<zdravstvena_knjizica> zdravstvenaKnjizice;
	
	
	public DomZdravlja() {
		this.lekari=new ArrayList<Lekar>();
		this.medicinskaSestre=new ArrayList<Medicinska_Sestra>();
		this.pacijenti=new ArrayList<Pacijent>();
		this.pregledi=new ArrayList<Pregled>();
		this.zdravstvenaKnjizice =new ArrayList<zdravstvena_knjizica>();
	}
	
	public ArrayList<Lekar> getLekare(){
		return lekari;
	}
	public ArrayList<Medicinska_Sestra> getSestre(){
		return medicinskaSestre;
	}
	public ArrayList<Pacijent>getPacijente(){
		return pacijenti;
	}
	public ArrayList<Pregled> getPreglede(){
		return pregledi;
	}
	public ArrayList<zdravstvena_knjizica> getKnjizice(){
		return zdravstvenaKnjizice;
	}
	
	public void dodajLekara(Lekar lekar) {
		this.lekari.add(lekar);
	}
	public void dodajSestru(Medicinska_Sestra sestra) {
		this.medicinskaSestre.add(sestra);
		
	}
	public void dodajPacijenta(Pacijent pacijent) {
		this.pacijenti.add(pacijent);
		
	}
	public void dodajPreglede(Pregled pregled) {
		this.pregledi.add(pregled);
		
	}
	public void dodajKnjizice(zdravstvena_knjizica knjizica) {
		this.zdravstvenaKnjizice.add(knjizica);
	}
	
	public void obrisiLekara(Lekar lekar) {
		this.lekari.remove(lekar);
	}
	public void obrisiSestru(Medicinska_Sestra sestra) {
		this.medicinskaSestre.remove(sestra);
	}
	public void obrisiPacijenta(Pacijent pacijent) {
		this.pacijenti.remove(pacijent);
	}
	public void obrisiPreglede(Pregled pregled) {
		this.pregledi.remove(pregled);
	}
	public void obrisiKnjizicu(zdravstvena_knjizica knjizica) {
		this.zdravstvenaKnjizice.remove(knjizica);
	}
   
	public void ucitajLekare(String filename) {
		try {
			File file = new File("src/files/" + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				
				String[] split = line.split("\\|");
				if (split[8].equals("Lekar")) {
					String ime=split[0];
					String prezime=split[1];
					String jmbg=split[2];
					String pol=split[3];
					String adresa=split[4];
					String brojTelefona=split[5];
					String korisnickoIme=split[6];
					String lozinka=split[7];
					Uloga uloga=Uloga.valueOf(split[8]);
					Sluzba sluzba=Sluzba.valueOf(split[9]);
					String specijalizacija=split[10];
					double plata =  Double.parseDouble(split[11]);
					Lekar lekar = new Lekar(ime,prezime,jmbg,pol,adresa,brojTelefona,korisnickoIme,lozinka,uloga,plata,sluzba,specijalizacija);
					lekari.add(lekar);
				}

				
			}
			reader.close();

		}catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o lekaru");
			e.printStackTrace();
		}	
		
		

	}
	public void ucitajKnjizice(String filename) {
		try {
			File file = new File("src/files/" + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				
				String[] split = line.split("\\|");
				int broj=Integer.parseInt(split[0]);
				KategorijaOsiguranja kategorijaosiguranja=KategorijaOsiguranja.valueOf(split[2]);
				String sDate=split[1];
				Date datumistekaknjizice =null;
				
				try {
					SimpleDateFormat datumisteka=new SimpleDateFormat("dd/mm/yyyy");
					datumistekaknjizice =datumisteka.parse(sDate);
				}catch(Exception e){
					
				}
				zdravstvena_knjizica knjizica=new zdravstvena_knjizica(broj, datumistekaknjizice , kategorijaosiguranja);
				zdravstvenaKnjizice.add(knjizica);
				
				
			}
			reader.close();

		}catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o knjizici");
			e.printStackTrace();
		}	
		
		

	}
	public void ucitajSestre(String filename) {
		try {
			File file = new File("src/files/" + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				
				String[] split = line.split("\\|");
				if (split[8].equals("MedicinskaSestra")) {
					String ime=split[0];
					String prezime=split[1];
					String jmbg=split[2];
					String pol=split[3];
					String adresa=split[4];
					String brojTelefona=split[5];
					String korisnickoIme=split[6];
					String lozinka=split[7];
					Uloga uloga=Uloga.valueOf(split[8]);
					Sluzba sluzba=Sluzba.valueOf(split[9]);
					double plata =  Double.parseDouble(split[10]);
					Medicinska_Sestra sestra = new Medicinska_Sestra(ime, prezime, jmbg,
							pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga,plata,sluzba);
					medicinskaSestre.add(sestra);
				}

				
			}
			reader.close();

		}catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o sestri");
			e.printStackTrace();
		}	
		
		

	}
	public void ucitajPreglede(String filename) {
		try {
			File file = new File("src/files/" + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				
					String[] split = line.split("\\|");
					
					String korisnickoImePacijenta=split[0];
					Pacijent pacijent1 = nadjiPacijenta(korisnickoImePacijenta);
					String korisnickoImeLekara=split[1];
					Lekar lekar1 = nadjiLekara(korisnickoImeLekara);
					String opisss=split[5];
					StatusPregleda statusPregleda=StatusPregleda.valueOf(split[4]);
					String soba=split[3];//ostavi string u slucaju da imamo 7A 3B sobe i slicno...
					//
					String termin1=split[2];
					Date termin =null;
					try {
						SimpleDateFormat datumisteka=new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");//DODATI VREME 
						termin =datumisteka.parse(termin1);
					}catch(Exception e){
						
					}
					//
					Pregled pregled = new Pregled(pacijent1, lekar1, termin, soba, statusPregleda, opisss);
					pregledi.add(pregled);
					
					
				

				
			}
			reader.close();

		}catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o pregledu");
			e.printStackTrace();
		}	
		
		

	}
	public void ucitajPacijente(String filename) {
		try {
			File file = new File("src/files/" + filename);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				if (split[8].equals("Pacijent")) {
					String ime=split[0];
					String prezime=split[1];
					String jmbg=split[2];
					String pol=split[3];
					String adresa=split[4];
					String brojTelefona=split[5];
					String korisnickoIme=split[6];
					String lozinka=split[7];
					Uloga uloga=Uloga.valueOf(split[8]);
					String lekarA = split[9];
					Lekar LekarB=nadjiLekara(lekarA);
					int A = Integer.parseInt(split[10]);
					zdravstvena_knjizica knjizica=nadjiKnjizicu(A);
					//
					
					////
				Pacijent pacijent = new Pacijent(ime,prezime,jmbg,pol,adresa,brojTelefona,
						korisnickoIme,lozinka,uloga,LekarB,knjizica);
					
					
				pacijenti.add(pacijent);
					
				}
				
			}
			reader.close();
			
		}catch (IOException e) {
			System.out.println("Greska prilikom snimanja podataka o Pacijentu");
			e.printStackTrace();
		
	}
	}

	public Lekar nadjiLekara(String lekarA){
		for (Lekar lekar : lekari) {
			if (lekar.getKorisnickoIme().equals(lekarA)) {
				return lekar;
			}
		}
		return null;
	}
	public zdravstvena_knjizica nadjiKnjizicu(int brojA){
		for (zdravstvena_knjizica knjizica : zdravstvenaKnjizice) {
			if (knjizica.getBroj()==brojA) {
				return knjizica;
			}
		}
		return null;
	}
	public  Pacijent nadjiPacijenta(String pacijentA) {
		for (Pacijent pacijent : pacijenti) {
			if (pacijent.getKorisnickoIme().equals(pacijentA)) {
				return pacijent;
			}
		}
		return null;
	}
	//Za sad nije potrebna funkcija nadjiSestru ali neka ostane ako zatreba
	public Medicinska_Sestra nadjiSestru(String sestraA) {
		for (Medicinska_Sestra sestra : medicinskaSestre) {
			if (sestra.getKorisnickoIme().equals(sestraA)) {
				return sestra;
			}
	}
		return null;


	}
	//nema potrebe za nadji pregled metodu
	
	public void snimiLekare(String imeFajla) {
		try {
			File file = new File("src/files/" + imeFajla);
			String content = "";
			for (Lekar lekar : lekari) {
				
				  content +=lekar.getIme()+"|"+lekar.getPrezime()+"|"+lekar.getJmbg()+"|"+lekar.getPol()+"|"+
				  lekar.getAdresa()+"|"+lekar.getBrojTelefona()+"|"+lekar.getKorisnickoIme()+
				 "|"+lekar.getLozinka()
				  +"|"+lekar.getUloga()+"|"+lekar.getSluzbaZaposlenog()+"|"+lekar.
				  getSpecijalizacija()+"|"+ lekar.getPlata()+"\n";
				 
				
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja lekara.");
		}
	}




























}
