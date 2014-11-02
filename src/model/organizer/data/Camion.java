package model.organizer.data;

/**
 *   La classe Camion rappresenta le caratteristiche di un camion.
 *   
 *   Permette di gestire le tre caratteristiche principali di tale tipo di macchina, cioè la capacit&agrave;, la portata e la lunghezza.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Camion extends Macchina {
	
	/**   Capacit&agrave; massima. */
	private int capacitaMax;
	
	/**   Cortata massima. */
	private int portataMax;
	
	/**   Lunghezza. */
	private int lunghezza;

	/**
	 * Istanzia un nuovo camion.
	 *
	 * @param codice Il codice del camion
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 */
	public Camion(int codice,String produttore,String modello,int capacita,int portata,int lunghezza){
		super(codice,produttore,modello);
		this.capacitaMax=capacita;
		this.portataMax=portata;
		this.lunghezza=lunghezza;
		
	}
	
	
	//GET
	/**
	 * Restituisce la capacit&agrave; massima del camion.
	 *
	 * @return La capacit&agrave; massima
	 */
	public int getCapacitaMassima(){	return this.capacitaMax; }
	
	/**
	 * Restituisce la portata massima del camion.
	 *
	 * @return La portata massima
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Restituisce la lunghezza del camion.
	 *
	 * @return La lunghezza
	 */
	public int getLunghezza(){		return this.lunghezza;	}
	
	
	//SET
	/**
	 * Modifica la capacit&agrave; massima del camion.
	 *
	 * @param capacita La nuova capacit&agrave; del camion
	 */
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	
	/**
	 * Modifica la portata massima del camion.
	 *
	 * @param portata La nuova portata massima del camion
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Modifica la lunghezza del camion.
	 *
	 * @param lunghezza La nuova lunghezza del camion
	 */
	public void setLunghezza(int lunghezza){		this.lunghezza=lunghezza;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getLunghezza();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + capacitaMax;
		result = prime * result + lunghezza;
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
		Camion other = (Camion) obj;
		if (capacitaMax != other.capacitaMax)
			return false;
		if (lunghezza != other.lunghezza)
			return false;
		if (portataMax != other.portataMax)
			return false;
		return true;
	}
	
}