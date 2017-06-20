package nl.hu.v1ipass.firstapp.model;

public class Trainingsessie {
	private Clublid clublid;
	private Training training;
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
	
	public Trainingsessie() {

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

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

}
