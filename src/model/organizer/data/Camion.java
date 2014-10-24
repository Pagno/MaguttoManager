package model.organizer.data;

// 
/**
 *   Class Camion.
 */
public class Camion extends Macchina {
	
	/**   capacita max. */
	private int capacitaMax;
	
	/**   portata max. */
	private int portataMax;
	
	/**   lunghezza. */
	private int lunghezza;

	/**
	 * Instantiates a new camion.
	 *
	 * @param codice codice del camion
	 * @param produttore produttore del camion
	 * @param Modello modello del camion
	 * @param capacita capacita del camion
	 * @param portata portata del camion
	 * @param lunghezza lunghezza del camion
	 */
	public Camion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza){
		super(codice,produttore,Modello);
		this.capacitaMax=capacita;
		this.portataMax=portata;
		this.lunghezza=lunghezza;
		
	}
	
	
	//GET
	/**
	 * Gets   capacita massima.
	 *
	 * @return capacita massima
	 */
	public int getCapacitaMassima(){	return this.capacitaMax; }
	
	/**
	 * Gets portata massima.
	 *
	 * @return portata massima
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Gets lunghezza.
	 *
	 * @return lunghezza
	 */
	public int getLunghezza(){		return this.lunghezza;	}
	
	
	//SET
	/**
	 * Sets capacita massima.
	 *
	 * @param capacita new capacita massima
	 */
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	
	/**
	 * Sets portata massima.
	 *
	 * @param portata new portata massima
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Sets lunghezza.
	 *
	 * @param lunghezza new lunghezza
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