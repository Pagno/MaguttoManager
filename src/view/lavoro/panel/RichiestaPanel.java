package view.lavoro.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RichiestaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6633814599841251596L;
	/**
	 * Create the panel.
	 */
	


	private JLabel lblTipo,lblPortata,lblCapacita,lblLunghezza,lblAltezza,lblProfondita,lblRotazione;
	private JTextField txtPortata,txtCapacita,txtLunghezza,txtAltezza,txtProfondita,txtRotazione;
	public JButton btnReset,btnAdd;
	
	String[] str= {"Gru","Ruspa","Camion","Escavatore"};
	public JComboBox<String> Type=new JComboBox<String>(str);
	
	public RichiestaPanel() {
		setLayout(new BorderLayout());
		btnReset=new JButton("Reset");
		btnAdd=new JButton("Aggiungi");
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		createPanel();
		Type.addActionListener(TypeListener());
		//Type.setSelectedIndex(0);
		
	}
	private void createPanel(){
		lblTipo = new JLabel("Tipo Macchina: ");
		lblPortata = new JLabel("Portata: ");
		lblCapacita = new JLabel("Capacita: ");
		lblLunghezza  = new JLabel("Lunghezza: ");
		lblAltezza = new JLabel("Altezza: ");
		lblProfondita = new JLabel("Profondita: ");
		lblRotazione  = new JLabel("Angolo Rotazione:");
		
		txtPortata  = new JTextField();
		txtCapacita  = new JTextField();
		txtLunghezza  = new JTextField();
		txtAltezza  = new JTextField();
		txtProfondita  = new JTextField();
		txtRotazione  = new JTextField();
		
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
					.addComponent(txtPortata)
					.addComponent(txtCapacita)
					.addComponent(txtLunghezza)
					.addComponent(txtAltezza)
					.addComponent(txtProfondita)
					.addComponent(txtRotazione)
					.addComponent(btnAdd)));
		layout.setVerticalGroup(layout
			.createSequentialGroup()
			.addGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblTipo )
					.addComponent(Type,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblPortata )
					.addComponent(txtPortata,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblCapacita )
					.addComponent(txtCapacita,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblLunghezza )
					.addComponent(txtLunghezza,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblAltezza )
					.addComponent(txtAltezza,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblProfondita )
					.addComponent(txtProfondita,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblRotazione )
					.addComponent(txtRotazione,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
			.addGroup(		
				layout.createParallelGroup(Alignment.LEADING)
					.addComponent(btnReset )
					.addComponent(btnAdd,
						GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)));

		this.setLayout(layout);
		
	}
	
	private ActionListener TypeListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
	
	public void clear(){
		txtPortata.setText("");
		txtCapacita.setText("");
		txtLunghezza.setText("");
		txtAltezza.setText("");
		txtProfondita.setText("");
		txtRotazione.setText("");
	}
	public void btnAddActionListener(ActionListener act){
		for( ActionListener al : btnReset.getActionListeners() ) {
			btnAdd.removeActionListener( al );
	    }
		btnAdd.addActionListener(act);
		
	}
	private void setVisibilitaCapacita(Boolean vis){
		lblCapacita.setVisible(vis);
		txtCapacita.setVisible(vis);
	}
	private void setVisibilitaPortata(Boolean vis){
		lblPortata.setVisible(vis);
		txtPortata.setVisible(vis);
	}
	private void setVisibilitaLunghezza(Boolean vis){
		lblLunghezza.setVisible(vis);
		txtLunghezza.setVisible(vis);
	}
	private void setVisibilitaAltezza(Boolean vis){
		lblAltezza.setVisible(vis);
		txtAltezza.setVisible(vis);
	}
	private void setVisibilitaProfondita(Boolean vis){
		lblProfondita.setVisible(vis);
		txtProfondita.setVisible(vis);
	}
	private void setVisibilitaRotazione(Boolean vis){
		lblRotazione.setVisible(vis);
		txtRotazione.setVisible(vis);
	}
	/*public void fill(ArrayList<String> data){
		dataInizioCantiere.setDate(null);
		dataFineCantiere.setDate(null);
		txtNomeCantiere.setText(data.get(1));
		btnReset.setText("Modifica");
	}*/
	
	
	

}
