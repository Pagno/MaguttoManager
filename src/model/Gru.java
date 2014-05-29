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
	public int getLunghezza(){		return this.lunghezza;	}
	public int getPortataMassima(){		return this.portataMax;	}
	public int getAltezza(){		return this.altezza;	}
	public int getAngoloRotazione(){		return this.angoloRotazione;	}
	
	
	//SET
	public void setLunghezzaMassima(int lunghezza){	this.lunghezza=lunghezza;	}
	public void setPortataMassima( int portata){		this.portataMax=portata;	}
	public void setAltezza(int altezza){		this.altezza=altezza;	}
	public void setAngoloRotazione(int rotazione){	this.angoloRotazione=rotazione;	}
	
	public String toString(){
		return this.getCodice() + " " + this.getProduttore() + " " + this.getModello() + " " + this.getLunghezza() + " " + this.getPortataMassima() + " " + this.getAltezza() + " " + this.getAngoloRotazione();
	}
	
	public boolean equals(Object obj){
		if(!(super.equals(obj))){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Gru g=(Gru)obj;
		if((this.angoloRotazione==g.getAngoloRotazione()) &&(this.portataMax==g.getPortataMassima())&&(this.lunghezza==g.getLunghezza())&&(this.altezza==g.getAltezza())){
			return true;
		}
		else return false;
	}
	
}