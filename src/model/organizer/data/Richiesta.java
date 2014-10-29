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
				boolean conflitto=false;
				//Caso capacità illimitate per una delle due richieste
				if((rt.getMinCapacita()==-1 && rt.getMaxCapacita()==-1)||(rt.getMinCapacita()==-1 && rt.getMaxCapacita()==-1)){
					conflitto=true;
				}
				//Caso capacità illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinCapacita()==-1&&ro.getMinCapacita()==-1)||(rt.getMinCapacita()==-1&&ro.getMinCapacita()<rt.getMaxCapacita())||(ro.getMinCapacita()==-1&&rt.getMinCapacita()<ro.getMaxCapacita()))){
					conflitto=true;
				}
				//Caso capacità illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxCapacita()==-1&&ro.getMaxCapacita()==-1)||(rt.getMaxCapacita()==-1&&ro.getMaxCapacita()>rt.getMinCapacita())||(ro.getMaxCapacita()==-1&&rt.getMaxCapacita()>ro.getMinCapacita()))){
					conflitto=true;
				}
				//Caso capacità limitate
				if(!conflitto&&(!((rt.getMinCapacita()>ro.getMaxCapacita())||(ro.getMinCapacita()>rt.getMaxCapacita())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso portate illimitate per una delle due richieste
				if((rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)||(rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)){
					conflitto=true;
				}
				//Caso portata illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinPortata()==-1&&ro.getMinPortata()==-1)||(rt.getMinPortata()==-1&&ro.getMinPortata()<rt.getMaxPortata())||(ro.getMinPortata()==-1&&rt.getMinPortata()<ro.getMaxPortata()))){
					conflitto=true;
				}
				//Caso portata illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxPortata()==-1&&ro.getMaxPortata()==-1)||(rt.getMaxPortata()==-1&&ro.getMaxPortata()>rt.getMinPortata())||(ro.getMaxPortata()==-1&&rt.getMaxPortata()>ro.getMinPortata()))){
					conflitto=true;
				}
				//Caso portate limitate
				if(!conflitto&&(!((rt.getMinPortata()>ro.getMaxPortata())||(ro.getMinPortata()>rt.getMaxPortata())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso lunghezze illimitate per una delle due richieste
				if((rt.getMinLunghezza()==-1 && rt.getMaxLunghezza()==-1)||(rt.getMinLunghezza()==-1 && rt.getMaxLunghezza()==-1)){
					conflitto=true;
				}
				//Caso lunghezza illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinLunghezza()==-1&&ro.getMinLunghezza()==-1)||(rt.getMinLunghezza()==-1&&ro.getMinLunghezza()<rt.getMaxLunghezza())||(ro.getMinLunghezza()==-1&&rt.getMinLunghezza()<ro.getMaxLunghezza()))){
					conflitto=true;
				}
				//Caso lunghezza illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxLunghezza()==-1&&ro.getMaxLunghezza()==-1)||(rt.getMaxLunghezza()==-1&&ro.getMaxLunghezza()>rt.getMinLunghezza())||(ro.getMaxLunghezza()==-1&&rt.getMaxLunghezza()>ro.getMinLunghezza()))){
					conflitto=true;
				}
				//Caso lunghezze limitate
				if(!conflitto&&(!((rt.getMinLunghezza()>ro.getMaxLunghezza())||(ro.getMinLunghezza()>rt.getMaxLunghezza())))){
					conflitto=true;
				}
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
				
				boolean conflitto=false;
				//Caso capacità illimitate per una delle due richieste
				if((rt.getMinCapacita()==-1 && rt.getMaxCapacita()==-1)||(rt.getMinCapacita()==-1 && rt.getMaxCapacita()==-1)){
					conflitto=true;
				}
				//Caso capacità illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinCapacita()==-1&&ro.getMinCapacita()==-1)||(rt.getMinCapacita()==-1&&ro.getMinCapacita()<rt.getMaxCapacita())||(ro.getMinCapacita()==-1&&rt.getMinCapacita()<ro.getMaxCapacita()))){
					conflitto=true;
				}
				//Caso capacità illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxCapacita()==-1&&ro.getMaxCapacita()==-1)||(rt.getMaxCapacita()==-1&&ro.getMaxCapacita()>rt.getMinCapacita())||(ro.getMaxCapacita()==-1&&rt.getMaxCapacita()>ro.getMinCapacita()))){
					conflitto=true;
				}
				//Caso capacità limitate
				if(!conflitto&&(!((rt.getMinCapacita()>ro.getMaxCapacita())||(ro.getMinCapacita()>rt.getMaxCapacita())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso portate illimitate per una delle due richieste
				if((rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)||(rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)){
					conflitto=true;
				}
				//Caso portata illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinPortata()==-1&&ro.getMinPortata()==-1)||(rt.getMinPortata()==-1&&ro.getMinPortata()<rt.getMaxPortata())||(ro.getMinPortata()==-1&&rt.getMinPortata()<ro.getMaxPortata()))){
					conflitto=true;
				}
				//Caso portata illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxPortata()==-1&&ro.getMaxPortata()==-1)||(rt.getMaxPortata()==-1&&ro.getMaxPortata()>rt.getMinPortata())||(ro.getMaxPortata()==-1&&rt.getMaxPortata()>ro.getMinPortata()))){
					conflitto=true;
				}
				//Caso portate limitate
				if(!conflitto&&(!((rt.getMinPortata()>ro.getMaxPortata())||(ro.getMinPortata()>rt.getMaxPortata())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso altezze illimitate per una delle due richieste
				if((rt.getMinAltezza()==-1 && rt.getMaxAltezza()==-1)||(rt.getMinAltezza()==-1 && rt.getMaxAltezza()==-1)){
					conflitto=true;
				}
				//Caso altezza illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinAltezza()==-1&&ro.getMinAltezza()==-1)||(rt.getMinAltezza()==-1&&ro.getMinAltezza()<rt.getMaxAltezza())||(ro.getMinAltezza()==-1&&rt.getMinAltezza()<ro.getMaxAltezza()))){
					conflitto=true;
				}
				//Caso altezza illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxAltezza()==-1&&ro.getMaxAltezza()==-1)||(rt.getMaxAltezza()==-1&&ro.getMaxAltezza()>rt.getMinAltezza())||(ro.getMaxAltezza()==-1&&rt.getMaxAltezza()>ro.getMinAltezza()))){
					conflitto=true;
				}
				//Caso altezze limitate
				if(!conflitto&&(!((rt.getMinAltezza()>ro.getMaxAltezza())||(ro.getMinAltezza()>rt.getMaxAltezza())))){
					conflitto=true;
				}
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
				
				boolean conflitto=false;
				//Caso lunghezze illimitate per una delle due richieste
				if((rt.getMinLunghezza()==-1 && rt.getMaxLunghezza()==-1)||(rt.getMinLunghezza()==-1 && rt.getMaxLunghezza()==-1)){
					conflitto=true;
				}
				//Caso lunghezza illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinLunghezza()==-1&&ro.getMinLunghezza()==-1)||(rt.getMinLunghezza()==-1&&ro.getMinLunghezza()<rt.getMaxLunghezza())||(ro.getMinLunghezza()==-1&&rt.getMinLunghezza()<ro.getMaxLunghezza()))){
					conflitto=true;
				}
				//Caso lunghezza illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxLunghezza()==-1&&ro.getMaxLunghezza()==-1)||(rt.getMaxLunghezza()==-1&&ro.getMaxLunghezza()>rt.getMinLunghezza())||(ro.getMaxLunghezza()==-1&&rt.getMaxLunghezza()>ro.getMinLunghezza()))){
					conflitto=true;
				}
				//Caso lunghezze limitate
				if(!conflitto&&(!((rt.getMinLunghezza()>ro.getMaxLunghezza())||(ro.getMinLunghezza()>rt.getMaxLunghezza())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso portate illimitate per una delle due richieste
				if((rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)||(rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)){
					conflitto=true;
				}
				//Caso portata illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinPortata()==-1&&ro.getMinPortata()==-1)||(rt.getMinPortata()==-1&&ro.getMinPortata()<rt.getMaxPortata())||(ro.getMinPortata()==-1&&rt.getMinPortata()<ro.getMaxPortata()))){
					conflitto=true;
				}
				//Caso portata illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxPortata()==-1&&ro.getMaxPortata()==-1)||(rt.getMaxPortata()==-1&&ro.getMaxPortata()>rt.getMinPortata())||(ro.getMaxPortata()==-1&&rt.getMaxPortata()>ro.getMinPortata()))){
					conflitto=true;
				}
				//Caso portate limitate
				if(!conflitto&&(!((rt.getMinPortata()>ro.getMaxPortata())||(ro.getMinPortata()>rt.getMaxPortata())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso angoli rotazione illimitati per una delle due richieste
				if((rt.getMinAngoloRotazione()==-1 && rt.getMaxAngoloRotazione()==-1)||(rt.getMinAngoloRotazione()==-1 && rt.getMaxAngoloRotazione()==-1)){
					conflitto=true;
				}
				//Caso angolo rotazione illimitato a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinAngoloRotazione()==-1&&ro.getMinAngoloRotazione()==-1)||(rt.getMinAngoloRotazione()==-1&&ro.getMinAngoloRotazione()<rt.getMaxAngoloRotazione())||(ro.getMinAngoloRotazione()==-1&&rt.getMinAngoloRotazione()<ro.getMaxAngoloRotazione()))){
					conflitto=true;
				}
				//Caso angolo rotazione illimitato a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxAngoloRotazione()==-1&&ro.getMaxAngoloRotazione()==-1)||(rt.getMaxAngoloRotazione()==-1&&ro.getMaxAngoloRotazione()>rt.getMinAngoloRotazione())||(ro.getMaxAngoloRotazione()==-1&&rt.getMaxAngoloRotazione()>ro.getMinAngoloRotazione()))){
					conflitto=true;
				}
				//Caso angoli rotazione limitati
				if(!conflitto&&(!((rt.getMinAngoloRotazione()>ro.getMaxAngoloRotazione())||(ro.getMinAngoloRotazione()>rt.getMaxAngoloRotazione())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso altezze illimitate per una delle due richieste
				if((rt.getMinAltezza()==-1 && rt.getMaxAltezza()==-1)||(rt.getMinAltezza()==-1 && rt.getMaxAltezza()==-1)){
					conflitto=true;
				}
				//Caso altezza illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinAltezza()==-1&&ro.getMinAltezza()==-1)||(rt.getMinAltezza()==-1&&ro.getMinAltezza()<rt.getMaxAltezza())||(ro.getMinAltezza()==-1&&rt.getMinAltezza()<ro.getMaxAltezza()))){
					conflitto=true;
				}
				//Caso altezza illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxAltezza()==-1&&ro.getMaxAltezza()==-1)||(rt.getMaxAltezza()==-1&&ro.getMaxAltezza()>rt.getMinAltezza())||(ro.getMaxAltezza()==-1&&rt.getMaxAltezza()>ro.getMinAltezza()))){
					conflitto=true;
				}
				//Caso altezze limitate
				if(!conflitto&&(!((rt.getMinAltezza()>ro.getMaxAltezza())||(ro.getMinAltezza()>rt.getMaxAltezza())))){
					conflitto=true;
				}
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
				
				boolean conflitto=false;
				//Caso capacità illimitate per una delle due richieste
				if((rt.getMinCapacita()==-1 && rt.getMaxCapacita()==-1)||(rt.getMinCapacita()==-1 && rt.getMaxCapacita()==-1)){
					conflitto=true;
				}
				//Caso capacità illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinCapacita()==-1&&ro.getMinCapacita()==-1)||(rt.getMinCapacita()==-1&&ro.getMinCapacita()<rt.getMaxCapacita())||(ro.getMinCapacita()==-1&&rt.getMinCapacita()<ro.getMaxCapacita()))){
					conflitto=true;
				}
				//Caso capacità illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxCapacita()==-1&&ro.getMaxCapacita()==-1)||(rt.getMaxCapacita()==-1&&ro.getMaxCapacita()>rt.getMinCapacita())||(ro.getMaxCapacita()==-1&&rt.getMaxCapacita()>ro.getMinCapacita()))){
					conflitto=true;
				}
				//Caso capacità limitate
				if(!conflitto&&(!((rt.getMinCapacita()>ro.getMaxCapacita())||(ro.getMinCapacita()>rt.getMaxCapacita())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso portate illimitate per una delle due richieste
				if((rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)||(rt.getMinPortata()==-1 && rt.getMaxPortata()==-1)){
					conflitto=true;
				}
				//Caso portata illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinPortata()==-1&&ro.getMinPortata()==-1)||(rt.getMinPortata()==-1&&ro.getMinPortata()<rt.getMaxPortata())||(ro.getMinPortata()==-1&&rt.getMinPortata()<ro.getMaxPortata()))){
					conflitto=true;
				}
				//Caso portata illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxPortata()==-1&&ro.getMaxPortata()==-1)||(rt.getMaxPortata()==-1&&ro.getMaxPortata()>rt.getMinPortata())||(ro.getMaxPortata()==-1&&rt.getMaxPortata()>ro.getMinPortata()))){
					conflitto=true;
				}
				//Caso portate limitate
				if(!conflitto&&(!((rt.getMinPortata()>ro.getMaxPortata())||(ro.getMinPortata()>rt.getMaxPortata())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso profondità illimitate per una delle due richieste
				if((rt.getMinProfondita()==-1 && rt.getMaxProfondita()==-1)||(rt.getMinProfondita()==-1 && rt.getMaxProfondita()==-1)){
					conflitto=true;
				}
				//Caso profondità illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinProfondita()==-1&&ro.getMinProfondita()==-1)||(rt.getMinProfondita()==-1&&ro.getMinProfondita()<rt.getMaxProfondita())||(ro.getMinProfondita()==-1&&rt.getMinProfondita()<ro.getMaxProfondita()))){
					conflitto=true;
				}
				//Caso profondità illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxProfondita()==-1&&ro.getMaxProfondita()==-1)||(rt.getMaxProfondita()==-1&&ro.getMaxProfondita()>rt.getMinProfondita())||(ro.getMaxProfondita()==-1&&rt.getMaxProfondita()>ro.getMinProfondita()))){
					conflitto=true;
				}
				//Caso profondità limitate
				if(!conflitto&&(!((rt.getMinProfondita()>ro.getMaxProfondita())||(ro.getMinProfondita()>rt.getMaxProfondita())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				conflitto=false;
				//Caso altezze illimitate per una delle due richieste
				if((rt.getMinAltezza()==-1 && rt.getMaxAltezza()==-1)||(rt.getMinAltezza()==-1 && rt.getMaxAltezza()==-1)){
					conflitto=true;
				}
				//Caso altezza illimitata a sx per almeno una delle due richieste
				if(!conflitto&&((rt.getMinAltezza()==-1&&ro.getMinAltezza()==-1)||(rt.getMinAltezza()==-1&&ro.getMinAltezza()<rt.getMaxAltezza())||(ro.getMinAltezza()==-1&&rt.getMinAltezza()<ro.getMaxAltezza()))){
					conflitto=true;
				}
				//Caso altezza illimitata a dx per almeno una delle due richieste
				if(!conflitto&&((rt.getMaxAltezza()==-1&&ro.getMaxAltezza()==-1)||(rt.getMaxAltezza()==-1&&ro.getMaxAltezza()>rt.getMinAltezza())||(ro.getMaxAltezza()==-1&&rt.getMaxAltezza()>ro.getMinAltezza()))){
					conflitto=true;
				}
				//Caso altezze limitate
				if(!conflitto&&(!((rt.getMinAltezza()>ro.getMaxAltezza())||(ro.getMinAltezza()>rt.getMaxAltezza())))){
					conflitto=true;
				}
				if(!conflitto){
					return false;
				}
				return true;
			}
		}
		return false;
	}
}