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

import controller.ControllerConnector;
import controller.ControllerInterface;


/**
*   View per inserimento e modifica delle Gru.
 */
public class ViewGru extends JDialog {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = -1364300169692664613L;

	/** content panel. */
	private final JPanel contentPanel = new JPanel();

	/** txt angolo rotazione. */
	private JTextField txtProduttore, txtModello, txtLunghezza, txtAltezza,
			txtPortataMax, txtAngoloRotazione;

	/** lbl angolo. */
	private JLabel lblProduttore, lblModello, lblLunghezza, lblAltezza,
			lblPortataMax, lblAngoloRotazione, lblMetri, lblTon, lblMetri2,
			lblAngolo;

	/** ok button. */
	private JButton okButton;

	private ControllerInterface insCtr;
	
	/**
	 * Inizializza il frame
	 *
	 * @param view view principale.
	 * @param obj dati della Gru da modificare.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 */
	public ViewGru(JFrame view, final Object[] obj,ControllerInterface aCtr) {
		super(view);
		insCtr=aCtr;
		setTitle("Modifica Gru");
		createLayout();
		setTextBox(obj);
		okButton.setText("Modifica");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if (insCtr.modificaGru(
							(Integer)obj[0],txtProduttore.getText(),
							txtModello.getText(),
							Integer.parseInt(txtAngoloRotazione.getText()), 
							Integer.parseInt(txtPortataMax.getText()), 
							Integer.parseInt(txtLunghezza.getText()), 
							Integer.parseInt(txtAltezza.getText()))) {
						dispose();}
				}catch(java.lang.NumberFormatException ex){
					JOptionPane
					.showMessageDialog(
							null,
							"I campi:\n - Lunghezza\n - Altezza\n - Portata Massima\n - Angolo Rotazione\ndevono contenere numeri. ",
							"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void setTextBox(Object[] v) {
		txtProduttore.setText(v[1].toString());
		txtModello.setText(v[2].toString());
		txtLunghezza.setText(v[3].toString());
		txtAltezza.setText(v[4].toString());
		txtPortataMax.setText(v[5].toString());
		txtAngoloRotazione.setText(v[6].toString());
	}

	/**
	 * Inizializza il frame.
	 *
	 * @param view view principale.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 *
	 */
	public ViewGru(JFrame view, ControllerInterface aCtr) {
		super(view);

		setName("editGru");
		insCtr = aCtr;
		setTitle("Aggiungi nuova Gru");
		createLayout();
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (insCtr.aggiungiGru(txtProduttore.getText(),
							txtModello.getText(),
							Integer.parseInt(txtAngoloRotazione.getText()),
							Integer.parseInt(txtPortataMax.getText()),
							Integer.parseInt(txtLunghezza.getText()),
							Integer.parseInt(txtAltezza.getText()))) {
						dispose();
					}
				} catch (java.lang.NumberFormatException ex) {
					JOptionPane
							.showMessageDialog(
									null,
									"I campi:\n - Lunghezza\n - Altezza\n - Portata Massima\n - Angolo Rotazione\ndevono contenere numeri. ",
									"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void createLayout() {
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
		lblMetri2 = new JLabel("metri	");
		lblTon = new JLabel("Kg");
		lblAngolo = new JLabel("Gradi");

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
		txtAngoloRotazione = new JTextField();
		txtAngoloRotazione.setColumns(5);
		txtAngoloRotazione.setName("angolo");
		txtLunghezza = new JTextField();
		txtLunghezza.setColumns(5);
		txtLunghezza.setName("lunghezza");

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
		okButton.setName("OK");
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
	 * Ritorna altezza inserita.
	 *
	 * @return altezza inserita.
	 */
	public String getAltezza() {
		return txtAltezza.getText();
	}

	/**
	 * Ritorna angolo rotazione inserito.
	 *
	 * @return angolo rotazione inserito.
	 */
	public String getAngoloRotazione() {
		return txtAngoloRotazione.getText();
	}

	/**
	 * Ritorna portata massima inserita.
	 *
	 * @return   portata massima inserita.
	 */
	public String getPortataMassima() {
		return txtPortataMax.getText();
	}

}
