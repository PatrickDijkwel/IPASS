package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Clublid;
import nl.hu.v1ipass.firstapp.persistence.ApplicationService;
import nl.hu.v1ipass.firstapp.persistence.ServiceProvider;

@Path("/clubleden")
public class ClublidResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllClubleden() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Clublid c : service.getAllClubleden()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("lidnummer", c.getLidNummer());
			job.add("naam", c.getNaam());
			job.add("achternaam", c.getAchternaam());
			job.add("wachtwoord", c.getWachtwoord());
			job.add("gebruikersnaam", c.getGebruikersnaam());
			job.add("teamnaam", c.getTeam().getTeamNaam());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@GET
	@Produces("application/json")
	@Path("{lidnummer}")
	public String getClublidByLidnummer(@PathParam("lidnummer") int lidnummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		Clublid c = service.findClublidByLidnummer(lidnummer);
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("lidnummer", c.getLidNummer());
		job.add("naam", c.getNaam());
		job.add("achternaam", c.getAchternaam());
		job.add("wachtwoord", c.getWachtwoord());
		job.add("gebruikersnaam", c.getGebruikersnaam());
		job.add("teamnaam", c.getTeam().getTeamNaam());
		JsonObject object= job.build();
		return object.toString();
	}
}
