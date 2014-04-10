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
		String qry = "create table APP.Room ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"RoomNumb integer not null unique, " +
				"Name varchar(20) not null, " +
				"Mq integer, " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.Client ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"ID integer not null unique, " +
				"Name varchar(30) not null, " +
				"Surname varchar(30) not null, " +
				"Counter integer not null, " +
				"Allergy varchar(50), " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.Food ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"Code varchar(10) not null unique, " +
				"Name varchar(40) not null, " +
				"Price numeric(5,2) not null, " +
				"Type varchar(15) not null check (Type like 'Primo' or Type like 'Secondo' or Type like 'Contorno' or Type like 'Dolce' or Type like 'Frutta' or Type like 'Bevanda' or Type like '-----'), " +
				"Description varchar(100), " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.Waiter ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"Numb integer not null unique, " +
				"Surname varchar(30) not null, " +
				"Name varchar(30) not null, " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.TableR ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"TableNumb integer not null unique, " +
				"Seats integer not null, " +
				"Content integer references APP.Room(RoomNumb) " +
				"on delete set null " +
				"on update restrict, " +
				"State varchar(10) not null check (State like 'Libero' or State like 'Occupato' or State like '-----'), " +
				"WaiterCode integer references APP.Waiter(Numb) " +
				"on delete set null " +
				"on update restrict, " +
				"Joined varchar(30), " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.Reservation ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"ReservationNumb integer not null unique, " +
				"Date date not null, " +
				"Time time not null, " +
				"ReservedTable integer references APP.TableR(TableNumb) " +
				"on delete set null " +
				"on update restrict, " +
				"IDClient integer references APP.Client(ID) " +
				"on delete set null " +
				"on update restrict, " +
				"PeopleNumb integer not null, primary key (I))";
		Database.update(qry);
		qry = "create table APP.OrderTable ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"OrderNumb integer not null unique, " +
				"DateOrd date not null, " +
				"TimeOrd time not null, " +
				"RelativeTo integer references APP.TableR(TableNumb) " +
				"on delete set null " +
				"on update restrict, " +
				"SeatsNumb integer not null, " +
				"StateOrd varchar(15) not null check (StateOrd like 'InCorso' or StateOrd like 'Archiviata' or StateOrd like '-----'), " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.SingleOrder ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"OrdNumb integer references APP.OrderTable(OrderNumb) " +
				"on delete set null " +
				"on update restrict, " +
				"Quantity integer not null, " +
				"Dish varchar(10) references APP.Food(Code) " +
				"on delete set null " +
				"on update restrict, " +
				"State varchar(15) not null check (State like 'DaCompletare' or State like 'Servita' or State like '-----'), " +
				"Notes varchar(50), " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.NearTable ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"Table1 integer references APP.TableR(TableNumb) " +
				"on delete restrict " +
				"on update restrict, " +
				"Table2 integer references APP.TableR(TableNumb) " +
				"on delete restrict " +
				"on update restrict, " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.TablesCheck ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"Date date not null, " +
				"Time time not null, " +
				"Amount numeric(7,2) not null, " +
				"PeopleNumb integer not null, " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.Users ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"ID varchar(5) not null unique, " +
				"Name varchar(50) not null, " +
				"Password varchar(50) not null, " +
				"Type varchar(8) not null check (Type like 'Admin' or Type like 'Waiter'), " +
				"DBupdate boolean not null, " +
				"primary key (I))";
		Database.update(qry);
		qry = "create table APP.Settings ( " +
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"NameParam varchar(50) not null, " +
				"Value varchar(20) not null, " +
				"primary key (I))";
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