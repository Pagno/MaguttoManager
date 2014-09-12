package model.organizer.data;

import java.util.ArrayList;

// 
/**
 *   Class Macchina.
 */
public abstract class Macchina {
	
	/**   modello. 		*/
	private String modello;
	
	/**   codice. 		*/
	private int codice;
	
	/**   produttore. 	*/
	private String produttore;
	
	/**	  Richiesta 	*/
	private ArrayList<Richiesta> richieste;
	

	/**
	 * Instantiates a new macchina.
	 *
	 * @param codice   codice
	 * @param produttore   produttore
	 * @param modello   modello
	 */
	public Macchina(int codice,String produttore,String modello){
		this.codice=codice;
		this.produttore=produttore;
		this.modello=modello;
	}
	
	/**
	 * Gets   codice.
	 *
	 * @return   codice
	 */
	public int getCodice(){		return this.codice;	}
	
	/**
	 * Gets   produttore.
	 *
	 * @return   produttore
	 */
	public String getProduttore(){		return this.produttore;	}
	
	/**
	 * Gets   modello.
	 *
	 * @return   modello
	 */
	public String getModello(){		return this.modello;	}
	
	/**
	 * Sets   produttore.
	 *
	 * @param produttore   new produttore
	 */
	public void setProduttore(String produttore){		this.produttore=produttore;	}
	
	/**
	 * Sets   modello.
	 *
	 * @param Modello   new modello
	 */
	public void setModello(String Modello){		this.modello=Modello;	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj==null){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Macchina m=(Macchina)obj;
		if(this.codice==m.getCodice()){
			if(this.produttore.equals(m.getProduttore())){
				if(this.modello.equals(m.getModello())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Gets   richiesta.
	 *
	 * @return   richiesta
	 */	
	public ArrayList<Richiesta> getRichiesta() {
		return richieste;
	}

	/**
	 *
	 * libera  macchina.
	 *
	 */
	public void liberaMacchina(Richiesta richiesta) {
		this.richieste.remove(richiesta);
	}

	/**
	 * Sets   richiesta.
	 *
	 * @param    new Richiesta
	 */
	public void addRichiesta(Richiesta richiesta) {
		this.richieste.add(richiesta);
	}
}