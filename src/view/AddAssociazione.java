package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.organizer.data.Associazione;
import model.organizer.data.Macchina;

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


// 
/**
 *   Class AddAssociazione.
 */
public class AddAssociazione extends JDialog{

	/**   Constant serialVersionUID. */
	private static final long serialVersionUID = 8556951976345173917L;
	
	/**   content panel. */
	private final JPanel contentPanel = new JPanel();

	/**   chiudi btn. */
	private JButton okButton,btnAggiungi,btnRimuovi,chiudiBtn;
	
	/**   table. */
	private JTable table;
	
	/**   list scroller. */
	private JScrollPane scrollPane,listScroller;
	
	/**   list. */
	private JList list;
	
	/**   lbl cantiere. */
	private JLabel lblCantiere;
	
	/**   table model. */
	public TableModel tableModel;
	
	/**   list model. */
	private DefaultListModel listModel;
	
	/**   tipo macchina. */
	private JComboBox tipoMacchina;
	
	/**   data fine. */
	private JDateChooser dataInizio,dataFine;
	

	/**
	 * Create   dialog.
	 *
	 * @param view   view
	 * @param nomeCantiere   nome cantiere
	 * @param inizio   inizio
	 * @param fine   fine
	 */


	/**
	 * @wbp.parser.constructor
	 */
	public AddAssociazione(JFrame view,String nomeCantiere,GregorianCalendar inizio,GregorianCalendar fine) {
		super(view);
		setTitle("Modifica Associazioni");
		setSize(new Dimension(790, 363));
		contentPanel.setLayout(new BorderLayout());

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(okButtonListeners());
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
	 * Sets   ok btn listeners.
	 *
	 * @param act   new ok btn listeners
	 */
	private ActionListener okButtonListeners() {
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		};
	}
	
	/**
	 * Sets   chiudi button listeners.
	 *
	 * @param act   new chiudi button listeners
	 */
	public void setChiudiButtonListeners(ActionListener act) {
		chiudiBtn.addActionListener(act);
	}
	
	/**
	 * Adds   macchina listener.
	 *
	 * @param act   act
	 */
	public void addAggiungiAssociazioneListener(ActionListener act){
		btnAggiungi.addActionListener(act);
	}
	
	/**
	 * Adds   combo box listener.
	 *
	 * @param act   act
	 */
	public void addComboBoxListener(ActionListener act){
		tipoMacchina.addActionListener(act);
	}
	
	/**
	 * Adds   rimuovi listener.
	 *
	 * @param act   act
	 */
	public void addRimuoviAssoziazioneListener(ActionListener act){
		btnRimuovi.addActionListener(act);
	}
	
	/**
	 * Clear list.
	 */
	public void clearList(){
		listModel.clear();
	}
	public void rimuoviMacchinaSelezionata(){
		listModel.remove(list.getSelectedIndex());
	}
	/**
	 * Gets   selected associazione.
	 *
	 * @return   selected associazione
	 */
	public int getCodiceAssociazioneSelezionata(){
		return table.getSelectedRow()==-1?-1:(int)tableModel.getValueAt(table.getSelectedRow())[0];
	}
	
	/**
	 * Rimuovi associazione selezionata.
	 */
	public void rimuoviAssociazioneSelezionata(){
		tableModel.removeData(table.getSelectedRow());
	}
	
	/**
	 * Adds   data.
	 *
	 * @param data   data
	 */
	public void addData(Object[] data){
		tableModel.addData(data);
	}
	
	/**
	 * Gets   associazioni.
	 *
	 * @return   associazioni
	 */
	public ArrayList<Object[]> getAssociazioni(){
		return tableModel.getData();
	}
	
	/**
	 * Gets   list selected.
	 *
	 * @return   list selected
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
	 * Gets   data inizio.
	 *
	 * @return   data inizio
	 */
	public GregorianCalendar getDataInizio() {
		if(dataInizio.getDate()==null)
			return null;
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(dataInizio.getDate());
		return gc;
	}
	
	/**
	 * Sets   data inizio.
	 *
	 * @param d   new data inizio
	 */
	public void setDataInizio(Date d) {
		dataInizio.setDate(d);
	}
	
	/**
	 * Gets   data fine.
	 *
	 * @return   data fine
	 */
	public GregorianCalendar getDataFine() {
		if(dataFine.getDate()==null)
			return null;
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(dataFine.getDate());
		return gc;
	}
	
	/**
	 * Sets   data fine.
	 *
	 * @param d   new data fine
	 */
	public void setDataFine(Date d) {
		dataFine.setDate(d);
	}
	
	/**
	 * Aggiungi macchina a lista.
	 *
	 * @param m   m
	 */
	public void aggiungiMacchinaALista(Macchina m){
		listModel.addElement(m);
	}
	
	
	
	/**
	 *   Class TableModel.
	 */
	class TableModel extends AbstractTableModel implements Observer{

		/**   Constant serialVersionUID. */
		private static final long serialVersionUID = -6590327420669891637L;

		/**   columns name. */
		private String[] columnsName = {"Macchina","dataInizio","dataFine"};

		/**   data. */
		private ArrayList<Object[]> data=new ArrayList<Object[]>();
		
		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		@Override
		public int getColumnCount() {
			// 
			return columnsName.length;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		@Override
		public int getRowCount() {
			return data.size();
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		public String getColumnName(int col) {
	        return columnsName[col];
	    }
		
		/**
		 * Removes   data.
		 *
		 * @param i   i
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
			// 
			if(arg1==1 || arg1==2){
				SimpleDateFormat df = new SimpleDateFormat();
			    df.applyPattern("dd/MM/yyyy");
			    return df.format(((GregorianCalendar)data.get(arg0)[arg1+1]).getTime());
			}
			return data.get(arg0)[arg1+1];
		}
		public Object[] getValueAt(int arg0) {

			return data.get(arg0);
		}
    	/**
    	 * Adds   data.
    	 *
    	 * @param obj   obj
    	 * @return true, if successful
    	 */
    	public boolean addData(Object[] obj){
	    	data.add(obj);
	    	fireTableDataChanged();
	    	return true;
	    }
	    
    	/**
    	 * Gets   data.
    	 *
    	 * @return   data
    	 */
    	public ArrayList<Object[]> getData(){
	    	return data;
	    }

		@Override
		public void update(Observable arg0, Object arg1) {
			boolean trovato=false;
			for(int i=0;i<data.size();i++){
				if(data.get(i)[0]==((Object[])arg1)[0]){
					data.set(i,(Object[])arg1);
					fireTableDataChanged();
					trovato=true;
				}
			}
			if(trovato==false){
				addData((Object[])arg1);
			}

			
		}
		
		
	}
}
