package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Kaarten;
import nl.hu.v1ipass.firstapp.persistence.ApplicationService;
import nl.hu.v1ipass.firstapp.persistence.ServiceProvider;

@Path("/kaarten")
public class KaartenResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllKaarten() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Kaarten k : service.getAllKaarten()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("kaartkleur", k.getKaartKleur());
			job.add("lidnummer", k.getLidNummer());
			job.add("wedstrijdnummer", k.getWedstrijdNummer());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

}
