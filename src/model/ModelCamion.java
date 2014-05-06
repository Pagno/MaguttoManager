package model;

import java.util.ArrayList;
import java.util.Iterator;


class ModelCamion {
	private ArrayList<Camion> listaCamion;
	
	public ModelCamion(){
		listaCamion=new ArrayList<Camion>();
	}
	
	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza){
		int codice=0;
		Camion cm= new Camion(codice,produttore,Modello,capacita,portata,lunghezza);
		listaCamion.add(cm);
	}
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza){
		Iterator<Camion> itr = listaCamion.iterator();
	    while (itr.hasNext()) {
	      if(itr.next().getCodice()==codice){
	    	  itr.next().setProduttore(produttore);
	    	  itr.next().setModello(Modello);
	    	  itr.next().setCapacitaMassima(capacita);
	    	  itr.next().setPortataMassima( portata);
	    	  itr.next().setLunghezza(lunghezza);
	      }
	    }
	}
	public boolean eliminaCamion(int codice){
		Iterator<Camion> itr = listaCamion.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().getCodice()==codice){
		    	 itr.remove();
		    	 return true;
	    	}
	    }
		return false;		
	}
}
