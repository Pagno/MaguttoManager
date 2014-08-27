package model.organizer.data;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *   Class Associazione.
 */
public class Associazione implements Cloneable {
	
	/**   id. */
	private int ID;
	
	/**   macchina. */
	private Macchina macchina;
	
	/**   lavoro. */
	private Lavoro lavoro;
	
	/**   data inizio. */
	private GregorianCalendar dataInizio;
	
	/**   data fine. */
	private GregorianCalendar dataFine;
	
	/**
	 * Instantiates a new associazione.
	 *
	 * @param ID id dell'Associazione
	 * @param macchina riferimento alla Macchina da Associare
	 * @param cantiere riferimento al Cantiere da Associare
	 * @param dataInizio data inizio
	 * @param dataFine data fine
	 */
	public Associazione(int ID, Macchina macchina,Lavoro lavoro, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.ID=ID;
		this.setMacchina(macchina);
		this.setLavoro(lavoro);
		this.setDataInizio(dataInizio);
		this.setDataFine(dataFine);
	}
	/**
	 * Gets   macchina.
	 *
	 * @return macchina
	 */
	public Macchina getMacchina() {
		return macchina;
	}

	/**
	 * Sets   macchina.
	 *
	 * @param macchina   new macchina
	 */
	public void setMacchina(Macchina macchina) {
		this.macchina = macchina;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Associazione clone(){
		return this;
	}
	
	/**
	 * Gets   id.
	 *
	 * @return   id
	 */
	public int getID(){
		return this.ID;
	}

	/**
	 * Gets   lavoro.
	 *
	 * @return   lavoro
	 */
	public Lavoro getLavoro() {
		return lavoro;
	}

	/**
	 * Sets   lavoro.
	 *
	 * @param lavoro   new lavoro
	 */
	public void setLavoro(Lavoro lav) {
		this.lavoro = lav;
	}

	/**
	 * Gets   data inizio.
	 *
	 * @return   data inizio
	 */
	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}
	
	/**
	 * Gets   str data inizio.
	 *
	 * @return   str data inizio
	 */
	public String getStrDataInizio() {
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataInizio.getTime());
	}

	/**
	 * Sets   data inizio.
	 *
	 * @param dataInizio   new data inizio
	 */
	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	/**
	 * Gets   data fine.
	 *
	 * @return   data fine
	 */
	public GregorianCalendar getDataFine() {
		return dataFine;
	}
	
	/**
	 * Gets   str data fine.
	 *
	 * @return   str data fine
	 */
	public String getStrDataFine() {

		
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataFine.getTime());
	}

	/**
	 * Sets   data fine.
	 *
	 * @param dataFine   new data fine
	 */
	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getID() + " " + this.getMacchina().getCodice() + " " + this.getLavoro().getCodice() + " " + this.getStrDataInizio() + " " + this.getStrDataFine();
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
		Associazione a=(Associazione)obj;
		if((this.ID==a.getID())&&(this.lavoro.equals(a.getLavoro()))&&(this.macchina.equals(a.getMacchina()))&&(this.dataInizio.equals(a.getDataInizio()))&&(this.dataFine.equals(a.getDataFine()))){
			return true;		
		}
		return false;
	}
}
