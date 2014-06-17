package model;

// TODO: Auto-generated Javadoc
/**
 *   Class Escavatore.
 */
public class Escavatore extends Macchina{
	
	/**   capacita max. */
	private int capacitaMax;
	
	/**   profondita max. */
	private int profonditaMax;
	
	/**   portata max. */
	private int portataMax;
	
	/**   altezza max. */
	private int altezzaMax;
	
	
	/**
	 * Instantiates a new escavatore.
	 *
	 * @param codice codice dell'Escavatore
	 * @param produttore produttore dell'Escavatore
	 * @param Modello modello dell'Escavatore
	 * @param capacita capacita dell'Escavatore
	 * @param portata portata dell'Escavatore
	 * @param altezza altezza dell'Escavatore
	 * @param profondita profondita dell'Escavatore
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
	 * Gets   capacita massima.
	 *
	 * @return   capacita massima
	 */
	public int getCapacitaMassima(){		return this.capacitaMax;	}
	
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
	
	/**
	 * Gets   profondita massima.
	 *
	 * @return   profondita massima
	 */
	public int getProfonditaMassima(){		return this.profonditaMax;	}
	
	
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
	 * Sets   profondita massima.
	 *
	 * @param profondita   new profondita massima
	 */
	public void setProfonditaMassima(int profondita){		this.profonditaMax=profondita;	}
	
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
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getAltezzaMassima() + " " + this.getProfonditaMassima();
	}
	
	/* (non-Javadoc)
	 * @see model.Macchina#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if(!(super.equals(obj))){
			return false;
		}
		Escavatore e=(Escavatore)obj;
		if((this.profonditaMax==e.getProfonditaMassima()) &&(this.portataMax==e.getPortataMassima())&&(this.capacitaMax==e.getCapacitaMassima())&&(this.altezzaMax==e.getAltezzaMassima())){
			return true;
		}
		else return false;
	}
}