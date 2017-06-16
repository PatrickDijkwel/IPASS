package nl.hu.v1ipass.firstapp.model;

import java.util.Date;

public class Training {
	private int trainingNummer;
	private Date datum;
	private String tijdstip;
	private Team team;
	private Veld veld;
	
	public Training(int trainingNummer, Date datum, String tijdstip) {
		this.trainingNummer = trainingNummer;
		this.datum = datum;
		this.tijdstip = tijdstip;
	}

	public int getTrainingNummer() {
		return trainingNummer;
	}

	public void setTrainingNummer(int trainingNummer) {
		this.trainingNummer = trainingNummer;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTijdstip() {
		return tijdstip;
	}

	public void setTijdstip(String tijdstip) {
		this.tijdstip = tijdstip;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Veld getVeld() {
		return veld;
	}

	public void setVeld(Veld veld) {
		this.veld = veld;
	}
	

}
