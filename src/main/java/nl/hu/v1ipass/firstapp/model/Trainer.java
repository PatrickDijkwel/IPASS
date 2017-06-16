package nl.hu.v1ipass.firstapp.model;


public class Trainer extends Persoon{
	private int trainerNummer;
	
	public Trainer(int trainerNummer, String naam, String achternaam, String wachtwoord, String gebruikersnaam) {
		super(naam, achternaam, wachtwoord, gebruikersnaam);
		this.trainerNummer = trainerNummer;
	}

	public int getTrainerNummer() {
		return trainerNummer;
	}

	public void setTrainerNummer(int trainerNummer) {
		this.trainerNummer = trainerNummer;
	}
	
}
