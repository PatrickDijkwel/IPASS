package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Clublid;
import nl.hu.v1ipass.firstapp.model.Team;

public class ClublidDAO extends BaseDAO{
	private TeamDAO teamDAO = new TeamDAO();
	
	private List<Clublid> selectClubleden(String query) {
		List<Clublid> results = new ArrayList<Clublid>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int lidnummer = rs.getInt("lidnummer");
				String naam = rs.getString("naam");
				String achternaam = rs.getString("achternaam");
				String wachtwoord = rs.getString("wachtwoord");
				String gebruikersnaam = rs.getString("gebruikersnaam");
				
				String teamnaam = rs.getString("teamnaam");
				Team team = teamDAO.findByTeamNaam(teamnaam);
				
				Clublid newClublid = new Clublid(lidnummer, naam, achternaam, wachtwoord, gebruikersnaam);
				
				newClublid.setTeam(team);
				
				results.add(newClublid);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	public List<Clublid> findAllClubleden() {
		return selectClubleden("SELECT * FROM CLUBLID");
	}
	//Zoekt alle clubleden op teamnaam
	public List<Clublid> findClubledenByTeamnaam(String teamnaam) {
		return selectClubleden("SELECT * FROM CLUBLID WHERE TEAMNAAM = '" + teamnaam + "'");
	}
	
	public Clublid findByLidNummer(int lidNummer) {
		return selectClubleden("SELECT * FROM CLUBLID WHERE LIDNUMMER = " + lidNummer).get(0);
	}
	//Methode voor bij het inloggen, om gegevens te verzamelen over wie ingelogd is
	public Clublid findClublidByGebruikersnaam(String gebruikersnaam) {
		return selectClubleden("SELECT * FROM CLUBLID WHERE GEBRUIKERSNAAM = '" + gebruikersnaam + "'").get(0);
	}

}
