package model;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;


class ElencoAssociazioni{
	
	private ArrayList<Associazione> associazioni;
	private int codice;
	
	public ElencoAssociazioni(){
		associazioni=new ArrayList<Associazione>();
		codice=0;
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
		Iterator<Associazione> itr = associazioni.iterator();
		while (itr.hasNext()) {
			if(itr.next().getID()==codice){
				itr.next().setCantiere(cantiere);
				itr.next().setMacchina(macchina);
				itr.next().setDataInizio(dataInizio);
				itr.next().setDataFine(dataFine);
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
	
	public ArrayList<Associazione> getElencoAssociazioni(){
		return associazioni;
	}
	
	public String toString(){
		String tmp = "";
		for(Associazione item:associazioni){
			tmp = tmp + item.toString() + "\n";
		}
		return tmp;
	}
	
}
