package model;

import java.util.ArrayList;
import java.util.Iterator;

public class ParcoMacchine{
	
	ArrayList<Macchina> macchine;
	
	
	public ParcoMacchine(){
		macchine=new ArrayList<Macchina>();
	}
	
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		int codice = 0;
		Gru gru=new Gru(codice,produttore,modello, rotazione, portata,lunghezza,altezza);
		macchine.add(gru);
	}
	
	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza){
		int codice=0;
		Camion cm= new Camion(codice,produttore,Modello,capacita,portata,lunghezza);
		macchine.add(cm);
	}
	
	public void aggiungiRuspa(String produttore, String Modello,int capacita,int portata,int altezza){
		int codice=0;
		Ruspa ruspa= new Ruspa(codice, produttore, Modello,capacita,portata,altezza);
		macchine.add(ruspa);
	}

	public void aggiungiEscavatore(String produttore, String Modello,int capacita,int portata,int altezza,int profondita){
		int codice=0;
		Escavatore escavatore= new Escavatore(codice,produttore, Modello,capacita,portata,altezza,profondita);
		macchine.add(escavatore);
	}
	
	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		Iterator<Macchina> itr = macchine.iterator();
	    while (itr.hasNext()) {
	      if(itr.next() instanceof Gru && itr.next().getCodice()==codice){
	    	  Gru g=(Gru)itr.next();
	    	  g.setProduttore(produttore);
	    	  g.setModello(modello);
	    	  g.setLunghezzaMassima(lunghezza);
	    	  g.setPortataMassima( portata);
	    	  g.setAltezzaMassima( altezza);
	    	  g.setAngoloRotazione(rotazione);
	      }
	    }
	}
	
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza){
		Iterator<Macchina> itr = macchine.iterator();
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
	
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza){
		Iterator<Macchina> itr = macchine.iterator();
	    while (itr.hasNext()) {
	      if(itr.next() instanceof Camion && itr.next().getCodice()==codice){
	    	  Camion g=(Camion)itr.next();
	    	  g.setProduttore(produttore);
	    	  g.setModello(Modello);
	    	  g.setCapacitaMassima(capacita);
	    	  g.setPortataMassima( portata);
	    	  g.setLunghezzaMassima(lunghezza);
	      }
	    }
	}
	
	public void modificaEscavatore(int codice, String produttore, String Modello,int capacita,int portata,int altezza,int profondita) {
		Iterator<Macchina> itr = macchine.iterator();
	    while (itr.hasNext()) {
	      if(itr.next() instanceof Escavatore && itr.next().getCodice()==codice){
	    	  Escavatore g=(Escavatore)itr.next();
	    	  g.setProduttore(produttore);
	    	  g.setModello(Modello);
	    	  g.setCapacitaMassima(capacita);
	    	  g.setPortataMassima( portata);
	    	  g.setProfonditaMassima(profondita);
	    	  g.setAltezzaMassima(altezza);
	      }
	    }
	}
	
	public void eliminaMacchina(int codice){
		Iterator<Macchina> itr = macchine.iterator();
	    while (itr.hasNext()) {
	    	if(itr.next().getCodice()==codice){
		    	 itr.remove();
	    	}
	    }		
	}
}
