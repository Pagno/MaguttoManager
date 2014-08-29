package model.organizer.data;
import java.text.SimpleDateFormat;
import java.util.*;
// 

/**
 *   Class Cantiere.
 */
public class Cantiere {
	
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
	
	/**
	 * Instantiates a new cantiere.
	 *
	 * @param codice codice del Cantiere
	 * @param nomeCantiere nome cantiere del Cantiere
	 * @param indirizzo indirizzo del Cantiere
	 * @param dataApertura data apertura del Cantiere
	 * @param dataChiusura data chiusura del Cantiere
	 */
	public Cantiere(int codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura ){
		this.setCodice(codice);
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
	public void addLavoro(Lavoro r) {
		lavori.add(r);
	}
	
	/**
	 * Rimuove un lavoro dato il codice.
	 *
	 * @return true se il lavoro è stato rimosso.
	 */
	public boolean rimuoviLavoro(int codiceLavoro) {
		for(int i=0;i<lavori.size();i++){
			if(lavori.get(i).getCodice()==codiceLavoro)
				lavori.remove(i);
		}
		return false;
	}
	
	/**
	 * Verifica se un certo lavoro è contenuto in questo cantiere
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
		if(lavori.size()==0){
			return null;
		}
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getCodice() + " " + this.getNomeCantiere() + " " + this.getIndirizzo() + " " + this.getStrDataApertura() + " " + this.getStrDataChiusura();
	}
	
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
		Cantiere c=(Cantiere)obj;
		if((this.codice==c.getCodice())&&(this.indirizzo.equals(c.getIndirizzo()))&&(this.nomeCantiere.equals(c.getNomeCantiere()))&&(this.dataApertura.equals(c.getDataApertura()))&&(this.dataChiusura.equals(c.getDataChiusura()))){
			return true;
		}
		return false;
	}

}