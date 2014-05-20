package model;

import java.util.ArrayList;


class ModelEscavatore extends ModelMacchina{
	private ArrayList<Escavatore> listaEscavatori;

	public ModelEscavatore(){
		listaEscavatori=new ArrayList<Escavatore>();
	}

	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		incrementaCodice();
		Escavatore escavatore= new Escavatore(getCodice(),produttore, Modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);
		
		Object[] v1={escavatore.getCodice(),produttore,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	void caricaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		aggiornaCodice(codice);
		Escavatore escavatore= new Escavatore(codice,produttore, Modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);
		

		Object[] v1={escavatore.getCodice(),produttore,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	public void modificaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita) {
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(Modello);
				item.setCapacitaMassima(capacita);
				item.setPortataMassima( portata);
				item.setProfonditaMassima(profondita);
				item.setAltezzaMassima(altezza);
			}
		}
	}

	public boolean eliminaEscavatore(int codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				return listaEscavatori.remove(item);
			}
		}
		return false;		
	}

	public ArrayList<Escavatore> getLista(){
		return listaEscavatori;
	}

	public boolean isEscavatore(Integer codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	public Escavatore getEscavatore(Integer codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}

	public String toString(){
		String tmp = "";
		for(Escavatore item:listaEscavatori){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
}
