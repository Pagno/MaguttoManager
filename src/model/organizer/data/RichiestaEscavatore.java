package model.organizer.data;


/**
 * The Class RichiestaEscavatore.
 */
public class RichiestaEscavatore extends RichiestaMacchina {
	
	/** The min capacita. */
	int minCapacita;
	
	/** The max capacita. */
	int maxCapacita;
	
	/** The min portata. */
	int minPortata;
	
	/** The max portata. */
	int maxPortata;
	
	/** The min altezza. */
	int minAltezza;
	
	/** The max altezza. */
	int maxAltezza;
	
	/** The min profondita. */
	int minProfondita;
	
	/** The max profondita. */
	int maxProfondita;
	
	/**
	 *Crea una nuova richiesta per gli escavatori.
	 *
	 * @param minCapacita the min capacita
	 * @param maxCapacita the max capacita
	 * @param minPortata the min portata
	 * @param maxPortata the max portata
	 * @param minAltezza the min altezza
	 * @param maxAltezza the max altezza
	 * @param minProfondita the min profondita
	 * @param maxProfondita the max profondita
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
	 * @param minAltezza minima altezza
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
	 * Ritorna minima profondita.
	 *
	 * @return minima profondita
	 */
	public int getMinProfondita() {
		return minProfondita;
	}

	/**
	 * Assegna minima profondita.
	 *
	 * @param minPortata minima profondita
	 */
	public void setMinProfondita(int minProfondita) {
		this.minProfondita = minProfondita;
	}

	/**
	 * Ritorna massima profondita.
	 *
	 * @return massima profondita
	 */
	public int getMaxProfondita() {
		return maxProfondita;
	}

	/**
	 * Assegna massima profondita.
	 *
	 * @param maxPortata massima profondita
	 */
	public void setMaxProfondita(int maxProfondita) {
		this.maxProfondita = maxProfondita;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  "Richiesta:Escavatore " + minCapacita
				+ "-" + maxCapacita + " " + minPortata
				+ "-" + maxPortata + " " + minAltezza
				+ "-" + maxAltezza + " "
				+ minProfondita + "-" + maxProfondita;
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
	
	
	
	
	
}
