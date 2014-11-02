package model.organizer.data;

/**
 *   Questa classe astratta rappresenta l'interfaccia delle caratteristiche richieste dalle macchine. Definisce quindi 
 *   i metodi che devono essere implementati dalle sue specializzazioni, una specializzazione per ciascun tipo di macchina.
 *   <p>
 *   Definisce inoltre il metodo statico usato per confrontare gli intervalli di caratteristiche.
 *   
 *   @author Matteo Pagnoncelli
 *   @author Mauro Valota
 */
public abstract class RichiestaMacchina{
	
	/**
	 * Istanzia una nuova richiesta macchina.
	 */
	public RichiestaMacchina() {
		super();
	}
	
	/**
	 * Verifica se la macchina rispetta le caratteristiche richieste.
	 *
	 * @param m La macchina proposta
	 * @return true, se la macchina soddisfa i requisiti richiesti
	 */
	public abstract boolean rispettaRichiesta(Macchina m);
	
	/**
	 * Verifica se le caratteristiche inserite potrebbero potenzialmente essere soddisfatte da una macchina in grado di 
	 * soddisfare anche le caratteristiche correnti.<BR>
	 * Ci&ograve; significa che le due richieste esigono lo stesso tipo di macchina, e che tutti gli intervalli delle 
	 * caratteristiche tipiche di tali macchine sono almeno parzialmente sovrapposti.
	 *
	 * @param other L'altro set di caratteristiche richieste
	 * @return true, se le due richieste potrebbero potenzialmente contendersi una particolare macchina
	 */
	public abstract boolean inConflitto(RichiestaMacchina other);

	/**
	 * Indica se due intervalli di valori interi sono sovrapposti almeno parzialmente.<BR>
	 * Riceve in input l'estremo sinistro e destro di ciascuno dei due intervalli, e verifica se almeno uno degli interi è 
	 * condiviso da entrambi gli intervalli.<BR>
	 * Permette inoltre di gestire il caso in cui uno o più dei quattro estremi non sia definito, tali situazioni vengono 
	 * indicati con il valore convenzionale -1.
	 *
	 * @param aMin L'estremo sinistro dell'intervallo a
	 * @param aMax L'estremo destro dell'intervallo a
	 * @param bMin L'estremo sinistro dell'intervallo b
	 * @param bMax L'estremo destro dell'intervallo b
	 * @return true, se gli intervalli a e b sono sovrapposti
	 */
	static boolean gestisciLimiti(int aMin,int aMax,int bMin, int bMax){
		boolean conflitto=false;
		//Caso caratteristiche illimitate per una delle due richieste
		if((aMin==-1 && aMax==-1)||(bMin==-1 && bMax==-1)){
			conflitto=true;
		}
		//Caso caratteristica illimitata a sx per almeno una delle due richieste
		if(!conflitto&&((aMin==-1 && bMin==-1)||(aMin==-1 && bMin<aMax)||(bMin==-1 && aMin<bMax))){
			conflitto=true;
		}
		//Caso caratteristica illimitata a dx per almeno una delle due richieste
		if(!conflitto&&((aMax==-1 && bMax==-1)||(aMax==-1&&bMax>aMin)||(bMax==-1&& aMax>bMin))){
			conflitto=true;
		}
		//Caso caratteristiche limitate
		if(!conflitto&&(!((aMin>bMax)||(bMin>aMax)))){
			conflitto=true;
		}
		if(!conflitto){
			return false;
		}
		else return true;
	}
}
