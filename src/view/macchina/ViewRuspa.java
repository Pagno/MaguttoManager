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
 *   View per inserimento e modifica delle Ruspe.
 */
public class ViewRuspa extends JDialog {

	/**   Constant serialVersionUID. */
	private static final long serialVersionUID = 4043996596294833629L;
	
	/**   content panel. */
	private final JPanel contentPanel = new JPanel();
	
	/**   txt capacita. */
	private JTextField txtProduttore, txtModello, txtAltezza,
			txtPortataMax, txtCapacita;
	
	/**   lbl cap. */
	private JLabel lblProduttore, lblModello, lblAltezza,
			lblPortataMax, lblCapacita, lblMetri, lblTon,
			lblCap;
	
	/**   ok button. */
	private JButton okButton;

	private ControllerInterface insCtr;
	/**
	 * Inizializza il frame
	 *
	 * @param view view principale.
	 * @param obj dati della Ruspa da modificare.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 */
	public ViewRuspa(JFrame view, final Object[] obj,ControllerInterface aCtr) {
		super(view);
		insCtr=aCtr;
		setTitle("Modifica Ruspa");
		createLayout();
		setTextBox(obj);
		okButton.setText("Modifica");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(insCtr.modificaRuspa((Integer)obj[0],
							txtProduttore.getText(),txtModello.getText(),
							Integer.parseInt(txtCapacita.getText()), 
							Integer.parseInt(txtPortataMax.getText()), 
							Integer.parseInt(txtAltezza.getText())))
						{	
							dispose();
						}
				}catch(java.lang.NumberFormatException ex){
					JOptionPane
					.showMessageDialog(
							null,
							"I campi:\n - Capacita\n - Altezza\n - Portata Massima\ndevono contenere numeri. ",
							"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void setTextBox( Object[] v){
		txtProduttore.setText(v[1].toString());
		txtModello.setText(v[2].toString());
		txtAltezza.setText(v[3].toString());
		txtCapacita.setText(v[4].toString());
		txtPortataMax.setText(v[5].toString());
	}

	/**
	 * Inizializza il frame.
	 *
	 * @param view view principale.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 *
	 */
	public ViewRuspa(JFrame view,ControllerInterface aCtr) {
		super(view);
		insCtr=aCtr;
		setTitle("Aggiungi nuova Ruspa");
		createLayout();
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(insCtr.aggiungiRuspa(txtProduttore.getText(),txtModello.getText(),
						Integer.parseInt(txtCapacita.getText()), 
						Integer.parseInt(txtPortataMax.getText()), 
						Integer.parseInt(txtAltezza.getText())))
					{	
						dispose();
					}
				}catch(java.lang.NumberFormatException ex){
					JOptionPane
					.showMessageDialog(
							null,
							"I campi:\n - Capacita\n - Altezza\n - Portata Massima\ndevono contenere numeri. ",
							"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	private void createLayout(){
		setResizable(true);
		setBounds(100, 100, 332, 282);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblProduttore = new JLabel("Produttore:");
		lblModello = new JLabel("Modello:");
		lblAltezza = new JLabel("Altezza:");
		lblPortataMax = new JLabel("Portata Massima:");
		lblCapacita = new JLabel("Capacita Massima:");
		lblMetri = new JLabel("metri");
		lblTon = new JLabel("Kg");
		lblCap = new JLabel("metri cubi");

		txtProduttore = new JTextField();
		txtProduttore.setName("produttore");
		txtProduttore.setColumns(15);
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
				txtAltezza.setText("");
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
	 * Ritorna altezza inserita.
	 *
	 * @return altezza inserita.
	 */
	public String getAltezza() {
		return txtAltezza.getText();
	}

	/**
	 * Ritorna capacita inserita.
	 *
	 * @return   capacita inserita.
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
		return txtPortataMax.getText();
	}

}
