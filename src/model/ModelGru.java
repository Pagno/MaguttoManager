package model;

import java.util.ArrayList;


class ModelGru extends ModelMacchina{
	private ArrayList<Gru> listaGru;
	private static ModelGru istanza;

	private ModelGru(){
		listaGru=new ArrayList<Gru>();
	}

	public static synchronized ModelGru getModelGru(){
		if(istanza==null){
			istanza=new ModelGru();
		}
		return istanza;
	}

	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		incrementaCodice();
		Gru gru=new Gru(getCodice(),produttore,modello, rotazione, portata,lunghezza,altezza);
		listaGru.add(gru);
		

		Object[] v1={gru.getCodice(),produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}
	void caricaGru(int codice, String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		aggiornaCodice(codice);
		Gru gru=new Gru(codice,produttore,modello, rotazione, portata,lunghezza,altezza);
		listaGru.add(gru);
		

		Object[] v1={gru.getCodice(),produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}

	public void modificaGru(int codice,String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(modello);
				item.setLunghezzaMassima(lunghezza);
				item.setPortataMassima( portata);
				item.setAltezza( altezza);
				item.setAngoloRotazione(rotazione);
			}
		}
		Object[] v1={codice,produttore,modello,lunghezza,altezza,portata,rotazione};
		setChanged();
		notifyObservers(v1);
	}

	public boolean eliminaGru(int codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return listaGru.remove(item);
			}
		}
		return false;		
	}
	public ArrayList<Gru> getLista(){
		return listaGru;
	}

	public boolean isGru(Integer codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	public Gru getGru(Integer codice){
		for(Gru item:listaGru){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}

	public String toString(){
		String tmp = "";
		for(Gru item:listaGru){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
	static void resetForTest(){
		if(istanza!=null){
			istanza=null;
		}
	}
}
