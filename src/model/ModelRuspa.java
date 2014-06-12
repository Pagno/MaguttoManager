package model;

import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * The Class ModelRuspa.
 */
class ModelRuspa extends ModelMacchina{
	
	/** The lista ruspe. */
	private ArrayList<Ruspa> listaRuspe;
	
	/** The istanza. */
	private static ModelRuspa istanza;

	/**
	 * Instantiates a new model ruspa.
	 */
	private ModelRuspa(){
		listaRuspe=new ArrayList<Ruspa>();
	}
	
	/**
	 * Gets the model ruspa.
	 *
	 * @return the model ruspa
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
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
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
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 */
	void caricaRuspa(int codice,String produttore, String modello,int capacita,int portata,int altezza){
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
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
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
	 * @param codice the codice
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
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public ArrayList<Ruspa> getLista(){
		return listaRuspe;
	}

	/**
	 * Checks if is ruspa.
	 *
	 * @param codice the codice
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
	 * Gets the ruspa.
	 *
	 * @param codice the codice
	 * @return the ruspa
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
			istanza=null;
		}
	}
}
