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
import nl.hu.v1ipass.firstapp.model.Wedstrijd;
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
	@GET
	@Produces("application/json")
	@Path("/clublid/{lidnummer}")
	public String getWedstrijdSessieByLidnummer(@PathParam("lidnummer") int lidnummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Wedstrijdsessie ws : service.findWedstrijdSessieByLidnummer(lidnummer)) {
			Wedstrijd w = service.findWedstrijdByWedstrijdnummer(ws.getWedstrijd().getWedstrijdNummer());
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = sdf.format(w.getDatum());
			
			job.add("wedstrijdnummer", w.getWedstrijdNummer());
			job.add("datum", dateString);
			job.add("tijdstip", w.getTijdstip());
			job.add("tegenstander", w.getTegenstander());
			job.add("veldnummer", w.getVeld().getVeldNummer());
			job.add("status", ws.getStatusAanwezigheidString());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@POST
	@Produces("application/json")
	@Path("/aanmaken")
	public void createWedstrijdSessie(@QueryParam("wedstrijdnummer") int wedstrijdnummer,
										@QueryParam("teamnaam") String teamnaam) {
		ApplicationService service = ServiceProvider.getApplicationService();
		for (Clublid c : service.findClubledenByTeamnaam(teamnaam)) {
			Wedstrijdsessie newWs = new Wedstrijdsessie();
			newWs.setClublid(c);
			newWs.setWedstrijd(service.findWedstrijdByWedstrijdnummer(wedstrijdnummer));
			service.createWedstrijdSessie(newWs);
		}
	}
	@PUT
	@Produces("application/json")
	@Path("/update")
	public void updateAanwezigheidClublid(@QueryParam("aanwezigheid") String status,
											@QueryParam("wedstrijdnummer") int wedstrijdnummer,
											@QueryParam("lidnummer") int lidnummer) {
		ApplicationService service = ServiceProvider.getApplicationService();
		Wedstrijdsessie newWs = new Wedstrijdsessie();
		
		newWs.setClublid(service.findClublidByLidnummer(lidnummer));
		newWs.setWedstrijd(service.findWedstrijdByWedstrijdnummer(wedstrijdnummer));
		newWs.setStatusAanwezigheid(status);
		
		service.updateWedstrijdSessie(newWs);
	}
}
