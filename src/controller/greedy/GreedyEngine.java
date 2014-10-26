package controller.greedy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;

import model.ModelInterface;
import model.organizer.data.Camion;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Macchina;
import model.organizer.data.Priorita;
import model.organizer.data.Lavoro;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;
import controller.data.Associazione;
import controller.data.Prenotazione;

public class GreedyEngine {
	
	
		public static ArrayList<Associazione>generaAssociazioni(ModelInterface model){
			ArrayList<Richiesta> sortedRichieste=GreedyEngine.ordinaRichieste(model.getRichiesteScoperte());
			ArrayList<Associazione>associazioni=new ArrayList<Associazione>();
			for(int i=0;i<sortedRichieste.size();i++){
				Associazione a=GreedyEngine.selezionaPrenotazionePiuPromettente(associazioni, sortedRichieste.get(i));
				if(a!=null){
					a.setConfermata(true);
					associazioni.add(a);
				}
				else{
					a=selezionaMacchinaLibera(model,sortedRichieste,associazioni,i);
					if(a!=null){
						a.setConfermata(true);
						associazioni.add(a);
					}
				}
			}
			//RIMUOVERE
			
			for(int i=0;i<associazioni.size();i++){
				System.out.println("Posizione: " + i + " - Richiesta " + associazioni.get(i).getRichiesta().getCodice() + " Macchina " + associazioni.get(i).getMacchina().getCodice());
			}
			//END RIMUOVERE
			return associazioni;
		}
		
		//FASE 2: SELEZIONE DI UNA MACCHINA LIBERA------------------------------------------------------------------------------- 
		
		static Associazione selezionaMacchinaLibera(ModelInterface model,ArrayList<Richiesta>sortedRichieste,ArrayList<Associazione>alreadySelected,int i){
			Richiesta ric=sortedRichieste.get(i);
			Associazione a=null;
			if(ric.getCaratteristiche() instanceof RichiestaCamion){
				ArrayList<Camion>disp=model.getElencoCamionDisponibili(ric.getCodice(), ric.getCodiceLavoro());
				disp=GreedyEngine.rimuoviMacchineAssociate(disp, alreadySelected, ric);
				if(i==sortedRichieste.size()-1){
					a=selezionaMacchinaSenzaPrenotazioni(ric, disp);
				}
				else{
					a=selezionaMacchinaSenzaPrenotazioni(ric,sortedRichieste, disp, i);
				}
			}
			else if(ric.getCaratteristiche() instanceof RichiestaRuspa){
				ArrayList<Ruspa>disp=model.getElencoRuspeDisponibili(ric.getCodice(), ric.getCodiceLavoro());
				disp=GreedyEngine.rimuoviMacchineAssociate(disp, alreadySelected, ric);
				if(i==sortedRichieste.size()-1){
					a=selezionaMacchinaSenzaPrenotazioni(ric, disp);
				}
				else{
					a=selezionaMacchinaSenzaPrenotazioni(ric,sortedRichieste, disp, i);
				}
			}
			else if(ric.getCaratteristiche() instanceof RichiestaGru){
				ArrayList<Gru>disp=model.getElencoGruDisponibili(ric.getCodice(), ric.getCodiceLavoro());
				disp=GreedyEngine.rimuoviMacchineAssociate(disp, alreadySelected, ric);
				if(i==sortedRichieste.size()-1){
					a=selezionaMacchinaSenzaPrenotazioni(ric, disp);
				}
				else{
					a=selezionaMacchinaSenzaPrenotazioni(ric,sortedRichieste, disp, i);
				}
			}
			else if(ric.getCaratteristiche() instanceof RichiestaEscavatore){
				ArrayList<Escavatore>disp=model.getElencoEscavatoriDisponibili(ric.getCodice(), ric.getCodiceLavoro());
				disp=GreedyEngine.rimuoviMacchineAssociate(disp, alreadySelected, ric);
				if(i==sortedRichieste.size()-1){
					a=selezionaMacchinaSenzaPrenotazioni(ric, disp);
				}
				else{
					a=selezionaMacchinaSenzaPrenotazioni(ric,sortedRichieste, disp, i);
				}
			}
			return a;
		}
		
		static <T extends Macchina> Associazione selezionaMacchinaSenzaPrenotazioni(Richiesta ric, ArrayList<Richiesta>sortedRichieste,ArrayList<T>disp,int i){
		if(!disp.isEmpty()){
			ArrayList<Richiesta>richiesteInCollisione=new ArrayList<Richiesta>();
			for(int j=i+1;j<sortedRichieste.size();j++){
				Richiesta r=sortedRichieste.get(j);
				if(ric.collide(r)){
					if(ric.inConflitto(r)){
						richiesteInCollisione.add(r);
					}
				}
			}
			int min=-1;
			T macchinaMin=null;

			for(T mac:disp){
				int k=0;
				for(Richiesta r:richiesteInCollisione){
					if(r.rispettaRichiesta(mac)){
						k++;
					}
				}
				if(min==-1||k<min){
					macchinaMin=mac;
					min=k;
				}
			}

			return new Associazione(ric,macchinaMin);
		}
		return null;
	}
	
	static <T extends Macchina> Associazione selezionaMacchinaSenzaPrenotazioni(Richiesta ric, ArrayList<T>disp){
		if(!disp.isEmpty()){
			return new Associazione(ric,disp.get(0));
		}
		return null;
	}
	
	static <T extends Macchina> ArrayList<T> rimuoviMacchineAssociate(ArrayList<T>disp,ArrayList<Associazione> alreadySelected, Richiesta ric){
		HashMap<T,T>macchineAssociateHashMap=new HashMap<T,T>();
		ArrayList<T>macchineLibere=new ArrayList<T>();
		for(Associazione a:alreadySelected){
			if(a.collide(ric)){
				if(ric.rispettaRichiesta(a.getMacchina())){
					macchineAssociateHashMap.put((T)a.getMacchina(),(T)a.getMacchina());
				}
			}
		}
		for(T mac:disp){
			if(!macchineAssociateHashMap.containsKey(mac)){
				macchineLibere.add(mac);
			}
		}
		return macchineLibere;
	}
	

	
	//FASE 1: ASSOCIAZIONE TRAMITE PRENOTAZIONI---------------------------------------------------------------------------------
	
	
	static Associazione selezionaPrenotazionePiuPromettente(ArrayList<Associazione>alreadySelected, Richiesta ric){
		//Se la richiesta ï¿½ soddisfatta, non seleziono alcuna prenotazione
		if(ric.isSoddisfatta()){
			return null;
		}
		else{

			//Per sicurezza, provvedo a controllare se la richiesta era già stata soddisfatta tramite associazione
			for(Associazione asso:alreadySelected){
				//Se la richiesta ï¿½ giï¿½ stata associata, non seleziono alcuna prenotazione
				if(asso.getRichiesta().equals(ric)){
					return null;
				}
			}
			
			//Genero l'hashmap delle prenotazioni collegate alla richiesta corrente
			HashMap<Macchina,Prenotazione>prenotazioniHashMap=generaPrenotazioni(ric);
			
			//Analizzo tutte le associazioni generate in precedenza
			for(Associazione asso:alreadySelected){
				//Se l'associazione è sovrapposta alla richiesta, potrebbe invalidare delle prenotazioni
				if(!((ric.getDataFine().before(asso.getDataInizio()))||(ric.getDataInizio().after(asso.getDataFine())))){
					//In questo caso, l'associazione è potenzialmente in conflitto con la richiesta corrente.
					//Se la macchina associata è stata prenotata, la prenotazione relativa deve essere annullata
					//perché non è possibile associare due volte la stessa macchina nello stesso periodo.
					if(prenotazioniHashMap.containsKey(asso.getMacchina())){
						prenotazioniHashMap.remove(asso.getMacchina());
					}
				}
			}
			
			//Il flag "selezionata" viene inizialmente settato a false.
			//Il suo valore passa a true quando viene selezionata la prima prenotazione valida
			Boolean selezionata=false;
			
			//Inizializzazione fittizia della variabile temp, per evitare un errore inutile nella condizione successiva
			Prenotazione temp=new Prenotazione(null,-1);
			
			//Si estrae il set di chiavi dell'hashmap, per poterne effettuare la scansione
			Set<Macchina>keys=prenotazioniHashMap.keySet();

			//Per tutte le macchine presenti nell'insieme di chiavi
			for(Macchina m:keys){
				//Si preleva la prenotazione corrispondente
				Prenotazione pre=prenotazioniHashMap.get(m);
				//Se non è ancora stata selezionata alcuna prenotazione (prima esecuzione del ciclo)
				//oppure se la prenotazione corrente è la più promettente di quelle finora analizzate
				if(!selezionata||pre.getDurataLavoro()<temp.getDurataLavoro()){
					//allora sostituisco il valore attuale di temp con la prenotazione corrente
					temp=pre;
					//inoltre setto il valore di selezionata a true, per indicare che ho trovato almeno una prenotazione valida
					selezionata=true;
				}
			}
			
			//Se non è stato possibile selezionare alcuna prenotazione, allora temp contiene l'inizializzazione fittizia.
			//Evito quindi di restituire valori fasulli, e mi limito a non generare l'associazione
			if(!selezionata){
				return null;
			}
			else{
				//In tal caso temp contiene una prenotazione valida, in particolare la prenotazione valida più promettente.
				//Il risultato di questa funzione sarà quindi l'associazione contenuta nella prenotazione temp.
				return temp.getAssociazione();
			}
		}

	}
	
	
	static HashMap<Macchina,Prenotazione> generaPrenotazioni(Richiesta ric){
		
		//Le prenotazioni vengono generate a partire dalle richieste dei lavori che rispettano i seguenti criteri:
		//	1.appartengono allo stesso cantiere della richiesta analizzata
		//	2.non sono cronologicamente sovrapposti al lavoro della richiesta analizzata
		//	3.inizia al massimo una settimana dopo la fine del lavoro della richiesta, 
		//	  oppure finisce al massimo una settimana prima dell'inizio del lavoro della richiesta.
		//Le richieste dei lavori compatibili con tali criteri verranno poste nell'array qui di seguito:
		ArrayList<Richiesta>richiesteCandidateArrayList=new ArrayList<Richiesta>();
		
		//I lavori per essere compatibili devono essere prima di tutto connessi alla richiesta corrente, cioè
		//appartenenti allo stesso cantiere di ric (primo criterio).
		//Si analizzano quindi tutti i lavori appartenenti a tale cantiere, uno alla volta.
		for(Lavoro lav:ric.getLavoriConnessi()){
			//Tra i lavori connessi a ric, gli unici compatibili sono quelli che rispettano il secondo e il terzo criterio.
			//La condizione seguente permette di verificare entrambi i criterti mancanti.
			if(lavoroFinisceMenoDiUnaSettimanaPrima(lav,ric.getLavoro())||lavoroIniziaMenoDiUnaSettimanaDopo(lav,ric.getLavoro())){
				//In tal caso lav è un lavoro compatibile, le sue richieste vengono quindi inserite nell'array delle richieste
				richiesteCandidateArrayList.addAll(lav.getListaRichieste());
			}
		}
		
		//Le macchine candidate ad essere prenotate verranno utilizzate come chiave in questa hashmap, 
		//il valore collegato è la durata minima dei lavori che vogliono prenotare questa macchina.
		HashMap<Macchina,Integer>macchinePrenotabiliHashMap=new HashMap<Macchina,Integer>();
		
		//Si analizzano tutte le richieste appartenenti ai lavori compatibili alla richiesta attuale
		for(Richiesta candidata:richiesteCandidateArrayList){
			//Se la richiesta attuale è soddisfatta, quindi se possiede una macchina
			if(candidata.isSoddisfatta()){
				//Tale macchina deve essere analizzata
				Macchina mac=candidata.getMacchina();
				//Nel caso in cui la macchina non sia già stata usata come chiave nell'hashmap
				if(!macchinePrenotabiliHashMap.containsKey(mac)){
					//devo verificare che la macchina sia adatta a soddisfare la richiesta attuale:
					//deve quindi rispettare le caratteristiche richieste ed essere libera nel periodo della richiesta attuale
					if(ric.rispettaRichiesta(mac) && mac.isLibera(ric.getDataInizio(), ric.getDataFine())){
						//Se la macchina è adatta, allora la utilizzo come chiave nell'hashmap e le associo la durata del lavoro
						macchinePrenotabiliHashMap.put(mac,candidata.getDurata());
					}
				}
				else{
					//Altrimenti, se la macchina è già stata usata come chiave nell'hashmap
					//significa che la macchina rispetta le caratteristiche necessarie ad essere prenotata.
					//mi limito quindi a valutare se la durata del lavoro attuale è minore di quella precedentemente valutata
					if(candidata.getDurata()<macchinePrenotabiliHashMap.get(mac)){
						//In tal caso, sostituisco il valore della durata precedente con quello della durata attuale
						macchinePrenotabiliHashMap.put(mac,candidata.getDurata());
					}
				}
			}
		}
		
		//Ora macchinePrenotabiliHashMap utilizza come chiavi tutte le macchine prenotabili
		//Estraggo quindi il set delle chiavi per poterle scorrere e trasformare in prenotazioni
		Set<Macchina>keys=macchinePrenotabiliHashMap.keySet();

		//Inizializzo l'hashmap in output.
		HashMap<Macchina,Prenotazione>prenotazioni=new HashMap<Macchina,Prenotazione>();


		//Per tutte le macchine prenotabili
		for(Macchina mac:keys){
			//Creo la nuova associazione da inserire nella prenotazione
			Associazione a=new Associazione(ric,mac);
			//Creo la nuova prenotazione, formata dall'associazione a e dalla durata minore dei lavori coinvolti
			Prenotazione p=new Prenotazione(a,macchinePrenotabiliHashMap.get(mac));
			//Inserisco la prenotazione così ottenuta nell'hashmap che costituirà il risultato
			//La chiave è la macchina prenotabile, la prenotazione quella appena realizzata
			prenotazioni.put(mac, p);
		}
		//Restituisco l'hashmap delle prenotazioni, vuoto se non c'è alcuna prenotazione realizzabile per la richiesta attuale
		return prenotazioni;
	}
	
	//Verifico se il lavoro element finisce meno di una settimana prima rispetto a base
	static boolean lavoroFinisceMenoDiUnaSettimanaPrima(Lavoro element, Lavoro base){
		//Verifico prima di tutto che i due lavori non siano lo stesso lavoro
		if(!element.equals(base)){
			//Verifico che la data di fine di element sia precedente alla data di inizio di base
			if(element.getDataFine().before(base.getDataInizio())){
				//In tal caso devo verificare che tra le due date ci siano al massimo 7 giorni di differenza.
				//Creo due variabili in cui copiare le date
				GregorianCalendar sx,dx;
				//E creo una variabile che conterrà i giorni di differenza
				int d;
				//Copio le due date in sx e dx
				sx=(GregorianCalendar)element.getDataFine().clone();
				dx=(GregorianCalendar)base.getDataInizio().clone();
				//inizializzo d
				d=0;
				//Finché sx precede dx e finché la differenza è minore di 8
				while(sx.before(dx)&&d<8){
					//Aumento di 1 giorno la data sx
					sx.add(Calendar.DAY_OF_MONTH, 1);
					//Incremento il contatore d
					d++;
				}
				if(d<=7){
					//Se il ciclo si è fermato prima di superare il 7, tra le due date c'è meno di una settimana
					//Quindi posso restituire true
					return true;
				}
				else{
					//Altrimenti tra le due date ci sono più di 7 giorni, quindi restituisco false
					return false;
				}
			}
			else{
				//Se la data finale di element è uguale o superiore alla data di inizio di base il criterio non è rispettato
				//Quindi restituisco false
				return false;
			}
		}
		else{
			//Se i due lavori sono in realtà lo stesso lavoro, restituisco false.
			return false;
		}
	}
	
	//Verifico se il lavoro element inizia meno di una settimana dopo rispetto a base
	static boolean lavoroIniziaMenoDiUnaSettimanaDopo(Lavoro element, Lavoro base){
		//Verifico prima di tutto che i due lavori non siano lo stesso lavoro
		if(!element.equals(base)){
			//Verifico che la data di inizio di element sia successiva alla data di fine di base
			if(element.getDataInizio().after(base.getDataFine())){
				//In tal caso devo verificare che tra le due date ci siano al massimo 7 giorni di differenza.
				//Creo due variabili in cui copiare le date
				GregorianCalendar sx,dx;
				//E creo una variabile che conterrà i giorni di differenza
				int d;
				//Copio le due date in sx e dx
				sx=(GregorianCalendar)base.getDataFine().clone();
				dx=(GregorianCalendar)element.getDataInizio().clone();
				//inizializzo d
				d=0;
				//Finché sx precede dx e finché la differenza è minore di 8
				while(sx.before(dx)&&d<8){
					//Aumento di 1 giorno la data sx
					sx.add(Calendar.DAY_OF_MONTH, 1);
					//Incremento il contatore d
					d++;
				}
				if(d<=7){
					//Se il ciclo si è fermato prima di superare il 7, tra le due date c'è meno di una settimana
					//Quindi posso restituire true
					return true;
				}
				else{
					//Altrimenti tra le due date ci sono più di 7 giorni, quindi restituisco false
					return false;
				}
			}
			else{
				//Se la data iniziale di element è uguale o inferiore alla data finale di base il criterio non è rispettato
				//Quindi restituisco false
				return false;
			}
		}
		else{
			//Se i due lavori sono in realtà lo stesso lavoro, restituisco false.
			return false;
		}
	}
	
	
	//FASE 0: ORDINAMENTO-------------------------------------------------------------------------------------------------
	
	static ArrayList<Richiesta> ordinaRichieste(ArrayList<Richiesta> richieste){
		//Ordino le richieste per priorità decrescenti
		
		//Inizialmente copio le richieste non ordinate nell'array sortedRichieste
		ArrayList<Richiesta> sortedRichieste=(ArrayList<Richiesta>)richieste.clone();
		
		//Utilizzo il metodo Collections.sort(List<T> list, Comparator<? super T> c)
		//Tale metodo implementa l'algoritmo MergeSort, che garantisce una complessitÃ  O(n log n)
		Collections.sort(sortedRichieste, new GreedyEngine.RichiesteComparator());
		
		//Restituisco quindi l'array contenente le richieste ordinate
		return sortedRichieste;
	}
	
	/*Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la prioritÃ  del cantiere di ins Ã¨ superiore a quella del cantiere di arr, restituisce true.
	 *se tale prioritÃ  Ã¨ minore, restituisce false.
	 *se le prioritÃ  sono uguali, passo al confronto della durata dei lavori.
	 */
	static boolean ordinaPerPriorita(Richiesta ins, Richiesta arr){
		//Valuto nel caso di priorità alta della richiesta da inserire
		if(ins.getPriorita()==Priorita.ALTA){
			if(arr.getPriorita()==Priorita.ALTA){
				//Se anche la priorità della richiesta nell'array è alta, devo analizzare le durate dei lavori
				return ordinaPerDurata(ins, arr);
			}
			else{
				//Altrimenti ha priorità maggiore, quindi restituisco true
				return true;
			}
		}
		//Nel caso di priorità media della richiesta da inserire
		else if(ins.getPriorita()==Priorita.MEDIA){
			if(arr.getPriorita()==Priorita.ALTA){
				//Se la richiesta nell'array ha priorità alta, ha priorità sicuramente inferiore quindi restituisco false
				return false;
			}
			else if(arr.getPriorita()==Priorita.MEDIA){
				//Se le due priorità si equivalgono, devo analizzare le durate dei lavori
				return ordinaPerDurata(ins, arr);
			}
			else{
				//Se la priorità dell'elemento nell'array è bassa, ha priorità maggiore quindi restituisco true
				return true;
			}
		}
		else{
			if(arr.getPriorita()==Priorita.ALTA||arr.getPriorita()==Priorita.MEDIA){
				//Se la priorità dell'array è alta o media, allora sicuramente ha priorità inferiore e restituisco false
				return false;
			}
			else{
				//Se le due priorità si equivalgono, devo analizzare le durate dei lavori
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
		//Devo valutare le durate dei due lavori, quindi le inserisco in queste due variabili
		int dIns=ins.getDurata();
		int dArr=arr.getDurata();
		//Se il lavoro di ins dura meno del lavoro di arr, restituisco true
		if( dIns<dArr){
			return true;
		}
		//Se il lavoro di ins dura più del lavoro di arr, restituisco false
		else if(dIns>dArr){
			return false;
		}
		//Infine se le durate si equivalgono devo valutare le date iniziali
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
		//Se il lavoro di ins inizia prima del lavoro di arr, restituisco true
		if(ins.getDataInizio().before(arr.getDataInizio())){
			return true;
		}
		//Se il lavoro di ins inizia dopo del lavoro di arr, restituisco false
		else if(ins.getDataInizio().after(arr.getDataInizio())) {
			return false;
		}
		//Se i due lavori iniziano lo stesso giorno vado a valutare i codici delle due richieste
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
		//A codice minore corrisponde elemento inserito prima
		//Inizio dalla valutazione dei codici dei cantieri, 
		if(ins.getCodiceCantiere()<arr.getCodiceCantiere()){
			//Se il codice del cantiere di ins è minore del codice del cantiere di arr, restituisco true
			return true;
		}
		else if(ins.getCodiceCantiere()>arr.getCodiceCantiere()) {
			//Altrimenti se è maggiore restituisco false
			return false;
		}
		else{
			//Se i codici dei cantieri si equivalgono, passo a valutare i codici dei lavori
			if(ins.getCodiceLavoro()<arr.getCodiceLavoro()){
				//Se il codice del lavoro di ins è minore del codice del lavoro di arr, restituisco true
				return true;
			}
			else if(ins.getCodiceLavoro()>arr.getCodiceLavoro()) {
				//Altrimenti se è maggiore restituisco false
				return false;
			}
			else{
				//Se i codici dei lavori si equivalgono, passo a valutare i codici delle richieste
				if(ins.getCodice()<arr.getCodice()){
					//Se il codice di ins è minore a quello di arr, restituisco true
					return true;
				}
				else{
					//Altrimenti restituisco false.
					//In questo caso ricade anche la possibilità che le richieste siano in realtà la stessa richiesta,
					//ma per l'utilizzo che dobbiamo fare di queste funzioni questo caso non dovrebbe mai accadere.
					return false;
				}
			}
		}
	}
	
	//COMPARATORI---------------------------------------------------------------------------------
	
	static class RichiesteComparator implements Comparator<Richiesta>{
		@Override
		public int compare(Richiesta r1, Richiesta r2){
			//Classe comparator usata per ordinare le richieste secondo il criterio definito qui sopra.
			//Se le due richieste si equivalgono, restituisco 0
			if(r1.equals(r2)){
				return 0;
			}
			else{
				//Altrimenti devo ordinare le due richieste.
				//Io voglio un ordine decrescente quindi devo restituire -1 nel caso in cui r1 
				//abbia priorità maggire di r2, 1 nel caso in cui sia r2 ad avere priorità maggiore
				//Per il criterio usato è impossibile che r1 e r2 siano diverse e abbiano uguale priorità
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


