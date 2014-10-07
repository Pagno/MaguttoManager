package view.lavoro.panel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisualizzaRichiestaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8632987215239339395L;
	private ArrayList<String> data;
	

	private JLabel lblTipo,lblPortata,lblCapacita,lblLunghezza,lblAltezza,lblProfondita,lblRotazione;
	public JButton btnAssociaMacchina;
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
		btnAssociaMacchina.setName("associaMacchina");
		
		add(lblTipo);
		add(lblCapacita);
		add(lblPortata);
		add(lblLunghezza);
		add(lblAltezza);
		add(lblProfondita);
		add(lblRotazione);
		add(btnAssociaMacchina);
	}

	public void addSoddisfaRichiestaListener(ActionListener act){
		for(ActionListener action:btnAssociaMacchina.getActionListeners()){
			btnAssociaMacchina.removeActionListener(action);
		}
		btnAssociaMacchina.addActionListener(act);
	}
	public void addLiberaRichiestaListener(ActionListener act){
		for(ActionListener action:btnAssociaMacchina.getActionListeners()){
			btnAssociaMacchina.removeActionListener(action);
		}
		btnAssociaMacchina.addActionListener(act);
	}
	public void loadData(ArrayList<String> dati){
		data=dati;
		lblTipo.setText(data.get(1));
		lblCapacita.setText(data.get(2)+" <Capactia< "+data.get(3));
		lblPortata.setText(data.get(4)+" <Portata< "+data.get(5));
		lblLunghezza.setText(data.get(6)+" <Lunghezza< "+data.get(7));
		lblAltezza.setText(data.get(8)+" <Altezza< "+data.get(9));
		lblProfondita.setText(data.get(10)+" <Profondità< "+data.get(11));
		lblRotazione.setText(data.get(12)+" <Rotazione< "+data.get(13));
	}
	public ArrayList<String> getData(){
		return data;
	}
}
