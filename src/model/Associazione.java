package model;
import java.util.*;


public class Associazione implements Cloneable {
	private Integer ID;
	private Macchina macchina;
	private Cantiere cantiere;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	
	public Associazione(Integer ID, Macchina macchina,Cantiere cantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		this.ID=ID;
		this.setMacchina(macchina);
		this.setCantiere(cantiere);
		this.setDataInizio(dataInizio);
		this.setDataFine(dataFine);
	}

	public Macchina getMacchina() {
		return macchina;
	}

	public void setMacchina(Macchina macchina) {
		this.macchina = macchina;
	}
	
	public Associazione clone(){
		return this;
	}
	
	public Integer getID(){
		return this.ID;
	}

	public Cantiere getCantiere() {
		return cantiere;
	}

	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}
	
	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}
	
	public String getStrDataInizio() {
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
		return year +"-"+ month +"-"+ day;
	}

	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	public GregorianCalendar getDataFine() {
		return dataFine;
	}
	
	public String getStrDataFine() {
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
		return year +"-"+ month +"-"+ day;
	}

	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}
	
	public String toString(){
		return this.getID() + " " + this.getMacchina().getCodice() + " " + this.getCantiere().getCodice() + " " + this.getStrDataInizio() + " " + this.getStrDataFine();
	}
	
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
		if(this.ID==a.getID()){
			if(this.cantiere.equals(a.getCantiere())){
				if(this.macchina.equals(a.getMacchina())){
					if(this.dataInizio.equals(a.getDataInizio())){
						if(this.dataFine.equals(a.getDataFine())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
