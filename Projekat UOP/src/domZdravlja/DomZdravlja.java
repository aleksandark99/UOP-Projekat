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
import java.util.Scanner;

import javax.net.ssl.SSLEngineResult.Status;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import java.text.DateFormat;
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
		//this.lekari.remove(lekar);
		lekar.setState(false);
	}
	public void obrisiSestru(Medicinska_Sestra sestra) {
		//this.medicinskaSestre.remove(sestra);
		sestra.setState(false);
	}
	public void obrisiPacijenta(Pacijent pacijent) {
		//this.pacijenti.remove(pacijent);
		pacijent.setState(false);
		pacijent.getKnjizica().setState(false);
		
		//kada obrisemo pacijenta obrisemo i knjizicu jer nam nije potrebna
		
	}
	public void obrisiPreglede(Pregled pregled) {
		//this.pregledi.remove(pregled);
		pregled.setState(false);
	}
	public void obrisiKnjizicu(zdravstvena_knjizica knjizica) {
		//this.zdravstvenaKnjizice.remove(knjizica);
		knjizica.setState(false);
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
					boolean state=Boolean.parseBoolean(split[12]); 
					Lekar lekar = new Lekar(ime,prezime,jmbg,pol,adresa,brojTelefona,korisnickoIme,lozinka,uloga,plata,sluzba,specijalizacija,state);
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
				boolean state = Boolean.parseBoolean(split[3]); 
				Date datumistekaknjizice =null;
				
				try {
					SimpleDateFormat datumisteka=new SimpleDateFormat("dd/mm/yyyy");
					datumistekaknjizice =datumisteka.parse(sDate);
				}catch(Exception e){
					
				}
				zdravstvena_knjizica knjizica=new zdravstvena_knjizica(broj, datumistekaknjizice , kategorijaosiguranja,state);
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
					boolean state = Boolean.parseBoolean(split[11]); 
					Medicinska_Sestra sestra = new Medicinska_Sestra(ime, prezime, jmbg,
							pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga,plata,sluzba,state);
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
					boolean state = Boolean.parseBoolean(split[6]); 
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
					Pregled pregled = new Pregled(pacijent1, lekar1, termin, soba, statusPregleda, opisss,state);
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
					boolean state = Boolean.parseBoolean(split[11]); 
					//
					
					////
				Pacijent pacijent = new Pacijent(ime,prezime,jmbg,pol,adresa,brojTelefona,
						korisnickoIme,lozinka,uloga,LekarB,knjizica,state);
					
					
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
	//Za sada nije potrebna funkcija nadjiSestru ali neka ostane ako zatreba
	public Medicinska_Sestra nadjiSestru(String sestraA) {
		for (Medicinska_Sestra sestra : medicinskaSestre) {
			if (sestra.getKorisnickoIme().equals(sestraA)) {
				return sestra;
			}
	}
		return null;


	}
	//za sadanema potrebe za nadji pregled metodu
	
	public void snimiLekare(String imeFajla) {
		try {
			File file = new File("src/files/" + imeFajla);
			String content = "";
			for (Lekar lekar : lekari) {
				
				  content +=lekar.getIme()+"|"+lekar.getPrezime()+"|"+lekar.getJmbg()+"|"+lekar.getPol()+"|"+
				  lekar.getAdresa()+"|"+lekar.getBrojTelefona()+"|"+lekar.getKorisnickoIme()+
				 "|"+lekar.getLozinka()
				  +"|"+lekar.getUloga()+"|"+lekar.getSluzbaZaposlenog()+"|"+lekar.
				  getSpecijalizacija()+"|"+ lekar.getPlata()+"|"+lekar.isState()+"\n";
				 
				
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja lekara.");
		}
	}
	public void snimiSestre(String imeFajla) {
		try {
			File file = new File("src/files/" + imeFajla);
			String content = "";
			for (Medicinska_Sestra sestra : medicinskaSestre) {
				
				  content +=sestra.getIme()+"|"+sestra.getPrezime()+"|"+sestra.getJmbg()+"|"+sestra.getPol()+"|"+
						  sestra.getAdresa()+"|"+sestra.getBrojTelefona()+"|"+sestra.getKorisnickoIme()+
				 "|"+sestra.getLozinka()
				  +"|"+sestra.getUloga()+"|"+sestra.getSluzbaZaposlenog()+"|"+
				  sestra.getPlata()+"|"+sestra.isState()+"\n";
				 
				
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja Sestre.");
		}
	}
	public void snimiKnjizice(String imeFajla) {
		try {
			File file = new File("src/files/" + imeFajla);
			String content = "";
			for (zdravstvena_knjizica knjizica : zdravstvenaKnjizice) {
				SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
				String dateString = df.format( knjizica.getDatumIsteka() );
				
			//	Date date = df.parse(toString(knjizica.getDatumIsteka()));
				  //content +=knjizica.getBroj()+"|"+knjizica.getDatumIsteka()+"|"+knjizica.getKategorijaOsiguranja()+"\n";
				content +=knjizica.getBroj()+"|"+dateString+"|"+knjizica.getKategorijaOsiguranja()+"|"+knjizica.isState()+"\n";
				
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja Knjizice.");
		}
	}
	public void snimiPacijente(String imeFajla) {
		try {
			File file = new File("src/files/" + imeFajla);
			String content = "";
			for (Pacijent pacijent : pacijenti) {
				
				  content +=pacijent.getIme()+"|"+pacijent.getPrezime()+"|"+pacijent.getJmbg()+"|"+pacijent.getPol()+"|"+
						  pacijent.getAdresa()+"|"+pacijent.getBrojTelefona()+"|"+pacijent.getKorisnickoIme()+
				 "|"+pacijent.getLozinka()
				  +"|"+pacijent.getUloga()+"|"+pacijent.getIzabraniLekar().getKorisnickoIme()+"|"+pacijent.getKnjizica().getBroj()+"|"+pacijent.isState()+"\n";
				 
				
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja Pacijenta.");
		}
	}	
	public void snimiPreglede(String imeFajla) {
		try {
			File file = new File("src/files/" + imeFajla);
			String content = "";
			for (Pregled pregled : pregledi) {
				SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
				String dateString = df.format( pregled.getTermin() );
				
				
				  content +=pregled.getPacijent().getKorisnickoIme()+"|"+
				
						  
				pregled.getLekar().getKorisnickoIme()+
						  "|"+dateString+"|"+pregled.getSoba()+"|"+pregled.getStatus()+"|"+
						  pregled.getKratak_opis()+"|"+pregled.isState()+"\n";
				
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja Pregleda.");
		}
	}
	



///////////////////////////////////////
	//FUNKCIJE ZA IZMENU(UPDATE)//
	Scanner keyboard = new Scanner(System.in);
	
	//IME
	public void updateImePacijenta(Pacijent pacijent) {
		System.out.println("unesi novo ime pacijenta");
		String userInput=keyboard.nextLine();		
		pacijent.setIme(userInput);
		System.out.println("Novo ime pacijenta:"+pacijent.getIme());

	}
	public void updateImeLekara(Lekar lekar) {
		System.out.println("unesi novo ime lekara");
		String userInput=keyboard.nextLine();		
		lekar.setIme(userInput);
		System.out.println("Novo ime lekara:"+lekar.getIme());

	}
	public void updateImeSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novo ime sestre");
		String userInput=keyboard.nextLine();		
		sestra.setIme(userInput);
		System.out.println("Novo ime sestre:"+sestra.getIme());

	}
	
	//PREZIME
	public void updatePrezimePacijenta(Pacijent pacijent) {
		System.out.println("unesi novo prezime pacijenta");
		String userInput=keyboard.nextLine();		
		pacijent.setPrezime(userInput);
		System.out.println("Novo preime pacijenta:"+pacijent.getPrezime());

	}
	public void updatePrezimeLekara(Lekar lekar) {
		System.out.println("unesi novo prezime lekara");
		String userInput=keyboard.nextLine();		
		lekar.setPrezime(userInput);
		System.out.println("Novo prezime lekara:"+lekar.getPrezime());

	}
	public void updatePrezimeSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novo prezime sestre");
		String userInput=keyboard.nextLine();		
		sestra.setPrezime(userInput);
		System.out.println("Novo prezime sestre:"+sestra.getPrezime());

	}

	//JMBG
	public void updateJMBGPacijenta(Pacijent pacijent) {
		System.out.println("unesi novi JMBG pacijenta");
		String userInput=keyboard.nextLine();		
		pacijent.setJmbg(userInput);
		System.out.println("Novi jmbg pacijenta:"+pacijent.getJmbg());

	}
	public void updateJMBGLekara(Lekar lekar) {
		System.out.println("unesi novi JMBG lekara");
		String userInput=keyboard.nextLine();		
		lekar.setIme(userInput);
		System.out.println("Novi JMBG lekara:"+lekar.getJmbg());

	}
	public void updateJMBGSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novi JMBG sestre");
		String userInput=keyboard.nextLine();		
		sestra.setJmbg(userInput);
		System.out.println("Novi JMBG sestre:"+sestra.getJmbg());

	}	

	//POL
	public void updatePolacijenta(Pacijent pacijent) {
		System.out.println("unesi novi pol pacijenta");
		String userInput=keyboard.nextLine();		
		pacijent.setPol(userInput);
		System.out.println("Novi pol pacijenta:"+pacijent.getPol());

	}
	public void updatePolLekara(Lekar lekar) {
		System.out.println("unesi novi pol lekara");
		String userInput=keyboard.nextLine();		
		lekar.setPol(userInput);
		System.out.println("Novi pol lekara:"+lekar.getPol());

	}
	public void updatePolSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novi pol sestre");
		String userInput=keyboard.nextLine();		
		sestra.setPol(userInput);
		System.out.println("Novi pol sestre:"+sestra.getPol());	
	}	

	//ADRESA
	public void updateAdresuPacijenta(Pacijent pacijent) {
		System.out.println("unesi novu adresu pacijenta");
		String userInput=keyboard.nextLine();		
		pacijent.setAdresa(userInput);
		System.out.println("Nova  adresa pacijenta:"+pacijent.getAdresa());

	}
	public void updateAdresuLekara(Lekar lekar) {
		System.out.println("unesi novu adresu lekara");
		String userInput=keyboard.nextLine();		
		lekar.setAdresa(userInput);
		System.out.println("Nova adresa lekara:"+lekar.getAdresa());

	}
	public void updateAdresuSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novu adresu sestre");
		String userInput=keyboard.nextLine();		
		sestra.setAdresa(userInput);
		System.out.println("Nova adresa sestre:"+sestra.getAdresa());	
	}
	
	//BROJ TELEFONA
	public void updateBrojPacijenta(Pacijent pacijent) {
		System.out.println("unesi novi Broj pacijenta");
		String userInput=keyboard.nextLine();		
		pacijent.setBrojTelefona(userInput);
		System.out.println("Novi Broj pacijenta:"+pacijent.getBrojTelefona());

	}
	public void updateBrojLekara(Lekar lekar) {
		System.out.println("unesi novi Broj lekara");
		String userInput=keyboard.nextLine();		
		lekar.setBrojTelefona(userInput);
		System.out.println("Novi Broj lekara:"+lekar.getBrojTelefona());

	}
	public void updateBrojSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novi Broj sestre");
		String userInput=keyboard.nextLine();		
		sestra.setBrojTelefona(userInput);
		System.out.println("Novi Broj sestre:"+sestra.getBrojTelefona());	
	}
	////KORISNICKO IME NECEMO MENJATI
	
	//LOZINKA
	public void updateLozinkuPacijenta(Pacijent pacijent) {
		System.out.println("unesi novu lozinku pacijenta");
		String userInput=keyboard.nextLine();		
		pacijent.setLozinka(userInput);
		System.out.println("Nova  lozinka pacijenta:"+pacijent.getLozinka());

	}
	public void updateLozinkuLekara(Lekar lekar) {
		System.out.println("unesi novu lozinku lekara");
		String userInput=keyboard.nextLine();		
		lekar.setLozinka(userInput);
		System.out.println("Nova lozinka lekara:"+lekar.getLozinka());

	}
	public void updateLozinkuSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novu lozinku sestre");
		String userInput=keyboard.nextLine();		
		sestra.setLozinka(userInput);
		System.out.println("Nova lozinka sestre:"+sestra.getLozinka());	
	}
	
	//PLATA
	public void updatePlataLekara(Lekar lekar) {
		System.out.println("unesi novu platu lekara");
//		int userInput=keyboard.nextInt();
		double userInput=keyboard.nextDouble();

		lekar.setPlata(userInput);
		System.out.println("Nova platu lekara:"+lekar.getPlata());

	}
	public void updatePlatuSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novu platu sestre");
//		int userInput=keyboard.nextInt();		
		double userInput=keyboard.nextDouble();
		sestra.setPlata(userInput);
		System.out.println("Nova platu sestre:"+sestra.getPlata());	
	}
	
	//SLUZBA
	public void updateSluzbuSestre(Medicinska_Sestra sestra) {
		System.out.println("unesi novu sluzbu sestre");
		System.out.println("1:sluzbaOpsteMedicine \n2.sluzbaZaPravneIekonomskePoslove \n3.sluzbaZaTehnickePoslove \n4.SluzbaZdravstveneZastiteDece \n5.sluzbaZdravstvneneZastiteRadnika \n6.stomatoloskaSluzba");
		String userInput=keyboard.nextLine();
		switch (userInput) {
		case "1":
			sestra.setSluzbaZaposlenog(Sluzba.sluzbaOpsteMedicine);
			break;
		case "2":
			sestra.setSluzbaZaposlenog(Sluzba.sluzbaZaPravneIekonomskePoslove);
			break;
		case "3":
			sestra.setSluzbaZaposlenog(Sluzba.sluzbaZaTehnickePoslove);
			break;
		case "4":
			sestra.setSluzbaZaposlenog(Sluzba.SluzbaZdravstveneZastiteDece);
			break;
		case "5":
			sestra.setSluzbaZaposlenog(Sluzba.sluzbaZdravstvneneZastiteRadnika);
			break;
		case "6":
			sestra.setSluzbaZaposlenog(Sluzba.stomatoloskaSluzba);		
			break;
		
		default:
			System.out.println("Ne postojeca opcija");
	}
		System.out.println("Nova sluzba sestre:"+sestra.getSluzbaZaposlenog());	
	}	
	public void updateSluzbuLekara(Lekar lekar) {
		System.out.println("unesi novu sluzbu lekara");
		System.out.println("1:sluzbaOpsteMedicine \n2.stomatoloskaSluzba \n3.sluzbaZdravstvneneZastiteRadnika \n4.SluzbaZdravstveneZastiteDece");
		String userInput=keyboard.nextLine();
		switch (userInput) {
		case "1":
			lekar.setSluzbaZaposlenog(Sluzba.sluzbaOpsteMedicine);
			break;

		case "2":
			lekar.setSluzbaZaposlenog(Sluzba.stomatoloskaSluzba);	
			break;

		case "3":
			lekar.setSluzbaZaposlenog(Sluzba.sluzbaZdravstvneneZastiteRadnika);
			break;

		case "4":
			lekar.setSluzbaZaposlenog(Sluzba.SluzbaZdravstveneZastiteDece);
			break;
		
		
		default:
			System.out.println("Ne postojeca opcija");
	}
		System.out.println("Nova sluzba lekara:"+lekar.getSluzbaZaposlenog());	
	}
	
	//SPECIJALIZACIJA
	public void updateSpecijalizacijuLekara(Lekar lekar) {
		System.out.println("unesi novu specijalizaciju lekara");
		String userInput=keyboard.nextLine();		
		lekar.setSpecijalizacija(userInput);
		System.out.println("Nova specijalizaciju lekara:"+lekar.getSpecijalizacija());

	}
	
	//IZABRANI LEKAR PACIJENTA
	public void updateIzabranogLekara(Pacijent pacijent,Lekar lekar) {
		System.out.println("Postavljanje novog izabranog lekara...");
		
		pacijent.setIzabraniLekar(lekar);
		
		System.out.println("Novi izabrani lekar je :"+ pacijent.getIzabraniLekar().getIme());
	}

	//DATUM PREGLEDA
	public void updateDatumPregleda(Pregled pregled) {
		System.out.println("unesi novi datum pregleda u formatu dd/MM/yyyy HH:mm:ss");
		String userInput=keyboard.nextLine();	
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date noviTermin = null;
		//String datum="5/5/1998 14:22:33";
	    try {  	    	
	    	noviTermin = sdf.parse(userInput);	       
	    } catch (Exception e) {}
		pregled.setTermin(noviTermin);
		System.out.println("Novi termin pregleda:"+pregled.getTermin());	
			}
	
	//SOBA PREGLEDA
	public void updateSobuPregleda(Pregled pregled) {
		System.out.println("unesi novu sobu pregleda");
		String userInput=keyboard.nextLine();	
		pregled.setSoba(userInput);
		System.out.println("Nova soba pregleda "+pregled.getSoba());	
	}
	
	//OPIS PREGLEDA
	public void updateOpisPregleda(Pregled pregled) {
		System.out.println("unesi novi opis pregleda");
		String userInput=keyboard.nextLine();	
		pregled.setKratak_opis(userInput);
		System.out.println("Novi opis pregleda "+pregled.getKratak_opis());	
	}
	
	//STATUS PREGLEDA
	public void updateStatusPregleda(Pregled pregled) {//OVA FUNKCIJA NECE OVAKO RADITI OVO JE SAMO ZA TESTIRANJE LOGIKE IZA FUNKCIJE || 
		System.out.println("unesi novi status pregleda");
		System.out.println("1:otkazan \n2.zakazan \n3.zatrazen \n4.zavrsen");

		String userInput=keyboard.nextLine();	

		switch (userInput) {
		case "1":
			pregled.setStatus(StatusPregleda.otkazan);
			break;
		case "2":
			pregled.setStatus(StatusPregleda.zakazan);
			break;
		case "3":		
			pregled.setStatus(StatusPregleda.zatrazen);
			break;
		case "4":
			pregled.setStatus(StatusPregleda.zavrsen);
			break;

		}
		System.out.println("Novi status pregleda "+pregled.getStatus());	
	}
	
	//DATUM ISTEKA KNJIZICE
	public void updateDatumIstekaKnjizice(Pacijent pacijent) {
		System.out.println("unesi novi datum isteka u formatu dd/MM/yyyy HH:mm:ss");
		String userInput=keyboard.nextLine();	
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date noviDatumIsteka = null;
		//String datum="5/5/1998 14:22:33";
	    try {  	    	
	    	noviDatumIsteka = sdf.parse(userInput);	       
	    } catch (Exception e) {}
		pacijent.getKnjizica().setDatumIsteka(noviDatumIsteka);
		System.out.println("Novi datum isteka knjizice :"+ pacijent.getKnjizica().getDatumIsteka());
	}
	
	//KATEGORIJA OSIGURANJA
	public void updateKategorijuOsiguranja(Pacijent pacijent) {
		System.out.println("unesi novu kategoriju osiguranja");
		System.out.println("1:Prva \n2.Druga \n3.Treca");
		String userInput=keyboard.nextLine();	

		switch (userInput) {
		case "1":
			pacijent.getKnjizica().setKategorijaOsiguranja(KategorijaOsiguranja.prva);
			break;
		case "2":
			pacijent.getKnjizica().setKategorijaOsiguranja(KategorijaOsiguranja.druga);
			break;
		case "3":		
			pacijent.getKnjizica().setKategorijaOsiguranja(KategorijaOsiguranja.treca);	
			break;
			}
		System.out.println("Novi kategorija osiguranja:"+ pacijent.getKnjizica().getKategorijaOsiguranja());
	}
	
	//DODAVANJE//
	
	public void dodajNovogPacijenta() {
		
	
		Scanner unoss = new Scanner(System.in);

		System.out.println("Unesi parametre za pacijenta");
		System.out.println("ime:");
		String ime=unoss.nextLine();	
		System.out.println("prezime:");
		String prezime=unoss.nextLine();	
		System.out.println("jmbg:");
		String jmbg=unoss.nextLine();	
		System.out.println("pol:");
		String pol=unoss.nextLine();	
		System.out.println("adresa:");
		String adresa=unoss.nextLine();	
		System.out.println("brojTelefona:");
		String brojTelefona=unoss.nextLine();	
		System.out.println("korisnickoIme:");
		String korisnickoIme=keyboard.nextLine();	
		System.out.println("lozinka:");
		String lozinka =unoss.nextLine();	
		Uloga  uloga=Uloga.Pacijent;
		System.out.println("Izaberite lekara");
		for(Lekar lekar : lekari) {
			if(lekar.isState()==true) {			
			System.out.println(lekar + "\n");
			}			
		}
		System.out.println("Unesite korisnicko ime lekara");
		String strLekar = unoss.nextLine();
		Lekar izabraniLekar=nadjiLekara(strLekar);
		zdravstvena_knjizica knjizica = new zdravstvena_knjizica();
		//
		Scanner dd = new Scanner(System.in);

		System.out.println("Unesi Datum isteka knjizice dd/mm/yyyy");
		//
	//	dd.hasNextLine();
		String userInput=dd.nextLine();
		
		
		
		
		
		DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date datumIsteka = null;
	    try {  	    	
	    	datumIsteka = sdf.parse(userInput);	       
	    } catch (Exception e) {}
	    //
		System.out.println("Unesi broj kjnizice");
		int broj = unoss.nextInt();
		knjizica.setBroj(broj);
		System.out.println("Izaneri kategoriju osiguranja \n1.Prva \n2.Druga \n3.Treca");
		int izbor = unoss.nextInt();
		KategorijaOsiguranja kat=null;
		if (izbor==1) {
			kat=KategorijaOsiguranja.prva;
		}else if(izbor==2) {
			kat=KategorijaOsiguranja.druga;			
		}else if(izbor==3){
			kat=KategorijaOsiguranja.treca;

		}
		knjizica.setKategorijaOsiguranja(kat);
		unoss.close();

	    knjizica.setDatumIsteka(datumIsteka);
		//
		//Pacijent pacijent = new Pacijent(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, izabraniLekar, knjizica, true);
		//Pacijent pacijent = new Pacijent();
		Pacijent pacijent = new Pacijent(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, izabraniLekar, knjizica, true);
		pacijenti.add(pacijent);
		zdravstvenaKnjizice.add(pacijent.getKnjizica());
		unoss.close();
		
	}
	
	//PRAVLJENJE KNJIZICE
	public zdravstvena_knjizica Novaknjizica() {
		System.out.println("Broj knjizice:");
		int broj = keyboard.nextInt();	
    	
		
		String poludecu = keyboard.nextLine();

		System.out.println("UNESI Datum isteka: dd/MM/yyyy HH:mm:ss");				
		System.out.println("GGGGGGGG");
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 //   	String userInput=keyboard.nextLine();		

		Date datumIsteka = null;
	    try {
	    	datumIsteka = sdf.parse(poludecu);	       
	    } catch (Exception e) {}
		System.out.println("gggg");
		System.out.println("Karegorija osiguranja:");
		
//		KategorijaOsiguranja kato =nadjiOsiguranje();

		
		zdravstvena_knjizica knjizica = new zdravstvena_knjizica(broj, datumIsteka, KategorijaOsiguranja.druga, true);
//	    zdravstvena_knjizica knjizica = new zdravstvena_knjizica();
		return knjizica;
	}
	
	//IZABIR KATEGORIJE OSIGURANJA
	public KategorijaOsiguranja nadjiOsiguranje() {
		KategorijaOsiguranja kato=null;
		System.out.println("UNESI KATEGORIJU OSIGRANJA");
		String izabir=keyboard.nextLine();	

		switch (izabir) {
		case "1":
			kato=KategorijaOsiguranja.prva;
			break;
		case "2":
			kato=KategorijaOsiguranja.druga;
			break;
		case "3":		
			kato=KategorijaOsiguranja.treca;
			
			break;
			}
		return kato;
	}
	
	//DODAJ LEKARA
	public void dodajNovogLekara() {
		Scanner unoss = new Scanner(System.in);

		System.out.println("ime:");
		String ime=keyboard.nextLine();	
		System.out.println("prezime:");
		String prezime=keyboard.nextLine();	
		System.out.println("jmbg:");
		String jmbg=keyboard.nextLine();	
		System.out.println("pol:");
		String pol=keyboard.nextLine();	
		System.out.println("adresa:");
		String adresa=keyboard.nextLine();	
		System.out.println("brojTelefona:");
		String brojTelefona=keyboard.nextLine();	
		System.out.println("korisnickoIme:");
		String korisnickoIme=keyboard.nextLine();	
		System.out.println("lozinka:");
		String lozinka =keyboard.nextLine();	
		System.out.println("plata:");
		double plata =keyboard.nextDouble();	
		Uloga uloga =Uloga.Lekar;
		System.out.println("specijalizacija:");
		String specijalizacija  =unoss.nextLine(); 
		System.out.println("Unesi sluzbu \n 1:sluzbaOpsteMedicine \n2.stomatoloskaSluzba \n3.sluzbaZdravstvneneZastiteRadnika \n4.SluzbaZdravstveneZastiteDece.");
		//Sluzba sluzba = Sluzba.sluzbaOpsteMedicine;
		int izbor = unoss.nextInt();
//		Sluzba sluzba=null;
		Sluzba sluzba=Sluzba.sluzbaOpsteMedicine;
		if(izbor==1) {
			 sluzba=Sluzba.sluzbaOpsteMedicine;
		}else if (izbor==2) {
			 sluzba=Sluzba.stomatoloskaSluzba;	
			 System.out.println(sluzba);
		}else if (izbor==3) {
			 sluzba=Sluzba.sluzbaZdravstvneneZastiteRadnika;
			
		}else if (izbor==4) {
			 sluzba=Sluzba.SluzbaZdravstveneZastiteDece;
			
		}
		else {
			 sluzba=Sluzba.sluzbaOpsteMedicine;
		}
		
		unoss.close();
		//
		
		
		
		Lekar lekar = new Lekar(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, plata, sluzba, specijalizacija, true);
		lekari.add(lekar);
	}

	//DODAJ SESTRU	
	public void dodajNovuSestru() {
		Scanner unoss = new Scanner(System.in);

		System.out.println("ime:");
		String ime=keyboard.nextLine();	
		System.out.println("prezime:");
		String prezime=keyboard.nextLine();	
		System.out.println("jmbg:");
		String jmbg=keyboard.nextLine();	
		System.out.println("pol:");
		String pol=keyboard.nextLine();	
		System.out.println("adresa:");
		String adresa=keyboard.nextLine();	
		System.out.println("brojTelefona:");
		String brojTelefona=keyboard.nextLine();	
		System.out.println("korisnickoIme:");
		String korisnickoIme=keyboard.nextLine();	
		System.out.println("lozinka:");
		String lozinka =keyboard.nextLine();	
		System.out.println("plata:");
		double plata =keyboard.nextDouble();	
		Uloga uloga =Uloga.MedicinskaSestra;

		System.out.println("Unesi sluzbu \n1:sluzbaOpsteMedicine \n2.stomatoloskaSluzba \n3.sluzbaZdravstvneneZastiteRadnika \n4.SluzbaZdravstveneZastiteDece \n5.sluzbaZaTehnickePoslove \n6.sluzbaZaPravneIekonomskePoslove");
		//Sluzba sluzba = Sluzba.sluzbaOpsteMedicine;
		int izbor = unoss.nextInt();
//		Sluzba sluzba=null;
		Sluzba sluzba=Sluzba.sluzbaOpsteMedicine;
		if(izbor==1) {
			 sluzba=Sluzba.sluzbaOpsteMedicine;
		}else if (izbor==2) {
			 sluzba=Sluzba.stomatoloskaSluzba;	
			 System.out.println(sluzba);
		}else if (izbor==3) {
			 sluzba=Sluzba.sluzbaZdravstvneneZastiteRadnika;
			
		}else if (izbor==4) {
			 sluzba=Sluzba.SluzbaZdravstveneZastiteDece;
			
		}else if (izbor==5) {
			 sluzba=Sluzba.sluzbaZaTehnickePoslove;
				
		}else if (izbor==6) {
			 sluzba=Sluzba.sluzbaZaPravneIekonomskePoslove;
				
		}
		else {
			 sluzba=Sluzba.sluzbaOpsteMedicine;
		}
		
		unoss.close();
		//
		Medicinska_Sestra sestra = new Medicinska_Sestra(ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga, plata, sluzba, true);
		medicinskaSestre.add(sestra);
				
	}
	
	//DODAJ PREGLED
	//PROSIRITI POSLE DA U ZAVISNOSTI KO GA ZADAJE da bude zatrazen ili zakazan pacijent/sestra
	public void dodajNoviPregled() {
		Scanner unoss = new Scanner(System.in);

		System.out.println("Izaberi Pacijenta");
		for(Pacijent pacijent : pacijenti) {
			if(pacijent.isState()==true) {
				
		System.out.println(pacijent + "\n"); 
			}
		}
		System.out.println("unesi korisnicko ime izabranog pacijenta");

		String strpac= unoss.nextLine();
		Pacijent pacijent1 = nadjiPacijenta(strpac);
		//
		System.out.println("Izaberi lekara");
		for(Lekar lekar : lekari) {
			if(lekar.isState()==true) {
				
		System.out.println(lekar + "\n"); 
			}
		}
		System.out.println("unesi korisnicko ime izabranog pacijenta");

		String strlekar= unoss.nextLine();
		Lekar lekar1 = nadjiLekara(strlekar);
		
		System.out.println("Unesi Datum");
		//
		System.out.println("unesi  datum pregleda u formatu dd/MM/yyyy HH:mm:ss");
		String userInput=keyboard.nextLine();	
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date noviTermin = null;
		//String datum="5/5/1998 14:22:33";
	    try {  	    	
	    	noviTermin = sdf.parse(userInput);	       
	    } catch (Exception e) {}
		System.out.println("Soba:");

		String soba= unoss.nextLine();
		System.out.println("Kratak opis:");
		String kratakOpis=unoss.nextLine();
		StatusPregleda status= StatusPregleda.zakazan;
		Pregled pregled = new Pregled(pacijent1, lekar1, noviTermin, soba, status, kratakOpis, true);
		pregledi.add(pregled);
		unoss.close();
	}
	
	

}
