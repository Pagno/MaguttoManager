package model.organizer;

import java.util.Observable;

/**
 *   La classe astratta ModelMacchina indica i metodi che devono esser condivisi dalle sue specializzazioni.
 *   <p>
 *   In particolare contiene i metodi per la gestione dei codici, che è condivisa fra tutti i tipi di macchina.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public abstract class ModelMacchina extends Observable{
	
	/**   Ultimo codice utilizzato da una macchina. */
	private static int codice;
	
	/**
	 * Aggiorna l'ultimo codice assegnato ad una macchina.<p>
	 * Caricando le macchine da database, potrebbe essere necessario aggiornare tale codice con un meccanismo diverso da quello
	 * usato nel normale inserimento delle macchine
	 *
	 * @param code Il codice caricato 
	 */
	protected void aggiornaCodice(int code){
		if(codice<code){
			codice=code;
		}
	}
	
	/**
	 * Incrementa l'ultimo codice utilizzato.
	 */
	protected void incrementaCodice(){
		codice++;
	}
	
	/**
	 * Resetta il codice, settandolo a 0.
	 */
	public static void initCodice(){
		codice=0;
	}
	
	/**
	 * Restituisce l'ultimo codice utilizzato.
	 *
	 * @return L'ultimo codice utilizzato
	 */
	protected int getCodice(){
		return codice;
	}

	//Metodi realizzati appositamente per il testing della classe.
	
	/**
	 * Restituisce il prossimo codice da assegnare a una macchina.
	 *
	 * @return Il prossimo codice da assegnare
	 */
	int getProssimoCodice(){
		return getCodice()+1;
	}
}