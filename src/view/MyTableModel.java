package view;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel { 
 
	
	
	ArrayList<String> ColName = {"Titolo", "Autore", "Quantità", "Prezzo", "Valore" };
	// ritorna il numero di colonne 
	public int getColumnCount() { return ColName.size(); } 
	
	public String getColumnName(int col) {
		return ColName.get(col); 
	} 

	// ritorna il numero di righe 
	public int getRowCount() { return 10;} 
	
	// ritorna il contenuto di una cella 
	public Object getValueAt(int row, int col) { 
		return new Integer((row+1)*(col+1));
	}
	

	// specifica se le celle sono editabili 
	public boolean isCellEditable(int row, int col) {
		 return false;
	}

	public void addData(){
		
	}
	public void resetData(ArrayList data,ArrayList colName){
		
	}
}