package guizaPrikaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class PrikazRacunaGUI extends JFrame{
	private JLabel txtIme = new JLabel("Ime :");
	private JLabel lblImePacijenta= new JLabel();
	

	private JLabel txtPrezime = new JLabel("Prezime:");
	private JLabel lblKorPrezimePacijenta= new JLabel();
	
	private JLabel txtBrojPregleda = new JLabel("Broj Pregleda");
	private JLabel lblBrojPregleda= new JLabel();

	private JLabel txtRacun = new JLabel("Racun:");
	private JLabel lblRacun =new JLabel();
	
	public PrikazRacunaGUI(String ime,String prezime,int brojPregleda,int Racun) {
		lblImePacijenta =new JLabel(ime);
		lblKorPrezimePacijenta=new JLabel(prezime);
		lblBrojPregleda=new JLabel(String.valueOf(brojPregleda));
		lblRacun=new JLabel(String.valueOf(Racun));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setSize(500, 500);
		initGUI();
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		add(txtIme);
		add(lblImePacijenta);
		add(txtPrezime);
		add(lblKorPrezimePacijenta);
		add(txtBrojPregleda);
		add(lblBrojPregleda);
		add(txtRacun);
		add(lblRacun);

	}
}
