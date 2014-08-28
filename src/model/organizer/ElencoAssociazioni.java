package model.organizer;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

import model.organizer.data.Associazione;
import model.organizer.data.Lavoro;
import model.organizer.data.Macchina;


// 
/**
 *   Class ElencoAssociazioni.
 */
public class ElencoAssociazioni extends Observable{

	/**   associazioni. */
	private ArrayList<Associazione> associazioni;
	
	/**   codice. */
	private int codice;
	
	/**   istanza. */
	private static ElencoAssociazioni istanza;

	/**
	 * Instantiates a new elenco associazioni.
	 */
	private ElencoAssociazioni(){
		associazioni=new ArrayList<Associazione>();
		codice=0;
	}
	
	/**
	 * Gets   elenco associazioni.
	 *
	 * @return   elenco associazioni
	 */
	public static synchronized ElencoAssociazioni getElencoAssociazioni(){
		if(istanza==null){
			istanza=new ElencoAssociazioni();
		}
		return istanza;
	}

	/**
	 * Inserisci associazione.
	 *
	 * @param macchina   macchina
	 * @param cantiere   cantiere
	 * @param dataInizio   data inizio
	 * @param dataFine   data fine
	 */
	public void inserisciAssociazione(Macchina macchina,Lavoro lavoro){
			codice++;
			Associazione a=new Associazione(codice, macchina, lavoro);
			associazioni.add(a);
			
			Object[] list={codice,macchina.getProduttore()+" - "+macchina.getModello()};
			setChanged();
			notifyObservers(list);
	}

	/**
	 * Carica associazione.
	 *
	 * @param codice   codice
	 * @param macchina   macchina
	 * @param cantiere   cantiere
	 * @param dataInizio   data inizio
	 * @param dataFine   data fine
	 */
	public void caricaAssociazione(Integer codice, Macchina macchina,Lavoro lavoro, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		if (this.codice<codice){
			this.codice=codice;
		}
		Associazione a=new Associazione(codice, macchina, lavoro);
		associazioni.add(a);
		
		Object[] list={codice,macchina.getProduttore()+" - "+macchina.getModello()
				,dataInizio,dataFine};
		setChanged();
		notifyObservers(list);
	}

	/**
	 * Modifica associazione.
	 *
	 * @param codice   codice
	 * @param macchina   macchina
	 * @param lavoro   cantiere
	 * @param dataInizio   data inizio
	 * @param dataFine   data fine
	 */
	public void modificaAssociazione(Integer codice, Macchina macchina,Lavoro lavoro, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		for (Associazione item:associazioni){
			if(item.getID()==codice){
				item.setLavoro(lavoro);
				item.setMacchina(macchina);
			}
		}
		
		Object[] list={codice,macchina.getProduttore()+" - "+macchina.getModello()
				,dataInizio,dataFine};
		setChanged();
		notifyObservers(list);
	}

	/**
	 * Elimina associazione.
	 *
	 * @param ID   id
	 * @return true, if successful
	 */
	public boolean eliminaAssociazione(Integer ID){
		for (Associazione item:associazioni){
			if(item.getID()==ID){
				associazioni.remove(item);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets   elenco associazioni list.
	 *
	 * @return   elenco associazioni list
	 */
	public ArrayList<Associazione> getElencoAssociazioniList(){
		return associazioni;
	}
	
	/**
	 *	
	 * @param codiceCantiere codice del cantiere di cui si vuole conoscere le associazioni
	 * @return   elenco associazioni list
	 */	
	public ArrayList<Associazione> getElencoAssociazioniList(int codiceCantiere){
		ArrayList<Associazione> arr=new ArrayList<Associazione>();
		for(Associazione a:associazioni){
			if(a.getLavoro().getCodice()==codiceCantiere)
				arr.add(a);
		}
		return arr;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String tmp = "";
		for(Associazione item:associazioni){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
	//Metodi realizzati appositamente per il testing della classe.
	
	/**
	 * Gets   next codice.
	 *
	 * @return   next codice
	 */
	public int getNextCodice(){
		return codice+1;
	}
	
	/**
	 * Reset for test.
	 */
	public static void resetForTest(){
		if(istanza!=null){
			istanza=null;
		}
	}
	
}
