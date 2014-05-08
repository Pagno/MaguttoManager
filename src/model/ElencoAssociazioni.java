package model;
import java.util.ArrayList;
import java.util.Date;


class ElencoAssociazioni{
	
	private ArrayList<Associazione> associazioni;
	private int codice;
	
	public ElencoAssociazioni(){
		associazioni=new ArrayList<Associazione>();
		codice=0;
	}
	
	public void inserisciAssociazione(Macchina macchina,Cantiere cantiere, Date dataInizio, Date dataFine){
			codice++;
			Associazione a=new Associazione(codice, macchina, cantiere, dataInizio, dataFine);
			associazioni.add(a);
	}
	public void caricaAssociazione(Integer codice, Macchina macchina,Cantiere cantiere, Date dataInizio, Date dataFine){
		if (this.codice<codice){
			this.codice=codice;
		}
		Associazione a=new Associazione(codice, macchina, cantiere, dataInizio, dataFine);
		associazioni.add(a);
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
	
}
