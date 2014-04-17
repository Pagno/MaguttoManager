package model;
import java.util.ArrayList;
import java.util.Date;

class ElencoAssociazioni {
	
	ArrayList<Associazione> elencoAssociazioni;
	
	public ElencoAssociazioni(){
		elencoAssociazioni=new ArrayList<Associazione>();
	}
	
	public void inserisciAssociazione(Integer ID, Macchina macchina,Cantiere cantiere, Date dataInizio, Date dataFine){
		Associazione a=new Associazione(ID, macchina, cantiere, dataInizio, dataFine);
		elencoAssociazioni.add(a);
	}
	
	public void eliminaAssociazione(Integer ID){
		for (Associazione item:elencoAssociazioni){
			if(item.getID()==ID){
				elencoAssociazioni.remove(item);
				break;
			}
		}
	}
	
	public ArrayList<Associazione> getElencoAssociazioni(){
		return elencoAssociazioni;
	}
	
}
