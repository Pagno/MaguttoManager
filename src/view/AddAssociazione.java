package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Associazione;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;


public class AddAssociazione extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8556951976345173917L;
	
	private final JPanel contentPanel = new JPanel();

	private JButton okButton,btnAggiungi;
	private ArrayList<Associazione> lista;
	private JTable table;
	private JScrollPane scrollPane;
	private JList list;
	private JLabel lblCantiere;
	private TableModel tableModel;
	private JDateChooser dataInizio,dataFine;

	/**
	 * Create the dialog.
	 */


	/**
	 * @wbp.parser.constructor
	 */
	public AddAssociazione(JDialog view,String nomeCantiere,Date inizio,Date fine) {
		super(view);
		setSize(new Dimension(600, 310));
		lista =new ArrayList<Associazione>();
		contentPanel.setLayout(new BorderLayout());

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);


		JButton chiudiBtn = new JButton("Chiudi");
		chiudiBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		chiudiBtn.setActionCommand("Cancel");
		buttonPane.add(chiudiBtn);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblCantiere= new JLabel("Cantiere "+nomeCantiere);
		if(nomeCantiere.equals(""))
			lblCantiere.setText("");
		lblCantiere.setBounds(12, 22, 270, 15);
		panel.add(lblCantiere);
		
		
		tableModel = new TableModel();
		
		table = new JTable(tableModel);
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(12, 49, 171, 173);
		panel.add(scrollPane);

		table.setPreferredScrollableViewportSize(scrollPane.getPreferredSize());
		
		
		list = new JList();
		list.setBounds(301, 48, 160, 173);
		panel.add(list);

		dataInizio = new JDateChooser();
		dataInizio.setDateFormatString("dd-MM-yy");
		dataInizio.setMinSelectableDate(inizio);
		dataInizio.setMaxSelectableDate(fine);
		dataInizio.setBounds(301, 22, 120, 25);
		dataInizio.setName("dataInizio");
		panel.add(dataInizio);

		dataFine = new JDateChooser();
		dataFine.setDateFormatString("dd-MM-yy");
		dataFine.setMaxSelectableDate(inizio);
		dataFine.setMaxSelectableDate(fine);
		dataFine.setBounds(421, 22, 120, 25);
		dataFine.setName("dataFine");
		panel.add(dataFine);
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(195, 97, 94, 25);
		panel.add(btnAggiungi);

		setVisible(true);
	}
	public ArrayList<Associazione> getAssociazioni(){
		return lista;
	}
	public void setInsertButtonListeners(ActionListener act) {
		okButton.addActionListener(act);
	}
	public void aggiungiAssoziazioneListenet(ActionListener act){
		btnAggiungi.addActionListener(act);
	}
	public void addData(Object[] data){
		tableModel.addData(data);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener evt){
		dataFine.addPropertyChangeListener(evt);
		dataInizio.addPropertyChangeListener(evt);
	}
	public Date getDataInizio() {
		return dataInizio.getDate();
	}
	public Date getDataFine() {
		return dataFine.getDate();
	}
	class TableModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6590327420669891637L;

		String[] columnsName = {"Macchina","dataInizio","dataFine"};

		private ArrayList<Object[]> data=new ArrayList<Object[]>();
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnsName.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.size();
		}
		public String getColumnName(int col) {
	        return columnsName[col];
	    }

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return data.get(arg0)[arg1];
		}
	    public boolean addData(Object[] obj){
	    	data.add(obj);
	    	fireTableDataChanged();
	    	return true;
	    }
	}
}
