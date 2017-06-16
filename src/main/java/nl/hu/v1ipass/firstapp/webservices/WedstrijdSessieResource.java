package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Wedstrijdsessie;
import nl.hu.v1ipass.persistence.ApplicationService;
import nl.hu.v1ipass.persistence.ServiceProvider;

@Path("/wedstrijdsessies")
public class WedstrijdSessieResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllWedstrijdSessies() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Wedstrijdsessie w : service.getAllWedstrijdSessies()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("status", w.getStatusAanwezigheidString());
			job.add("wedstrijdnummer", w.getWedstrijd().getWedstrijdNummer());
			job.add("lidnummer", w.getClublid().getLidNummer());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

}
