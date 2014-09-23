package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.ModelInterface;
import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Macchina;
import model.organizer.data.Priority;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
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
		ArrayList<Prenotazione>prenotazioni=new ArrayList<Prenotazione>();
		for(Richiesta ric:sortedRichieste){
			for(Lavoro lav:ric.getLavoro().getCantiere().getElencoLavori()){
				if(lavoroEndsLessThanAWeekBefore(lav,ric.getLavoro())||lavoroStartsLessThanAWeekAfter(lav,ric.getLavoro())){
					for(Richiesta item:lav.getListaRichieste()){
						if(item.isSoddisfatta()){
							if(ric.rispettaRichiesta(item.getMacchina())){
								if(item.getMacchina().isFree(ric.getDataInizio(), ric.getDataFine())){
									sx=(GregorianCalendar)lav.getDataInizio();
									dx=(GregorianCalendar)lav.getDataFine();
									d=0;
									while(sx.before(dx)){
										sx.add(Calendar.DAY_OF_MONTH, 1);
										d++;
									}
									prenotazioni.add(new Prenotazione(new Associazione(ric,item.getMacchina()),d));
								}
							}
						}
					}
				}
			}
		}
		//prenotazioni contiene tutte le coppie macchina-richiesta i cui lavori rispettano i criteri selezionati.
		
		//TODO seleziona coppie proposte
		//TODO Attenzione! Quando seleziono una coppia settare confermato a true!!!
		ArrayList<Associazione>associazioni=new ArrayList<Associazione>();
		//Considera le varie richieste in ordine di priorità.
		//Per ciascuna richiesta, controlla se sono presenti prenotazioni disponibili.
		//Se sono presenti molte prenotazioni, seleziona quella collegata al lavoro più corto.
		for(Richiesta ric:sortedRichieste){
			
			Prenotazione temp=selectMostPromisingReservation(associazioni,prenotazioni,ric);
			
			//Se ne ha trovata almeno una, allora la seleziona e poi rimuove tutte le selezioni per questa richiesta
			if(temp!=null){
				associazioni.add(temp.select());
				removeReservationsByRequest(prenotazioni,ric);
			}
			else{
				//TODO
				if(ric.getCaratteristiche() instanceof RichiestaCamion){
					ArrayList<Camion>disp=model.elencoCamionDisponibili(ric.getCodice(), ric.getCodiceLavoro());
					boolean isSelected=false;
					for(Camion item:disp){
						boolean isPrenotato=false;
						for(Prenotazione p:prenotazioni){
							if(p.getAssociazione().getMacchina().equals(item)){
								isPrenotato=true;
								break;
							}
						}
						if(!isPrenotato){
							Associazione a=new Associazione(ric,item);
							a.setConfermata(true);
							associazioni.add(a);
							isSelected=true;
							break;
						}
					}
					if(!isSelected){
						for(int i=prenotazioni.size()-1;i>=0;i--){
							Prenotazione p=prenotazioni.get(i);
							if(disp.contains(p.getAssociazione().getMacchina())){
								associazioni.add(p.select());
							}
						}
					}
					
				}
				else if(ric.getCaratteristiche() instanceof RichiestaCamion){

				}
				else if(ric.getCaratteristiche() instanceof RichiestaCamion){

				}
				else{

				}
			}
		}
		
		//SELEZIONO LE COPPIE PIU' PROMETTENTI
		
		return associazioni;
	}
	
	
	
	
	
	
	//FUNZIONI DI SELEZIONE 
	
	public static void removeReservationsByRequest(ArrayList<Prenotazione>list, Richiesta ric){
		for(Prenotazione coppia:list){
			if(coppia.getRichiesta().equals(ric)){
				list.remove(coppia);
			}
		}
	}
	
	public static Prenotazione selectMostPromisingReservation(ArrayList<Associazione>alreadySelected,ArrayList<Prenotazione>list, Richiesta ric){
		Prenotazione temp=new Prenotazione(null,-1);
		//Seleziona le prenotazioni per questa richiesta
		for(Prenotazione coppia:list){
			if(coppia.getRichiesta().equals(ric)){
				if(temp.getDurataLavoro()==-1||coppia.getDurataLavoro()<temp.getDurataLavoro()){
					boolean valid=true;
					for(Associazione a:alreadySelected){
						if(coppia.getAssociazione().getMacchina().equals(a.getMacchina())){
							if(!((ric.getDataFine().before(a.getRichiesta().getDataInizio()))||(ric.getDataInizio().after(a.getRichiesta().getDataFine())))){
								valid=false;
								break;
							}
						}
					}
					if(valid){
						temp=coppia;
					}
				}
			}
		}
		if(temp.getDurataLavoro()==-1){
			return null;
		}
		else{
			return temp;
		}
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
