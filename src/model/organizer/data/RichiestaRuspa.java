package model.organizer.data;

// TODO: Auto-generated Javadoc
/**
 * The Class RichiestaRuspa.
 */
public class RichiestaRuspa extends RichiestaMacchina {
	
	/** The min capacita. */
	private int minCapacita;
	
	/** The max capacita. */
	private int maxCapacita;
	
	/** The min portata. */
	private int minPortata;
	
	/** The max portata. */
	private int maxPortata;
	
	/** The min altezza. */
	private int minAltezza;
	
	/** The max altezza. */
	private int maxAltezza;

	/**
	 * Crea una nuova richiesta per le ruspe.
	 *
	 * @param minCapacita the min capacita
	 * @param maxCapacita the max capacita
	 * @param minPortata the min portata
	 * @param maxPortata the max portata
	 * @param minAltezza the min altezza
	 * @param maxAltezza the max altezza
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
		return "Richiesta:Ruspa " + minCapacita + "-"
				+ maxCapacita + " " + minPortata + "-"
				+ maxPortata + " " + minAltezza + "-"
				+ maxAltezza;
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
}
