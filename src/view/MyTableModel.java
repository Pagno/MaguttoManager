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
	
	
	private Object[] v1={"Kathy", "Smith","Snowboarding", new Integer(5), new Boolean(false)};   
	private Object[] v2={"John", "Doe","Rowing", new Integer(3), new Boolean(true)};    	
	private ArrayList<Object[]> data=new ArrayList<Object[]>();
			//new ArrayList<Object>("Kathy","Smith","Snowboarding", new Integer(5),new Boolean(false))};
    private Object[][] data1;/*={
            {"Kathy", "Smith",
                "Snowboarding", new Integer(5), new Boolean(false)},
               {"John", "Doe",
                "Rowing", new Integer(3), new Boolean(true)},
               {"Sue", "Black",
                "Knitting", new Integer(2), new Boolean(false)},
               {"Jane", "White",
                "Speed reading", new Integer(20), new Boolean(true)},
               {"Joe", "Brown",
                "Pool", new Integer(10), new Boolean(false)}
               };//same as before...
    		*/
    public MyTableModel(String[] column){
    	columnNames=column;
    	//v.add(new ArrayList<Object>());
    	//data.add(v1);data.add(v2);
    }
    
    public boolean addData(Object[] obj){
    	data.add(obj);
    	fireTableDataChanged();
    	return true;
    }
    
    public void resetData(ArrayList<Object[]> d){
    	data=d;
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
		// TODO Auto-generated method stub
		addData((Object[])arg1);
	}
}