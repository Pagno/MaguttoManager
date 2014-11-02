package model.organizer.data;

/**
 *   La classe Ruspa rappresenta le caratteristiche di una ruspa.
 *   
 *   Permette di gestire le tre caratteristiche principali di tale tipo di macchina, cioè la capacit&agrave;, la portata e l'altezza.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Ruspa extends Macchina{
	
	/**   Capacit&agrave; massima. */
	private int capacitaMax;
	
	/**   Portata massima. */
	private int portataMax;
	
	/**   Altezza massima. */
	private int altezzaMax;


	/**
	 * Istanzia una nuova ruspa.
	 *
	 * @param codice Il codice della Ruspa
	 * @param produttore Il produttore della Ruspa
	 * @param modello Il modello della Ruspa
	 * @param capacita La capacit&agrave; della Ruspa
	 * @param portata La portata della Ruspa
	 * @param altezza L'altezza della Ruspa
	 */
	public Ruspa(int codice, String produttore, String modello,int capacita,int portata,int altezza) {
		super(codice, produttore, modello);

		this.capacitaMax=capacita;
		this.altezzaMax=altezza;
		this.portataMax=portata;
	}
	
	//GET
	/**
	 * Restituisce la capacit&agrave; massima della ruspa.
	 *
	 * @return La capacit&agrave; massima della ruspa
	 */
	public int getCapacitaMassima(){	return this.capacitaMax; }
	
	/**
	 * Restituisce la portata massima della ruspa.
	 *
	 * @return La portata massima della ruspa
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Restituisce l'altezza massima della ruspa.
	 *
	 * @return L'altezza massima della ruspa
	 */
	public int getAltezzaMassima(){		return this.altezzaMax;	}
	
	
	//SET
	/**
	 * Modifica la capacit&agrave; massima della ruspa.
	 *
	 * @param capacita La nuova capacit&agrave; massima della ruspa
	 */
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	
	/**
	 * Modifica la portata massima della ruspa.
	 *
	 * @param portata La nuova portata massima della ruspa
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Modifica l'altezza massima della ruspa.
	 *
	 * @param altezza La nuova altezza massima della ruspa
	 */
	public void setAltezzaMassima(int altezza){		this.altezzaMax=altezza;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getAltezzaMassima();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + altezzaMax;
		result = prime * result + capacitaMax;
		result = prime * result + portataMax;
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
		Ruspa other = (Ruspa) obj;
		if (altezzaMax != other.altezzaMax)
			return false;
		if (capacitaMax != other.capacitaMax)
			return false;
		if (portataMax != other.portataMax)
			return false;
		return true;
	}
	
	
}