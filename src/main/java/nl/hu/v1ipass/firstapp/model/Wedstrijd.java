package nl.hu.v1ipass.firstapp.model;

import java.util.Date;

public class Wedstrijd {
	private int wedstrijdNummer;
	private Date datum;
	private String tijdstip;
	private String tegenstander;
	private String puntenVoor;
	private String puntenTegen;
	private Team team;
	private Veld veld;
	
	public Wedstrijd(Date datum, String tijdstip, String tegenstander) {
		this.datum = datum;
		this.tijdstip = tijdstip;
		this.tegenstander = tegenstander;
	}

	public int getWedstrijdNummer() {
		return wedstrijdNummer;
	}

	public void setWedstrijdNummer(int wedstrijdNummer) {
		this.wedstrijdNummer = wedstrijdNummer;
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

	public String getTegenstander() {
		return tegenstander;
	}

	public void setTegenstander(String tegenstander) {
		this.tegenstander = tegenstander;
	}

	public String getPuntenVoor() {
		return puntenVoor;
	}

	public void setPuntenVoor(String puntenVoor) {
		this.puntenVoor = puntenVoor;
	}

	public String getPuntenTegen() {
		return puntenTegen;
	}

	public void setPuntenTegen(String puntenTegen) {
		this.puntenTegen = puntenTegen;
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
