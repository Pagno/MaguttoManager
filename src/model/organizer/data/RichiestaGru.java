package model.organizer.data;

public class RichiestaGru extends RichiestaMacchina {
	private int minLunghezza;
	private int maxLunghezza;
	private int minAltezza;
	private int maxAltezza;
	private int minPortata;
	private int maxPortata;
	private int minAngoloRotazione;
	private int maxAngoloRotazione;
	
	public RichiestaGru(int minLunghezza, int maxLunghezza, int minAltezza,
			int maxAltezza, int minPortata, int maxPortata,
			int minAngoloRotazione, int maxAngoloRotazione) {
		super();
		this.minLunghezza = minLunghezza;
		this.maxLunghezza = maxLunghezza;
		this.minAltezza = minAltezza;
		this.maxAltezza = maxAltezza;
		this.minPortata = minPortata;
		this.maxPortata = maxPortata;
		this.minAngoloRotazione = minAngoloRotazione;
		this.maxAngoloRotazione = maxAngoloRotazione;
	}

	public int getMinLunghezza() {
		return minLunghezza;
	}

	public void setMinLunghezza(int minLunghezza) {
		this.minLunghezza = minLunghezza;
	}

	public int getMaxLunghezza() {
		return maxLunghezza;
	}

	public void setMaxLunghezza(int maxLunghezza) {
		this.maxLunghezza = maxLunghezza;
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

	public int getMinAngoloRotazione() {
		return minAngoloRotazione;
	}

	public void setMinAngoloRotazione(int minAngoloRotazione) {
		this.minAngoloRotazione = minAngoloRotazione;
	}

	public int getMaxAngoloRotazione() {
		return maxAngoloRotazione;
	}

	public void setMaxAngoloRotazione(int maxAngoloRotazione) {
		this.maxAngoloRotazione = maxAngoloRotazione;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichiestaGru other = (RichiestaGru) obj;
		if (maxAltezza != other.maxAltezza)
			return false;
		if (maxAngoloRotazione != other.maxAngoloRotazione)
			return false;
		if (maxLunghezza != other.maxLunghezza)
			return false;
		if (maxPortata != other.maxPortata)
			return false;
		if (minAltezza != other.minAltezza)
			return false;
		if (minAngoloRotazione != other.minAngoloRotazione)
			return false;
		if (minLunghezza != other.minLunghezza)
			return false;
		if (minPortata != other.minPortata)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Richiesta:Gru " + minLunghezza + "-"
				+ maxLunghezza + " " + minAltezza + "-"
				+ maxAltezza + " " + minPortata + "-"
				+ maxPortata + " " + minAngoloRotazione
				+ "-" + maxAngoloRotazione ;
	}

	@Override
	public boolean rispettaRichiesta(Macchina m) {
		if (m == null)
			return false;
		if (Gru.class != m.getClass())
			return false;
		Gru gru = (Gru) m;

		boolean soddisfa=true;
		soddisfa=soddisfa && ((minLunghezza==-1)?soddisfa : gru.getLunghezza() >= minLunghezza);
		soddisfa=soddisfa && ((maxLunghezza==-1)?soddisfa : gru.getLunghezza() <= maxLunghezza);
		soddisfa=soddisfa && ((minPortata==-1)?soddisfa : gru.getPortataMassima() >= minPortata);
		soddisfa=soddisfa && ((maxPortata==-1)?soddisfa : gru.getPortataMassima() <= maxPortata);
		soddisfa=soddisfa && ((minAltezza==-1)?soddisfa : gru.getAltezza() >= minAltezza);
		soddisfa=soddisfa && ((maxAltezza==-1)?soddisfa : gru.getAltezza() <= maxAltezza);
		soddisfa=soddisfa && ((minAngoloRotazione==-1)?soddisfa : gru.getAngoloRotazione() >= minAngoloRotazione);
		soddisfa=soddisfa && ((maxAngoloRotazione==-1)?soddisfa : gru.getAngoloRotazione() <= maxAngoloRotazione);
		return soddisfa;
	}
	
	
	
	
}
