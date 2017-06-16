package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Trainer;
import nl.hu.v1ipass.persistence.ApplicationService;
import nl.hu.v1ipass.persistence.ServiceProvider;

@Path("/trainers")
public class TrainerResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllTrainers() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Trainer t : service.getAllTrainers()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("trainernummer", t.getTrainerNummer());
			job.add("naam", t.getNaam());
			job.add("achternaam", t.getAchternaam());
			job.add("wachtwoord", t.getWachtwoord());
			job.add("gebruikersnaam", t.getGebruikersnaam());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
}
