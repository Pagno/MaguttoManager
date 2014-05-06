package model;

import java.util.ArrayList;
import java.util.Iterator;


class ModelEscavatore extends ModelMacchina{
	private ArrayList<Escavatore> listaEscavatori;
	
	public ModelEscavatore(){
		listaEscavatori=new ArrayList<Escavatore>();
	}
	
	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		incrementaCodice();
		Escavatore escavatore= new Escavatore(getCodice(),produttore, Modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);
	}
	public void caricaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		aggiornaCodice(codice);
		Escavatore escavatore= new Escavatore(codice,produttore, Modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);
	}
	
	public void modificaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita) {
		Iterator<Escavatore> itr = listaEscavatori.iterator();
	    while (itr.hasNext()) {
	      if(itr.next().getCodice()==codice){;
	    	  itr.next().setProduttore(produttore);
	    	  itr.next().setModello(Modello);
	    	  itr.next().setCapacitaMassima(capacita);
	    	  itr.next().setPortataMassima( portata);
	    	  itr.next().setProfonditaMassima(profondita);
	    	  itr.next().setAltezzaMassima(altezza);
	      }
	    }
	}
	
	public boolean eliminaEscavatore(int codice){
		Iterator<Escavatore> itr = listaEscavatori.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().getCodice()==codice){
		    	 itr.remove();
		    	 return true;
	    	}
	    }
		return false;		
	}
	public ArrayList<Escavatore> getLista(){
		return listaEscavatori;
	}
}
