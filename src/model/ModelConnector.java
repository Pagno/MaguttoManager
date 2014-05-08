package model;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	private void inizializza() {
		ModelMacchina.initCodice();
		mg = new ModelGru();
		mc = new ModelCamion();
		mr = new ModelRuspa();
		me = new ModelEscavatore();
		ea = new ElencoAssociazioni();
		lc = new ModelCantiere();
	}

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
						"Name varchar(30) not null, " +
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

			}
			else{
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
			db.connect();
			//POPOLARE IL DATABASE
			db.emptyTable("Associazione");
			db.emptyTable("Macchina");
			db.emptyTable("Cantiere");

			for(Cantiere item:lc.getLista()){
				String qry = "insert into APP.Cantiere (Codice,Nome,DataApertura,DataChiusura,Indirizzo)"+
						"values(" + item.getCodice() + "," + item.getNomeCantiere() + "," + 
						item.getDataApertura() + "," + item.getDataChiusura() + 
						"," + item.getIndirizzo() + ")" ;
				db.update(qry);
			}

			for(Camion item:mc.getLista()){

				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,LunghezzaMax)"+
						"values(" + item.getCodice() + "," + item.getProduttore() + "," + 
						item.getModello() + ",'Camion'," + item.getCapacitaMassima() + 
						"," + item.getPortataMassima() + "," + item.getLunghezza() + ")" ;
				db.update(qry);
			}	
			for(Escavatore item:me.getLista()){
				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax,ProfonditaMax)"+
						"values(" + item.getCodice() + "," + item.getProduttore() + "," + 
						item.getModello() + ",'Escavatore'," + item.getCapacitaMassima() + 
						"," + item.getPortataMassima() + "," + item.getAltezzaMassima() + 
						"," + item.getProfonditaMassima() + ")" ;
				db.update(qry);
			}
			for(Gru item:mg.getLista()){
				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,PortataMax,AltezzaMax,LunghezzaMax,RotazioneMax)"+
						"values(" + item.getCodice() + "," + item.getProduttore() + 
						"," + item.getModello() + ",'Gru'," +  item.getPortataMassima() + "," + item.getAltezza() + 
						"," + item.getLunghezza() + item.getAngoloRotazione() + "," + ")" ;
				db.update(qry);
			}
			for(Ruspa item:mr.getLista()){
				String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax)"+
						"values(" + item.getCodice() + "," + item.getProduttore() + "," + 
						item.getModello() + ",'Ruspa'," + item.getCapacitaMassima() + 
						"," + item.getPortataMassima() + "," + item.getAltezzaMassima() + ")" ;
				db.update(qry);
			}
			for(Associazione item:ea.getElencoAssociazioni()){
				String qry = "insert into APP.Associazione (Id,CodiceMacchina,CodiceCantiere,DataInizio,DataFine)"+
						"values(" + item.getID() + "," + item.getMacchina().getCodice() + "," + 
						item.getCantiere().getCodice() + "," + item.getDataInizio() + 
						"," + item.getDataFine() + ")" ;
				db.update(qry);
			}

			db.disconnect();
		} 
		catch (DBException e) {
			e.printStackTrace();
		}
	}
	
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		mg.aggiungiGru(produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	@Override
	public void modificaGru(int codice, String produttore, String modello,int rotazione, int portata, int lunghezza, int altezza) {
		mg.modificaGru(codice, produttore, modello, rotazione, portata, lunghezza, altezza);
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
			db.connect();
			String qry="select * from APP.Macchina where Tipo='Camion'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				mc.caricaCamion(res.getInt("Codice"),res.getString("Produttore"), res.getString("Modello"), res.getInt("CapacitaMax"), res.getInt("PortataMax"), res.getInt("LunghezzaMax"));
			}
			db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	
	private void loadEscavatori(){
		//carica gli escavatori
		try {
			db.connect();
			String qry="select * from APP.Macchina where Tipo='Escavatore'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				me.caricaEscavatore(res.getInt("Codice"), res.getString("Produttore"), res.getString("Modello"), res.getInt("CapacitaMax"), res.getInt("PortataMax"), res.getInt("AltezzaMax"), res.getInt("ProfonditaMax"));
			}
			db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	
	private void loadRuspe(){
		//carica le ruspe
		try {
			db.connect();
			String qry="select * from APP.Macchina where Tipo='Ruspa'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				mr.caricaRuspa(res.getInt("Codice"), res.getString("Produttore"), res.getString("Modello"), res.getInt("CapacitaMax"), res.getInt("PortataMax"), res.getInt("AltezzaMax"));
			}
			db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	
	private void loadGru(){
		//carica le gru
		try {
			db.connect();
			String qry="select * from APP.Macchina where Tipo='Gru'";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				mg.caricaGru(res.getInt("Codice"), res.getString("Produttore"), res.getString("Modello"), res.getInt("RotazioneMax"), res.getInt("PortataMax"), res.getInt("LunghezzaMax"), res.getInt("AltezzaMax"));
			}
			db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	
	private void loadCantieri(){
		//carica i cantieri
		try {
			db.connect();
			String qry="select * from APP.Cantiere";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				lc.caricaCantiere(res.getInt("Codice"), res.getString("Name"), res.getString("Indirizzo"), res.getDate("DataApertura"), res.getDate("DataChiusura"));
			}
			db.disconnect();
		} catch (DBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	
	private void loadAssociazioni(){
		//carica le associazioni
		try {
			db.connect();
			String qry="select * from APP.Associazione";
			ResultSet res=db.interrogate(qry);
			while(res.next()){
				ea.caricaAssociazione(res.getInt("Id"), getMacchina(res.getInt("CodiceMacchina")), lc.getCantiere(res.getInt("CodiceCantiere")), res.getDate("DataInizio"), res.getDate("DataFine"));
			}
			db.disconnect();
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

}
