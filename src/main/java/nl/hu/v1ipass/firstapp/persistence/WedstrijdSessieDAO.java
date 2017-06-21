package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Clublid;
import nl.hu.v1ipass.firstapp.model.Wedstrijd;
import nl.hu.v1ipass.firstapp.model.Wedstrijdsessie;

public class WedstrijdSessieDAO extends BaseDAO{
	private WedstrijdDAO wedstrijdDAO = new WedstrijdDAO();
	private ClublidDAO clublidDAO = new ClublidDAO();
	
	private List<Wedstrijdsessie> selectWedstrijdSessies(String query) {
		List<Wedstrijdsessie> results = new ArrayList<Wedstrijdsessie>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String status = rs.getString("status");
				
				int wedstrijdNummer = rs.getInt("wedstrijdnummer");
				Wedstrijd wedstrijd = wedstrijdDAO.findByWedstrijdNummer(wedstrijdNummer);
				
				int lidNummer = rs.getInt("lidnummer");
				Clublid clublid = clublidDAO.findByLidNummer(lidNummer);
				
				Wedstrijdsessie newWedstrijdSessie = new Wedstrijdsessie();
				newWedstrijdSessie.setStatusAanwezigheid(status);
				newWedstrijdSessie.setWedstrijd(wedstrijd);
				newWedstrijdSessie.setClublid(clublid);
				
				results.add(newWedstrijdSessie);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Wedstrijdsessie> findAllWedstrijdSessies() {
		return selectWedstrijdSessies("SELECT * FROM WEDSTRIJDSESSIE");
	}
	//Voert query uit op de database
	//Zoekt alle wedstrijdsessies met een specifiek lidnummer
	public List<Wedstrijdsessie> findWedstrijdSessieByLidnummer(int lidnummer) {
		return selectWedstrijdSessies("SELECT * FROM WEDSTRIJDSESSIE WHERE LIDNUMMER = " + lidnummer);
	}
	//Voor het zien van de aanwezigheid van de teamleden per wedstrijd
	public List<Wedstrijdsessie> findWedstrijdSessieByWedstrijdnummer(int wedstrijdnummer) {
		return selectWedstrijdSessies("SELECT * FROM WEDSTRIJDSESSIE WHERE WEDSTRIJDNUMMER = " + wedstrijdnummer);
	}
	
	public void createWedstrijdSessie(Wedstrijdsessie wedstrijdsessie) {
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO WEDSTRIJDSESSIE (WEDSTRIJDNUMMER, LIDNUMMER) VALUES (" +
							wedstrijdsessie.getWedstrijd().getWedstrijdNummer() + ", " +
							wedstrijdsessie.getClublid().getLidNummer() + ")" );
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	//Voert update query uit
	//Past de status aan van een clublid bij een wedstrijdsessie
	public void updateWedstrijdSessie(Wedstrijdsessie wedstrijdsessie) {
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			
			if (wedstrijdsessie.getStatusAanwezigheidString() == "afwezig") {
				wedstrijdsessie.setStatusAanwezigheid("aanwezig");
			} else {
				wedstrijdsessie.setStatusAanwezigheid("afwezig");
			}
			
			stmt.executeUpdate("UPDATE WEDSTRIJDSESSIE SET STATUS = '" + wedstrijdsessie.getStatusAanwezigheidString() 
			+ "' WHERE WEDSTRIJDNUMMER = " + wedstrijdsessie.getWedstrijd().getWedstrijdNummer()
			+ " AND LIDNUMMER = " + wedstrijdsessie.getClublid().getLidNummer());			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
}
