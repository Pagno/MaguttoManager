package view.lavoro.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Panello necessario per l'inserimento dei dati del cantiere.
 */
public class PanelCantiere extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8114659454885608761L;
	


	/** The txt indirizzo. */
	private JTextField txtNomeCantiere, txtIndirizzo;
	
	/** The lbl data fine cantiere. */
	private JLabel lblPriorita,lblNomeCantiere, lblIndirizzoCantiere, lblDataInizioCantiere,lblDataFineCantiere;
	
	/** The data fine cantiere. */
	private JDateChooser dataInizioCantiere,dataFineCantiere;
	
	/** The btn reset. */
	private JButton btnAdd,btnReset;
	
	/** The priority. */
	String[] priority={"BASSA","MEDIA","ALTA"};
	
	/** The priorita. */
	private JComboBox<String> priorita=new JComboBox<String>(priority);
	
	/**
	 * Istanzia il pannello.
	 */
	public PanelCantiere() {
		setLayout(new BorderLayout());
		createPanel();
		btnReset.setVisible(false);
	}

	/**
	 * Inizializza il nuovo pannello.
	 */
	private void createPanel(){
		lblNomeCantiere = new JLabel("Nome:");
		lblPriorita = new JLabel("Priorita:");
		lblIndirizzoCantiere = new JLabel("Indirizzo Cantiere:");
		lblDataInizioCantiere = new JLabel("Data Inizio:");
		lblDataFineCantiere = new JLabel("Data Fine:");
		dataInizioCantiere = new JDateChooser();
		dataInizioCantiere.getJCalendar().setTodayButtonVisible(true);
		dataInizioCantiere.getJCalendar().setNullDateButtonVisible(true);
		dataInizioCantiere.setName("dataInizioCantiere");
		dataFineCantiere = new JDateChooser();
		dataFineCantiere.setName("dataFineCantiere");
		txtNomeCantiere = new JTextField();
		txtNomeCantiere.setColumns(15);
		txtNomeCantiere.setName("nomeCantiere");
		txtIndirizzo = new JTextField();
		txtIndirizzo.setName("indirizzoCantiere");;
		txtIndirizzo.setColumns(15);
		btnAdd=new JButton("Edit");
		btnAdd.setName("edit");
		btnReset=new JButton("Reset");
		btnReset=new JButton("resetCantiere");
		priorita.setName("prioritaCantiere");
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeCantiere)
								.addComponent(lblIndirizzoCantiere)
								.addComponent(lblDataInizioCantiere)
								.addComponent(lblDataFineCantiere)
								.addComponent(lblPriorita)
								.addComponent(btnReset))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNomeCantiere)
								.addComponent(txtIndirizzo)
								.addComponent(dataInizioCantiere)
								.addComponent(dataFineCantiere)
								.addComponent(priorita)
								.addComponent(btnAdd)));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeCantiere)
								.addComponent(txtNomeCantiere,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIndirizzoCantiere)
								.addComponent(txtIndirizzo,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataInizioCantiere)
								.addComponent(dataInizioCantiere,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataFineCantiere)
								.addComponent(dataFineCantiere,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPriorita)
								.addComponent(priorita,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnReset)
								.addComponent(btnAdd,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));

		this.setLayout(layout);
	}
	
	/**
	 * Pulisce i dati inseriti nei campi.
	 */
	private void clear(){
		dataInizioCantiere.setDate(null);
		dataFineCantiere.setDate(null);
		txtNomeCantiere.setText("");
		btnAdd.setText("Inserisci");
	}
	public void addModificaCantiereListener(ActionListener action){
		for(ActionListener act:btnAdd.getActionListeners() ){
			btnAdd.removeActionListener(act);
		}
		btnAdd.addActionListener(action);
	}
	
	/**
	 * Imposta i dati del cantiere
	 *
	 * @param v dati del cantiere
	 */
	public void setRangeDate(GregorianCalendar inizio,GregorianCalendar fine,boolean hasLavoro){
		if(hasLavoro==false){
			dataInizioCantiere.setMaxSelectableDate(fine.getTime());
			dataFineCantiere.setMinSelectableDate(inizio.getTime());
		}else{
			dataInizioCantiere.setMaxSelectableDate(inizio.getTime());
			dataFineCantiere.setMinSelectableDate(fine.getTime());
		}
	}
	/**
	 * Ritorna la priorita cantiere.
	 *
	 * @return priorita priorita cantiere
	 */
	public String getPrioritaCantiere() {return (String)priorita.getSelectedItem();}; 
	
	public String getNomeCantiere() {return txtNomeCantiere.getText();}
	/**
	 * Ritorna indirizzo cantiere.
	 *
	 * @return Indirizzo cantiere
	 */
	public String getIndirizzoCantiere() {return txtIndirizzo.getText();}

	/**
	 * Ritorna data inizio cantiere.
	 *
	 * @return SSta inizio cantiere
	 */
	public Date getDataInizioCantiere(){return dataInizioCantiere.getDate();}

	/**
	 * Ritorna data fine cantiere.
	 *
	 * @return data fine cantiere
	 */
	public Date getDataFineCantiere(){return dataFineCantiere.getDate();}

	/**
	 * Assegna il nome cantiere.
	 *
	 * @param nome nome cantiere
	 */
	public void setNomeCantiere(String nome) {txtNomeCantiere.setText(nome);}

	/**
	 * Assegna la priorita cantiere.
	 *
	 * @param priorita priorita cantiere
	 */
	public void setPrioritaCantiere(String priorita) {this.priorita.setSelectedItem(priorita); }

	/**
	 * Assegna l'indirizzo cantiere.
	 *
	 * @param Indirizzo indirizzo cantiere
	 */
	public void setIndirizzoCantiere(String Indirizzo) {txtIndirizzo.setText(Indirizzo);}

	/**
	 * Assegna la data inizio cantiere.
	 *
	 * @param inizio data inizio cantiere
	 */
	public void setDataInizioCantiere(GregorianCalendar inizio){dataInizioCantiere.setDate(inizio.getTime());}
	

	/**
	 * Assegna la data fine cantiere.
	 *
	 * @param fine data fine cantiere
	 */
	public void setDataFineCantiere(GregorianCalendar fine){dataFineCantiere.setDate(fine.getTime());}
	
	

	/**
	 * Assegna la data inizio cantiere.
	 *
	 * @param list the new data inizio cantiere changed listener
	 */
	public void setDataInizioCantiereChangedListener(PropertyChangeListener list){dataInizioCantiere.addPropertyChangeListener(list);}
	

	/**
	 * Assegna la  minima data fine cantiere.
	 *
	 * @param d minima data fine cantiere
	 */
	public void setMinimaDataFineCantiere(Date d){dataFineCantiere.setMinSelectableDate(d);}
	

	/**
	 * Assegna la massima data inizio cantiere.
	 *
	 * @param d massima data inizio cantiere
	 */
	public void setMassimaDataInizioCantiere(Date d){dataInizioCantiere.setMaxSelectableDate(d);}
	

	/**
	 * Assegna la minima data fine.
	 *
	 * @param d minima data fine
	 */
	public void setMinimaDataFine(Date d){dataFineCantiere.setMinSelectableDate(d);}

	/**
	 * Assegna la massima data inizio.
	 *
	 * @param d massima data inizio
	 */
	public void setMassimaDataInizio(Date d){dataInizioCantiere.setMaxSelectableDate(d);}

}
