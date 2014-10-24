package model.organizer.data;

// 
/**
 *   Class Ruspa.
 */
public class Ruspa extends Macchina{
	
	/**   capacita max. */
	private int capacitaMax;
	
	/**   portata max. */
	private int portataMax;
	
	/**   altezza max. */
	private int altezzaMax;


	/**
	 * Instantiates a new ruspa.
	 *
	 * @param codice   codice della Ruspa
	 * @param produttore   produttore della Ruspa
	 * @param Modello   modello della Ruspa
	 * @param capacita   capacita della Ruspa
	 * @param portata   portata della Ruspa
	 * @param altezza   altezza della Ruspa
	 */
	public Ruspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza) {
		super(codice, produttore, Modello);

		this.capacitaMax=capacita;
		this.altezzaMax=altezza;
		this.portataMax=portata;
	}
	
	//GET
	/**
	 * Gets   capacita massima.
	 *
	 * @return   capacita massima
	 */
	public int getCapacitaMassima(){	return this.capacitaMax; }
	
	/**
	 * Gets   portata massima.
	 *
	 * @return   portata massima
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Gets   altezza massima.
	 *
	 * @return   altezza massima
	 */
	public int getAltezzaMassima(){		return this.altezzaMax;	}
	
	
	//SET
	/**
	 * Sets   capacita massima.
	 *
	 * @param capacita   new capacita massima
	 */
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	
	/**
	 * Sets   portata massima.
	 *
	 * @param portata   new portata massima
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Sets   altezza massima.
	 *
	 * @param altezza   new altezza massima
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