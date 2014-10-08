package controller.greedy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

import model.ModelInterface;
import model.organizer.data.Camion;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Macchina;
import model.organizer.data.Priority;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;
import controller.data.Associazione;
import controller.data.Prenotazione;

public class GreedyEngine {
	
	
	public static ArrayList<Associazione> generateAssociations(ModelInterface model){
		//ORDINAMENTO RICHIESTE PER PRIORITA'
		
		ArrayList<Richiesta> sortedRichieste=GreedyEngine.sortRequests(model.getRichiesteScoperte());
		
		//sortedRichieste contiene le richieste ordinate per priorit�, indice pi� basso corrisponde a priorit� maggiore.
		//durate contiene le durate relative alle richieste, nelle medesime posizioni
		
		//IMPOSTO LE PRENOTAZIONI
		ArrayList<Prenotazione>prenotazioni=GreedyEngine.generateReservations(sortedRichieste);
		//prenotazioni contiene tutte le coppie macchina-richiesta i cui lavori rispettano i criteri selezionati.
		
		//GENERO LE ASSOCIAZIONI
		ArrayList<Associazione>associazioni=new ArrayList<Associazione>();
		//Considera le varie richieste in ordine di priorit�.
		//Per ciascuna richiesta, controlla se sono presenti prenotazioni disponibili.
		//Se sono presenti molte prenotazioni, seleziona quella collegata al lavoro pi� corto.
		for(Richiesta ric:sortedRichieste){
			Prenotazione temp=selectMostPromisingReservation(associazioni,prenotazioni,ric);
			//La richiesta � gi� stata analizzata, quindi le sue eventuali prenotazioni ora sono inutili.
			//Le rimuoviamo dalla lista, per migliorare la velocit� dei cicli su prenotazione
			prenotazioni=removeReservationsByRequest(prenotazioni,ric);
			//Se ne ha trovata almeno una, allora la seleziona aggiungendola ad associazioni
			if(temp!=null){
				associazioni.add(temp.select());
			}
			else{
				//In tal caso non ha trovato prenotazioni
				//Cerca quindi di selezionare una macchina non prenotata da altri,
				//altrimenti una macchina prenotata, rubandola da una richiesta a bassa priorit�
				if(ric.getCaratteristiche() instanceof RichiestaCamion){
					ArrayList<Camion>disp=model.elencoCamionDisponibili(ric.getCodice(), ric.getCodiceLavoro());
					selectMacchinaWithoutReservation(ric,disp,associazioni,prenotazioni);
				}
				else if(ric.getCaratteristiche() instanceof RichiestaGru){
					ArrayList<Gru>disp=model.elencoGruDisponibili(ric.getCodice(), ric.getCodiceLavoro());
					selectMacchinaWithoutReservation(ric,disp,associazioni,prenotazioni);
				}
				else if(ric.getCaratteristiche() instanceof RichiestaRuspa){
					ArrayList<Ruspa>disp=model.elencoRuspeDisponibili(ric.getCodice(), ric.getCodiceLavoro());
					selectMacchinaWithoutReservation(ric,disp,associazioni,prenotazioni);
				}
				else{
					//Resta solo il caso escavatore
					ArrayList<Escavatore>disp=model.elencoEscavatoriDisponibili(ric.getCodice(), ric.getCodiceLavoro());
					selectMacchinaWithoutReservation(ric,disp,associazioni,prenotazioni);
				}
			}
		}
		
		return associazioni;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	
	//FUNZIONI DI SELEZIONE
	
	static <T extends Macchina> void insertAssociation(Richiesta ric, T mac, ArrayList<Associazione>associazioni){
		Associazione a=new Associazione(ric,mac);
		a.setConfermata(true);
		associazioni.add(a);
	}
	
	static <T extends Macchina> ArrayList<Associazione> selectMacchinaWithoutReservation(Richiesta ric,ArrayList<T>disp,ArrayList<Associazione>associazioni,ArrayList<Prenotazione>prenotazioni){
		if(ric.isSoddisfatta()){
			//Se ric � gi� soddisfatta, non faccio nulla
		}
		else{
			boolean isAssociata=false;
			for(Associazione a:associazioni){
				if(a.getRichiesta().equals(ric)){
					isAssociata=true;
					break;
				}
			}
			if(isAssociata){
				//Se ric � gi� soddisfatta, non faccio nulla
			}
			else{
				if(disp.size()==0){
					//In tal caso non ho nessuna macchina libera per soddisfare la richiesta
					//L'algoritmo non suggerisce alcuna macchina da inserire per tale richiesta
				}
				else{
					ArrayList<T>nonAssociate=new ArrayList<T>();
					for(T mac:disp){
						boolean alreadyAssociato=false;
						for(Associazione a:associazioni){
							//controllo che la macchina non sia gi� associata e quindi occupata temporaneamente
							//Le macchine erano gi� libere, il controllo � effettuato in reserveMacchineFromLavoro
							if(mac.equals(a.getMacchina())){
								if(!((ric.getDataFine().before(a.getDataInizio()))||(ric.getDataInizio().after(a.getDataFine())))){
									//se la macchina � la stessa e gli intervalli si sovrappongono, la macchina � gi� associata
									alreadyAssociato=true;
									break;
								}
							}
						}
						if(!alreadyAssociato){
							nonAssociate.add(mac);
						}
					}
					disp=nonAssociate;
					if(disp.size()==0){
						//In tal caso non ho nessuna macchina libera o non associata per soddisfare la richiesta
						//L'algoritmo non suggerisce alcuna macchina da inserire per tale richiesta
					}
					else{
						//Se arrivo qui, c'� almeno una macchina libera o ancora non associata che soddisfa la richiesta
						//Quindi potr� sicuramente soddisfare la richiesta con almeno una macchina
						//Cerchiamo prima una macchina non prenotata da altri
						boolean isSelezionato=false;
						for(T mac:disp){
							boolean isPrenotato=false;
							for(Prenotazione p:prenotazioni){
								if(p.getMacchina().equals(mac)){
									if(!((ric.getDataFine().before(p.getDataInizio()))||(ric.getDataInizio().after(p.getDataFine())))){
										//Se la macchina � la stessa e gli intervalli si sovrappongono, la macchina � prenotata
										isPrenotato=true;
										break;
									}
								}
							}
							if(!isPrenotato){
								//La macchina � libera, non � associata e non � prenotata da altri.
								//Posso quindi associarla senza problemi
								insertAssociation(ric,mac,associazioni);
								isSelezionato=true;
								break;
							}
						}
						if(!isSelezionato){
							//Se siamo arrivati a questo punto, c'� almeno una macchina associabile a questa richiesta,
							//ma tutte quelle libere sono gi� prenotate da altre richieste.
							//Rubo quindi la macchina alla richiesta meno prioritaria tra tutte.
							//Sono sicuro di trovare almeno una prenotazione, perch� c'� almeno una macchina libera ma nessuna era non prenotata.
							//Le prenotazioni sono in ordine di priorit� della richiesta, quindi seleziono quella con indice pi� alto.
							for(int i=prenotazioni.size()-1; i>=0; i--){
								if(disp.contains(prenotazioni.get(i).getMacchina())){
									insertAssociation(ric,prenotazioni.get(i).getMacchina(),associazioni);
									break;
								}
							}
						}
					}
				}
			}
		}
		return associazioni;
	}
	
	static ArrayList<Prenotazione> removeReservationsByRequest(ArrayList<Prenotazione>list, Richiesta ric){
		ArrayList<Prenotazione>nuovaList=new ArrayList<Prenotazione>();
		for(Prenotazione coppia:list){
			if(!coppia.getRichiesta().equals(ric)){
				nuovaList.add(coppia);
			}
		}
		return nuovaList;
	}
	
	static Prenotazione selectMostPromisingReservation(ArrayList<Associazione>alreadySelected,ArrayList<Prenotazione>list, Richiesta ric){
		//Se la richiesta � soddisfatta, non seleziono alcuna prenotazione
		if(ric.isSoddisfatta()){
			return null;
		}
		else{

			//Se la richiesta � gi� stata associata, non seleziono alcuna prenotazione
			boolean isAssociato=false;
			for(Associazione asso:alreadySelected){
				if(asso.getRichiesta().equals(ric)){
					isAssociato=true;
					break;
				}
			}
			if(isAssociato){
				return null;
			}
			else{
				Prenotazione temp=new Prenotazione(null,-1);
				//Seleziona le prenotazioni per questa richiesta
				for(Prenotazione coppia:list){
					if(coppia.getRichiesta().equals(ric)){
						if(temp.getDurataLavoro()==-1||coppia.getDurataLavoro()<temp.getDurataLavoro()){
							boolean valid=true;
							for(Associazione a:alreadySelected){
								//controllo che la prenotazione non coinvolga una macchina gi� associata e quindi occupata temporaneamente
								//Le macchine erano gi� libere, il controllo � effettuato in reserveMacchineFromLavoro
								if(coppia.getMacchina().equals(a.getMacchina())){
									if(!((ric.getDataFine().before(a.getDataInizio()))||(ric.getDataInizio().after(a.getDataFine())))){
										//se la macchina � la stessa e le tempistiche si sovrappongono, la macchina � gi� occupata
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
		}
	}
	
	
	//FUNZIONI DI VERIFICA DEL CRITERIO DI PRENOTAZIONE
	
	static ArrayList<Prenotazione> generateReservations(ArrayList<Richiesta> sortedRichieste){
		ArrayList<Prenotazione>prenotazioni=new ArrayList<Prenotazione>();
		for(Richiesta ric:sortedRichieste){
			for(Lavoro lav:ric.getRelatedWorks()){
				if(lavoroEndsLessThanAWeekBefore(lav,ric.getLavoro())||lavoroStartsLessThanAWeekAfter(lav,ric.getLavoro())){
					reserveMacchineFromLavoro(ric, lav, prenotazioni);
				}
			}
		}
		return prenotazioni;
	}
	
	static void reserveMacchineFromLavoro(Richiesta ric, Lavoro lav,ArrayList<Prenotazione>prenotazioni){
		int d;
		GregorianCalendar sx,dx;
		if(lav!=null){
			sx=(GregorianCalendar)lav.getDataInizio().clone();
			dx=(GregorianCalendar)lav.getDataFine().clone();
			d=0;
			while(sx.before(dx)){
				sx.add(Calendar.DAY_OF_MONTH, 1);
				d++;
			}
			for(Richiesta item:lav.getListaRichieste()){
				if(item.isSoddisfatta()){
					if(ric.rispettaRichiesta(item.getMacchina())){
						if(item.getMacchina().isFree(ric.getDataInizio(), ric.getDataFine())){
							prenotazioni.add(new Prenotazione(new Associazione(ric,item.getMacchina()),d));
						}
					}
				}
			}
		}
	}
	
	//Verifico se il lavoro element finisce meno di una settimana prima rispetto a base
	static boolean lavoroEndsLessThanAWeekBefore(Lavoro element, Lavoro base){
		GregorianCalendar sx,dx;
		int d;
		if(!element.equals(base)){
			if(element.getDataFine().before(base.getDataInizio())){
				sx=(GregorianCalendar)element.getDataFine().clone();
				dx=(GregorianCalendar)base.getDataInizio().clone();
				d=0;
				while(sx.before(dx)&&d<8){
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
	static boolean lavoroStartsLessThanAWeekAfter(Lavoro element, Lavoro base){
		GregorianCalendar sx,dx;
		int d;
		if(!element.equals(base)){
			if(element.getDataInizio().after(base.getDataFine())){
				sx=(GregorianCalendar)base.getDataFine().clone();
				dx=(GregorianCalendar)element.getDataInizio().clone();
				d=0;
				while(sx.before(dx)&&d<8){
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
	
	static ArrayList<Richiesta> sortRequests(ArrayList<Richiesta> richieste){
		//Utilizzo il metodo Collections.sort(List<T> list, Comparator<? super T> c)
		//Tale metodo implementa l'algoritmo MergeSort, che garantisce una complessit� O(n log n)
		ArrayList<Richiesta> sortedRichieste=(ArrayList<Richiesta>)richieste.clone();
		Collections.sort(sortedRichieste, new GreedyEngine.RichiesteComparator());
		return sortedRichieste;
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la priorit� del cantiere di ins � superiore a quella del cantiere di arr, restituisce true.
	 *se tale priorit� � minore, restituisce false.
	 *se le priorit� sono uguali, passo al confronto della durata dei lavori.
	 */
	static boolean sortByPriority(Richiesta ins, Richiesta arr){
		if(ins.getPriorita()==Priority.ALTA){
			if(arr.getPriorita()==Priority.ALTA){
				return sortByDuration(ins, arr);
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
				return sortByDuration(ins, arr);
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
				return sortByDuration(ins, arr);
			}
		}
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la durata del lavoro di ins � minore a quella del lavoro di arr, restituisce true.
	 *se tale durata � maggiore, restituisce false.
	 *se le durate sono uguali, passo al confronto della data d'inizio del lavoro.
	 */
	static boolean sortByDuration(Richiesta ins, Richiesta arr){
		int dIns=ins.getDurata();
		int dArr=arr.getDurata();
		if( dIns<arr.getDurata()){
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
	 *se la data d'inizio del lavoro di ins � minore a quella del lavoro di arr, restituisce true.
	 *se tale data d'inizio � maggiore, restituisce false.
	 *se le data d'inizio sono uguali, passo al confronto dei codici.
	 */
	static boolean sortByStartDate(Richiesta ins, Richiesta arr){
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
	 *se il codice del cantiere di ins � minore del codice del cantiere di arr, restituisco true.
	 *se tale codice � maggiore, restituisco false.
	 *se il cantiere � lo stesso, passo al confronto dei codici dei lavori.
	 *se il codice del lavoro di ins � minore del codice del lavoro di arr, restituisco true.
	 *se tale codice � maggiore, restituisco false.
	 *se il lavoro � lo stesso, passo al confronto dei codici delle richieste.
	 *Il codice delle richieste � per forza diverso da richiesta a richiesta, quindi escludo l'uguaglianza.
	 *se il codice di ins � minore del codice di arr, restituisco true.
	 *se tale codice � maggiore, restituisco false.
	 */
	static boolean sortByCodes(Richiesta ins, Richiesta arr){
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
	
	static class RichiesteComparator implements Comparator<Richiesta>{
		@Override
		public int compare(Richiesta r1, Richiesta r2){
			if(r1.equals(r2)){
				return 0;
			}
			else{
				if(GreedyEngine.sortByPriority(r1, r2)){
					return -1;
				}
				else{
					return 1;
				}
			}
		}
		
	}
}