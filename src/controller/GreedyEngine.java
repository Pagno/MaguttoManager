package controller;

import java.util.ArrayList;

import model.ModelInterface;
import model.organizer.data.Cantiere;
import model.organizer.data.Priority;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;

public class GreedyEngine {

	public static ArrayList<Couple> generateAssociations(ModelInterface model){
		ArrayList<Couple>Associazioni=new ArrayList<Couple>();
		//TODO
		//ORDINAMENTO RICHIESTE PER PRIORITA'
		ArrayList<Richiesta> richieste=model.getRichiesteScoperte();
		ArrayList<Richiesta> sortedRichieste=new ArrayList<Richiesta>();
		ArrayList<Integer> durate=new ArrayList<Integer>();
		int d;
		boolean inserito;
		for(Richiesta ric:richieste){
			//TODO controlla d!!!
			
			d=ric.getDataFine().compareTo(ric.getDataInizio());
			inserito=false;
			for(int i=0; i<sortedRichieste.size();i++){
				if(sortByPriority(ric,sortedRichieste.get(i),d,durate.get(i))){
					sortedRichieste.add(i,ric);
					durate.add(i,d);
					inserito=true;
				}
			}
			if(!inserito){
				sortedRichieste.add(ric);
				durate.add(d);
			}
		}
		
		
		return Associazioni;
	}
	
	
	
	private static boolean sortByPriority(Richiesta ins, Richiesta arr, Integer dIns, Integer dArr){
		if(ins.getPriorita()==Priority.ALTA){
			if(arr.getPriorita()==Priority.ALTA){
				return sortByDuration(ins, arr, dIns, dArr);
			}
			else{
				return true;
			}
		}
		else if(ins.getPriorita()==Priority.MEDIA){
			if(arr.getPriorita()==Priority.ALTA){
				return false;
			}
			else if(arr.getPriorita()==Priority.MEDIA){
				return sortByDuration(ins, arr, dIns, dArr);
			}
			else{
				return true;
			}
		}
		else{
			if(arr.getPriorita()==Priority.ALTA||arr.getPriorita()==Priority.MEDIA){
				return false;
			}
			else{
				return sortByDuration(ins, arr, dIns, dArr);
			}
		}
	}
	
	private static boolean sortByDuration(Richiesta ins, Richiesta arr, Integer dIns, Integer dArr){
		if(dIns<dArr){
			return true;
		}
		else if(dIns>dArr){
			return false;
		}
		else{
			return sortByStartDate(ins, arr);
		}
	}
	
	private static boolean sortByStartDate(Richiesta ins, Richiesta arr){
		if(ins.getDataInizio().compareTo(arr.getDataInizio())<0){
			return true;
		}
		else if(ins.getDataInizio().compareTo(arr.getDataInizio())>0) {
			return false;
		}
		else{
			return sortByCodes(ins,arr);
		}
	}
	
	private static boolean sortByCodes(Richiesta ins, Richiesta arr){
		if(ins.getCodiceCantiere()<arr.getCodiceCantiere()){
			return true;
		}
		else if(ins.getCodiceCantiere()>arr.getCodiceCantiere()) {
			return false;
		}
		else{
			if(ins.getCodiceLavoro()<arr.getCodiceLavoro()){
				return true;
			}
			else if(ins.getCodiceLavoro()>arr.getCodiceLavoro()) {
				return false;
			}
			else{
				if(ins.getCodice()<arr.getCodice()){
					return true;
				}
				else{
					return false;
				}
			}
		}
	}
}
