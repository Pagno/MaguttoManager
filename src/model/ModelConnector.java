package model;

import database.DBException;
import database.Database;
import database.DatabaseInterface;

public class ModelConnector implements ModelInterface {
	
	private ModelGru mg;
	private ModelCamion mc;
	private ModelRuspa mr;
	private ModelEscavatore me;
	private ElencoAssociazioni ea;
	private DatabaseInterface db;
	
	private void inizializza() {
		mg = new ModelGru();
		mc = new ModelCamion();
		mr = new ModelRuspa();
		me = new ModelEscavatore();
		ea = new ElencoAssociazioni();
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
				//IL DB NON E' VUOTO, CARICO I DATI NEL MODELLO
				//TODO
			}
			db.disconnect();
		} 
		catch (DBException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void storeData() {
		/*try {
			db.connect();
			//POPOLARE IL DATABASE
			db.emptyTable("Associazione");
			db.emptyTable("Macchina");
			db.emptyTable("Cantiere");
			
			for(Cantiere item:pc.getListaCantieri()){
				String qry = "insert into APP.Cantiere (Codice,Nome,DataApertura,DataChiusura,Indirizzo)"+
						"values(" + item.getCodice() + "," + item.getNomeCantiere() + "," + 
						item.getDataApertura() + "," + item.getDataChiusura() + 
						"," + item.getIndirizzo() + ")" ;
				db.update(qry);
			}
			
			for(Macchina item:pm.getListaMacchine()){
				if(item instanceof Camion){
					Camion temp=(Camion) item;
					String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,LunghezzaMax)"+
							"values(" + temp.getCodice() + "," + temp.getProduttore() + "," + 
							temp.getModello() + ",'Camion'," + temp.getCapacitaMassima() + 
							"," + temp.getPortataMassima() + "," + temp.getLunghezza() + ")" ;
					db.update(qry);
				}
				else if(item instanceof Escavatore){
					Escavatore temp=(Escavatore) item;
					String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax,ProfonditaMax)"+
							"values(" + temp.getCodice() + "," + temp.getProduttore() + "," + 
							temp.getModello() + ",'Escavatore'," + temp.getCapacitaMassima() + 
							"," + temp.getPortataMassima() + "," + temp.getAltezzaMassima() + 
							"," + temp.getProfonditaMassima() + ")" ;
					db.update(qry);
				}
				else if(item instanceof Gru){
					Gru temp=(Gru) item;
					String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,PortataMax,AltezzaMax,LunghezzaMax,RotazioneMax)"+
							"values(" + temp.getCodice() + "," + temp.getProduttore() + 
							"," + temp.getModello() + ",'Gru'," +  temp.getPortataMassima() + "," + temp.getAltezza() + 
							"," + temp.getLunghezza() + temp.getAngoloRotazione() + "," + ")" ;
					db.update(qry);
				}
				else if(item instanceof Ruspa){
					Ruspa temp=(Ruspa) item;
					String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax)"+
							"values(" + temp.getCodice() + "," + temp.getProduttore() + "," + 
							temp.getModello() + ",'Ruspa'," + temp.getCapacitaMassima() + 
							"," + temp.getPortataMassima() + "," + temp.getAltezzaMassima() + ")" ;
					db.update(qry);
				}
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
		}*/
	}
	
	public void aggiungiGru(String produttore,String modello, int rotazione, int portata,int lunghezza,int altezza){
		mg.aggiungiGru(produttore, modello, rotazione, portata, lunghezza, altezza);
	}

	@Override
	public void modificaGru(int codice, String produttore, String modello,int rotazione, int portata, int lunghezza, int altezza) {
		mg.modificaGru(codice, produttore, modello, rotazione, portata, lunghezza, altezza);
		
	}

	@Override
	public boolean eliminaMacchina(int codice) {
		return false;
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
	
}
