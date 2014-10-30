package view.lavoro.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Panello necessario per l'inserimento dei dati della richiesta.
 */
public class PanelRichiesta extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6633814599841251596L;
	/**
	 * Create the panel.
	 */
	


	private JLabel lblTipo,lblPortata,lblCapacita,lblLunghezza,lblAltezza,lblProfondita,lblRotazione;
	
	/** The txt min rotazione. */
	private JTextField txtMinPortata,txtMinCapacita,txtMinLunghezza,txtMinAltezza,txtMinProfondita,txtMinRotazione;
	
	/** The txt max rotazione. */
	private JTextField txtMaxPortata,txtMaxCapacita,txtMaxLunghezza,txtMaxAltezza,txtMaxProfondita,txtMaxRotazione;
	
	/** The btn add. */
	public JButton btnReset,btnAdd;
	
	/** The str. */
	String[] str= {"Gru","Ruspa","Camion","Escavatore"};
	
	/** The Type. */
	public JComboBox<String> Type=new JComboBox<String>(str);
	
	/**
	 * Istanzia il nuovo pannello
	 */
	public PanelRichiesta() {
		setLayout(new BorderLayout());
		btnReset=new JButton("Reset");
		btnReset.setName("reset");
		btnAdd=new JButton("Aggiungi");
		btnAdd.setName("aggiungi");
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearData();
			}
		});
		createPanel();
		Type.addActionListener(TypeListener());
		Type.setSelectedIndex(0);
		Type.setName("tipoMacchina");
		
	}
	private void createPanel(){
		lblTipo = new JLabel("Tipo Macchina: ");
		lblPortata = new JLabel("Portata: ");
		lblCapacita = new JLabel("Capacita: ");
		lblLunghezza  = new JLabel("Lunghezza: ");
		lblAltezza = new JLabel("Altezza: ");
		lblProfondita = new JLabel("Profondita: ");
		lblRotazione  = new JLabel("Angolo Rotazione:");
		
		txtMinPortata  = new JTextField();
		txtMinPortata.setName("minPortata");
		txtMinCapacita  = new JTextField();
		txtMinCapacita.setName("minCapacita");
		txtMinLunghezza  = new JTextField();
		txtMinLunghezza.setName("minLunghezza");
		txtMinAltezza  = new JTextField();
		txtMinAltezza.setName("minAltezza");
		txtMinProfondita  = new JTextField();
		txtMinProfondita.setName("minProfondita");
		txtMinRotazione  = new JTextField();
		txtMinRotazione.setName("minRotazione");
		
		txtMaxPortata  = new JTextField();
		txtMaxPortata.setName("maxPortata");
		txtMaxCapacita  = new JTextField();
		txtMaxCapacita.setName("maxCapacita");
		txtMaxLunghezza  = new JTextField();
		txtMaxLunghezza.setName("maxLunghezza");
		txtMaxAltezza  = new JTextField();
		txtMaxAltezza.setName("maxAltezza");
		txtMaxProfondita  = new JTextField();
		txtMaxProfondita.setName("maxProfondita");
		txtMaxRotazione  = new JTextField();
		txtMaxRotazione.setName("maxRotazione");
		
		JLabel lblLabel1=new JLabel("");
		JLabel lblLabel2=new JLabel("");
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
			.createSequentialGroup()
			.addGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblTipo)
					.addComponent(lblPortata)
					.addComponent(lblCapacita)
					.addComponent(lblLunghezza)
					.addComponent(lblAltezza)
					.addComponent(lblProfondita)
					.addComponent(lblRotazione)
					.addComponent(btnReset))
			.addGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(Type)
					.addComponent(txtMinPortata)
					.addComponent(txtMinCapacita)
					.addComponent(txtMinLunghezza)
					.addComponent(txtMinAltezza)
					.addComponent(txtMinProfondita)
					.addComponent(txtMinRotazione)
					.addComponent(btnAdd))
			.addGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblLabel1)
					.addComponent(txtMaxPortata)
					.addComponent(txtMaxCapacita)
					.addComponent(txtMaxLunghezza)
					.addComponent(txtMaxAltezza)
					.addComponent(txtMaxProfondita)
					.addComponent(txtMaxRotazione)
					.addComponent(lblLabel2)));
		layout.setVerticalGroup(layout
			.createSequentialGroup()
			.addGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblTipo )
					.addComponent(Type,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
					.addComponent(lblLabel1))
			.addGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblPortata )
					.addComponent(txtMinPortata,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(txtMaxPortata,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblCapacita )
					.addComponent(txtMinCapacita,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(txtMaxCapacita,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblLunghezza )
					.addComponent(txtMinLunghezza,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(txtMaxLunghezza,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblAltezza )
					.addComponent(txtMinAltezza,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(txtMaxAltezza,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblProfondita )
					.addComponent(txtMinProfondita,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(txtMaxProfondita,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblRotazione )
					.addComponent(txtMinRotazione,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(txtMaxRotazione,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(btnReset )
					.addComponent(btnAdd,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)
					.addComponent(lblLabel2)));

		this.setLayout(layout);
		
	}
	
	private ActionListener TypeListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearData();
				
				if(((String)Type.getSelectedItem()).equals("Gru")){
					setVisibilitaCapacita(false);
					setVisibilitaPortata(true);
					setVisibilitaLunghezza(true);
					setVisibilitaAltezza(true);
					setVisibilitaProfondita(false);
					setVisibilitaRotazione(true);
				}else if(((String)Type.getSelectedItem()).equals("Ruspa")){
					setVisibilitaCapacita(true);
					setVisibilitaPortata(true);
					setVisibilitaLunghezza(false);
					setVisibilitaAltezza(true);
					setVisibilitaProfondita(false);
					setVisibilitaRotazione(false);
				}else if(((String)Type.getSelectedItem()).equals("Escavatore")){
					setVisibilitaCapacita(true);
					setVisibilitaPortata(true);
					setVisibilitaLunghezza(false);
					setVisibilitaAltezza(true);
					setVisibilitaProfondita(true);
					setVisibilitaRotazione(false);
				}else if(((String)Type.getSelectedItem()).equals("Camion")){
					setVisibilitaCapacita(true);
					setVisibilitaPortata(true);
					setVisibilitaLunghezza(true);
					setVisibilitaAltezza(false);
					setVisibilitaProfondita(false);
					setVisibilitaRotazione(false);
				}
			}
		};
	}
	
	private void setVisibilitaCapacita(Boolean vis){
		lblCapacita.setVisible(vis);
		txtMinCapacita.setVisible(vis);
		txtMaxCapacita.setVisible(vis);
	}
	private void setVisibilitaPortata(Boolean vis){
		lblPortata.setVisible(vis);
		txtMinPortata.setVisible(vis);
		txtMaxPortata.setVisible(vis);
	}
	private void setVisibilitaLunghezza(Boolean vis){
		lblLunghezza.setVisible(vis);
		txtMinLunghezza.setVisible(vis);
		txtMaxLunghezza.setVisible(vis);
	}
	private void setVisibilitaAltezza(Boolean vis){
		lblAltezza.setVisible(vis);
		txtMinAltezza.setVisible(vis);
		txtMaxAltezza.setVisible(vis);
	}

	private void setVisibilitaProfondita(Boolean vis){
		lblProfondita.setVisible(vis);
		txtMinProfondita.setVisible(vis);
		txtMaxProfondita.setVisible(vis);
	}

	private void setVisibilitaRotazione(Boolean vis){
		lblRotazione.setVisible(vis);
		txtMinRotazione.setVisible(vis);
		txtMaxRotazione.setVisible(vis);
	}
	/*public void fill(ArrayList<String> data){
		dataInizioCantiere.setDate(null);
		dataFineCantiere.setDate(null);
		txtNomeCantiere.setText(data.get(1));
		btnReset.setText("Modifica");
	}*/
	
	/**
	 * Ritorna   portata minima.
	 *
	 * @return portata minima
	 */
	public int getMinPortata() {
		return txtMinPortata.getText().equals("")?0:Integer.parseInt(txtMinPortata.getText());
	}
	
	/**
	 * Ritorna capacita minima.
	 *
	 * @return capacita minima
	 */
	public int getMinCapacita() {
		return txtMinCapacita.getText().equals("")?0:Integer.parseInt(txtMinCapacita.getText());
	}
	
	/**
	 * Ritorna lunghezza minima.
	 *
	 * @return lunghezza minima
	 */
	public int getMinLunghezza() {
		return txtMinLunghezza.getText().equals("")?0:Integer.parseInt(txtMinLunghezza.getText());
	}
	
	/**
	 * Ritorna altezza minima.
	 *
	 * @return altezza minima
	 */
	public int getMinAltezza() {
		return txtMinAltezza.getText().equals("")?0:Integer.parseInt(txtMinAltezza.getText());
	}
	
	/**
	 * Ritorna profondita minima.
	 *
	 * @return profondita minima
	 */
	public int getMinProfondita() {
		return txtMinProfondita.getText().equals("")?0:Integer.parseInt(txtMinProfondita.getText());
	}
	
	/**
	 * Ritorna rotazione minima.
	 *
	 * @return rotazione minima
	 */
	public int getMinRotazione() {
		return txtMinRotazione.getText().equals("")?0:Integer.parseInt(txtMinRotazione.getText());
	}
	
	/**
	 * Ritorna tipo macchina.
	 *
	 * @return tipo macchina
	 */
	public String getTipoMacchina() {
		return (String)Type.getSelectedItem();
	}
	
	
	/**
	 * Ritorna portata.
	 *
	 * @return portata massima.
	 */
	public int getMaxPortata() {
		return txtMaxPortata.getText().equals("")?0:Integer.parseInt(txtMaxPortata.getText());
	}
	
	/**
	 * Ritorna capacita.
	 *
	 * @return capacita massima.
	 */
	public int getMaxCapacita() {
		return txtMaxCapacita.getText().equals("")?0:Integer.parseInt(txtMaxCapacita.getText());
	}
	
	/**
	 * Ritorna lunghezza.
	 *
	 * @return lunghezza massima.
	 */
	public int getMaxLunghezza() {
		return txtMaxLunghezza.getText().equals("")?0:Integer.parseInt(txtMaxLunghezza.getText());
	}
	
	/**
	 * Ritorna altezza.
	 *
	 * @return altezza massima.
	 */
	public int getMaxAltezza() {
		return txtMaxAltezza.getText().equals("")?0:Integer.parseInt(txtMaxAltezza.getText());
	}
	
	/**
	 * Ritorna profondita.
	 *
	 * @return profondita massima.
	 */
	public int getMaxProfondita() {
		return txtMaxProfondita.getText().equals("")?0:Integer.parseInt(txtMaxProfondita.getText());
	}
	
	/**
	 * Ritorna rotazione.
	 *
	 * @return rotazione massima.
	 */
	public int getMaxRotazione() {
		return txtMaxRotazione.getText().equals("")?0:Integer.parseInt(txtMaxRotazione.getText());
	}
	
	/**
	 * Pulisci i campi dai dati inseriti.
	 */
	public void clearData() {

		txtMinPortata.setText("");
		txtMinCapacita.setText("");
		txtMinLunghezza.setText("");
		txtMinAltezza.setText("");
		txtMinProfondita.setText("");
		txtMinRotazione.setText("");
		
		txtMaxPortata.setText("");
		txtMaxCapacita.setText("");
		txtMaxLunghezza.setText("");
		txtMaxAltezza.setText("");
		txtMaxProfondita.setText("");
		txtMaxRotazione.setText("");
	}

}
