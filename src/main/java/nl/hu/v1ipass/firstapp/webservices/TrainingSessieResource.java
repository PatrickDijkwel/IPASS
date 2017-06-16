package nl.hu.v1ipass.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Trainingsessie;
import nl.hu.v1ipass.persistence.ApplicationService;
import nl.hu.v1ipass.persistence.ServiceProvider;

@Path("/trainingsessies")
public class TrainingSessieResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllTrainingSessies() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Trainingsessie t : service.getAllTrainingSessies()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("status", t.getStatusAanwezigheidString());
			job.add("lidnummer", t.getClublid().getLidNummer());
			job.add("trainingnummer", t.getTraining().getTrainingNummer());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	

}
