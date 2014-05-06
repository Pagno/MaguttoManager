package model;
import java.util.ArrayList;
import java.util.Date;


class ElencoAssociazioni{
	
	ArrayList<Associazione> associazioni;
	private static int codice=0;
	
	public ElencoAssociazioni(){
		associazioni=new ArrayList<Associazione>();
	}
	
	public void inserisciAssociazione(Integer ID, Macchina macchina,Cantiere cantiere, Date dataInizio, Date dataFine){
			codice++;
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