package controller.greedy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;

import model.ModelInterface;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;
import controller.data.Associazione;
import controller.data.Prenotazione;

public class GreedyEngine {
	
	
	public static ArrayList<Associazione> generaAssociazioni(ModelInterface model){
		//ORDINAMENTO RICHIESTE PER PRIORITA'
		
		ArrayList<Associazione>associazioni=new ArrayList<Associazione>();
		ArrayList<Associazione>associazioniDaPrenotazioni=new ArrayList<Associazione>();
		ArrayList<Richiesta> sortedRichieste=GreedyEngine.ordinaRichieste(model.getRichiesteScoperte());
		//ArrayList<Richiesta> uncoveredRichieste=new ArrayList<Richiesta>();
		
		//Fase 1: Seleziono le macchine basandomi sulle prenotazioni
		associazioniDaPrenotazioni=GreedyEngine.selezionaConPrenotazioni(sortedRichieste);
		
		//Fase 2: Seleziono le macchine rimaste libere
		associazioni=GreedyEngine.selezionaMacchineLibere(model, sortedRichieste, associazioniDaPrenotazioni);
		
		/*Fase 3: Le macchine sono state associate alle richieste prima per prenotazione e poi per disponibilit�.
		 * Pu� per� accadere che alcune richieste ad alta priorit� siano rimaste scoperte perch� richieste a basso livello 
		 * avevano prenotato una macchina disponibile, ottenendo cos� l'associazione ed impedendo alla richiesta ad alto livello 
		 * di ottenerla.
		 * Ora si procede quindi ad analizzare le richieste scoperte per priorit�: nel caso in cui venga trovata una macchina 
		 * adatta a soddisfare la richiesta ad alta priorit� ma associata ad una richiesta a bassa priorit�, essa viene rubata
		 * dalla richiesta ad alto livello.
		 * Si effettua poi un controllo delle associazioni da prenotazione: se si sta analizzando una richiesta di una certa priorit�,
		 * poich� esse vengono analizzate per priorit� decrescenti tutte le associazioni con priorit� superiori possono essere confermate,
		 * quindi vengono spostate nell'altra lista di associazioni, costruendo cos� l'output dell'algoritmo.
		 */
		GreedyEngine.rubaMacchineAdAssociazioni(sortedRichieste, associazioniDaPrenotazioni, associazioni);
		return associazioni;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	
	//FUNZIONI DI SELEZIONE
	
	static void rubaMacchineAdAssociazioni(ArrayList<Richiesta>sortedRichieste,ArrayList<Associazione> associazioniDaPrenotazioni,ArrayList<Associazione> associazioni){
		boolean isCleaned=true;
		Comparator<Richiesta>comp=new RichiesteComparator();
		while(!sortedRichieste.isEmpty()){
			isCleaned=true;
			boolean isRubata=false;
			boolean isInferiore=false;
			Richiesta ric=sortedRichieste.get(0);//Prendo la richiesta scoperta con pi� alta priorit�
			sortedRichieste.remove(0); //E la elimino dalla coda, non servir� pi� analizzarla
			//Le associazioni sono in ordine di priorit� di richiesta decrescente, quindi scorro la lista al contrario
			for(int j=associazioniDaPrenotazioni.size()-1; j>=0;j--){ 
				Associazione asso=associazioniDaPrenotazioni.get(j);
				if(!isInferiore){
					if(comp.compare(ric, asso.getRichiesta())<0){ //Se ric ha priorit� pi� alta, restituisce -1
						if(!isRubata){ //Se non ho ancora rubato la macchina devo controllare se rispetta la richiesta
							if(ric.rispettaRichiesta(asso.getMacchina())){ 
								//in tal caso ric ha priorit� pi� alta, e la macchina rispetta la richiesta. 
								//Quindi rubo la macchina.
								Associazione rubata=new Associazione(ric,asso.getMacchina());
								//Visto che le richieste scoperte di priorit� superiore sono gi� state analizzate, 
								//posso confermare questa associazione inserendola nelle associazioni definitive.
								associazioni.add(rubata);
								//Visto che ho gi� rubato la macchina, non devo pi� fare controlli sulle macchine
								isRubata=true;
								//Reinserisco la richiesta a cui ho rubato la macchina nelle richieste scoperte, 
								//potrebbe a sua volta rubarne un'altra in seguito
								boolean reinserito=false;
								//RIMUOVERE
								//System.out.println("Check " + sortedRichieste);
								//END RIMUOVERE
								for(int i=0; i<sortedRichieste.size();i++){ //Inserisco la richiesta scoperta nell'array alla giusta posizione
									if(comp.compare(asso.getRichiesta(), sortedRichieste.get(i))<0){ //Appena trovo una richiesta con priorit� minore
										sortedRichieste.add(i, asso.getRichiesta());
										reinserito=true;
										break;
									}
								}
								if(!reinserito){ //In tal caso � la richiesta scoperta di pi� bassa priorit�, la inserisco in fondo
									sortedRichieste.add(asso.getRichiesta());
								}
								//Elimino la vecchia associazione, che non esiste pi�
								associazioniDaPrenotazioni.set(j, null);
								//L'array contiene quindi elementi inutili, segnalo la necessit� di fare pulizia
								isCleaned=false;
							}
						}
					}
					else{
						isInferiore=true;
						associazioni.add(asso);
						associazioniDaPrenotazioni.set(j,null);
						isCleaned=false;
					}
				}
				else{
					associazioni.add(asso);
					associazioniDaPrenotazioni.set(j,null);
					isCleaned=false;
				}
			}
			while(!isCleaned){
				isCleaned=!(associazioniDaPrenotazioni.remove(null));
			}
		}
		if(!associazioniDaPrenotazioni.isEmpty()){
			//Se l'ultima richiesta scoperta era pi� alta di una richiesta non scoperta,
			//ci sono ancora delle associazioni da prenotazioni non ancora confermate.
			//Provvedo quindi a spostarle in associazioni
			associazioni.addAll(associazioniDaPrenotazioni);
			associazioniDaPrenotazioni.clear();
		}
		for(Associazione a:associazioni){
			a.setConfermata(true);
		}
	}
	
	static ArrayList<Associazione>selezionaConPrenotazioni(ArrayList<Richiesta>sortedRichieste){
		ArrayList<Associazione> associazioniDaPrenotazioni=new ArrayList<Associazione>();
		boolean isCleaned=true;
		for(int i=0; i<sortedRichieste.size();i++){
			Richiesta ric=sortedRichieste.get(i);
			Associazione a=GreedyEngine.selezionaPrenotazionePiuPromettente(associazioniDaPrenotazioni, ric);
			if(a!=null){
				associazioniDaPrenotazioni.add(a);
				sortedRichieste.set(i, null);
				isCleaned=false;
			}
		}
		while(!isCleaned){
			isCleaned=!(sortedRichieste.remove(null));
		}
		return associazioniDaPrenotazioni;
	}
	
	static Associazione selezionaPrenotazionePiuPromettente(ArrayList<Associazione>alreadySelected, Richiesta ric){
		//Se la richiesta � soddisfatta, non seleziono alcuna prenotazione
		if(ric.isSoddisfatta()){
			return null;
		}
		else{

			
			for(Associazione asso:alreadySelected){
				//Se la richiesta � gi� stata associata, non seleziono alcuna prenotazione
				if(asso.getRichiesta().equals(ric)){
					return null;
				}
			}
			

			HashMap<Macchina,Prenotazione>prenotazioniHashMap=generaPrenotazioni(ric);
			
			
			for(Associazione asso:alreadySelected){
					//Se l'associazione � sovrapposta alla richiesta, pu� eliminare delle associazioni
					if(!((ric.getDataFine().before(asso.getDataInizio()))||(ric.getDataInizio().after(asso.getDataFine())))){
						//Se la macchina associata � presente, la prenotazione relativa viene annullata
						if(prenotazioniHashMap.containsKey(asso.getMacchina())){
							prenotazioniHashMap.remove(asso.getMacchina());
						}
					}
				
			}
			
			Boolean selezionata=false;
			Prenotazione temp=new Prenotazione(null,-1);
			Set<Macchina>keys=prenotazioniHashMap.keySet();

			for(Macchina m:keys){
				Prenotazione pre=prenotazioniHashMap.get(m);
				if(!selezionata||pre.getDurataLavoro()<temp.getDurataLavoro()){
					temp=pre;
					selezionata=true;
				}
			}

			if(!selezionata){
				return null;
			}
			else{
				return temp.getAssociazione();
			}
		}

	}
	
	static ArrayList<Associazione>selezionaMacchineLibere(ModelInterface model, ArrayList<Richiesta>sortedRichieste,ArrayList<Associazione> associazioniDaPrenotazioni ){
		ArrayList<Associazione> associazioniDaLibere=new ArrayList<Associazione>();
		boolean isCleaned=true;
		for(int i=0;i<sortedRichieste.size();i++){
			Richiesta ric=sortedRichieste.get(i);
			Associazione a;
			a=null;
			if(ric.getCaratteristiche() instanceof RichiestaCamion){
				a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric, model.getElencoCamionDisponibili(ric.getCodice(), ric.getCodiceLavoro()), associazioniDaLibere, associazioniDaPrenotazioni);
			}
			else if(ric.getCaratteristiche() instanceof RichiestaRuspa){
				a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric, model.getElencoRuspeDisponibili(ric.getCodice(), ric.getCodiceLavoro()), associazioniDaLibere, associazioniDaPrenotazioni);
			}
			else if(ric.getCaratteristiche() instanceof RichiestaGru){
				a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric, model.getElencoGruDisponibili(ric.getCodice(), ric.getCodiceLavoro()), associazioniDaLibere, associazioniDaPrenotazioni);
			}
			else if(ric.getCaratteristiche() instanceof RichiestaEscavatore){
				a=GreedyEngine.selezionaMacchinaSenzaPrenotazioni(ric, model.getElencoEscavatoriDisponibili(ric.getCodice(), ric.getCodiceLavoro()), associazioniDaLibere, associazioniDaPrenotazioni);
			}
			if(a!=null){
				associazioniDaLibere.add(a);
				sortedRichieste.set(i,null);
				isCleaned=false;
			}
		}
		while(!isCleaned){
			isCleaned=!(sortedRichieste.remove(null));
		}
		return associazioniDaLibere;
	}
	
	static <T extends Macchina> Associazione selezionaMacchinaSenzaPrenotazioni(Richiesta ric,ArrayList<T>disp,ArrayList<Associazione>associazioniDaLibere,ArrayList<Associazione>associazioniDaPrenotazioni){
		ArrayList<Associazione>alreadySelected=new ArrayList<Associazione>();
		alreadySelected.addAll(associazioniDaPrenotazioni);
		alreadySelected.addAll(associazioniDaLibere);
		
		HashMap<T,T>macchineAssociateHashMap=new HashMap<T,T>();
		
		//Se non ci sono macchine disponibili (non controllo le gi� associate) o la richiesta � gi� soddisfatta
		//non seleziono nulla 
		if(ric.isSoddisfatta()||disp.size()==0){ 
			return null;                         
		}
		else{
			

			//Riempio l'hashmap delle macchine gi� associate in conflitto con la richiesta
			for(Associazione a:alreadySelected){
				//Se la richiesta � gi� associata restituisce null
				if(a.getRichiesta().equals(ric)){
					return null;
				}
				if(!((ric.getDataFine().before(a.getDataInizio()))||(ric.getDataInizio().after(a.getDataFine())))){
					//in tal caso l'associazione e la richiesta si riferiscono allo stesso periodo, 
					//quindi sono potenzialmente in conflitto
					if(ric.rispettaRichiesta(a.getMacchina())){
						//In tal caso la macchina potrebbe essere tra le disponibili, quindi la devo considerare
							macchineAssociateHashMap.put((T)a.getMacchina(), (T)a.getMacchina());
					}
				}
			}
						
			//Se la macchina non � contenuta nell'hashmap, allora � libera e posso selezionarla
			for(T mac:disp) {
				if(!macchineAssociateHashMap.containsValue(mac)){
			    	Associazione a=new Associazione(ric,mac);
			    	return a;
			     }
			}
			return null;
		}
	}
	
	//FUNZIONI DI VERIFICA DEL CRITERIO DI PRENOTAZIONE
	
	static HashMap<Macchina,Prenotazione> generaPrenotazioni(Richiesta ric){
		HashMap<Macchina,Prenotazione>prenotazioni=new HashMap<Macchina,Prenotazione>();
			for(Lavoro lav:ric.getLavoriConnessi()){
				if(lavoroFinisceMenoDiUnaSettimanaPrima(lav,ric.getLavoro())||lavoroIniziaMenoDiUnaSettimanaDopo(lav,ric.getLavoro())){
					prenotazioni.putAll(prenotaMacchineDaLavoro(ric, lav));
				}
			}
		return prenotazioni;
	}
	
	static HashMap<Macchina,Prenotazione> prenotaMacchineDaLavoro(Richiesta ric, Lavoro lav){
		if(lav==null){
			return null;
		}
		else{
			HashMap<Macchina,Prenotazione>prenotazioni=new HashMap<Macchina,Prenotazione>();
			int d=lav.getDurata();
			for(Richiesta item:lav.getListaRichieste()){
				if(item.isSoddisfatta()){
					Macchina mac=item.getMacchina();
					if(ric.rispettaRichiesta(mac)){
						if(mac.isLibera(ric.getDataInizio(), ric.getDataFine())){
							if(prenotazioni.containsKey(mac)){
								Prenotazione p=prenotazioni.get(mac);
								if(d<p.getDurataLavoro()){
									p.setDurataLavoro(d);
								}
							}
							else{
								prenotazioni.put(mac, new Prenotazione(new Associazione(ric,item.getMacchina()),d));
							}
						}
					}
				}
			}
			return prenotazioni;
		}
	}
	
	//Verifico se il lavoro element finisce meno di una settimana prima rispetto a base
	static boolean lavoroFinisceMenoDiUnaSettimanaPrima(Lavoro element, Lavoro base){
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
	static boolean lavoroIniziaMenoDiUnaSettimanaDopo(Lavoro element, Lavoro base){
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
	
	static ArrayList<Richiesta> ordinaRichieste(ArrayList<Richiesta> richieste){
		//Utilizzo il metodo Collections.sort(List<T> list, Comparator<? super T> c)
		//Tale metodo implementa l'algoritmo MergeSort, che garantisce una complessità O(n log n)
		ArrayList<Richiesta> sortedRichieste=(ArrayList<Richiesta>)richieste.clone();
		Collections.sort(sortedRichieste, new GreedyEngine.RichiesteComparator());
		return sortedRichieste;
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la priorità del cantiere di ins è superiore a quella del cantiere di arr, restituisce true.
	 *se tale priorità è minore, restituisce false.
	 *se le priorità sono uguali, passo al confronto della durata dei lavori.
	 */
	static boolean ordinaPerPriorita(Richiesta ins, Richiesta arr){
		if(ins.getPriorita()==Priorita.ALTA){
			if(arr.getPriorita()==Priorita.ALTA){
				return ordinaPerDurata(ins, arr);
			}
			else{
				return true;
			}
		}
		else if(ins.getPriorita()==Priorita.MEDIA){
			if(arr.getPriorita()==Priorita.ALTA){
				return false;
			}
			else if(arr.getPriorita()==Priorita.MEDIA){
				return ordinaPerDurata(ins, arr);
			}
			else{
				return true;
			}
		}
		else{
			if(arr.getPriorita()==Priorita.ALTA||arr.getPriorita()==Priorita.MEDIA){
				return false;
			}
			else{
				return ordinaPerDurata(ins, arr);
			}
		}
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la durata del lavoro di ins è minore a quella del lavoro di arr, restituisce true.
	 *se tale durata è maggiore, restituisce false.
	 *se le durate sono uguali, passo al confronto della data d'inizio del lavoro.
	 */
	static boolean ordinaPerDurata(Richiesta ins, Richiesta arr){
		int dIns=ins.getDurata();
		int dArr=arr.getDurata();
		if( dIns<arr.getDurata()){
			return true;
		}
		else if(dIns>dArr){
			return false;
		}
		else{
			return ordinaPerDataIniziale(ins, arr);
		}
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la data d'inizio del lavoro di ins è minore a quella del lavoro di arr, restituisce true.
	 *se tale data d'inizio è maggiore, restituisce false.
	 *se le data d'inizio sono uguali, passo al confronto dei codici.
	 */
	static boolean ordinaPerDataIniziale(Richiesta ins, Richiesta arr){
		if(ins.getDataInizio().before(arr.getDataInizio())){
			return true;
		}
		else if(ins.getDataInizio().after(arr.getDataInizio())) {
			return false;
		}
		else{
			return ordinaPerCodice(ins,arr);
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
	static boolean ordinaPerCodice(Richiesta ins, Richiesta arr){
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
				if(GreedyEngine.ordinaPerPriorita(r1, r2)){
					return -1;
				}
				else{
					return 1;
				}
			}
		}
		
	}
}
