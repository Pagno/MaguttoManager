package view.lavoro.panel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * Panello usato per la visualizzazione dei dati della richiesta.
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
	 * Istanzia il nuovo pannello.
	 */
	public PanelVisualizzaRichiesta() {
		super();
		createPanel();
	}

	/**
	 * Inizializza il nuovo pannello.
	 */
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
		if(data.get(2).equals("-1")&&data.get(3).equals("-1")){
			lblCapacita.setText("Capacit\u00E0 non vincolata.");
		}
		else{
			if(data.get(2).equals("-1")){
				lblCapacita.setText("Capacit\u00E0 < "+data.get(3));
			}
			else{
				if(data.get(3).equals("-1")){
					lblCapacita.setText("Capacit\u00E0 > "+data.get(2));
				}
				else{
					lblCapacita.setText(data.get(2)+" < Capacit\u00E0 < "+data.get(3));
				}
			}
		}
		if(data.get(4).equals("-1")&&data.get(5).equals("-1")){
			lblPortata.setText("Portata non vincolata.");
		}
		else{
			if(data.get(4).equals("-1")){
				lblPortata.setText("Portata < "+data.get(5));
			}
			else{
				if(data.get(5).equals("-1")){
					lblPortata.setText("Portata > "+data.get(4));
				}
				else{
					lblPortata.setText(data.get(4)+" < Portata < "+data.get(5));
				}
			}
		}
		if(data.get(6).equals("-1")&&data.get(7).equals("-1")){
			lblLunghezza.setText("Lunghezza non vincolata.");
		}
		else{
			if(data.get(6).equals("-1")){
				lblLunghezza.setText("Lunghezza < "+data.get(7));
			}
			else{
				if(data.get(7).equals("-1")){
					lblLunghezza.setText("Lunghezza > "+data.get(6));
				}
				else{
					lblLunghezza.setText(data.get(6)+" < Lunghezza < "+data.get(7));
				}
			}
		}
		if(data.get(8).equals("-1")&&data.get(9).equals("-1")){
			lblAltezza.setText("Altezza non vincolata.");
		}
		else{
			if(data.get(8).equals("-1")){
				lblAltezza.setText("Altezza < "+data.get(9));
			}
			else{
				if(data.get(9).equals("-1")){
					lblAltezza.setText("Altezza > "+data.get(8));
				}
				else{
					lblAltezza.setText(data.get(8)+" < Altezza < "+data.get(9));
				}
			}
		}
		if(data.get(10).equals("-1")&&data.get(11).equals("-1")){
			lblProfondita.setText("Profondit\u00E0 non vincolata.");
		}
		else{
			if(data.get(10).equals("-1")){
				lblProfondita.setText("Profondit\u00E0 < "+data.get(11));
			}
			else{
				if(data.get(11).equals("-1")){
					lblProfondita.setText("Profondit\u00E0 > "+data.get(10));
				}
				else{
					lblProfondita.setText(data.get(10)+" < Profondit\u00E0 < "+data.get(11));
				}
			}
		}
		if(data.get(12).equals("-1")&&data.get(13).equals("-1")){
			lblRotazione.setText("Rotazione non vincolata.");
		}
		else{
			if(data.get(12).equals("-1")){
				lblRotazione.setText("Rotazione < "+data.get(13));
			}
			else{
				if(data.get(13).equals("-1")){
					lblRotazione.setText("Rotazione > "+data.get(12));
				}
				else{
					lblRotazione.setText(data.get(12)+" < Rotazione < "+data.get(13));
				}
			}
		}
		if(data.get(1).equals("Camion")){
			lblCapacita.setVisible(true);
			lblPortata.setVisible(true);
			lblLunghezza.setVisible(true);
			lblAltezza.setVisible(false);
			lblProfondita.setVisible(false);
			lblRotazione.setVisible(false);
		}
		if(data.get(1).equals("Escavatore")){
			lblCapacita.setVisible(true);
			lblPortata.setVisible(true);
			lblLunghezza.setVisible(false);
			lblAltezza.setVisible(true);
			lblProfondita.setVisible(true);
			lblRotazione.setVisible(false);
		}
		if(data.get(1).equals("Gru")){
			lblCapacita.setVisible(false);
			lblPortata.setVisible(true);
			lblLunghezza.setVisible(true);
			lblAltezza.setVisible(true);
			lblProfondita.setVisible(false);
			lblRotazione.setVisible(true);
		}
		if(data.get(1).equals("Ruspa")){
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
	 * Ritorna i dati della richiesta visualizzata.
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

	/**
	 * Aggiunge l'ActionListener per liberare richiesta.
	 *
	 * @param associaMacchinaListener Associa macchina listener
	 */
	public void addLiberaRichiestaListener(ActionListener associaMacchinaListener) {
		for(ActionListener act:btnAssociaMacchina.getActionListeners()){
			btnAssociaMacchina.removeActionListener(act);
		}
		btnAssociaMacchina.addActionListener(associaMacchinaListener);
	}

	/**
	 *Aggiunge l'ActionListener per soddisfare la richiesta.
	 *
	 * @param liberaRichiestaListener Libera richiesta listener
	 */
	public void addSoddisfaRichiestaListener(
			ActionListener liberaRichiestaListener) {
		for(ActionListener act:btnAssociaMacchina.getActionListeners()){
			btnAssociaMacchina.removeActionListener(act);
		}
		btnAssociaMacchina.addActionListener(liberaRichiestaListener);
		
	}
}
