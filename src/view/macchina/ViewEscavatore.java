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
 *   View per inserimento e modifica degli Escavatori.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class ViewEscavatore extends JDialog {

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


	private ControllerInterface insCtr;
	/**
	 * Inizializza il frame
	 *
	 * @param view view principale.
	 * @param obj dati degli Escavatori da modificare.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 */
	public ViewEscavatore(JFrame view, final Object[] obj,ControllerInterface aCtr) {
		super(view);
		insCtr=aCtr;
		setTitle("Modifica Escavatore");
		createLayout();
		setTextBox(obj);
		okButton.setText("Modifica");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(insCtr.modificaEscavatore((Integer)obj[0],
							txtProduttore.getText(),txtModello.getText(),
							Integer.parseInt(txtCapacita.getText()), 
							Integer.parseInt(txtPortataMax.getText()), 
							Integer.parseInt(txtAltezza.getText()),
							Integer.parseInt(txtProfondita.getText())))
						{	
							dispose();
						}
				}catch(java.lang.NumberFormatException ex){
					JOptionPane
					.showMessageDialog(
							null,
							"I campi:\n - Profondita\n - Altezza\n - Capacita\n - Portata Massima\ndevono contenere numeri. ",
							"Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void setTextBox( Object[] v){
		txtProduttore.setText(v[1].toString());
		txtModello.setText(v[2].toString());
		txtAltezza.setText(v[3].toString());
		txtProfondita.setText(v[4].toString());
		txtCapacita.setText(v[5].toString());
		txtPortataMax.setText(v[6].toString());
	}

	/**
	 * Inizializza il frame.
	 *
	 * @param view view principale.
	 * @param aCtr Interfaccia del conroller per comunicare con il model.
	 *
	 */
	public ViewEscavatore(JFrame view,ControllerInterface aCtr) {
		super(view);
		insCtr=aCtr;
		setTitle("Aggiungi un nuovo Escavatore");

		setName("editEscavatore");

		createLayout();
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(insCtr.aggiungiEscavatore(txtProduttore.getText(),txtModello.getText(),
						Integer.parseInt(txtCapacita.getText()), 
						Integer.parseInt(txtPortataMax.getText()), 
						Integer.parseInt(txtAltezza.getText()),
						Integer.parseInt(txtProfondita.getText())))
					{	
						dispose();
					}
				}catch(java.lang.NumberFormatException ex){
					JOptionPane
					.showMessageDialog(
							null,
							"I campi:\n - Profondita\n - Altezza\n - Capacita\n - Portata Massima\ndevono contenere numeri. ",
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
	 * @return   altezza inserita.
	 */
	public String getAltezza() {
		return txtAltezza.getText();
	}
	/**
	 * Ritorna profondita inserita.
	 *
	 * @return profondita inserita.
	 */
	public String getProfondita() {
		return txtProfondita.getText();
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
		return txtPortataMax.getText();
	}

}
