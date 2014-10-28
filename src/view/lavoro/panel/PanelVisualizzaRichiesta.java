package view.lavoro.panel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panello necessario per la visualizzazione dei dati della richiesta.
 */
public class PanelVisualizzaRichiesta extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8632987215239339395L;
	
	/** The data. */
	private ArrayList<String> data;
	

	/** The lbl macchina. */
	private JLabel lblTipo,lblPortata,lblCapacita,lblLunghezza,lblAltezza,lblProfondita,lblRotazione,lblMacchina;
	
	/** The btn associa macchina. */
	public JButton btnAssociaMacchina;
	/**
	 * Istanzia il nuovo pannello
	 */
	public PanelVisualizzaRichiesta() {
		super();
		createPanel();
	}

	private void createPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		lblTipo=new JLabel();
		lblPortata=new JLabel("Portata");
		lblCapacita=new JLabel("Capacita");
		lblLunghezza=new JLabel("Lunghezza");
		lblAltezza=new JLabel("Altezza");
		lblProfondita=new JLabel("Profondita");
		lblRotazione=new JLabel("Rotazione");
		lblMacchina=new JLabel("");
		btnAssociaMacchina=new JButton("Associa Macchina");
		btnAssociaMacchina.setName("associaMacchina");
		
		add(lblTipo);
		add(lblCapacita);
		add(lblPortata);
		add(lblLunghezza);
		add(lblAltezza);
		add(lblProfondita);
		add(lblRotazione);
		add(lblMacchina);
		add(btnAssociaMacchina);
	}
	
	/**
	 * Carica i dati relativi alla richiesta da visualizzare.
	 *
	 * @param dati dati della richiesta da visualizzare.
	 */
	public void loadData(ArrayList<String> dati){
		data=dati;
		lblTipo.setText(data.get(1));
		lblCapacita.setText(data.get(2)+" <Capacita< "+data.get(3));
		lblPortata.setText(data.get(4)+" <Portata< "+data.get(5));
		lblLunghezza.setText(data.get(6)+" <Lunghezza< "+data.get(7));
		lblAltezza.setText(data.get(8)+" <Altezza< "+data.get(9));
		lblProfondita.setText(data.get(10)+" <Profondita< "+data.get(11));
		lblRotazione.setText(data.get(12)+" <Rotazione< "+data.get(13));
		if(data.get(1)=="Camion"){
			lblCapacita.setVisible(true);
			lblPortata.setVisible(true);
			lblLunghezza.setVisible(true);
			lblAltezza.setVisible(false);
			lblProfondita.setVisible(false);
			lblRotazione.setVisible(false);
		}
		if(data.get(1)=="Escavatore"){
			lblCapacita.setVisible(true);
			lblPortata.setVisible(true);
			lblLunghezza.setVisible(false);
			lblAltezza.setVisible(true);
			lblProfondita.setVisible(true);
			lblRotazione.setVisible(false);
		}
		if(data.get(1)=="Gru"){
			lblCapacita.setVisible(false);
			lblPortata.setVisible(true);
			lblLunghezza.setVisible(true);
			lblAltezza.setVisible(true);
			lblProfondita.setVisible(false);
			lblRotazione.setVisible(true);
		}
		if(data.get(1)=="Ruspa"){
			lblCapacita.setVisible(true);
			lblPortata.setVisible(true);
			lblLunghezza.setVisible(false);
			lblAltezza.setVisible(true);
			lblProfondita.setVisible(false);
			lblRotazione.setVisible(false);
		}
		lblMacchina.setVisible(false);
	}
	
	/**
	 * Ritorna i dati della richiesta visualizzata
	 *
	 * @return dati della richiesta visualizzata
	 */
	public ArrayList<String> getData(){
		return data;
	}
	
	/**
	 * Imposta la macchina associata alla richiesta.
	 *
	 * @param string macchina associata alla richiesta.
	 */
	public void setMacchina(String string) {
		lblMacchina.setVisible(true);
		lblMacchina.setText("Macchina Associata: "+string);		
	}

	public void addLiberaRichiestaListener(ActionListener associaMacchinaListener) {
		for(ActionListener act:btnAssociaMacchina.getActionListeners()){
			btnAssociaMacchina.removeActionListener(act);
		}
		btnAssociaMacchina.addActionListener(associaMacchinaListener);
	}

	public void addSoddisfaRichiestaListener(
			ActionListener liberaRichiestaListener) {
		for(ActionListener act:btnAssociaMacchina.getActionListeners()){
			btnAssociaMacchina.removeActionListener(act);
		}
		btnAssociaMacchina.addActionListener(liberaRichiestaListener);
		
	}
}
