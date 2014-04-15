import database.Database;
import database.JavaDBException;
import model.ParcoMacchine;


public class Main {

	/**
	 * @param args
	 * @throws JavaDBException 
	 */
	public static void main(String[] args) throws JavaDBException {
		// TODO Auto-generated method stub
		//ParcoMacchine pm=new ParcoMacchine();
		Database db=new Database();
		db.connect();
		db.popola();
		db.disconnect();
	}

}