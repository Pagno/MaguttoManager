package model.organizer.data;

public class RichiestaRuspa extends RichiestaMacchina {
	private int minCapacita;
	private int maxCapacita;
	private int minPortata;
	private int maxPortata;
	private int minAltezza;
	private int maxAltezza;

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

	public int getMinCapacita() {
		return minCapacita;
	}

	public void setMinCapacita(int minCapacita) {
		this.minCapacita = minCapacita;
	}

	public int getMaxCapacita() {
		return maxCapacita;
	}

	public void setMaxCapacita(int maxCapacita) {
		this.maxCapacita = maxCapacita;
	}

	public int getMinPortata() {
		return minPortata;
	}

	public void setMinPortata(int minPortata) {
		this.minPortata = minPortata;
	}

	public int getMaxPortata() {
		return maxPortata;
	}

	public void setMaxPortata(int maxPortata) {
		this.maxPortata = maxPortata;
	}

	public int getMinAltezza() {
		return minAltezza;
	}

	public void setMinAltezza(int minAltezza) {
		this.minAltezza = minAltezza;
	}

	public int getMaxAltezza() {
		return maxAltezza;
	}

	public void setMaxAltezza(int maxAltezza) {
		this.maxAltezza = maxAltezza;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
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

	@Override
	public String toString() {
		return minCapacita + "-"
				+ maxCapacita + " " + minPortata + "-"
				+ maxPortata + " " + minAltezza + "-"
				+ maxAltezza;
	}

	@Override
	public boolean rispettaRichiesta(Macchina m) {
		if (m == null)
			return false;
		if (Ruspa.class != m.getClass())
			return false;
		Ruspa other = (Ruspa) m;
		if(other.getCapacitaMassima() < minCapacita)
			return false;
		if(other.getCapacitaMassima() > maxCapacita)
			return false;
		if(other.getPortataMassima() < minPortata)
			return false;
		if(other.getPortataMassima() > maxPortata)
			return false;
		if(other.getAltezzaMassima() < minAltezza)
			return false;
		if(other.getAltezzaMassima() > maxAltezza)
			return false;
		return true;
	}
}
