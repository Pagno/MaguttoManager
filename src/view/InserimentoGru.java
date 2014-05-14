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

public class InserimentoGru extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProduttore, txtModello, txtLunghezza, txtAltezza,
			txtPortataMax, txtAngoloRotazione;
	private JLabel lblProduttore, lblModello, lblLunghezza, lblAltezza,
			lblPortataMax, lblAngoloRotazione, lblMetri, lblTon, lblMetri2,
			lblAngolo;
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public InserimentoGru(JFrame view, Object[] obj) {
		this(view);
		txtProduttore.setText((String) obj[0]);
	}

	public InserimentoGru(JFrame view) {
		super(view);
		setTitle("Aggiungi nuova Gru");
		setResizable(true);
		setBounds(100, 100, 332, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblProduttore = new JLabel("Produttore:");
		lblModello = new JLabel("Modello:");
		lblAltezza = new JLabel("Altezza:");
		lblPortataMax = new JLabel("Portata Massima:");
		lblAngoloRotazione = new JLabel("Angolo Rotazione:");
		lblLunghezza = new JLabel("Lunghezza:");
		lblMetri = new JLabel("metri");
		lblMetri2 = new JLabel("metri");
		lblTon = new JLabel("Tonellate");
		lblAngolo = new JLabel("Gradi");

		txtProduttore = new JTextField();
		txtProduttore.setColumns(15);
		txtModello = new JTextField();
		txtModello.setColumns(15);
		txtAltezza = new JTextField();
		txtAltezza.setColumns(5);
		txtPortataMax = new JTextField();
		txtPortataMax.setColumns(5);
		txtAngoloRotazione = new JTextField();
		txtAngoloRotazione.setColumns(5);
		txtLunghezza = new JTextField();
		txtLunghezza.setColumns(5);

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
								.addComponent(lblAltezza)
								.addComponent(lblPortataMax)
								.addComponent(lblAngoloRotazione))
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
														txtAltezza,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMetri2))
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
														txtAngoloRotazione,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAngolo))));
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
								.addComponent(lblAltezza)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														txtAltezza,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMetri2)))
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
								.addComponent(lblAngoloRotazione)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														txtAngoloRotazione,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAngolo))));

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
				txtAltezza.setText("");
				txtLunghezza.setText("");
				txtPortataMax.setText("");
				txtAngoloRotazione.setText("");
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

	public String getAltezza() {
		return txtAltezza.getText();
	}

	public String getAngoloRotazione() {
		return txtAngoloRotazione.getText();
	}

	public String getPortataMassima() {
		return txtPortataMax.getText();
	}

}
