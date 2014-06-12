package model;

import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * The Class ModelGru.
 */
class ModelGru extends ModelMacchina{
	
	/** The lista gru. */
	private ArrayList<Gru> listaGru;
	
	/** The istanza. */
	private static ModelGru istanza;

	/**
	 * Instantiates a new model gru.
	 */
	private ModelGru(){
		listaGru=new ArrayList<Gru>();
	}

	/**
	 * Gets the model gru.
	 *
	 * @return the model gru
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
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param rotazione the rotazione
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 * @param altezza the altezza
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
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param rotazione the rotazione
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 * @param altezza the altezza
	 */
	void caricaGru(int codice, String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
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
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param rotazione the rotazione
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 * @param altezza the altezza
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
	 * @param codice the codice
	 * @return true, if successful
	 */
	public boolean eliminaGru(int codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return listaGru.remove(item);
			}
		}
		return false;		
	}
	
	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public ArrayList<Gru> getLista(){
		return listaGru;
	}

	/**
	 * Checks if is gru.
	 *
	 * @param codice the codice
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
	 * Gets the gru.
	 *
	 * @param codice the codice
	 * @return the gru
	 */
	public Gru getGru(Integer codice){
		for(Gru item:listaGru){
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
			istanza=null;
		}
	}
}
