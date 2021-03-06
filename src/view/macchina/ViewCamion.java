package view.macchina;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ControllerInterface;


/**
 *   View per inserimento e modifica dei Camion.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class ViewCamion extends JDialog {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 8556951976345173917L;

	/** content panel. */
	private final JPanel contentPanel = new JPanel();

	/** txt capacita. */
	private JTextField txtProduttore, txtModello, txtLunghezza, txtPortata,
			txtCapacita;

	/** lbl cap. */
	private JLabel lblProduttore, lblModello, lblLunghezza, lblPortataMax,
			lblCapacita, lblMetri, lblTon, lblCap;

	/** ok button. */
	private JButton okButton;
	
	/** The ins ctr. */
	private ControllerInterface insCtr;

	/**
	 * Inizializza il frame.
	 *
	 * @param view view principale.
	 * @param obj dati dei Camion da modificare.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 */
	public ViewCamion(JFrame view, final Object[] obj,
			ControllerInterface aCtr) {
		super(view);
		insCtr = aCtr;
		setTitle("Modifica Camion");
		createLayout();
		setTextBox(obj);
		okButton.setText("Modifica");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (insCtr.modificaCamion((Integer) obj[0],
							txtProduttore.getText(), txtModello.getText(),
							Integer.parseInt(txtCapacita.getText()),
							Integer.parseInt(txtPortata.getText()),
							Integer.parseInt(txtLunghezza.getText()))) {
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

	}

	/**
	 * Modifica i dati del Camion
	 *
	 * @param v the new text box
	 */
	private void setTextBox(Object[] v) {
		txtProduttore.setText(v[1].toString());
		txtModello.setText(v[2].toString());
		txtLunghezza.setText(v[3].toString());
		txtCapacita.setText(v[4].toString());
		txtPortata.setText(v[5].toString());
	}


	/**
	 * Inizializza il frame.
	 *
	 * @param view view principale.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 *
	 */
	public ViewCamion(JFrame view, ControllerInterface aCtr) {
		super(view);
		insCtr = aCtr;
		setTitle("Aggiungi un nuovo Camion");
		setName("editCamion");
		createLayout();
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (insCtr.aggiungiCamion(txtProduttore.getText(),
							txtModello.getText(),
							Integer.parseInt(txtCapacita.getText()),
							Integer.parseInt(txtPortata.getText()),
							Integer.parseInt(txtLunghezza.getText()))) {
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

	}

	/**
	 * Istanzia gli oggetti della view.
	 */
	private void createLayout() {
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
		txtProduttore.setName("produttore");
		txtModello = new JTextField();
		txtModello.setColumns(15);
		txtModello.setName("modello");
		txtLunghezza = new JTextField();
		txtLunghezza.setColumns(5);
		txtLunghezza.setName("lunghezza");
		txtPortata = new JTextField();
		txtPortata.setColumns(5);
		txtPortata.setName("portata");
		txtCapacita = new JTextField();
		txtCapacita.setColumns(5);
		txtCapacita.setName("capacita");

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
		okButton.setName("OK");
		
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
	 * Assegna i listener per il bottone di inserimento.
	 *
	 * @param act  ActionListener.
	 */
	public void setInsertButtonListeners(ActionListener act) {
		okButton.addActionListener(act);
	}

	/**
	 * Ritorna produttore inserito.
	 *
	 * @return   produttore inserito.
	 */
	public String getProduttore() {
		return txtProduttore.getText();
	}

	/**
	 * Ritorna modello inserito.
	 *
	 * @return   modello inserito.
	 */
	public String getModello() {
		return txtModello.getText();
	}
	/**
	 * Ritorna lunghezza inserita.
	 *
	 * @return lunghezza inserita.
	 */
	public String getLunghezza() {
		return txtLunghezza.getText();
	}
	
	/**
	 * Ritorna capacita inserita.
	 *
	 * @return capacita inserita.
	 */
	public String getCapacita() {
		return txtCapacita.getText();
	}

	/**
	 * Ritorna portata massima inserita.
	 *
	 * @return   portata massima inserita.
	 */
	public String getPortataMassima() {
		return txtPortata.getText();
	}

}
