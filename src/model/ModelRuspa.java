package model;

import java.util.ArrayList;
import java.util.Iterator;


class ModelRuspa {
	private ArrayList<Ruspa> listaRuspe;
	
	
	public ModelRuspa(){
		listaRuspe=new ArrayList<Ruspa>();
	}
	
	
	public void aggiungiRuspa(String produttore, String Modello,int capacita,int portata,int altezza){
		int codice=0;
		Ruspa ruspa= new Ruspa(codice, produttore, Modello,capacita,portata,altezza);
		listaRuspe.add(ruspa);
	}
	
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza){
		Iterator<Ruspa> itr = listaRuspe.iterator();
	    while (itr.hasNext()) {
	      if(itr.next().getCodice()==codice){
	    	  itr.next().setProduttore(produttore);
	    	  itr.next().setModello(Modello);
	    	  itr.next().setCapacitaMassima(capacita);
	    	  itr.next().setPortataMassima(portata);
	    	  itr.next().setAltezzaMassima(altezza);
	      }
	    }
	}
	
	public boolean eliminaRuspa(int codice){
		Iterator<Ruspa> itr = listaRuspe.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().getCodice()==codice){
		    	 itr.remove();
		    	 return true;
	    	}
	    }
		return false;		
	}
}
