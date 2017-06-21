package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Team;
import nl.hu.v1ipass.firstapp.model.Training;
import nl.hu.v1ipass.firstapp.model.Veld;

public class TrainingDAO extends BaseDAO{
	private TeamDAO teamDAO = new TeamDAO();
	private VeldDAO veldDAO = new VeldDAO();
	
	private List<Training> selectTrainingen(String query) {
		List<Training> results = new ArrayList<Training>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				int trainingnummer = rs.getInt("trainingnummer");
				Date datum = rs.getDate("datum");
				String tijdstip = rs.getString("tijdstip");
				
				String teamnaam = rs.getString("teamnaam");
				Team team = teamDAO.findByTeamNaam(teamnaam);
				
				int veldNummer = rs.getInt("veldnummer");
				Veld veld = veldDAO.findByVeldNummer(veldNummer);
				
				Training newTraining = new Training(datum, tijdstip);
				newTraining.setTrainingNummer(trainingnummer);
				newTraining.setTeam(team);
				newTraining.setVeld(veld);
				
				results.add(newTraining);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Training> findAllTrainingen() {
		return selectTrainingen("SELECT * FROM TRAINING");
	}
	//Voert query uit op de database
	//Het return de training met de ingevoerde trainingnummer
	public Training findByTrainingNummer(int trainingnummer) {
		return selectTrainingen("SELECT * FROM TRAINING WHERE TRAININGNUMMER = " + trainingnummer).get(0);
	}
	//Runt een query op de database om de nieuwste Trainingobject in de datbase te vinden
	public Training findLatestTrainingRecord() {
		return selectTrainingen("SELECT * FROM TRAINING ORDER BY TRAININGNUMMER DESC LIMIT 1").get(0);
	}

	public List<Training> findTrainingByTeam(Team team) {
		return selectTrainingen("SELECT * FROM TRAINING WHERE TEAMNAAM = '" + team.getTeamNaam() + "'");
	}
	
	public void createTraining(Training training) {
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO TRAINING (DATUM, TIJDSTIP, TEAMNAAM, VELDNUMMER) VALUES('" +
								training.getDatum() + "', '" +
								training.getTijdstip() + "', '" +
								training.getTeam().getTeamNaam() + "', " +
								training.getVeld().getVeldNummer() + ")");
			System.out.println("Training is toegevoegd!");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
