package model;
import java.util.ArrayList;
import java.util.GregorianCalendar;


// TODO: Auto-generated Javadoc
/**
 * The Class ElencoAssociazioni.
 */
class ElencoAssociazioni{

	/** The associazioni. */
	private ArrayList<Associazione> associazioni;
	
	/** The codice. */
	private int codice;
	
	/** The istanza. */
	private static ElencoAssociazioni istanza;

	/**
	 * Instantiates a new elenco associazioni.
	 */
	private ElencoAssociazioni(){
		associazioni=new ArrayList<Associazione>();
		codice=0;
	}
	
	/**
	 * Gets the elenco associazioni.
	 *
	 * @return the elenco associazioni
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
	 * @param macchina the macchina
	 * @param cantiere the cantiere
	 * @param dataInizio the data inizio
	 * @param dataFine the data fine
	 */
	public void inserisciAssociazione(Macchina macchina,Cantiere cantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine){
			codice++;
			Associazione a=new Associazione(codice, macchina, cantiere, dataInizio, dataFine);
			associazioni.add(a);
	}

	/**
	 * Carica associazione.
	 *
	 * @param codice the codice
	 * @param macchina the macchina
	 * @param cantiere the cantiere
	 * @param dataInizio the data inizio
	 * @param dataFine the data fine
	 */
	void caricaAssociazione(Integer codice, Macchina macchina,Cantiere cantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		if (this.codice<codice){
			this.codice=codice;
		}
		Associazione a=new Associazione(codice, macchina, cantiere, dataInizio, dataFine);
		associazioni.add(a);
	}

	/**
	 * Modifica associazione.
	 *
	 * @param codice the codice
	 * @param macchina the macchina
	 * @param cantiere the cantiere
	 * @param dataInizio the data inizio
	 * @param dataFine the data fine
	 */
	public void modificaAssociazione(Integer codice, Macchina macchina,Cantiere cantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		for (Associazione item:associazioni){
			if(item.getID()==codice){
				item.setCantiere(cantiere);
				item.setMacchina(macchina);
				item.setDataInizio(dataInizio);
				item.setDataFine(dataFine);
			}
		}
	}

	/**
	 * Elimina associazione.
	 *
	 * @param ID the id
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
	 * Gets the elenco associazioni list.
	 *
	 * @return the elenco associazioni list
	 */
	public ArrayList<Associazione> getElencoAssociazioniList(){
		return associazioni;
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
	 * Gets the next codice.
	 *
	 * @return the next codice
	 */
	int getNextCodice(){
		return codice+1;
	}
	
	/**
	 * Reset for test.
	 */
	static void resetForTest(){
		if(istanza!=null){
			istanza=null;
		}
	}
	
}
