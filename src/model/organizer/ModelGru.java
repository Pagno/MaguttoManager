package model.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Camion;
import model.organizer.data.Gru;


// 
/**
 *   Class ModelGru.
 */
public class ModelGru extends ModelMacchina{
	
	/**   lista gru. */
	private ArrayList<Gru> listaGru;
	
	/**   istanza. */
	private static ModelGru istanza;

	/**
	 * Instantiates a new model gru.
	 */
	private ModelGru(){
		listaGru=new ArrayList<Gru>();
	}

	/**
	 * Gets   model gru.
	 *
	 * @return   model gru
	 */
	public static synchronized ModelGru getModelGru(){
		if(istanza==null){
			istanza=new ModelGru();
		}
		return istanza;
	}

	/**
	 * Aggiungi gru.
	 *
	 * @param produttore   produttore
	 * @param modello   modello
	 * @param rotazione   rotazione
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 * @param altezza   altezza
	 */
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		incrementaCodice();
		Gru gru=new Gru(getCodice(),produttore,modello, rotazione, portata,lunghezza,altezza);
		listaGru.add(gru);
		

		Object[] v1={gru.getCodice(),produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Carica gru.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param modello   modello
	 * @param rotazione   rotazione
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 * @param altezza   altezza
	 */
	public void caricaGru(int codice, String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		aggiornaCodice(codice);
		Gru gru=new Gru(codice,produttore,modello, rotazione, portata,lunghezza,altezza);
		listaGru.add(gru);
		

		Object[] v1={gru.getCodice(),produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Modifica gru.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param modello   modello
	 * @param rotazione   rotazione
	 * @param portata   portata
	 * @param lunghezza   lunghezza
	 * @param altezza   altezza
	 */
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(modello);
				item.setLunghezzaMassima(lunghezza);
				item.setPortataMassima( portata);
				item.setAltezza( altezza);
				item.setAngoloRotazione(rotazione);
			}
		}
		Object[] v1={codice,produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina gru.
	 *
	 * @param codice   codice
	 * @return true, if successful
	 */
	public boolean eliminaGru(int codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				item.liberaRichieste();
				return listaGru.remove(item);
			}
		}
		return false;		
	}
	
	/**
	 * Gets   lista.
	 *
	 * @return   lista
	 */
	public ArrayList<Gru> getLista(){
		return listaGru;
	}

	/**
	 * Checks if is gru.
	 *
	 * @param codice   codice
	 * @return true, if is gru
	 */
	public boolean isGru(Integer codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Gets   gru.
	 *
	 * @param codice   codice
	 * @return   gru
	 */
	public Gru getGru(Integer codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}
	
	public ArrayList<Gru> getDisponibili(GregorianCalendar dataInizio,GregorianCalendar dataFine){
		ArrayList<Gru> listaLibera=new ArrayList<Gru>();
		
		for(Gru gru:listaGru){
			if(gru.isFree(dataInizio, dataFine))
				listaLibera.add(gru);
		}
		return listaLibera;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Gru item:listaGru){
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
