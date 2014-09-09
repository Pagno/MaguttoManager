package view.lavoro.panel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

public class VisualizzaRichiestaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8632987215239339395L;
	private ArrayList<String> data;
	

	private JLabel lblTipo,lblPortata,lblCapacita,lblLunghezza,lblAltezza,lblProfondita,lblRotazione;
	private JButton btnAssociaMacchina;
	/**
	 * Create the panel.
	 */
	public VisualizzaRichiestaPanel() {
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
		btnAssociaMacchina=new JButton("Associa Macchina");
		
		add(lblTipo);
		add(lblCapacita);
		add(lblPortata);
		add(lblLunghezza);
		add(lblAltezza);
		add(lblProfondita);
		add(lblRotazione);
		add(btnAssociaMacchina);
	}

	public void addAggiungiAssociazioneListener(ActionListener act){
		btnAssociaMacchina.addActionListener(act);
	}
	public void loadData(ArrayList<String> dati){
		data=dati;
		lblTipo.setText(data.get(2));
		lblCapacita.setText(data.get(3)+" <Capactia< "+data.get(4));
	}
	public ArrayList<String> getData(){
		return data;
	}
}
