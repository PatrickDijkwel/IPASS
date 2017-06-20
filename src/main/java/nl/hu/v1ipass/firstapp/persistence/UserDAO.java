package nl.hu.v1ipass.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO{
	public String findUserTypeByUsernameAndPassword(String gebruikersnaam, String wachtwoord) {
		String role = null;
		String query = "?";
		
		try (Connection conn = super.getConnection()) {
			
			if (gebruikersnaam.toLowerCase().contains("trainer")) {
				query = "SELECT WACHTWOORD FROM TRAINER WHERE GEBRUIKERSNAAM = ?";
				role = "trainer";
			}
			if (gebruikersnaam.toLowerCase().contains("clublid")) {
				query = "SELECT WACHTWOORD FROM CLUBLID WHERE GEBRUIKERSNAAM = ?";
				role = "clublid";
			}
			if (gebruikersnaam.toLowerCase().contains("medewerker")) {
				query = "SELECT WACHTWOORD FROM CLUBMEDEWERKER WHERE GEBRUIKERSNAAM = ?";
				role = "medewerker";
			}
			
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, gebruikersnaam);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("wachtwoord").equals(wachtwoord)) {
					return role;
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return role = null;
	}

}
