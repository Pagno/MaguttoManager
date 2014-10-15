package model.organizer;

import java.util.Observable;

// 
/**
 *   Class ModelMacchina.
 */
public abstract class ModelMacchina extends Observable{
	
	/**   codice. */
	private static int codice;
	
	/**
	 * Aggiorna codice.
	 *
	 * @param code   code
	 */
	protected void aggiornaCodice(int code){
		if(codice<code){
			codice=code;
		}
	}
	
	/**
	 * Incrementa codice.
	 */
	protected void incrementaCodice(){
		codice++;
	}
	
	/**
	 * Inits   codice.
	 */
	public static void initCodice(){
		codice=0;
	}
	
	/**
	 * Gets   codice.
	 *
	 * @return   codice
	 */
	protected int getCodice(){
		return codice;
	}

	//Metodi realizzati appositamente per il testing della classe.
	
	/**
	 * Gets   next codice.
	 *
	 * @return   next codice
	 */
	int getProssimoCodice(){
		return getCodice()+1;
	}
}