package model;

import database.DBException;
import database.Database;
import database.DatabaseInterface;

public class ModelConnector implements ModelInterface {
	
	private ParcoCantieri pc;
	private ParcoMacchine pm;
	private ElencoAssociazioni ea;
	private DatabaseInterface db;
	
	private void inizializza() {
		pc = new ParcoCantieri();
		pm = new ParcoMacchine();
		ea = new ElencoAssociazioni();
	}

	@Override
	public void downloadDati() {
		inizializza();
		db = new Database();
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
	public void uploadDati() {
		try {
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
		}
	}

}
