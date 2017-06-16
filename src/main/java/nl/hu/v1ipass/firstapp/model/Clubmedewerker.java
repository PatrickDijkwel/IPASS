package nl.hu.v1ipass.firstapp.model;

public class Clubmedewerker extends Persoon{
	private int clubNummer;
	
	public Clubmedewerker(int clubNummer, String naam, String achternaam, String wachtwoord, String gebruikersnaam) {
		super(naam, achternaam, wachtwoord, gebruikersnaam);
		this.clubNummer = clubNummer;
	}

	public int getClubNummer() {
		return clubNummer;
	}

	public void setClubNummer(int clubNummer) {
		this.clubNummer = clubNummer;
	}

}
