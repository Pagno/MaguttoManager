package model.organizer.data;


/**
 *   Questa classe rappresenta le caratteristiche richieste nel caso di richiesta di escavatori. 
 *   <p>
 *   Definisce quindi gli intervalli di valori entro cui devono rientrare le caratteristiche dell'escavatore, fornendo poi i metodi 
 *   necessari a controllare che le caratteristiche siano soddisfatte.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class RichiestaEscavatore extends RichiestaMacchina {
	
	/** Capacit&agrave; minima. */
	int minCapacita;
	
	/** Capacit&agrave; massima. */
	int maxCapacita;
	
	/** Portata minima. */
	int minPortata;
	
	/** Portata massima. */
	int maxPortata;
	
	/** Altezza minima. */
	int minAltezza;
	
	/** Altezza massima. */
	int maxAltezza;
	
	/** Profondit&agrave; minima. */
	int minProfondita;
	
	/** Profondit&agrave; massima. */
	int maxProfondita;
	
	/**
	 * Crea un nuovo set di requisiti per una richiesta di escavatori.
	 *
	 * @param minCapacita La capacit&agrave; minima
	 * @param maxCapacita La capacit&agrave; massima
	 * @param minPortata La portata minima
	 * @param maxPortata La portata massima
	 * @param minAltezza L'altezza minima
	 * @param maxAltezza L'altezza massima
	 * @param minProfondita La profondit&agrave; minima
	 * @param maxProfondita La profondit&agrave; massima
	 */
	public RichiestaEscavatore(int minCapacita, int maxCapacita,
			int minPortata, int maxPortata, int minAltezza, int maxAltezza,
			int minProfondita, int maxProfondita) {
		super();
		this.minCapacita = minCapacita;
		this.maxCapacita = maxCapacita;
		this.minPortata = minPortata;
		this.maxPortata = maxPortata;
		this.minAltezza = minAltezza;
		this.maxAltezza = maxAltezza;
		this.minProfondita = minProfondita;
		this.maxProfondita = maxProfondita;
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
	 * @param maxPortata La nuova massima portata
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
	 * @param maxPortata La nuova massima altezza
	 */
	public void setMaxAltezza(int maxAltezza) {
		this.maxAltezza = maxAltezza;
	}

	/**
	 * Restituisce la profondit&agrave; minima.
	 *
	 * @return La profondit&agrave; minima
	 */
	public int getMinProfondita() {
		return minProfondita;
	}

	/**
	 * Modifica la profondit&agrave; minima.
	 *
	 * @param minPortata La nuova profondit&agrave; minima
	 */
	public void setMinProfondita(int minProfondita) {
		this.minProfondita = minProfondita;
	}

	/**
	 * Restituisce la profondit&agrave; massima.
	 *
	 * @return La profondit&agrave; massima
	 */
	public int getMaxProfondita() {
		return maxProfondita;
	}

	/**
	 * Modifica la profondit&agrave; massima.
	 *
	 * @param maxProfondita the new max profondita
	 */
	public void setMaxProfondita(int maxProfondita) {
		this.maxProfondita = maxProfondita;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String value="Richiesta:Escavatore ";
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
			value=value+"* ";
		}
		else{
			value=value+ maxAltezza + " ";
		}
		if(minProfondita==-1){
			value=value+"*-";
		}
		else{
			value=value+ minProfondita + "-";
		}
		if(maxProfondita==-1){
			value=value+"*";
		}
		else{
			value=value+ maxProfondita;
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
		if (!(obj instanceof RichiestaEscavatore))
			return false;
		RichiestaEscavatore other = (RichiestaEscavatore) obj;
		if (maxAltezza != other.maxAltezza)
			return false;
		if (maxCapacita != other.maxCapacita)
			return false;
		if (maxPortata != other.maxPortata)
			return false;
		if (maxProfondita != other.maxProfondita)
			return false;
		if (minAltezza != other.minAltezza)
			return false;
		if (minCapacita != other.minCapacita)
			return false;
		if (minPortata != other.minPortata)
			return false;
		if (minProfondita != other.minProfondita)
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
		if (Escavatore.class != m.getClass())
			return false;
		Escavatore other = (Escavatore) m;

		
		boolean soddisfa=true;
		soddisfa=soddisfa && ((minCapacita==-1)?soddisfa : other.getCapacitaMassima() >= minCapacita);
		soddisfa=soddisfa && ((maxCapacita==-1)?soddisfa : other.getCapacitaMassima() <= maxCapacita);
		soddisfa=soddisfa && ((minPortata==-1)?soddisfa : other.getPortataMassima() >= minPortata);
		soddisfa=soddisfa && ((maxPortata==-1)?soddisfa : other.getPortataMassima() <= maxPortata);
		soddisfa=soddisfa && ((minAltezza==-1)?soddisfa : other.getAltezzaMassima() >= minAltezza);
		soddisfa=soddisfa && ((maxAltezza==-1)?soddisfa : other.getAltezzaMassima() <= maxAltezza);
		soddisfa=soddisfa && ((minProfondita==-1)?soddisfa : other.getProfonditaMassima() >= minProfondita);
		soddisfa=soddisfa && ((maxProfondita==-1)?soddisfa : other.getProfonditaMassima() <= maxProfondita);
		return soddisfa;
	}
	
	/* (non-Javadoc)
	 * @see model.organizer.data.RichiestaMacchina#inConflitto(model.organizer.data.RichiestaMacchina)
	 */
	@Override
	public boolean inConflitto(RichiestaMacchina other){
		if(other instanceof RichiestaEscavatore){
			RichiestaEscavatore ro;
			ro=(RichiestaEscavatore)other;
			
			boolean conflitto=gestisciLimiti(this.getMinCapacita(),this.getMaxCapacita(),ro.getMinCapacita(),ro.getMaxCapacita());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinPortata(),this.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
			if(!conflitto){
				return false;
			}
			conflitto=gestisciLimiti(this.getMinProfondita(),this.getMaxProfondita(),ro.getMinProfondita(),ro.getMaxProfondita());
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
