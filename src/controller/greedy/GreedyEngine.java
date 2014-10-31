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
import controller.greedy.data.Associazione;
import controller.greedy.data.Prenotazione;


/**
 * The Class GreedyEngine.
 */
public class GreedyEngine {


	/**
	 * Genera le associazioni.
	 *
	 * @param model L'interfaccia del modello
	 * @return ArrayList contenente le associazioni generate
	 */
	public static ArrayList<Associazione>generaAssociazioni(ModelInterface model){
		//Ordina le richieste in ordine decrescente di priorit�, partendo dalle richieste ottenute dal model
		ArrayList<Richiesta> sortedRichieste=GreedyEngine.ordinaRichieste(model.getRichiesteScoperte());
		//Inizializza l'ArrayList in output
		ArrayList<Associazione>associazioni=new ArrayList<Associazione>();
		//Per tutte le richieste, in ordine di priorit�
		for(int i=0;i<sortedRichieste.size();i++){
			//Prova ad ottenere un'associazione da prenotazioni
			Associazione a=GreedyEngine.selezionaPrenotazionePiuPromettente(associazioni, sortedRichieste.get(i));
			//Se ha generato un'associazione
			if(a!=null){
				//Aggiunge l'associazione all'arraylist di output
				associazioni.add(a);
			}
			else{
				//Altrimenti se non � stata generata un'associazione da prenotazioni cerca una macchina libera
				a=selezionaMacchinaLibera(model,sortedRichieste,associazioni,i);
				if(a!=null){
					//Se � stata generata un'associazione da macchina libera, gestisce l'associazione
					//Altrimenti, se nessuna associazione � stata generata non avevo macchine libere disponibili,
					//quindi non faccio niente.

					//setta a true il campo confermata
					a.setConfermata(true);
					//Aggiunge l'associazione all'arraylist di output
					associazioni.add(a);
				}
			}
		}
		//Terminato il ciclo, restituisce in output l'arraylist contenente le associazioni generate
		return associazioni;
	}

	//FASE 2: SELEZIONE DI UNA MACCHINA LIBERA------------------------------------------------------------------------------- 

	/**
	 * Seleziona una macchina libera.
	 *
	 * @param model L'interfaccia del modello
	 * @param sortedRichieste Le richieste ordinate
	 * @param alreadySelected L'array contenente le associazioni gi� generate
	 * @param i indice della richiesta attuale
	 * @return l'associazione generata
	 */
	static Associazione selezionaMacchinaLibera(ModelInterface model,ArrayList<Richiesta>sortedRichieste,ArrayList<Associazione>alreadySelected,int i){
		//Prelevo la richiesta corrente
		Richiesta ric=sortedRichieste.get(i);
		//Inizializzo l'associazione a null
		Associazione a=null;
		//Riempio l'arraylist con le macchine disponibili, utilizzando il componente model
		ArrayList<Macchina>disp=model.getElencoMacchineDisponibili(ric.getCodice());
		//Da tale elenco di macchine disponibili, devo eliminare le macchine associate precedentemente
		disp=GreedyEngine.rimuoviMacchineAssociate(disp, alreadySelected, ric);
		//Se la richiesta analizzata � l'ultima, allora non devo considerare le richieste successive
		if(i==sortedRichieste.size()-1){
			//Quindi mi limito a prelevare una delle macchine libere
			a=selezionaMacchinaSenzaPrenotazioni(ric, disp);
		}
		else{
			//Altrimenti selezioner� la macchina pi� promettente, 
			//valutanto il numero di richieste successive soddisfacibili da ciascuna macchina
			a=selezionaMacchinaSenzaPrenotazioni(ric,sortedRichieste, disp, i);
		}
		//Se la selezione di una macchina � andata a buon fine restituisce l'associazione, altrimenti restituisce null
		return a;
	}

	/**
	 * Seleziona una macchina senza prenotazioni.
	 *
	 * @param <T> the generic type
	 * @param ric La richiesta attuale
	 * @param sortedRichieste L'array di richieste ordinate
	 * @param disp Le macchine disponibili per questa richiesta
	 * @param i l'indice della richiesta attuale
	 * @return l'associazione generata
	 */
	static <T extends Macchina> Associazione selezionaMacchinaSenzaPrenotazioni(Richiesta ric, ArrayList<Richiesta>sortedRichieste,ArrayList<T>disp,int i){
		//Se ho macchine disponibili non faccio nulla, cerco quella pi� promettente
		if(!disp.isEmpty()){
			//Se ho almeno una macchina libera, generer� sicuramente l'associazione
			//Inizializzo l'array delle richieste in collisione
			ArrayList<Richiesta>richiesteInCollisione=new ArrayList<Richiesta>();
			//Per tutte le richieste successive a quella attualmente considerata
			for(int j=i+1;j<sortedRichieste.size();j++){
				//Prelevo la richiesta
				Richiesta r=sortedRichieste.get(j);
				//Se le due richieste sono cronologicamente in conflitto (hanno date sovrapposte)
				if(ric.collide(r)){
					//Se le due richieste hanno la possibilit� di utilizzare la stessa macchina (esistente o solo teorica)
					if(ric.inConflitto(r)){
						//Allora le due richieste sono in collisione, quindi aggiungo la richiesta all'array
						richiesteInCollisione.add(r);
					}
				}
			}
			
			//Cerco la macchina che soddisfa il minor numero di richieste successive.
			//min conterr� il numero min di richieste soddisfacibili, macchinaMin la macchina associata a tale numero�
			//Inizializzo min a -1 (valore impossibile in seguito, perch� avr� numeri maggiori o uguali a 0)
			int min=-1;
			//Inizialmente non seleziono alcuna macchina minima
			T macchinaMin=null;
			
			//Per tutte le macchine disponibili e non associate
			for(T mac:disp){
				//Inizializzo il contatore a 0
				int k=0;
				//Per tutte le richieste potenzialmente in conflitto
				for(Richiesta r:richiesteInCollisione){
					//Se la richiesta � soddisfatta dalla macchina
					if(r.rispettaRichiesta(mac)){
						//Allora incremento il contatore
						k++;
					}
				}
				//Se � la prima macchina analizzata (il minimo � a -1) oppure 
				//se la macchina attualmente analizzata soddisfa meno richieste della macchina migliore finora
				if(min==-1||k<min){
					//Sostituisco la macchina precedente con quella attuale
					macchinaMin=mac;
					//E memorizzo il suo numero di richieste soddisfatte
					min=k;
				}
			}
			//Restituisco l'associazione tra la richiesta attuale e la macchina pi� promettente.
			//Se pi� macchine avevano lo stesso numero di richieste, e tale numero � il minimo, 
			//la macchina selezionata � la prima analizzata
			return new Associazione(ric,macchinaMin);
		}
		//Se non ho macchine libere, non genero alcuna associazione
		return null;
	}

	/**
	 * Seleziona una macchina senza prenotazioni.
	 *
	 * @param <T> the generic type
	 * @param ric La richiesta attuale
	 * @param disp Le macchine disponibili
	 * @return l'associazione generata
	 */
	static <T extends Macchina> Associazione selezionaMacchinaSenzaPrenotazioni(Richiesta ric, ArrayList<T>disp){
		//Questa funzione viene lanciata solo nel caso in cui si stia analizzando l'ultima richiesta dell'array
		//Se ho almeno una macchina libera
		if(!disp.isEmpty()){
			//mi limito ad associare la richiesta corrente e la prima macchina nell'array
			return new Associazione(ric,disp.get(0));
		}
		//Altrimenti non genero associazioni
		return null;
	}

	/**
	 * Rimuove le macchine associate dall'array delle macchine disponibili.
	 *
	 * @param <T> the generic type
	 * @param disp Le macchine disponibili
	 * @param alreadySelected Le associazioni gi� generate
	 * @param ric La richiesta attuale
	 * @return La lista delle macchine libere e non associate
	 */
	static <T extends Macchina> ArrayList<T> rimuoviMacchineAssociate(ArrayList<T>disp,ArrayList<Associazione> alreadySelected, Richiesta ric){
		//Tutte le macchine contenute attualmente in disp sono macchine sicuramente libere, ma potenzialmente associate
		//Devo quindi eliminare tutte le macchine associate dalla lista, per evitare di preoccuparmene in seguito
		
		//Genero un hashmap, che conterr� tutte le macchine associate
		HashMap<T,T>macchineAssociateHashMap=new HashMap<T,T>();
		//Inizializzo l'array in output
		ArrayList<T>macchineLibere=new ArrayList<T>();
		//Per tutte le associazioni generate in precedenza
		for(Associazione a:alreadySelected){
			//Se l'associazione (o meglio la richiesta in essa contenuta) � sovrapposta cronologicamente 
			//alla richiesta corrente
			if(a.collide(ric)){
				//E inoltre se la macchina associata soddisfa la richiesta corrente
				if(ric.rispettaRichiesta(a.getMacchina())){
					//Allora devo inserire la macchina nell'hashmap, poich� tale macchina � da scartare nel seguito
					macchineAssociateHashMap.put((T)a.getMacchina(),(T)a.getMacchina());
				}
			}
		}
		//Per tutte le macchine disponibili
		for(T mac:disp){
			//Se la macchina considerata non � contenuta nell'hashmap
			if(!macchineAssociateHashMap.containsKey(mac)){
				//La macchina, oltre a essere disponibile, � anche non associata
				//Quindi la inserisco nell'array delle macchine libere
				macchineLibere.add(mac);
			}
		}
		//restituisco l'array contenente le macchine libere e non associate
		return macchineLibere;
	}



	//FASE 1: ASSOCIAZIONE TRAMITE PRENOTAZIONI---------------------------------------------------------------------------------


	/**
	 * Seleziona la prenotazione piu promettente tra quelle generate per la richiesta corrente.
	 *
	 * @param alreadySelected Le associazioni generate in precedenza
	 * @param ric la richiesta attuale
	 * @return l'associazione generata
	 */
	static Associazione selezionaPrenotazionePiuPromettente(ArrayList<Associazione>alreadySelected, Richiesta ric){
		//Se la richiesta � soddisfatta, non seleziono alcuna prenotazione
		if(ric.isSoddisfatta()){
			return null;
		}
		else{

			//Per sicurezza, provvedo a controllare se la richiesta era gi� stata soddisfatta tramite associazione
			for(Associazione asso:alreadySelected){
				//Se la richiesta � gi� stata associata, non seleziono alcuna prenotazione
				if(asso.getRichiesta().equals(ric)){
					return null;
				}
			}

			//Genero l'hashmap delle prenotazioni collegate alla richiesta corrente
			HashMap<Macchina,Prenotazione>prenotazioniHashMap=generaPrenotazioni(ric);

			//Analizzo tutte le associazioni generate in precedenza
			for(Associazione asso:alreadySelected){
				//Se l'associazione � sovrapposta alla richiesta, potrebbe invalidare delle prenotazioni
				if(!((ric.getDataFine().before(asso.getDataInizio()))||(ric.getDataInizio().after(asso.getDataFine())))){
					//In questo caso, l'associazione � potenzialmente in conflitto con la richiesta corrente.
					//Se la macchina associata � stata prenotata, la prenotazione relativa deve essere annullata
					//perch� non � possibile associare due volte la stessa macchina nello stesso periodo.
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
				//Se non � ancora stata selezionata alcuna prenotazione (prima esecuzione del ciclo)
				//oppure se la prenotazione corrente � la pi� promettente di quelle finora analizzate
				if(!selezionata||pre.getDurataLavoro()<temp.getDurataLavoro()){
					//allora sostituisco il valore attuale di temp con la prenotazione corrente
					temp=pre;
					//inoltre setto il valore di selezionata a true, per indicare che ho trovato almeno una prenotazione valida
					selezionata=true;
				}
			}

			//Se non � stato possibile selezionare alcuna prenotazione, allora temp contiene l'inizializzazione fittizia.
			//Evito quindi di restituire valori fasulli, e mi limito a non generare l'associazione
			if(!selezionata){
				return null;
			}
			else{
				//In tal caso temp contiene una prenotazione valida, in particolare la prenotazione valida pi� promettente.
				//Il risultato di questa funzione sar� quindi l'associazione contenuta nella prenotazione temp.
				return temp.select();
			}
		}

	}


	/**
	 * Genera le prenotazioni.
	 *
	 * @param ric la richiesta attuale
	 * @return l'HashMap contenente le prenotazioni per la richiesta attuale
	 */
	static HashMap<Macchina,Prenotazione> generaPrenotazioni(Richiesta ric){

		//Le prenotazioni vengono generate a partire dalle richieste dei lavori che rispettano i seguenti criteri:
		//	1.appartengono allo stesso cantiere della richiesta analizzata
		//	2.non sono cronologicamente sovrapposti al lavoro della richiesta analizzata
		//	3.inizia al massimo una settimana dopo la fine del lavoro della richiesta, 
		//	  oppure finisce al massimo una settimana prima dell'inizio del lavoro della richiesta.
		//Le richieste dei lavori compatibili con tali criteri verranno poste nell'array qui di seguito:
		ArrayList<Richiesta>richiesteCandidateArrayList=new ArrayList<Richiesta>();

		//I lavori per essere compatibili devono essere prima di tutto connessi alla richiesta corrente, cio�
		//appartenenti allo stesso cantiere di ric (primo criterio).
		//Si analizzano quindi tutti i lavori appartenenti a tale cantiere, uno alla volta.
		for(Lavoro lav:ric.getLavoriConnessi()){
			//Tra i lavori connessi a ric, gli unici compatibili sono quelli che rispettano il secondo e il terzo criterio.
			//La condizione seguente permette di verificare entrambi i criterti mancanti.
			if(lavoroFinisceMenoDiUnaSettimanaPrima(lav,ric.getLavoro())||lavoroIniziaMenoDiUnaSettimanaDopo(lav,ric.getLavoro())){
				//In tal caso lav � un lavoro compatibile, le sue richieste vengono quindi inserite nell'array delle richieste
				richiesteCandidateArrayList.addAll(lav.getListaRichieste());
			}
		}

		//Le macchine candidate ad essere prenotate verranno utilizzate come chiave in questa hashmap, 
		//il valore collegato � la durata minima dei lavori che vogliono prenotare questa macchina.
		HashMap<Macchina,Integer>macchinePrenotabiliHashMap=new HashMap<Macchina,Integer>();

		//Si analizzano tutte le richieste appartenenti ai lavori compatibili alla richiesta attuale
		for(Richiesta candidata:richiesteCandidateArrayList){
			//Se la richiesta attuale � soddisfatta, quindi se possiede una macchina
			if(candidata.isSoddisfatta()){
				//Tale macchina deve essere analizzata
				Macchina mac=candidata.getMacchina();
				//Nel caso in cui la macchina non sia gi� stata usata come chiave nell'hashmap
				if(!macchinePrenotabiliHashMap.containsKey(mac)){
					//devo verificare che la macchina sia adatta a soddisfare la richiesta attuale:
					//deve quindi rispettare le caratteristiche richieste ed essere libera nel periodo della richiesta attuale
					if(ric.rispettaRichiesta(mac) && mac.isLibera(ric.getDataInizio(), ric.getDataFine())){
						//Se la macchina � adatta, allora la utilizzo come chiave nell'hashmap e le associo la durata del lavoro
						macchinePrenotabiliHashMap.put(mac,candidata.getDurata());
					}
				}
				else{
					//Altrimenti, se la macchina � gi� stata usata come chiave nell'hashmap
					//significa che la macchina rispetta le caratteristiche necessarie ad essere prenotata.
					//mi limito quindi a valutare se la durata del lavoro attuale � minore di quella precedentemente valutata
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
			//Inserisco la prenotazione cos� ottenuta nell'hashmap che costituir� il risultato
			//La chiave � la macchina prenotabile, la prenotazione quella appena realizzata
			prenotazioni.put(mac, p);
		}
		//Restituisco l'hashmap delle prenotazioni, vuoto se non c'� alcuna prenotazione realizzabile per la richiesta attuale
		return prenotazioni;
	}


	/**
	 * Verifica se il lavoro element finisce meno di una settimana prima rispetto a base.
	 *
	 * @param element il lavoro di cui valutare la compatibilit�
	 * @param base il lavoro considerato
	 * @return true, if successful
	 */
	static boolean lavoroFinisceMenoDiUnaSettimanaPrima(Lavoro element, Lavoro base){
		//Verifico prima di tutto che i due lavori non siano lo stesso lavoro
		if(!element.equals(base)){
			//Verifico che la data di fine di element sia precedente alla data di inizio di base
			if(element.getDataFine().before(base.getDataInizio())){
				//In tal caso devo verificare che tra le due date ci siano al massimo 7 giorni di differenza.
				//Creo due variabili in cui copiare le date
				GregorianCalendar sx,dx;
				//E creo una variabile che conterr� i giorni di differenza
				int d;
				//Copio le due date in sx e dx
				sx=(GregorianCalendar)element.getDataFine().clone();
				dx=(GregorianCalendar)base.getDataInizio().clone();
				//inizializzo d
				d=0;
				//Finch� sx precede dx e finch� la differenza � minore di 8
				while(sx.before(dx)&&d<8){
					//Aumento di 1 giorno la data sx
					sx.add(Calendar.DAY_OF_MONTH, 1);
					//Incremento il contatore d
					d++;
				}
				if(d<=7){
					//Se il ciclo si � fermato prima di superare il 7, tra le due date c'� meno di una settimana
					//Quindi posso restituire true
					return true;
				}
				else{
					//Altrimenti tra le due date ci sono pi� di 7 giorni, quindi restituisco false
					return false;
				}
			}
			else{
				//Se la data finale di element � uguale o superiore alla data di inizio di base il criterio non � rispettato
				//Quindi restituisco false
				return false;
			}
		}
		else{
			//Se i due lavori sono in realt� lo stesso lavoro, restituisco false.
			return false;
		}
	}


	/**
	 * Verifica se il lavoro element inizia meno di una settimana dopo rispetto a base.
	 *
	 * @param element il lavoro di cui valutare la compatibilit�
	 * @param base il lavoro considerato
	 * @return true, if successful
	 */
	static boolean lavoroIniziaMenoDiUnaSettimanaDopo(Lavoro element, Lavoro base){
		//Verifico prima di tutto che i due lavori non siano lo stesso lavoro
		if(!element.equals(base)){
			//Verifico che la data di inizio di element sia successiva alla data di fine di base
			if(element.getDataInizio().after(base.getDataFine())){
				//In tal caso devo verificare che tra le due date ci siano al massimo 7 giorni di differenza.
				//Creo due variabili in cui copiare le date
				GregorianCalendar sx,dx;
				//E creo una variabile che conterr� i giorni di differenza
				int d;
				//Copio le due date in sx e dx
				sx=(GregorianCalendar)base.getDataFine().clone();
				dx=(GregorianCalendar)element.getDataInizio().clone();
				//inizializzo d
				d=0;
				//Finch� sx precede dx e finch� la differenza � minore di 8
				while(sx.before(dx)&&d<8){
					//Aumento di 1 giorno la data sx
					sx.add(Calendar.DAY_OF_MONTH, 1);
					//Incremento il contatore d
					d++;
				}
				if(d<=7){
					//Se il ciclo si � fermato prima di superare il 7, tra le due date c'� meno di una settimana
					//Quindi posso restituire true
					return true;
				}
				else{
					//Altrimenti tra le due date ci sono pi� di 7 giorni, quindi restituisco false
					return false;
				}
			}
			else{
				//Se la data iniziale di element � uguale o inferiore alla data finale di base il criterio non � rispettato
				//Quindi restituisco false
				return false;
			}
		}
		else{
			//Se i due lavori sono in realt� lo stesso lavoro, restituisco false.
			return false;
		}
	}



	//FASE 0: ORDINAMENTO-------------------------------------------------------------------------------------------------

	/**
	 * Ordina le richieste nell'array.
	 *
	 * @param richieste l'array delle richieste non ordinate
	 * @return l'array ordinato
	 */
	static ArrayList<Richiesta> ordinaRichieste(ArrayList<Richiesta> richieste){
		//Ordino le richieste per priorit� decrescenti

		//Inizialmente copio le richieste non ordinate nell'array sortedRichieste
		ArrayList<Richiesta> sortedRichieste=(ArrayList<Richiesta>)richieste.clone();

		//Utilizzo il metodo Collections.sort(List<T> list, Comparator<? super T> c)
		//Tale metodo implementa l'algoritmo MergeSort, che garantisce una complessità O(n log n)
		Collections.sort(sortedRichieste, new GreedyEngine.RichiesteComparator());

		//Restituisco quindi l'array contenente le richieste ordinate
		return sortedRichieste;
	}


	/**
	 *Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la priorità del cantiere di ins è superiore a quella del cantiere di arr, restituisce true.
	 *se tale priorità è minore, restituisce false.
	 *se le priorità sono uguali, passo al confronto della durata dei lavori.
	 *
	 * @param ins l'elemento da inserire nell'array
	 * @param arr l'elemento dell'array con cui confrontare l'elemento da inserire
	 * @return true, if successful
	 */
	static boolean ordinaPerPriorita(Richiesta ins, Richiesta arr){
		//Valuto nel caso di priorit� alta della richiesta da inserire
		if(ins.getPriorita()==Priorita.ALTA){
			if(arr.getPriorita()==Priorita.ALTA){
				//Se anche la priorit� della richiesta nell'array � alta, devo analizzare le durate dei lavori
				return ordinaPerDurata(ins, arr);
			}
			else{
				//Altrimenti ha priorit� maggiore, quindi restituisco true
				return true;
			}
		}
		//Nel caso di priorit� media della richiesta da inserire
		else if(ins.getPriorita()==Priorita.MEDIA){
			if(arr.getPriorita()==Priorita.ALTA){
				//Se la richiesta nell'array ha priorit� alta, ha priorit� sicuramente inferiore quindi restituisco false
				return false;
			}
			else if(arr.getPriorita()==Priorita.MEDIA){
				//Se le due priorit� si equivalgono, devo analizzare le durate dei lavori
				return ordinaPerDurata(ins, arr);
			}
			else{
				//Se la priorit� dell'elemento nell'array � bassa, ha priorit� maggiore quindi restituisco true
				return true;
			}
		}
		else{
			if(arr.getPriorita()==Priorita.ALTA||arr.getPriorita()==Priorita.MEDIA){
				//Se la priorit� dell'array � alta o media, allora sicuramente ha priorit� inferiore e restituisco false
				return false;
			}
			else{
				//Se le due priorit� si equivalgono, devo analizzare le durate dei lavori
				return ordinaPerDurata(ins, arr);
			}
		}
	}


	/**
	 *Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la durata del lavoro di ins è minore a quella del lavoro di arr, restituisce true.
	 *se tale durata è maggiore, restituisce false.
	 *se le durate sono uguali, passo al confronto della data d'inizio del lavoro.
	 *
	 * @param ins L'elemento da inserire nell'array
	 * @param arr l'elemento dell'array con cui confrontare l'elemento da inserire
	 * @return true, if successful
	 */
	static boolean ordinaPerDurata(Richiesta ins, Richiesta arr){
		//Devo valutare le durate dei due lavori, quindi le inserisco in queste due variabili
		int dIns=ins.getDurata();
		int dArr=arr.getDurata();
		//Se il lavoro di ins dura meno del lavoro di arr, restituisco true
		if( dIns<dArr){
			return true;
		}
		//Se il lavoro di ins dura pi� del lavoro di arr, restituisco false
		else if(dIns>dArr){
			return false;
		}
		//Infine se le durate si equivalgono devo valutare le date iniziali
		else{
			return ordinaPerDataIniziale(ins, arr);
		}
	}


	/**
	 *Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se la data d'inizio del lavoro di ins è minore a quella del lavoro di arr, restituisce true.
	 *se tale data d'inizio è maggiore, restituisce false.
	 *se le data d'inizio sono uguali, passo al confronto dei codici.
	 *
	 * @param ins L'elemento da inserire nell'array
	 * @param arr l'elemento dell'array con cui confrontare l'elemento da inserire
	 * @return true, if successful
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


	/**
	 *Restituisce true se la richiesta ins va inserita subito prima della richiesta arr.
	 *se il codice del cantiere di ins è minore del codice del cantiere di arr, restituisco true.
	 *se tale codice è maggiore, restituisco false.
	 *se il cantiere è lo stesso, passo al confronto dei codici dei lavori.
	 *se il codice del lavoro di ins è minore del codice del lavoro di arr, restituisco true.
	 *se tale codice è maggiore, restituisco false.
	 *se il lavoro è lo stesso, passo al confronto dei codici delle richieste.
	 *Il codice delle richieste è per forza diverso da richiesta a richiesta, quindi escludo l'uguaglianza.
	 *se il codice di ins è minore del codice di arr, restituisco true.
	 *se tale codice è maggiore, restituisco false.
	 *
	 * @param ins L'elemento da inserire nell'array
	 * @param arr l'elemento dell'array con cui confrontare l'elemento da inserire
	 * @return true, if successful
	 */
	static boolean ordinaPerCodice(Richiesta ins, Richiesta arr){
		//A codice minore corrisponde elemento inserito prima
		//Inizio dalla valutazione dei codici dei cantieri, 
		if(ins.getCodiceCantiere()<arr.getCodiceCantiere()){
			//Se il codice del cantiere di ins � minore del codice del cantiere di arr, restituisco true
			return true;
		}
		else if(ins.getCodiceCantiere()>arr.getCodiceCantiere()) {
			//Altrimenti se � maggiore restituisco false
			return false;
		}
		else{
			//Se i codici dei cantieri si equivalgono, passo a valutare i codici dei lavori
			if(ins.getCodiceLavoro()<arr.getCodiceLavoro()){
				//Se il codice del lavoro di ins � minore del codice del lavoro di arr, restituisco true
				return true;
			}
			else if(ins.getCodiceLavoro()>arr.getCodiceLavoro()) {
				//Altrimenti se � maggiore restituisco false
				return false;
			}
			else{
				//Se i codici dei lavori si equivalgono, passo a valutare i codici delle richieste
				if(ins.getCodice()<arr.getCodice()){
					//Se il codice di ins � minore a quello di arr, restituisco true
					return true;
				}
				else{
					//Altrimenti restituisco false.
					//In questo caso ricade anche la possibilit� che le richieste siano in realt� la stessa richiesta,
					//ma per l'utilizzo che dobbiamo fare di queste funzioni questo caso non dovrebbe mai accadere.
					return false;
				}
			}
		}
	}

	//COMPARATORI---------------------------------------------------------------------------------

	/**
	 * The Class RichiesteComparator.
	 */
	static class RichiesteComparator implements Comparator<Richiesta>{

		/**
		 * metodo comparatore basato sui metodi di ordinamento visti in precedenza.
		 *
		 * @param r1 Richiesta 1
		 * @param r2 Richiesta 2
		 * @return -1 se r1 ha priorit� maggiore di r2, 1 se r1 ha priorit� minore di r2, 0 se r1 e r2 sono la stessa richiesta
		 */
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
				//abbia priorit� maggire di r2, 1 nel caso in cui sia r2 ad avere priorit� maggiore
				//Per il criterio usato � impossibile che r1 e r2 siano diverse e abbiano uguale priorit�
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


