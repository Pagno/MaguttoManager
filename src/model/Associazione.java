package model;
import java.text.SimpleDateFormat;
import java.util.*;


// TODO: Auto-generated Javadoc
/**
 *   Class Associazione.
 */
public class Associazione implements Cloneable {
	
	/**   id. */
	private int ID;
	
	/**   macchina. */
	private Macchina macchina;
	
	/**   cantiere. */
	private Cantiere cantiere;
	
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
	public Associazione(int ID, Macchina macchina,Cantiere cantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.ID=ID;
		this.setMacchina(macchina);
		this.setCantiere(cantiere);
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
	 * Gets   cantiere.
	 *
	 * @return   cantiere
	 */
	public Cantiere getCantiere() {
		return cantiere;
	}

	/**
	 * Sets   cantiere.
	 *
	 * @param cantiere   new cantiere
	 */
	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
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
		/*System.out.println(data);
		String year = (new Integer(dataInizio.get(Calendar.YEAR))).toString();
		Integer temp=0;
		if(dataInizio.get(Calendar.MONTH)==Calendar.JANUARY){temp=1;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.FEBRUARY){temp=2;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.MARCH){temp=3;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.APRIL){temp=4;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.MAY){temp=5;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.JUNE){temp=6;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.JULY){temp=7;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.AUGUST){temp=8;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.SEPTEMBER){temp=9;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.OCTOBER){temp=10;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.NOVEMBER){temp=11;}
		else if(dataInizio.get(Calendar.MONTH)==Calendar.DECEMBER){temp=12;}
		String month = "0" + temp.toString();
		month = month.substring(month.length()-2, month.length());
		temp = new Integer(dataInizio.get(Calendar.DAY_OF_MONTH));
		String day = "0" + temp.toString();
		day = day.substring(day.length()-2, day.length());
		return year +"-"+ month +"-"+ day;*/
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
		
		
		/*
		String year = (new Integer(dataFine.get(Calendar.YEAR))).toString();
		Integer temp=0;
		if(dataFine.get(Calendar.MONTH)==Calendar.JANUARY){temp=1;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.FEBRUARY){temp=2;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.MARCH){temp=3;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.APRIL){temp=4;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.MAY){temp=5;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.JUNE){temp=6;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.JULY){temp=7;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.AUGUST){temp=8;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.SEPTEMBER){temp=9;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.OCTOBER){temp=10;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.NOVEMBER){temp=11;}
		else if(dataFine.get(Calendar.MONTH)==Calendar.DECEMBER){temp=12;}
		String month = "0" + temp.toString();
		month = month.substring(month.length()-2, month.length());
		temp = new Integer(dataFine.get(Calendar.DAY_OF_MONTH));
		String day = "0" + temp.toString();
		day = day.substring(day.length()-2, day.length());
		return year +"-"+ month +"-"+ day;*/
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
		return this.getID() + " " + this.getMacchina().getCodice() + " " + this.getCantiere().getCodice() + " " + this.getStrDataInizio() + " " + this.getStrDataFine();
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
		if((this.ID==a.getID())&&(this.cantiere.equals(a.getCantiere()))&&(this.macchina.equals(a.getMacchina()))&&(this.dataInizio.equals(a.getDataInizio()))&&(this.dataFine.equals(a.getDataFine()))){
			return true;		
		}
		return false;
	}
}
