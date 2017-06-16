package nl.hu.v1ipass.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Clublid;
import nl.hu.v1ipass.firstapp.model.Training;
import nl.hu.v1ipass.firstapp.model.Trainingsessie;

public class TrainingSessieDAO extends BaseDAO{
	private TrainingDAO trainingDAO = new TrainingDAO();
	private ClublidDAO clublidDAO = new ClublidDAO();
	
	private List<Trainingsessie> selectTrainingSessies(String query) {
		List<Trainingsessie> results = new ArrayList<Trainingsessie>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String status = rs.getString("status");
				
				int lidNummer = rs.getInt("lidnummer");
				Clublid clublid = clublidDAO.findByLidNummer(lidNummer);
				
				int trainingNummer = rs.getInt("trainingnummer");
				Training training = trainingDAO.findByTrainingNummer(trainingNummer);
				
				Trainingsessie newTrainingsessie = new Trainingsessie(status);
				newTrainingsessie.setClublid(clublid);
				newTrainingsessie.setTraining(training);
				
				results.add(newTrainingsessie);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Trainingsessie> findAllTrainingSessies() {
		return selectTrainingSessies("SELECT * FROM TRAININGSESSIE");
	}

}
