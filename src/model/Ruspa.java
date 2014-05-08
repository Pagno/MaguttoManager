package model;

class Ruspa extends Macchina{
	private int capacitaMax;
	private int portataMax;
	private int altezzaMax;


	public Ruspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza) {
		super(codice, produttore, Modello);

		this.capacitaMax=capacita;
		this.altezzaMax=altezza;
		this.portataMax=portata;
	}
	
	//GET
	public int getCapacitaMassima(){	return this.capacitaMax; }
	public int getPortataMassima(){		return this.portataMax;	}
	public int getAltezzaMassima(){		return this.altezzaMax;	}
	
	
	//SET
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	public void setAltezzaMassima(int altezza){		this.altezzaMax=altezza;	}
	
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getAltezzaMassima();
	}
	
}
