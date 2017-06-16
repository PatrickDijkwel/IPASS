package nl.hu.v1ipass.firstapp.webservices;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Team;
import nl.hu.v1ipass.firstapp.model.Training;
import nl.hu.v1ipass.persistence.ApplicationService;
import nl.hu.v1ipass.persistence.ServiceProvider;

@Path("/trainingen")
public class TrainingResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllTrainingen() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Training t : service.getAllTrainingen()) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateFormat = sdf.format(t.getDatum());
			
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("trainingnummer", t.getTrainingNummer());
			job.add("datum", dateFormat);
			job.add("tijdstip", t.getTijdstip());
			job.add("teamnaam", t.getTeam().getTeamNaam());
			job.add("veldnummer", t.getVeld().getVeldNummer());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Produces("application/json")
	@Path("{trainernummer}")
	public String getTrainingByTrainer(@PathParam("trainernummer") int trainernummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		Team team = service.getTeamByTrainer(trainernummer);
		for (Training t : service.getTrainingByTeam(team)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateFormat = sdf.format(t.getDatum());
			
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("trainingnummer", t.getTrainingNummer());
			job.add("datum", dateFormat);
			job.add("tijdstip", t.getTijdstip());
			job.add("teamnaam", t.getTeam().getTeamNaam());
			job.add("veldnummer", t.getVeld().getVeldNummer());
			
			//Dag van de week in JSON object zetten
			SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
			String weekDag = sdf2.format(t.getDatum());
			job.add("dagnaam", weekDag);
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

}
