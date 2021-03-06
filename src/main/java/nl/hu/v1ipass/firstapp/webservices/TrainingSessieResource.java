package nl.hu.v1ipass.firstapp.webservices;

import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import nl.hu.v1ipass.firstapp.model.Clublid;
import nl.hu.v1ipass.firstapp.model.Training;
import nl.hu.v1ipass.firstapp.model.Trainingsessie;
import nl.hu.v1ipass.firstapp.persistence.ApplicationService;
import nl.hu.v1ipass.firstapp.persistence.ServiceProvider;

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
	@GET
	@Produces("application/json")
	@Path("/clublid/{lidnummer}")
	//Return de informatie voor alle trainingen die de clublid moet bijwonen
	//Bij de informatie zit ook de status van het clublid voor die specifieke trainingsessi die hoort bij de training
	public String getTrainingsessieByLidnummer(@PathParam("lidnummer") int lidnummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Trainingsessie ts : service.findTrainingSessieByLidnummer(lidnummer)) {
			Training t = service.findTrainingByTrainingnummer(ts.getTraining().getTrainingNummer());
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = sdf.format(t.getDatum());
			
			job.add("trainingnummer", t.getTrainingNummer());
			
			job.add("datum", dateString);
			job.add("tijdstip", t.getTijdstip());
			job.add("veldnummer", t.getVeld().getVeldNummer());
			job.add("status", ts.getStatusAanwezigheidString());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	//Deze resource wordt aangeroepen in overzichtTrainingenT.html
	//Hier wordt de informatie verzameld over de aanwezigheid per clublid van een training
	@GET
	@Produces("application/json")
	@Path("/aanwezigheid")
	public String getAanwezigheidByTrainingnummer(@QueryParam("trainingnummer") int trainingnummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Trainingsessie ts : service.findTrainingSessieByTrainingnummer(trainingnummer)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("naam", ts.getClublid().getNaam());
			job.add("achternaam", ts.getClublid().getAchternaam());
			job.add("status", ts.getStatusAanwezigheidString());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	
	@POST
	@Produces("application/json")
	@Path("/aanmaken")
	//Maakt alle trainingsessie aan voor de clubleden met die teamnaam
	//Trainingnummer komt van het trainingobject dat aangemaakt werd bij het goedkeuren van de aanvraag voor een training
	public void createTrainingSessie(@QueryParam("trainingnummer") int trainingnummer,
										@QueryParam("teamnaam") String teamnaam) {
		ApplicationService service = ServiceProvider.getApplicationService();
		for (Clublid c : service.findClubledenByTeamnaam(teamnaam)) {
			Trainingsessie newTs = new Trainingsessie();
			newTs.setClublid(c);
			newTs.setTraining(service.findTrainingByTrainingnummer(trainingnummer));
			service.createTrainingSessie(newTs);
		}
	}
	@PUT
	@Produces("application/json")
	@Path("/update")
	//Past de status aan van het clublid bij die specifieke training
	public void updateAanwezigheidClublid(@QueryParam("aanwezigheid") String status,
											@QueryParam("trainingnummer") int trainingnummer,
											@QueryParam("lidnummer") int lidnummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		Trainingsessie newTs = new Trainingsessie(); 
		
		newTs.setClublid(service.findClublidByLidnummer(lidnummer));
		newTs.setTraining(service.findTrainingByTrainingnummer(trainingnummer));
		newTs.setStatusAanwezigheid(status);

		service.updateTrainingSessie(newTs);
	}

}
