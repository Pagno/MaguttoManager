package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Associazione;

import com.toedter.calendar.JDateChooser;

// TODO: Auto-generated Javadoc
/**
 *   Class EditCantiere.
 */
public class EditCantiere extends JDialog implements PropertyChangeListener{

	/**   Constant serialVersionUID. */
	private static final long serialVersionUID = 8556951976345173917L;
	
	/**   content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/**   txt indirizzo. */
	private JTextField txtNome, txtIndirizzo;
	
	/**   lbl data fine. */
	private JLabel lblNome, lblIndirizzo, lblDataInizio,lblDataFine;
	
	/**   ok button. */
	private JButton okButton;
	
	/**   data fine. */
	private JDateChooser dataInizio,dataFine;
	
	/**
	 * Create   dialog.
	 *
	 * @param view   view
	 * @param obj   obj
	 */
	public EditCantiere(JFrame view, Object[] obj) {
		this(view);
		setTitle("Modifica Cantiere");
		setTextBox(obj);
		okButton.setText("Modifica");
		
	}
	
	/**
	 * Sets   text box.
	 *
	 * @param v   new text box
	 */
	private void setTextBox( Object[] v){
		txtNome.setText(v[1].toString());
		txtIndirizzo.setText(v[2].toString());
		//dc.setText(v[3].toString());
		String[] tokens = ((String)v[3]).split("/");
		Date d=new Date(Integer.parseInt(tokens[2])-1900,Integer.parseInt(tokens[1])-1,Integer.parseInt(tokens[0]));
		dataInizio.setDate(d);
		
		tokens = ((String)v[4]).split("/");
		Date d2=new Date(Integer.parseInt(tokens[2])-1900,Integer.parseInt(tokens[1])-1,Integer.parseInt(tokens[0]));
		dataFine.setDate(d2);
	}

	/**
	 * Instantiates a new edits   cantiere.
	 *
	 * @param view   view
	 * @wbp.parser.constructor 
	 */
	public EditCantiere(JFrame view) {
		super(view);
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
		dataInizio = new JDateChooser();

		//dataInizio.getJCalendar().getDayChooser().addDateEvaluator(new BirthdayEvaluator());
		//dataInizio.getJCalendar().getDayChooser().addDateEvaluator(new TestDateEvaluator());
		dataInizio.getJCalendar().setTodayButtonVisible(true);
		dataInizio.getJCalendar().setNullDateButtonVisible(true);
		
		
		dataFine = new JDateChooser();

		txtNome = new JTextField();
		txtNome.setColumns(15);
		txtIndirizzo = new JTextField();
		txtIndirizzo.setColumns(15);
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
								.addComponent(lblDataFine))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNome)
								.addComponent(txtIndirizzo)
								.addComponent(dataInizio)
								.addComponent(dataFine)));
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
										GroupLayout.PREFERRED_SIZE)));

		contentPanel.setLayout(layout);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Inserisci");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
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
	}

	/**
	 * Sets   insert button listeners.
	 *
	 * @param act   new insert button listeners
	 */
	public void setInsertButtonListeners(ActionListener act) {
		okButton.addActionListener(act);
	}

	/**
	 * Gets   nome cantiere.
	 *
	 * @return   nome cantiere
	 */
	public String getNomeCantiere() {
		return txtNome.getText();
	}

	/**
	 * Gets   indirizzo.
	 *
	 * @return   indirizzo
	 */
	public String getIndirizzo() {
		return txtIndirizzo.getText();
	}
	
	/**
	 * Gets   data inizio.
	 *
	 * @return   data inizio
	 */
	public Date getDataInizio(){
		return dataInizio.getDate();
	}
	
	/**
	 * Gets   data fine.
	 *
	 * @return   data fine
	 */
	public Date getDataFine(){
		return dataFine.getDate();
	}
	
	/**
	 * Sets   minima data fine.
	 *
	 * @param d   new minima data fine
	 */
	public void setMinimaDataFine(Date d){
		if(dataFine.getDate()==null || dataFine.getDate().before(d))
			dataFine.setDate(null);
		dataFine.setMinSelectableDate(d);
	}
	public void setMassimaDataInizio(Date d){
		dataInizio.setMaxSelectableDate(d);
	}
	/**
	 * Sets   data inizio changed listener.
	 *
	 * @param list   new data inizio changed listener
	 */
	public void setDataInizioChangedListener(PropertyChangeListener list){
		dataInizio.addPropertyChangeListener(list);
	}
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		setMinimaDataFine(getDataInizio());
		
	}
	
	/**
	 * Gets   associazioni list.
	 *
	 * @return   associazioni list
	 */
	public ArrayList<Associazione> getAssociazioniList(){
		return null;
	}
}
