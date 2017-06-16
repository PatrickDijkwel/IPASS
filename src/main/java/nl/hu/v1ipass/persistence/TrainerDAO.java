package nl.hu.v1ipass.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Trainer;

public class TrainerDAO extends BaseDAO{	
	private List<Trainer> selectTrainers(String query) {
		List<Trainer> results = new ArrayList<Trainer>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int trainernummer = rs.getInt("trainernummer");
				String naam = rs.getString("naam");
				String achternaam = rs.getString("achternaam");
				String wachtwoord = rs.getString("wachtwoord");
				String gebruikersnaam = rs.getString("gebruikersnaam");
	
				Trainer newTrainer = new Trainer(trainernummer, naam, achternaam, wachtwoord, gebruikersnaam);

				results.add(newTrainer);
			} 
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Trainer> findAllTrainers() {
		return selectTrainers("SELECT * FROM TRAINER");
	}
	public Trainer findByTrainerNummer(int trainernummer) {
		return selectTrainers("SELECT * FROM TRAINER WHERE TRAINERNUMMER = " + trainernummer).get(0);
	}
	
}
