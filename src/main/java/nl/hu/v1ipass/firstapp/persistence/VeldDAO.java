package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1ipass.firstapp.model.Veld;

public class VeldDAO extends BaseDAO{
	private List<Veld> selectVelden(String query) {
		List<Veld> results = new ArrayList<Veld>();
		
		try(Connection conn = super.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int veldNummer = rs.getInt("veldnummer");
				String veldType = rs.getString("veldtype");
				
				Veld newVeld = new Veld(veldNummer, veldType);
				
				results.add(newVeld);
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	public List<Veld> findAllVelden() {
		return selectVelden("SELECT * FROM VELD");
	}
	public Veld findByVeldNummer(int veldNummer) {
		return selectVelden("SELECT * FROM VELD WHERE VELDNUMMER = " + veldNummer).get(0);
	}

}
