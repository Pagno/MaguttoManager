package model;

class Camion extends Macchina {
	private int capacitaMax;
	private int portataMax;
	private int lunghezza;

	public Camion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza){
		super(codice,produttore,Modello);
		this.capacitaMax=capacita;
		this.portataMax=portata;
		this.lunghezza=lunghezza;
		
	}
	
	
	//GET
	public int getCapacitaMassima(){	return this.capacitaMax; }
	public int getPortataMassima(){		return this.portataMax;	}
	public int getLunghezzaMassima(){		return this.lunghezza;	}
	
	
	//SET
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	public void setLunghezzaMassima(int lunghezza){		this.lunghezza=lunghezza;	}

}
