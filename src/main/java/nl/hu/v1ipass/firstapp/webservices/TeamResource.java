package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Team;
import nl.hu.v1ipass.persistence.ApplicationService;
import nl.hu.v1ipass.persistence.ServiceProvider;

@Path("/teams")
public class TeamResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllTeams() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Team t : service.getAllTeams()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("teamnaam", t.getTeamNaam());
			job.add("leeftijdscategorie", t.getLeeftijdsCategorie());
			job.add("trainernummer", t.getTrainer().getTrainerNummer());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@GET
	@Produces("application/json")
	@Path("{trainernummer}")
	public String getTeamByTrainer(@PathParam("trainernummer") int trainernummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		Team t = service.getTeamByTrainer(trainernummer);
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("teamnaam", t.getTeamNaam());
		job.add("leeftijdscategorie", t.getLeeftijdsCategorie());
		job.add("trainernummer", t.getTrainer().getTrainerNummer());
		
		return job.build().toString();
		
	}

}
