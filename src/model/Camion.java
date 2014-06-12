package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Camion.
 */
public class Camion extends Macchina {
	
	/** The capacita max. */
	private int capacitaMax;
	
	/** The portata max. */
	private int portataMax;
	
	/** The lunghezza. */
	private int lunghezza;

	/**
	 * Instantiates a new camion.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 */
	public Camion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza){
		super(codice,produttore,Modello);
		this.capacitaMax=capacita;
		this.portataMax=portata;
		this.lunghezza=lunghezza;
		
	}
	
	
	//GET
	/**
	 * Gets the capacita massima.
	 *
	 * @return the capacita massima
	 */
	public int getCapacitaMassima(){	return this.capacitaMax; }
	
	/**
	 * Gets the portata massima.
	 *
	 * @return the portata massima
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Gets the lunghezza.
	 *
	 * @return the lunghezza
	 */
	public int getLunghezza(){		return this.lunghezza;	}
	
	
	//SET
	/**
	 * Sets the capacita massima.
	 *
	 * @param capacita the new capacita massima
	 */
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	
	/**
	 * Sets the portata massima.
	 *
	 * @param portata the new portata massima
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Sets the lunghezza.
	 *
	 * @param lunghezza the new lunghezza
	 */
	public void setLunghezza(int lunghezza){		this.lunghezza=lunghezza;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getLunghezza();
	}
	
	/* (non-Javadoc)
	 * @see model.Macchina#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if(!(super.equals(obj))){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Camion c=(Camion)obj;
		if((this.capacitaMax==c.getCapacitaMassima()) &&(this.portataMax==c.getPortataMassima())&&(this.lunghezza==c.getLunghezza())){
			return true;
		}
		else return false;
	}

}