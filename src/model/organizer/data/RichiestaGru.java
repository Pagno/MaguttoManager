package model.organizer.data;


/**
 * The Class RichiestaGru.
 */
public class RichiestaGru extends RichiestaMacchina {
	
	/** The min lunghezza. */
	private int minLunghezza;
	
	/** The max lunghezza. */
	private int maxLunghezza;
	
	/** The min altezza. */
	private int minAltezza;
	
	/** The max altezza. */
	private int maxAltezza;
	
	/** The min portata. */
	private int minPortata;
	
	/** The max portata. */
	private int maxPortata;
	
	/** The min angolo rotazione. */
	private int minAngoloRotazione;
	
	/** The max angolo rotazione. */
	private int maxAngoloRotazione;
	
	/**
	 * Crea una nuova richiesta per le Gru.
	 *
	 * @param minLunghezza the min lunghezza
	 * @param maxLunghezza the max lunghezza
	 * @param minAltezza the min altezza
	 * @param maxAltezza the max altezza
	 * @param minPortata the min portata
	 * @param maxPortata the max portata
	 * @param minAngoloRotazione the min angolo rotazione
	 * @param maxAngoloRotazione the max angolo rotazione
	 */
	public RichiestaGru(int minLunghezza, int maxLunghezza, int minAltezza,
			int maxAltezza, int minPortata, int maxPortata,
			int minAngoloRotazione, int maxAngoloRotazione) {
		super();
		this.minLunghezza = minLunghezza;
		this.maxLunghezza = maxLunghezza;
		this.minAltezza = minAltezza;
		this.maxAltezza = maxAltezza;
		this.minPortata = minPortata;
		this.maxPortata = maxPortata;
		this.minAngoloRotazione = minAngoloRotazione;
		this.maxAngoloRotazione = maxAngoloRotazione;
	}

	/**
	 * Ritorna minima lunghezza.
	 *
	 * @return minima lunghezza
	 */
	public int getMinLunghezza() {
		return minLunghezza;
	}

	/**
	 * Assegna minima lunghezza.
	 *
	 * @param minLunghezza minima lunghezza
	 */
	public void setMinLunghezza(int minLunghezza) {
		this.minLunghezza = minLunghezza;
	}

	/**
	 * Ritorna massima lunghezza.
	 *
	 * @return massima lunghezza
	 */
	public int getMaxLunghezza() {
		return maxLunghezza;
	}

	/**
	 * Assegna massima lunghezza.
	 *
	 * @param maxLunghezza massima lunghezza
	 */
	public void setMaxLunghezza(int maxLunghezza) {
		this.maxLunghezza = maxLunghezza;
	}

	/**
	 * Ritorna minima altezza.
	 *
	 * @return minima altezza
	 */
	public int getMinAltezza() {
		return minAltezza;
	}

	/**
	 * Assegna minima altezza.
	 *
	 * @param minPortata minima altezza
	 */
	public void setMinAltezza(int minAltezza) {
		this.minAltezza = minAltezza;
	}

	/**
	 * Ritorna massima altezza.
	 *
	 * @return massima altezza
	 */
	public int getMaxAltezza() {
		return maxAltezza;
	}

	/**
	 * Assegna massima altezza.
	 *
	 * @param maxPortata massima altezza
	 */
	public void setMaxAltezza(int maxAltezza) {
		this.maxAltezza = maxAltezza;
	}

	/**
	 * Ritorna minima portata.
	 *
	 * @return minima portata
	 */
	public int getMinPortata() {
		return minPortata;
	}

	/**
	 * Assegna minima portata.
	 *
	 * @param minPortata minima portata
	 */
	public void setMinPortata(int minPortata) {
		this.minPortata = minPortata;
	}

	/**
	 * Ritorna massima portata.
	 *
	 * @return massima portata
	 */
	public int getMaxPortata() {
		return maxPortata;
	}

	/**
	 * Assegna massima portata.
	 *
	 * @param maxPortata massima portata
	 */
	public void setMaxPortata(int maxPortata) {
		this.maxPortata = maxPortata;
	}

	/**
	 * Ritorna minimo angolo di rotazione.
	 *
	 * @return minimo angolo di rotazione
	 */
	public int getMinAngoloRotazione() {
		return minAngoloRotazione;
	}

	/**
	 * Assegna minimo angolo di rotazione.
	 *
	 * @param minAngoloRotazione minimo angolo di rotazione
	 */
	public void setMinAngoloRotazione(int minAngoloRotazione) {
		this.minAngoloRotazione = minAngoloRotazione;
	}

	/**
	 * Ritorna massimo angolo di rotazione.
	 *
	 * @return massimo angolo di rotazione
	 */
	public int getMaxAngoloRotazione() {
		return maxAngoloRotazione;
	}

	/**
	 * Assegna massimo angolo di rotazione.
	 *
	 * @param maxAngoloRotazione massimo angolo di rotazione
	 */
	public void setMaxAngoloRotazione(int maxAngoloRotazione) {
		this.maxAngoloRotazione = maxAngoloRotazione;
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
		if (!(obj instanceof RichiestaGru))
			return false;
		RichiestaGru other = (RichiestaGru) obj;
		if (maxAltezza != other.maxAltezza)
			return false;
		if (maxAngoloRotazione != other.maxAngoloRotazione)
			return false;
		if (maxLunghezza != other.maxLunghezza)
			return false;
		if (maxPortata != other.maxPortata)
			return false;
		if (minAltezza != other.minAltezza)
			return false;
		if (minAngoloRotazione != other.minAngoloRotazione)
			return false;
		if (minLunghezza != other.minLunghezza)
			return false;
		if (minPortata != other.minPortata)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String value="Richiesta:Gru ";
		if(minLunghezza==-1){
			value=value+"*-";
		}
		else{
			value=value+ minLunghezza + "-";
		}
		if(maxLunghezza==-1){
			value=value+"* ";
		}
		else{
			value=value+ maxLunghezza + " ";
		}
		if(minAltezza==-1){
			value=value+"*-";
		}
		else{
			value=value+ minAltezza + "-";
		}
		if(maxAltezza==-1){
			value=value+"* ";
		}
		else{
			value=value+ maxAltezza + " ";
		}
		if(minPortata==-1){
			value=value+"*-";
		}
		else{
			value=value+ minPortata + "-";
		}
		if(maxPortata==-1){
			value=value+"* ";
		}
		else{
			value=value+ maxPortata + " ";
		}
		if(minAngoloRotazione==-1){
			value=value+"*-";
		}
		else{
			value=value+ minAngoloRotazione + "-";
		}
		if(maxAngoloRotazione==-1){
			value=value+"*";
		}
		else{
			value=value+ maxAngoloRotazione;
		}
		return  value ;
	}

	/* (non-Javadoc)
	 * @see model.organizer.data.RichiestaMacchina#rispettaRichiesta(model.organizer.data.Macchina)
	 */
	@Override
	public boolean rispettaRichiesta(Macchina m) {
		if (m == null)
			return false;
		if (Gru.class != m.getClass())
			return false;
		Gru gru = (Gru) m;

		boolean soddisfa=true;
		soddisfa=soddisfa && ((minLunghezza==-1)?soddisfa : gru.getLunghezza() >= minLunghezza);
		soddisfa=soddisfa && ((maxLunghezza==-1)?soddisfa : gru.getLunghezza() <= maxLunghezza);
		soddisfa=soddisfa && ((minPortata==-1)?soddisfa : gru.getPortataMassima() >= minPortata);
		soddisfa=soddisfa && ((maxPortata==-1)?soddisfa : gru.getPortataMassima() <= maxPortata);
		soddisfa=soddisfa && ((minAltezza==-1)?soddisfa : gru.getAltezza() >= minAltezza);
		soddisfa=soddisfa && ((maxAltezza==-1)?soddisfa : gru.getAltezza() <= maxAltezza);
		soddisfa=soddisfa && ((minAngoloRotazione==-1)?soddisfa : gru.getAngoloRotazione() >= minAngoloRotazione);
		soddisfa=soddisfa && ((maxAngoloRotazione==-1)?soddisfa : gru.getAngoloRotazione() <= maxAngoloRotazione);
		return soddisfa;
	}
	
	@Override
	public boolean inConflitto(RichiestaMacchina other){
		if(other instanceof RichiestaGru){
			RichiestaGru ro;
			ro=(RichiestaGru)other;
			
			boolean conflitto=gestisciLimiti(this.getMinLunghezza(),this.getMaxLunghezza(),ro.getMinLunghezza(),ro.getMaxLunghezza());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinPortata(),this.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinAngoloRotazione(),this.getMaxAngoloRotazione(),ro.getMinAngoloRotazione(),ro.getMaxAngoloRotazione());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinAltezza(),this.getMaxAltezza(),ro.getMinAltezza(),ro.getMaxAltezza());
			if(!conflitto){
				return false;
			}
			return true;
		}
		return false;
	}
	
	
}
