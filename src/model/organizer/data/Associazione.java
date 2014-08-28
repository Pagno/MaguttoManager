package model.organizer.data;


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
	

	
	/**
	 * Instantiates a new associazione.
	 *
	 * @param ID id dell'Associazione
	 * @param macchina riferimento alla Macchina da Associare
	 * @param cantiere riferimento al Cantiere da Associare
	 * @param dataInizio data inizio
	 * @param dataFine data fine
	 */
	public Associazione(int ID, Macchina macchina,Lavoro lavoro){
		this.setMacchina(macchina);
		this.setLavoro(lavoro);
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


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getID() + " " + this.getMacchina().getCodice() + " " + this.getLavoro().getCodice() ;
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
		if((this.ID==a.getID())&&(this.lavoro.equals(a.getLavoro()))&&(this.macchina.equals(a.getMacchina()))){
			return true;		
		}
		return false;
	}
}
