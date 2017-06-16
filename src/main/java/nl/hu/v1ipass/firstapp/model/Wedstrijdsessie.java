package nl.hu.v1ipass.firstapp.model;


public class Wedstrijdsessie {
	private Clublid clublid;
	private Wedstrijd wedstrijd;
	private Status statusAanwezigheid;
	private enum Status {
		AANWEZIG("aanwezig"), AFWEZIG("afwezig");
		
		private String key;
		
		private Status(String key) {
			this.key = key;
		}
		
		private String getKey() {
			return key;
		}
	}
	
	public Wedstrijdsessie(String status) {
		if (statusAanwezigheid == null) {
			statusAanwezigheid = Status.AANWEZIG;
		} else {
			this.setStatusAanwezigheid(status);
		}
	}
	
	public Status getStatusAanwezigheid() {
		return statusAanwezigheid;
	}
	
	public String getStatusAanwezigheidString() {
		return statusAanwezigheid.getKey();
	}
	
	public void setStatusAanwezigheid(String key) {
		switch(key) {
		case "aanwezig":
			statusAanwezigheid = Status.AANWEZIG;
			break;
		case "afwezig":
			statusAanwezigheid = Status.AFWEZIG;
			break;
		default:
			break;
		}
	}

	public Clublid getClublid() {
		return clublid;
	}

	public void setClublid(Clublid clublid) {
		this.clublid = clublid;
	}

	public Wedstrijd getWedstrijd() {
		return wedstrijd;
	}

	public void setWedstrijd(Wedstrijd wedstrijd) {
		this.wedstrijd = wedstrijd;
	}

}
