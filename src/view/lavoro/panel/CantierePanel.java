package view.lavoro.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class CantierePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8114659454885608761L;
	


	private JTextField txtNomeCantiere, txtIndirizzo;
	private JLabel lblPriorita,lblNomeCantiere, lblIndirizzoCantiere, lblDataInizioCantiere,lblDataFineCantiere;
	private JDateChooser dataInizioCantiere,dataFineCantiere;
	private JButton btnAdd,btnReset;
	String[] priority={"BASSA","MEDIA","ALTA"};
	private JComboBox<String> priorita=new JComboBox<String>(priority);
	
	public CantierePanel() {
		setLayout(new BorderLayout());
		createPanel();
		btnReset.setVisible(false);
	}
	private void createPanel(){
		lblNomeCantiere = new JLabel("Nome:");
		lblPriorita = new JLabel("Priorita:");
		lblIndirizzoCantiere = new JLabel("Indirizzo Cantiere:");
		lblDataInizioCantiere = new JLabel("Data Inizio:");
		lblDataFineCantiere = new JLabel("Data Fine:");
		dataInizioCantiere = new JDateChooser();
		dataInizioCantiere.getJCalendar().setTodayButtonVisible(true);
		dataInizioCantiere.getJCalendar().setNullDateButtonVisible(true);
		dataFineCantiere = new JDateChooser();
		txtNomeCantiere = new JTextField();
		txtNomeCantiere.setColumns(15);
		txtIndirizzo = new JTextField();
		txtIndirizzo.setColumns(15);
		btnAdd=new JButton("Edit");
		btnReset=new JButton("Reset");
		
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeCantiere)
								.addComponent(lblIndirizzoCantiere)
								.addComponent(lblDataInizioCantiere)
								.addComponent(lblDataFineCantiere)
								.addComponent(lblPriorita)
								.addComponent(btnReset))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNomeCantiere)
								.addComponent(txtIndirizzo)
								.addComponent(dataInizioCantiere)
								.addComponent(dataFineCantiere)
								.addComponent(priorita)
								.addComponent(btnAdd)));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeCantiere)
								.addComponent(txtNomeCantiere,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIndirizzoCantiere)
								.addComponent(txtIndirizzo,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataInizioCantiere)
								.addComponent(dataInizioCantiere,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataFineCantiere)
								.addComponent(dataFineCantiere,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPriorita)
								.addComponent(priorita,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(		
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnReset)
								.addComponent(btnAdd,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));

		this.setLayout(layout);
	}
	
	public void clear(){
		dataInizioCantiere.setDate(null);
		dataFineCantiere.setDate(null);
		txtNomeCantiere.setText("");
		btnAdd.setText("Inserisci");
	}
	
	public void btnAddActionListener(ActionListener act){
		for( ActionListener al : btnAdd.getActionListeners() ) {
			btnAdd.removeActionListener( al );
	    }
		btnAdd.addActionListener(act);
		
	}
	/*public void fill(ArrayList<String> data){
		dataInizioCantiere.setDate(null);
		dataFineCantiere.setDate(null);
		txtNomeCantiere.setText(data.get(1));
		btnAddCantiere.setText("Modifica");
	}*/
	

	//private GregorianCalendar di,df;
	public void setDatiCantiere(Object[] v){
		
		txtNomeCantiere.setText(v[1].toString());
		txtIndirizzo.setText(v[2].toString());
		//dc.setText(v[3].toString());
		String[] tokens = ((String)v[3]).split("/");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try{
			dataInizioCantiere.setDate(df.parse((String)(v[3])));
			dataFineCantiere.setDate(df.parse((String)v[4]));
		}catch(ParseException error){
			System.out.println("Formato date errato.");
		}
		priorita.setSelectedItem(v[5]);
	}
	
	public void setAddCantiereListeners(ActionListener act) {
		btnAdd.addActionListener(act);
	}
	public String getNomeCantiere() {return txtNomeCantiere.getText();}
	public String getPrioritaCantiere() {return (String)priorita.getSelectedItem();}
	public String getIndirizzoCantiere() {return txtIndirizzo.getText();}
	public Date getDataInizioCantiere(){return dataInizioCantiere.getDate();}
	public Date getDataFineCantiere(){return dataFineCantiere.getDate();}

	public void setNomeCantiere(String nome) {txtNomeCantiere.setText(nome);}
	public void setPrioritaCantiere(String priorita) {this.priorita.setSelectedItem(priorita); }
	public void setIndirizzoCantiere(String Indirizzo) {txtIndirizzo.setText(Indirizzo);}
	public void setDataInizioCantiere(GregorianCalendar inizio){dataInizioCantiere.setDate(inizio.getTime());}
	public void setDataFineCantiere(GregorianCalendar fine){dataFineCantiere.setDate(fine.getTime());}
	
	
	public void setDataInizioCantiereChangedListener(PropertyChangeListener list){dataInizioCantiere.addPropertyChangeListener(list);}
	public void setMinimaDataFineCantiere(Date d){dataFineCantiere.setMinSelectableDate(d);}
	public void setMassimaDataInizioCantiere(Date d){dataInizioCantiere.setMaxSelectableDate(d);}
	public void setMinimaDataFine(Date d){dataFineCantiere.setMinSelectableDate(d);}
	public void setMassimaDataInizio(Date d){dataInizioCantiere.setMaxSelectableDate(d);}

}
