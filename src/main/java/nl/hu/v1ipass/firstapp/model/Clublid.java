package nl.hu.v1ipass.firstapp.model;

public class Clublid extends Persoon{
	private int lidNummer;
	private Team team;
	
	public Clublid(int lidNummer, String naam, String achternaam, String wachtwoord, String gebruikersnaam) {
		super(naam, achternaam, wachtwoord, gebruikersnaam);
		this.lidNummer = lidNummer;
	}

	public int getLidNummer() {
		return lidNummer;
	}

	public void setLidNummer(int lidNummer) {
		this.lidNummer = lidNummer;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
}
