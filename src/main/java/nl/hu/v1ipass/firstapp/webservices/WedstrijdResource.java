package nl.hu.v1ipass.firstapp.webservices;

import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Wedstrijd;
import nl.hu.v1ipass.persistence.ApplicationService;
import nl.hu.v1ipass.persistence.ServiceProvider;

@Path("/wedstrijden")
public class WedstrijdResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllWedstrijden() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Wedstrijd w : service.getAllWedstrijden()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = sdf.format(w.getDatum());
			
			job.add("wedstrijdnummer", w.getWedstrijdNummer());
			job.add("datum", dateString);
			job.add("tijdstip", w.getTijdstip());
			job.add("tegenstander", w.getTegenstander());
			if (w.getPuntenVoor() == null) {
				job.add("puntenvoor", "null");
			} else {
				job.add("puntenvoor", w.getPuntenVoor());
			}
			if (w.getPuntenTegen() == null) {
				job.add("puntentegen", "null");
			} else {
				job.add("puntentegen", w.getPuntenTegen());
			}
			job.add("teamnaam", w.getTeam().getTeamNaam());
			job.add("veldnummer", w.getVeld().getVeldNummer());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Produces("application/json")
	@Path("{trainernummer}")
	public String getWedstrijdByTrainer(@PathParam("trainernummer") int trainernummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Wedstrijd w : service.getWedstrijdByTrainer(trainernummer)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = sdf.format(w.getDatum());
			
			job.add("wedstrijdnummer", w.getWedstrijdNummer());
			job.add("datum", dateString);
			job.add("tijdstip", w.getTijdstip());
			job.add("tegenstander", w.getTegenstander());
			if (w.getPuntenVoor() == null) {
				job.add("puntenvoor", "null");
			} else {
				job.add("puntenvoor", w.getPuntenVoor());
			}
			if (w.getPuntenTegen() == null) {
				job.add("puntentegen", "null");
			} else {
				job.add("puntentegen", w.getPuntenTegen());
			}
			job.add("teamnaam", w.getTeam().getTeamNaam());
			job.add("veldnummer", w.getVeld().getVeldNummer());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

}
