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
		return "Ruspa:  "+minCapacita + "-"
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

		boolean soddisfa=true;
		soddisfa=soddisfa && (minCapacita==0)?true:other.getCapacitaMassima() < minCapacita;
		soddisfa=soddisfa && (maxCapacita==0)?true:other.getCapacitaMassima() >maxCapacita;
		soddisfa=soddisfa && (minPortata==0)?true:other.getPortataMassima() < minPortata;
		soddisfa=soddisfa && (maxPortata==0)?true:other.getPortataMassima() >maxPortata;
		soddisfa=soddisfa && (minAltezza==0)?true:other.getAltezzaMassima() < minAltezza;
		soddisfa=soddisfa && (maxAltezza==0)?true:other.getAltezzaMassima() >maxAltezza;
		
		return true;
	}
}
