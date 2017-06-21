package nl.hu.v1ipass.firstapp.webservices;

import java.security.Key;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1ipass.firstapp.model.Clublid;
import nl.hu.v1ipass.firstapp.model.Clubmedewerker;
import nl.hu.v1ipass.firstapp.model.Trainer;
import nl.hu.v1ipass.firstapp.persistence.ApplicationService;
import nl.hu.v1ipass.firstapp.persistence.ServiceProvider;
import nl.hu.v1ipass.firstapp.persistence.UserDAO;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();
	//Method voor het inloggen op de webpagina
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String authenticateUser(@FormParam("gebruikersnaam") String gebruikersnaam,
									 @FormParam("wachtwoord") String wachtwoord) {
		
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		//Authenticatie van de gebruiker op de database
		UserDAO dao = new UserDAO();
		//Deze returnt de rol van de gebruiker
		//Mogelijke rollen: medewerker, trainer, clublid
		String role = dao.findUserTypeByUsernameAndPassword(gebruikersnaam.toLowerCase(), wachtwoord);
		
		if (role == null) { throw new IllegalArgumentException("No user found!"); }
		
		//return hier het lidnummer/clubnummer/trainernummer van de gebruiker
		if (role == "clublid") {
			Clublid c = service.findClublidByGebruikersnaam(gebruikersnaam.toLowerCase());
			
			job.add("lidnummer", c.getLidNummer());
		}
		if (role == "trainer") {
			Trainer t = service.findTrainerByGebruikersnaam(gebruikersnaam.toLowerCase());
			
			job.add("trainernummer", t.getTrainerNummer());
		}
		if (role == "medewerker") {
			Clubmedewerker cm = service.findClubmedewerkerByGebruikersnaam(gebruikersnaam.toLowerCase());
			job.add("clubnummer", cm.getClubNummer());
		}
		
		
		job.add("role", role);
		
		JsonObject object = job.build();
		return object.toString();
	}



}
