package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


// TODO: Auto-generated Javadoc
/**
 * The Class AddAssociazione.
 */
public class AddAssociazione extends JDialog{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8556951976345173917L;
	
	/** The content panel. */
	private final JPanel contentPanel = new JPanel();

	/** The chiudi btn. */
	private JButton okButton,btnAggiungi,btnRimuovi,chiudiBtn;
	
	/** The table. */
	private JTable table;
	
	/** The list scroller. */
	private JScrollPane scrollPane,listScroller;
	
	/** The list. */
	private JList list;
	
	/** The lbl cantiere. */
	private JLabel lblCantiere;
	
	/** The table model. */
	private TableModel tableModel;
	
	/** The list model. */
	private DefaultListModel listModel;
	
	/** The tipo macchina. */
	private JComboBox tipoMacchina;
	
	/** The data fine. */
	private JDateChooser dataInizio,dataFine;
	

	/**
	 * Create the dialog.
	 *
	 * @param view the view
	 * @param nomeCantiere the nome cantiere
	 * @param inizio the inizio
	 * @param fine the fine
	 */


	/**
	 * @wbp.parser.constructor
	 */
	public AddAssociazione(JFrame view,String nomeCantiere,GregorianCalendar inizio,GregorianCalendar fine) {
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


		chiudiBtn = new JButton("Chiudi");
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
		dataInizio.setMinSelectableDate(inizio.getTime());
		dataInizio.setMaxSelectableDate(fine.getTime());
		dataInizio.setBounds(548, 22, 120, 25);
		dataInizio.setName("dataInizio");
		panel.add(dataInizio);

		dataFine = new JDateChooser();
		dataFine.setDateFormatString("dd-MM-yy");
		dataFine.setMinSelectableDate(inizio.getTime());
		dataFine.setMaxSelectableDate(fine.getTime());
		dataFine.setBounds(668, 22, 120, 25);
		dataFine.setName("dataFine");
		panel.add(dataFine);
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(330, 135, 120, 25);
		panel.add(btnAggiungi);
		
		tipoMacchina = new JComboBox();
		tipoMacchina.setModel(new DefaultComboBoxModel(new String[] {"Ruspa", "Gru", "Camion", "Escavatore"}));
		tipoMacchina.setBounds(418, 22, 126, 25);
		panel.add(tipoMacchina);
		
		btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.setBounds(330, 177, 117, 25);
		panel.add(btnRimuovi);

		setVisible(true);
	}

	/**
	 * Sets the ok btn listeners.
	 *
	 * @param act the new ok btn listeners
	 */
	public void setOkBtnListeners(ActionListener act) {
		okButton.addActionListener(act);
	}
	
	/**
	 * Sets the chiudi button listeners.
	 *
	 * @param act the new chiudi button listeners
	 */
	public void setChiudiButtonListeners(ActionListener act) {
		chiudiBtn.addActionListener(act);
	}
	
	/**
	 * Adds the macchina listener.
	 *
	 * @param act the act
	 */
	public void addMacchinaListener(ActionListener act){
		btnAggiungi.addActionListener(act);
	}
	
	/**
	 * Adds the combo box listener.
	 *
	 * @param act the act
	 */
	public void addComboBoxListener(ActionListener act){
		tipoMacchina.addActionListener(act);
	}
	
	/**
	 * Adds the rimuovi listener.
	 *
	 * @param act the act
	 */
	public void addRimuoviListener(ActionListener act){
		btnRimuovi.addActionListener(act);
	}
	
	/**
	 * Clear list.
	 */
	public void clearList(){
		listModel.clear();
	}
	
	/**
	 * Gets the selected associazione.
	 *
	 * @return the selected associazione
	 */
	public int getSelectedAssociazione(){
		return table.getSelectedRow();
	}
	
	/**
	 * Rimuovi associazione selezionata.
	 */
	public void rimuoviAssociazioneSelezionata(){
		tableModel.removeData(table.getSelectedRow());
	}
	
	/**
	 * Adds the data.
	 *
	 * @param data the data
	 */
	public void addData(Object[] data){
		tableModel.addData(data);
	}
	
	/**
	 * Gets the associazioni.
	 *
	 * @return the associazioni
	 */
	public ArrayList<Object[]> getAssociazioni(){
		return tableModel.getData();
	}
	
	/**
	 * Gets the list selected.
	 *
	 * @return the list selected
	 */
	public Macchina getListSelected(){
		return (Macchina)list.getSelectedValue();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Window#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener evt){
		dataFine.addPropertyChangeListener(evt);
		dataInizio.addPropertyChangeListener(evt);
	}
	
	/**
	 * Gets the data inizio.
	 *
	 * @return the data inizio
	 */
	public GregorianCalendar getDataInizio() {
		if(dataInizio.getDate()==null)
			return null;
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(dataInizio.getDate());
		return gc;
	}
	
	/**
	 * Sets the data inizio.
	 *
	 * @param d the new data inizio
	 */
	public void setDataInizio(Date d) {
		dataInizio.setDate(d);
	}
	
	/**
	 * Gets the data fine.
	 *
	 * @return the data fine
	 */
	public GregorianCalendar getDataFine() {
		if(dataFine.getDate()==null)
			return null;
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(dataFine.getDate());
		return gc;
	}
	
	/**
	 * Sets the data fine.
	 *
	 * @param d the new data fine
	 */
	public void setDataFine(Date d) {
		dataFine.setDate(d);
	}
	
	/**
	 * Aggiungi macchina a lista.
	 *
	 * @param m the m
	 */
	public void aggiungiMacchinaALista(Macchina m){
		listModel.addElement(m);
	}
	
	
	
	/**
	 * The Class TableModel.
	 */
	class TableModel extends AbstractTableModel{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -6590327420669891637L;

		/** The columns name. */
		private String[] columnsName = {"Macchina","dataInizio","dataFine"};

		/** The data. */
		private ArrayList<Object[]> data=new ArrayList<Object[]>();
		
		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnsName.length;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.size();
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		public String getColumnName(int col) {
	        return columnsName[col];
	    }
		
		/**
		 * Removes the data.
		 *
		 * @param i the i
		 * @return true, if successful
		 */
		public boolean removeData(int i){
	    	try{
	    		data.remove(i);
	    		fireTableDataChanged();
	    		return true;
	    	}catch(IndexOutOfBoundsException error){
	    		return false;
	    	}
	    }

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			if(arg1==1 || arg1==2){
				SimpleDateFormat df = new SimpleDateFormat();
			    df.applyPattern("dd/MM/yyyy");
			    return df.format(((GregorianCalendar)data.get(arg0)[arg1+1]).getTime());
			}
			return data.get(arg0)[arg1+1];
		}
	    
    	/**
    	 * Adds the data.
    	 *
    	 * @param obj the obj
    	 * @return true, if successful
    	 */
    	public boolean addData(Object[] obj){
	    	data.add(obj);
	    	fireTableDataChanged();
	    	return true;
	    }
	    
    	/**
    	 * Gets the data.
    	 *
    	 * @return the data
    	 */
    	public ArrayList<Object[]> getData(){
	    	return data;
	    }
	}
}
