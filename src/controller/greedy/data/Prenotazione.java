package controller.greedy.data;

import java.util.GregorianCalendar;

import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;

/**
 *   La classe Prenotazione permette di memorizzare una prenotazione, contenente un'associazione tra richiesta e macchina 
 *   e la durata del lavoro pi&ugrave; breve tra quelli che generano la prenotazione in esame.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Prenotazione {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Prenotazione [associazione=" + associazione + ", durataLavoro="
				+ durataLavoro + "]";
	}

	/** Associazione. */
	private Associazione associazione;
	
	/** Durata del lavoro. */
	private int durataLavoro;
	
	/**
	 * Istanzia una nuova prenotazione.
	 *
	 * @param associazione L'associazione
	 * @param durataLavoro La durata del lavoro
	 */
	public Prenotazione(Associazione associazione,Integer durataLavoro){
		this.associazione=associazione;
		this.durataLavoro=durataLavoro;
	}

	/**
	 * Restituisce l'associazione.
	 *
	 * @return L'associazione
	 */
	public Associazione getAssociazione() {
		return associazione;
	}

	/**
	 * Modifica l'associazione.
	 *
	 * @param associazione La nuova associazione
	 */
	public void setAssociazione(Associazione associazione) {
		this.associazione = associazione;
	}

	/**
	 * Restituisce la durata del lavoro.
	 *
	 * @return La durata del lavoro
	 */
	public int getDurataLavoro() {
		return durataLavoro;
	}

	/**
	 * Modifica la durata del lavoro.
	 *
	 * @param durataLavoro La nuova durata del lavoro
	 */
	public void setDurataLavoro(Integer durataLavoro) {
		this.durataLavoro = durataLavoro;
	}
	
	/**
	 * Seleziona la prenotazione.
	 *
	 * @return L'associazione
	 */
	public Associazione select() {
		associazione.setConfermata(true);
		return associazione;
	}
	
	/**
	 * Restituisce la richiesta.
	 *
	 * @return La richiesta
	 */
	public Richiesta getRichiesta() {
		return associazione.getRichiesta();
	}
	
	/**
	 * Restituisce la macchina.
	 *
	 * @return La macchina
	 */
	public Macchina getMacchina(){
		return associazione.getMacchina();
	}
	
	/**
	 * Restituisce la data d'inizio.
	 *
	 * @return La data d'inizio
	 */
	public GregorianCalendar getDataInizio(){
		return associazione.getDataInizio();
	}
	
	/**
	 * Restituisce la data di fine.
	 *
	 * @return La data di fine
	 */
	public GregorianCalendar getDataFine(){
		return associazione.getDataFine();
	}
}
