package view.lavoro.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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
	


	private JLabel lbldato1,lbldato2,lbldato3,lbldato4,lbldato5,lbldato6;
	private JTextField txtdato1,txtdato2,txtdato3,txtdato4,txtdato5,txtdato6;
	public JButton btnReset,btnAdd;
	
	
	public RichiestaPanel() {
		setLayout(new BorderLayout());
		btnReset=new JButton("Reset");
		btnAdd=new JButton("Aggiungi");
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 
				clear();
			}
		});

		createPanel();
	}
	private void createPanel(){
		lbldato1 = new JLabel("NomeLavoro:");
		lbldato2 = new JLabel("Data Inizio:");
		lbldato3  = new JLabel("Data Fine:");
		lbldato4 = new JLabel("NomeLavoro:");
		lbldato5 = new JLabel("Data Inizio:");
		lbldato6  = new JLabel("Data Fine:");
		
		txtdato1  = new JTextField();
		txtdato2  = new JTextField();
		txtdato3  = new JTextField();
		txtdato4  = new JTextField();
		txtdato5  = new JTextField();
		txtdato6  = new JTextField();
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldato1)
								.addComponent(lbldato2)
								.addComponent(lbldato3)
								.addComponent(lbldato4)
								.addComponent(lbldato5)
								.addComponent(lbldato6)
								.addComponent(btnReset))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtdato1)
								.addComponent(txtdato2)
								.addComponent(txtdato3)
								.addComponent(txtdato4)
								.addComponent(txtdato5)
								.addComponent(txtdato6)
								.addComponent(btnAdd)));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldato1 )
								.addComponent(txtdato1,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldato2 )
								.addComponent(txtdato2,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldato3 )
								.addComponent(txtdato3,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldato4 )
								.addComponent(txtdato4,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldato5 )
								.addComponent(txtdato5,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldato6 )
								.addComponent(txtdato6,
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
	
	public void clear(){
		txtdato1.setText("");
		txtdato2.setText("");
		txtdato3.setText("");
		txtdato4.setText("");
		txtdato5.setText("");
		txtdato6.setText("");
	}
	public void btnAddActionListener(ActionListener act){
		for( ActionListener al : btnReset.getActionListeners() ) {
			btnAdd.removeActionListener( al );
	    }
		btnAdd.addActionListener(act);
		
	}
	/*public void fill(ArrayList<String> data){
		dataInizioCantiere.setDate(null);
		dataFineCantiere.setDate(null);
		txtNomeCantiere.setText(data.get(1));
		btnReset.setText("Modifica");
	}*/
	
	
	

}
