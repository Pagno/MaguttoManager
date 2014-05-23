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
	public int getLunghezza(){		return this.lunghezza;	}
	
	
	//SET
	public void setCapacitaMassima(int capacita){	this.capacitaMax=capacita;	}
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	public void setLunghezza(int lunghezza){		this.lunghezza=lunghezza;	}
	
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getCapacitaMassima() + " " + this.getPortataMassima() + " " + this.getLunghezza();
	}
	
	public boolean equals(Object obj){
		if(!(super.equals(obj))){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Camion c=(Camion)obj;
		if((this.capacitaMax==c.getCapacitaMassima()) &&(this.portataMax==c.getPortataMassima())&&(this.lunghezza==c.getLunghezza())){
			return true;
		}
		else return false;
	}

}