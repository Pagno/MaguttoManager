package model;
import java.util.ArrayList;
import java.util.GregorianCalendar;


class ElencoAssociazioni{

	private ArrayList<Associazione> associazioni;
	private int codice;
	private static ElencoAssociazioni istanza;

	private ElencoAssociazioni(){
		associazioni=new ArrayList<Associazione>();
		codice=0;
	}
	
	public static synchronized ElencoAssociazioni getElencoAssociazioni(){
		if(istanza==null){
			istanza=new ElencoAssociazioni();
		}
		return istanza;
	}

	public void inserisciAssociazione(Macchina macchina,Cantiere cantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine){
			codice++;
			Associazione a=new Associazione(codice, macchina, cantiere, dataInizio, dataFine);
			associazioni.add(a);
	}

	void caricaAssociazione(Integer codice, Macchina macchina,Cantiere cantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine){
		if (this.codice<codice){
			this.codice=codice;
		}
		Associazione a=new Associazione(codice, macchina, cantiere, dataInizio, dataFine);
		associazioni.add(a);
	}

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

	public boolean eliminaAssociazione(Integer ID){
		for (Associazione item:associazioni){
			if(item.getID()==ID){
				associazioni.remove(item);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Associazione> getElencoAssociazioniList(){
		return associazioni;
	}

	public String toString(){
		String tmp = "";
		for(Associazione item:associazioni){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
	//Metodi realizzati appositamente per il testing della classe.
	
	int getNextCodice(){
		return codice+1;
	}
	
	static void resetForTest(){
		if(istanza!=null){
			istanza=null;
		}
	}
	
}
