package model;
import java.util.*;
class Cantiere {
	private String nomeCantiere;
	private String indirizzo;
	private Date dataApertura;
	private Date dataChiusura;

	public Cantiere(String nomeCantiere,String indirizzo,Date dataApertura,Date dataChiusura ){
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

	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public Date getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}



}