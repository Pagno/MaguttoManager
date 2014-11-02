package model.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Camion;


/**
 *   La classe ModelCamion permette di gestire tutte le istanze della classe Camion.
 *   <p>
 *   Permette quindi di inserire, modificare o eliminare camion.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class ModelCamion extends ModelMacchina{
	
	/**   Lista di camion. */
	private ArrayList<Camion> listaCamion;
	
	/**   Istanza di ModelCamion. */
	private static ModelCamion istanza;

	/**
	 * Istanzia un nuovo ModelCamion.
	 */
	private ModelCamion(){
		listaCamion=new ArrayList<Camion>();
	}
	
	/**
	 * Restituisce una nuova istanza di ModelCamion se non ne &egrave; stata istanziata una in precedenza, altrimenti restituisce
	 * l'istanza realizzata in precedenza.<p>
	 * Questo metodo permette di adottare il pattern Singleton.
	 *
	 * @return L'istanza di ModelCamion
	 */
	public static synchronized ModelCamion getModelCamion(){
		if(istanza==null){
			istanza=new ModelCamion();
		}
		return istanza;
	}

	/**
	 * Aggiunge un camion alla lista.
	 *
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 */
	public void aggiungiCamion(String produttore,String modello,int capacita,int portata,int lunghezza){
		incrementaCodice();
		Camion cm= new Camion(getCodice(),produttore,modello,capacita,portata,lunghezza);
		listaCamion.add(cm);
		
		Object[] v1={cm.getCodice(),produttore,modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Carica un camion nella lista.<p>
	 * Da usare esclusivamente quando si caricano i camion da database.
	 *
	 * @param codice Il codice del camion
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 */
	public void caricaCamion(int codice, String produttore,String modello,int capacita,int portata,int lunghezza){
		aggiornaCodice(codice);
		Camion cm= new Camion(codice,produttore,modello,capacita,portata,lunghezza);
		listaCamion.add(cm);
		Object[] v1={cm.getCodice(),produttore,modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Modifica il camion identificato dal codice inserito.
	 *
	 * @param codice Il codice del camion
	 * @param produttore Il produttore del camion
	 * @param modello Il modello del camion
	 * @param capacita La capacit&agrave; del camion
	 * @param portata La portata del camion
	 * @param lunghezza La lunghezza del camion
	 */
	public void modificaCamion(int codice,String produttore,String modello,int capacita,int portata,int lunghezza){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(modello);
				item.setCapacitaMassima(capacita);
				item.setPortataMassima( portata);
				item.setLunghezza(lunghezza);
			}
		}
		Object[] v1={codice,produttore,modello,lunghezza,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina il camion identificato dal codice inserito.
	 *
	 * @param codice Il codice del camion
	 * @return true, se il camion viene eliminato correttamente
	 */
	public boolean eliminaCamion(int codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				item.liberaRichieste();
				return listaCamion.remove(item);
			}
		}
		return false;		
	}

	/**
	 * Restituisce la lista dei camion.
	 *
	 * @return La lista dei camion
	 */
	public ArrayList<Camion> getLista(){
		return listaCamion;
	}

	/**
	 * Verifica se la macchina che possiede il codice indicato è effettivamente un camion.
	 *
	 * @param codice Il codice della macchina desiderata
	 * @return true, se il codice indicato appartiene a un camion
	 */
	public boolean isCamion(Integer codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Restituisce il camion indentificato dal codice inserito.
	 *
	 * @param codice Il codice del camion desiderato
	 * @return Il camion desiderato
	 */
	public Camion getCamion(Integer codice){
		for(Camion item:listaCamion){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Restituisce la lista contenente i camion disponibili nel periodo indicato dalle due date.
	 *
	 * @param dataInizio La data d'inizio del periodo
	 * @param dataFine La data di fine del periodo
	 * @return La lista dei camion disponibili nel periodo indicato
	 */
	public ArrayList<Camion> getDisponibili(GregorianCalendar dataInizio,GregorianCalendar dataFine){
		ArrayList<Camion> listaLibera=new ArrayList<Camion>();
		
		for(Camion camion:listaCamion){
			if(camion.isLibera(dataInizio, dataFine))
				listaLibera.add(camion);
		}
		return listaLibera;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Camion item:listaCamion){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
	//Metodi realizzati appositamente per il testing della classe.
	

	/**
	 * Resetta la lista e il codice per effettuare i test.
	 */
	public void resetForTest(){
		/*if(istanza!=null){
			istanza=null;
		}*/
		ModelMacchina.initCodice();
		listaCamion.clear();
	}
	
	/**
	 * Aggiunge un Camion per effettuare i test.
	 * 
	 * @param camion Il camion da inserire
	 */
	public void aggiungiCamionForTest(Camion camion){
		aggiornaCodice(camion.getCodice());
		listaCamion.add(camion);
	}
	
	/**
	 * Distrugge l'istanza per eseguire i test.
	 */
	public void initForTest(){
		if(istanza!=null){
			ModelMacchina.initCodice();
			istanza=null;
		}
	}
}