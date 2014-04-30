package model;
import java.util.ArrayList;
import java.util.Date;

class ElencoAssociazioni {
	
	ArrayList<Associazione> elencoAssociazioniGru;
	ArrayList<Associazione> elencoAssociazioniRuspa;
	
	public ElencoAssociazioni(){
		elencoAssociazioniGru=new ArrayList<Associazione>();
	}
	
	public void inserisciAssociazione(Integer ID, Macchina macchina,Cantiere cantiere, Date dataInizio, Date dataFine){
		Associazione a=new Associazione(ID, macchina, cantiere, dataInizio, dataFine);
		
		
		if(macchina instanceof Gru)	
			elencoAssociazioniGru.add(a);
	}
	
	public void eliminaAssociazione(Integer ID){
		for (Associazione item:elencoAssociazioniGru){
			if(item.getID()==ID){
				elencoAssociazioniGru.remove(item);
				break;
			}
		}
	}
	
	public ArrayList<Associazione> getElencoAssociazioni(){
		return elencoAssociazioniGru;
	}
	
}
