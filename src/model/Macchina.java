package model;

abstract class Macchina {
	private String modello;
	private int codice;
	private String produttore;
	
	public Macchina(int codice,String produttore,String Modello){
		this.codice=codice;
		this.produttore=produttore;
		this.modello=Modello;
	}
	
	public int getCodice(){		return this.codice;	}
	public String getProduttore(){		return this.produttore;	}
	public String getModello(){		return this.modello;	}
	public void setProduttore(String produttore){		this.produttore=produttore;	}
	public void setModello(String Modello){		this.modello=Modello;	}
	//PROVA
}