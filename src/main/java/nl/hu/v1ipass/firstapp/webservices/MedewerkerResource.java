package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Clubmedewerker;
import nl.hu.v1ipass.firstapp.persistence.ApplicationService;
import nl.hu.v1ipass.firstapp.persistence.ServiceProvider;

@Path("/medewerkers")
public class MedewerkerResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllMedewerkers() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Clubmedewerker cm : service.getAllClubmedewerkers()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("clubnummer", cm.getClubNummer());
			job.add("naam", cm.getNaam());
			job.add("achternaam", cm.getAchternaam());
			job.add("wachtwoord", cm.getWachtwoord());
			job.add("gebruikersnaam", cm.getGebruikersnaam());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

}
