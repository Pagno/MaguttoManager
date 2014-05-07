package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel { 
 
	
	
	private String[] columnNames={"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
	private Object[] v1={"Kathy", "Smith","Snowboarding", new Integer(5), new Boolean(false)};   
	private Object[] v2={"John", "Doe","Rowing", new Integer(3), new Boolean(true)};    	
	private ArrayList<Object[]> data=new ArrayList<Object[]>();
			//new ArrayList<Object>("Kathy","Smith","Snowboarding", new Integer(5),new Boolean(false))};
    private Object[][] data1={
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
    		
    public MyTableModel(){
    	//v.add(new ArrayList<Object>());
    	data.add(v1);data.add(v2);
    }
    		
    public void addData(Object[] obj){
    	data.add(obj);
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
		// TODO Auto-generated method stub
		return data.get(arg0)[arg1];
	}
}