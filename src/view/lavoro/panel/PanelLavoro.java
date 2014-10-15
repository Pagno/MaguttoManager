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

public class PanelLavoro extends JPanel implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4417739288868546177L;
	/**
	 * Create the panel.
	 */

	public JTextField txtNomeLavoro;
	public JLabel lblNomeLavoro, lblDataInizioLavoro, lblDataFineLavoro;
	public JDateChooser dataInizioLavoro, dataFineLavoro;
	public JButton btnLavoro, btnReset;

	Date minDataInizio, maxDataFine;

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

	public void setRangeDate(Date inizio, Date fine) {
		dataInizioLavoro.setMinSelectableDate(inizio);
		dataInizioLavoro.setMaxSelectableDate(fine);
		dataFineLavoro.setMinSelectableDate(inizio);
		dataFineLavoro.setMaxSelectableDate(fine);
	}

	public void clear() {
		dataInizioLavoro.setDate(null);
		dataFineLavoro.setDate(null);
		txtNomeLavoro.setText("");
	}

	public void btnAddActionListener(ActionListener act) {
		for (ActionListener al : btnLavoro.getActionListeners()) {
			btnLavoro.removeActionListener(al);
		}
		btnLavoro.addActionListener(act);

	}

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

	public String getNomeLavoro() {
		return txtNomeLavoro.getText();
	}

	public Date getDataInizioLavoro() {
		return dataInizioLavoro.getDate();
	}

	public Date getDataFineLavoro() {
		return dataFineLavoro.getDate();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JDateChooser event = (JDateChooser) evt.getSource();
		try {
			if (event.getName().compareTo("dataInizio") == 0
					&& dataFineLavoro.getDate() != null
					&& dataInizioLavoro.getDate() != null) {

				if (dataInizioLavoro.getDate().after(dataFineLavoro.getDate())) {
					dataInizioLavoro.setDate(null);
				} else {
					dataFineLavoro.setMinSelectableDate(dataInizioLavoro.getDate());
				}
			}
			if (event.getName().compareTo("dataFine") == 0
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
}
