package model.organizer;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.organizer.data.Escavatore;


/**
 *   La classe ModelEscavatore permette di gestire tutte le istanze della classe Escavatore.
 *   <p>
 *   Permette quindi di inserire, modificare o eliminare escavatori.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public class ModelEscavatore extends ModelMacchina{
	
	/**   Lista di escavatori. */
	private ArrayList<Escavatore> listaEscavatori;
	
	/**   Istanza di ModelEscavatore. */
	private static ModelEscavatore istanza;
	
	/**
	 * Istanzia un nuovo ModelEscavatore.
	 */
	private ModelEscavatore(){
		listaEscavatori=new ArrayList<Escavatore>();
	}
	
	/**
	 * Restituisce una nuova istanza di ModelEscavatore se non ne &egrave; stata istanziata una in precedenza, altrimenti restituisce
	 * l'istanza realizzata in precedenza.<p>
	 * Questo metodo permette di adottare il pattern Singleton.
	 *
	 * @return L'istanza di ModelEscavatore
	 */
	public static synchronized ModelEscavatore getModelEscavatore(){
		if(istanza==null){
			istanza=new ModelEscavatore();
		}
		return istanza;
	}

	/**
	 * Aggiunge un escavatore alla lista.
	 *
	 * @param produttore Il produttore dell'escavatore
	 * @param modello Il modello dell'escavatore
	 * @param capacita La capacit&agrave; dell'escavatore
	 * @param portata La portata dell'escavatore
	 * @param altezza L'altezza dell'escavatore
	 * @param profondita La profondit&agrave; dell'escavatore
	 */
	public void aggiungiEscavatore(String produttore, String modello,int capacita,int portata,int altezza,int profondita){
		incrementaCodice();
		Escavatore escavatore= new Escavatore(getCodice(),produttore, modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);

		Object[] v1={escavatore.getCodice(),produttore,modello,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}
	
	/**
	 * Carica un escavatore nella lista.<p>
	 * Da usare esclusivamente quando si caricano gli escavatori da database.
	 *
	 * @param codice Il codice dell'escavatore
	 * @param produttore Il produttore dell'escavatore
	 * @param modello Il modello dell'escavatore
	 * @param capacita La capacit&agrave; dell'escavatore
	 * @param portata La portata dell'escavatore
	 * @param altezza L'altezza dell'escavatore
	 * @param profondita La profondit&agrave; dell'escavatore
	 */
	public void caricaEscavatore(int codice, String produttore, String modello,int capacita,int portata,int altezza,int profondita){
		aggiornaCodice(codice);
		Escavatore escavatore= new Escavatore(codice,produttore, modello,capacita,portata,altezza,profondita);
		listaEscavatori.add(escavatore);

		Object[] v1={escavatore.getCodice(),produttore,modello,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Modifica l'escavatore identificato dal codice inserito.
	 *
	 * @param codice Il codice dell'escavatore
	 * @param produttore Il produttore dell'escavatore
	 * @param Modello Il modello dell'escavatore
	 * @param capacita La capacit&agrave; dell'escavatore
	 * @param portata La portata dell'escavatore
	 * @param altezza L'altezza dell'escavatore
	 * @param profondita La profondit&agrave; dell'escavatore
	 */
	public void modificaEscavatore(int codice, String produttore, String modello,int capacita,int portata,int altezza,int profondita) {
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				item.setProduttore(produttore);
				item.setModello(modello);
				item.setCapacitaMassima(capacita);
				item.setPortataMassima( portata);
				item.setProfonditaMassima(profondita);
				item.setAltezzaMassima(altezza);
			}
		}
		Object[] v1={codice,produttore,modello,altezza,profondita,capacita,portata};
		setChanged();
		notifyObservers(v1);
	}

	/**
	 * Elimina l'escavatore identificato dal codice inserito.
	 *
	 * @param codice Il codice dell'escavatore
	 * @return true, se l'escavatore viene eliminato correttamente
	 */
	public boolean eliminaEscavatore(int codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				item.liberaRichieste();
				return listaEscavatori.remove(item);
			}
		}
		return false;		
	}

	/**
	 * Restituisce la lista degli escavatori.
	 *
	 * @return La lista degli escavatori
	 */
	public ArrayList<Escavatore> getLista(){
		return listaEscavatori;
	}

	/**
	 * Verifica se la macchina che possiede il codice indicato è effettivamente un escavatore.
	 *
	 * @param codice Il codice della macchina desiderata
	 * @return true, se il codice indicato appartiene a un escavatore
	 */
	public boolean isEscavatore(Integer codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				return true;
			}
		}
		return false;	
	}

	/**
	 * Restituisce l'escavatore indentificato dal codice inserito.
	 *
	 * @param codice Il codice dell'escavatore desiderato
	 * @return L'escavatore desiderato
	 */
	public Escavatore getEscavatore(Integer codice){
		for(Escavatore item:listaEscavatori){
			if(item.getCodice()==codice){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Restituisce la lista contenente gli escavatori disponibili nel periodo indicato dalle due date.
	 *
	 * @param dataInizio La data d'inizio del periodo
	 * @param dataFine La data di fine del periodo
	 * @return La lista degli escavatori disponibili nel periodo indicato
	 */
	public ArrayList<Escavatore> getDisponibili(GregorianCalendar dataInizio,GregorianCalendar dataFine){
		ArrayList<Escavatore>listaLibera=new ArrayList<Escavatore>();
		for(Escavatore escavatore:listaEscavatori){
			if(escavatore.isLibera(dataInizio, dataFine)){
				listaLibera.add(escavatore);
			}
		}
		return listaLibera;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Escavatore item:listaEscavatori){
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
			ModelMacchina.initCodice();
			istanza=null;
		}*/
		ModelMacchina.initCodice();
		listaEscavatori.clear();
	}
	
	/**
	 * Aggiunge un Escavatore per effettuare i test.
	 * 
	 * @param escavatore L'escavatore da inserire
	 */
	public void aggiungiEscavatoreForTest(Escavatore escavatore){
		aggiornaCodice(escavatore.getCodice());
		listaEscavatori.add(escavatore);
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
