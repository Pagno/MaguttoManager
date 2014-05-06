package model;
import java.util.*;


class Associazione implements Cloneable {
	private Integer ID;
	private Macchina macchina;
	private Cantiere cantiere;
	private Date dataInizio;
	private Date dataFine;
	
	public Associazione(Integer ID, Macchina macchina,Cantiere cantiere, Date dataInizio, Date dataFine){
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
	
	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
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
}