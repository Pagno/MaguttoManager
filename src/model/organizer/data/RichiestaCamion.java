package model.organizer.data;



/**
 * The Class RichiestaCamion.
 */
public class RichiestaCamion extends RichiestaMacchina {
	
	/** The min capacita. */
	public int minCapacita;
	
	/** The min portata. */
	public int minPortata;
	
	/** The min lunghezza. */
	public int minLunghezza;
	
	/** The max capacita. */
	public int maxCapacita;
	
	/** The max portata. */
	public int maxPortata;
	
	/** The max lunghezza. */
	public int maxLunghezza;
	
	/**
	 * Crea una nuova richiesta per i camion.
	 *
	 * @param minCapacita Minima capacita richiesta
	 * @param maxCapacita the Massima capacita richiesta
	 * @param minPortata the Minima portata richiesta
	 * @param maxPortata the Massima portata richiesta
	 * @param minLunghezza the Minima lunghezza richiesta
	 * @param maxLunghezza the Massima lunghezza richiesta
	 */
	public RichiestaCamion(int minCapacita, int maxCapacita, int minPortata, int maxPortata, int minLunghezza, int maxLunghezza) {
		super();
		this.minCapacita = minCapacita;
		this.minPortata = minPortata;
		this.minLunghezza = minLunghezza;
		this.maxCapacita = maxCapacita;
		this.maxPortata = maxPortata;
		this.maxLunghezza = maxLunghezza;
		
	}

	/**
	 * Ritorna minima capacita.
	 *
	 * @return minima capacita
	 */
	public int getMinCapacita() {
		return minCapacita;
	}

	/**
	 * Assegna minima capacita.
	 *
	 * @param minCapacita minima capacita
	 */
	public void setMinCapacita(int minCapacita) {
		this.minCapacita = minCapacita;
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
	 * Ritorna massima capacita.
	 *
	 * @return massima capacita
	 */
	public int getMaxCapacita() {
		return maxCapacita;
	}

	/**
	 * Assegna massima capacita.
	 *
	 * @param maxCapacita massima capacita
	 */
	public void setMaxCapacita(int maxCapacita) {
		this.maxCapacita = maxCapacita;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String value="Richiesta:Camion ";
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
		if(minLunghezza==-1){
			value=value+"*-";
		}
		else{
			value=value+ minLunghezza + "-";
		}
		if(maxLunghezza==-1){
			value=value+"*";
		}
		else{
			value=value+ maxLunghezza;
		}
		return  value ;
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
		if (!(obj instanceof RichiestaCamion))
			return false;
		RichiestaCamion other = (RichiestaCamion) obj;
		if (maxCapacita != other.maxCapacita)
			return false;
		if (maxLunghezza != other.maxLunghezza)
			return false;
		if (maxPortata != other.maxPortata)
			return false;
		if (minCapacita != other.minCapacita)
			return false;
		if (minLunghezza != other.minLunghezza)
			return false;
		if (minPortata != other.minPortata)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see model.organizer.data.RichiestaMacchina#rispettaRichiesta(model.organizer.data.Macchina)
	 */
	@Override
	public boolean rispettaRichiesta(Macchina m) {
		if (m == null)
			return false;
		if (Camion.class != m.getClass())
			return false;
		Camion other = (Camion) m;


		boolean soddisfa=true;
		soddisfa=soddisfa && ((minCapacita==-1)?soddisfa : other.getCapacitaMassima() >= minCapacita);
		soddisfa=soddisfa && ((maxCapacita==-1)?soddisfa : other.getCapacitaMassima() <= maxCapacita);
		soddisfa=soddisfa && ((minPortata==-1)?soddisfa : other.getPortataMassima() >= minPortata);
		soddisfa=soddisfa && ((maxPortata==-1)?soddisfa : other.getPortataMassima() <= maxPortata);
		soddisfa=soddisfa && ((minLunghezza==-1)?soddisfa : other.getLunghezza() >= minLunghezza);
		soddisfa=soddisfa && ((maxLunghezza==-1)?soddisfa : other.getLunghezza() <= maxLunghezza);
		return soddisfa;
	}
	
	@Override
	public boolean inConflitto(RichiestaMacchina other){
		if(other instanceof RichiestaCamion){
			RichiestaCamion ro;
			ro=(RichiestaCamion)other;
			boolean conflitto=gestisciLimiti(this.getMinCapacita(),this.getMaxCapacita(),ro.getMinCapacita(),ro.getMaxCapacita());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinPortata(),this.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinLunghezza(),this.getMaxLunghezza(),ro.getMinLunghezza(),ro.getMaxLunghezza());
			if(!conflitto){
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	
	
}
