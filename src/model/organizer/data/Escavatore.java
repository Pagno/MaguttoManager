package model.organizer.data;

/**
 *   La classe Escavatore rappresenta le caratteristiche di un escavatore.
 *   
 *   Permette di gestire le quattro caratteristiche principali di tale tipo di macchina, cioè la capacit&agrave;, la profondit&agrave;, la portata e l'altezza.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Escavatore extends Macchina{
	
	/**   Capacit&agrave; massima. */
	private int capacitaMax;
	
	/**   Profondit&agrave; massima. */
	private int profonditaMax;
	
	/**   Portata massima. */
	private int portataMax;
	
	/**   Altezza massima. */
	private int altezzaMax;
	
	
	/**
	 * Istanzia un nuovo escavatore.
	 *
	 * @param codice Il codice dell'Escavatore
	 * @param produttore Il produttore dell'Escavatore
	 * @param modello Il modello dell'Escavatore
	 * @param capacita La capacit&agrave; dell'Escavatore
	 * @param portata La portata dell'Escavatore
	 * @param altezza L'altezza dell'Escavatore
	 * @param profondita La profondit&agrave; dell'Escavatore
	 */
	public Escavatore(int codice, String produttore, String modello,int capacita,int portata,int altezza,int profondita) {
		super(codice, produttore, modello);
	
		this.capacitaMax=capacita;
		this.altezzaMax=altezza;
		this.portataMax=portata;
		this.profonditaMax=profondita;
		
	}
	
	//GET
	/**
	 * Restituisce la capacit&agrave; massima dell'escavatore.
	 *
	 * @return La capacit&agrave; massima
	 */
	public int getCapacitaMassima(){		return this.capacitaMax;	}
	
	/**
	 * Restituisce la portata massima dell'escavatore.
	 *
	 * @return La portata massima
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Restituisce l'altezza massima dell'escavatore.
	 *
	 * @return L'altezza massima
	 */
	public int getAltezzaMassima(){		return this.altezzaMax;	}
	
	/**
	 * Restituisce la profondit&agrave; massima dell'escavatore.
	 *
	 * @return La profondit&agrave; massima
	 */
	public int getProfonditaMassima(){		return this.profonditaMax;	}
	
	
	//SET
	/**
	 * Modifica la capacit&agrave; massima dell'escavatore.
	 *
	 * @param capacita La nuova capacit&agrave; massima dell'escavatore
	 */
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	
	/**
	 * Modifica la portata massima dell'escavatore.
	 *
	 * @param portata La nuova portata massima dell'escavatore
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Modifica la profondit&agrave; massima dell'escavatore.
	 *
	 * @param profondita La nuova profondit&agrave; massima dell'escavatore
	 */
	public void setProfonditaMassima(int profondita){		this.profonditaMax=profondita;	}
	
	/**
	 * Modifica l'altezza massima dell'escavatore.
	 *
	 * @param altezza La nuova altezza massima dell'escavatore
	 */
	public void setAltezzaMassima(int altezza){		this.altezzaMax=altezza;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getAltezzaMassima() + " " + this.getProfonditaMassima();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + altezzaMax;
		result = prime * result + capacitaMax;
		result = prime * result + portataMax;
		result = prime * result + profonditaMax;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escavatore other = (Escavatore) obj;
		if (altezzaMax != other.altezzaMax)
			return false;
		if (capacitaMax != other.capacitaMax)
			return false;
		if (portataMax != other.portataMax)
			return false;
		if (profonditaMax != other.profonditaMax)
			return false;
		return true;
	}
	
	
	
}