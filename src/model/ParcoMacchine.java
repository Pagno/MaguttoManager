package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.Database;

public class ParcoMacchine{
	
	ArrayList<Macchina> gru;
	
	
	public ParcoMacchine(){
		gru=new ArrayList<Macchina>();
		loadGru();
	}


	private void loadGru() {
		try {

			Database.connect();
			
			String qry="SELECT * FROM APP.Macchina where Tipo='Gru'";
			ResultSet res = Database.interrogate(qry);
			while (res.next()){
				Gru gru=new Gru(res.getString("Produttore"));
			}

			
			
			Database.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
