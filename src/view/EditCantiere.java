package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

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

public class EditCantiere extends JDialog implements PropertyChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8556951976345173917L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome, txtIndirizzo;
	private JLabel lblNome, lblIndirizzo, lblDataInizio,lblDataFine,lblAssociazione;
	private JButton okButton;
	private JDateChooser dataInizio,dataFine;
	private JButton addAssociazione;
	/**
	 * Create the dialog.
	 */
	public EditCantiere(JFrame view, Object[] obj) {
		this(view);
		setTitle("Modifica Camion");
		setTextBox(obj);
		okButton.setText("Modifica");
	}
	private void setTextBox( Object[] v){
		txtNome.setText(v[1].toString());
		txtIndirizzo.setText(v[2].toString());
		//dc.setText(v[3].toString());
	}

	/**
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
		lblAssociazione =new JLabel("Aggiungi Associazione.");
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
		addAssociazione=new JButton("Add");

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
								.addComponent(lblAssociazione))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNome)
								.addComponent(txtIndirizzo)
								.addComponent(dataInizio)
								.addComponent(dataFine)
								.addComponent(addAssociazione)));
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
								.addComponent(lblAssociazione)
								.addComponent(addAssociazione,
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
				dataInizio.setDate(null);
				dataFine.setDate(null);
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

	public void setInsertButtonListeners(ActionListener act) {
		okButton.addActionListener(act);
	}

	public String getNomeCantiere() {
		return txtNome.getText();
	}

	public String getIndirizzo() {
		return txtIndirizzo.getText();
	}
	public Date getDataInizio(){
		return dataInizio.getDate();
	}
	public Date getDataFine(){
		return dataFine.getDate();
	}
	public void setMinimaDataFine(Date d){
		if(dataFine.getDate()==null || dataFine.getDate().before(d))
			dataFine.setDate(null);
		dataFine.setMinSelectableDate(d);
	}
	
	public void setDataInizioChangedListener(PropertyChangeListener list){
		dataInizio.addPropertyChangeListener(list);
	}
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(dataInizio.getDate()==null)
			System.out.println("Null");
		setMinimaDataFine(getDataInizio());
		
	}
	public ArrayList<Associazione> getAssociazioniList(){
		return null;
	}
	public void setInsertAddAssociazioneListeners(ActionListener act) {
		addAssociazione.addActionListener(act);
	}
}
