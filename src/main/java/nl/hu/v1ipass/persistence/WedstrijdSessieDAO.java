package nl.hu.v1ipass.persistence;

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
				
				Wedstrijdsessie newWedstrijdSessie = new Wedstrijdsessie(status);
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
	
}
