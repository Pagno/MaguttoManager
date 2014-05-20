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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

public class EditCamion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8556951976345173917L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProduttore, txtModello, txtLunghezza,txtPortataMax, txtCapacita;
	private JLabel lblProduttore, lblModello, lblLunghezza,
			lblPortataMax, lblCapacita, lblMetri, lblTon,
			lblCap;
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public EditCamion(JFrame view, Object[] obj) {
		this(view);
		setTitle("Modifica Camion");
		setTextBox(obj);
		okButton.setText("Modifica");
	}
	private void setTextBox( Object[] v){
		txtProduttore.setText(v[1].toString());
		txtModello.setText(v[2].toString());
		txtLunghezza.setText(v[3].toString());
		txtCapacita.setText(v[4].toString());
		txtPortataMax.setText(v[5].toString());
	}

	/**
	 * @wbp.parser.constructor
	 */
	public EditCamion(JFrame view) {
		super(view);
		setTitle("Aggiungi un nuovo Camion");
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
		txtPortataMax = new JTextField();
		txtPortataMax.setColumns(5);
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
				txtPortataMax.setText("");
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

	public void setInsertButtonListeners(ActionListener act) {
		okButton.addActionListener(act);
	}

	public String getProduttore() {
		return txtProduttore.getText();
	}

	public String getModello() {
		return txtModello.getText();
	}


	public String getLunghezza() {
		return txtLunghezza.getText();
	}

	public String getCapacita() {
		return txtCapacita.getText();
	}

	public String getPortataMassima() {
		return txtPortataMax.getText();
	}

}
