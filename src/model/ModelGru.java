package model;

import java.util.ArrayList;
import java.util.Iterator;

class ModelGru {
	private ArrayList<Gru> listaGru;
	
	public ModelGru(){
		listaGru=new ArrayList<Gru>();
	}
	
	
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		int codice = 0;
		Gru gru=new Gru(codice,produttore,modello, rotazione, portata,lunghezza,altezza);
		listaGru.add(gru);
	}
	
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		Iterator<Gru> itr = listaGru.iterator();
	    while (itr.hasNext()) {
	      if(itr.next().getCodice()==codice){
	    	  itr.next();
	    	  itr.next().setProduttore(produttore);
	    	  itr.next().setModello(modello);
	    	  itr.next().setLunghezzaMassima(lunghezza);
	    	  itr.next().setPortataMassima( portata);
	    	  itr.next().setAltezzaMassima( altezza);
	    	  itr.next().setAngoloRotazione(rotazione);
	      }
	    }
	}
	
	public boolean eliminaGru(int codice){
		Iterator<Gru> itr = listaGru.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().getCodice()==codice){
		    	 itr.remove();
		    	 return true;
	    	}
	    }
		return false;		
	}
}
