package model.organizer.data;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *   Class Cantiere.
 */
public class Cantiere extends DefaultMutableTreeNode  {
	
	/**   codiceCantiere. */
	private int codice;
		
	/**   nome cantiere. */
	private String nomeCantiere;
	
	/**   indirizzo. */
	private String indirizzo;
	
	/**   data apertura. */
	private GregorianCalendar dataApertura;
	
	/**   data chiusura. */
	private GregorianCalendar dataChiusura;
	
	/**   Elenco richieste macchine.  */
	private ArrayList<Lavoro> lavori;
	
	private Priorita priorita;
	
	/**
	 * Instantiates a new cantiere.
	 *
	 * @param codice codice del Cantiere
	 * @param nomeCantiere nome cantiere del Cantiere
	 * @param indirizzo indirizzo del Cantiere
	 * @param dataApertura data apertura del Cantiere
	 * @param dataChiusura data chiusura del Cantiere
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
	 * Aggiungi una nuova richiesta di macchina.
	 *
	 * @param Richiesta richiesta
	 */
	public void aggiungiLavoro(Lavoro r) {
		lavori.add(r);
	}
	
	/**
	 * Rimuove un lavoro dato il codice.
	 *
	 * @return true se il lavoro � stato rimosso.
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
	 * Rimuove tutti i lavori.
	 *
	 */
	public void svuotaLavori(){
		for(Lavoro l:lavori){
			l.svuotaRichieste();
		}
		lavori.clear();
	}
	
	/**
	 * Verifica se un certo lavoro � contenuto in questo cantiere
	 *
	 * @return true se contiene il lavoro
	 */
	public boolean hasLavoro(int codiceLavoro) {
		for(int i=0;i<lavori.size();i++){
			if(lavori.get(i).getCodice()==codiceLavoro)
				return true;
		}
		return false;
	}
	
	/**
	 * Restituisce il lavoro richiesto
	 *
	 * @return lavoro
	 */
	public Lavoro getLavoro(int codiceLavoro) {
		for(int i=0;i<lavori.size();i++){
			if(lavori.get(i).getCodice()==codiceLavoro)
				return lavori.get(i);
		}
		return null;
	}
	
	/**
	 * Gets elenco richieste.
	 *
	 * @return elenco richieste
	 */
	public ArrayList<Lavoro> getElencoLavori() {
		return lavori;
	}
	
	/**
	 * Gets nome cantiere.
	 *
	 * @return nome cantiere
	 */
	public String getNomeCantiere() {
		return nomeCantiere;
	}

	/**
	 * Sets nome cantiere.
	 *
	 * @param nomeCantiere new nome cantiere
	 */
	public void setNomeCantiere(String nomeCantiere) {
		this.nomeCantiere = nomeCantiere;
	}

	/**
	 * Gets indirizzo.
	 *
	 * @return indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Sets indirizzo.
	 *
	 * @param indirizzo new indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets   data apertura.
	 *
	 * @return   data apertura
	 */
	public GregorianCalendar getDataApertura() {
		return dataApertura;
	}
	
	/**
	 * Gets str data apertura.
	 *
	 * @return str data apertura
	 */
	public String getStrDataApertura() {

		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataApertura.getTime());
	}

	/**
	 * Sets data apertura.
	 *
	 * @param dataApertura new data apertura
	 */
	public void setDataApertura(GregorianCalendar dataApertura) {
		this.dataApertura = dataApertura;
	}

	/**
	 * Gets data chiusura.
	 *
	 * @return data chiusura
	 */
	public GregorianCalendar getDataChiusura() {
		return dataChiusura;
	}
	
	/**
	 * Gets str data chiusura.
	 *
	 * @return str data chiusura
	 */
	public String getStrDataChiusura() {
		


		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataChiusura.getTime());
	}

	/**
	 * Sets data chiusura.
	 *
	 * @param dataChiusura new data chiusura
	 */
	public void setDataChiusura(GregorianCalendar dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	/**
	 * Gets codice.
	 *
	 * @return codice
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Sets codice.
	 *
	 * @param codice new codice
	 */
	public void setCodice(Integer codice) {
		this.codice = codice;
	}

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
			priStr="Priorità Alta";
		}
		else if(priorita==Priorita.MEDIA){
			priStr="Priorità Media";
		}
		else{
			priStr="Priorità Bassa";
		}
		return this.getCodice() + " " + this.getNomeCantiere() + " " + this.getIndirizzo() + " " + this.getStrDataApertura() + " " + this.getStrDataChiusura() + " - " + priStr;
	}
	






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


	public Priorita getPriorita() {
		return priorita;
	}
	
	public String getPrioritaString() {
		return priorita.toString();
	}

	public void setPriorita(Priorita priorita) {
		this.priorita = priorita;
	}
	
}
