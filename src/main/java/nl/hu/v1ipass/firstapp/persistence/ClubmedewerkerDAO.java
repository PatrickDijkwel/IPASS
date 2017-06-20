package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Clubmedewerker;

public class ClubmedewerkerDAO extends BaseDAO {
	//Geeft een lijst aan Clubmedewerker-objecten terug
	//Op basis van de query die wordt ingevoerd
	private List<Clubmedewerker> selectClubmedewerkers(String query) {
		List<Clubmedewerker> results = new ArrayList<Clubmedewerker>();
		
		try (Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int clubnummer = rs.getInt("clubnummer");
				String naam = rs.getString("naam");
				String achternaam = rs.getString("achternaam");
				String wachtwoord = rs.getString("wachtwoord");
				String gebruikersnaam = rs.getString("gebruikersnaam");
				
				Clubmedewerker newMedewerker = new Clubmedewerker(clubnummer, naam, achternaam, wachtwoord, gebruikersnaam);
				
				results.add(newMedewerker);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	public List<Clubmedewerker> findAllMedewerkers() {
		return selectClubmedewerkers("SELECT * FROM CLUBMEDEWERKER");
	}
	
	public Clubmedewerker findByClubNummer(int clubNummer) {
		return selectClubmedewerkers("SELECT * FROM CLUBMEDEWERKER WHERE CLUBNUMMER = " + clubNummer).get(0);
	}
	//Methode voor bij het inloggen, om gegevens te verzamelen over wie ingelogd is
	public Clubmedewerker findClubmedewerkerByGebruikersnaam(String gebruikersnaam) {
		return selectClubmedewerkers("SELECT * FROM CLUBMEDEWERKER WHERE GEBRUIKERSNAAM = '" + gebruikersnaam + "'").get(0);
	}
}
