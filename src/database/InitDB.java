package database;

// TODO: Auto-generated Javadoc
/**
 * Classe che crea le tabelle e inserisce dei dati nel caso di primo utilizzo dell'applicazione
 * 
 * @author Mauro
 */
public class InitDB {
	
	/**
	 * Istanzia un nuovo InitDB.
	 */
	public InitDB(){
		
	}
	
	
	/**
	 * Crea le tabelle.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void createTables() throws JavaDBException{
		String qry = "create table APP.Macchina ( " +
				"Codice integer  generated always as identity(start with 100, increment by 1) primary key, " +
				"Produttore  varchar(20) not null, " +
				"Modello varchar(20) not null, " +
				"Tipo varchar(10) not null check (Tipo like 'Gru' or Tipo like 'Camion' or Tipo like 'Ruspa' or Tipo like 'Escavatore'), " +
				"CapacitaMax integer, " +
				"PortataMax integer, " +
				"AltezzaMax integer, " +
				"LunghezzaMax integer, " +
				"ProfonditaMax integer, " +
				"RotazioneMax integer)";
		Database.update(qry);

		
		qry = "create table APP.Cantiere ( " +
				"Codice integer  generated always as identity(start with 100, increment by 1) primary key, " +
				"Name varchar(30) not null, " +
				"DataApertura date not null, " +
				"DataChiusura date not null, " +
				"Indirizzo varchar(50) not null)";
		Database.update(qry);

		qry = "create table APP.Prenotazione ( " +
				"Id integer generated always as identity(start with 1, increment by 1) primary key, " +
				"CodiceMacchina integer references APP.Macchina(Codice), " +
				"CodiceCantiere integer references APP.Cantiere(Codice), " +
				"DataInizio date not null, " +
				"DataFine date not null)";
		Database.update(qry);
	}
	
	
	public void popola()throws JavaDBException{
		String qry = "insert into APP.Macchina (Produttore,Modello,Tipo,CapacitaMax,PortataMax,AltezzaMax)"+
				//"values('Wacker Neuson','901s','Ruspa',907,35,237)" ;
				"values('Kramer Allrad','350','Ruspa',640,17,225)";
		Database.update(qry);
		
		
	}

	
	/**
	 * Inserisce nelle tabelle Users e Settings.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void insertIntoTables() throws JavaDBException{
		String qry = "insert into APP.Users (ID, Name, Password, Type, DBupdate) values " +
				"('Amm1', 'Username', 'Password', 'Admin', true)";
		Database.update(qry);
		qry = "insert into APP.Settings (NameParam, Value) values " +
				"('Sconto'	  , '30'  ), " +
				"('Contatore' , '5'   ), " +
				"('FirstLogin', 'true'), " +
				"('Update'	  , 'true'), " +
				"('Coperto'	  , '1'	  )";
		Database.update(qry);
		
	}
	
}