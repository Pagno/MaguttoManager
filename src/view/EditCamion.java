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

// TODO: Auto-generated Javadoc
/**
 * The Class EditCamion.
 */
public class EditCamion extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8556951976345173917L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/** The txt capacita. */
	private JTextField txtProduttore, txtModello, txtLunghezza,txtPortata, txtCapacita;
	
	/** The lbl cap. */
	private JLabel lblProduttore, lblModello, lblLunghezza,
			lblPortataMax, lblCapacita, lblMetri, lblTon,
			lblCap;
	
	/** The ok button. */
	private JButton okButton;

	/**
	 * Create the dialog.
	 *
	 * @param view the view
	 * @param obj the obj
	 */
	public EditCamion(JFrame view, Object[] obj) {
		this(view);
		setTitle("Modifica Cantiere");
		setTextBox(obj);
		okButton.setText("Modifica");
	}
	
	/**
	 * Sets the text box.
	 *
	 * @param v the new text box
	 */
	private void setTextBox( Object[] v){
		txtProduttore.setText(v[1].toString());
		txtModello.setText(v[2].toString());
		txtLunghezza.setText(v[3].toString());
		txtCapacita.setText(v[4].toString());
		txtPortata.setText(v[5].toString());
	}

	/**
	 * Instantiates a new edits the camion.
	 *
	 * @param view the view
	 * @wbp.parser.constructor 
	 */
	public EditCamion(JFrame view) {
		super(view);
		setTitle("Aggiungi un nuovo Cantiere");
		setResizable(true);
		setBounds(100, 100, 332, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblProduttore = new JLabel("Produttore:");
		lblModello = new JLabel("Modello:");
		lblLunghezza = new JLabel("Lunghezza:");
		lblPortataMax = new JLabel("Portata Massima:");
		lblCapacita = new JLabel("Capacita Massima:");
		lblMetri = new JLabel("metri");
		lblTon = new JLabel("Kg");
		lblCap = new JLabel("metri cubi");

		txtProduttore = new JTextField();
		txtProduttore.setColumns(15);
		txtModello = new JTextField();
		txtModello.setColumns(15);
		txtLunghezza = new JTextField();
		txtLunghezza.setColumns(5);
		txtPortata = new JTextField();
		txtPortata.setColumns(5);
		txtCapacita = new JTextField();
		txtCapacita.setColumns(5);

		GroupLayout layout = new GroupLayout(contentPanel);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProduttore)
								.addComponent(lblModello)
								.addComponent(lblLunghezza)
								.addComponent(lblPortataMax)
								.addComponent(lblCapacita))
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
														txtLunghezza,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMetri))
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(
														txtPortata,
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
												.addComponent(lblCap))));
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
								.addComponent(lblLunghezza)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														txtLunghezza,
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
														txtPortata,
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
												.addComponent(lblCap))));

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
				txtProduttore.setText("");
				txtModello.setText("");
				txtLunghezza.setText("");
				txtPortata.setText("");
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
	 * Sets the insert button listeners.
	 *
	 * @param act the new insert button listeners
	 */
	public void setInsertButtonListeners(ActionListener act) {
		okButton.addActionListener(act);
	}
	
	/**
	 * Gets the produttore.
	 *
	 * @return the produttore
	 */
	public String getProduttore() {
		return txtProduttore.getText();
	}

	/**
	 * Gets the modello.
	 *
	 * @return the modello
	 */
	public String getModello() {
		return txtModello.getText();
	}

	
	/**
	 * Gets the lunghezza.
	 *
	 * @return the lunghezza
	 */
	public String getLunghezza() {
		return txtLunghezza.getText();
	}

	/**
	 * Gets the capacita.
	 *
	 * @return the capacita
	 */
	public String getCapacita() {
		return txtCapacita.getText();
	}

	/**
	 * Gets the portata massima.
	 *
	 * @return the portata massima
	 */
	public String getPortataMassima() {
		return txtPortata.getText();
	}

}
