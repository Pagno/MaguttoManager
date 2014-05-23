package model;

class Escavatore extends Macchina{
	private int capacitaMax;
	private int profonditaMax;
	private int portataMax;
	private int altezzaMax;
	
	
	public Escavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita) {
		super(codice, produttore, Modello);
	
		this.capacitaMax=capacita;
		this.altezzaMax=altezza;
		this.portataMax=portata;
		this.profonditaMax=profondita;
		
	}
	
	//GET
	public int getCapacitaMassima(){		return this.capacitaMax;	}
	public int getPortataMassima(){		return this.portataMax;	}
	public int getAltezzaMassima(){		return this.altezzaMax;	}
	public int getProfonditaMassima(){		return this.profonditaMax;	}
	
	
	//SET
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	public void setProfonditaMassima(int profondita){		this.profonditaMax=profondita;	}
	public void setAltezzaMassima(int altezza){		this.altezzaMax=altezza;	}
	
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getAltezzaMassima() + " " + this.getProfonditaMassima();
	}
	
	public boolean equals(Object obj){
		if(!(super.equals(obj))){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Escavatore e=(Escavatore)obj;
		if((this.profonditaMax==e.getProfonditaMassima()) &&(this.portataMax==e.getPortataMassima())&&(this.capacitaMax==e.getCapacitaMassima())&&(this.altezzaMax==e.getAltezzaMassima())){
			return true;
		}
		else return false;
	}
}