package controller.greedy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

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
		
		/*Fase 3: Le macchine sono state associate alle richieste prima per prenotazione e poi per disponibilità.
		 * Può però accadere che alcune richieste ad alta priorità siano rimaste scoperte perché richieste a basso livello 
		 * avevano prenotato una macchina disponibile, ottenendo così l'associazione ed impedendo alla richiesta ad alto livello 
		 * di ottenerla.
		 * Ora si procede quindi ad analizzare le richieste scoperte per priorità: nel caso in cui venga trovata una macchina 
		 * adatta a soddisfare la richiesta ad alta priorità ma associata ad una richiesta a bassa priorità, essa viene rubata
		 * dalla richiesta ad alto livello.
		 * Si effettua poi un controllo delle associazioni da prenotazione: se si sta analizzando una richiesta di una certa priorità,
		 * poiché esse vengono analizzate per priorità decrescenti tutte le associazioni con priorità superiori possono essere confermate,
		 * quindi vengono spostate nell'altra lista di associazioni, costruendo così l'output dell'algoritmo.
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
			Richiesta ric=sortedRichieste.get(0);//Prendo la richiesta scoperta con più alta priorità
			sortedRichieste.remove(0); //E la elimino dalla coda, non servirà più analizzarla
			//Le associazioni sono in ordine di priorità di richiesta decrescente, quindi scorro la lista al contrario
			for(int j=associazioniDaPrenotazioni.size()-1; j>=0;j--){ 
				Associazione asso=associazioniDaPrenotazioni.get(j);
				if(!isInferiore){
					if(comp.compare(ric, asso.getRichiesta())<0){ //Se ric ha priorità più alta, restituisce -1
						if(!isRubata){ //Se non ho ancora rubato la macchina devo controllare se rispetta la richiesta
							if(ric.rispettaRichiesta(asso.getMacchina())){ 
								//in tal caso ric ha priorità più alta, e la macchina rispetta la richiesta. 
								//Quindi rubo la macchina.
								Associazione rubata=new Associazione(ric,asso.getMacchina());
								//Visto che le richieste scoperte di priorità superiore sono già state analizzate, 
								//posso confermare questa associazione inserendola nelle associazioni definitive.
								associazioni.add(rubata);
								//Visto che ho già rubato la macchina, non devo più fare controlli sulle macchine
								isRubata=true;
								//Reinserisco la richiesta a cui ho rubato la macchina nelle richieste scoperte, 
								//potrebbe a sua volta rubarne un'altra in seguito
								boolean reinserito=false;
								for(int i=0; i<sortedRichieste.size();i++){ //Inserisco la richiesta scoperta nell'array alla giusta posizione
									if(comp.compare(asso.getRichiesta(), sortedRichieste.get(i))<0){ //Appena trovo una richiesta con priorità minore
										sortedRichieste.add(i, asso.getRichiesta());
										reinserito=true;
										break;
									}
								}
								if(!reinserito){ //In tal caso è la richiesta scoperta di più bassa priorità, la inserisco in fondo
									sortedRichieste.add(asso.getRichiesta());
								}
								//Elimino la vecchia associazione, che non esiste più
								associazioniDaPrenotazioni.set(j, null);
								//L'array contiene quindi elementi inutili, segnalo la necessità di fare pulizia
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
			//Se l'ultima richiesta scoperta era più alta di una richiesta non scoperta,
			//ci sono ancora delle associazioni da prenotazioni non ancora confermate.
			//Provvedo quindi a spostarle in associazioni
			associazioni.addAll(associazioniDaPrenotazioni);
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
	
	static <T extends Macchina> Associazione selezionaMacchinaSenzaPrenotazioni(Richiesta ric,ArrayList<T>disp,ArrayList<Associazione>associazioniDaLibere,ArrayList<Associazione>associazioniDaPrenotazioni){

		ArrayList<Associazione>alreadySelected=new ArrayList<Associazione>();
		alreadySelected.addAll(associazioniDaPrenotazioni);
		alreadySelected.addAll(associazioniDaLibere);

		//RIMUOVI
		System.out.println("Check 0");
		//END RIMUOVI
		
		/*Se non ci sono macchine disponibili (non controllo le già associate) o la richiesta è già soddisfatta
		  non seleziono nulla */
		if(ric.isSoddisfatta()||disp.size()==0){ 
			//RIMUOVI
			System.out.println("Check 1 - END");
			//END RIMUOVI
			return null;                         
		}
		else{
			//In tal caso è possibile che ci siano macchine disponibili, ma già associate.
			//RIMUOVI
			System.out.println("Check 2");
			//END RIMUOVI
			ArrayList<T>nonAssociate=new ArrayList<T>();
			nonAssociate.addAll(disp); //Per ora inserisco tutte le macchine disponibili nella lista
			boolean isAssociata=false;
			boolean isCleaned=true;
			for(Associazione a:alreadySelected){
				//RIMUOVI
				System.out.println("Check 3");
				//END RIMUOVI
				if(a.getRichiesta().equals(ric)){
					//RIMUOVI
					System.out.println("Check 4");
					//END RIMUOVI
					isAssociata=true; //Approfittiamo del ciclo per controllare se la richiesta è già stata associata.
					break;            //Se la richiesta è già associata, possiamo terminare il ciclo.
				}
				//RIMUOVI
				System.out.println("Check 5");
				//END RIMUOVI
				//RIMUOVI
				System.out.println("Ci sono " + nonAssociate.size() + " macchine");
				//END RIMUOVI
				for(int j=0;j<nonAssociate.size();j++){ //Cerchiamo le macchine nella lista che sono già state associate
					T mac=nonAssociate.get(j);
					//RIMUOVI
					System.out.println("Check 6");
					//END RIMUOVI
					//RIMUOVI
					System.out.println("Analizzo " + mac);
					//END RIMUOVI
					//controllo che la macchina non sia già  associata e quindi occupata temporaneamente
					//Le macchine erano già  libere, il controllo viene effettuato in reserveMacchineFromLavoro
					if(mac!=null){
						if(mac.equals(a.getMacchina())){
							//RIMUOVI
							System.out.println("Check 7");
							//END RIMUOVI
							if(!((ric.getDataFine().before(a.getDataInizio()))||(ric.getDataInizio().after(a.getDataFine())))){
								//se la macchina è la stessa e gli intervalli si sovrappongono, la macchina è già  associata
								//RIMUOVI
								System.out.println("Check 8");
								//END RIMUOVI
								nonAssociate.set(j, null); //Quindi la rimuovo dalla lista delle non associate
								isCleaned=false;
							}
						}
					}
				}
			}
			if(isAssociata){
				//RIMUOVI
				System.out.println("Check 9 - END");
				//END RIMUOVI
				return null; //Se la richiesta è già stata associata, non si genera una nuova associazione.
			}
			else{
				//RIMUOVI
				System.out.println("Check 10");
				//END RIMUOVI
				while(!isCleaned){ //La prima volta lo effettua solo se ho trovato macchine già associate
					//RIMUOVI
					System.out.println("Check 11");
					//END RIMUOVI
					isCleaned=!(nonAssociate.remove(null)); //Le macchine già associate sono state poste a null, quindi rimuovo tali occorrenze dalla lista
				}
				
				if(nonAssociate.size()==0){
					//In tal caso non ho nessuna macchina libera o non associata per soddisfare la richiesta
					//L'algoritmo non suggerisce alcuna macchina da inserire per tale richiesta
					//RIMUOVI
					System.out.println("Check 12 - END");
					//END RIMUOVI
					return null;
				}
				else{
					//Se arrivo qui, c'è almeno una macchina libera o ancora non associata che soddisfa la richiesta
					//Quindi è possibile sicuramente soddisfare la richiesta con almeno una macchina
					//RIMUOVI
					System.out.println("Check 13 - END");
					//END RIMUOVI
					Associazione genAsso=new Associazione(ric,nonAssociate.get(0));
					return genAsso;
				}
			}
		}
	}
	
	static Associazione selezionaPrenotazionePiuPromettente(ArrayList<Associazione>alreadySelected, Richiesta ric){
		//Se la richiesta è soddisfatta, non seleziono alcuna prenotazione
		if(ric.isSoddisfatta()){
			return null;
		}
		else{

			//Se la richiesta è già  stata associata, non seleziono alcuna prenotazione
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
				ArrayList<Prenotazione>list=generaPrenotazioni(ric);
				for(Prenotazione coppia:list){
						if(temp.getDurataLavoro()==-1||coppia.getDurataLavoro()<temp.getDurataLavoro()){
							boolean valid=true;
							for(Associazione a:alreadySelected){ //Scorre le associazioni generate in precedenza
								//controllo che la prenotazione non coinvolga una macchina già associata e quindi occupata temporaneamente
								//Le macchine erano già  libere, il controllo viene effettuato in reserveMacchineFromLavoro
								if(coppia.getMacchina().equals(a.getMacchina())){
									if(!((ric.getDataFine().before(a.getDataInizio()))||(ric.getDataInizio().after(a.getDataFine())))){
										//se la macchina è la stessa e le tempistiche si sovrappongono, la macchina è già  occupata
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
				
				if(temp.getDurataLavoro()==-1){
					return null;
				}
				else{
					return temp.getAssociazione();
				}
			}
		}
	}
	
	
	//FUNZIONI DI VERIFICA DEL CRITERIO DI PRENOTAZIONE
	
	static ArrayList<Prenotazione> generaPrenotazioni(Richiesta ric){
		ArrayList<Prenotazione>prenotazioni=new ArrayList<Prenotazione>();
			for(Lavoro lav:ric.getLavoriConnessi()){
				if(lavoroFinisceMenoDiUnaSettimanaPrima(lav,ric.getLavoro())||lavoroIniziaMenoDiUnaSettimanaDopo(lav,ric.getLavoro())){
					prenotaMacchineDaLavoro(ric, lav, prenotazioni);
				}
			}
		return prenotazioni;
	}
	
	static void prenotaMacchineDaLavoro(Richiesta ric, Lavoro lav,ArrayList<Prenotazione>prenotazioni){
		if(lav!=null){
			int d=lav.getDurata();
			for(Richiesta item:lav.getListaRichieste()){
				if(item.isSoddisfatta()){
					if(ric.rispettaRichiesta(item.getMacchina())){
						if(item.getMacchina().isLibera(ric.getDataInizio(), ric.getDataFine())){
							boolean isPresente=false;
							for(int i=0; i<prenotazioni.size();i++){
								if(prenotazioni.get(i).getMacchina().equals(item.getMacchina())){
									isPresente=true;
									if(d<prenotazioni.get(i).getDurataLavoro()){
										prenotazioni.get(i).setDurataLavoro(d);
									}
									break;
								}
							}
							if(!isPresente){
								prenotazioni.add(new Prenotazione(new Associazione(ric,item.getMacchina()),d));
							}
						}
					}
				}
			}
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
		//Tale metodo implementa l'algoritmo MergeSort, che garantisce una complessitÃ  O(n log n)
		ArrayList<Richiesta> sortedRichieste=(ArrayList<Richiesta>)richieste.clone();
		Collections.sort(sortedRichieste, new GreedyEngine.RichiesteComparator());
		return sortedRichieste;
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la prioritÃ  del cantiere di ins Ã¨ superiore a quella del cantiere di arr, restituisce true.
	 *se tale prioritÃ  Ã¨ minore, restituisce false.
	 *se le prioritÃ  sono uguali, passo al confronto della durata dei lavori.
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
	 *se la durata del lavoro di ins Ã¨ minore a quella del lavoro di arr, restituisce true.
	 *se tale durata Ã¨ maggiore, restituisce false.
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
	 *se la data d'inizio del lavoro di ins Ã¨ minore a quella del lavoro di arr, restituisce true.
	 *se tale data d'inizio Ã¨ maggiore, restituisce false.
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
	 *se il codice del cantiere di ins Ã¨ minore del codice del cantiere di arr, restituisco true.
	 *se tale codice Ã¨ maggiore, restituisco false.
	 *se il cantiere Ã¨ lo stesso, passo al confronto dei codici dei lavori.
	 *se il codice del lavoro di ins Ã¨ minore del codice del lavoro di arr, restituisco true.
	 *se tale codice Ã¨ maggiore, restituisco false.
	 *se il lavoro Ã¨ lo stesso, passo al confronto dei codici delle richieste.
	 *Il codice delle richieste Ã¨ per forza diverso da richiesta a richiesta, quindi escludo l'uguaglianza.
	 *se il codice di ins Ã¨ minore del codice di arr, restituisco true.
	 *se tale codice Ã¨ maggiore, restituisco false.
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
