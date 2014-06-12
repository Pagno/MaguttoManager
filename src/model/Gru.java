package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Gru.
 */
public class Gru extends Macchina{
	
	/** The lunghezza. */
	private int lunghezza;
	
	/** The altezza. */
	private int altezza;
	
	/** The portata max. */
	private int portataMax;
	
	/** The angolo rotazione. */
	private int angoloRotazione;
	
	/**
	 * Instantiates a new gru.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param modello the modello
	 * @param rotazione the rotazione
	 * @param portata the portata
	 * @param lunghezza the lunghezza
	 * @param altezza the altezza
	 */
	public Gru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		super(codice,produttore,modello);

		this.altezza=altezza;
		this.angoloRotazione=rotazione;
		this.portataMax=portata;
		this.lunghezza=lunghezza;
	}
	
	//GET
	/**
	 * Gets the lunghezza.
	 *
	 * @return the lunghezza
	 */
	public int getLunghezza(){		return this.lunghezza;	}
	
	/**
	 * Gets the portata massima.
	 *
	 * @return the portata massima
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Gets the altezza.
	 *
	 * @return the altezza
	 */
	public int getAltezza(){		return this.altezza;	}
	
	/**
	 * Gets the angolo rotazione.
	 *
	 * @return the angolo rotazione
	 */
	public int getAngoloRotazione(){		return this.angoloRotazione;	}
	
	
	//SET
	/**
	 * Sets the lunghezza massima.
	 *
	 * @param lunghezza the new lunghezza massima
	 */
	public void setLunghezzaMassima(int lunghezza){	this.lunghezza=lunghezza;	}
	
	/**
	 * Sets the portata massima.
	 *
	 * @param portata the new portata massima
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Sets the altezza.
	 *
	 * @param altezza the new altezza
	 */
	public void setAltezza(int altezza){		this.altezza=altezza;	}
	
	/**
	 * Sets the angolo rotazione.
	 *
	 * @param rotazione the new angolo rotazione
	 */
	public void setAngoloRotazione(int rotazione){	this.angoloRotazione=rotazione;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getLunghezza() + " " + this.getPortataMassima() + " " + this.getAltezza() + " " + this.getAngoloRotazione();
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
		Gru g=(Gru)obj;
		if((this.angoloRotazione==g.getAngoloRotazione()) &&(this.portataMax==g.getPortataMassima())&&(this.lunghezza==g.getLunghezza())&&(this.altezza==g.getAltezza())){
			return true;
		}
		else return false;
	}
	
}