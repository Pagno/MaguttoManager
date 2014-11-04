package controller.greedy.data;

import java.util.GregorianCalendar;

import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;

/**
 *   La classe Associazione permette di memorizzare l'assoziazione tra macchina e richieste generata da GreedyEngine
 *   prima che l'utente abbia deciso di confermarla.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Associazione {
	
	/** Richiesta. */
	private Richiesta ric;
	
	/** Macchina. */
	private Macchina mac;
	
	/** Stato dell'associazione. */
	private boolean confermata;
	
	/**
	 * Istanzia una nuova associazione.
	 *
	 * @param r La richiesta
	 * @param m La macchina
	 */
	public Associazione(Richiesta r, Macchina m){
		ric=r;
		mac=m;
		confermata=false;
	}

	/**
	 * Restituisce la richiesta.
	 *
	 * @return La richiesta
	 */
	public Richiesta getRichiesta() {
		return ric;
	}

	/**
	 * Modifica la richiesta.
	 *
	 * @param ric La nuova richiesta
	 */
	public void setRichiesta(Richiesta ric) {
		this.ric = ric;
	}

	/**
	 * Restituisce la macchina.
	 *
	 * @return La macchina
	 */
	public Macchina getMacchina() {
		return mac;
	}

	/**
	 * Modifica la macchina.
	 *
	 * @param mac La nuova macchina
	 */
	public void setMacchina(Macchina mac) {
		this.mac = mac;
	}
	
	/**
	 * Modifica lo stato dell'associazione, true se l'associazione è confermata.
	 *
	 * @param val Lo stato dell'associazione
	 */
	public void setConfermata(boolean val){
		confermata=val;
	}
	
	/**
	 * Restituisce lo stato dell'associazione.
	 *
	 * @return Lo stato dell'associazione
	 */
	public boolean getConfermata(){
		return confermata;
	}
	
	/**
	 * Concretizza l'associazione, utilizzando la macchina per soddisfare la richiesta.
	 */
	public void concretizza(){
		if(ric.rispettaRichiesta(mac)){
			ric.setMacchina(mac);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String result="Cantiere:"+ric.getLavoro().getCantiere().getNomeCantiere()
				+" Lavoro:"+ric.getLavoro().getNome()+ " -->  Macchina: "+mac.getProduttore()+" - "+mac.getModello();
		return result;
	}
	
	/**
	 * Restituisce la data d'inizio del lavoro cui appartiene la richiesta.
	 *
	 * @return La data d'inizio
	 */
	public GregorianCalendar getDataInizio(){
		return ric.getDataInizio();
	}
	
	/**
	 * Restituisce la data di fine del lavoro cui appartiene la richiesta.
	 *
	 * @return La data di fine
	 */
	public GregorianCalendar getDataFine(){
		return ric.getDataFine();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associazione other = (Associazione) obj;
		if (confermata != other.confermata)
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (ric == null) {
			if (other.ric != null)
				return false;
		} else if (!ric.equals(other.ric))
			return false;
		return true;
	}
	
	/**
	 * Verifica se la richiesta inserita &egrave; cronologicamente sovrapposta a quella memorizzata nell'associazione.
	 *
	 * @param other L'altra richiesta
	 * @return true, se le due richieste sono sovrapposte cronologicamente
	 */
	public boolean collide(Richiesta other){
		return this.ric.collide(other);
	}
	
	/*public boolean collide(Associazione other){
		return this.ric.collide(other.getRichiesta());
	}*/
	
}
