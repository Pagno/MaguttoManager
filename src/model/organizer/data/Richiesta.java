package model.organizer.data;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class Richiesta extends DefaultMutableTreeNode{
	


	private RichiestaMacchina caratteristiche;
	private Macchina macchina;
	
	/**   codice. */
	private static int ultimoCodice;

	private int codice;
	
	
	public Richiesta(RichiestaMacchina caratteristiche) {
		super();
		
		assignCodice();
		this.caratteristiche = caratteristiche;
		this.setMacchina(null);
	}
	
	public Richiesta(RichiestaMacchina caratteristiche, int codiceRichiesta) {
		super();
		
		if(codiceRichiesta>ultimoCodice){
			ultimoCodice=codiceRichiesta;
		}
		this.codice=codiceRichiesta;
		this.caratteristiche = caratteristiche;
		this.setMacchina(null);
	}
	
	
	/**
	 * Inits   codice.
	 */
	public static void initCodice(){
		ultimoCodice=0;
	}
	
	private void assignCodice(){
		ultimoCodice++;
		codice=ultimoCodice;
	}
	
	/**
	 * Gets   codice.
	 *
	 * @return   codice
	 */
	public int getCodice(){
		return codice;
	}

	public RichiestaMacchina getCaratteristiche() {
		return caratteristiche;
	}


	public void setCaratteristiche(RichiestaMacchina caratteristiche) {
		this.caratteristiche = caratteristiche;
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
			RichiestaCamion rc=(RichiestaCamion)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
			l.add("0");l.add("0");
			l.add("0");l.add("0");
			l.add("0");l.add("0");
		}else if(getCaratteristiche() instanceof RichiestaEscavatore){
			RichiestaEscavatore rc=(RichiestaEscavatore)getCaratteristiche();
			l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add(Integer.toString(rc.getMinProfondita()));l.add(Integer.toString(rc.getMaxProfondita()));
			l.add("0");l.add("0");
			
		}else if(getCaratteristiche() instanceof RichiestaGru){
			RichiestaGru rc=(RichiestaGru)getCaratteristiche();
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
			l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
			l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
			l.add("0");l.add("0");
			l.add(Integer.toString(rc.getMinAngoloRotazione()));l.add(Integer.toString(rc.getMaxAngoloRotazione()));
		}else if(getCaratteristiche() instanceof RichiestaRuspa){
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
		if (getClass() != obj.getClass())
			return false;
		Richiesta other = (Richiesta) obj;
	
		if (caratteristiche == null) {
			if (other.caratteristiche != null)
				return false;
		} else if (!caratteristiche.equals(other.caratteristiche))
			return false;
		if (codice != other.codice)
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
	 * Gets   next codice.
	 *
	 * @return   next codice
	 */
	static int getNextCodice(){
		return ultimoCodice+1;
	}


	public Macchina getMacchina() {
		return macchina;
	}


	public void setMacchina(Macchina macchina) {
		if(this.rispettaRichiesta(macchina)){
			this.macchina = macchina;
		}
		else{
			this.macchina=null;
		}
	}
	
	
}

