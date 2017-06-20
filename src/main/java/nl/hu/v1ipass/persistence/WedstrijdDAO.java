package nl.hu.v1ipass.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Team;
import nl.hu.v1ipass.firstapp.model.Veld;
import nl.hu.v1ipass.firstapp.model.Wedstrijd;


public class WedstrijdDAO extends BaseDAO{
	private TeamDAO teamDAO = new TeamDAO();
	private VeldDAO veldDAO = new VeldDAO();
	
	private List<Wedstrijd> selectWedstrijden(String query) {
		List<Wedstrijd> results = new ArrayList<Wedstrijd>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int wedstrijdnummer = rs.getInt("wedstrijdnummer");
				Date datum = rs.getDate("datum");
				String tijdstip = rs.getString("tijdstip");
				String tegenstander = rs.getString("tegenstander");			
				
				String teamnaam = rs.getString("teamnaam");
				Team team = teamDAO.findByTeamNaam(teamnaam);
				
				int veldNummer = rs.getInt("veldnummer");
				Veld veld = veldDAO.findByVeldNummer(veldNummer);
				
				Wedstrijd newWedstrijd = new Wedstrijd(datum, tijdstip, tegenstander);
				newWedstrijd.setWedstrijdNummer(wedstrijdnummer);
				newWedstrijd.setTeam(team);
				newWedstrijd.setVeld(veld);
				
				if (rs.getString("punten_voor") != null && rs.getString("punten_tegen") != null) {
					String puntenVoor = rs.getString("punten_voor");
					String puntenTegen = rs.getString("punten_tegen");
					newWedstrijd.setPuntenVoor(puntenVoor);
					newWedstrijd.setPuntenTegen(puntenTegen);
				}
				results.add(newWedstrijd);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Wedstrijd> findAllWedstrijden() {
		return selectWedstrijden("SELECT * FROM WEDSTRIJD");
	}

	public Wedstrijd findByWedstrijdNummer(int wedstrijdNummer) {
		return selectWedstrijden("SELECT * FROM WEDSTRIJD WHERE WEDSTRIJDNUMMER = " + wedstrijdNummer).get(0);
	}
	
	public Wedstrijd findLatestWedstrijdRecord() {
		return selectWedstrijden("SELECT * FROM WEDSTRIJD ORDER BY WEDSTRIJDNUMMER DESC LIMIT 1").get(0);
	}
	
	public List<Wedstrijd> findWedstrijdByTeam(Team team) {
		return selectWedstrijden("SELECT * FROM WEDSTRIJD WHERE TEAMNAAM = '" + team.getTeamNaam() + "'");
	}
	
	public void createWedstrijd(Wedstrijd wedstrijd) {
		try(Connection conn = super.getConnection()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO WEDSTRIJD (DATUM, TIJDSTIP, TEGENSTANDER, TEAMNAAM, VELDNUMMER) VALUES('" +
					wedstrijd.getDatum() + "', '" +
					wedstrijd.getTijdstip() + "', '" +
					wedstrijd.getTegenstander() + "', '" +
					wedstrijd.getTeam().getTeamNaam() + "', " +
					wedstrijd.getVeld().getVeldNummer() + ")");
			System.out.println("Wedstrijd is toegevoegd!");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	

}
