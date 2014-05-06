package model;
import java.util.ArrayList;
import java.util.Date;


class AssociazioniGru{
	
	ArrayList<Associazione> associazioni;
	private static int codice=0;
	
	public AssociazioniGru(){
		associazioni=new ArrayList<Associazione>();
	}
	
	public boolean inserisciAssociazione(Integer ID, Macchina macchina,Cantiere cantiere, Date dataInizio, Date dataFine){
		if(macchina instanceof Gru)	{
			codice++;
			Integer codice=new Integer(12);
			Associazione a=new Associazione(codice, macchina, cantiere, dataInizio, dataFine);
			associazioni.add(a);
			return true;
		}
		return false;
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
