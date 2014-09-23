package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.ModelInterface;
import model.organizer.data.Cantiere;
import model.organizer.data.Priority;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import controller.Associazione;

public class GreedyEngine {

	public static ArrayList<Associazione> generateAssociations(ModelInterface model){
		ArrayList<Associazione>Associazioni=new ArrayList<Associazione>();

		//ORDINAMENTO RICHIESTE PER PRIORITA'
		ArrayList<Richiesta> richieste=model.getRichiesteScoperte();
		ArrayList<Richiesta> sortedRichieste=new ArrayList<Richiesta>();
		ArrayList<Integer> durate=new ArrayList<Integer>();
		int d;
		boolean inserito;
		GregorianCalendar sx,dx;
		for(Richiesta ric:richieste){
			d=0;
			sx=(GregorianCalendar)ric.getDataInizio().clone();
			dx=(GregorianCalendar)ric.getDataFine().clone();
			while(sx.before(dx)){
				sx.add(Calendar.DAY_OF_MONTH, 1);
				d++;
			}
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
		//sortedRichieste contiene le richieste ordinate per priorità, indice più basso corrisponde a priorità maggiore.
		
		//IMPOSTO LE PRENOTAZIONI
		ArrayList<Associazione>prenotazioni=new ArrayList<Associazione>();
		for(Richiesta ric:sortedRichieste){
			for(Lavoro lav:ric.getLavoro().getCantiere().getElencoLavori()){
				if(lavoroEndsLessThanAWeekBefore(lav,ric.getLavoro())||lavoroStartsLessThanAWeekAfter(lav,ric.getLavoro())){
					for(Richiesta item:lav.getListaRichieste()){
						if(item.isSoddisfatta()){
							if(ric.rispettaRichiesta(item.getMacchina())){
								prenotazioni.add(new Associazione(ric,item.getMacchina()));
							}
						}
					}
				}
			}
		}
		//prenotazioni contiene tutte le coppie macchina-richiesta i cui lavori rispettano i criteri selezionati.
		
		//TODO seleziona coppie proposte
		//TODO Attenzione! Quando seleziono una coppia settare confermato a true!!!
		//SELEZIONO LE COPPIE PIU' PROMETTENTI
		
		return Associazioni;
	}
	
	
	
	
	//FUNZIONI DI VERIFICA DEL CRITERIO DI PRENOTAZIONE
	
	//Verifico se il lavoro element finisce meno di una settimana prima rispetto a base
	public static boolean lavoroEndsLessThanAWeekBefore(Lavoro element, Lavoro base){
		GregorianCalendar sx,dx;
		int d;
		if(!element.equals(base)){
			if(element.getDataFine().before(base.getDataInizio())){
				sx=(GregorianCalendar)element.getDataFine().clone();
				dx=(GregorianCalendar)base.getDataInizio().clone();
				d=0;
				while(sx.before(dx)||d<8){
					sx.add(Calendar.DAY_OF_MONTH, 1);
					d++;
				}
				if(d<=7){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	//Verifico se il lavoro element inizia meno di una settimana dopo rispetto a base
	public static boolean lavoroStartsLessThanAWeekAfter(Lavoro element, Lavoro base){
		GregorianCalendar sx,dx;
		int d;
		if(!element.equals(base)){
			if(element.getDataInizio().after(base.getDataFine())){
				sx=(GregorianCalendar)element.getDataInizio().clone();
				dx=(GregorianCalendar)base.getDataFine().clone();
				d=0;
				while(sx.before(dx)||d<8){
					sx.add(Calendar.DAY_OF_MONTH, 1);
					d++;
				}
				if(d<=7){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	//FUNZIONI DI ORDINAMENTO-------------------------------------------------------------------------------------------------
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	*se la priorità del cantiere di ins è superiore a quella del cantiere di arr, restituisce true.
	*se tale priorità è minore, restituisce false.
	*se le priorità sono uguali, passo al confronto della durata dei lavori.
	*/
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
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	*se la durata del lavoro di ins è minore a quella del lavoro di arr, restituisce true.
	*se tale durata è maggiore, restituisce false.
	*se le durate sono uguali, passo al confronto della data d'inizio del lavoro.
	*/
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
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	*se la data d'inizio del lavoro di ins è minore a quella del lavoro di arr, restituisce true.
	*se tale data d'inizio è maggiore, restituisce false.
	*se le data d'inizio sono uguali, passo al confronto dei codici.
	*/
	private static boolean sortByStartDate(Richiesta ins, Richiesta arr){
		if(ins.getDataInizio().before(arr.getDataInizio())){
			return true;
		}
		else if(ins.getDataInizio().after(arr.getDataInizio())) {
			return false;
		}
		else{
			return sortByCodes(ins,arr);
		}
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	*se il codice del cantiere di ins è minore del codice del cantiere di arr, restituisco true.
	*se tale codice è maggiore, restituisco false.
	*se il cantiere è lo stesso, passo al confronto dei codici dei lavori.
	*se il codice del lavoro di ins è minore del codice del lavoro di arr, restituisco true.
	*se tale codice è maggiore, restituisco false.
	*se il lavoro è lo stesso, passo al confronto dei codici delle richieste.
	*Il codice delle richieste è per forza diverso da richiesta a richiesta, quindi escludo l'uguaglianza.
	*se il codice di ins è minore del codice di arr, restituisco true.
	*se tale codice è maggiore, restituisco false.
	*/
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
