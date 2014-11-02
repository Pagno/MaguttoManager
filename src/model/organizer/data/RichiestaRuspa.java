package model.organizer.data;


/**
 *   Questa classe rappresenta le caratteristiche richieste nel caso di richiesta di ruspe. 
 *   <p>
 *   Definisce quindi gli intervalli di valori entro cui devono rientrare le caratteristiche dela rusoa, fornendo poi i metodi 
 *   necessari a controllare che le caratteristiche siano soddisfatte.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class RichiestaRuspa extends RichiestaMacchina {
	
	/** Capacit&agrave; minima. */
	private int minCapacita;
	
	/** Capacit&agrave; massima. */
	private int maxCapacita;
	
	/** Portata minima. */
	private int minPortata;
	
	/** Portata massima. */
	private int maxPortata;
	
	/** Altezza minima. */
	private int minAltezza;
	
	/** Altezza massima. */
	private int maxAltezza;

	/**
	 * Crea un nuovo set di requisiti per una richiesta di ruspe.
	 *
	 * @param minCapacita La capacit&agrave; minima
	 * @param maxCapacita La capacit&agrave; massima
	 * @param minPortata La portata minima
	 * @param maxPortata La portata massima
	 * @param minAltezza L'altezza minima
	 * @param maxAltezza L'altezza massima
	 */
	public RichiestaRuspa(int minCapacita, int maxCapacita, int minPortata,
			int maxPortata, int minAltezza, int maxAltezza) {
		super();
		this.minCapacita = minCapacita;
		this.maxCapacita = maxCapacita;
		this.minPortata = minPortata;
		this.maxPortata = maxPortata;
		this.minAltezza = minAltezza;
		this.maxAltezza = maxAltezza;
	}

	/**
	 * Restituisce la capacit&agrave; minima.
	 *
	 * @return La capacit&agrave; minima
	 */
	public int getMinCapacita() {
		return minCapacita;
	}

	/**
	 * Modifica la capacit&agrave; minima.
	 *
	 * @param minCapacita La nuova capacit&agrave; minima
	 */
	public void setMinCapacita(int minCapacita) {
		this.minCapacita = minCapacita;
	}

	/**
	 * Restituisce la capacit&agrave; massima.
	 *
	 * @return La capacit&agrave; massima
	 */
	public int getMaxCapacita() {
		return maxCapacita;
	}

	/**
	 * Modifica la capacit&agrave; massima.
	 *
	 * @param maxCapacita La nuova capacit&agrave; massima
	 */
	public void setMaxCapacita(int maxCapacita) {
		this.maxCapacita = maxCapacita;
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
	 * Modifica l'altezza massima.
	 *
	 * @param maxAltezza La nuova altezza massima
	 */
	public void setMaxAltezza(int maxAltezza) {
		this.maxAltezza = maxAltezza;
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
		if (!(obj instanceof RichiestaRuspa))
			return false;
		RichiestaRuspa other = (RichiestaRuspa) obj;
		if (maxAltezza != other.maxAltezza)
			return false;
		if (maxCapacita != other.maxCapacita)
			return false;
		if (maxPortata != other.maxPortata)
			return false;
		if (minAltezza != other.minAltezza)
			return false;
		if (minCapacita != other.minCapacita)
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
		
		String value="Richiesta:Ruspa ";
		if(minCapacita==-1){
			value=value+"*-";
		}
		else{
			value=value+ minCapacita + "-";
		}
		if(maxCapacita==-1){
			value=value+"* ";
		}
		else{
			value=value+ maxCapacita + " ";
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
		if(minAltezza==-1){
			value=value+"*-";
		}
		else{
			value=value+ minAltezza + "-";
		}
		if(maxAltezza==-1){
			value=value+"*";
		}
		else{
			value=value+ maxAltezza;
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
		if (Ruspa.class != m.getClass())
			return false;
		Ruspa ruspa = (Ruspa) m;

		boolean soddisfa=true;
		soddisfa=soddisfa && ((minCapacita==-1)?soddisfa:ruspa.getCapacitaMassima() >= minCapacita);
		soddisfa=soddisfa && ((maxCapacita==-1)?soddisfa:ruspa.getCapacitaMassima() <= maxCapacita);
		soddisfa=soddisfa && ((minPortata==-1)?soddisfa:ruspa.getPortataMassima() >= minPortata);
		soddisfa=soddisfa && ((maxPortata==-1)?soddisfa:ruspa.getPortataMassima() <= maxPortata);
		soddisfa=soddisfa && ((minAltezza==-1)?soddisfa:ruspa.getAltezzaMassima() >= minAltezza);
		soddisfa=soddisfa && ((maxAltezza==-1)?soddisfa:ruspa.getAltezzaMassima() <= maxAltezza);
		return soddisfa;
	}
	
	/* (non-Javadoc)
	 * @see model.organizer.data.RichiestaMacchina#inConflitto(model.organizer.data.RichiestaMacchina)
	 */
	@Override
	public boolean inConflitto(RichiestaMacchina other){
		if(other instanceof RichiestaRuspa){
			RichiestaRuspa ro;
			ro=(RichiestaRuspa)other;
			
			boolean conflitto=gestisciLimiti(this.getMinCapacita(),this.getMaxCapacita(),ro.getMinCapacita(),ro.getMaxCapacita());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinPortata(),this.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
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
