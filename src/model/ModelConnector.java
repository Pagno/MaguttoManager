package model;

import java.sql.SQLException;

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
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void uploadDati() {
		try {
			db.connect();
			//POPOLARE IL DATABASE ALLA CHIUSURA
			/*String qry = "insert into APP.Macchina (Codice,Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax)"+
					"values(101,'Wacker Neuson','901s','Ruspa',907,35,237)" ;
					//"values(100,'Kramer Allrad','350','Ruspa',640,17,225)";
			db.update(qry);*/
			db.disconnect();
		} 
		catch (DBException e) {
			e.printStackTrace();
		}
	}

}
