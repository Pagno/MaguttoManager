package model.organizer.data;

public class RichiestaEscavatore extends RichiestaMacchina {
	int minCapacita;
	int maxCapacita;
	int minPortata;
	int maxPortata;
	int minAltezza;
	int maxAltezza;
	int minProfondita;
	int maxProfondita;
	
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

	public int getMinProfondita() {
		return minProfondita;
	}

	public void setMinProfondita(int minProfondita) {
		this.minProfondita = minProfondita;
	}

	public int getMaxProfondita() {
		return maxProfondita;
	}

	public void setMaxProfondita(int maxProfondita) {
		this.maxProfondita = maxProfondita;
	}

	@Override
	public String toString() {
		return  "Escavatore:  "+minCapacita
				+ "-" + maxCapacita + " " + minPortata
				+ "-" + maxPortata + " " + minAltezza
				+ "-" + maxAltezza + " "
				+ minProfondita + "-" + maxProfondita;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
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

	@Override
	public boolean rispettaRichiesta(Macchina m) {
		if (m == null)
			return false;
		if (Escavatore.class != m.getClass())
			return false;
		Escavatore other = (Escavatore) m;
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
		if(other.getProfonditaMassima() < minProfondita)
			return false;
		if(other.getProfonditaMassima() > maxProfondita)
			return false;
		return true;
	}
	
	
	
	
	
}
