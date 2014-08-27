package model.organizer.data;

public class RichiestaCamion extends RichiestaMacchina {
	public int minCapacita;
	public int minPortata;
	public int minLunghezza;
	public int maxCapacita;
	public int maxPortata;
	public int maxLunghezza;
	
	public RichiestaCamion(int minCapacita, int maxCapacita, int minPortata, int maxPortata, int minLunghezza, int maxLunghezza) {
		super();
		this.minCapacita = minCapacita;
		this.minPortata = minPortata;
		this.minLunghezza = minLunghezza;
		this.maxCapacita = maxCapacita;
		this.maxPortata = maxPortata;
		this.maxLunghezza = maxLunghezza;
		
	}

	public int getMinCapacita() {
		return minCapacita;
	}

	public void setMinCapacita(int minCapacita) {
		this.minCapacita = minCapacita;
	}

	public int getMinPortata() {
		return minPortata;
	}

	public void setMinPortata(int minPortata) {
		this.minPortata = minPortata;
	}

	public int getMinLunghezza() {
		return minLunghezza;
	}

	public void setMinLunghezza(int minLunghezza) {
		this.minLunghezza = minLunghezza;
	}

	public int getMaxCapacita() {
		return maxCapacita;
	}

	public void setMaxCapacita(int maxCapacita) {
		this.maxCapacita = maxCapacita;
	}

	public int getMaxPortata() {
		return maxPortata;
	}

	public void setMaxPortata(int maxPortata) {
		this.maxPortata = maxPortata;
	}

	public int getMaxLunghezza() {
		return maxLunghezza;
	}

	public void setMaxLunghezza(int maxLunghezza) {
		this.maxLunghezza = maxLunghezza;
	}

	@Override
	public String toString() {
		return  minCapacita + "-"
				+  maxCapacita + " "  + minPortata
				+ "-" + maxPortata + + minLunghezza
				+ "-" + maxLunghezza ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
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

	@Override
	public boolean rispettaRichiesta(Macchina m) {
		if (m == null)
			return false;
		if (Camion.class != m.getClass())
			return false;
		Camion other = (Camion) m;
		if(other.getCapacitaMassima() < minCapacita)
			return false;
		if(other.getCapacitaMassima() > maxCapacita)
			return false;
		if(other.getPortataMassima() < minPortata)
			return false;
		if(other.getPortataMassima() > maxPortata)
			return false;
		if(other.getLunghezza() < minLunghezza)
			return false;
		if(other.getLunghezza() > maxLunghezza)
			return false;
		return true;
	}
	
	
	
	
}
