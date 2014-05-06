package model;

import java.util.ArrayList;
import java.util.Iterator;


class ModelEscavatore {
	private ArrayList<Escavatore> listaEscavatori;
	
	public ModelEscavatore(){
		listaEscavatori=new ArrayList<Escavatore>();
	}
	
	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		int codice=0;
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
}
