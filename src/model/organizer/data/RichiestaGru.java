package model.organizer.data;


/**
 *   Questa classe rappresenta le caratteristiche richieste nel caso di richiesta di gru. 
 *   <p>
 *   Definisce quindi gli intervalli di valori entro cui devono rientrare le caratteristiche della gru, fornendo poi i metodi 
 *   necessari a controllare che le caratteristiche siano soddisfatte.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class RichiestaGru extends RichiestaMacchina {
	
	/** Lunghezza minima. */
	private int minLunghezza;
	
	/** Lunghezza massima. */
	private int maxLunghezza;
	
	/** Altezza minima. */
	private int minAltezza;
	
	/** Altezza massima. */
	private int maxAltezza;
	
	/** Portata minima. */
	private int minPortata;
	
	/** Portata massima. */
	private int maxPortata;
	
	/** Angolo di rotazione minimo. */
	private int minAngoloRotazione;
	
	/** Angolo di rotazione massimo. */
	private int maxAngoloRotazione;
	
	/**
	 * Crea un nuovo set di requisiti per una richiesta di gru.
	 *
	 * @param minLunghezza La lunghezza minima
	 * @param maxLunghezza La lunghezza
	 * @param minAltezza L'altezza minima
	 * @param maxAltezza L'altezza massima
	 * @param minPortata La portata minima
	 * @param maxPortata La portata massima
	 * @param minAngoloRotazione L'angolo di rotazione minima
	 * @param maxAngoloRotazione L'angolo di rotazione massima
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
	 * Restituisce la lunghezza minima.
	 *
	 * @return La lunghezza minima
	 */
	public int getMinLunghezza() {
		return minLunghezza;
	}

	/**
	 * Modifica la lunghezza minima.
	 *
	 * @param minLunghezza La nuova lunghezza minima
	 */
	public void setMinLunghezza(int minLunghezza) {
		this.minLunghezza = minLunghezza;
	}

	/**
	 * Restituisce la lunghezza massima.
	 *
	 * @return La lunghezza massima
	 */
	public int getMaxLunghezza() {
		return maxLunghezza;
	}

	/**
	 * Modifica la lunghezza massima.
	 *
	 * @param maxLunghezza La lunghezza massima
	 */
	public void setMaxLunghezza(int maxLunghezza) {
		this.maxLunghezza = maxLunghezza;
	}

	/**
	 * Restituisce l'altezza minima.
	 *
	 * @return L'altezza minima
	 */
	public int getMinAltezza() {
		return minAltezza;
	}

	/**
	 * Modifica l'altezza minima.
	 *
	 * @param minAltezza La nuova altezza minima
	 */
	public void setMinAltezza(int minAltezza) {
		this.minAltezza = minAltezza;
	}

	/**
	 * Restituisce l'altezza massima.
	 *
	 * @return L'altezza massima
	 */
	public int getMaxAltezza() {
		return maxAltezza;
	}

	/**
	 * Modifica l'altezza massima
	 *
	 * @param maxAltezza L'altezza massima
	 */
	public void setMaxAltezza(int maxAltezza) {
		this.maxAltezza = maxAltezza;
	}

	/**
	 * Restituisce la portata minima.
	 *
	 * @return La portata minima
	 */
	public int getMinPortata() {
		return minPortata;
	}

	/**
	 * Modifica la portata minima.
	 *
	 * @param minPortata La nuova portata minima
	 */
	public void setMinPortata(int minPortata) {
		this.minPortata = minPortata;
	}

	/**
	 * Restituisce la portata massima.
	 *
	 * @return La portata massima
	 */
	public int getMaxPortata() {
		return maxPortata;
	}

	/**
	 * Modifica la portata massima.
	 *
	 * @param maxPortata La nuova portata massima
	 */
	public void setMaxPortata(int maxPortata) {
		this.maxPortata = maxPortata;
	}

	/**
	 * Restituisce l'angolo di rotazione minimo.
	 *
	 * @return L'angolo di rotazione minimo
	 */
	public int getMinAngoloRotazione() {
		return minAngoloRotazione;
	}

	/**
	 * Modifica l'angolo di rotazione minimo.
	 *
	 * @param minAngoloRotazione Il nuovo angolo di rotazione minimo
	 */
	public void setMinAngoloRotazione(int minAngoloRotazione) {
		this.minAngoloRotazione = minAngoloRotazione;
	}

	/**
	 * Restituisce l'angolo di rotazione massimo.
	 *
	 * @return L'angolo di rotazione massimo
	 */
	public int getMaxAngoloRotazione() {
		return maxAngoloRotazione;
	}

	/**
	 * Modifica l'angolo di rotazione massimo.
	 *
	 * @param maxAngoloRotazione Il nuovo angolo di rotazione massimo
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
	
	/* (non-Javadoc)
	 * @see model.organizer.data.RichiestaMacchina#inConflitto(model.organizer.data.RichiestaMacchina)
	 */
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
