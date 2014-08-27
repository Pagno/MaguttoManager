package view.lavoro.panel;

import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

public class VisualizzaRichiestaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8632987215239339395L;
	
	

	private JLabel lblText1,lblText2,lblText3,lblText4,lblText5,lblText6;
	private JLabel lblDato1,lblDato2,lblDato3,lblDato4,lblDato5,lblDato6;
	/**
	 * Create the panel.
	 */
	public VisualizzaRichiestaPanel() {
		super();
		createPanel();
	}
	private void createPanel(){
		lblDato1 = new JLabel("NomeLavoro:");
		lblDato2 = new JLabel("Data Inizio:");
		lblDato3  = new JLabel("Data Fine:");
		lblDato4 = new JLabel("NomeLavoro:");
		lblDato5 = new JLabel("Data Inizio:");
		lblDato6  = new JLabel("Data Fine:");
		

		lblText1 = new JLabel("NomeLavoro:");
		lblText2 = new JLabel("Data Inizio:");
		lblText3 = new JLabel("Data Fine:");
		lblText4 = new JLabel("NomeLavoro:");
		lblText5 = new JLabel("Data Inizio:");
		lblText6  = new JLabel("Data Fine:");
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblText1)
								.addComponent(lblText2)
								.addComponent(lblText3)
								.addComponent(lblText4)
								.addComponent(lblText5)
								.addComponent(lblText6))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDato1)
								.addComponent(lblDato2)
								.addComponent(lblDato3)
								.addComponent(lblDato4)
								.addComponent(lblDato5)
								.addComponent(lblDato6)));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDato1 )
								.addComponent(lblText1,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDato2 )
								.addComponent(lblText2,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDato3 )
								.addComponent(lblText3,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDato4 )
								.addComponent(lblText4,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDato5 )
								.addComponent(lblText5,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDato6 )
								.addComponent(lblText6,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));

		this.setLayout(layout);
		
	}
	public void loadData(ArrayList<String> dati){
		
	}
}
