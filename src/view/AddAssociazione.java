package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Associazione;
import model.Macchina;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;


public class AddAssociazione extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8556951976345173917L;
	
	private final JPanel contentPanel = new JPanel();

	private JButton okButton,btnAggiungi;
	private JTable table;
	private JScrollPane scrollPane,listScroller;
	private JList list;
	private JLabel lblCantiere;
	private TableModel tableModel;
	private DefaultListModel listModel;
	private JDateChooser dataInizio,dataFine;

	/**
	 * Create the dialog.
	 */


	/**
	 * @wbp.parser.constructor
	 */
	public AddAssociazione(JDialog view,String nomeCantiere,Date inizio,Date fine) {
		super(view);
		setSize(new Dimension(790, 363));
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
		scrollPane.setBounds(12, 49, 306, 233);
		panel.add(scrollPane);

		table.setPreferredScrollableViewportSize(scrollPane.getPreferredSize());
		

		listModel=new DefaultListModel();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		

		listScroller = new JScrollPane(list);
		listScroller.setBounds(462, 48, 314, 234);
		panel.add(listScroller);

		dataInizio = new JDateChooser();
		dataInizio.setDateFormatString("dd-MM-yy");
		dataInizio.setMinSelectableDate(inizio);
		dataInizio.setMaxSelectableDate(fine);
		dataInizio.setBounds(548, 22, 120, 25);
		dataInizio.setName("dataInizio");
		panel.add(dataInizio);

		dataFine = new JDateChooser();
		dataFine.setDateFormatString("dd-MM-yy");
		dataFine.setMaxSelectableDate(inizio);
		dataFine.setMaxSelectableDate(fine);
		dataFine.setBounds(668, 22, 120, 25);
		dataFine.setName("dataFine");
		panel.add(dataFine);
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(330, 135, 120, 25);
		panel.add(btnAggiungi);

		setVisible(true);
	}
	public void setInsertButtonListeners(ActionListener act) {
		okButton.addActionListener(act);
	}
	public void aggiungiAssoziazioneListener(ActionListener act){
		btnAggiungi.addActionListener(act);
	}
	public void addData(String[] data){
		tableModel.addData(data);
	}
	public Macchina getListSlected(){
		return (Macchina)list.getSelectedValue();
	}
	public void addPropertyChangeListener(PropertyChangeListener evt){
		dataFine.addPropertyChangeListener(evt);
		dataInizio.addPropertyChangeListener(evt);
	}
	public GregorianCalendar getDataInizio() {
		if(dataInizio.getDate()==null)
			return null;
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(dataInizio.getDate());
		return gc;
	}
	public void setDataInizio(Date d) {
		dataInizio.setDate(d);
	}
	public GregorianCalendar getDataFine() {
		if(dataFine.getDate()==null)
			return null;
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(dataFine.getDate());
		return gc;
	}
	public void setDataFine(Date d) {
		dataFine.setDate(d);
	}
	
	public void aggiungiMacchinaALista(Macchina m){
		listModel.addElement(m);
	}
	
	
	
	class TableModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6590327420669891637L;

		String[] columnsName = {"Macchina","dataInizio","dataFine"};

		private ArrayList<String[]> data=new ArrayList<String[]>();
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
			return data.get(arg0)[arg1+1];
		}
	    public boolean addData(String[] obj){
	    	data.add(obj);
	    	fireTableDataChanged();
	    	return true;
	    }
	}
}
