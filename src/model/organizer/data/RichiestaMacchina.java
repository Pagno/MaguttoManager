package model.organizer.data;

/**
 * The Class RichiestaMacchina.
 */
public abstract class RichiestaMacchina{
	
	/**
	 * Instantiates a new richiesta macchina.
	 */
	public RichiestaMacchina() {
		super();
	}
	
	/**
	 * True se la macchina rispetta la richiesta.
	 *
	 * @param m La macchina
	 * @return true, if successful
	 */
	public abstract boolean rispettaRichiesta(Macchina m);

}
