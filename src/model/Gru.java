package model;

// TODO: Auto-generated Javadoc
/**
 *   Class Gru.
 */
public class Gru extends Macchina{
	
	/**   lunghezza. */
	private int lunghezza;
	
	/**   altezza. */
	private int altezza;
	
	/**   portata max. */
	private int portataMax;
	
	/**   angolo rotazione. */
	private int angoloRotazione;
	
	/**
	 * Instantiates a new gru.
	 *
	 * @param codice   codice della Gru
	 * @param produttore   produttore della Gru
	 * @param modello   modello della Gru
	 * @param rotazione   rotazione della Gru
	 * @param portata   portata della Gru
	 * @param lunghezza   lunghezza della Gru
	 * @param altezza   altezza della Gru
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
	 * Gets   lunghezza.
	 *
	 * @return   lunghezza
	 */
	public int getLunghezza(){		return this.lunghezza;	}
	
	/**
	 * Gets   portata massima.
	 *
	 * @return   portata massima
	 */
	public int getPortataMassima(){		return this.portataMax;	}
	
	/**
	 * Gets   altezza.
	 *
	 * @return   altezza
	 */
	public int getAltezza(){		return this.altezza;	}
	
	/**
	 * Gets   angolo rotazione.
	 *
	 * @return   angolo rotazione
	 */
	public int getAngoloRotazione(){		return this.angoloRotazione;	}
	
	
	//SET
	/**
	 * Sets   lunghezza massima.
	 *
	 * @param lunghezza   new lunghezza massima
	 */
	public void setLunghezzaMassima(int lunghezza){	this.lunghezza=lunghezza;	}
	
	/**
	 * Sets   portata massima.
	 *
	 * @param portata   new portata massima
	 */
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	
	/**
	 * Sets   altezza.
	 *
	 * @param altezza   new altezza
	 */
	public void setAltezza(int altezza){		this.altezza=altezza;	}
	
	/**
	 * Sets   angolo rotazione.
	 *
	 * @param rotazione   new angolo rotazione
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
		Gru g=(Gru)obj;
		if((this.angoloRotazione==g.getAngoloRotazione()) &&(this.portataMax==g.getPortataMassima())&&(this.lunghezza==g.getLunghezza())&&(this.altezza==g.getAltezza())){
			return true;
		}
		else return false;
	}
	
}