package model.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Camion;


// 
/**
 *   Class ModelCamion.
 */
public class ModelCamion extends ModelMacchina{
	
	/**   lista camion. */
	private ArrayList<Camion> listaCamion;
	
	/**   istanza. */
	private static ModelCamion istanza;

	/**
	 * Instantiates a new model camion.
	 */
	private ModelCamion(){
		listaCamion=new ArrayList<Camion>();
	}
	
	/**
	 * Gets   model camion.
	 *
	 * @return   model camion
	 */
	public static synchronized ModelCamion getModelCamion(){
		if(istanza==null){
			istanza=new ModelCamion();
		}
		return istanza;
	}

	/**
	 * Aggiungi camion.
	 *
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 */
	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza){
		incrementaCodice();
		Camion cm= new Camion(getCodice(),produttore,Modello,capacita,portata,lunghezza);
		listaCamion.add(cm);
		
		Object[] v1={cm.getCodice(),produttore,Modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Carica camion.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 */
	public void caricaCamion(int codice, String produttore,String Modello,int capacita,int portata,int lunghezza){
		aggiornaCodice(codice);
		Camion cm= new Camion(codice,produttore,Modello,capacita,portata,lunghezza);
		listaCamion.add(cm);

		Object[] v1={cm.getCodice(),produttore,Modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Modifica camion.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 */
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(Modello);
				item.setCapacitaMassima(capacita);
				item.setPortataMassima( portata);
				item.setLunghezza(lunghezza);
			}
		}
		Object[] v1={codice,produttore,Modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina camion.
	 *
	 * @param codice   codice
	 * @return true, if successful
	 */
	public boolean eliminaCamion(int codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				return listaCamion.remove(item);
			}
		}
		return false;		
	}

	/**
	 * Gets   lista.
	 *
	 * @return   lista
	 */
	public ArrayList<Camion> getLista(){
		return listaCamion;
	}

	/**
	 * Checks if is camion.
	 *
	 * @param codice   codice
	 * @return true, if is camion
	 */
	public boolean isCamion(Integer codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Gets   camion.
	 *
	 * @param codice   codice
	 * @return   camion
	 */
	public Camion getCamion(Integer codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Camion item:listaCamion){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
	//Metodi realizzati appositamente per il testing della classe.
	

	/**
	 * Reset for test.
	 */
	static void resetForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
	
	
}