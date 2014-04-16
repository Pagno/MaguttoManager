package model;

public class Gru extends Macchina{
	private int lunghezza;
	private int altezza;
	private int portataMax;
	private int angoloRotazione;
	
	public Gru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		super(codice,produttore,modello);

		this.altezza=altezza;
		this.angoloRotazione=rotazione;
		this.portataMax=portata;
		this.lunghezza=lunghezza;
	}
	
	//GET
	public int getLunghezzaMassima(){		return this.lunghezza;	}
	public int getPortataMassima(){		return this.portataMax;	}
	public int getAltezzaMassima(){		return this.altezza;	}
	public int getAngoloRotazione(){		return this.angoloRotazione;	}
	
	
	//SET
	public void setLunghezzaMassima(int lunghezza){	this.lunghezza=lunghezza;	}
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	public void setAltezzaMassima(int altezza){		this.altezza=altezza;	}
	public void setAngoloRotazione(int rotazione){	this.angoloRotazione=rotazione;	}
	
}