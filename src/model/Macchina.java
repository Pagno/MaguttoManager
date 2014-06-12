package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Macchina.
 */
public abstract class Macchina {
	
	/** The modello. */
	private String modello;
	
	/** The codice. */
	private int codice;
	
	/** The produttore. */
	private String produttore;
	
	/**
	 * Instantiates a new macchina.
	 *
	 * @param codice the codice
	 * @param produttore the produttore
	 * @param modello the modello
	 */
	public Macchina(int codice,String produttore,String modello){
		this.codice=codice;
		this.produttore=produttore;
		this.modello=modello;
	}
	
	/**
	 * Gets the codice.
	 *
	 * @return the codice
	 */
	public int getCodice(){		return this.codice;	}
	
	/**
	 * Gets the produttore.
	 *
	 * @return the produttore
	 */
	public String getProduttore(){		return this.produttore;	}
	
	/**
	 * Gets the modello.
	 *
	 * @return the modello
	 */
	public String getModello(){		return this.modello;	}
	
	/**
	 * Sets the produttore.
	 *
	 * @param produttore the new produttore
	 */
	public void setProduttore(String produttore){		this.produttore=produttore;	}
	
	/**
	 * Sets the modello.
	 *
	 * @param Modello the new modello
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
}