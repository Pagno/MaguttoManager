package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import model.organizer.ElencoAssociazioni;
import model.organizer.ModelCamion;
import model.organizer.ModelCantiere;
import model.organizer.ModelEscavatore;
import model.organizer.ModelGru;
import model.organizer.ModelLavoro;
import model.organizer.ModelMacchina;
import model.organizer.ModelRuspa;
import model.organizer.data.Associazione;
import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Macchina;
import model.organizer.data.Ruspa;
import database.DBException;
import database.DatabaseInterface;

// 
/**
 *   Class ModelConnector.
 */
public class ModelConnector extends Observable implements ModelInterface{

	/**   mg. */
	private ModelGru mg;
	
	/**   mc. */
	private ModelCamion mc;
	
	/**   mr. */
	private ModelRuspa mr;
	
	/**   me. */
	private ModelEscavatore me;
	
	/**   lc. */
	private ModelCantiere lc;
	
	/**   ea. */
	private ElencoAssociazioni ea;
	
	/**   ml. */
	private ModelLavoro ml;
	
	/**   db. */
	private DatabaseInterface db;
	
	/**   istanza. */
	private static ModelConnector istanza;

	/**
	 * Instantiates a new model connector.
	 *
	 * @param data   data
	 */
	private ModelConnector(DatabaseInterface data){
		db=data;
		inizializza();
		//refreshData();
	}
	
	/**
	 * Gets   model connector.
	 *
	 * @param data   data
	 * @return   model connector
	 */
	public static synchronized ModelConnector getModelConnector(DatabaseInterface data){
		if(istanza==null){
			istanza=new ModelConnector(data);
		}
		return istanza;
	}
	
	/**
	 * Adds   gru observer.
	 *
	 * @param observer   observer
	 */
	public void addGruObserver(Observer observer){
		mg.addObserver(observer);
	}
	
	/**
	 * Adds   associazione observer.
	 *
	 * @param observer   observer
	 */
	public void addAssociazioniObserver(Observer observer){
		ea.deleteObservers();
		ea.addObserver(observer);
	}	
	public void addLavoroObserver(Observer observer){
	}
	/**
	 * Adds   ruspa observer.
	 *
	 * @param observer   observer
	 */
	public void addRuspaObserver(Observer observer){
		mr.addObserver(observer);
	}
	
	/**
	 * Adds   camion observer.
	 *
	 * @param observer   observer
	 */
	public void addCamionObserver(Observer observer){
		mc.addObserver(observer);
	}
	
	/**
	 * Adds   escavatore observer.
	 *
	 * @param observer   observer
	 */
	public void addEscavatoreObserver(Observer observer){
		me.addObserver(observer);
	}
	
	
	/**
	 * Adds   cantiere observer.
	 *
	 * @param observer   observer
	 */
	public void addCantiereObserver(Observer observer){
		lc.addObserver(observer);
	}
	
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#refreshData()
	 */
	@Override
	public void refreshData() {
		mg.getLista().clear();
		mc.getLista().clear();
		mr.getLista().clear();
		me.getLista().clear();
		ea.getElencoAssociazioniList().clear();
		lc.getLista().clear();
		
		try {
			db.connect();
			if(db.isEmpty()){

				//TABELLE INESISTENTI, VENGONO CREATE

				String qry = "create table APP.Macchina ( " +
						"Codice integer  primary key, " +
						"Produttore  varchar(20) not null, " +
						"Modello varchar(20) not null, " +
						"Tipo varchar(10) not null check (Tipo like 'Gru' or Tipo like 'Camion' or Tipo like 'Ruspa' or Tipo like 'Escavatore'), " +
						"CapacitaMax integer, " +
						"PortataMax integer, " +
						"AltezzaMax integer, " +
						"LunghezzaMax integer, " +
						"ProfonditaMax integer, " +
						"RotazioneMax integer)";
				db.update(qry);

				qry = "create table APP.Cantiere ( " +
						"Codice integer  primary key, " +
						"Nome varchar(30) not null, " +
						"DataApertura date not null, " +
						"DataChiusura date not null, " +
						"Indirizzo varchar(50) not null)";
				db.update(qry);

				qry = "create table APP.Associazione ( " +
						"Id integer primary key, " +
						"CodiceMacchina integer references APP.Macchina(Codice), " +
						"CodiceCantiere integer references APP.Cantiere(Codice), " +
						"DataInizio date not null, " +
						"DataFine date not null)";
				db.update(qry);
				System.out.println("DB creato");
			}
			else{
				//db.disconnect();
				//Tabelle esistenti, carico il DB
				loadDB();
			}
			db.disconnect();
		} 
		catch (DBException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#storeData()
	 */
	@Override
	public void storeData() {
		try {
			System.out.println("Salvataggio Dati.");
			db.connect();
			//POPOLARE IL DATABASE
			db.emptyTable("Associazione");
			db.emptyTable("Macchina");
			db.emptyTable("Cantiere");

			for(Cantiere item:lc.getLista()){
				String qry = "insert into APP.Cantiere (Codice,Nome,DataApertura,DataChiusura,Indirizzo)"+
						"values(" + item.getCodice() + ",'" + item.getNomeCantiere() + "','" + 
						item.getStrDataApertura() + "','" + item.getStrDataChiusura() + 
						"','" + item.getIndirizzo() + "')" ;
				db.update(qry);
			}

			for(Camion item:mc.getLista()){

				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,LunghezzaMax)"+
						"values(" + item.getCodice() + ",'" + item.getProduttore() + "','" + 
						item.getModello() + "','Camion'," + item.getCapacitaMassima() + 
						"," + item.getPortataMassima() + "," + item.getLunghezza() + ")" ;
				db.update(qry);
			}	
			for(Escavatore item:me.getLista()){
				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax,ProfonditaMax)"+
						"values(" + item.getCodice() + ",'" + item.getProduttore() + "','" + 
						item.getModello() + "','Escavatore'," + item.getCapacitaMassima() + 
						"," + item.getPortataMassima() + "," + item.getAltezzaMassima() + 
						"," + item.getProfonditaMassima() + ")" ;
				db.update(qry);
			}
			for(Gru item:mg.getLista()){
				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,PortataMax,AltezzaMax,LunghezzaMax,RotazioneMax)"+
						"values(" + item.getCodice() + ",'" + item.getProduttore() + 
						"','" + item.getModello() + "','Gru'," +  item.getPortataMassima() + "," + item.getAltezza() + 
						"," + item.getLunghezza() + "," + item.getAngoloRotazione() + ")" ;
				db.update(qry);
			}
			for(Ruspa item:mr.getLista()){
				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax)"+
						"values(" + item.getCodice() + ",'" + item.getProduttore() + "','" + 
						item.getModello() + "','Ruspa'," + item.getCapacitaMassima() + 
						"," + item.getPortataMassima() + "," + item.getAltezzaMassima() + ")" ;
				db.update(qry);
			}
			for(Associazione item:ea.getElencoAssociazioniList()){
				String qry = "insert into APP.Associazione (Id,CodiceMacchina,CodiceCantiere,DataInizio,DataFine)"+
						"values(" + item.getID() + "," + item.getMacchina().getCodice() + "," + 
						item.getCantiere().getCodice() + ",'" + item.getStrDataInizio() + 
						"','" + item.getStrDataFine() + "')" ;
				db.update(qry);
			}

			db.disconnect();
		} 
		catch (DBException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#eliminaMacchina(int)
	 */
	@Override
	public boolean eliminaMacchina(int mCode) {
		boolean found=false;
		if(mc.isCamion(mCode)){
			found = mc.eliminaCamion(mCode);
		}
		if((!found) && me.isEscavatore(mCode)){
			found = me.eliminaEscavatore(mCode);
		}
		if((!found) && mg.isGru(mCode)){
			found = mg.eliminaGru(mCode);
		}
		if((!found) && mr.isRuspa(mCode)){
			found = mr.eliminaRuspa(mCode);
		}
		return found;
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#aggiungiGru(java.lang.String, java.lang.String, int, int, int, int)
	 */
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		mg.aggiungiGru(produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#modificaGru(int, java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public void modificaGru(int codice, String produttore, String modello,int rotazione, int portata, int lunghezza, int altezza) {
		mg.modificaGru(codice, produttore, modello, rotazione, portata, lunghezza, altezza);
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#aggiungiCamion(java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public void aggiungiCamion(String produttore, String Modello, int capacita,	int portata, int lunghezza) {
		mc.aggiungiCamion(produttore, Modello, capacita, portata, lunghezza);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#modificaCamion(int, java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public void modificaCamion(int codice, String produttore, String Modello,int capacita, int portata, int lunghezza) {
		mc.modificaCamion(codice, produttore, Modello, capacita, portata, lunghezza);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#aggiungiRuspa(java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public void aggiungiRuspa(String produttore, String Modello, int capacita,int portata, int altezza) {
		mr.aggiungiRuspa(produttore, Modello, capacita, portata, altezza);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#modificaRuspa(int, java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita, int portata, int altezza) {
		mr.modificaRuspa(codice, produttore, Modello, capacita, portata, altezza);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#aggiungiEscavatore(java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public void aggiungiEscavatore(String produttore, String Modello,int capacita, int portata, int altezza, int profondita) {
		me.aggiungiEscavatore(produttore, Modello, capacita, portata, altezza, profondita);
		
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#modificaEscavatore(int, java.lang.String, java.lang.String, int, int, int, int)
	 */
	@Override
	public void modificaEscavatore(int codice, String produttore,String Modello, int capacita, int portata, int altezza,int profondita) {
		me.modificaEscavatore(codice, produttore, Modello, capacita, portata, altezza, profondita);		
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#aggiungiCantiere(java.lang.String, java.lang.String, java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public int aggiungiCantiere(String nomeCantiere, String indirizzo, GregorianCalendar dataApertura, GregorianCalendar dataChiusura) {
		return lc.aggiungiCantiere(nomeCantiere, indirizzo, dataApertura, dataChiusura);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#modificaCantiere(int, java.lang.String, java.lang.String, java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public void modificaCantiere(int codice, String nomeCantiere, String indirizzo, GregorianCalendar dataApertura, GregorianCalendar dataChiusura) {
			lc.modificaCantiere(codice, nomeCantiere, indirizzo, dataApertura, dataChiusura);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#eliminaCantiere(int)
	 */
	@Override
	public boolean eliminaCantiere(int codice) {
		boolean check=true;
		ArrayList<Integer> id=new ArrayList<Integer>();
		for(int i=0;i<ea.getElencoAssociazioniList().size();i++){
			if(ea.getElencoAssociazioniList().get(i).getCantiere().getCodice()==codice)
				id.add(ea.getElencoAssociazioniList().get(i).getID());
				//check=check && eliminaAssociazione(ea.getElencoAssociazioniList().get(i).getID());
			
		}
		
		for(int i=0;i<id.size();i++){
			check=check && eliminaAssociazione(id.get(i));
		}
		
		return check && lc.rimuoviCantiere(codice);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#aggiungiAssociazione(java.lang.Integer, java.lang.Integer, java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public void aggiungiAssociazione(Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		ea.inserisciAssociazione(getMacchina(codiceMacchina), lc.getCantiere(codiceCantiere), dataInizio, dataFine);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#modificaAssociazione(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public void modificaAssociazione(Integer codice, Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		ea.modificaAssociazione(codice, getMacchina(codiceMacchina), lc.getCantiere(codiceCantiere), dataInizio, dataFine);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#eliminaAssociazione(int)
	 */
	@Override
	public boolean eliminaAssociazione(int codice) {
		return ea.eliminaAssociazione(codice);
	}
	
	/**
	 * Elimina associazioni cantiere.
	 *
	 * @param codiceCantiere   codice cantiere
	 * @return true, if successful
	 */
	public boolean eliminaAssociazioniCantiere(int codiceCantiere) {
		return ea.eliminaAssociazione(codiceCantiere);
	}
	public ArrayList<Associazione> elencoAssociazioniCantiere(int codiceCantiere) {
		return ea.getElencoAssociazioniList(codiceCantiere);
	}
	/**
	 * Load db.
	 */
	private void loadDB(){

		//carica le macchine
		loadCamion();
		loadEscavatori();
		loadRuspe();
		loadGru();

		//carica i cantieri
		loadCantieri();

		//carica le associazioni
		loadAssociazioni();

	}

	/**
	 * Load camion.
	 */
	private void loadCamion(){
		//carica i camion
		try {
			//db.connect();
			String qry="select * from APP.Macchina where Tipo='Camion'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				mc.caricaCamion(res.getInt("Codice"),res.getString("Produttore"), res.getString("Modello"), res.getInt("CapacitaMax"), res.getInt("PortataMax"), res.getInt("LunghezzaMax"));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Load escavatori.
	 */
	private void loadEscavatori(){
		//carica gli escavatori
		try {
			//db.connect();
			String qry="select * from APP.Macchina where Tipo='Escavatore'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				me.caricaEscavatore(res.getInt("Codice"), res.getString("Produttore"), res.getString("Modello"), res.getInt("CapacitaMax"), res.getInt("PortataMax"), res.getInt("AltezzaMax"), res.getInt("ProfonditaMax"));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Load ruspe.
	 */
	private void loadRuspe(){
		//carica le ruspe
		try {
			//db.connect();
			String qry="select * from APP.Macchina where Tipo='Ruspa'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				mr.caricaRuspa(res.getInt("Codice"), res.getString("Produttore"), res.getString("Modello"), res.getInt("CapacitaMax"), res.getInt("PortataMax"), res.getInt("AltezzaMax"));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Load gru.
	 */
	private void loadGru(){
		//carica le gru
		try {
			//db.connect();
			String qry="select * from APP.Macchina where Tipo='Gru'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				mg.caricaGru(res.getInt("Codice"), res.getString("Produttore"), res.getString("Modello"), res.getInt("RotazioneMax"), res.getInt("PortataMax"), res.getInt("LunghezzaMax"), res.getInt("AltezzaMax"));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Load cantieri.
	 */
	private void loadCantieri(){
		//carica i cantieri
		try {
			//db.connect();
			String qry="select * from APP.Cantiere";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				lc.caricaCantiere(res.getInt("Codice"), res.getString("Nome"), res.getString("Indirizzo"), convertToDate(res.getString("DataApertura")), convertToDate(res.getString("DataChiusura")));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Load associazioni.
	 */
	private void loadAssociazioni(){
		//carica le associazioni
		try {
			//db.connect();
			String qry="select * from APP.Associazione";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				ea.caricaAssociazione(res.getInt("Id"), getMacchina(res.getInt("CodiceMacchina")), lc.getCantiere(res.getInt("CodiceCantiere")), convertToDate(res.getString("DataInizio")), convertToDate(res.getString("DataFine")));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Gets   macchina.
	 *
	 * @param mCode   m code
	 * @return   macchina
	 */
	private Macchina getMacchina(Integer mCode){
		if(mc.isCamion(mCode)){
			return mc.getCamion(mCode);
		}
		if(me.isEscavatore(mCode)){
			return me.getEscavatore(mCode);
		}
		if(mg.isGru(mCode)){
			return mg.getGru(mCode);
		}
		if(mr.isRuspa(mCode)){
			return mr.getRuspa(mCode);
		}
		return null;
	}
	/**
	 * Gets   cantiere.
	 *
	 * @param mCode   m code
	 * @return   macchina
	 */
	private Cantiere getCantiere(Integer mCode){
		return lc.getCantiere(mCode);
	}
	/**
	 * Convert to date.
	 *
	 * @param datestr   datestr
	 * @return   gregorian calendar
	 */
	private GregorianCalendar convertToDate(String datestr){
		String year = datestr.substring(0,4);
		String month = datestr.substring(5,7);
		String day = datestr.substring(8,10);
		int yint=Integer.parseInt(year);
		int mint=Integer.parseInt(month);
		if(mint==1){mint=Calendar.JANUARY;}
		else if(mint==2){mint=Calendar.FEBRUARY;}
		else if(mint==3){mint=Calendar.MARCH;}
		else if(mint==4){mint=Calendar.APRIL;}
		else if(mint==5){mint=Calendar.MAY;}
		else if(mint==6){mint=Calendar.JUNE;}
		else if(mint==7){mint=Calendar.JULY;}
		else if(mint==8){mint=Calendar.AUGUST;}
		else if(mint==9){mint=Calendar.SEPTEMBER;}
		else if(mint==10){mint=Calendar.OCTOBER;}
		else if(mint==11){mint=Calendar.NOVEMBER;}
		else if(mint==12){mint=Calendar.DECEMBER;}
		int dint=Integer.parseInt(day);
		return new GregorianCalendar(yint,mint,dint);
	}

	/**
	 * Inizializza.
	 */
	private void inizializza() {
		ModelMacchina.initCodice();
		mg = ModelGru.getModelGru();
		mc = ModelCamion.getModelCamion();
		mr = ModelRuspa.getModelRuspa();
		me = ModelEscavatore.getModelEscavatore();
		ml = ModelLavoro.getModelLavoro();
		ea = ElencoAssociazioni.getElencoAssociazioni();
		lc = ModelCantiere.getModelCantiere();
	}

	//da rimuovere
	/**
	 * Pubblica contenuto.
	 */
	public void pubblicaContenuto(){
		System.out.println("---CAMION-------------------");
		System.out.println(mc.toString());
		System.out.println("---ESCAVATORI---------------");
		System.out.println(me.toString());
		System.out.println("---RUSPE--------------------");
		System.out.println(mr.toString());
		System.out.println("---GRU----------------------");
		System.out.println(mg.toString());
		System.out.println("---CANTIERI-----------------");
		System.out.println(lc.toString());
		System.out.println("---ASSOCIAZIONI-------------");
		System.out.println(ea.toString());
	}
	
	/**
	 * Elenco ruspe disponibili.
	 *
	 * @param inizio   inizio
	 * @param fine   fine
	 * @return   array list
	 */
	public ArrayList<Ruspa> elencoRuspeDisponibili(GregorianCalendar inizio,GregorianCalendar fine){
		ArrayList<Ruspa> ruspe=new ArrayList<Ruspa>();
		boolean disp;
		for(Ruspa r:mr.getLista()){
			disp=true;
			for(Associazione item:ea.getElencoAssociazioniList()){
				if(item.getMacchina().equals(r)){
					if(!(fine.before(item.getDataInizio()) || inizio.after(item.getDataFine())))
						disp=false;
				
				}
			}
			if(disp==true)
				ruspe.add(r);
		}
		return ruspe;
	}
	
	/**
	 * Elenco gru disponibili.
	 *
	 * @param inizio   inizio
	 * @param fine   fine
	 * @return   array list
	 */
	public ArrayList<Gru> elencoGruDisponibili(GregorianCalendar inizio,GregorianCalendar fine){
		
		ArrayList<Gru> gru=new ArrayList<Gru>();
		gru.clear();
		boolean disp=true;
		ArrayList<Gru> g=mg.getLista();
		for(Gru r:g){
			disp=true;
			for(Associazione item:ea.getElencoAssociazioniList()){				
				if(item.getMacchina().equals(r)){

					
					if(!(fine.before(item.getDataInizio()) || inizio.after(item.getDataFine())))
						disp=false;
				}
			}

			if(disp==true){
				gru.add(r);
			}
		}
		return gru;
	}
	/**
	 * Elenco camion disponibili.
	 *
	 * @param inizio   inizio
	 * @param fine   fine
	 * @return   array list
	 */
	public ArrayList<Camion> elencoCamionDisponibili(GregorianCalendar inizio,GregorianCalendar fine){
		ArrayList<Camion> cam=new ArrayList<Camion>();
		boolean disp;
		for(Camion r:mc.getLista()){
			disp=true;
			for(Associazione item:ea.getElencoAssociazioniList()){				
				if(item.getMacchina().equals(r)){

					
					if(!(fine.before(item.getDataInizio()) || inizio.after(item.getDataFine())))
						disp=false;
				}
			}

			if(disp==true){
				cam.add(r);
			}
		}
		return cam;
	}
	
	/**
	 * Elenco escavatore disponibili.
	 *
	 * @param inizio   inizio
	 * @param fine   fine
	 * @return   array list
	 */
	public ArrayList<Escavatore> elencoEscavatoreDisponibili(GregorianCalendar inizio,GregorianCalendar fine){
		ArrayList<Escavatore> escavatori=new ArrayList<Escavatore>();
		boolean disp=true;
		for(Escavatore r:me.getLista()){
			disp=true;
			for(Associazione item:ea.getElencoAssociazioniList()){				
				if(item.getMacchina().equals(r)){

					
					if(!(fine.before(item.getDataInizio()) || inizio.after(item.getDataFine())))
						disp=false;
				}
			}

			if(disp==true){
				escavatori.add(r);
			}
		}
		return escavatori;
	}
	public ArrayList<ArrayList<String>> getAssociazioniList(int codiceCantiere){
		ArrayList<String> associazione;
		ArrayList<ArrayList<String>> listaAssociazioni=new ArrayList<ArrayList<String>>();
		
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		for(Associazione ass:ea.getElencoAssociazioniList(codiceCantiere)){
			associazione=new ArrayList<String>();
			associazione.add(Integer.toString(ass.getID()));//ID
			associazione.add(Integer.toString(ass.getMacchina().getCodice()));//Codice Macchina
			associazione.add(Integer.toString(ass.getCantiere().getCodice()));//Codice Cantiere
			
			associazione.add(df.format(ass.getDataInizio().getTime()));//Data Inizio
			associazione.add(df.format(ass.getDataFine().getTime()));//Data Fine
			
			
			
			listaAssociazioni.add(associazione);
		}
		
		return listaAssociazioni;
	}
	public ArrayList<ArrayList<String>> getAssociazioniList(){
		ArrayList<String> associazione;
		ArrayList<ArrayList<String>> listaAssociazioni=new ArrayList<ArrayList<String>>();
		
		SimpleDateFormat df = new SimpleDateFormat();
	    df.applyPattern("yyyy-MM-dd");
	    
		for(Associazione ass:ea.getElencoAssociazioniList()){
			associazione=new ArrayList<String>();
			associazione.add(Integer.toString(ass.getID()));//ID
			associazione.add(Integer.toString(ass.getMacchina().getCodice()));//Codice Macchina
			associazione.add(Integer.toString(ass.getCantiere().getCodice()));//Codice Cantiere
			
			associazione.add(df.format(ass.getDataInizio().getTime()));//Data Inizio
			associazione.add(df.format(ass.getDataFine().getTime()));//Data Fine
			
			
			
			listaAssociazioni.add(associazione);
		}
		
		return listaAssociazioni;
	}

	@Override
	public ArrayList<ArrayList<String>> getLavoriCantiereList(int codiceCantiere) {ArrayList<String> work1=new ArrayList<>();work1.add("1");work1.add("Scavi");work1.add("Scavicchi");
		ArrayList<ArrayList<String>> arr=new ArrayList<ArrayList<String>>();
		ArrayList<String> ass1=new ArrayList<>();ass1.add("1");ass1.add("scavi");ass1.add("24/07/2014");ass1.add("24/08/2014");
		ArrayList<String> ass2=new ArrayList<>();ass2.add("2");ass2.add("fondamenta");ass2.add("13/08/2014");ass2.add("11/09/2014");
		arr.add(ass1);arr.add(ass2);
		return arr;
	}

	@Override
	public void insertLavoro(String nome, GregorianCalendar inizio,
			GregorianCalendar fine, int idCantiere) {
		 ml.aggiungiLavoro(nome,inizio,fine,getCantiere(idCantiere));
		
	}

	@Override
	public ArrayList<ArrayList<String>> getRichiesteLavoroList(int codiceCantiere) {
		ArrayList<ArrayList<String>> arr=new ArrayList<ArrayList<String>>();
		ArrayList<String> ass1=new ArrayList<>();ass1.add("2");ass1.add("Escavatore");ass1.add("24/07/2014");ass1.add("24/08/2014");
		
		ArrayList<String> ass2=new ArrayList<>();ass2.add("2");ass2.add("Camion");ass2.add("13/05/2014");ass2.add("19/05/2014");
		arr.add(ass1);arr.add(ass2);
		return arr;
	}
}
