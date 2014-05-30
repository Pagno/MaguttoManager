package model;
import java.text.SimpleDateFormat;
import java.util.*;
class Cantiere {
	private int codice;
	private String nomeCantiere;
	private String indirizzo;
	private GregorianCalendar dataApertura;
	private GregorianCalendar dataChiusura;

	public Cantiere(int codice,String nomeCantiere,String indirizzo,GregorianCalendar dataApertura,GregorianCalendar dataChiusura ){
		this.setCodice(codice);
		this.nomeCantiere=nomeCantiere;
		this.indirizzo=indirizzo;
		this.dataApertura=dataApertura;
		this.dataChiusura=dataChiusura;
	}

	public String getNomeCantiere() {
		return nomeCantiere;
	}

	public void setNomeCantiere(String nomeCantiere) {
		this.nomeCantiere = nomeCantiere;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public GregorianCalendar getDataApertura() {
		return dataApertura;
	}
	
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

	public void setDataApertura(GregorianCalendar dataApertura) {
		this.dataApertura = dataApertura;
	}

	public GregorianCalendar getDataChiusura() {
		return dataChiusura;
	}
	
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

	public void setDataChiusura(GregorianCalendar dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	public String toString(){
		return this.getCodice() + " " + this.getNomeCantiere() + " " + this.getIndirizzo() + " " + this.getStrDataApertura() + " " + this.getStrDataChiusura();
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