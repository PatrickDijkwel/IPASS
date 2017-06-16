package nl.hu.v1ipass.firstapp.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1ipass.firstapp.model.Aanvraag;
import nl.hu.v1ipass.persistence.ApplicationService;
import nl.hu.v1ipass.persistence.ServiceProvider;

@Path("/aanvragen")
public class AanvraagResource {
	ApplicationService service = ServiceProvider.getApplicationService();
	
	@GET
	@Produces("application/json")
	public String getAllAanvragen() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Aanvraag a : service.getAllAanvragen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String aanvraagDateString = sdf.format(a.getAanvraagDatum());
			String dateString = sdf.format(a.getDatum());
			
			job.add("aanvraagnummer", a.getAanvraagNummer());
			job.add("aanvraagtype", a.getAanvraagType());
			job.add("aanvraagdatum", aanvraagDateString);
			job.add("status", a.getAanvraagStatusString());
			job.add("trainernummer", a.getTrainer().getTrainerNummer());
			job.add("datum", dateString);
			job.add("tijdstip", a.getTijdstip());
			job.add("veldnummer", a.getVeld().getVeldNummer());
			
			if (a.getTegenstander() == null) {
				job.add("tegenstander", "null");
			} else {
				job.add("tegenstander", a.getTegenstander());
			}
			
			
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();		
	}
	
	@GET
	@Produces("application/json")
	@Path("{trainernummer}")
	public String getTrainerAanvragen(@PathParam("trainernummer") int trainernummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Aanvraag a : service.getTrainerAanvragen(trainernummer)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String aanvraagDateString = sdf.format(a.getAanvraagDatum());
			String dateString = sdf.format(a.getDatum());
			
			job.add("aanvraagnummer", a.getAanvraagNummer());
			job.add("aanvraagtype", a.getAanvraagType());
			job.add("aanvraagdatum", aanvraagDateString);
			job.add("status", a.getAanvraagStatusString());
			job.add("trainernummer", a.getTrainer().getTrainerNummer());
			job.add("datum", dateString);
			job.add("tijdstip", a.getTijdstip());
			job.add("veldnummer", a.getVeld().getVeldNummer());
			
			if (a.getTegenstander() == null) {
				job.add("tegenstander", "null");
			} else {
				job.add("tegenstander", a.getTegenstander());
			}
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();	
	}
	@GET
	@Produces("application/json")
	@Path("/overzicht")
	public String getOverzichtAanvragen() {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Aanvraag a : service.getAllAanvragen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String aanvraagDateString = sdf.format(a.getAanvraagDatum());
			String dateString = sdf.format(a.getDatum());
			
			job.add("aanvraagnummer", a.getAanvraagNummer());
			job.add("aanvraagtype", a.getAanvraagType());
			job.add("aanvraagdatum", aanvraagDateString);
			job.add("status", a.getAanvraagStatusString());
			job.add("trainernummer", a.getTrainer().getTrainerNummer());
			job.add("datum", dateString);
			job.add("tijdstip", a.getTijdstip());
			job.add("veldnummer", a.getVeld().getVeldNummer());
			job.add("teamnaam", service.getTeamByTrainer(a.getTrainer().getTrainerNummer()).getTeamNaam());
			job.add("trainernaam", a.getTrainer().getNaam() + " " + a.getTrainer().getAchternaam());

			
			if (a.getTegenstander() == null) {
				job.add("tegenstander", "null");
			} else {
				job.add("tegenstander", a.getTegenstander());
			}
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@PUT
	@Path("{aanvraagnummer}")
	public void updateAanvraagStatus(@PathParam("aanvraagnummer") int aanvraagnummer, @FormParam("status") String status) {
		ApplicationService service = ServiceProvider.getApplicationService();
		service.updateAanvraagStatus(aanvraagnummer, status); 
	}
	@POST
	@Produces("application/json")
	@Path("/training")
	public void createAanvraag(@FormParam("datum") String datum,
								 @FormParam("tijdstip") String tijdstip,
								 @FormParam("veld") int veldnummer,
								 @FormParam("trainernummer") int trainernummer) throws ParseException {
		String aanvraagtype = "Training";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = sdf.parse(datum);
		ApplicationService service = ServiceProvider.getApplicationService();
		Date aanvraagDatum = new Date();
		
		Aanvraag newAanvraag = new Aanvraag(aanvraagtype, aanvraagDatum, dt, tijdstip);
		newAanvraag.setVeld(service.findVeldByVeldnummer(veldnummer));
		newAanvraag.setTrainer(service.findTrainerByTrainernummer(trainernummer));
		service.createAanvraag(newAanvraag);
	}
	@POST
	@Produces("application/json")
	@Path("/wedstrijd")
	public void createAanvraag(@FormParam("datum") String datum,
								 @FormParam("tijdstip") String tijdstip,
								 @FormParam("veld") int veldnummer,
								 @FormParam("tegenstander") String tegenstander,
								 @FormParam("trainernummer") int trainernummer) throws ParseException {
		String aanvraagtype = "Oefenwedstrijd";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = sdf.parse(datum);
		ApplicationService service = ServiceProvider.getApplicationService();
		Date aanvraagDatum = new Date();
		
		Aanvraag newAanvraag = new Aanvraag(aanvraagtype, aanvraagDatum, dt, tijdstip);
		newAanvraag.setTegenstander(tegenstander);
		newAanvraag.setVeld(service.findVeldByVeldnummer(veldnummer));
		newAanvraag.setTrainer(service.findTrainerByTrainernummer(trainernummer));
		service.createAanvraag(newAanvraag);
	}

}
