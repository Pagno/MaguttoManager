package model.organizer.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TreeSet;

// 
/**
 *   Class Macchina.
 */
public abstract class Macchina{
	
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
	
	/**
	 * Aggiunge una richiesta.
	 *
	 * @param r   richiesta da aggiungere
	 */
	public void aggiungiRichiesta(Richiesta r){
		elencoRichiesta.add(r);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
		result = prime * result
				+ ((elencoRichiesta == null) ? 0 : elencoRichiesta.hashCode());
		result = prime * result + ((modello == null) ? 0 : modello.hashCode());
		result = prime * result
				+ ((produttore == null) ? 0 : produttore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Macchina other = (Macchina) obj;
		if (codice != other.codice)
			return false;
		if (elencoRichiesta == null) {
			if (other.elencoRichiesta != null)
				return false;
		} else if (!elencoRichiesta.equals(other.elencoRichiesta))
			return false;
		if (modello == null) {
			if (other.modello != null)
				return false;
		} else if (!modello.equals(other.modello))
			return false;
		if (produttore == null) {
			if (other.produttore != null)
				return false;
		} else if (!produttore.equals(other.produttore))
			return false;
		return true;
	}

	/**
	 * Gets richiesta.
	 *
	 * @param codiceRichiesta il codice della richiesta desiderata
	 */
	public Richiesta getRichiesta(int codiceRichiesta){
		for(Richiesta item:elencoRichiesta){
			if(item.getCodice()==codiceRichiesta){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Delete richiesta.
	 *
	 * @param La richiesta da eliminare
	 */
	public void rimuoviRichiesta(Richiesta r){
		elencoRichiesta.remove(r);
	}
	
	/**
	 * Ritorna true se la macchina è libera nel periodo indicato dalle due date.
	 *
	 * @param inizio la data iniziale
	 * @param fine la data finale
	 */
	public boolean isLibera(GregorianCalendar inizio,GregorianCalendar fine){
		for(Richiesta richiesta:elencoRichiesta){
			if(!(fine.before(richiesta.getDataInizio()) || inizio.after(richiesta.getDataFine()))){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Elimina la macchina da tutte le richieste che la occupavano
	 */
	public void liberaRichieste(){
		ArrayList<Richiesta>list=new ArrayList<Richiesta>();
		for(Richiesta richiesta:elencoRichiesta){
			list.add(richiesta);
		}
		for(Richiesta item:list){
			item.setMacchina(null);
		}
	}
}
