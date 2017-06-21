package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Aanvraag;
import nl.hu.v1ipass.firstapp.model.Trainer;
import nl.hu.v1ipass.firstapp.model.Veld;

public class AanvraagDAO extends BaseDAO{
	TrainerDAO trainerDAO = new TrainerDAO();
	VeldDAO veldDAO = new VeldDAO();
	
	private List<Aanvraag> selectAanvragen(String query) {
		List<Aanvraag> results = new ArrayList<Aanvraag>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int aanvraagNummer = rs.getInt("aanvraagnummer");
				String aanvraagType = rs.getString("aanvraagtype");
				Date aanvraagDatum = rs.getDate("aanvraagDatum");
				String status = rs.getString("status");
				Date datum = rs.getDate("datum");
				String tijdstip = rs.getString("tijdstip");
				
				
				int trainerNummer = rs.getInt("trainernummer");
				Trainer trainer = trainerDAO.findByTrainerNummer(trainerNummer);
				
				int veldnummer = rs.getInt("veldnummer");
				Veld veld = veldDAO.findByVeldNummer(veldnummer);
				
				Aanvraag newAanvraag = new Aanvraag(aanvraagType, aanvraagDatum, datum, tijdstip);
				newAanvraag.setAanvraagNummer(aanvraagNummer);
				newAanvraag.setAanvraagStatus(status);
				newAanvraag.setTrainer(trainer);
				newAanvraag.setVeld(veld);
				
				
				if (rs.getString("tegenstander") != null) {
					String tegenstander = rs.getString("tegenstander");
					newAanvraag.setTegenstander(tegenstander);
				}
				
				results.add(newAanvraag);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	//Deze methode return alle aanvragen
	public List<Aanvraag> findAllAanvragen() {
		return selectAanvragen("SELECT * FROM AANVRAAG");
	}
	//Deze methode geeft de aanvraag op basis van het aanvraagnummer
	public Aanvraag findByAanvraagNummer(int aanvraagNummer) {
		return selectAanvragen("SELECT * FROM AANVRAAG WHERE AANVRAAGNUMMER = " + aanvraagNummer).get(0);
	}
	//Returnt alle aanvragen van de trainer op trainernummer
	public List<Aanvraag> findTrainerAanvragen(int trainernummer) {
		return selectAanvragen("SELECT * FROM AANVRAAG WHERE TRAINERNUMMER = " + trainernummer);
	}
	
	//Update de status van een aanvraag, met een aanvraagnummer
	public void updateAanvraag(int aanvraagnummer, String status) {
		try(Connection conn = getConnection()) {
			Statement stmt = conn.createStatement();
			String query = ("UPDATE AANVRAAG SET STATUS = '" + status + "' WHERE AANVRAAGNUMMER = " + aanvraagnummer);
			stmt.executeUpdate(query);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	//maakt een nieuw aanvraag object aan in de database
	public void createAanvraag(Aanvraag aanvraag) {
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			if(aanvraag.getTegenstander() == null) {
				stmt.executeUpdate("INSERT INTO AANVRAAG (AANVRAAGTYPE, AANVRAAGDATUM, TRAINERNUMMER, DATUM, TIJDSTIP, VELDNUMMER) VALUES ('" +
						aanvraag.getAanvraagType() + "', '" +
						aanvraag.getAanvraagDatum() + "', " +
						aanvraag.getTrainer().getTrainerNummer() + ", '" +
						aanvraag.getDatum() + "', '" +
						aanvraag.getTijdstip() + "', " +
						aanvraag.getVeld().getVeldNummer() + ")"
						);
				System.out.println("Aanvraag voor een training is toegevoegd!");
			} else {
				//Dit is voor wanneer de aanvraag een verzoek is voor een oefenwedstrijd ipv een training
				//Deze heeft ook het attribuut "TEGENSTANDER" erbij zitten
				stmt.executeUpdate("INSERT INTO AANVRAAG (AANVRAAGTYPE, AANVRAAGDATUM, TRAINERNUMMER, DATUM, TIJDSTIP, VELDNUMMER, TEGENSTANDER) VALUES ('" +
						aanvraag.getAanvraagType() + "', '" +
						aanvraag.getAanvraagDatum() + "', " +
						aanvraag.getTrainer().getTrainerNummer() + ", '" +
						aanvraag.getDatum() + "', '" +
						aanvraag.getTijdstip() + "', " +
						aanvraag.getVeld().getVeldNummer() + ", '" +
						aanvraag.getTegenstander() + "')"
				);
				System.out.println("Aanvraag voor een wedstrijd is toegevoegd!");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
