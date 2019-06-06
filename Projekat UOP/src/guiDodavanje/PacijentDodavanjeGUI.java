package guiDodavanje;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import net.miginfocom.swing.MigLayout;
import osobe.Pacijent;

public class PacijentDodavanjeGUI extends JFrame {
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);

	private JLabel lblPol = new JLabel("Pol");
	private JTextField txtpol = new JTextField(20);
	
	
	private JLabel lblAdresa  = new JLabel("Adreas");
	private JTextField txtAdresa = new JTextField(20);
	
	private JLabel lblBrojTelefona  = new JLabel("Broj Telefona");
	private JTextField txtBrojTelefona  = new JTextField(20);
	

	
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	
	private JLabel lblKorImeIzabranogLekara = new JLabel("Korisnicko ime izabranog lekara");
	private JTextField txtKorImeIzabranogLekara  = new JTextField(20);
	
	private JLabel lblBrojKnjizice  = new JLabel("Broj knjizice");
	private JTextField txtBrojKnjizice = new JTextField(20);
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DomZdravlja domzravlja;
	private Pacijent pacijent;
	
	
	
	public PacijentDodavanjeGUI(DomZdravlja domzravlja,Pacijent pacijent) {
		
		this.domzravlja = domzravlja;
		this.pacijent = pacijent;
		if(pacijent != null) {
			setTitle(pacijent.getIme() + " - Izmena podataka");
		}else {
			setTitle("Dodavanje pacijenata");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		
	}
	private void initGUI() {
		
	}
	
	
	
	private void initActions() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(pacijent != null) {
			popuniPolja();
		}else {
			txtKorisnickoIme.setEnabled(true);
			txtBrojKnjizice.setEnabled(true);// OVDE DODATI DA SE NAPRAVI I NOVA KNJIZICA 

		}
		add(lblIme);  add(txtIme);
		add(lblPrezime);  add(txtPrezime);
		add(lblJMBG);  add(txtJMBG);
		add(lblPol);  add(txtpol);
		add(lblAdresa);  add(txtAdresa);
		add(lblBrojTelefona);  add(txtBrojTelefona);
		add(lblKorisnickoIme);  add(txtKorisnickoIme);
		add(lblLozinka);  add(pfLozinka);
		add(lblKorImeIzabranogLekara);  add(txtKorImeIzabranogLekara);
		add(lblBrojKnjizice);  add(txtBrojKnjizice);

		
		
		
		
		
	}
	
	
	
	private void popuniPolja(){
	
		txtIme.setText(pacijent.getIme());
		txtPrezime.setText(pacijent.getPrezime());
		txtJMBG.setText(pacijent.getJmbg());
		txtpol.setText(pacijent.getPol());
		txtAdresa.setText(pacijent.getAdresa());
		txtBrojTelefona.setText(pacijent.getBrojTelefona());
		txtKorisnickoIme.setText(pacijent.getKorisnickoIme());
		pfLozinka.setText(pacijent.getLozinka());
		txtKorImeIzabranogLekara.setText(pacijent.getIzabraniLekar().getKorisnickoIme());
		txtBrojKnjizice.setText(String.valueOf(pacijent.getKnjizica().getBroj()));
		
		txtKorisnickoIme.setEnabled(false);
		txtBrojKnjizice.setEnabled(false);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
