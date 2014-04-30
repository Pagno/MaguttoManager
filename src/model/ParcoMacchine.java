package model;

import java.util.ArrayList;
import java.util.Iterator;

class ParcoMacchine{
	
	private ArrayList<Macchina> listaMacchine;
	
	
	public ParcoMacchine(){
		listaMacchine=new ArrayList<Macchina>();
	}

	

	
	public void aggiungiRuspa(String produttore, String Modello,int capacita,int portata,int altezza){
		int codice=0;
		Ruspa ruspa= new Ruspa(codice, produttore, Modello,capacita,portata,altezza);
		listaMacchine.add(ruspa);
	}

	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		int codice=0;
		Escavatore escavatore= new Escavatore(codice,produttore, Modello,capacita,portata,altezza,profondita);
		listaMacchine.add(escavatore);
	}

	
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza){
		Iterator<Macchina> itr = listaMacchine.iterator();
	    while (itr.hasNext()) {
	      if(itr.next() instanceof Ruspa && itr.next().getCodice()==codice){
	    	  Ruspa g=(Ruspa)itr.next();
	    	  g.setProduttore(produttore);
	    	  g.setModello(Modello);
	    	  g.setCapacitaMassima(capacita);
	    	  g.setPortataMassima( portata);
	    	  g.setAltezzaMassima( altezza);
	      }
	    }
	}
	

	
	public void eliminaMacchina(int codice){
		Iterator<Macchina> itr = listaMacchine.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().getCodice()==codice){
		    	 itr.remove();
	    	}
	    }		
	}
	
	public ArrayList<Macchina> getListaMacchine(){
		return this.listaMacchine;
	}
}
