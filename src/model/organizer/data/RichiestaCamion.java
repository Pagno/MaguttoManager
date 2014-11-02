package model.organizer.data;



/**
 *   Questa classe rappresenta le caratteristiche richieste nel caso di richiesta di camion. 
 *   <p>
 *   Definisce quindi gli intervalli di valori entro cui devono rientrare le caratteristiche del camion, fornendo poi i metodi 
 *   necessari a controllare che le caratteristiche siano soddisfatte.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class RichiestaCamion extends RichiestaMacchina {
	
	/** Capacit&agrave; minima accettabile. */
	public int minCapacita;
	
	/** Portata minima accettabile. */
	public int minPortata;
	
	/** Lunghezza minima accettabile. */
	public int minLunghezza;
	
	/** Capacit&agrave; massima accettabile. */
	public int maxCapacita;
	
	/** Portata massima accettabile. */
	public int maxPortata;
	
	/** Lunghezza massima accettabile. */
	public int maxLunghezza;
	
	/**
	 * Crea un nuovo set di requisiti per una richiesta di camion.
	 *
	 * @param minCapacita La minima capacit&agrave; richiesta
	 * @param maxCapacita La massima capacit&agrave; richiesta
	 * @param minPortata La minima portata richiesta
	 * @param maxPortata La massima portata richiesta
	 * @param minLunghezza La minima lunghezza richiesta
	 * @param maxLunghezza La massima lunghezza richiesta
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
	 * Restituisce la capacit&grave; minima.
	 *
	 * @return La capacit&grave; minima
	 */
	public int getMinCapacita() {
		return minCapacita;
	}

	/**
	 * Modifica la capacit&grave; minima.
	 *
	 * @param minCapacita La nuova capacit&grave; minima
	 */
	public void setMinCapacita(int minCapacita) {
		this.minCapacita = minCapacita;
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
	 * @param maxLunghezza La nuova lunghezza massima
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
	
	/* (non-Javadoc)
	 * @see model.organizer.data.RichiestaMacchina#inConflitto(model.organizer.data.RichiestaMacchina)
	 */
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
