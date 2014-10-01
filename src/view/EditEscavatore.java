package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;

// 
/**
 *   Class EditEscavatore.
 */
public class EditEscavatore extends JDialog {

	/**   Constant serialVersionUID. */
	private static final long serialVersionUID = 8556951976345173917L;
	
	/**   content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/**   txt profondita. */
	private JTextField txtProduttore, txtModello, txtAltezza,txtPortataMax, txtCapacita,txtProfondita;
	
	/**   lbl cap. */
	private JLabel lblProduttore, lblModello, lblAltezza,lblProfondita,
			lblPortataMax, lblCapacita, lblMetri, lblTon,lblMetri2,
			lblCap;
	
	/**   ok button. */
	private JButton okButton;

	/**
	 * Create   dialog.
	 *
	 * @param view   view
	 * @param obj   obj
	 */
	public EditEscavatore(JFrame view, Object[] obj) {
		this(view);
		setTitle("Modifica Escavatore");
		setTextBox(obj);
		okButton.setText("Modifica");
	}
	
	/**
	 * Sets   text box.
	 *
	 * @param v   new text box
	 */
	private void setTextBox( Object[] v){
		txtProduttore.setText(v[1].toString());
		txtModello.setText(v[2].toString());
		txtAltezza.setText(v[3].toString());
		txtProfondita.setText(v[4].toString());
		txtCapacita.setText(v[5].toString());
		txtPortataMax.setText(v[6].toString());
	}

	/**
	 * Instantiates a new edits   escavatore.
	 *
	 * @param view   view
	 * @wbp.parser.constructor 
	 */
	public EditEscavatore(JFrame view) {
		super(view);
		setTitle("Aggiungi un nuovo Escavatore");
		setResizable(true);
		setBounds(100, 100, 332, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblProduttore = new JLabel("Produttore:");
		lblModello = new JLabel("Modello:");
		lblAltezza = new JLabel("Lunghezza:");
		lblPortataMax = new JLabel("Portata Massima:");
		lblProfondita = new JLabel("Profondita:");
		lblCapacita = new JLabel("Capacita Massima:");
		lblMetri = new JLabel("metri");
		lblTon = new JLabel("Kg");
		lblCap = new JLabel("metri cubi");
		lblMetri2=new JLabel("Metri");

		txtProduttore = new JTextField();
		txtProduttore.setColumns(15);
		txtProduttore.setName("produttore");
		txtModello = new JTextField();
		txtModello.setColumns(15);
		txtModello.setName("modello");
		txtAltezza = new JTextField();
		txtAltezza.setColumns(5);
		txtAltezza.setName("altezza");
		txtPortataMax = new JTextField();
		txtPortataMax.setColumns(5);
		txtPortataMax.setName("portata");
		txtCapacita = new JTextField();
		txtCapacita.setColumns(5);
		txtCapacita.setName("capacita");
		txtProfondita = new JTextField();
		txtProfondita.setColumns(5);
		txtProfondita.setName("profondita");

		GroupLayout layout = new GroupLayout(contentPanel);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProduttore)
								.addComponent(lblModello)
								.addComponent(lblAltezza)
								.addComponent(lblPortataMax)
								.addComponent(lblCapacita)
								.addComponent(lblProfondita))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtProduttore,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtModello,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(
														txtAltezza,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMetri))
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(
														txtPortataMax,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTon))
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(
														txtCapacita,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCap))
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(
														txtProfondita,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMetri2))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProduttore)
								.addComponent(txtProduttore,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblModello)
								.addComponent(txtModello,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAltezza)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														txtAltezza,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMetri)))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPortataMax)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														txtPortataMax,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTon)))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCapacita)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														txtCapacita,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCap)))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProfondita)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														txtProfondita,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMetri2))));

		contentPanel.setLayout(layout);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Inserisci");
		okButton.setActionCommand("OK");
		okButton.setName("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtProduttore.setText("");
				txtModello.setText("");
				txtAltezza.setText("");
				txtPortataMax.setText("");
				txtProfondita.setText("");
				txtCapacita.setText("");
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
	 * Gets   produttore.
	 *
	 * @return   produttore
	 */
	public String getProduttore() {
		return txtProduttore.getText();
	}

	/**
	 * Gets   modello.
	 *
	 * @return   modello
	 */
	public String getModello() {
		return txtModello.getText();
	}


	/**
	 * Gets   altezza.
	 *
	 * @return   altezza
	 */
	public String getAltezza() {
		return txtAltezza.getText();
	}
	
	/**
	 * Gets   profondita.
	 *
	 * @return   profondita
	 */
	public String getProfondita() {
		return txtProfondita.getText();
	}
	
	/**
	 * Gets   capacita.
	 *
	 * @return   capacita
	 */
	public String getCapacita() {
		return txtCapacita.getText();
	}

	/**
	 * Gets   portata massima.
	 *
	 * @return   portata massima
	 */
	public String getPortataMassima() {
		return txtPortataMax.getText();
	}

}
