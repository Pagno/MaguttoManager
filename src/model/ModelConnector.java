package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import database.DBException;
import database.DatabaseInterface;

public class ModelConnector implements ModelInterface {

	private ModelGru mg;
	private ModelCamion mc;
	private ModelRuspa mr;
	private ModelEscavatore me;
	private ModelCantiere lc;
	private ElencoAssociazioni ea;
	private DatabaseInterface db;

	public ModelConnector(DatabaseInterface data){
		db=data;
		refreshData();
	}

	@Override
	public void refreshData() {
		inizializza();

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
			for(Associazione item:ea.getElencoAssociazioni()){
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

	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		mg.aggiungiGru(produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	@Override
	public void modificaGru(int codice, String produttore, String modello,int rotazione, int portata, int lunghezza, int altezza) {
		mg.modificaGru(codice, produttore, modello, rotazione, portata, lunghezza, altezza);
	}
	@Override
	public void aggiungiCamion(String produttore, String Modello, int capacita,	int portata, int lunghezza) {
		mc.aggiungiCamion(produttore, Modello, capacita, portata, lunghezza);
	}

	@Override
	public void modificaCamion(int codice, String produttore, String Modello,int capacita, int portata, int lunghezza) {
		mc.modificaCamion(codice, produttore, Modello, capacita, portata, lunghezza);
	}

	@Override
	public void aggiungiRuspa(String produttore, String Modello, int capacita,int portata, int altezza) {
		mr.aggiungiRuspa(produttore, Modello, capacita, portata, altezza);
	}

	@Override
	public void modificaRuspa(int codice, String produttore, String Modello,int capacita, int portata, int altezza) {
		mr.modificaRuspa(codice, produttore, Modello, capacita, portata, altezza);
	}

	@Override
	public void aggiungiEscavatore(String produttore, String Modello,int capacita, int portata, int altezza, int profondita) {
		me.aggiungiEscavatore(produttore, Modello, capacita, portata, altezza, profondita);
	}

	@Override
	public void modificaEscavatore(int codice, String produttore,String Modello, int capacita, int portata, int altezza,int profondita) {
		me.modificaEscavatore(codice, produttore, Modello, capacita, portata, altezza, profondita);		
	}

	@Override
	public void aggiungiCantiere(String nomeCantiere, String indirizzo, GregorianCalendar dataApertura, GregorianCalendar dataChiusura) {
		lc.aggiungiCantiere(nomeCantiere, indirizzo, dataApertura, dataChiusura);
	}

	@Override
	public void modificaCantiere(int codice, String nomeCantiere, String indirizzo, GregorianCalendar dataApertura, GregorianCalendar dataChiusura) {
			lc.modificaCantiere(codice, nomeCantiere, indirizzo, dataApertura, dataChiusura);
	}

	@Override
	public boolean eliminaCantiere(int codice) {
		return lc.rimuoviCantiere(codice);
	}

	@Override
	public void aggiungiAssociazione(Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		ea.inserisciAssociazione(getMacchina(codiceMacchina), lc.getCantiere(codiceCantiere), dataInizio, dataFine);
	}

	@Override
	public void modificaAssociazione(Integer codice, Integer codiceMacchina,Integer codiceCantiere, GregorianCalendar dataInizio, GregorianCalendar dataFine) {
		ea.modificaAssociazione(codice, getMacchina(codiceMacchina), lc.getCantiere(codiceCantiere), dataInizio, dataFine);
	}

	@Override
	public boolean eliminaAssociazione(int codice) {
		return ea.eliminaAssociazione(codice);
	}

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

	private void inizializza() {
		ModelMacchina.initCodice();
		mg = new ModelGru();
		mc = new ModelCamion();
		mr = new ModelRuspa();
		me = new ModelEscavatore();
		ea = new ElencoAssociazioni();
		lc = new ModelCantiere();
	}

	//da rimuovere
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



}