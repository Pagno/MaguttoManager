package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel implements Observer{ 
 	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames;
	
	private ArrayList<Object[]> data=new ArrayList<Object[]>();
			
    public MyTableModel(String[] column){
    	columnNames=column;
    }
    
    public boolean addData(Object[] obj){
    	data.add(obj);
    	fireTableDataChanged();
    	return true;
    }
    public boolean removeData(int i){
    	try{
    		data.remove(i);
    		fireTableDataChanged();
    		return true;
    	}catch(IndexOutOfBoundsException error){
    		return false;
    	}
    }
    public boolean remveData(Object[] data){
    	if(this.data.remove(data)){
    		fireTableDataChanged();
    		return true;
    	}
    	return false;
    }
    public void resetData(ArrayList<Object[]> d){
    	data=d;
    }
    public void resetData(){
    	data.clear();
    	fireTableDataChanged();
    }
    
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

    public String getColumnName(int col) {
        return columnNames[col];
    }
	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0)[arg1+1];
	}
	public void removeRow(int row){
		data.remove(row);
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
	
	public Object[] getRowData(int row){
		return data.get(row);
	}
}