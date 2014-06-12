package model;
import java.text.SimpleDateFormat;
import java.util.*;
// TODO: Auto-generated Javadoc

/**
 * The Class Cantiere.
 */
class Cantiere {
	
	/** The codice. */
	private int codice;
	
	/** The nome cantiere. */
	private String nomeCantiere;
	
	/** The indirizzo. */
	private String indirizzo;
	
	/** The data apertura. */
	private GregorianCalendar dataApertura;
	
	/** The data chiusura. */
	private GregorianCalendar dataChiusura;

	/**
	 * Instantiates a new cantiere.
	 *
	 * @param codice the codice
	 * @param nomeCantiere the nome cantiere
	 * @param indirizzo the indirizzo
	 * @param dataApertura the data apertura
	 * @param dataChiusura the data chiusura
	 */
	public Cantiere(int codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura ){
		this.setCodice(codice);
		this.nomeCantiere=nomeCantiere;
		this.indirizzo=indirizzo;
		this.dataApertura=dataApertura;
		this.dataChiusura=dataChiusura;
	}

	/**
	 * Gets the nome cantiere.
	 *
	 * @return the nome cantiere
	 */
	public String getNomeCantiere() {
		return nomeCantiere;
	}

	/**
	 * Sets the nome cantiere.
	 *
	 * @param nomeCantiere the new nome cantiere
	 */
	public void setNomeCantiere(String nomeCantiere) {
		this.nomeCantiere = nomeCantiere;
	}

	/**
	 * Gets the indirizzo.
	 *
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Sets the indirizzo.
	 *
	 * @param indirizzo the new indirizzo
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Gets the data apertura.
	 *
	 * @return the data apertura
	 */
	public GregorianCalendar getDataApertura() {
		return dataApertura;
	}
	
	/**
	 * Gets the str data apertura.
	 *
	 * @return the str data apertura
	 */
	public String getStrDataApertura() {

		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataApertura.getTime());
		
		/*
		String year = (new Integer(dataApertura.get(Calendar.YEAR))).toString();
		Integer temp=0;
		if(dataApertura.get(Calendar.MONTH)==Calendar.JANUARY){temp=1;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.FEBRUARY){temp=2;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.MARCH){temp=3;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.APRIL){temp=4;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.MAY){temp=5;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.JUNE){temp=6;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.JULY){temp=7;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.AUGUST){temp=8;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.SEPTEMBER){temp=9;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.OCTOBER){temp=10;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.NOVEMBER){temp=11;}
		else if(dataApertura.get(Calendar.MONTH)==Calendar.DECEMBER){temp=12;}
		String month = "0" + temp.toString();
		month = month.substring(month.length()-2, month.length());
		temp = new Integer(dataApertura.get(Calendar.DAY_OF_MONTH));
		String day = "0" + temp.toString();
		day = day.substring(day.length()-2, day.length());
		return year +"-"+ month +"-"+ day;
		*/
	}

	/**
	 * Sets the data apertura.
	 *
	 * @param dataApertura the new data apertura
	 */
	public void setDataApertura(GregorianCalendar dataApertura) {
		this.dataApertura = dataApertura;
	}

	/**
	 * Gets the data chiusura.
	 *
	 * @return the data chiusura
	 */
	public GregorianCalendar getDataChiusura() {
		return dataChiusura;
	}
	
	/**
	 * Gets the str data chiusura.
	 *
	 * @return the str data chiusura
	 */
	public String getStrDataChiusura() {
		


		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		return df.format(dataChiusura.getTime());
		/*
		String year = (new Integer(dataChiusura.get(Calendar.YEAR))).toString();
		Integer temp=0;
		if(dataChiusura.get(Calendar.MONTH)==Calendar.JANUARY){temp=1;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.FEBRUARY){temp=2;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.MARCH){temp=3;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.APRIL){temp=4;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.MAY){temp=5;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.JUNE){temp=6;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.JULY){temp=7;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.AUGUST){temp=8;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.SEPTEMBER){temp=9;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.OCTOBER){temp=10;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.NOVEMBER){temp=11;}
		else if(dataChiusura.get(Calendar.MONTH)==Calendar.DECEMBER){temp=12;}
		String month = "0" + temp.toString();
		month = month.substring(month.length()-2, month.length());
		temp = new Integer(dataChiusura.get(Calendar.DAY_OF_MONTH));
		String day = "0" + temp.toString();
		day = day.substring(day.length()-2, day.length());
		return year +"-"+ month +"-"+ day;*/
	}

	/**
	 * Sets the data chiusura.
	 *
	 * @param dataChiusura the new data chiusura
	 */
	public void setDataChiusura(GregorianCalendar dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	/**
	 * Gets the codice.
	 *
	 * @return the codice
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Sets the codice.
	 *
	 * @param codice the new codice
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
		if(this.codice==c.getCodice()){
			if(this.indirizzo.equals(c.getIndirizzo())){
				if(this.nomeCantiere.equals(c.getNomeCantiere())){
					if(this.dataApertura.equals(c.getDataApertura())){
						if(this.dataChiusura.equals(c.getDataChiusura())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}