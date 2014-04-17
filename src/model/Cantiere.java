package model;
import java.util.*;
class Cantiere {
	private Integer codice;
	private String nomeCantiere;
	private String indirizzo;
	private Date dataApertura;
	private Date dataChiusura;

	public Cantiere(Integer codice,String nomeCantiere,String indirizzo,Date dataApertura,Date dataChiusura ){
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

	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	

}