package view.cantiere;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.organizer.data.Priorita;
import com.toedter.calendar.JDateChooser;
import controller.ControllerInterface;

/**
 * Frame dedicato all'inserimento e modifica dei Cantieri.
 * 
 * @author Matteo Pagnoncelli
 * @author Mauro Valota
 */
public class ViewCantiere extends JDialog implements PropertyChangeListener {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 8556951976345173917L;

	/** content panel. */
	private final JPanel contentPanel = new JPanel();

	/** txt indirizzo. */
	private JTextField txtNome, txtIndirizzo;

	/** lbl data fine. */
	private JLabel lblPriorita, lblNome, lblIndirizzo, lblDataInizio,
			lblDataFine;

	/** The priority. */
	String[] priority = { "BASSA", "MEDIA", "ALTA" };
	
	/** The priorita. */
	private JComboBox<String> priorita = new JComboBox<String>(priority);

	/** ok button. */
	private JButton okButton;

	/** data fine. */
	private JDateChooser dataInizio, dataFine;

	/** The edit. */
	private boolean edit = false;

	/** The df. */
	private GregorianCalendar di, df;

	/**
	 * Crea un nuovo frame per l'inserimento di una nuovo cantiere.
	 *
	 * @param view view padre
	 * @param insCtr Interfaccia del componente controller necessario per comunicare con i dati dell'applicazione
	 */
	public ViewCantiere(JFrame view, final ControllerInterface insCtr) {
		super(view);
		setName("insertCantiere");
		setTitle("Aggiungi un nuovo Cantiere");
		setResizable(true);
		setBounds(100, 100, 332, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblNome = new JLabel("Nome Cantiere:");
		lblIndirizzo = new JLabel("Indirizzo Cantiere:");
		lblDataInizio = new JLabel("Data Inizio:");
		lblDataFine = new JLabel("Data Fine:");
		lblPriorita = new JLabel("Priorita:");
		dataInizio = new JDateChooser();
		dataInizio.setName("dataInizio");
		
		//dataInizio.getJCalendar().getDayChooser().addDateEvaluator(new BirthdayEvaluator());
		//dataInizio.getJCalendar().getDayChooser().addDateEvaluator(new TestDateEvaluator());

		dataInizio.getJCalendar().setTodayButtonVisible(true);
		dataInizio.getJCalendar().setNullDateButtonVisible(true);

		dataFine = new JDateChooser();
		dataFine.setName("dataFine");

		txtNome = new JTextField();
		txtNome.setColumns(15);
		txtNome.setName("nome");
		txtIndirizzo = new JTextField();
		txtIndirizzo.setColumns(15);
		txtIndirizzo.setName("indirizzo");
		//dc = new JTextField();
		//dc.setColumns(5);

		GroupLayout layout = new GroupLayout(contentPanel);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(lblIndirizzo)
								.addComponent(lblDataInizio)
								.addComponent(lblDataFine)
								.addComponent(lblPriorita))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNome)
								.addComponent(txtIndirizzo)
								.addComponent(dataInizio)
								.addComponent(dataFine).addComponent(priorita)));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(txtNome,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIndirizzo)
								.addComponent(txtIndirizzo,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataInizio)
								.addComponent(dataInizio,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataFine)
								.addComponent(dataFine,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPriorita)
								.addComponent(priorita,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));

		contentPanel.setLayout(layout);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Inserisci");
		okButton.setActionCommand("OK");
		okButton.setName("OK");
		buttonPane.add(okButton);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GregorianCalendar inizio,fine;
					(inizio=new GregorianCalendar()).setTime(getDataInizio());
					(fine=new GregorianCalendar()).setTime(getDataFine());
					if (insCtr.aggiungiCantiere(txtNome.getText(),
							txtIndirizzo.getText(),
							inizio,fine,
							Priorita.valueOf((String)priorita.getSelectedItem()))) {
						dispose();
					}

				} catch (java.lang.NumberFormatException ex) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Lunghezza\n - Capacita\n - Portata Massima\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		getRootPane().setDefaultButton(okButton);

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtIndirizzo.setText("");
				dataFine.setDate(null);
				dataInizio.setDate(null);

			}
		});
		okButton.setActionCommand("Reset");
		buttonPane.add(resetButton);

		JButton chiudiBtn = new JButton("Chiudi");
		chiudiBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		chiudiBtn.setActionCommand("Cancel");
		buttonPane.add(chiudiBtn);

		setVisible(true);
		dataInizio.addPropertyChangeListener(this);
		dataFine.addPropertyChangeListener(this);
	}

	/**
	 * Restituisce la data di inizio.
	 *
	 * @return Data di inizio
	 */
	private Date getDataInizio() {
		return dataInizio.getDate();
	}

	/**
	 * Restituisce la data di fine.
	 *
	 * @return Data di fine
	 */
	private Date getDataFine() {
		return dataFine.getDate();
	}

	/**
	 * Modifica la minima data di fine.
	 *
	 * @param d Minima data di fine
	 */
	private void setMinimaDataFine(Date d) {
		dataFine.setMinSelectableDate(d);
	}

	/**
	 * Modifica la massima data di inizio.
	 *
	 * @param d Massima data di inizio
	 */
	private void setMassimaDataInizio(Date d) {
		dataInizio.setMaxSelectableDate(d);
	}
	
	/**
	 * Sets the data inizio changed listener.
	 *
	 * @param list the new data inizio changed listener
	 */
	private void setDataInizioChangedListener(PropertyChangeListener list) {
		dataInizio.addPropertyChangeListener(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

		if (edit == true) {
			if (getDataInizio() != null && di != null) {
				if (getDataInizio().after(di.getTime())) {
					dataInizio.setDate(di.getTime());
					setMassimaDataInizio(di.getTime());
					JOptionPane
							.showMessageDialog(
									null,
									"La nuova data di inizio non puo essere maggiore della precedente.",
									"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (getDataFine() != null && df != null) {
				if (getDataFine().before(df.getTime())) {
					dataFine.setDate(df.getTime());
					setMinimaDataFine(df.getTime());
					JOptionPane
							.showMessageDialog(
									null,
									"La nuova data di fine non puo essere minore della precedente.",
									"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			setMinimaDataFine(getDataInizio());
			setMassimaDataInizio(getDataFine());
		}
	}
}
