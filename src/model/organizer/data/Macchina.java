package model.organizer.data;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TreeSet;

/**
 *   La classe Macchina rappresenta le caratteristiche comuni di tutte le tipologie di macchine.
 *   Permette di gestire le tre caratteristiche fondamentali, cioè il modello della macchina, il codice ad essa associato 
 *   e il produttore della macchina.
 *   <p>
 *   Poich&eacute; tutte le macchine possono soddisfare delle richieste, l'elenco delle richieste viene gestito attraverso
 *   la classe padre.
 *   <p>
 *   Essendo una classe astratta, non &egrave; possibile creare delle istanze della classe macchina, ma è necessario 
 *   utilizzare una delle classi figlie che la specializzano.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public abstract class Macchina{
	
	/**   Modello della macchina. 		*/
	private String modello;
	
	/**   Codice della macchina. 		*/
	private int codice;
	
	/**   Produttore della macchina. 	*/
	private String produttore;
	

	/**	  Elenco delle richieste soddisfatte dalla macchina. 	*/
	private TreeSet<Richiesta> elencoRichiesta;

	/**
	 * Istanzia una nuova macchina.
	 *
	 * @param codice Il codice della macchina
	 * @param produttore Il produttore della macchina
	 * @param modello Il modello della macchina
	 */
	public Macchina(int codice,String produttore,String modello){
		this.codice=codice;
		this.produttore=produttore;
		this.modello=modello;
		elencoRichiesta=new TreeSet<Richiesta>();
	}
	
	/**
	 * Restituisce il codice della macchina.
	 *
	 * @return Il codice della macchina
	 */
	public int getCodice(){		return this.codice;	}
	
	/**
	 * Restituisce il produttore della macchina.
	 *
	 * @return Il produttore della macchina
	 */
	public String getProduttore(){		return this.produttore;	}
	
	/**
	 * Restituisce il modello della macchina.
	 *
	 * @return Il modello della macchina
	 */
	public String getModello(){		return this.modello;	}
	
	/**
	 * Modifica il produttore della macchina.
	 *
	 * @param produttore Il nuovo produttore della macchina
	 */
	public void setProduttore(String produttore){		this.produttore=produttore;	}
	
	/**
	 * Modifica il modello della macchina.
	 *
	 * @param modello Il nuovo modello della macchina
	 */
	public void setModello(String modello){		this.modello=modello;	}
	
	/**
	 * Aggiunge una richiesta all'elenco delle soddisfatte da questa macchinaq.
	 *
	 * @param r La richiesta da aggiungere
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
	 * Restituisce la richiesta cui appartiene il codice indicato.
	 *
	 * @param codiceRichiesta Il codice della richiesta desiderata
	 * @return La richiesta desiderata se essa è soddisfatta da questa macchina, altrimenti null
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
	 * Cancella la richiesta dall'elenco delle soddisfatte.
	 *
	 * @param La richiesta da eliminare
	 */
	public void rimuoviRichiesta(Richiesta r){
		elencoRichiesta.remove(r);
	}
	
	/**
	 * Verifica se la macchina è libera nel periodo indicato dalle due date.
	 *
	 * @param inizio La data iniziale del periodo da verificare
	 * @param fine La data finale del periodo da verificare
	 * @return true se la macchina non è associata ad alcuna richiesta in questo periodo
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
