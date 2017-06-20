package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Veld;
import nl.hu.v1ipass.firstapp.persistence.ApplicationService;
import nl.hu.v1ipass.firstapp.persistence.ServiceProvider;

@Path("/velden")
public class VeldResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllVelden() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Veld v : service.getAllVelden()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("veldnummer", v.getVeldNummer());
			job.add("veldtype", v.getVeldType());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

}
