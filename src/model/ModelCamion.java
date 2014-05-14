package model;

import java.util.ArrayList;
import java.util.Iterator;


class ModelCamion extends ModelMacchina{
	private ArrayList<Camion> listaCamion;

	public ModelCamion(){
		listaCamion=new ArrayList<Camion>();
	}

	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza){
		incrementaCodice();
		Camion cm= new Camion(getCodice(),produttore,Modello,capacita,portata,lunghezza);
		listaCamion.add(cm);
	}
	void caricaCamion(int codice, String produttore,String Modello,int capacita,int portata,int lunghezza){
		aggiornaCodice(codice);
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

	public ArrayList<Camion> getLista(){
		return listaCamion;
	}

	public boolean isCamion(Integer codice){
		Iterator<Camion> itr = listaCamion.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().getCodice()==codice){
		    	 return true;
	    	}
	    }
		return false;	
	}

	public Camion getCamion(Integer codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}

	public String toString(){
		String tmp = "";
		for(Camion item:listaCamion){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
}