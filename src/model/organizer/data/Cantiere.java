package model.organizer.data;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *   Questa classe rappresenta un cantiere. Permette di gestire le caratteristiche principali cioè il codice del cantiere, 
 *   il nome del cantiere, il suo indirizzo, le date di apertura e chiusura del cantiere e la sua priorit&agrave;.
 *   <p>
 *   Ogni istanza contiene l'elenco dei lavori associati al particolare cantiere.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class Cantiere extends DefaultMutableTreeNode  {
	
	/**   Codice del cantiere. */
	private int codice;
		
	/**   Nome del cantiere. */
	private String nomeCantiere;
	
	/**   Indirizzo. */
	private String indirizzo;
	
	/**   Data di apertura. */
	private GregorianCalendar dataApertura;
	
	/**   Data di chiusura. */
	private GregorianCalendar dataChiusura;
	
	/**   Elenco dei lavori collegati al cantiere.  */
	private ArrayList<Lavoro> lavori;
	
	/**   Priorit&agrave; del cantiere.  */
	private Priorita priorita;
	
	/**
	 * Istanzia un nuovo cantiere.
	 *
	 * @param codice Il codice del cantiere
	 * @param nomeCantiere Il nome del cantiere
	 * @param indirizzo L'indirizzo del cantiere
	 * @param dataApertura La data di apertura del cantiere
	 * @param dataChiusura La data di chiusura del cantiere
	 * @param priorita the priorita
	 */
	public Cantiere(int codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura,Priorita priorita ){
		super(null);
		this.setCodice(codice);
		this.priorita=priorita;
		this.nomeCantiere=nomeCantiere;
		this.indirizzo=indirizzo;
		this.dataApertura=dataApertura;
		this.dataChiusura=dataChiusura;
		lavori=new ArrayList<Lavoro>();
	}

	
	/**
	 * Associa un nuovo lavoro al cantiere.
	 *
	 * @param l Il lavoro da associare al cantiere
	 */
	public void aggiungiLavoro(Lavoro l) {
		lavori.add(l);
	}
	
	/**
	 * Rimuove un lavoro dato il suo codice.
	 *
	 * @param codiceLavoro Il codice del lavoro da rimuovere
	 * @return true se il lavoro &egrave; stato rimosso
	 */
	public boolean rimuoviLavoro(int codiceLavoro) {
		for(int i=0;i<lavori.size();i++){
			if(lavori.get(i).getCodice()==codiceLavoro){
				lavori.get(i).svuotaRichieste();
				lavori.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Rimuove tutti i lavori associati a questo cantiere.
	 *
	 */
	public void svuotaLavori(){
		for(Lavoro l:lavori){
			l.svuotaRichieste();
		}
		lavori.clear();
	}
	
	/**
	 * Verifica se un certo lavoro &egrave; associato a questo cantiere.
	 *
	 * @param codiceLavoro Il codice del lavoro
	 * @return true se il lavoro è associato a questo cantiere
	 */
	public boolean hasLavoro(int codiceLavoro) {
		for(int i=0;i<lavori.size();i++){
			if(lavori.get(i).getCodice()==codiceLavoro)
				return true;
		}
		return false;
	}
	
	/**
	 * Restituisce il lavoro richiesto.
	 *
	 * @param codiceLavoro Il codice del lavoro richiesto
	 * @return Il lavoro richiesto
	 */
	public Lavoro getLavoro(int codiceLavoro) {
		for(int i=0;i<lavori.size();i++){
			if(lavori.get(i).getCodice()==codiceLavoro)
				return lavori.get(i);
		}
		return null;
	}
	
	/**
	 * Restituisce l'elenco dei lavori.
	 *
	 * @return L'elenco dei lavori associati al cantiere
	 */
	public ArrayList<Lavoro> getElencoLavori() {
		return lavori;
	}
	
	/**
	 * Restituisce il nome del cantiere.
	 *
	 * @return Il nome del cantiere
	 */
	public String getNomeCantiere() {
		return nomeCantiere;
	}

	/**
	 * Modifica il nome del cantiere.
	 *
	 * @param nomeCantiere Il nuovo nome del cantiere
	 */
	public void setNomeCantiere(String nomeCantiere) {
		this.nomeCantiere = nomeCantiere;
	}

	/**
	 * Restituisce l'indirizzo del cantiere.
	 *
	 * @return L'indirizzo del cantiere
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Modifica l'indirizzo del cantiere.
	 *
	 * @param indirizzo Il nuovo indirizzo del cantiere
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Restituisce la data di apertura del cantiere.
	 *
	 * @return La data di apertura del cantiere
	 */
	public GregorianCalendar getDataApertura() {
		return dataApertura;
	}
	
	/**
	 * Restituisce una stringa rappresentante la data di apertura del cantiere.
	 *
	 * @return La data di apertura sotto forma di stringa
	 */
	public String getStrDataApertura() {

		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataApertura.getTime());
	}

	/**
	 * Modifica la data di apertura.
	 *
	 * @param dataApertura La nuova data di apertura
	 */
	public void setDataApertura(GregorianCalendar dataApertura) {
		this.dataApertura = dataApertura;
	}

	/**
	 * Restituisce la data di chiusura del cantiere.
	 *
	 * @return La data di chiusura del cantiere
	 */
	public GregorianCalendar getDataChiusura() {
		return dataChiusura;
	}
	
	/**
	 * Restituisce una stringa rappresentante la data di chiusura del cantiere.
	 *
	 * @return La data di chiusura sotto forma di stringa
	 */
	public String getStrDataChiusura() {
		


		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataChiusura.getTime());
	}

	/**
	 * Modifica la data di chiusura.
	 *
	 * @param dataChiusura La nuova data di chiusura
	 */
	public void setDataChiusura(GregorianCalendar dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	/**
	 * Restituisce il codice del cantiere.
	 *
	 * @return codice
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Modifica il codice del cantiere.
	 *
	 * @param codice Il nuovo codice del cantiere
	 */
	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	/**
	 * Verifica se il cantiere ha dei lavori scoperti.
	 *
	 * @return true se almeno uno dei lavori possiede delle richieste scoperte
	 */
	public boolean isScoperto(){
		for(Lavoro lav:lavori){
			if(lav.isScoperto()){
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String priStr;
		if(priorita==Priorita.ALTA){
			priStr="Priorit\u00E0 Alta";
		}
		else if(priorita==Priorita.MEDIA){
			priStr="Priorit\u00E0 Media";
		}
		else{
			priStr="Priorit\u00E0 Bassa";
		}
		return this.getCodice() + " " + this.getNomeCantiere() + " " + this.getIndirizzo() + " " + this.getStrDataApertura() + " " + this.getStrDataChiusura() + " - " + priStr;
	}
	






	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cantiere))
			return false;
		Cantiere other = (Cantiere) obj;
		if (codice != other.codice)
			return false;
		if (dataApertura == null) {
			if (other.dataApertura != null)
				return false;
		} else if (!dataApertura.equals(other.dataApertura))
			return false;
		if (dataChiusura == null) {
			if (other.dataChiusura != null)
				return false;
		} else if (!dataChiusura.equals(other.dataChiusura))
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (!lavori.equals(other.lavori))
			return false;
		if (nomeCantiere == null) {
			if (other.nomeCantiere != null)
				return false;
		} else if (!nomeCantiere.equals(other.nomeCantiere))
			return false;
		if (priorita != other.priorita)
			return false;
		return true;
	}


	/**
	 * Restituisce la priorit&agrave; del cantiere.
	 *
	 * @return La priorit&agrave; del cantiere
	 */
	public Priorita getPriorita() {
		return priorita;
	}
	
	/**
	 * Restituisce la priorit&agrave; del cantiere sotto forma di stringa.
	 *
	 * @return Stringa rappresentante la priorit&agrave; del cantiere
	 */
	public String getPrioritaString() {
		return priorita.toString();
	}

	/**
	 * Modifica la priorit&agrave; del cantiere.
	 *
	 * @param priorita La nuova priorit&agrave;
	 */
	public void setPriorita(Priorita priorita) {
		this.priorita = priorita;
	}
	
}
