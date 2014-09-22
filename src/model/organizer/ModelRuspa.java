package model.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Camion;
import model.organizer.data.Ruspa;


// 
/**
 *   Class ModelRuspa.
 */
public class ModelRuspa extends ModelMacchina{
	
	/**   lista ruspe. */
	private ArrayList<Ruspa> listaRuspe;
	
	/**   istanza. */
	private static ModelRuspa istanza;

	/**
	 * Instantiates a new model ruspa.
	 */
	private ModelRuspa(){
		listaRuspe=new ArrayList<Ruspa>();
	}
	
	/**
	 * Gets   model ruspa.
	 *
	 * @return   model ruspa
	 */
	public static synchronized ModelRuspa getModelRuspa(){
		if(istanza==null){
			istanza=new ModelRuspa();
		}
		return istanza;
	}


	/**
	 * Aggiungi ruspa.
	 *
	 * @param produttore   produttore
	 * @param modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 */
	public void aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza){
		incrementaCodice();
		Ruspa ruspa= new Ruspa(getCodice(), produttore, modello,capacita,portata,altezza);
		listaRuspe.add(ruspa);

		Object[] v1={ruspa.getCodice(),produttore,modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Carica ruspa.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 */
	public void caricaRuspa(int codice,String produttore, String modello,int capacita,int portata,int altezza){
		aggiornaCodice(codice);
		Ruspa ruspa= new Ruspa(codice, produttore, modello,capacita,portata,altezza);
		listaRuspe.add(ruspa);
		
		Object[] v1={ruspa.getCodice(),produttore,modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Modifica ruspa.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 */
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
		    	item.setModello(Modello);
		    	item.setCapacitaMassima(capacita);
		    	item.setPortataMassima(portata);
		    	item.setAltezzaMassima(altezza);
			}
		}

		Object[] v1={codice,produttore,Modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina ruspa.
	 *
	 * @param codice   codice
	 * @return true, if successful
	 */
	public boolean eliminaRuspa(int codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				return listaRuspe.remove(item);
			}	
		}
		return false;		
	}

	/**
	 * Gets   lista.
	 *
	 * @return   lista
	 */
	public ArrayList<Ruspa> getLista(){
		return listaRuspe;
	}

	/**
	 * Checks if is ruspa.
	 *
	 * @param codice   codice
	 * @return true, if is ruspa
	 */
	public boolean isRuspa(Integer codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				return true;
			}	
		}
		return false;	
	}

	/**
	 * Gets   ruspa.
	 *
	 * @param codice   codice
	 * @return   ruspa
	 */
	public Ruspa getRuspa(Integer codice){
		for(Ruspa item:listaRuspe){
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
		for(Ruspa item:listaRuspe){
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
