package model.organizer.data;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Lavoro {
	private int codice;
	private String nome;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private Cantiere cantiere;
	
	
	public Lavoro(int codice, String nome, GregorianCalendar dataInizio,
			GregorianCalendar dataFine, Cantiere cantiere) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.cantiere = cantiere;
	}


	public int getCodice() {
		return codice;
	}


	public void setCodice(int codice) {
		this.codice = codice;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public GregorianCalendar getDataInizio() {
		return dataInizio;
	}


	public void setDataInizio(GregorianCalendar dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getStrDataInizio() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataInizio.getTime());
		
	}

	public GregorianCalendar getDataFine() {
		return dataFine;
	}

	public String getStrDataFine() {

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyy-MM-dd");

		return df.format(dataFine.getTime());
		
	}


	public void setDataFine(GregorianCalendar dataFine) {
		this.dataFine = dataFine;
	}


	public Cantiere getCantiere() {
		return cantiere;
	}


	public void setCantiere(Cantiere cantiere) {
		this.cantiere = cantiere;
	}


	@Override
	public String toString() {
		return  codice + " " + nome + " "
				+ getStrDataInizio() + " " + getStrDataFine() + " "
				+ cantiere;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lavoro other = (Lavoro) obj;
		if (cantiere == null) {
			if (other.cantiere != null)
				return false;
		} else if (!cantiere.equals(other.cantiere))
			return false;
		if (codice != other.codice)
			return false;
		if (dataFine == null) {
			if (other.dataFine != null)
				return false;
		} else if (!dataFine.equals(other.dataFine))
			return false;
		if (dataInizio == null) {
			if (other.dataInizio != null)
				return false;
		} else if (!dataInizio.equals(other.dataInizio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

	
}
