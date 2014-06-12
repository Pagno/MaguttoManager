package model;

import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * The Class ModelEscavatore.
 */
class ModelEscavatore extends ModelMacchina{
	
	/** The lista escavatori. */
	private ArrayList<Escavatore> listaEscavatori;
	
	/** The istanza. */
	private static ModelEscavatore istanza;
	
	/**
	 * Instantiates a new model escavatore.
	 */
	private ModelEscavatore(){
		listaEscavatori=new ArrayList<Escavatore>();
	}
	
	/**
	 * Gets the model escavatore.
	 *
	 * @return the model escavatore
	 */
	public static synchronized ModelEscavatore getModelEscavatore(){
		if(istanza==null){
			istanza=new ModelEscavatore();
		}
		return istanza;
	}

	/**
	 * Aggiungi escavatore.
	 *
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 * @param profondita the profondita
	 */
	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		incrementaCodice();
		Escavatore escavatore= new Escavatore(getCodice(),produttore, Modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);

		Object[] v1={escavatore.getCodice(),produttore,Modello,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Carica escavatore.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 * @param profondita the profondita
	 */
	void caricaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		aggiornaCodice(codice);
		Escavatore escavatore= new Escavatore(codice,produttore, Modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);

		Object[] v1={escavatore.getCodice(),produttore,Modello,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Modifica escavatore.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 * @param profondita the profondita
	 */
	public void modificaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita) {
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(Modello);
				item.setCapacitaMassima(capacita);
				item.setPortataMassima( portata);
				item.setProfonditaMassima(profondita);
				item.setAltezzaMassima(altezza);
			}
		}
		Object[] v1={codice,produttore,Modello,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina escavatore.
	 *
	 * @param codice the codice
	 * @return true, if successful
	 */
	public boolean eliminaEscavatore(int codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				return listaEscavatori.remove(item);
			}
		}
		return false;		
	}

	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public ArrayList<Escavatore> getLista(){
		return listaEscavatori;
	}

	/**
	 * Checks if is escavatore.
	 *
	 * @param codice the codice
	 * @return true, if is escavatore
	 */
	public boolean isEscavatore(Integer codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Gets the escavatore.
	 *
	 * @param codice the codice
	 * @return the escavatore
	 */
	public Escavatore getEscavatore(Integer codice){
		for(Escavatore item:listaEscavatori){
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
		for(Escavatore item:listaEscavatori){
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
