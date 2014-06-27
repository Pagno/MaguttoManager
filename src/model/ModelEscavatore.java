package model;

import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 *   Class ModelEscavatore.
 */
class ModelEscavatore extends ModelMacchina{
	
	/**   lista escavatori. */
	private ArrayList<Escavatore> listaEscavatori;
	
	/**   istanza. */
	private static ModelEscavatore istanza;
	
	/**
	 * Instantiates a new model escavatore.
	 */
	private ModelEscavatore(){
		listaEscavatori=new ArrayList<Escavatore>();
	}
	
	/**
	 * Gets   model escavatore.
	 *
	 * @return   model escavatore
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
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 * @param profondita   profondita
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
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 * @param profondita   profondita
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
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param Modello   modello
	 * @param capacita   capacita
	 * @param portata   portata
	 * @param altezza   altezza
	 * @param profondita   profondita
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
	 * @param codice   codice
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
	 * Gets   lista.
	 *
	 * @return   lista
	 */
	public ArrayList<Escavatore> getLista(){
		return listaEscavatori;
	}

	/**
	 * Checks if is escavatore.
	 *
	 * @param codice   codice
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
	 * Gets   escavatore.
	 *
	 * @param codice   codice
	 * @return   escavatore
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
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}
