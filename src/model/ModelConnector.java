package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import model.organizer.ModelCamion;
import model.organizer.ModelCantiere;
import model.organizer.ModelEscavatore;
import model.organizer.ModelGru;
import model.organizer.ModelMacchina;
import model.organizer.ModelRuspa;
import model.organizer.data.Camion;
import model.organizer.data.Cantiere;
import model.organizer.data.Escavatore;
import model.organizer.data.Gru;
import model.organizer.data.Lavoro;
import model.organizer.data.Macchina;
import model.organizer.data.Richiesta;
import model.organizer.data.RichiestaCamion;
import model.organizer.data.RichiestaEscavatore;
import model.organizer.data.RichiestaGru;
import model.organizer.data.RichiestaMacchina;
import model.organizer.data.RichiestaRuspa;
import model.organizer.data.Ruspa;
import model.organizer.data.Priority;
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
	
	/**
	 * Adds   Lavoro observer.
	 *
	 * @param observer   observer
	 */
	public void addLavoroObserver(Observer observer){
		lc.addLavoroObserver(observer);
	}
	/**
	 * Adds   Richiesta observer.
	 *
	 * @param observer   observer
	 */
	public void addRichiestaObserver(Observer observer){
		lc.addRichiestaObserver(observer);
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
		for(Cantiere cantiere:lc.getListaCantieri()){
			for(Lavoro lavoro:cantiere.getElencoLavori())
				lavoro.getListaRichieste().clear();
			cantiere.getElencoLavori().clear();
		}
		lc.getListaCantieri().clear();
		
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
						"Indirizzo varchar(50) not null,"+
						"Priorita varchar(10) not null check (Priorita like 'BASSA' or Priorita like 'MEDIA' or Priorita like 'ALTA'))";
				db.update(qry);
				
				qry = "create table APP.Lavoro (" +
						"Codice integer  primary key, "+
						"Nome varchar(30) not null,"+
						"CodiceCantiere integer references APP.Cantiere(Codice), "+
						"DataInizio date not null,"+
						"DataFine date not null)";
				db.update(qry);
				
				qry = "create table APP.Richiesta( "+
						"Codice integer  primary key,"+
						"CodiceLavoro integer references APP.Lavoro(Codice), "+
						"CodiceMacchina integer references APP.Macchina(Codice),"+
						"Tipo varchar(10) not null check (Tipo like 'Gru' or Tipo like 'Camion' or Tipo like 'Ruspa' or Tipo like 'Escavatore'),"+
						"CapacitaMin integer,CapacitaMax integer, "+
						"PortataMin integer, PortataMax integer, "+
						"AltezzaMin integer, AltezzaMax integer,"+
						"LunghezzaMin integer, LunghezzaMax integer,"+
						"ProfonditaMin integer, ProfonditaMax integer,"+
						"RotazioneMin integer,RotazioneMax integer)";

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
			db.emptyTable("Richiesta");
			db.emptyTable("Lavoro");
			db.emptyTable("Cantiere");
			db.emptyTable("Macchina");

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

			for(Cantiere cantiere:lc.getListaCantieri()){
				String qry = "insert into APP.Cantiere (Codice,Nome,DataApertura,DataChiusura,Indirizzo,Priorita)"+
						"values(" + cantiere.getCodice() + ",'" + cantiere.getNomeCantiere() + "','" + 
						cantiere.getStrDataApertura() + "','" + cantiere.getStrDataChiusura() + 
						"','" + cantiere.getIndirizzo() + "','" + cantiere.getPrioritaString() +"')" ;
				db.update(qry);
				
				for(Lavoro lavoro: cantiere.getElencoLavori()){
					qry = "insert into APP.Lavoro (Codice,Nome,CodiceCantiere,DataInizio,DataFine)"+
							"values(" + lavoro.getCodice() + ",'" + lavoro.getNome() + "'," +  cantiere.getCodice() + ",'" +
							lavoro.getStrDataInizio() + "','" + lavoro.getStrDataFine() + "')" ;
					db.update(qry);
					for(Richiesta richiesta:lavoro.getListaRichieste()){

						String codiceMacchina=richiesta.getMacchina()==null?"":"CodiceMacchina,";
						String codice=richiesta.getMacchina()==null?"":""+richiesta.getMacchina().getCodice()+",";
						
						if(richiesta.getCaratteristiche() instanceof RichiestaCamion ){
							RichiestaCamion camion=(RichiestaCamion)richiesta.getCaratteristiche();
							qry = "insert into APP.Richiesta (Codice,CodiceLavoro,"+codiceMacchina+"Tipo,CapacitaMin,CapacitaMax,PortataMin,PortataMax,LunghezzaMin,LunghezzaMax)"+
								"values(" + richiesta.getCodice() + "," + lavoro.getCodice() + ","+codice+",'Camion'," +
								camion.getMinCapacita() + "," + camion.getMaxCapacita() + "," + camion.getMinPortata() + "," +  camion.getMaxPortata() + "," + 
								camion.getMinLunghezza() + "," + camion.getMaxLunghezza() + ")" ;
							db.update(qry);
						}else if(richiesta.getCaratteristiche() instanceof RichiestaGru ){
							RichiestaGru Gru=(RichiestaGru)richiesta.getCaratteristiche();
							qry = "insert into APP.Richiesta (Codice,CodiceLavoro,"+codiceMacchina+"Tipo,AltezzaMin,AltezzaMax,PortataMin,PortataMax,LunghezzaMin,LunghezzaMax,RotazioneMin,RotazioneMax)"+
								"values(" + richiesta.getCodice() + "," + lavoro.getCodice() + ","+codice+"'Gru'," +
								Gru.getMinAltezza() + "," + Gru.getMaxAltezza() + "," + Gru.getMinPortata() + "," +  Gru.getMaxPortata() + "," + 
								Gru.getMinLunghezza() + "," + Gru.getMaxLunghezza() + "," + Gru.getMinAngoloRotazione() +"," + Gru.getMaxAngoloRotazione() +")" ;
							db.update(qry);
						}else if(richiesta.getCaratteristiche() instanceof RichiestaEscavatore ){
							RichiestaEscavatore escavatore=(RichiestaEscavatore)richiesta.getCaratteristiche();
							qry = "insert into APP.Richiesta (Codice,CodiceLavoro,"+codiceMacchina+"Tipo,AltezzaMin,AltezzaMin,PortataMin,PortataMax,CapacitaMin,CapacitaMax,ProfonditaMin,ProfonditaMax)"+
								"values(" + richiesta.getCodice() + ",'" + lavoro.getCodice() + ","+codice+"'Escavatore'," +
								escavatore.getMinAltezza() + "," + escavatore.getMaxAltezza() + "," + escavatore.getMinPortata() + "," +  escavatore.getMaxPortata() + "," + 
								escavatore.getMinCapacita() + "," + escavatore.getMaxCapacita() + "," + escavatore.getMinProfondita() +"," + escavatore.getMaxProfondita() +")" ;
							db.update(qry);
						}else if(richiesta.getCaratteristiche() instanceof RichiestaRuspa ){
							RichiestaRuspa ruspa=(RichiestaRuspa)richiesta.getCaratteristiche();
							qry = "insert into APP.Richiesta (Codice,CodiceLavoro,"+codiceMacchina+"Tipo,AltezzaMin,AltezzaMax,PortataMin,PortataMax,CapacitaMin,CapacitaMax)"+
								"values(" + richiesta.getCodice() + "," + lavoro.getCodice() + ","+codice+"'Ruspa'," +
								ruspa.getMinAltezza() + "," + ruspa.getMaxAltezza() + "," + ruspa.getMinPortata() + "," +  ruspa.getMaxPortata() + "," + 
								ruspa.getMinCapacita() + "," + ruspa.getMaxCapacita() +")" ;
							db.update(qry);
						}
					}
					
				}
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
	public void aggiungiCantiere(String nomeCantiere, String indirizzo, GregorianCalendar dataApertura, GregorianCalendar dataChiusura, Priority priorita) {
		lc.aggiungiCantiere(nomeCantiere, indirizzo, dataApertura, dataChiusura,priorita);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#modificaCantiere(int, java.lang.String, java.lang.String, java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public void modificaCantiere(int codice, String nomeCantiere, String indirizzo, GregorianCalendar dataApertura, GregorianCalendar dataChiusura, Priority priorita) {
			lc.modificaCantiere(codice, nomeCantiere, indirizzo, dataApertura, dataChiusura, priorita);
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#eliminaCantiere(int)
	 */
	@Override
	public boolean eliminaCantiere(int codice) {
		return lc.rimuoviCantiere(codice);
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
		//carica i lavori
		loadLavori();
		//carica le richieste
		loadRichieste();

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
				lc.caricaCantiere(res.getInt("Codice"), res.getString("Nome"), res.getString("Indirizzo"), convertToDate(res.getString("DataApertura")), convertToDate(res.getString("DataChiusura")),Priority.valueOf(res.getString("Priorita")));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	/**
	 * Load lavoro.
	 */
	private void loadLavori(){
		//carica le associazioni
		try {
			//db.connect();
			String qry="select * from APP.Lavoro";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				lc.caricaLavoro(res.getInt("CodiceCantiere"), res.getInt("codice"), res.getString("Nome"), convertToDate(res.getString("DataInizio")), convertToDate(res.getString("DataFine")));
			}
			//db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	/**
	 * Load richieste.
	 */
	private void loadRichieste(){
		//carica le associazioni
		try {
			//db.connect();
			String qry="select * from APP.Richiesta";
			ResultSet res=db.interrogate(qry);
			RichiestaMacchina richiesta=null;
			Lavoro lavoro;
			while(res.next()){
				lavoro=lc.getLavoro(res.getInt("CodiceLavoro"));
				if(res.getString("Tipo").equalsIgnoreCase("Gru")){
					richiesta=new RichiestaGru(res.getInt("LunghezzaMin"),res.getInt("LunghezzaMax"),
							res.getInt("AltezzaMin"),res.getInt("AltezzaMax"),res.getInt("PortataMin"),
							res.getInt("PortataMax"),res.getInt("RotazioneMin"),res.getInt("RotazioneMax"));
					
				}else if(res.getString("Tipo").equalsIgnoreCase("Camion")){
					richiesta=new RichiestaCamion(res.getInt("CapacitaMin"),res.getInt("CapacitaMax"),
							res.getInt("PortataMin"),res.getInt("PortataMax"),res.getInt("LunghezzaMin"),res.getInt("LunghezzaMax"));
					
				}else if(res.getString("Tipo").equalsIgnoreCase("Ruspa")){
					richiesta=new RichiestaRuspa(res.getInt("CapacitaMin"),res.getInt("CapacitaMax"),
							res.getInt("PortataMin"),res.getInt("PortataMax"),res.getInt("AltezzaMin"),res.getInt("AltezzaMax"));
					
				}else if(res.getString("Tipo").equalsIgnoreCase("Escavatore")){
					richiesta=new RichiestaEscavatore(res.getInt("CapacitaMin"),res.getInt("CapacitaMax"),
							res.getInt("PortataMin"),res.getInt("PortataMax"),res.getInt("AltezzaMin"),
							res.getInt("AltezzaMax"),res.getInt("ProfonditaMin"),res.getInt("ProfonditaMax"));
					
				}
				lavoro.inserisciRichiesta(richiesta);
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
		for(Cantiere c:lc.getListaCantieri()){
			System.out.println("	CANTIERE: "+c.toString());
			for(Lavoro l:c.getElencoLavori()){
				System.out.println("		LAVORO: "+l.toString());
				for(Richiesta r:l.getListaRichieste()){
					System.out.println("			RICHIESTA: "+r.getCaratteristiche().toString());
				}
			}
		}
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
			
			for(Cantiere cantiere:lc.getListaCantieri()){
				for(Lavoro lavoro:cantiere.getElencoLavori()){
					for(Richiesta richiesta:lavoro.getListaRichieste()){
						if(richiesta.getMacchina().equals(r)){
							if(!(fine.before(lavoro.getDataInizio()) || inizio.after(lavoro.getDataFine())))
								disp=false;
						}
					}
						
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
			for(Cantiere cantiere:lc.getListaCantieri()){
				for(Lavoro lavoro:cantiere.getElencoLavori()){
					for(Richiesta richiesta:lavoro.getListaRichieste()){
						if(richiesta.getMacchina().equals(r)){
							if(!(fine.before(lavoro.getDataInizio()) || inizio.after(lavoro.getDataFine())))
								disp=false;
						}
					}
						
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
			for(Cantiere cantiere:lc.getListaCantieri()){
				for(Lavoro lavoro:cantiere.getElencoLavori()){
					for(Richiesta richiesta:lavoro.getListaRichieste()){
						if(richiesta.getMacchina().equals(r)){
							if(!(fine.before(lavoro.getDataInizio()) || inizio.after(lavoro.getDataFine())))
								disp=false;
						}
					}
						
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
			for(Cantiere cantiere:lc.getListaCantieri()){
				for(Lavoro lavoro:cantiere.getElencoLavori()){
					for(Richiesta richiesta:lavoro.getListaRichieste()){
						if(richiesta.getMacchina().equals(r)){
							if(!(fine.before(lavoro.getDataInizio()) || inizio.after(lavoro.getDataFine())))
								disp=false;
						}
					}
						
				}
			}

			if(disp==true){
				escavatori.add(r);
			}
		}
		return escavatori;
	}

	@Override
	public ArrayList<ArrayList<String>> getLavoriCantiereList(int codiceCantiere) {ArrayList<String> work1=new ArrayList<>();work1.add("1");work1.add("Scavi");work1.add("Scavicchi");
		ArrayList<ArrayList<String>> elencoLavori=new ArrayList<ArrayList<String>>();
		ArrayList<String> l;
		for(Lavoro lavoro:lc.getCantiere(codiceCantiere).getElencoLavori()){
			//CODICE,NOME,DATAINIZIO,DATAFINE
			l=new ArrayList<String>();
			
			l.add(Integer.toString(lavoro.getCodice()));
			l.add(lavoro.getNome());
			SimpleDateFormat df = new SimpleDateFormat();
			df.applyPattern("dd/MM/yyyy");
			l.add(df.format(lavoro.getDataInizio().getTime()));
			l.add(df.format(lavoro.getDataFine().getTime()));
			elencoLavori.add(l);
		}
		return elencoLavori;
	}

	@Override
	public void insertLavoro(String nome, GregorianCalendar inizio,
			GregorianCalendar fine, int idCantiere) {
		 lc.aggiungiLavoro(idCantiere, nome, inizio, fine);
	}

	@Override
	public ArrayList<ArrayList<String>> getRichiesteLavoroList(int codiceCantiere) {
		ArrayList<ArrayList<String>> elencoRichieste=new ArrayList<ArrayList<String>>();
		ArrayList<String> l=null;
		for(Lavoro lavoro:lc.getCantiere(codiceCantiere).getElencoLavori()){
			for(Richiesta richiesta:lavoro.getListaRichieste()){
				l=new ArrayList<String>();
				l.add(Integer.toString(lavoro.getCodice()));l.add(Integer.toString(richiesta.getCodice()));
				if(richiesta.getCaratteristiche() instanceof RichiestaCamion){
					RichiestaCamion rc=(RichiestaCamion)richiesta.getCaratteristiche();
					l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
					l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
					l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
					l.add("0");l.add("0");
					l.add("0");l.add("0");
					l.add("0");l.add("0");
				}else if(richiesta.getCaratteristiche() instanceof RichiestaEscavatore){
					RichiestaEscavatore rc=(RichiestaEscavatore)richiesta.getCaratteristiche();
					l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
					l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
					l.add("0");l.add("0");
					l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
					l.add(Integer.toString(rc.getMinProfondita()));l.add(Integer.toString(rc.getMaxProfondita()));
					l.add("0");l.add("0");
					
				}else if(richiesta.getCaratteristiche() instanceof RichiestaGru){
					RichiestaGru rc=(RichiestaGru)richiesta.getCaratteristiche();
					l.add("0");l.add("0");
					l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
					l.add(Integer.toString(rc.getMinLunghezza()));l.add(Integer.toString(rc.getMaxLunghezza()));
					l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
					l.add("0");l.add("0");
					l.add(Integer.toString(rc.getMinAngoloRotazione()));l.add(Integer.toString(rc.getMaxAngoloRotazione()));
				}else if(richiesta.getCaratteristiche() instanceof RichiestaRuspa){
					RichiestaRuspa rc=(RichiestaRuspa)richiesta.getCaratteristiche();
					l.add(Integer.toString(rc.getMinCapacita()));l.add(Integer.toString(rc.getMaxCapacita()));
					l.add(Integer.toString(rc.getMinPortata()));l.add(Integer.toString(rc.getMaxPortata()));
					l.add("0");l.add("0");
					l.add(Integer.toString(rc.getMinAltezza()));l.add(Integer.toString(rc.getMaxAltezza()));
					l.add("0");l.add("0");
					l.add("0");l.add("0");

					elencoRichieste.add(l);
				}
			}
		}
		return elencoRichieste;
	}

	@Override
	public ArrayList<Cantiere> getListaCantieri() {
		return lc.getListaCantieri();
	}

	@Override
	public ArrayList<String> addRichiesta(int codiceCantiere, int codiceLavoro,
			RichiestaMacchina richiesta) {
		return lc.aggiungiRichiesta(codiceCantiere, codiceLavoro,richiesta);
	}

	@Override
	public boolean deleteRichiesta(int codiceRichiesta) {
		return lc.rimuoviRichiesta(codiceRichiesta);
	}

	@Override
	public boolean deleteLavoro(int codiceLavoro) {
		return lc.rimuoviLavoro(codiceLavoro);
	}
	
	@Override
	public void soddisfaRichiesta(int codiceRichiesta, int codiceMacchina){
		Macchina macchina=getMacchina(codiceMacchina);
		lc.soddisfaRichiesta(codiceRichiesta, macchina);
	}
}
