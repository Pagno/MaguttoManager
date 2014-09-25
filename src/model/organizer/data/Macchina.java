package model.organizer.data;

import java.util.GregorianCalendar;
import java.util.TreeSet;

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
	private TreeSet<Richiesta> elencoRichiesta;

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
		elencoRichiesta=new TreeSet<Richiesta>();
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
	
	public void addRichiesta(Richiesta r){
		elencoRichiesta.add(r);
	}
	
	public void removeRichiesta(Richiesta r){
		elencoRichiesta.remove(r);
	}
	public boolean isFree(GregorianCalendar inizio,GregorianCalendar fine){
		//if(!(fine.before(lavoro.getDataInizio()) || inizio.after(lavoro.getDataFine())))
		for(Richiesta richiesta:elencoRichiesta){
			if(!(fine.before(richiesta.getDataInizio()) || inizio.after(richiesta.getDataFine())))
				return false;
		}
		
		return true;
	}
	
	public void liberaRichieste(){
		for(Richiesta richiesta:elencoRichiesta){
			richiesta.setMacchina(null);
		}
	}
}
