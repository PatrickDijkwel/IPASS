package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Team;
import nl.hu.v1ipass.firstapp.model.Trainer;

public class TeamDAO extends BaseDAO{
	TrainerDAO trainerDAO = new TrainerDAO();
	
	private List<Team> selectTeams(String query) {
		List<Team> results = new ArrayList<Team>();
		
		try (Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String teamnaam = rs.getString("teamnaam");
				String leeftijdscategorie = rs.getString("leeftijdscategorie");
				
				int trainernummer = rs.getInt("trainernummer");
				Trainer trainer = trainerDAO.findByTrainerNummer(trainernummer);
				
				Team newTeam = new Team(teamnaam, leeftijdscategorie);
				newTeam.setTrainer(trainer);
				
				results.add(newTeam);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Team> findAllTeams() {
		return selectTeams("SELECT * FROM TEAM");
	}
	public Team findByTeamNaam(String teamnaam) {
		return selectTeams("SELECT * FROM TEAM WHERE TEAMNAAM = '" + teamnaam + "'").get(0);
	}
	public Team findByTrainer(int trainernummer) {
		return selectTeams("SELECT * FROM TEAM WHERE TRAINERNUMMER = " + trainernummer).get(0);
	}

}
