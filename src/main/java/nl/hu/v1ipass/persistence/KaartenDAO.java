package nl.hu.v1ipass.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Kaarten;

public class KaartenDAO extends BaseDAO{
	private List<Kaarten> selectKaarten(String query) {
		List<Kaarten> results = new ArrayList<Kaarten>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String kaartKleur = rs.getString("kaart_kleur");
				int lidNummer = rs.getInt("lidnummer");
				int wedstrijdNummer = rs.getInt("wedstrijdnummer");
				
				Kaarten newKaarten = new Kaarten(kaartKleur, lidNummer, wedstrijdNummer);
				
				results.add(newKaarten);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Kaarten> findAllKaarten() {
		return selectKaarten("SELECT * FROM KAARTEN");
	}

}
