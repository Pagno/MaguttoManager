package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class MyTableModel.
 */
public class MyTableModel extends AbstractTableModel implements Observer{ 
 	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The column names. */
	private String[] columnNames;
	
	/** The data. */
	private ArrayList<Object[]> data=new ArrayList<Object[]>();
			
    /**
     * Instantiates a new my table model.
     *
     * @param column the column
     */
    public MyTableModel(String[] column){
    	columnNames=column;
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
    
    /**
     * Remve data.
     *
     * @param data the data
     * @return true, if successful
     */
    public boolean remveData(Object[] data){
    	if(this.data.remove(data)){
    		fireTableDataChanged();
    		return true;
    	}
    	return false;
    }
    
    /**
     * Reset data.
     *
     * @param d the d
     */
    public void resetData(ArrayList<Object[]> d){
    	data=d;
    }
    
    /**
     * Reset data.
     */
    public void resetData(){
    	data.clear();
    	fireTableDataChanged();
    }
    
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
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
        return columnNames[col];
    }
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0)[arg1+1];
	}
	
	/**
	 * Removes the row.
	 *
	 * @param row the row
	 */
	public void removeRow(int row){
		data.remove(row);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
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
	
	/**
	 * Gets the row data.
	 *
	 * @param row the row
	 * @return the row data
	 */
	public Object[] getRowData(int row){
		return data.get(row);
	}
}