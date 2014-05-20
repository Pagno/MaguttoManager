package model;

import java.util.ArrayList;


class ModelCamion extends ModelMacchina{
	private ArrayList<Camion> listaCamion;

	public ModelCamion(){
		listaCamion=new ArrayList<Camion>();
	}

	public void aggiungiCamion(String produttore,String Modello,int capacita,int portata,int lunghezza){
		incrementaCodice();
		Camion cm= new Camion(getCodice(),produttore,Modello,capacita,portata,lunghezza);
		
		listaCamion.add(cm);
		Object[] v1={cm.getCodice(),produttore,Modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	void caricaCamion(int codice, String produttore,String Modello,int capacita,int portata,int lunghezza){
		aggiornaCodice(codice);
		Camion cm= new Camion(codice,produttore,Modello,capacita,portata,lunghezza);
		listaCamion.add(cm);

		Object[] v1={cm.getCodice(),produttore,Modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	public void modificaCamion(int codice,String produttore,String Modello,int capacita,int portata,int lunghezza){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(Modello);
				item.setCapacitaMassima(capacita);
				item.setPortataMassima( portata);
				item.setLunghezza(lunghezza);
			}
		}
	}

	public boolean eliminaCamion(int codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				return listaCamion.remove(item);
			}
		}
		return false;		
	}

	public ArrayList<Camion> getLista(){
		return listaCamion;
	}

	public boolean isCamion(Integer codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
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