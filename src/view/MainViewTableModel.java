package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

/**
 *   Classe dedicata alla visualizzazione dei dati nella tabella nella view Principale
 *   @see MainView
 */
public class MainViewTableModel extends AbstractTableModel implements Observer{ 
 	
	/**   Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**   Nomi delle colonne. */
	private String[] columnNames;
	
	/**   Dati da visualizzare nella tabella */
	private ArrayList<Object[]> data=new ArrayList<Object[]>();
			
    /**
     * Inizializzo la classe con il nome delle colonne della tabella
     *
     * @param column Array di stringhe contenente il nome delle colonne
     */
    public MainViewTableModel(String[] column){
    	columnNames=column;
    }
    
    /**
     * Aggiungi nuovi dati alla tabella
     *
     * @param obj dati da inserire nella tabella
     * @return true se correttamente inseriti
     */
    public boolean addData(Object[] obj){
    	data.add(obj);
    	fireTableDataChanged();
    	return true;
    }
    
    /**
     * Rimuove la i-esima riga dalla tabella 
     *
     * @param i indice della riga da rimuovere
     * @return true se correttamente rimossi.
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
     * Rimuove i dati i dalla tabella
     *
     * @param data dati da rimuovere dalla tabella.
     * @return true se correttamente rimossi.
     */
    public boolean removeData(Object[] data){
    	if(this.data.remove(data)){
    		fireTableDataChanged();
    		return true;
    	}
    	return false;
    }
    
    /**
     * Assegna i nuovi dati da visualizzare nella tabella, i precedenti verranno persi.
     *
     * @param d dati da visualizzare.
     */
    public void resetData(ArrayList<Object[]> d){
    	data=d;
    }
    
    /**
     * Elimino tutti i dati dalla tabella.
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
		// 
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// 
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
	 * rimuovi a dati dalla tabella
	 *
	 * @param row indice della riga da rimuovere
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
	 * Ritorna i dati salvati nella i-esima riga della tabella
	 *
	 * @param row indice della riga 
	 * @return dati della riga i-esima
	 */
	public Object[] getRowData(int row){
		return data.get(row);
	}
}