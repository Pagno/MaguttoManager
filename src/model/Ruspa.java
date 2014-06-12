package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Ruspa.
 */
public class Ruspa extends Macchina{
	
	/** The capacita max. */
	private int capacitaMax;
	
	/** The portata max. */
	private int portataMax;
	
	/** The altezza max. */
	private int altezzaMax;


	/**
	 * Instantiates a new ruspa.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 */
	public Ruspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza) {
		super(codice, produttore, Modello);

		this.capacitaMax=capacita;
		this.altezzaMax=altezza;
		this.portataMax=portata;
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
	 * Gets the altezza massima.
	 *
	 * @return the altezza massima
	 */
	public int getAltezzaMassima(){		return this.altezzaMax;	}
	
	
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
	 * Sets the altezza massima.
	 *
	 * @param altezza the new altezza massima
	 */
	public void setAltezzaMassima(int altezza){		this.altezzaMax=altezza;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getAltezzaMassima();
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
		Ruspa r=(Ruspa)obj;
		if((this.portataMax==r.getPortataMassima())&&(this.capacitaMax==r.getCapacitaMassima())&&(this.altezzaMax==r.getAltezzaMassima())){
			return true;
		}
		else return false;
	}
	
}