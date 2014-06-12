package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Escavatore.
 */
public class Escavatore extends Macchina{
	
	/** The capacita max. */
	private int capacitaMax;
	
	/** The profondita max. */
	private int profonditaMax;
	
	/** The portata max. */
	private int portataMax;
	
	/** The altezza max. */
	private int altezzaMax;
	
	
	/**
	 * Instantiates a new escavatore.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param Modello the modello
	 * @param capacita the capacita
	 * @param portata the portata
	 * @param altezza the altezza
	 * @param profondita the profondita
	 */
	public Escavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita) {
		super(codice, produttore, Modello);
	
		this.capacitaMax=capacita;
		this.altezzaMax=altezza;
		this.portataMax=portata;
		this.profonditaMax=profondita;
		
	}
	
	//GET
	/**
	 * Gets the capacita massima.
	 *
	 * @return the capacita massima
	 */
	public int getCapacitaMassima(){		return this.capacitaMax;	}
	
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
	
	/**
	 * Gets the profondita massima.
	 *
	 * @return the profondita massima
	 */
	public int getProfonditaMassima(){		return this.profonditaMax;	}
	
	
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
	 * Sets the profondita massima.
	 *
	 * @param profondita the new profondita massima
	 */
	public void setProfonditaMassima(int profondita){		this.profonditaMax=profondita;	}
	
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
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getAltezzaMassima() + " " + this.getProfonditaMassima();
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
		Escavatore e=(Escavatore)obj;
		if((this.profonditaMax==e.getProfonditaMassima()) &&(this.portataMax==e.getPortataMassima())&&(this.capacitaMax==e.getCapacitaMassima())&&(this.altezzaMax==e.getAltezzaMassima())){
			return true;
		}
		else return false;
	}
}