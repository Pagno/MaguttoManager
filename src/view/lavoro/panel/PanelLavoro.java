package view.lavoro.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Panello necessario per l'inserimento dei dati del lavoro.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class PanelLavoro extends JPanel implements PropertyChangeListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4417739288868546177L;

	/** The txt nome lavoro. */
	public JTextField txtNomeLavoro;
	
	/** The lbl data fine lavoro. */
	public JLabel lblNomeLavoro, lblDataInizioLavoro, lblDataFineLavoro;
	
	/** The data fine lavoro. */
	public JDateChooser dataInizioLavoro, dataFineLavoro;
	
	/** The btn reset. */
	public JButton btnLavoro, btnReset;

	/** The max data fine. */
	Date minDataInizio, maxDataFine;

	/**
	 * Istanzia il panel lavoro.
	 *
	 * @param inizio minima data di inizio del lavoro
	 * @param fine massima data di fine del lavoro
	 */
	public PanelLavoro(Date inizio, Date fine) {// Data Inizio e fine del
												// cantiere
		setLayout(new BorderLayout());
		createPanel();
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});

		minDataInizio = inizio;
		maxDataFine = fine;
		setRangeDate(inizio, fine);

	}

	/**
	 * Crea il pannello.
	 */
	private void createPanel() {
		lblNomeLavoro = new JLabel("NomeLavoro:");
		lblDataInizioLavoro = new JLabel("Data Inizio:");
		lblDataFineLavoro = new JLabel("Data Fine:");
		dataInizioLavoro = new JDateChooser();
		dataInizioLavoro.setName("dataInizio");
		dataInizioLavoro.getJCalendar().setTodayButtonVisible(true);
		dataInizioLavoro.getJCalendar().setNullDateButtonVisible(true);
		dataInizioLavoro.addPropertyChangeListener(this);

		dataInizioLavoro.setName("dataInizioLavoro");
	
		dataFineLavoro  = new JDateChooser();

		dataFineLavoro.setName("dataFine");
		dataFineLavoro.getJCalendar().setTodayButtonVisible(true);
		dataFineLavoro.getJCalendar().setNullDateButtonVisible(true);
		dataFineLavoro.addPropertyChangeListener(this);

		dataFineLavoro.setName("dataFineLavoro");
		
		txtNomeLavoro  = new JTextField();
		txtNomeLavoro.setColumns(15);

		txtNomeLavoro.setName("nomeLavoro");
		btnLavoro=new JButton("Inserisci");
		btnLavoro.setName("inserisciLavoro");
		btnReset=new JButton("Reset");
		btnReset.setName("resetLavoro");
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeLavoro)
								.addComponent(lblDataInizioLavoro)
								.addComponent(lblDataFineLavoro)
								.addComponent(btnReset))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNomeLavoro)
								.addComponent(dataInizioLavoro)
								.addComponent(dataFineLavoro)
								.addComponent(btnLavoro)));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeLavoro)
								.addComponent(txtNomeLavoro,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataInizioLavoro)
								.addComponent(dataInizioLavoro,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataFineLavoro)
								.addComponent(dataFineLavoro,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnReset)
								.addComponent(btnLavoro,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));

		this.setLayout(layout);

	}

	/**
	 * Assegna la minima e massina data per la creazione di una lavoro.
	 *
	 * @param inizio minima data di inizio del lavoro
	 * @param fine massima data di fine del lavoro
	 */
	public void setRangeDate(Date inizio, Date fine) {
		dataInizioLavoro.setMinSelectableDate(inizio);
		dataInizioLavoro.setMaxSelectableDate(fine);
		dataFineLavoro.setMinSelectableDate(inizio);
		dataFineLavoro.setMaxSelectableDate(fine);
	}

	/**
	 * Pulisce i dati inseriti nei campi.
	 */
	public void clear() {
		dataInizioLavoro.setDate(null);
		dataFineLavoro.setDate(null);
		txtNomeLavoro.setText("");
	}

	/**
	 * Carica i dati del cantiere.
	 *
	 * @param data i dati del cantiere
	 */
	public void fill(ArrayList<String> data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataInizioLavoro.setDate(df.parse(data.get(2)));
			dataFineLavoro.setDate(df.parse(data.get(3)));
			dataInizioLavoro.setMaxSelectableDate(dataFineLavoro.getDate());
		} catch (ParseException ex) {
			System.out.println("Error Parsing data " + ex.getMessage());
		}
		txtNomeLavoro.setText(data.get(1));
		btnLavoro.setText("Modifica");
	}

	/**
	 * Ritorna il nome lavoro.
	 *
	 * @return nome lavoro
	 */
	public String getNomeLavoro() {
		return txtNomeLavoro.getText();
	}

	/**
	 * Ritorna la data inizio lavoro.
	 *
	 * @return data inizio lavoro
	 */
	public Date getDataInizioLavoro() {
		return dataInizioLavoro.getDate();
	}

	/**
	 * Ritorna la data fine lavoro.
	 *
	 * @return data fine lavoro
	 */
	public Date getDataFineLavoro() {
		return dataFineLavoro.getDate();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JDateChooser event = (JDateChooser) evt.getSource();
		try {
			if (event.getName().compareTo("dataInizioLavoro") == 0
					&& dataFineLavoro.getDate() != null
					&& dataInizioLavoro.getDate() != null) {

				if (dataInizioLavoro.getDate().after(dataFineLavoro.getDate())) {
					dataInizioLavoro.setDate(null);
				} else {
					dataFineLavoro.setMinSelectableDate(dataInizioLavoro.getDate());
				}
			}
			if (event.getName().compareTo("dataFineLavoro") == 0
					&& dataInizioLavoro.getDate() != null
					&& dataFineLavoro.getDate() != null) {
				if (dataFineLavoro.getDate().before(dataInizioLavoro.getDate())) {
					dataFineLavoro.setDate(null);
				} else {
					dataInizioLavoro.setMaxSelectableDate(dataFineLavoro.getDate());
				}
			}
		} catch (NullPointerException ex) {

		}
	}

	/**
	 * Aggiunge un ActionLister per la modifica del lavoro.
	 *
	 * @param aggiungiLavoroListener ActionListener da aggiungere al bottone
	 */
	public void btnAddActionListener(ActionListener aggiungiLavoroListener) {
		for(ActionListener act:btnLavoro.getActionListeners()){
			btnLavoro.removeActionListener(act);
		}
		btnLavoro.addActionListener(aggiungiLavoroListener);
	}
}
