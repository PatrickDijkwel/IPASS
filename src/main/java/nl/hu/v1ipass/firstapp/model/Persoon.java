package nl.hu.v1ipass.firstapp.model;

public class Persoon {
	private String naam;
	private String achternaam;
	private String wachtwoord;
	private String gebruikersnaam;
	
	public Persoon(String nm, String achter, String ww, String gebruiker) {
		this.naam = nm;
		this.achternaam = achter;
		this.wachtwoord = ww;
		this.gebruikersnaam = gebruiker;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

}
