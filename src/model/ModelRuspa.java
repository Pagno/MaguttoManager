package model;

import java.util.ArrayList;


class ModelRuspa extends ModelMacchina{
	private ArrayList<Ruspa> listaRuspe;


	public ModelRuspa(){
		listaRuspe=new ArrayList<Ruspa>();
	}


	public void aggiungiRuspa(String produttore, String modello,int capacita,int portata,int altezza){
		incrementaCodice();
		Ruspa ruspa= new Ruspa(getCodice(), produttore, modello,capacita,portata,altezza);
		listaRuspe.add(ruspa);

		Object[] v1={ruspa.getCodice(),produttore,modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	void caricaRuspa(int codice,String produttore, String modello,int capacita,int portata,int altezza){
		aggiornaCodice(codice);
		Ruspa ruspa= new Ruspa(codice, produttore, modello,capacita,portata,altezza);
		listaRuspe.add(ruspa);
		
		Object[] v1={ruspa.getCodice(),produttore,modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	public void modificaRuspa(int codice, String produttore, String Modello,int capacita,int portata,int altezza){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
		    	item.setModello(Modello);
		    	item.setCapacitaMassima(capacita);
		    	item.setPortataMassima(portata);
		    	item.setAltezzaMassima(altezza);
			}
		}

		Object[] v1={codice,produttore,Modello,altezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	public boolean eliminaRuspa(int codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				return listaRuspe.remove(item);
			}	
		}
		return false;		
	}

	public ArrayList<Ruspa> getLista(){
		return listaRuspe;
	}

	public boolean isRuspa(Integer codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				return true;
			}	
		}
		return false;	
	}

	public Ruspa getRuspa(Integer codice){
		for(Ruspa item:listaRuspe){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}

	public String toString(){
		String tmp = "";
		for(Ruspa item:listaRuspe){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}

}
