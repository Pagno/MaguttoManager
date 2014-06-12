package model;

import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelMacchina.
 */
public abstract class ModelMacchina extends Observable{
	
	/** The codice. */
	private static int codice;
	
	/**
	 * Aggiorna codice.
	 *
	 * @param code the code
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
	 * Inits the codice.
	 */
	protected static void initCodice(){
		codice=0;
	}
	
	/**
	 * Gets the codice.
	 *
	 * @return the codice
	 */
	protected int getCodice(){
		return codice;
	}

	//Metodi realizzati appositamente per il testing della classe.
	
	/**
	 * Gets the next codice.
	 *
	 * @return the next codice
	 */
	int getNextCodice(){
		return getCodice()+1;
	}
}