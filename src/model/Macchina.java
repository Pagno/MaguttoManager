package model;

abstract class Macchina {
	private String modello;
	private int codice;
	private String produttore;
	
	public Macchina(int codice,String produttore,String modello){
		this.codice=codice;
		this.produttore=produttore;
		this.modello=modello;
	}
	
	public int getCodice(){		return this.codice;	}
	public String getProduttore(){		return this.produttore;	}
	public String getModello(){		return this.modello;	}
	public void setProduttore(String produttore){		this.produttore=produttore;	}
	public void setModello(String Modello){		this.modello=Modello;	}
	
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj==null){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Macchina m=(Macchina)obj;
		if(this.codice==m.getCodice()){
			if(this.produttore.equals(m.getProduttore())){
				if(this.modello.equals(m.getModello())){
					return true;
				}
			}
		}
		return false;
	}
}