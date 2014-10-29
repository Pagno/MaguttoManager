package model.organizer.data;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.GregorianCalendar;
public final class Richiesta extends DefaultMutableTreeNode implements Comparable<Richiesta>{
	private RichiestaMacchina caratteristiche;
	private Macchina macchina;
	private Lavoro lavoro;
	/** codice. */
	private static int ultimoCodice;
	private int codice;
	public Richiesta(RichiestaMacchina caratteristiche, Lavoro lavoro) {
		super();
		assignCodice();
		this.caratteristiche = caratteristiche;
		this.lavoro=lavoro;
		this.setMacchina(null);
	}
	public Richiesta(RichiestaMacchina caratteristiche,Lavoro lavoro, int codiceRichiesta) {
		super();
		if(codiceRichiesta>ultimoCodice){
			ultimoCodice=codiceRichiesta;
		}
		this.codice=codiceRichiesta;
		this.caratteristiche = caratteristiche;
		this.lavoro=lavoro;
		this.setMacchina(null);
	}
	/**
	 * Inits codice.
	 */
	public static void initCodice(){
		ultimoCodice=0;
	}
	private void assignCodice(){
		ultimoCodice++;
		codice=ultimoCodice;
	}
	/**
	 * Gets codice.
	 *
	 * @return codice
	 */
	public int getCodice(){
		return codice;
	}
	public RichiestaMacchina getCaratteristiche() {
		return caratteristiche;
	}
	public void setCaratteristiche(RichiestaMacchina caratteristiche) {
		this.caratteristiche = caratteristiche;
		if(!caratteristiche.rispettaRichiesta(macchina)){
			this.setMacchina(null);
		}
	}
	public boolean isSoddisfatta() {
		if(macchina==null){
			return false;
		}
		else{
			return true;
		}
	}
	public ArrayList<String> getData() {
		ArrayList<String> l=new ArrayList<String>();
		l.add(Integer.toString(getCodice()));
		if(getCaratteristiche() instanceof RichiestaCamion){
			l.add("Camion");
			RichiestaCamion rc=(RichiestaCamion)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
			l.add("0");l.add("0");
			l.add("0");l.add("0");
			l.add("0");l.add("0");
		}else if(getCaratteristiche() instanceof RichiestaEscavatore){
			l.add("Escavatore");
			RichiestaEscavatore rc=(RichiestaEscavatore)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add(Integer.toString(rc.getMinProfondita()));l.add(Integer.toString(rc.getMaxProfondita()));
			l.add("0");l.add("0");
		}else if(getCaratteristiche() instanceof RichiestaGru){
			l.add("Gru");
			RichiestaGru rc=(RichiestaGru)getCaratteristiche();
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAngoloRotazione()));l.add(Integer.toString(rc.getMaxAngoloRotazione()));
		}else {
			//Resta solo il caso ruspa
			l.add("Ruspa");
			RichiestaRuspa rc=(RichiestaRuspa)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add("0");l.add("0");
			l.add("0");l.add("0");
		}
		return l;
	}
	@Override
	public String toString() {
		String s;
		s=getCodice() + " " + caratteristiche + " " + isSoddisfatta();
		if(isSoddisfatta()){
			s=s + " " + macchina.getCodice();
		}
		else{
			s=s + " null";
		}
		return s;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Richiesta))
			return false;
		Richiesta other = (Richiesta) obj;
		if (caratteristiche == null) {
			if (other.caratteristiche != null)
				return false;
		} else if (!caratteristiche.equals(other.caratteristiche))
			return false;
		if (codice != other.codice)
			return false;
		if (lavoro == null) {
			if (other.lavoro != null)
				return false;
		} else if (!lavoro.equals(other.lavoro))
			return false;
		if (macchina == null) {
			if (other.macchina != null)
				return false;
		} else if (!macchina.equals(other.macchina))
			return false;
		return true;
	}
	public boolean rispettaRichiesta(Macchina m){
		return caratteristiche.rispettaRichiesta(m);
	}
	//Metodi realizzati appositamente per il testing della classe.
	/**
	 * Gets next codice.
	 *
	 * @return next codice
	 */
	static int getNextCodice(){
		return ultimoCodice+1;
	}
	public Macchina getMacchina() {
		return macchina;
	}
	public void setMacchina(Macchina macchina) {
		if(this.macchina!=null){
			this.macchina.rimuoviRichiesta(this);
		}
		if(macchina==null){
			this.macchina=null;
		}
		else{
			if(this.rispettaRichiesta(macchina)){
				this.macchina = macchina;
				this.macchina.aggiungiRichiesta(this);
			}
			else{
				this.macchina=null;
			}
		}
	}
	@Override
	public int compareTo(Richiesta r) {
		if(this.equals(r)) {return 0;}
		if(this.lavoro.getDataInizio().compareTo(r.lavoro.getDataInizio())!=0){
			return this.lavoro.getDataInizio().compareTo(r.lavoro.getDataInizio());
		}
		else return this.codice-r.getCodice();
	}
	public GregorianCalendar getDataInizio(){
		return lavoro.getDataInizio();
	}
	public GregorianCalendar getDataFine(){
		return lavoro.getDataFine();
	}
	public Priorita getPriorita(){
		return lavoro.getPriorita();
	}
	public int getCodiceLavoro(){
		return lavoro.getCodice();
	}
	public int getCodiceCantiere(){
		return lavoro.getCodiceCantiere();
	}
	public Lavoro getLavoro(){
		return lavoro;
	}
	public ArrayList<Lavoro> getLavoriConnessi(){
		return lavoro.getLavoriConnessi();
	}
	public int getDurata(){
		return lavoro.getDurata();
	}
	public boolean collide(Richiesta other){
		if(this.getDataInizio().after(other.getDataFine())||this.getDataFine().before(other.getDataInizio())){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean inConflitto(Richiesta other){
		if(this.caratteristiche instanceof RichiestaCamion){
			if(other.getCaratteristiche() instanceof RichiestaCamion){
				RichiestaCamion rt,ro;
				rt=(RichiestaCamion)this.caratteristiche;
				ro=(RichiestaCamion)other.getCaratteristiche();
				boolean conflitto=gestisciLimiti(rt.getMinCapacita(),rt.getMaxCapacita(),ro.getMinCapacita(),ro.getMaxCapacita());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinPortata(),rt.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinLunghezza(),rt.getMaxLunghezza(),ro.getMinLunghezza(),ro.getMaxLunghezza());
				if(!conflitto){
					return false;
				}
				return true;
			}
		}
		if(this.caratteristiche instanceof RichiestaRuspa){
			if(other.getCaratteristiche() instanceof RichiestaRuspa){
				RichiestaRuspa rt,ro;
				rt=(RichiestaRuspa)this.caratteristiche;
				ro=(RichiestaRuspa)other.getCaratteristiche();
				
				boolean conflitto=gestisciLimiti(rt.getMinCapacita(),rt.getMaxCapacita(),ro.getMinCapacita(),ro.getMaxCapacita());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinPortata(),rt.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinAltezza(),rt.getMaxAltezza(),ro.getMinAltezza(),ro.getMaxAltezza());
				if(!conflitto){
					return false;
				}
				return true;
			}
		}
		if(this.caratteristiche instanceof RichiestaGru){
			if(other.getCaratteristiche() instanceof RichiestaGru){
				RichiestaGru rt,ro;
				rt=(RichiestaGru)this.caratteristiche;
				ro=(RichiestaGru)other.getCaratteristiche();
				
				boolean conflitto=gestisciLimiti(rt.getMinLunghezza(),rt.getMaxLunghezza(),ro.getMinLunghezza(),ro.getMaxLunghezza());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinPortata(),rt.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinAngoloRotazione(),rt.getMaxAngoloRotazione(),ro.getMinAngoloRotazione(),ro.getMaxAngoloRotazione());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinAltezza(),rt.getMaxAltezza(),ro.getMinAltezza(),ro.getMaxAltezza());
				if(!conflitto){
					return false;
				}
				return true;
			}
		}
		if(this.caratteristiche instanceof RichiestaEscavatore){
			if(other.getCaratteristiche() instanceof RichiestaEscavatore){
				RichiestaEscavatore rt,ro;
				rt=(RichiestaEscavatore)this.caratteristiche;
				ro=(RichiestaEscavatore)other.getCaratteristiche();
				
				boolean conflitto=gestisciLimiti(rt.getMinCapacita(),rt.getMaxCapacita(),ro.getMinCapacita(),ro.getMaxCapacita());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinPortata(),rt.getMaxPortata(),ro.getMinPortata(),ro.getMaxPortata());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinProfondita(),rt.getMaxProfondita(),ro.getMinProfondita(),ro.getMaxProfondita());
				if(!conflitto){
					return false;
				}
				conflitto=gestisciLimiti(rt.getMinAltezza(),rt.getMaxAltezza(),ro.getMinAltezza(),ro.getMaxAltezza());
				if(!conflitto){
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	boolean gestisciLimiti(int aMin,int aMax,int bMin, int bMax){
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